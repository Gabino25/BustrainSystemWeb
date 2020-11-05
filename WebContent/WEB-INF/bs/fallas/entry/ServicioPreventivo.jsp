<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servicio Preventivo</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/InquiryFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/ServicioPreventivo.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Servicio - Preventivo
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
<td>${fallas.fecharep}</td>
</tr>
</c:forEach>
</tbody>
</table>

<fieldset style="margin-left:10px;width:98%">
<form id="formServPrev" action="${pageContext.request.contextPath}/ServicioPreventivoCO" method="post" onsubmit=" return validaFormulario(this);" autocomplete="off">
<input type="hidden" id="accionFormId" name="accionFormName" />
<table>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectUnidadId">Eco:</label></td>
<td><select id="selectUnidadId" name="eco" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
<td style="padding: 15px 10px"/>
<td class="xxbstdtextalign"><label for="fechaRealizacion">Fecha Realizacion:</label></td>
<td><input id="fechaRealizacion" name="fechaRealizacion" type="date" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kilometraje">Kilometraje</label></td>
<td><input id="kilometraje" name="kiolometraje" required type="number"></td>
<td style="padding: 15px 10px"/>
<td class="xxbstdtextalign"><label for="TipoServicio">Tipo Servicio</label></td>
<td>
    <select id="TipoServicio" name="TipoServicio" required>
     <option value=""></option>
     <option value="MECANICO">MECANICO</option>
     <option value="ELECTRICO">ELECTRICO</option>
     <option value="HOJALATERIA Y PINTURA">HOJALATERIA Y PINTURA</option>
     <option value="CLIMAS">CLIMAS</option>
     <option value="EXTERNO">EXTERNO</option>
    </select>
</td>
</tr>
<tr>
<td colspan="2"><label>Descripcion del Servicio</label></td>
</tr>
<tr>
<td colspan="5"><textarea id="descServicio" name="descServicio" rows="4" cols="70" ></textarea></td>
</tr>
</table>

<input type="submit" id="submitButtonId" name="submitButtonId"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="folioTrx" name="folioTrx"/>
</form>
</fieldset>

<div class="rowButtons">
<table id="buttonsTable">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionServPrev('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="accionServPrev('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="accionServPrev('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="accionServPrev('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="accionServPrev('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionServPrev('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="accionServPrev('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>