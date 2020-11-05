<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Baja de Trabajador</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/upd/BajaTrabajador.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/upd/BajaTrabajador.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<table>
<tr>
<td>
<form id="bajaTrabajadorFormId" action="${pageContext.request.contextPath}/BajaTrabajadorCO" method="post">
<table id="formTabLeft">
<tr>
<td><label>Numero:</label></td>
<td><input id="numero" name="numero" type="text" size="15" value="${trabajador.numero}" readonly/></td>
</tr>
<tr>
<td><label>Vigencia de Licencia:</label></td>
<td><input id="vigenciaLicencia" name="vigenciaLicencia" type="text" value="${trabajador.licenciaddMMyyyyV4}" readonly/></td>
</tr>
<tr>
<td><label>Nombre:</label></td>
<td><input id="nombre" name="nombre" type="text" size="60" value="${trabajador.nombre}"  readonly></td>
</tr>
<tr>
<td><label>No. Seguro:</label></td>
<td><input id="noSeguro" name="noSeguro" type="text" size="40" value="${trabajador.seguro}" readonly></td>
</tr>
<tr>
<td><label>Puesto:</label></td>
<td><input id="puesto" name="puesto" type="text" size="40" value="${trabajador.puesto}" readonly></td>
</tr>
<tr>
<td><label>Direccion:</label></td>
<td><textarea id="direccion" name="direcccion" rows="2" cols="46" readonly>${trabajador.direccion}</textarea></td>
</tr>
<tr>
<td><label>Telefono:</label></td>
<td><input id="telefono" name="telefono" type="text" size="40" value="${trabajador.telefono}" readonly></td>
</tr>
<tr>
<td><label>Fecha:</label></td>
<td><input id="fecha" name="fecha" type="text" size="40" value="${trabajador.fechaddMMyyyyV4}" readonly></td>
</tr>
<tr>
<td><label>Estado:</label></td>
<td><input type="text" value="${trabajador.estado}" readonly></td>
</tr>
<tr>
<td><label>Notas:</label></td>
<td><textarea id="notas" name="notas" readonly>${trabajador.nota}</textarea></td>
</tr>
<tr>
<td><label>Fecha de Baja:</label></td>
<td><input id="fechaBaja" type="date" name="fechaBaja" required></td>
</tr>
<tr>
<td><label>Motivo de Baja:</label></td>
<td>
<select id="selectMotivoBajaId" name="motivoBaja" required>
<option value="${trabajador.motivo}">${trabajador.motivo}</option>
<option value="ABANDONO DE TRABAJO">ABANDONO DE TRABAJO</option>
<option value="DESPIDO">DESPIDO</option>
<option value="SUELDO">SUELDO</option>
<option value="OTRO TRABAJO">OTRO TRABAJO</option>
<option value="PROBLEMA PERSONAL">PROBLEMA PERSONAL</option>
<option value="SALUD">SALUD</option>
<option value="HORARIOS">HORARIOS</option>
</select>
</td>
</tr>
</table>
<input id="accionId" type="hidden" name="accionName"/> 
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
</form>
</td>
<td>
<table id="formTabRight" style="border-collapse:collapse">
<tr>
<td width="402px" height="300px">
<img id="output" name="imgFoto" width="402px" height="300px" src="data:image/jpg;base64,${trabajador.fotoBase64}">
<td>
</tr>
</table>
</td>
</tr>
</table>

<table>
<tr>
<td>
<button id="cancelarBtn" class="btn btn-primary" onclick="accionBajaTrabajador('Cancelar');">Cancelar</button>
</td>
<td>
<button id="actualizarBtn" class="btn btn-primary" onclick="accionBajaTrabajador('Actualizar');">Actualizar</button>
</td>
</tr>
</table>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>