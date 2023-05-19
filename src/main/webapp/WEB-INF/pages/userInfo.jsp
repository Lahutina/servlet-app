<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Info</title>
    <style><%@include file="/WEB-INF/pages/style.css"%></style>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <h1>Hello!</h1>
    ID: <b>${user.getId()}</b>
    <br/>
    <br/>
    Email: <b>${user.getEmail()}</b>
    <br/>
    <br/>
    Your role: <b>${user.getRole()}</b>
    <br/>

</body>
</html>