<%-- 
    Document   : userInfoView
    Created on : Mar 1, 2018, 8:03:32 AM
    Author     : viquy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Hello: ${user.userName}</h3>
 
    User Name: <b>${user.userName}</b>
    <br />
    Gender: ${user.gender } <br />
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>
