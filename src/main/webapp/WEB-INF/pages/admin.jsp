<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin</title>
    <style>
        <%@include file="/WEB-INF/pages/style.css" %>
    </style>
</head>

<body>

<jsp:include page="header.jsp"></jsp:include>

<h3> Hello, this is admin page and only admin role can access it! </h3>

<h1>User List</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <% for (User user : (List<User>) request.getAttribute("userList")) { %>
    <tr>
        <td><%= user.getId() %>
        </td>
        <td><%= user.getEmail() %>
        </td>
        <td><%= user.getPassword() %>
        </td>
        <td><%= user.getRole() %>
        </td>
        <td>
            <form method="POST" action="${pageContext.request.contextPath}/deleteUser">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <input type="submit" value="Delete">
            </form>
            <form method="get" action="${pageContext.request.contextPath}/editUser">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <input type="submit" value="Edit">
            </form>
        </td>
    </tr>
    <% } %>
</table>
<br>
<form method="get" action="${pageContext.request.contextPath}/createUser">
    <input type="submit" value="Add User">
</form>

</body>
</html>