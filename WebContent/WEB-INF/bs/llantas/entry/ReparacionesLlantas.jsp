<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reparaciones Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/MovimientosLlantas.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/ReparacionesLlantas.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Llantas - Reparaciones Llanta
</div>

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>FOLIO</th>
<th>NOMBRE</th>
<th>OPERADOR</th>
<th>FECHA</th>
<th>POSICION</th>
<th>UNIDAD</th>
<th>PRESION</th>
<th>OBSERVACIONES</th>
<th>TIPO&nbsp;MOV</th>
<th>KILOMETRAJE</th>
<th>USUARIO</th>
<th>FECHACAPTURA</th>
<th>PRESIONANTERIOR</th>
<th>COSTO</th>
<th>NOTA</th>
<th>KMRECORRIDO</th>
<th>PROF</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="movimientosLlantas" items="${listMovimientosLlantas}" varStatus="row">
<tr>
<td>${movimientosLlantas.folio}</td>
<td>${movimientosLlantas.nombre}</td>
<td>${movimientosLlantas.operador}</td>
<td>${movimientosLlantas.fechaddMMyyyyV2}</td>
<td>${movimientosLlantas.posicion}</td>
<td>${movimientosLlantas.unidad}</td>
<td>${movimientosLlantas.presion}</td>
<td>${movimientosLlantas.observaciones}</td>
<td>${movimientosLlantas.tipo_mov}</td>
<td>${movimientosLlantas.kilometraje}</td>
<td>${movimientosLlantas.usuario}</td>
<td>${movimientosLlantas.fechacapturaddMMyyyyV3}</td>
<td>${movimientosLlantas.presionanterior}</td>
<td>${movimientosLlantas.costo}</td>
<td>${movimientosLlantas.nota}</td>
<td>${movimientosLlantas.kmrecorrido}</td>
<td>${movimientosLlantas.prof}</td>
</tr>
</c:forEach>
</tbody>
</table>
<fieldset style="margin-left:10px;width:98%;">
<form id="formMovimientosLlantas" method="post" action="${pageContext.request.contextPath}/ReparacionesLlantasCO" onsubmit="return validaFormulario(this);" autocomplete="off">
<table id="TablInputs">
<tr>
<td class="xxbstdtextalign"><label for="selectLlantaId">Llanta:</label></td>
<td>
<select id="selectLlantaId" name="llanta">
<option value=""></option>
<c:forEach var="selLisLlantas" items="${selectListLlantas}" varStatus="row">
<option value="${selLisLlantas.nombre}">${selLisLlantas.nombre}</option>
</c:forEach>  
</select>
</td>
<td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td>
<td>
<select id="selectUnidadId" name="unidad">
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" required/></td>
<td class="xxbstdtextalign"><label for="kilometraje">Kilometraje:</label></td>
<td><input type="number" id="kilometraje" name="kilometraje" min="0" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectOperadorId">Operador:</label></td>
<td>
<select id="selectOperadorId" name="operador">
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</td>
<td class="xxbstdtextalign"><label for="selectPosicionId">Posicion:</label></td>
<td>
<select id="selectPosicionId" name="posicion" required>
<option value=""></option>
<option value="DD">DD</option>
<option value="DI">DI</option>
<option value="TDE">TDE</option>
<option value="TDI">TDI</option>
<option value="TII">TII</option>
<option value="TIE">TIE</option>
<option value="REF">REF</option>
</select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input type="number" id="costo" name="costo" min="0" required/></td>
</tr>
<tr>
<td>
<label>Nota:</label>
</td>
</tr>
<tr>
<td colspan="4"><textarea rows="2" cols="80" id="nota" name="nota"></textarea></td>
</tr>
</table>
<input type="hidden" id="accion" name="accionMovimientosLlantas"/>
<input id="submitGuardar" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" name="otherTransaction"  id="otherTransaction" value="${reqAttrOtherTransaction}"/>
<input type="hidden" id="movLlantaTrx" name="movLlantaTrx"/>
</form>
</fieldset>

<table>
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn"  class="btn btn-primary" onclick="accionReparacionesLlantas('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionReparacionesLlantas('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionReparacionesLlantas('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionReparacionesLlantas('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionReparacionesLlantas('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionReparacionesLlantas('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionReparacionesLlantas('Salir');">Salir</button></td>
</tr>
</table>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>