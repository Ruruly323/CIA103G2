<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>通知修改 - update_emp_input.jsp</title>

<style>
  table#table-1 {
    width: 800px;
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
    background-color: white;
    margin-top: 1px;
    margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>通知修改 <br> update_emp_input.jsp</h3> 
		 <h4><a href="listAllEmp.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<!-- 顯示錯誤訊息 -->
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
    <tr>
        <td>櫃位通知訊息編號:<font color=red><b>*</b></font></td>
        <td>${param.counterInformNo}</td>
    </tr>
    <tr>
        <td>內文:</td>
        <td>
            <input type="TEXT" name="informMsg" value="${param.informMsg}" size="45" style="width:535px; height: 100px"/>
        </td> 
        <!-- 顯示內文的錯誤訊息 -->
        <td style="color: red;">
            <c:if test="${not empty errorMsgs}">
                <c:forEach var="message" items="${errorMsgs}">
                    <c:if test="${message.contains('informMsg')}">
                        ${message}
                    </c:if>
                </c:forEach>
            </c:if>
        </td>
    </tr>
    <tr>
        <td>通知時間:</td>
        <td>
            <input type="TEXT" name="informDate" value="${param.informDate}" size="45"/>
        </td> 
        <!-- 顯示通知時間的錯誤訊息 -->
        <td style="color: red;">
            <c:if test="${not empty errorMsgs}">
                <c:forEach var="message" items="${errorMsgs}">
                    <c:if test="${message.contains('informDate')}">
                        ${message}
                    </c:if>
                </c:forEach>
            </c:if>
        </td>
    </tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="counterInformNo" value="${param.counterInformNo}">
<input type="submit" value="送出修改">
</FORM>
</body>
</html>
