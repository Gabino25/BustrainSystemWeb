<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogar Unidades</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/combust/upd/CatalogarUnidades.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vales/combust/upd/CatalogarUnidades.css">
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Combustible - Catalogar Unidades
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead>
<tr>
<th>FOLIO</th>
<th>UNIDAD</th>
<th>CATEGORIA&nbsp;1</th>
<th>CATEGORIA&nbsp;2</th>
</tr>
</thead>
<tbody>
<c:forEach  var="liCatalogarUnidades" items="${listCatalogarUnidades}" varStatus="row">
<tr>
<td>${liCatalogarUnidades.folio}</td>
<td>${liCatalogarUnidades.eco}</td>
<td>${liCatalogarUnidades.categoria1}</td>
<td>${liCatalogarUnidades.categoria2}</td>
</tr>
</c:forEach>
</tbody>
</table>

<div id="updIdDiv">
<form action="${pageContext.request.contextPath}/CatalogarUnidadesCO" method="post" autocomplete="off">
<table>
<tbody>
<tr>
<td><input type="hidden" id="folio" name="folio" required>
    <label>Unidad:</label></td>
<td><input id="unidad" name="unidad" type="text" readonly required></td>
</tr>
<tr>
<td><label>Categoria&nbsp;1:</label></td>
<td><select id="categoria1" name="categoria1" required>
    <option value=""></option>
    <option value="ADMINISTRATIVO">ADMINISTRATIVO</option>
    <option value="TALLER">TALLER</option>
    <option value="AUTOBUSES">AUTOBUSES</option>
    <option value="CAMIONETAS(VANS Y PICK UPS)">CAMIONETAS(VANS Y PICK UPS)</option>
    <option value="SERVICIO EJECUTIVO">SERVICIO EJECUTIVO</option>
    </select></td>
</tr>
<tr>
<td><label>Categoria&nbsp;2:</label></td>
<td><select id="categoria2" name="categoria2" >
       <option value=""></option>
    </select>
</td>
</tr>
</tbody>
</table>

<input type="hidden" id="accionCatalogarUnidades" name="accionCatalogarUnidades">
<input type="submit" id="submitForm">
<input type="reset" id="resetForm">
</form>
</div>

<button class="btn btn-primary" onclick="accionCatalogarUnidades('Buscar');">Buscar</button>
<button class="btn btn-primary" onclick="accionCatalogarUnidades('Actualizar')">Actualizar</button>

 <jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>