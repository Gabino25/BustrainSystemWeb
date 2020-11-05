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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ap/upd/FacturasUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<div id="moduloPantallaDiv" class="modPantDiv">
 Compras - Capturar Facturas
</div> 

<select id="unidadDemo" name="unidadDemo" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
  
<select id="gastoDemo" name="gastoDemo" required>
  <option value=""></option>
  <c:forEach var="selLisCtasOper" items="${selectListCtasOper}" varStatus="row">
  <option value="${selLisCtasOper.descripcioncta}">${selLisCtasOper.descripcioncta}</option>
  </c:forEach>  
  </select>  

<input type="hidden" id="proveedorFrom" name="proveedorFrom" value="${facturaDto.proveedor}"/>

<div style="height:10px;width:100%;"></div>


<form id="formUpd" action="${pageContext.request.contextPath}/FacturasCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off" >
<fieldset style="margin-left:10px;margin-top:10px;width:98%">
<table>
<tbody>
<tr>
<td><label>Proveedor:</label></td>
<td>
<select id="proveedor" name="proveedor" required>
<option value=""></option>
<c:forEach var="selListCatProveed" items="${selectListCatProveedores}" varStatus="row">
  <option value="${selListCatProveed.empresa}">${selListCatProveed.empresa}</option>
  </c:forEach>  
</select>
</td>
<td>
<label>Folio:</label>
</td>
<td><input type="text" id="folio" name="folio" value="${facturaDto.folio}" required></td>
<td><label>Costo:</label></td>
<td><input type="number" id="costo" name="costo" value="${facturaDto.costo}" required></td>
</tr>
<tr>
<td><label>Orden:</label></td>
<td>
<input type="number" id="orden" name="orden" min="0" value="${facturaDto.orden}" required/>
</td>
<td><label>Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" value="${facturaDto.fechayyyyMMdd}" required></td>
<td colspan="2">
<input type="hidden" id="tipoTrx" name="tipoTrx" value="${facturaDto.tipo}"/>
<input type="hidden" id="numfact" name="numfact" value="${facturaDto.numfact}"/>
<input type="radio" id="contado" name="tipo" value="CONTADO" checked><label for="contado">CONTADO</label>&nbsp;&nbsp;&nbsp;<input type="radio" id="credito" name="tipo" value="CREDITO"><label for="credito">CREDITO</label>
</td>
</tr>
</tbody>
</table>
</fieldset>

<div style="height:20px;width:100%;"></div>

<table id="TablUpd" class="table table-striped table-bordered table-condensed" style="margin:10px;width:95%;">
 <thead>
 <tr>
 <th>CONCEPTO</th>
 <th>UNIDAD</th>
 <th>PRECIO</th>
 <th>GASTO</th>
 <!-- <th>NUMCONCEPTO</th> (4) -->
 </tr>
 </thead>
 <tbody>
 <c:forEach  var="listConceptos" items="${listConceptosFactura}" varStatus="row">
 <tr>
 <td>${listConceptos.concepto}</td>
 <td>${listConceptos.eco}</td>
 <td>${listConceptos.costo}</td>
 <td>${listConceptos.gasto}</td>
 <td>${listConceptos.numconcepto}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>

<input type="submit" id="submitFormFactura" name="submitFormFactura"/>
<input type="reset" id="resetFormFactura" name="resetFormFactura"/>
<input type="hidden" id="accionFactura" name="accionFactura"/>
<input type="hidden" id="facturaTrx" name="facturaTrx"/>
<input type="hidden" id="inputCountRows" name="inputCountRows"/>
</form>

<button class="btn btn-primary" id="RegresarBtn" onclick="Regresar();" >Regresar</button>
<button class="btn btn-primary" id="UpdateBtn" onclick="Actualizar();" >Actualizar</button>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>