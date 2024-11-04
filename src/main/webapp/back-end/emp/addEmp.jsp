<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
   EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員權限新增 - addEmp.jsp</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>管理員權限新增</h3></td></tr>
</table>



<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all ManagerAuth.  <br><br></li>
  
  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
  
  
  <FORM METHOD="post" ACTION="emp.do" name="form1">
 </ul>


<table>	
	<tr>
    <td>管理員編號:<font color="red"><b>*</b></font></td>
    <td><input type="TEXT" name="managerNo" value="${empVO.managerNo}"/></td>
</tr>
<tr>
    <td>管理員名稱:</td>
    <td><input type="TEXT" name="managerName" value="${empVO.managerName}" size="45"/></td>
</tr>
<tr>
    <td>管理員帳號:</td>
    <td><input type="TEXT" name="managerAccount" value="${empVO.managerAccount}" size="45"/></td>
</tr>
<tr>
    <td>權限編號:</td>
    <td><input type="TEXT" name="authNo" value="${empVO.authNo}" size="45"/></td>
</tr>
<tr>
    <td>權限名稱:</td>
    <td><input type="TEXT" name="authTitle" value="${empVO.authTitle}" size="45"/></td>
</tr>
<tr>
    <td>權限內容:</td>
    <td><input type="TEXT" name="authContext" value="${empVO.authContext}" size="45"/></td>
</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>
