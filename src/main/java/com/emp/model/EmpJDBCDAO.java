package com.emp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface {
	public static final String driver = "com.mysql.cj.jdbc.Driver";   
	public static final String url = "jdbc:mysql://localhost:3306/dobuy?serverTimezone=Asia/Taipei";
	public static final String userid = "root";
	public static final String passwd = "Ruru3089";

	private static final String INSERT_STMT = 
		"INSERT INTO counterInform (counterInformNo, counterNo, informMsg, informDate)  VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT counterInformNo, counterNo, informMsg, informDate, informRead FROM counterInform order by counterInformNo";
	private static final String GET_ONE_STMT = 
		"SELECT counterInformNo, counterNo, informMsg, informDate, informRead FROM counterInform where counterInformNo = ?";
	private static final String DELETE = 
		"DELETE FROM counterInform where counterInformNo = ?";
	private static final String UPDATE = 
		"UPDATE counterInform set informMsg=?, informDate=? where counterInformNo = ?";

	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, empVO.getCounterInformNo());
			pstmt.setInt(2, empVO.getCounterNo());
			pstmt.setString(3, empVO.getInformMsg());
			pstmt.setDate(4, empVO.getInformDate());
			

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt);
		}
	}

	@Override
	public void update(EmpVO empVO) {
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        Class.forName(driver);
	        con = DriverManager.getConnection(url, userid, passwd);
	        pstmt = con.prepareStatement(UPDATE);

	        pstmt.setString(1, empVO.getInformMsg()); // 設置訊息
	        pstmt.setDate(2, empVO.getInformDate());  // 設置日期，確保是 java.sql.Date 類型
	        pstmt.setInt(3, empVO.getCounterInformNo());  // 設置編號

	        pstmt.executeUpdate();
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
	        closeResources(con, pstmt);
	    }
	}

	@Override
	public void delete(Integer counterInformNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, counterInformNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public EmpVO findByPrimaryKey(Integer counterInformNo) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, counterInformNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setCounterInformNo(rs.getInt("counterInformNo"));
				empVO.setCounterNo(rs.getInt("counterNo"));
				empVO.setInformMsg(rs.getString("informMsg"));
				empVO.setInformDate(rs.getDate("informDate"));
				empVO.setInformRead(rs.getInt("informRead"));
				
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt, rs);
		}
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				empVO = new EmpVO();
				empVO.setCounterInformNo(rs.getInt("counterInformNo"));
				empVO.setCounterNo(rs.getInt("counterNo"));
				empVO.setInformMsg(rs.getString("informMsg"));
				empVO.setInformDate(rs.getDate("informDate"));
				empVO.setInformRead(rs.getInt("informRead"));
				
				list.add(empVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
			closeResources(con, pstmt, rs);
		}
		return list;
	}

	private void closeResources(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		closeResources(con, pstmt);
	}
	
	public static void main(String[] args) {
		EmpJDBCDAO dao =new EmpJDBCDAO();
		List<EmpVO> empVO = dao.getAll();
		for(EmpVO emp :empVO) {
			System.out.println(emp.getCounterInformNo());
		}
	}
}
