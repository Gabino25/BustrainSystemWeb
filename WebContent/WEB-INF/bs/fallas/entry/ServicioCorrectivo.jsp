<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servicio Correctivo</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/InquiryFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/ServicioCorrectivo.js"></script>


</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Servicio - Correctivo
</div> 

<table id="TablROServCorr" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>NUMERO</th>
<th>UNIDAD</th>
<th>DESCRIPCION</th>
<th>REPARACION</th>
<th>KILOMETRAJE</th>
<th>REPORTA</th>
<th>FECHA&nbsp;ALTA</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="fallas" items="${listFallas}" varStatus="row">
<tr>
<td>${fallas.numero}</td>
<td>${fallas.eco}</td>
<td>${fallas.descripcion}</td>
<td>${fallas.reparacion}</td>
<td>${fallas.kilometraje}</td>
<td>${fallas.reporta}</td>
<td>${fallas.fechaalta}</td>
</tr>
</c:forEach>
</tbody>
</table>


<fieldset style="margin-left:10px;width:98%">
<form id="formServCorr" name="formServCorr" action="${pageContext.request.contextPath}/ServicioCorrectivoCO" method="post" onsubmit=" return validaFormulario(this); " autocomplete="off">
<input type="hidden" id="accionFormId" name="accionFormName" />
<label for="folioRO">Folio:</label><input   id="folioRO" name="folioRO" type="text" readonly />
<label for="ecoRO">Eco:</label><input   id="ecoRO" name="ecoRO" type="text" readonly />
<label for="reportaRO">Reporta:</label><input  id="reportaRO" name="reportaRO" type="text" readonly />
<br>
<label>Descripcion de la falla:</label>
<br>
<textarea  id="descripcionFalla" rows="4" cols="100" readonly></textarea>
<br>
<label id="repDeFallaLab">Reparacion de la falla:</label>
<br>
<textarea  id="repDeFallaLabInput" name="reparacion" rows="4" cols="100" required></textarea>
<input  type="submit" id="submitButtonId" name="submitButtonId"/>
<input  type="reset" id="resetButtonId" name="resetButtonId"/>
<input type="hidden" id="numeroTrx" name="numeroTrx">
</form>
</fieldset>



<div id="divReporteInfo">

</div>

<div class="rowButtons">
<table id="buttonsTable">
<tr>
<td><button id="ReporteBtn" class="btn btn-primary" onclick="accionServCorr('Reporte');">Reporte</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionServCorr('Guardar');">Guardar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionServCorr('Cancelar');">Cancelar</button></td>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionServCorr('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>