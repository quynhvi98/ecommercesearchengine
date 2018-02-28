<%-- 
    Document   : register
    Created on : Feb 28, 2018, 3:39:02 PM
    Author     : viquy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>REGISTER PAGE</h1>
        <form action="Controller" method="POST">
            Username <input type="text" name="txtUser" value=""/></br>
            Password <input type="password" name="txtPass" value=""/></br>
            Lastname <input type="text" name="txtLast" value=""/></br>
            Admin <input type="submit" value="Register" name="btAction"/>
        </form>
    </body>
</html>
