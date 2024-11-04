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
		"INSERT INTO auth (managerNo, managerName, managerAccount, authNo, authTitle, authContext) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT managerNo, managerName, managerAccount, authNo, authTitle, authContext FROM auth order by managerNo";
	private static final String GET_ONE_STMT = 
		"SELECT managerNo, managerName, managerAccount, authNo, authTitle, authContext FROM auth where managerNo = ?";
	private static final String DELETE = 
		"DELETE FROM auth where managerNo = ?";
	private static final String UPDATE = 
		"UPDATE auth set managerName=?, managerAccount=?, authNo=?, authTitle=?, authContext=? where empNo = ?";

	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, empVO.getManagerNo());
			pstmt.setString(2, empVO.getManagerName());
			pstmt.setString(3, empVO.getManagerAccount());
			pstmt.setInt(4, empVO.getAuthNo());
			pstmt.setString(5, empVO.getAuthTitle());
			pstmt.setString(6, empVO.getAuthContext());

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
			
			pstmt.setInt(1, empVO.getManagerNo());
			pstmt.setString(2, empVO.getManagerName());
			pstmt.setString(3, empVO.getManagerAccount());
			pstmt.setInt(4, empVO.getAuthNo());
			pstmt.setString(5, empVO.getAuthTitle());
			pstmt.setString(6, empVO.getAuthContext());

			

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
	public void delete(Integer managerNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, managerNo);

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
	public EmpVO findByPrimaryKey(Integer managerNo) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, managerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pstmt.setInt(1, empVO.getManagerNo());
				pstmt.setString(2, empVO.getManagerName());
				pstmt.setString(3, empVO.getManagerAccount());
				pstmt.setInt(4, empVO.getAuthNo());
				pstmt.setString(5, empVO.getAuthTitle());
				pstmt.setString(6, empVO.getAuthContext());

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
				empVO.setManagerNo(rs.getInt("managerNo"));
				empVO.setManagerName(rs.getString("managerName"));
				empVO.setManagerAccount(rs.getString("managerAccount"));
				empVO.setAuthNo(rs.getInt("authNo"));
				empVO.setAuthTitle(rs.getString("authTitle"));
				empVO.setAuthContext(rs.getString("authContext"));
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
			System.out.println(emp.getManagerName());
		}
	}
}
