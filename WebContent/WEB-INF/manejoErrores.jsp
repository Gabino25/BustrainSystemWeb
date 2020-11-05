<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.lang.NullPointerException" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manejo de Errores</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
</head>
<body>
<h1>Manejo de Errores</h1>
<br>
<br> 
Ocurri&oacute; un error: <%= exception.getMessage()%> 
</body>
</html>