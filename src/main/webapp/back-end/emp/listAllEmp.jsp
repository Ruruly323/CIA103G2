<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工權限 - listAllEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>管理員權限表</h3>
		 <h4><img src="images/back1.gif" width="100" height="32" border="0"></h4>
	</td></tr>
</table>

<!-- 功能按鈕 -->
    <button onclick="location.href='select_page.jsp'">查詢</button>
    <button onclick="location.href='addEmp.jsp'">新增</button>

<table>
	<tr>
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>管理員帳號</th>
		<th>權限編號</th>
		<th>權限名稱</th>
		<th>權限內容</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${empVO.managerNo}</td>
			<td>${empVO.managerName}</td>
			<td>${empVO.managerAccount}</td>
			<td>${empVO.authNo}</td>
			<td>${empVO.authTitle}</td>
			<td>${empVO.authContext}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="managerNo"  value="${empVO.managerNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="managerNo"  value="${empVO.managerNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>