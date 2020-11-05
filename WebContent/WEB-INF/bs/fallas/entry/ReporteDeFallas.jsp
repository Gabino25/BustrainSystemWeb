<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte de Fallas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/InquiryFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/ReporteDeFallas.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Servicio - Reporte de Fallas
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>FOLIO</th>
<th>UNIDAD</th>
<th>REPORTE</th>
<th>FECHA</th>

</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="fallas" items="${listFallas}" varStatus="row">
<tr>
<td>${fallas.numero}</td>
<td>${fallas.eco}</td>
<td>${fallas.descripcion}</td>
<td>${fallas.fechaddMMyyyyV2}</td>
</tr>
</c:forEach>
</tbody>
</table>

<fieldset style="margin-left:10px;width:98%">
<form id="formReporteDeFallas" action="${pageContext.request.contextPath}/ReporteDeFallasCO" method="post" onsubmit=" return validaFormulario(this);" autocomplete="off">
<input type="hidden" id="accionFormId" name="accionFormName" />
<table>
<tr>
<td class="xxbstdtextalign"><label for="selectUnidadId">Eco:</label></td>
<td><select id="selectUnidadId" name="eco" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
<td style="padding: 5px 10px"></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectListCatTrab">Reporta:</label></td>
<td><select id="selectListCatTrab" name="reporta" required>
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</td>
<td style="padding: 5px 10px"></td>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" name="fecha" type="date" readonly required /></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kilometraje">Kilometraje:</label></td>
<td><input id="kilometraje" name="kilometraje" required></td>
<td style="padding: 5px 10px"></td>
<td class="xxbstdtextalign"><label for="hora">Hora:</label></td>
<td><input id="hora" type="time" name="hora" readonly required /></td>
</tr>
<tr>
<td rowspan="3" colspan="5">
<label>Descripcion de la falla:</label><br>
<textarea id="descFalla" name="descFalla" cols="60" required></textarea>
</td>
</tr>
</table>
<input type="submit" id="submitButtonId" name="submitButtonId"/>
<input type="reset" id="resetFormId" name="resetFormId"/>
<input type="hidden" id="folioTrx" name="folioTrx"/>
</form>
</fieldset>

<div class="rowButtons">
<table id="buttonsTable">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionRepDefa('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionRepDefa('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionRepDefa('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionRepDefa('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionRepDefa('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionRepDefa('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionRepDefa('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html> 