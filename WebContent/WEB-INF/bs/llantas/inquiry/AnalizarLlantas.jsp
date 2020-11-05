<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Analizar Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/InquiryAnalizarLlantas.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/InquiryAnalizarLlantas.css">

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Llantas - Analizar Llantas
</div> 

<table>
<tr>
<td>
<form id="formAnalizarLlantas" name="formAnalizarLlantas" method="post" action="${pageContext.request.contextPath}/AnalizarLlantasCO">
<input type="hidden" id="accion" name="accionAnalizarLlantas">
<table>
<tr>
<td><div id="desdeDatePicker" onchange="detectaCambio();"></div></td>
<td><div id="hastaDatePicker" onchange="detectaCambio();"></div></td>
</tr>
<tr>
<td colspan="2">
 <fieldset>
  <legend>Unidades:</legend>
  <input type="radio" id="unidadesTodas" name="unidadesTodas" value="Todas" onclick="detectaRadios('Unidades');"/><label>Todas</label>
  <select id="selectUnidadId" name="unidad" onclick="detectaSelects('Unidades');">
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
 </fieldset>
</td>
</tr>
<tr>
<td colspan="2">
 <fieldset>
  <legend>Tipo Movimiento:</legend>
  <input type="radio" id="tipoMovimientoTodas" name="tipoMovimientoTodas" value="Todas" onclick="detectaRadios('TipoMovimiento');"/><label>Todas</label>
  <select id="selectTipoMovimiento" name="tipoMovimeinto" onclick="detectaSelects('TipoMovimiento');">
  <option value=""></option>
  <option value="ASIGNACION_A_UNIDAD">ASIGNACION A UNIDAD</option>
  <option value="REVISION_DE_PROFUNDIDAD">REVISION DE PROFUNDIDAD</option>
  <option value="REPARACIONES_DE_LLANTAS">REPARACIONES DE LLANTAS</option>
  </select>
 </fieldset>
</td>
</tr>
<tr>
<td colspan="2">
 <fieldset>
  <legend>Llantas:</legend>
  <input type="radio" id="llantasTodas" name="llantasTodas" value="Todas" onclick="detectaRadios('Llantas');"/><label>Todas</label>
 <select name="llanta" id="selectLlantasId" name="selectLlantasId" onclick="detectaSelects('Llantas');">
 <option value=""></option>
 <c:forEach var="selLisLlantas" items="${selectListLlantas}" varStatus="row">
 <option value="${selLisLlantas.nombre}">${selLisLlantas.nombre}</option>
 </c:forEach>  
 </select>
 </fieldset>
</td>
</tr>
</table>
</form>
</td>
<td>
<div id="DivTablRO">
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
</div>
</td>
</tr>
</table>

<button class="btn btn-primary" onclick="accionAnalizarLlantas('Filtrar');">Filtrar</button>
<button class="btn btn-primary" onclick="accionAnalizarLlantas('Salir');">Salir</button>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>