<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<select id="trabajador" name="trabajador" required >
<option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</body>
</html>