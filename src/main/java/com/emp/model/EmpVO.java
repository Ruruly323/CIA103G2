package com.emp.model;
import java.sql.Date;


public class EmpVO implements java.io.Serializable{
	private Integer counterInformNo;
	private Integer counterNo;
	private String informMsg;
	private Date informDate;
	private Integer informRead;
	
	
	public Integer getCounterInformNo() {
        return counterInformNo != null ? counterInformNo : 0; // 返回預設值
    }

    public void setCounterInformNo(Integer counterInformNo) {
        this.counterInformNo = counterInformNo;
    }

    public Integer getCounterNo() {
        return counterNo != null ? counterNo : 0; // 返回預設值
    }

    public void setCounterNo(Integer counterNo) {
        this.counterNo = counterNo;
    }
	public String getInformMsg() {
		return informMsg;
	}
	public void setInformMsg(String informMsg) {
		this.informMsg = informMsg;
	}
	public Date getInformDate() {
		return informDate;
	}
	public void setInformDate(Date informDate) {
		this.informDate = informDate;
	}
	public Integer getInformRead() {
		 return informRead != null ? informRead : 0;
	}
	public void setInformRead(Integer informRead) {
		this.informRead = informRead;
	}
	
		
}
