<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trabajadores</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Trabajadores.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/upd/TrabajadoresUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<form id="formUpd" action="${pageContext.request.contextPath}/TrabajadoresCO" method="post" enctype="multipart/form-data" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<input id="noEmpBuscar" type="hidden" name="noEmpBuscar" value="${trabajador.numero}"> 
<input id="noEmpBaja" type="hidden" name="noEmpBaja"> 
<fieldset style="margin-left:10px;width: 98%;">
<table><tr><td>
<table>
<tr><td colspan="2" align=center><H3>Datos&nbsp;Generales</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="Ntrabajador">#Empleado:</label></td>
<td><input id="Ntrabajador" name="Ntrabajador" type="number" size="40" value="${trabajador.NEmpleado}" /></td>
</tr>
<tr>
<td><label>Fecha Ingreso:</label></td>
<td><input id="fechaIngreso" name="fechaIngreso" type="date" value="${trabajador.fechaingresoyyyyMMdd}" required></td>
</tr>
<tr>
<td><label>Nombre:</label></td>
<td><input id="nombre" name="nombre" type="text" size="60" value="${trabajador.nombre}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="reingreso">¿Reingreso?</label></td>
<td>
<select name="reingreso" id="reingreso" required>
<option value="${trabajador.reingreso}">${trabajador.reingreso}</option>
<option value="SI">SI</option>
<option value="NO">NO</option>
</select>
</td>
</tr>
<tr>
<td><label>No.&nbsp;Seguro:</label></td>
<td><input id="noSeguro" name="noSeguro" type="number" size="40" value="${trabajador.seguro}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fechaNacimiento">Fecha&nbsp;Nacimiento:</label></td>
<td><input id="fechaNacimiento" name="fechaNacimiento" type="date" value="${trabajador.fechaNacimiento}" ></td>
</tr>
<tr>	
<td><label>RFC:</label></td>
<td><input id="rfc" type="text" name="rfc" maxlength="13" value="${trabajador.rfc}" required/></td>
</tr>
<tr>
<td><label>CURP:</label></td>
<td><input id="curp" type="text" name="curp" maxlength="18" value="${trabajador.curp}" required/></td>
</tr>
<tr>
<td><label>Estado Civil:</label></td>
<td>
<select id="selectEstadoCivilId" name="estadoCivil" required>
<option value="${trabajador.estadocivil}">${trabajador.estadocivil}</option>
<option value="CASADO">CASADO</option>
<option value="SOLTERO">SOLTERO</option>
</select>
</td>
</tr>
<tr>
<td><label>Direccion:</label></td>
<td><textarea id="direccion" name="direcccion" rows="2" cols="46" required>${trabajador.direccion}</textarea></td>
</tr>
<tr>
<td><label>Telefono:</label></td>
<td><input id="telefono" name="telefono" type="text" size="40" value="${trabajador.telefono}" required></td>
</tr>
<tr>
<td><label>Estatura:</label></td>
<td><input id="estatura" type="number" name="estatura" min="0" step="0.01" value="${trabajador.estatura}" required/></td>
</tr>
<tr>
<td><label>Peso:</label></td>
<td><input id="peso" type="number" name="peso" min="0" step="0.01" value="${trabajador.peso}" required></td>
</tr>
<tr><td colspan="2" align=center><H3>Datos&nbsp;Laborales</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="status">Status:</label></td>
<td>
<select id="status" name="status" required>
<option value="${trabajador.estado}">${trabajador.estado}</option>
<option value="ACTIVO">ACTIVO</option>
<option value="INACTIVO">INACTIVO</option>
</select>
</td>
</tr>
<tr>
<td><label>Puesto:</label></td>
<td><input id="puesto" name="puesto" type="text" size="40" value="${trabajador.puesto}" required/></td>
</tr>
<tr>
<td><label>Area:</label></td>
<td><select id="selectAreaId" name="area" required>
     <option value="${trabajador.area}">${trabajador.area}</option>
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
 <option value="${trabajador.gafete}">${trabajador.gafete}</option>
<option value="SI">SI</option>
<option value="NO">NO</option>
</select>
</td>
<td class="xxbstdtextalign">Fecha&nbsp;de&nbsp;entrega:&nbsp;<input type=date name=fechaGafete id=fechaGafete value="${trabajador.fechaGafete}"/></td></tr>
<tr>
<td><label>Vigencia&nbsp;de&nbsp;Licencia:</label></td>
<td><input id="vigenciaLicencia" name="vigenciaLicencia" type="date" value="${trabajador.licenciayyyyMMdd}" required/></td>
</tr>
<tr>
<td><label>Vigencia&nbsp;de&nbsp;Licencia&nbsp;Federal:</label></td>
<td><input id="vigenciaLicenciaF" name="vigenciaLicenciaF" type="date" value="${trabajador.licenciaF}" /></td>
</tr>
<tr>
<td><label>Alergias:</label></td>
<td><textarea id="notas" name="notas" required>${trabajador.nota}</textarea></td>
</tr>
<tr>	
<td colspan="2" align=center><H3>Tallas&nbsp;del&nbsp;Trabajador</H3></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="Camisa">Camisa:</label></td>
<td><input id="Camisa" name="Camisa" type="text" size="30"  value="${trabajador.tcamisa}" /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Playera">Playera:</label></td>
<td><input id="Playera" name="Playera" type="text" size="30" value="${trabajador.tplayera}" /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Pantalon">Pantalon:</label></td>
<td><input id="Pantalon" name="Pantalon" type="text" size="30" value="${trabajador.tpantalon}" /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="Zapatos">Zapatos:</label></td>
<td><input id="Zapatos" name="Zapatos" type="text" size="30" value="${trabajador.tzapatos}"/></td>
</tr>
<tr>
<td colspan="2" align=center><H3></H3></td></tr>
</table></td><td>
<table  style="border-collapse:collapse">
<tr>
<td width="402px" height="300px">
<img id="output" name="imgFoto" width="402px" height="300px" src="data:image/jpg;base64,${trabajador.fotoBase64}">
<td>
</tr>
<tr>
<td >
<input id="fileFoto" type="file" name="fileFoto" accept="image/*" onchange="openFile(event)"></td>
</tr>
</table></td></tr></table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>

</form>


<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>