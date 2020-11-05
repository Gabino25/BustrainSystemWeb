<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rutas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Rutas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/Rutas.js"></script>


</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogos - Rutas
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>NUMERO</th>
<th>CLAVE</th>
<th>CLIENTE</th>
<th>RUTA</th>
<th>COSTO</th>
<th>FECHA</th>
<th>DESCRIPCION</th>
<th>TIPOUNIDAD</th>
<th>TIPOPAGO</th>
<th>HORAINICIO</th>
<th>HORAFIN</th>
<th>ESTADO</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
 <c:forEach  var="rutas" items="${listRutas}" varStatus="row">
<tr>
<td>${rutas.numero}</td>
<td>${rutas.clave}</td>
<td>${rutas.cliente}</td>
<td>${rutas.ruta}</td>
<td>${rutas.costo}</td>
<td>${rutas.fechaddMMyyyyV2}</td>
<td>${rutas.descripcion}</td>
<td>${rutas.tipounidad}</td>
<td>${rutas.tipopago}</td>
<td>${rutas.horainicio}</td>
<td>${rutas.horafin}</td>
<td>${rutas.estado}</td>
</tr>
</c:forEach>
</tbody>
</table>

<form id="catRutasForm" action="${pageContext.request.contextPath}/RutasCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off" >
<fieldset style="margin-left:10px;width:98%;">
<input id="accionId" type="hidden" name="accionName"> 
<label for="selectListEmpresas">Empresa:</label>
<select id="selectListEmpresas" name="empresa" required>
  <option value=""></option>
  <c:forEach var="selListCatClients" items="${selectListCatClientes}" varStatus="row">
  <option value="${selListCatClients.empresa}">${selListCatClients.empresa}</option>
  </c:forEach>  
</select>

<table>
<tr>
<td>
<table>
<tr>
<td class="xxbstdtextalign">
<label for="clave">Clave:</label>
</td>
<td><input id="clave" type="text" name="clave" maxlength="50" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRuta">Nombre&nbsp;Ruta:</label></td>
<td><input id="nombreRuta" type="text"  name="nombreRuta" size=50 required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input id="costo"  type="number" name="costo" size=35 required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date"  name="fecha" size=35 required/></td>
</tr>
<tr>
<td colspan=2>
<table>
<tr>
<td>
<fieldset>
  <legend>Tipo&nbsp;Servicio</legend>
  <input id="tipServAutobus" type="radio" value="AUTOBUS" name="tipoServicio" checked><label for="tipServAutobus" style="padding-left:5px;padding-right:40px;">Autobus</label>
  <input id="tipServtaxi" type="radio" value="TAXI" name="tipoServicio"><label for="tipServtaxi" style="padding-left:5px;padding-right:40px;">Taxi</label>
</fieldset>
</td>
<td>
<fieldset>
  <legend>Tipo&nbsp;Pago</legend>
  <input id="tipPagoContado" type="radio" value="CONTADO" name="tipoPago" checked><label for="tipPagoContado" style="padding-left:5px;padding-right:40px;">Contado</label>
  <input id="tipPagoCredito" type="radio" value="CREDITO" name="tipoPago"><label  for="tipPagoCredito" style="padding-left:5px;padding-right:40px;">Credito</label>
</fieldset>
</td>
</tr>
</table>
</td>
</tr>
</table>

</td>
<td class="xxbstdvalign">
<table>
<tr>
<td><label>Descripcion</label></td>
</tr>
<tr>
<td>
<textarea id="descripcion" rows="6" cols="60" name="descripcion"></textarea>
</td>
</tr>
</table>
</td>
</tr>
</table>
</fieldset>

<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="numeroRutaTrx" name="numeroRutaTrx"/>

</form>

<div style=" margin-top: 1px; margin-bottom: 1px; margin-left: 150px;margin-right: 150px;">
<table style="margin: 0px auto;">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="acccionCatRutas('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="acccionCatRutas('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="acccionCatRutas('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="acccionCatRutas('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="acccionCatRutas('Modificar');">Modificar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="acccionCatRutas('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="acccionCatRutas('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>