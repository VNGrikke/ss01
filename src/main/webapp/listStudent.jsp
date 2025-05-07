<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.servlet.jsp.jstl/core" prefix="c" %>

<html>
<head>
    <title>List Student</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>Danh sách người dùng</h2>

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Class</th>
            <th>Type</th>
            <th>License Plate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.className}</td>
                <td>${user.type}</td>
                <td>${user.licensePlate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>