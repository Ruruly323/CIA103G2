<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<% //��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
   EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�s�W���� - addEmp.jsp</title>

<style>
  table#table-1 {
	width: 700px;
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
   <tr><td><h3><br>�s�W����<br></h3></td></tr>
</table>



<h3>��Ʒs�W:</h3>



<ul>
  <li><a href='listAllEmp.jsp'>List</a> �Ҧ��q�� <br><br></li>
  
 <c:if test="${not empty errorMsgs}">
    <font style="color:red">�Эץ��H�U���~:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>
  
  
<FORM METHOD="post" ACTION="emp.do" name="form1">
  
 </ul>


<table>	
	<tr>
    <td>�d��T���s��:<font color="red"><b>*</b></font></td>
    <td><input type="TEXT" name="counterInformNo" value="${empVO.counterInformNo}" size="45"/></td>
</tr>
<tr>
    <td>�d��s��:</td>
    <td><input type="TEXT" name="counterNo" value="${empVO.counterNo}" size="45"/></td>
</tr>
<tr>
    <td>����:</td>
    <td><input type="TEXT" name="informMsg" value="${empVO.informMsg}" size="45" style="width:535px; height: 100px;"/></td>
</tr>
<tr>
    <td>�q���ɶ�:</td>
    <td><input type="TEXT" name="informDate" value="${empVO.informDate}" size="45"/></td>
</tr>


<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>
</html>
