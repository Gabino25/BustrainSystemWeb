<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Proveedores</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/catalogoStyleMain.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/upd/ProveedoresUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<br>
<fieldset style="margin-top:20px;margin-left:10px;width: 98%;">
<form id="formUpd" action="${pageContext.request.contextPath}/ProveedoresCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"/> 
<table>
<tr>
<td>
<table>
<tr>
<td class="xxbstdtextalign"><label for="rfc">Rfc:</label></td>
<td><input id="rfc" type="text" name="rfc" value="${Proveedor.rfc}" maxlength="50" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreEmpresa">Nombre&nbsp;Empresa:</label></td>
<td><input id="nombreEmpresa" type="text" name="nombreEmpresa" size=60 maxlength="80" value="${Proveedor.empresa}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRepresentante">Nombre&nbsp;Representante:</label></td>
<td><input id="nombreRepresentante" type="text" name="nombreRepresentante" size=60 maxlength="50" value="${Proveedor.nombre}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="direccion">Direccion:</label></td>
<td><textarea id="direccion" name="direccion" required>${Proveedor.direccion}</textarea> </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="telefono">Telefono:</label></td>
<td><input id="telefono" type="text" name="telefono" size=40 maxlength="50" value="${Proveedor.telefono}" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date" name="fecha" size=40  value="${Proveedor.fechayyyyMMdd}" required></td>
</tr>
</table>
</td>
<td class="xxbstdvalign">
<table>
<tr>
<td><label for="notas">Notas:</label></td>
</tr>
<tr>
<td><textarea  id="notas"  name="notas" >${Proveedor.descripcion}</textarea></td>
</tr>
</table>
</td>
</tr>
</table>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="claveProveedorTrx" name="claveProveedorTrx" value="${Proveedor.clave}"/>
</form>
</fieldset>

<div style="margin-left:10px;width: 98%;">
<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>