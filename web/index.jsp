<%-- 
    Document   : index
    Created on : Feb 27, 2018, 10:20:03 PM
    Author     : viquy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action ="Controller" method ="post">
            Username <input type="text" name ="txtUserName" value=""/><br/>
            Password <input type="password" name="txtPass" value =""/><br/>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset"/>
            <h1>Login Bean</h1>
        </form>
    </body>
</html>
