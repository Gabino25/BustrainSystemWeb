<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Diesel</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/combust/EntryValesCombustible.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vales/combust/EntryValesCombustible.css"/>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Combustible - Cargas
</div> 

<table>
<tr>
<td style="margin-right: 50px;
           padding-right: 50px;
           vertical-align:top;">
<div id="fechaDatePicker" onchange="detectaCambio();">
</div>
<form id="formEntryCombustible" name="formEntryCombustible" method="post" action="${pageContext.request.contextPath}/EntryCombustibleCO" onsubmit=" return validaFormulario(this);" autocomplete="off" >
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr>
<td class="xxbstdtextalign"><label for="folio">Folio:</label></td>
<td><input id="folio" name="folio" type="number" min="0" required/><label id="folioMsg"></label></td>
</tr>
<tr><td class="xxbstdtextalign"><label for="fechaC">Fecha de Carga:</label></td>
<td><input id=fechaC type=date name=fechaC required/></td></tr>
<tr>
<td class="xxbstdtextalign"><label for="selectGasolineraId">Gasolinera:</label></td>
<td>
<select id="selectGasolineraId" name="gasolinera" required>
  <option value=""></option>
  <c:forEach var="selLisGasolinera" items="${selectListGasolineras}" varStatus="row">
  <option value="${selLisGasolinera.nombre}">${selLisGasolinera.nombre}</option>
  </c:forEach>  
  </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td>
<td><select id="selectUnidadId" name="unidad" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectOperadorId">Operador:</label></td>
<td><select id="selectOperadorId" name="operador" required>
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="litros">Litros:</label></td>
<td><input id="litros" name="litros" type="number" min="0" step="0.001" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="total">Total:&nbsp;&nbsp;&nbsp;$</label></td>
<td><input id="total" name="total" type="number" min="0" step="0.01" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kmanterior">Km&nbsp;Anterior:</label></td>
<td><input id="kmanterior" name="kmanterior" type="number" min="0" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kmactual">Km&nbsp;Actual:</label></td>
<td><input id="kmactual" name="kmactual" type="number" min="0" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="combustible">Comb:</label></td>
<td><select id="combustible" name="combustible" required>
    <option value=""></option>
    <option value="MAGNA">MAGNA</option>
    <option value="PREMIUM">PREMIUM</option>
    <option value="DIESEL">DIESEL</option>
    </select></td>
</tr>
</table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" name="accionEntryCombustible"  id="accion"/>
<input type="hidden" id="folioVale" name="folioVale"/>
</form>
</td> <!--  END TD primera Columna -->
<td>
<div id="DivTablRO">
<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>NOTA</th>
<th>UNIDAD</th>
<th>TOTAL</th>
<th>LITROS</th>
<th>KILOMENTRAJE</th>
<th>GASOLINERA</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="liDiesel" items="${listDiesel}" varStatus="row">
<tr>
<td>${liDiesel.nota}</td>
<td>${liDiesel.unidad}</td>
<td class="xxbstar">${liDiesel.enUStotal}</td>
<td class="xxbstar">${liDiesel.enUSV2litros}</td>
<td class="xxbstar">${liDiesel.kilometraje}</td>
<td>${liDiesel.gasolinera}</td>
</tr>
</c:forEach>
</tbody>
<tfoot>
<tr>
<th>NOTA</th>
<th>UNIDAD</th>
<th class="xxbstar">TOTAL</th>
<th class="xxbstar">LITROS</th>
<th>KILOMENTRAJE</th>
<th>GASOLINERA</th>
</tr>
</tfoot>
</table>
</div>
</td><!-- END TD Segunda Columna -->
</tr>
</table><!-- END table Contenedora -->
<table>
<tr>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="acccionEntryCombustible('Guardar');">Guardar</button></td>
<td><button id="EliminarBtn" class="btn btn-primary" onclick="acccionEntryCombustible('Eliminar');">Eliminar</button></td>
<td><button class="btn btn-primary" onclick="acccionEntryCombustible('Salir');">Salir</button></td>
</tr>
</table>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>