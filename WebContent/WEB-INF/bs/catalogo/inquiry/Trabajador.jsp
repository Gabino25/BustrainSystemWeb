<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page import="java.util.Base64"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trabajador</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Trabajador.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/Trabajador.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-left:10px;width:98%">
<table>
<tr>
<td>
<form id="trabajadorFormId" action="${pageContext.request.contextPath}/TrabajadorCO" method="post">
<table id="formTabLeft">
<tr><td colspan="2" align=center><H3>Datos&nbsp;Generales</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="Ntrabajador">#Empleado:</label></td>
<td><input id="Ntrabajador" name="Ntrabajador" type="number" size="40" value="${trabajador.NEmpleado}" readonly /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" name="fecha" type="text"  value="${trabajador.fechaddMMyyyyV4}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="numero">Numero:</label></td>
<td><input id="numero" name="numero" type="text" size="15" value="${trabajador.numero}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombre">Nombre:</label></td>
<td><input id="nombre" name="nombre" type="text" size="60" value="${trabajador.nombre}"  readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="reingreso">¿Reingreso?</label></td>
<td><input id="reingreso" name="reingreso" type="text" size="10" value="${trabajador.reingreso}"  readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="noSeguro">No. Seguro:</label></td>
<td><input id="noSeguro" name="noSeguro" type="text" size="40" value="${trabajador.seguro}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fechaNacimiento">Fecha&nbsp;Nacimiento:</label></td>
<td><input id="fechaNacimiento" name="fechaNacimiento" type="text" value="${trabajador.fechaNacimiento}" readonly ></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="direccion">Direccion:</label></td>
<td><textarea id="direccion" name="direcccion" rows="2" cols="46" readonly>${trabajador.direccion}</textarea></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="telefono">Telefono:</label></td>
<td><input id="telefono" name="telefono" type="text" size="40" value="${trabajador.telefono}" readonly></td>
</tr>
<tr><td colspan="2" align=center><H3>Datos&nbsp;Laborales</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="puesto">Puesto:</label></td>
<td><input id="puesto" name="puesto" type="text" size="40" value="${trabajador.puesto}" readonly></td>
</tr>
<tr><td class="xxbstdtextalign">Gafete:&nbsp;<input type=text name=gafete id=gafete value="${trabajador.gafete}" readonly>
</td>
<td class="xxbstdtextalign">Fecha&nbsp;de&nbsp;entrega:&nbsp;<input type=text name=fechaGafete id=fechaGafete value="${trabajador.fechaGafete}" readonly /></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="vigenciaLicencia">Vigencia&nbsp;de&nbsp;Licencia:</label></td>
<td><input id="vigenciaLicencia" name="vigenciaLicencia" type="text" value="${trabajador.licenciaddMMyyyyV4}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="vigenciaLicenciaF">Vigencia&nbsp;de&nbsp;Licencia&nbsp;Federal:</label></td>
<td><input id="vigenciaLicenciaF" name="vigenciaLicenciaF" type="text" value="${trabajador.licenciaF}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="estado">Estado:</label></td>
<td><input id="estado" type="text" value="${trabajador.estado}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="notas">Alergias:</label></td>
<td><textarea id="notas" name="notas" readonly>${trabajador.nota}</textarea></td>
</tr>
<tr>	
<td colspan="2" align=center><H3>Tallas&nbsp;del&nbsp;Trabajador</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="Camisa">Camisa:</label></td>
<td><input id="Camisa" name="Camisa" type="text" size="30"  value="${trabajador.tcamisa}" readonly /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Playera">Playera:</label></td>
<td><input id="Playera" name="Playera" type="text" size="30" value="${trabajador.tplayera}" readonly /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Pantalon">Pantalon:</label></td>
<td><input id="Pantalon" name="Pantalon" type="text" size="30" value="${trabajador.tpantalon}" readonly /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Zapatos">Zapatos:</label></td>
<td><input id="Zapatos" name="Zapatos" type="text" size="30" value="${trabajador.tzapatos}" readonly /></td>
</tr>
<tr>
<!-- -------------------PARA-ESPACIO-EN-LA-TABLA------------------ -->
<td colspan="2" align=center><H3></H3></td></tr>
<!-- --------------------------------------------------------------- -->
</table>
<input id="accionId" type="hidden" name="accionName"> 
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
</fieldset>

<button id="regresar" class="btn btn-primary" onclick="accionTrabajador('Regresar');">Regresar</button>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>