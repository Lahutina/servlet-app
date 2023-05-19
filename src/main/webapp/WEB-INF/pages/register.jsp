<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Register</title>
    <style><%@include file="/WEB-INF/pages/style.css"%></style>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <h1>Register</h1>

    <form method="POST" action="${pageContext.request.contextPath}/register">
        <table border="0">
            <tr>
                <td>Email</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
