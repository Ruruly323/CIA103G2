package com.emp.model;
import java.sql.Date;

public class EmpVO implements java.io.Serializable{
	private Integer managerNo;
	private String managerName;
	private String managerAccount;
	private Integer authNo;
	private String authTitle;
	private String authContext;
	
	public EmpVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmpVO(Integer managerNo, String managerName, String managerAccount, Integer authNo, String authTitle,
			String authContext) {
		super();
		this.managerNo = managerNo;
		this.managerName = managerName;
		this.managerAccount = managerAccount;
		this.authNo = authNo;
		this.authTitle = authTitle;
		this.authContext = authContext;
	}

	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public Integer getAuthNo() {
		return authNo;
	}
	public void setAuthNo(Integer authNo) {
		this.authNo = authNo;
	}
	public String getAuthTitle() {
		return authTitle;
	}
	public void setAuthTitle(String authTitle) {
		this.authTitle = authTitle;
	}
	public String getAuthContext() {
		return authContext;
	}
	public void setAuthContext(String authContext) {
		this.authContext = authContext;
	}
		
}
