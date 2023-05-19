<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
    <style>
        <%@include file="/WEB-INF/pages/style.css" %>
    </style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h1>Edit user</h1>

<form method="POST" action="${pageContext.request.contextPath}/editUser">
    <input type="hidden" name="userId" value="${user.getId()}">
    <table border="0">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${user.getEmail()}"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="${user.getPassword()}"></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>
                <select name="role">
                    <option value="ADMIN">Admin</option>
                    <option value="USER">User</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update">
            </td>
        </tr>
    </table>
</form>
</body>
</html>