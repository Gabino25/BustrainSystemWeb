<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Factruras</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<div style="height:10px;width:100%;"></div>
<fieldset style="margin-left:10px;margin-top:10px;width:98%">

<table>
<tbody>
<tr>
<td class="xxbstdtextalign"><label for="proveedor">Proveedor:</label></td>
<td>
<input type="text" id="proveedor" name="proveedor" value="${facturaDto.proveedor}" size="80" readonly/>
</td>
<td class="xxbstdtextalign"><label for="folio">Folio:</label></td>
<td><input type="text" id="folio" name="folio" value="${facturaDto.folio}" readonly></td>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input type="number" id="costo" name="costo" value="${facturaDto.costo}" readonly></td>
</tr>
<tr>
<td  class="xxbstdtextalign"><label for="orden">Orden:</label></td>
<td>
<input type="number" id="orden" name="orden" min="0" value="${facturaDto.orden}" readonly/>
</td>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" value="${facturaDto.fechayyyyMMdd}" readonly></td>
<td class="xxbstdtextalign"><label for="contado">Tipo:</label></td>
<td colspan="2">
<input type="text" id="contado" name="tipo" value="${facturaDto.tipo}" readonly/>
</td>
</tr>
</tbody>
</table>

</fieldset>

<div style="height:10px;width:100%;"></div>

<table id="TablRO" class="table table-striped table-bordered table-condensed" style="margin:10px;width:95%;">
 <thead>
 <tr>
 <th>CONCEPTO</th>
 <th>UNIDAD</th>
 <th>PRECIO</th>
 <th>GASTO</th>
 </tr>
 </thead>
 <tbody>
 <c:forEach  var="listConceptos" items="${listConceptosFactura}" varStatus="row">
 <tr>
 <td>${listConceptos.concepto}</td>
 <td>${listConceptos.eco}</td>
 <td>${listConceptos.costo}</td>
 <td>${listConceptos.gasto}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>

 <form action="${pageContext.request.contextPath}/FacturasCO?accionFactura=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary" >Regresar</button>
 </form>
 
 <jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>