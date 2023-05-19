<%@ page contentType="text/html;charset=UTF-8"%>
<html>
   <head>
      <title>Login</title>
      <style><%@include file="/WEB-INF/pages/style.css"%></style>
   </head>
   <body>
 
      <jsp:include page="header.jsp"></jsp:include>
 
      <h3>Login Page</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>Email</td>
               <td><input type="text" name="email" value= "${user.email}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>
          
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
               </td>
            </tr>
         </table>
      </form>
 
   </body>
</html>