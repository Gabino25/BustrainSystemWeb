<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/catalogoStyleMain.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/Clientes.js"></script>


</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogos - Clientes
</div> 

 <table id="TablRO" class="table table-striped table-bordered table-condensed" >
 <thead class="xxbstheadbg">
 <tr>
 <th>CLAVE</th>
 <th>RFC</th>
 <th>EMPRESA</th>
 <th>DIRECCION</th>
 <th>TELEFONO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
 <th>CONTACTO</th>
 <th>FECHA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
 <th>DESCRIPCION</th>
 <th>ESTADO</th>
 </tr>
 </thead>
 <tbody class="xxbstbodybg">
 <c:forEach  var="catClientes" items="${listCatClientes}" varStatus="row">
 <tr>
 <td>${catClientes.clave}</td>
 <td>${catClientes.rfc}</td>
 <td>${catClientes.empresa}</td>
 <td>${catClientes.direccion}</td>
 <td>${catClientes.telefono }</td>
 <td>${catClientes.contacto }</td>
 <td>${catClientes.fechaddMMyyyy }</td>
 <td>${catClientes.descripcion}</td>
 <td>${catClientes.estado}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>
  
<form id="catClientesForm" action="${pageContext.request.contextPath}/ClientesCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr>
<td>
<table>
<tr>
<td class="xxbstdtextalign"><label for="rfc" class="xxbstdtextalign">Rfc:</label></td>
<td><input id="rfc" type="text" name="rfc" maxlength="50" required/></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreEmpresa">Nombre&nbsp;Empresa:</label></td>
<td><input id="nombreEmpresa" type="text" name="nombreEmpresa" size=60 required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRepresentante">Nombre&nbsp;Representante:</label></td>
<td><input id="nombreRepresentante" type="text" name="nombreRepresentante" size=60 required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="direccion">Direccion:</label></td>
<td><textarea id="direccion"  name="direccion" required></textarea> </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="telefono">Telefono:</label></td>
<td><input id="telefono" type="text" name="telefono" size=40 maxlength="50" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date" name="fecha" size=40 required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Estado</label></td>
<td><input id="estadoY" type="radio" value="ACTIVO" name="Estado" checked><label for="estadoY">Activo</label>
    <input id="estadoN" type="radio" value="INACTIVO" name="Estado" ><label for="estadoN">Inactivo</label>
</td>
</tr>
</table>
</td>
<td class="xxbstdvalign">
<table>
<tr>
<td><label for="notas">Notas:</label>
</td>
</tr>
<tr>
<td>
<textarea  id="notas"  name="notas"></textarea>
</td>
</tr>
</table>
</td>
</tr>
</table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="claveClienteTrx" name="claveClienteTrx"/>
</form>

<br>
<div class="rowButtons">
<table id="buttonsTable">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionCatClie('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionCatClie('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionCatClie('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionCatClie('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionCatClie('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionCatClie('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionCatClie('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>