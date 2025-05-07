<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.servlet.jsp.jstl/core" prefix="c" %>
<html>
<head>
  <title>To-Do List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
    }
    .form-container {
      margin-bottom: 20px;
    }
    input[type="text"] {
      padding: 8px;
      width: 70%;
      margin-right: 10px;
    }
    button {
      padding: 8px 16px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
    button:hover {
      background-color: #45a049;
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
    .completed {
      text-decoration: line-through;
      color: #888;
    }
    .message {
      color: green;
      margin: 10px 0;
    }
    .error {
      color: red;
      margin: 10px 0;
    }
  </style>
</head>
<body>
<h2>Quản lý công việc hàng ngày</h2>

<!-- Form thêm công việc -->
<div class="form-container">
  <form action="todo" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="description" placeholder="Nhập công việc mới" required>
    <button type="submit">Thêm công việc</button>
  </form>
</div>

<!-- Danh sách công việc -->
<c:choose>
  <c:when test="${empty tasks}">
    <p>Chưa có công việc nào.</p>
  </c:when>
  <c:otherwise>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Mô tả</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="task" items="${tasks}">
        <tr>
          <td>${task.id}</td>
          <td class="${task.completed ? 'completed' : ''}">${task.description}</td>
          <td>${task.completed ? 'Hoàn thành' : 'Chưa hoàn thành'}</td>
          <td>
            <c:if test="${not task.completed}">
              <form action="todo" method="post" style="display:inline;">
                <input type="hidden" name="action" value="complete">
                <input type="hidden" name="taskId" value="${task.id}">
                <button type="submit">Hoàn thành</button>
              </form>
            </c:if>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:otherwise>
</c:choose>
</body>
</html>