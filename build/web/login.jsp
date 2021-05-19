<%-- 
    Document   : login.jsp
    Created on : May 17, 2021, 8:25:27 AM
    Author     : MinhT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Login page JSP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="MainController?btnAction=login" method="post">
            <p class="text-danger">${mess}</p>
            Use Name: <input type="text" name="username"/><br>
            Password: <input type="password" name="pass"/><br>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
