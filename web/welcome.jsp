<%-- 
    Document   : welcome
    Created on : Feb 28, 2018, 8:42:13 AM
    Author     : viquy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USER}</font>
        <h1>Welcome to the MVCdemo!</h1>
    </body>
        <form action ="Controller">
            Name <input type="text" name="txtSearch" value=""/><br/>
            <input type="submit" value ="Search" name="btcAction"/>
        </form>
        
</html>
