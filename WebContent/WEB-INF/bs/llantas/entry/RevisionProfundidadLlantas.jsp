<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Revision Profundidad</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/MovimientosLlantas.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/RevisionProfundidadLlantas.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Llantas - Profundidad
</div>

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>FOLIO</th>
<th>FECHA&nbsp;REVISION</th>
<th>LLANTAS</th>
<th>UNIDAD</th>
<th>PROFUNDIDAD</th>
<th>POSICION</th>
<th>KILOMETRAJE</th>
<th>OPERADOR</th>
<th>TIPO&nbsp;MOVIMIENTO</th>
<th>OBSERVACIONES</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="movimientosLlantas" items="${listMovimientosLlantas}" varStatus="row">
<tr>
<td>${movimientosLlantas.folio}</td>
<td>${movimientosLlantas.fechaddMMyyyyV2}</td>
<td>${movimientosLlantas.nombre}</td>
<td>${movimientosLlantas.unidad}</td>
<td>${movimientosLlantas.prof}</td>
<td>${movimientosLlantas.posicion}</td>
<td>${movimientosLlantas.kilometraje}</td>
<td>${movimientosLlantas.operador}</td>
<td>${movimientosLlantas.tipo_mov}</td>
<td>${movimientosLlantas.observaciones}</td>
</tr>
</c:forEach>
</tbody>
</table>

<fieldset style="margin-left:10px;width:98%">

<form id="formMovimientosLlantas" method="post" action="${pageContext.request.contextPath}/RevisionProfundidadLlantasCO" onsubmit="return validaFormulario(this);" autocomplete="off">
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
<td><input type="number" id="kilometraje" name="kilometraje" required/></td>
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
<td class="xxbstdtextalign"><label for="profundidad">Profundidad:</label></td>
<td><input type="number" id="profundidad" name="profundidad" min="0" step="0.1" required/></td>
</tr>
<tr>
<td>
<label>Observaciones:</label>
</td>
</tr>
<tr>
<td colspan="4"><textarea rows="2" cols="80" id="observaciones" name="observaciones"></textarea></td>
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
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionRevProfLlantas('Salir');">Salir</button></td>
</tr>
</table>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>