<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rutas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Rutas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/upd/RutasUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%;">
<form id="formUpd" action="${pageContext.request.contextPath}/RutasCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off" >
<input id="accionId" type="hidden" name="accionName"> 
<label>Empresa:</label>
<input id="clienteTrx" type="hidden" value="${Ruta.cliente}"/>
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
<td class="xxbstdtextalign"><label for="clave">Clave:</label></td>
<td><input id="clave" type="text" name="clave" maxlength="50" value="${Ruta.clave}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRuta">Nombre&nbsp;Ruta:</label></td>
<td><input id="nombreRuta" type="text"  name="nombreRuta" size=50 value="${Ruta.ruta}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input id="costo"  type="number" name="costo" size=35 value="${Ruta.costo}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date"  name="fecha" size=35 value="${Ruta.fechayyyyMMdd}" required/></td>
</tr>
<tr>
<td colspan=2>
<table>
<tr>
<td>
<fieldset>
  <legend>Tipo&nbsp;Servicio</legend>
  <input id="tipounidadTrx" type="hidden" value="${Ruta.tipounidad}">
  <input id="tipServAutobus" type="radio" value="AUTOBUS" name="tipoServicio"><label for="tipServAutobus" style="padding-left:5px;padding-right:40px;">Autobus</label>
  <input id="tipServtaxi" type="radio" value="TAXI" name="tipoServicio"><label for="tipServtaxi" style="padding-left:5px;padding-right:40px;">Taxi</label>
</fieldset>
</td>
<td>
<fieldset>
  <legend>Tipo&nbsp;Pago</legend>
  <input id="tipopagoTrx" type="hidden" value="${Ruta.tipopago}">
  <input id="tipPagoContado" type="radio" value="CONTADO" name="tipoPago"><label for="tipPagoContado" style="padding-left:5px;padding-right:40px;">Contado</label>
  <input id="tipPagoCredito" type="radio" value="CREDITO" name="tipoPago"><label for="tipPagoCredito" style="padding-left:5px;padding-right:40px;">Credito</label>
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
<textarea id="descripcion" rows="6" cols="60" name="descripcion">${Ruta.descripcion}</textarea>
</td>
</tr>
</table>
</td>
</tr>
</table>

<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="numeroRutaTrx" name="numeroRutaTrx" value="${Ruta.numero}"/>

</form>
</fieldset>

<div style="margin-left:10px;width:98%;">
<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>