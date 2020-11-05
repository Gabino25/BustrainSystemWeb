<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trabajadores</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Trabajadores.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/Trabajadores.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogos - Trabajadores
</div> 
<table id="TableRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>N°&nbsp;REGISTRO</th>
<th>N°&nbsp;EMPLEADO</th>
<th>NOMBRE</th>
<th>NSS</th>
<th>FECHA&nbsp;DE&nbsp;NACIMIENTO</th>
<th>TELEFONO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
<th>FECHA&nbsp;INGRESO</th>
<th>STATUS</th>
<th>DIRECCION</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="catTrabajadores" items="${listTrabajadores}" varStatus="row">
<tr>
<td>${catTrabajadores.numero}</td>
<td>${catTrabajadores.NEmpleado}</td>
<td>${catTrabajadores.nombre}</td>
<td>${catTrabajadores.seguro}</td>
<td>${catTrabajadores.fechaNacimiento}</td>
<td>${catTrabajadores.telefono}</td>
<td>${catTrabajadores.fechaingresoddMMyyyyV4}</td>
<td>${catTrabajadores.estado}</td>
<td>${catTrabajadores.direccion}</td>
</tr>
</c:forEach>
</tbody>
</table>
<div>
<form id="catTrabajForm" action="${pageContext.request.contextPath}/TrabajadoresCO" method="post" enctype="multipart/form-data" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<input id="noEmpBuscar" type="hidden" name="noEmpBuscar"> 
<input id="noEmpBaja" type="hidden" name="noEmpBaja"> 
<fieldset style="margin-left:10px;width: 98%;">
<table><tr><td>
<table>
<tr><td colspan="2" align=center><H3>Datos&nbsp;Generales</H3></td></tr>
<!-- -----------------------NUMERO DE EMPLEADO------------------------- -->
<tr>
<td class="xxbstdtextalign"><label for="Ntrabajador">#Empleado:</label></td>
<td><input id="Ntrabajador" name="Ntrabajador" type="number" size="40" required />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<label for="reingreso">Reingreso:&nbsp;&nbsp;</label> 
<select name="reingreso" id="reingreso" required>
<option value=""></option>
<option value="SI">SI</option>
<option value="NO">NO</option>
</select></td>
</tr>
<!-- ------------------------------------------------------------------- -->
 <tr>
<td class="xxbstdtextalign"><label for="fechaIngreso">Fecha&nbsp;Ingreso:</label></td>
<td><input id="fechaIngreso" name="fechaIngreso" type="date" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombre">Nombre:</label></td>
<td><input id="nombre" name="nombre" type="text" size="60" required/></td>
</tr>
<!-- SI&nbsp;<input id="ReingresoS" value=SI name="reingreso" type="radio" />&nbsp; -->
<!-- NO&nbsp;<input id="ReingresoN" value=NO name="reingreso" type="radio" /></td> -->
<tr>
<td class="xxbstdtextalign"><label for="noSeguro">No.&nbsp;Seguro:</label></td>
<td><input id="noSeguro" name="noSeguro" type="number" size="40" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fechaNacimiento">Fecha&nbsp;Nacimiento:</label></td>
<td><input id="fechaNacimiento" name="fechaNacimiento" type="date" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="rfc">RFC:</label></td>
<td><input id="rfc" type="text" name="rfc" maxlength="13" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="curp">CURP:</label></td>
<td><input id="curp" type="text" name="curp" maxlength="18" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectEstadoCivilId">Estado Civil:</label></td>
<td>
<select id="selectEstadoCivilId" name="estadoCivil" required>
<option value=""></option>
<option value="CASADO">CASADO</option>
<option value="SOLTERO">SOLTERO</option>
</select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="direccion">Direccion:</label></td>
<td><textarea id="direccion" name="direcccion" rows="2" cols="46" required></textarea></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="telefono">Telefono:</label></td>
<td><input id="telefono" name="telefono" type="text" size="40" required></td>
</tr>
<tr><td colspan="2" align=center><H3>Datos&nbsp;Laborales</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="status">Status:</label></td>
<td>
<select id="status" name="status" required>
<option value=""></option>
<option value="ACTIVO">ACTIVO</option>
<option value="INACTIVO">INACTIVO</option>
</select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="puesto">Puesto:</label></td>
<td><input id="puesto" name="puesto" type="text" size="40" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectAreaId">Area:</label></td>
<td><select id="selectAreaId" name="area" required>
     <option value=""></option>
     <option value="ADMINISTRATIVO">ADMINISTRATIVO</option>
     <option value="AUTOBUS">AUTOBUS</option>
     <option value="TALLER">TALLER</option>
     <option value="TAXI">TAXI</option>
     <option value="TLA">TLA</option>
     <option value="TRANSCANADA">TRANSCANADA</option>
    </select>
</td>
</tr>
<tr><td class="xxbstdtextalign">Gafete:&nbsp;<select name=gafete id=gafete >
<option value="NO">NO</option>
<option value="SI">SI</option>
</select>
</td>
<td class="xxbstdtextalign">Fecha&nbsp;de&nbsp;entrega:&nbsp;<input type=date name=fechaGafete id=fechaGafete /></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="vigenciaLicencia">Vigencia&nbsp;de&nbsp;Licencia:</label></td>
<td><input id="vigenciaLicencia" name="vigenciaLicencia" type="date" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="vigenciaLicenciaF">Vigencia&nbsp;de&nbsp;Licencia&nbsp;Federal:</label></td>
<td><input id="vigenciaLicenciaF" name="vigenciaLicenciaF" type="date"/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="estatura">Estatura:</label></td>
<td><input id="estatura" type="number" name="estatura" min="0" step="0.01" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="peso">Peso:</label></td>
<td><input id="peso" type="number" name="peso" min="0" step="0.01" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="notas">Alergias:</label></td>
<td><textarea id="notas" name="notas" required></textarea></td>
</tr>
<tr>
<td colspan="2" align=center><H3>Tallas&nbsp;del&nbsp;Trabajador</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="Camisa">Camisa:</label></td>
<td><input id="Camisa" name="Camisa" type="text" size="30" required /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Playera">Playera:</label></td>
<td><input id="Playera" name="Playera" type="text" size="30" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Pantalon">Pantalon:</label></td>
<td><input id="Pantalon" name="Pantalon" type="text" size="30" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Zapatos">Zapatos:</label></td>
<td><input id="Zapatos" name="Zapatos" type="text" size="30" required /></td>
</tr>
</table>
</td><td>
<table style="border-collapse:collapse">
<tr>
<td colspan=2 width="402px" height="300px">
<img id="output" name="imgFoto" width="402px" height="300px">
</td>
</tr>
<tr>
<td colspan=2 >
<input id="fileFoto" type="file" name="fileFoto" accept="image/*" onchange="openFile(event)" required></td>
</tr>
</table>
</td></tr></table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
</form>
</div>
<table>
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionCatTrabaj('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Cancelar');">Cancelar</button></td>
<td><button id="BajaBtn" class="btn btn-primary" onclick="accionCatTrabaj('Baja');">Baja</button></td>
<% } %>
<td><button id="UniformesBtn" class="btn btn-primary" onclick="accionCatTrabaj('Uniformes');">Uniformes</button></td>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionCatTrabaj('Salir');">Salir</button></td>
</tr>
</table>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>