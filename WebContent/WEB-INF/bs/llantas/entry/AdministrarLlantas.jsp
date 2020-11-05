<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrar&nbsp;Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/AdministrarLlantas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/AdministrarLlantas.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Llantas - Administrar
</div> 

<table>
<tr>
<td>
<form id="formAdministrarLlantas"  action="${pageContext.request.contextPath}/AdministrarLlantasCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off">
<fieldset style="margin-left:10px;">
<table id="TablInputs">
<tr>
<td class="xxbstdtextalign"><label for="codigo">Codigo:</label></td>
<td><input type="text" id="codigo" name="codigo" required/><label id="msgCodigo"></label></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="medida">Medida:</label></td>
<td><input type="text" id="medida" name="medida" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="marca">Marca:</label></td>
<td><input type="text" id="marca" name="marca" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="modelo">Modelo:</label></td>
<td><input type="text" id="modelo" name="modelo" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="profundidad">Profundidad&nbsp;(mm):</label></td>
<td><input type="number" id="profundidad" name="profundidad" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="estado">Estado:</label></td>
<td><select id="estado" name="estado" required>
    <option value=""></option>
    <option value="NUEVA">NUEVA</option>
    <option value="USADA">USADA</option>
    <option value="RENOVADA">RENOVADA</option>
    </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input type="number" id="costo" name="costo" step="0.01" min="0" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fechaAlta">Fecha&nbsp;Alta &nbsp;:</label></td>
<td><input type="date" id="fechaAlta" name="fechaAlta" required/></td>
</tr>
</table><!-- END <table id="TablInputs"> -->
</fieldset>
<input type="hidden" id="accion" name="accionAdministrarLlantas"/>
<input id="submitGuardar" type="submit" />
<input id="resetFormId" type="reset"/>
<input type="hidden" name="otherTransaction"  id="otherTransaction" value="${reqAttrOtherTransaction}"/>
<input type="hidden" id="llantaTrx" name="llantaTrx"/>
</form>
</td>
<td rowspan="2">
<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>NOMBRE</th>
<th>MEDIDA</th>
<th>MARCA</th>
<th>MODELO</th>
<th>COSTO</th>
<th>ESTADO</th>
<th>KM&nbsp;ACUMULADO</th>
<th>PROF&nbsp;INI</th>
<th>PROF&nbsp;ACT</th>
<th>FECHA&nbsp;ALTA</th>
<th>FECHA&nbsp;REV</th>
<th>UNIDAD</th>
<th>POSICION</th>
<th>PRESION</th>
<th>NOTAS</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="llantas" items="${listLlantas}" varStatus="row">
 <tr>
 <td>${llantas.nombre}</td>
 <td>${llantas.medida}</td>
 <td>${llantas.marca}</td>
 <td>${llantas.modelo}</td>
 <td class="xxbstdtextalignV3">${llantas.enUScosto}</td>
 <td>${llantas.estado}</td>
 <td class="xxbstdtextalignV3">${llantas.kmacumulado}</td>
 <td class="xxbstdtextalignV3">${llantas.profundidad_inicial}</td>
 <td class="xxbstdtextalignV3">${llantas.profundidad_actual}</td>
 <td>${llantas.fecha_altaddMMyyyyV2}</td>
 <td>${llantas.fecha_revisionddMMyyyyV2}</td>
 <td>${llantas.unidad}</td>
 <td>${llantas.posicion}</td>
 <td class="xxbstdtextalignV3">${llantas.presion}</td>
 <td>${llantas.nota}</td>
 </tr>
 </c:forEach>
</tbody>
</table> <!-- END <table id="TablRO" -->
</td>
</tr>
<tr>
<td>
<table>
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td>
<button id="NuevaBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Nueva');">Nueva</button>
</td>
<td>
<button id="GuardarBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Guardar');">Guardar</button>
</td>
<td>
<button id="BorrarBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Borrar');">Borrar</button>
</td>
<% } %>
<td>
<button id="BuscarBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Buscar');">Buscar</button>
</td>
</tr>
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td>
<button id="ModificarBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Modificar');">Modificar</button>
</td>
<td>
<button id="CancelarBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Cancelar');">Cancelar</button>
</td>
<% } %>
<td>
<button id="SalirBtn" class="btn btn-primary" onclick="accionAdministrarLlantas('Salir');">Salir</button>
</td>
</tr>
</table> <!-- END table Buttons -->
</td>
</tr>
</table> <!-- END main table -->
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>