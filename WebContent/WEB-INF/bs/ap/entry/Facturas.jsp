<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Facturas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ap/entry/Facturas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ap/entry/Facturas.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Compras - Capturar Facturas
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed" >
 <thead class="xxbstheadbg">
 <tr>
 <th>NUMERO</th>
 <th>PROVEEDOR</th>
 <th>ORDEN</th>
 <th>FOLIO</th>
 <th>COSTO</th>
 <th>FECHA</th>
 <th>TIPO</th>
 </tr>
 </thead>
 <tbody class="xxbstbodybg">
 <c:forEach  var="liFacturas" items="${listFacturas}" varStatus="row">
 <tr>
 <td>${liFacturas.numfact}</td>
 <td>${liFacturas.proveedor}</td>
 <td>${liFacturas.orden}</td>
 <td>${liFacturas.folio}</td>
 <td>$${liFacturas.costoenUSPatternV3}</td>
 <td>${liFacturas.fechaddMMyyyyV2}</td>
 <td>${liFacturas.tipo}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>

<fieldset style="margin-left:10px;width:98%">
<form id="formFactura" action="${pageContext.request.contextPath}/FacturasCO" method="post" onsubmit="return validaFormFactura(this);" autocomplete="off" >
<table>
<tbody>
<tr>
<td class="xxbstdtextalign"><label for="proveedor">Proveedor:</label></td>
<td>
<select id="proveedor" name="proveedor" >
<option value=""></option>
<c:forEach var="selListCatProveed" items="${selectListCatProveedores}" varStatus="row">
  <option value="${selListCatProveed.empresa}">${selListCatProveed.empresa}</option>
  </c:forEach>  
</select>
</td>
<td class="xxbstdtextalign">
<label for="folio">Folio:</label>
</td>
<td><input type="text" id="folio" name="folio" required></td>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input type="number" id="costo" name="costo" min="0" step="0.01" required></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="orden">Orden:</label></td>
<td>
<input type="number" id="orden" name="orden" min="0" />
</td>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" required></td>
<td colspan="2">
<input type="radio" id="contado" name="tipo" value="CONTADO" checked><label for="contado">CONTADO</label>&nbsp;&nbsp;&nbsp;<input type="radio" id="credito" name="tipo" value="CREDITO"><label for="credito">CREDITO</label>
</td>
</tr>
</tbody>
</table>
<input type="submit" id="submitFormFactura" name="submitFormFactura"/>
<input type="reset" id="resetFormFactura" name="resetFormFactura"/>
<input type="hidden" id="accionFactura" name="accionFactura"/>
<input type="hidden" id="facturaTrx" name="facturaTrx"/>
</form>
</fieldset>

<table>
<thead>
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button class="btn btn-primary" id="NuevaBtn"  onclick="accionFactura('Nueva');">Nueva</button></td>
<td><button class="btn btn-primary" id="GuardarBtn" onclick="accionFactura('Guardar');">Guardar</button></td>
<td><button class="btn btn-primary" id="BorrarBtn" onclick="accionFactura('Borrar');">Borrar</button></td>
<% } %>
<td><button class="btn btn-primary" id="BuscarBtn" onclick="accionFactura('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button class="btn btn-primary" id="ModificarBtn" onclick="accionFactura('Modificar');">Modificar</button></td>
<td><button class="btn btn-primary" id="CancelarBtn" onclick="accionFactura('Cancelar');">Cancelar</button></td>
<% } %>
<td><button class="btn btn-primary" id="SalirBtn" onclick="accionFactura('Salir');">Salir</button></td>
</tr>
</thead>
</table>

<div style="height:15px;width:100%;"></div>

<div id="lineasFacturaDiv">
<form id="formLineas" action="${pageContext.request.contextPath}/FacturasCO" method="post" onsubmit="return validaFormulario();" autocomplete="off" >
<table id="linFactTab" class="table table-striped table-bordered table-condensed" style="margin-left:10px;margin-right:10px;width:95%;" >
<thead>
<tr>
<td>
<button type="button" id="AgregarBtn" class="btn btn-primary" onclick="agregarLinea();">Agregar</button>
</td>
</tr>
<tr>
<th>CONCEPTO</th>
<th>UNIDAD</th>
<th>PRECIO</th>
<th>GASTO</th>
</tr>
</thead>
<tbody>
<tr>
<td><input type="text" id="concepto2" name="concepto2" required></td>
<td>
<select id="unidad2" name="unidad2" >
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
  </td>
<td><input type="number" id="precio2" name="precio2" min="0" step="0.01" required></td>
<td><select id="gasto2" name="gasto2" >
  <option value=""></option>
  <c:forEach var="selLisCtasOper" items="${selectListCtasOper}" varStatus="row">
  <option value="${selLisCtasOper.descripcioncta}">${selLisCtasOper.descripcioncta}</option>
  </c:forEach>  
  </select>
</td>
</tr>
</tbody>
</table>
<input type="submit" id="submitFormLineas"/>
<input type="reset" id="resetFormLineas"/>
<input type="hidden" id="accionFacturaL" name="accionFacturaL"/>

</form>
<button class="btn btn-primary" id="guardarFacturaBtn" onclick="guardarFactura();">Guardar Lineas</button>
<button class="btn btn-primary" id="cancelarFacturaBtn" onclick="cancelarFactura();">Cancelar Lineas</button>
</div>

<form id="idFormTrx" action="${pageContext.request.contextPath}/FacturasCO" method="post" onsubmit="return validaFormFactura(this);" autocomplete="off">
<input type="hidden" id="inputCountRows" name="inputCountRows"/>
<input type="hidden" id="accionFormTrx" name="accionFacturaL"/>
<!--  -->
<input type="hidden" id="proveedorV2" name="proveedorV2"/>
<input type="hidden" id="folioV2" name="folioV2"/>
<input type="hidden" id="costoV2" name="costoV2"/>
<input type="hidden" id="fechaV2" name="fechaV2"/>
<input type="hidden" id="ordenV2" name="ordenV2"/>
<input type="hidden" id="tipoV2" name="tipoV2"/>
<!--  -->
</form>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>