<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bustrain System</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/catalogoStyleMain.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/Proveedores.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogos - Proveedores
</div> 

 <table id="TablRO" class="table table-striped table-bordered table-condensed">
 <thead class="xxbstheadbg">
 <tr>
 <th>CLAVE</th>
 <th>EMPRESA</th>
 <th>TELEFONO</th>
 <th>DIRECCION</th>
 <th>CONTACTO</th>
 <th>FECHA</th>
 <th>RFC</th>
 <th>DESCRIPCION</th>
 </tr>
 </thead>
 <tbody class="xxbstbodybg">
 <c:forEach  var="catProveedores" items="${listCatProveedores}" varStatus="row">
 <tr>
 <td>${catProveedores.clave}</td>
 <td>${catProveedores.empresa}</td>
 <td>${catProveedores.telefono}</td>
 <td>${catProveedores.direccion}</td>
 <td>${catProveedores.nombre}</td>
 <td>${catProveedores.fechaddMMyyyy}</td>
 <td>${catProveedores.rfc}</td>
 <td>${catProveedores.descripcion}</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>

<form id="catProveedoresForm" action="${pageContext.request.contextPath}/ProveedoresCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr>
<td>
<table>
<tr>
<td class="xxbstdtextalign"><label for="rfc">Rfc:</label></td>
<td><input id="rfc" type="text" name="rfc" maxlength="50" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreEmpresa">Nombre&nbsp;Empresa:</label></td>
<td><input id="nombreEmpresa" type="text" name="nombreEmpresa" size=60 maxlength="80" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRepresentante">Nombre&nbsp;Representante:</label></td>
<td><input id="nombreRepresentante" type="text" name="nombreRepresentante" size=60 maxlength="50" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="direccion">Direccion:</label></td>
<td><textarea id="direccion" name="direccion" required></textarea> </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="telefono">Telefono:</label></td>
<td><input id="telefono" type="text" name="telefono" size=40 maxlength="50" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date" name="fecha" size=40  required></td>
</tr>
</table>
</td>
<td class="xxbstdvalign">
<table>
<tr>
<td class="xxbstdvalign"><label>Notas:</label></td>
</tr>
<tr>
<td class="xxbstdvalign"><textarea  id="notas"  name="notas"></textarea></td>
</tr>
</table>
</td>
</tr>
</table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="claveProveedorTrx" name="claveProveedorTrx"/>

</form>


<div class="row">
<table id="buttonsTable">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary"  onclick="accionCatProv('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionCatProv('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionCatProv('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionCatProv('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionCatProv('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionCatProv('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionCatProv('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>