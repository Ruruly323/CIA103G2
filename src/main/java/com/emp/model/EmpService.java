package com.emp.model;

import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpJDBCDAO();
	}

	public EmpVO addEmp(Integer managerNo, String managerName, String managerAccount, Integer authNo, String authTitle, String authContext) {

		EmpVO empVO = new EmpVO();

		empVO.setManagerNo(managerNo);
		empVO.setManagerName(managerName);
		empVO.setManagerAccount(managerAccount);
		empVO.setAuthNo(authNo);
		empVO.setAuthTitle(authTitle);
		empVO.setAuthContext(authContext);
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(Integer managerNo, String managerName, String managerAccount, Integer authNo, String authTitle, String authContext) {

		EmpVO empVO = new EmpVO();

		empVO.setManagerNo(managerNo);
		empVO.setManagerName(managerName);
		empVO.setManagerAccount(managerAccount);
		empVO.setAuthNo(authNo);
		empVO.setAuthTitle(authTitle);
		empVO.setAuthContext(authContext);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer managerNo) {
		dao.delete(managerNo);
	}

	public EmpVO getOneEmp(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
