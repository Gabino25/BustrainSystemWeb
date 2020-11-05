<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Filtrado Facturas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ap/inquiry/FiltradoFacturas.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ap/inquiry/FiltradoFacturas.css">
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Compras - Filtrado Facturas
</div> 

<table>
<tr>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><div id="desdeDatePicker" onchange="detectaCambio();"></div></td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><div id="hastaDatePicker" onchange="detectaCambio();"></div></td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><fieldset>
<div class="form-check">
 <input class="form-check-input" type="radio" id="todoRadioId" name="TodoUnProvDesc" checked/><label class="form-check-label"  for="todoRadioId">Todo:</label><br><br>
</div>
     <input class="form-check-input" type="radio" id="unidadRadioId" name="TodoUnProvDesc" /><label class="form-check-label"  for="unidadRadioId">Unidad:</label><br><br>
     <input class="form-check-input" type="radio" id="proveedorRadioId" name="TodoUnProvDesc" /><label  class="form-check-label" for="proveedorRadioId">Proveedor:</label><br><br>
     <input class="form-check-input" type="radio" id="descripcionRadioId" name="TodoUnProvDesc" /><label  class="form-check-label" for="descripcionRadioId">Descripcion:</label>
    </fieldset></td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1">
<label id="facturasLab">Facturas:</label>
<br>
<label id="detalleLab">Detalle:</label>
</td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1">
<button class="btn btn-primary" onclick="accionFiltradoFacturas('GastoMensualExcel');">Gasto Mensual Excel</button><br><br>
<button class="btn btn-primary" onclick="accionFiltradoFacturas('Salir');">Salir</button>
</td>
</tr>
</table>

<form id="formFiltradoFacturas" action="${pageContext.request.contextPath}/FiltradoFacturasCO" method="post">
<input type="hidden" id="accion" name="accion"/>
</form>
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><label>Unidad:</label></td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><select id="unidad" name="unidad" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
  </td>
</tr>
<tr>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><label>Proveedor:</label></td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><select id="proveedor" name="proveedor" required>
<option value=""></option>
<c:forEach var="selListCatProveed" items="${selectListCatProveedores}" varStatus="row">
  <option value="${selListCatProveed.empresa}">${selListCatProveed.empresa}</option>
  </c:forEach>  
</select>
</td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><button class="btn btn-primary" onclick="accionFiltradoFacturas('Filtrar');">Filtrar</button></td>
</tr>
<tr>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><label>Descripcion:</label></td>
<td class="xxbsTdLeftRightV1 xxbsTdTopBottomV1"><input id="descripcion" type="text" size="60"></td>
</tr>
</table>
</fieldset>
<div id="DivTablRO">
<table id="TablRO" class="table table-striped table-bordered table-condensed" >
 <thead class="xxbstheadbg">
 <tr>
 <th>FOLIO</th>
 <th>PROVEEDOR</th>
 <th>DESCRIPCION</th>
 <th>UNIDAD</th>
 <th>COSTO</th>
 <th>FECHA</th>
 </tr>
 </thead>
 <tbody class="xxbstbodybg">
 <c:forEach  var="liFacturas" items="${listFacturas}" varStatus="row">
 <tr>
 <td>${liFacturas.folio}</td>
 <td>${liFacturas.proveedor}</td>
 <td>${liFacturas.descripcion}</td>
 <td>${liFacturas.unidad}</td>
 <td>$${liFacturas.costoenUSPatternV3}</td>
 <td>${liFacturas.fechaddMMyyyyV2}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>