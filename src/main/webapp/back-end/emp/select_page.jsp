<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>select informmsg</title>

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
   <tr><td><h3>櫃位訊息查詢</h3></td></tr>
</table>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Emps.  <br><br></li>
  
  
  

  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
   
  <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>選擇櫃位消息編號:</b>
       <select size="1" name="counterInformNo">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.counterInformNo}">${empVO.counterInformNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>訊息管理</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new InformMsg</li>
</ul>

</body>
</html>