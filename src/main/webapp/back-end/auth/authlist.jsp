<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>員工權限表</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            margin: 10px;
        }
    </style>
</head>
<body>
    <h1>員工權限表</h1>
    
    <!-- 功能按鈕 -->
    <button onclick="location.href='queryEmployee.jsp'">查詢</button>
    <button onclick="location.href='addEmployee.jsp'">新增員工</button>

    <!-- 員工權限表 -->
    <table>
        <tr>
            <th>員工姓名</th>
            <th>員工帳號</th>
            <th>員工權限</th>
            <th>權限等級</th>
            <th>權限內容</th>
            <th>操作</th>
        </tr>

        <!-- 使用 JSTL 循環顯示員工列表 -->
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.account}</td>
                <td>${employee.permission}</td>
                <td>${employee.level}</td>
                <td>${employee.permissionContent}</td>
                <td>
                    <a href="editEmployee.jsp?id=${employee.id}">修改</a> |
                    <a href="deleteEmployee?id=${employee.id}" onclick="return confirm('確定要刪除嗎？')">刪除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>