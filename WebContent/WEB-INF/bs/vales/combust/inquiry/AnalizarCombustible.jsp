<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Analizar Combustible</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/combust/InquiryAnalizarCombustible.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vales/combust/InquiryAnalizarCombustible.css">

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Combustibles - Analizar
</div> 

<form id="formAnalizarCombustible" name="formAnalizarCombustible" method="post" action="${pageContext.request.contextPath}/AnalizarCombustibleCO">
<input type="hidden" name="accionAnalizaCombustible"  id="accion"/>
<input type="hidden" id="jsFechaDesdeDay" name="jsFechaDesdeDay">
<input type="hidden" id="jsFechaDesdeMonth" name="jsFechaDesdeMonth">
<input type="hidden" id="jsFechaDesdeFullYear" name="jsFechaDesdeFullYear">
<input type="hidden" id="jsFechaHastaDay" name="jsFechaHastaDay">
<input type="hidden" id="jsFechaHastaMonth" name="jsFechaHastaMonth">
<input type="hidden" id="jsFechaHastaFullYear" name="jsFechaHastaFullYear">
</form>


<table>
<tbody>
<tr>
<td>
<table>
<tr>
<td style="margin-left:20px;padding-left:20px;margin-right:20px;padding-right:20px"><div id="desdeDatePicker"  onchange="detectaCambio();"></div></td>
<td style="margin-left:20px;padding-left:20px;margin-right:20px;padding-right:20px"><div id="hastaDatePicker"  onchange="detectaCambio();"></div></td>
</tr>
<tr>
<td>
<label for="selectUnidadId">Unidad:</label>
<select id="selectUnidadId" name="unidad" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
<td>
 <label for="selectGasolineraId">Gasolinera:</label>
 <select id="selectGasolineraId" name="gasolinera" onchange="detectaCambio();">
  <option value=""></option>
  <c:forEach var="selLisGasolinera" items="${selectListGasolineras}" varStatus="row">
  <option value="${selLisGasolinera.nombre}">${selLisGasolinera.nombre}</option>
  </c:forEach>  
 </select>
</td>
</tr>
</table>
</td>
<td>
<table>
<tr>
<td><button id="CargasAexcelBtn" class="btn btn-primary" onclick="acccionAnalizarCombustible('CargasAexcel');">Cargas a Excel</button></td>
<td><button class="btn btn-primary" onclick="acccionAnalizarCombustible('RendimientosAexcel');">Rendimientos a Excel</button></td>
<td><button class="btn btn-primary" onclick="acccionAnalizarCombustible('CatalogarUnidades')">Catalogar Unidades</button></td>
<td><button class="btn btn-primary" onclick="acccionAnalizarCombustible('Salir');">Salir</button></td>
</tr>
<tr>
<td><button id="CargasAexcelBtnCategoria" class="btn btn-primary" onclick="acccionAnalizarCombustible('CargasAexcelCategoria');">Cargas a Excel por Categoria</button></td>
<td><button id="imprimirBtn" class="btn btn-primary" onclick="imprimir();">Imprimir</button></td>
</tr>

<tr>
<td class="xxbssums"><label for="sumTotal">Total:</label></td>
<td class="xxbssums"><label for="sumLitros">Litros:</label></td>
<td class="xxbssums"><label for="sumKm">KM:</label></td>
<td class="xxbssums"><label for="sumRendimiento">Rendimiento:</label></td>
</tr>
<tr>
<td class="xxbssums"><input style="text-align:right" type="text" id="sumTotal" size="15" readonly></td>
<td class="xxbssums"><input style="text-align:right" type="text" id="sumLitros" size="15" readonly></td>
<td class="xxbssums"><input style="text-align:right" type="text" id="sumKm"  size="15" readonly></td>
<td class="xxbssums"><input style="text-align:right" type="text" id="sumRendimiento" size="15" readonly></td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>

<div class="progress">
  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">
  </div>
</div>


<div id="DivTablRO">
 <table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>FECHA</th>
<th>NOTA</th>
<th>UNIDAD</th>
<th>TOTAL</th>
<th>LITROS</th>
<th>KMS</th>
<th>KM&nbsp;ANTERIOR</th>
<th>KM&nbsp;REC</th>
<th>REND</th>
<th>OPERADOR</th>
<th>FECHA&nbsp;FACT</th>
<th>GASOLINERA</th>
<th>COMBUSTIBLE</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="liDiesel" items="${listDiesel}" varStatus="row">
<tr>
<td>${liDiesel.fechaddMMyyyyV2}</td>
<td>${liDiesel.nota}</td>
<td>${liDiesel.unidad}</td>
<td>${liDiesel.total}</td>
<td>${liDiesel.litros}</td>
<td>${liDiesel.kilometraje}</td>
<td>${liDiesel.kmanterior}</td>
<td>${liDiesel.kmsrecorridos}</td>
<td>${liDiesel.rendimiento}</td>
<td>${liDiesel.operador}</td>
<td>${liDiesel.fechafactura}</td>
<td>${liDiesel.gasolinera}</td>
<td>${liDiesel.combustible}</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>

<div id="imprimirDiv" >
 <img  src="${pageContext.request.contextPath}/resources/images/BustrainGlobal.png"  style="display:block;margin-left:auto;margin-right:auto;width:250px;height:80px"> 
 <h2 style="text-align:center;">CARGAS COMBUSTIBLE</h2>
 <table id="imprimirTab"  style="margin-left: auto; margin-right: auto;border-collapse: collapse;">
 <thead>
 <tr>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;border-left: 1px solid #000;">FECHA</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;">FOLIO</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;">UNIDAD</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;">COSTO</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;">KMS</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;">LTS</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;">REND</th>
  <th style="padding-left:10px;padding-right:10px;border-top: 1px solid #000;border-bottom: 1px solid #000;border-right: 1px solid #000;">OPERADOR</th>
 </tr>
 </thead>
 </table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>