<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/catalogoStyleMain.css">
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-top:20px;margin-left:10px;width: 98%;">
<table>
<tr>
<td>
<table>
<tr>
<td class="xxbstdtextalign"><label for="rfc">Rfc:</label></td>
<td><input id="rfc" type="text" name="rfc" maxlength="50" value="${Cliente.rfc}" readonly/></td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreEmpresa">Nombre&nbsp;Empresa:</label></td>
<td><input id="nombreEmpresa" type="text" name="nombreEmpresa" size=60 value="${Cliente.empresa}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRepresentante">Nombre&nbsp;Representante:</label></td>
<td><input id="nombreRepresentante" type="text" name="nombreRepresentante" value="${Cliente.contacto}" size=60 readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="direccion">Direccion:</label></td>
<td><textarea id="direccion"  name="direccion" readonly>${Cliente.direccion}</textarea> </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="telefono">Telefono:</label></td>
<td><input id="telefono" type="text" name="telefono" size=40 maxlength="50" value="${Cliente.telefono}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date" name="fecha" value="${Cliente.fechaddMMyyyy}" size=40 readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="estado">Estado</label></td>
<td><input id="estado" value="${Cliente.estado}" readonly/>
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
<textarea  id="notas"  name="notas" readonly>${Cliente.descripcion}</textarea>
</td>
</tr>
</table>
</td>
</tr>
</table>
</fieldset>

 <form action="${pageContext.request.contextPath}/ClientesCO?accionName=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary" >Regresar</button>
 </form>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>