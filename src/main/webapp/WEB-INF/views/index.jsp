<%-- 
    Document   : index
    Created on : Feb 2, 2021, 9:02:35 AM
    Author     : Manjit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%= request.getAttribute("name") %>
       
    </body>
</html>
