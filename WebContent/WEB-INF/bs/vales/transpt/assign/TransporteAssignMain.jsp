<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asignacion Vales</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/transpt/ValesTransporte.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/sol.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vales/transpt/ValesTransporte.css">

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Asignacion Vales - Asignacion
</div> 

 <table id="TablRO" class="table table-striped table-bordered table-condensed" >
<thead class="xxbstheadbg">
<tr>
<th>NUMERO</th>
<th>NOMBRE</th>
<th>FOLIO&nbsp;INICIAL</th>
<th>FOLIO&nbsp;FINAL</th>
<th>FECHA</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
  <c:forEach var="asignVales" items="${listAsignacionVales}" varStatus="row">
  <tr>
  <td>${asignVales.numero}</td>
  <td>${asignVales.nombre}</td>
  <td>${asignVales.folioinicial}</td>
  <td>${asignVales.foliofinal}</td>
  <td>${asignVales.fechaddMMyyyyV2}</td>
  </tr>
  </c:forEach>
</tbody>
</table>



<fieldset style="margin-left:10px;width:98%">

<div style=" margin-top: 1px; margin-bottom: 1px; margin-left: 150px;margin-right: 150px;">
<div>
<form id="formAssignTranspt" name="formAssignCombust" method="post" action="${pageContext.request.contextPath}/ValesAssignMainCO" onsubmit="return validaFormulario(this);" autocomplete="off">
  <table class="formTable">
  <tr>
  <td class="xxbstdtextalign"><label for="selectListCatTrab">Operador:</label></td>
  <td>
    <select id="selectListCatTrab" name="operador" style="width: 400px"  >
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
  </td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label for="folioInicial">Folio&nbsp;Inicial:</label></td>
  <td><input id ="folioInicial" type="number" name="folioInicial" min="0" required/><label id="msgFolioInicial"></label></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label for="folioFinal">Folio&nbsp;Final:</label></td>
  <td><input id="folioFinal" type="number" name="folioFinal" min="0" required><label id="msgFolioFinal"></label></td>
  </tr>
  </table>
  <input type="hidden" name="formAssignAccion"  id="accion"/>
  <input type="hidden" name="idFolios"  id="folios"/>
  <input id="submitFormId" type="submit"/>
</form>
</div>
</div>

<table  style="margin: 0px auto;">
<tr>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="valFormAssignValesCombust('Guardar');">Guardar</button></td>
<td><button id="EliminarBtn" class="btn btn-primary" onclick="valFormAssignValesCombust('Eliminar');">Eliminar</button></td>
<td><button id="SalirBtn" class="btn btn-primary" onclick="valFormAssignValesCombust('Salir');">Salir</button></td>
</tr>
</table>

</fieldset>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>