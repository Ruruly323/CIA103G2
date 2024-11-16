package com.emp.model;

import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpJDBCDAO();
	}

	public EmpVO addEmp(Integer counterInformNo, Integer counterNo, String informMsg, java.sql.Date informDate) {

		EmpVO empVO = new EmpVO();

		empVO.setCounterInformNo(counterInformNo);
		empVO.setCounterNo(counterNo);
		empVO.setInformMsg(informMsg);
		empVO.setInformDate(informDate);
		
		
		
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(Integer counterInformNo, String informMsg, java.sql.Date informDate) {

		EmpVO empVO = new EmpVO();

		empVO.setCounterInformNo(counterInformNo);		
		empVO.setInformMsg(informMsg);
		empVO.setInformDate(informDate);
		
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer counterInformNo) {
		dao.delete(counterInformNo);
	}

	public EmpVO getOneEmp(Integer counterInformNO) {
		return dao.findByPrimaryKey(counterInformNO);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
