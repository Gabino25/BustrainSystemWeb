<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Celulares Trabajadores</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/CelularesTrabajadores.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/CelularesTrabajadores.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogo - Celulares
</div> 
<h2 align=center >ASIGNACION&nbsp;DE&nbsp;CELULARES</h2>
<table><tr><td>
<div>
<form id="catTrabajForm" action="${pageContext.request.contextPath}/CelularesTrabajadoresCO" method="post" enctype="multipart/form-data" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<input id="noEmpBuscar" type="hidden" name="noEmpBuscar"> 
<input id="noEmpBaja" type="hidden" name="noEmpBaja"> 
<fieldset style="margin-left:10px;width:98%">
<table>
<tr>
<td>Fecha:</td>
<td><input type="date" name=fecha id=fecha /></td>
</tr>
<tr>
<td>Nombre:</td>
<td>
<select id="trabajador" name="trabajador" required >
<option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select></td>
</tr>
<tr>
<td>Marca:</td>
<td><select name=marca id=marca>
<option></option>
<option Value="SAMSUNG">SAMSUNG</option>
<option value="LG">LG</option>
<option value="HUAWEI">HUAWEI</option>
<option value="OTRA">Otra</option>
</select></td>
</tr>
<tr>
<td>Modelo:</td>
<td><input type=text name=modelo id=modelo /></td>
</tr>
<tr>
<td>EMEI:</td>
<td><input type=text name=numeroSerie id=numeroSerie /></td>
</tr>
<tr>
<td>Numero:</td>
<td><input type=text name=numero id=numero /></td>
</tr>
<tr><td>Nota:</td>
<td><textarea id=nota name=nota></textarea></td></tr>
</table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
</form>
</div>
</td>
<td>
<table id="TableRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>NOMBRE&nbsp;&nbsp;</th>
<th>MARCA&nbsp;&nbsp;</th>
<th>MODELO&nbsp;&nbsp;</th>
<th>N/S&nbsp;&nbsp;</th>
<th>N°&nbsp;CELULAR&nbsp;&nbsp;</th>
<th>FECHA</th>
</tr></thead>
<tbody class="xxbstbodybg">
<c:forEach  var="list" items="${list}" varStatus="row">
<tr>
<td>${list.trabajador}</td>
<td>${list.marca}</td>
<td>${list.modelo}</td>
<td>${list.numeroSerie}</td>
<td>${list.numero}</td>
<td>${list.nota}</td>
<td>${list.fecha}</td>
</tr>
</c:forEach>
</tbody>
</table>
</td></tr></table>

<table>
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionCatTrabaj('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Borrar');">Borrar</button></td>
<% } %>
<!-- <td><button id="BuscarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Buscar');">Buscar</button></td> -->
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Cancelar');">Cancelar</button></td>
<td><button id="BajaBtn" class="btn btn-primary" onclick="accionCatTrabaj('Baja');">Baja</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionCatTrabaj('Salir');">Salir</button></td>
</tr>
</table>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>