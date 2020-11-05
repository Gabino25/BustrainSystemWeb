<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Repaciones Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/MovimientosLlantas.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/upd/ReparacionesLlantasUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%;">

<form id="formUpd" action="${pageContext.request.contextPath}/ReparacionesLlantasCO" method="post">
<table id="TablInputs">
<tr>
<td class="xxbstdtextalign"><label>Llanta:</label></td>
<td>
<select id="selectLlantaId" name="llanta" >
<option value="${movimientoLlanta.nombre}">${movimientoLlanta.nombre}</option>
<c:forEach var="selLisLlantas" items="${selectListLlantas}" varStatus="row">
<option value="${selLisLlantas.nombre}">${selLisLlantas.nombre}</option>
</c:forEach>  
</select>
</td>
<td class="xxbstdtextalign"><label>Unidad:</label></td>
<td>
<select id="selectUnidadId" name="unidad" >
  <option value="${movimientoLlanta.unidad}">${movimientoLlanta.unidad}</option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
</select>	
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" value="${movimientoLlanta.fechayyyyMMdd}" required/></td>
<td class="xxbstdtextalign"><label>Kilometraje:</label></td>
<td><input type="number" id="kilometraje" name="kilometraje" min="0" value="${movimientoLlanta.kilometraje}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Operador:</label></td>
<td>
<select id="selectOperadorId" name="operador" style="width: 400px" >
  <option value="${movimientoLlanta.operador}">${movimientoLlanta.operador}</option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</td>

<td class="xxbstdtextalign"><label>Posicion:</label></td>
<td>
<select id="selectPosicionId" name="posicion" required>
<option value="${movimientoLlanta.posicion}">${movimientoLlanta.posicion}</option>
<option value="DD">DD</option>
<option value="DI">DI</option>
<option value="TDE">TDE</option>
<option value="TDI">TDI</option>
<option value="TII">TII</option>
<option value="TIE">TIE</option>
<option value="REF">REF</option>
</select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Costo:</label></td>
<td><input type="number" id="costo" name="costo" min="0" value="${movimientoLlanta.costo}" required/></td>
</tr>
<tr>
<td>
<label>Nota:</label>
</td>
</tr>
<tr>
<td colspan="4"><textarea rows="2" cols="80" id="nota" name="nota" required>${movimientoLlanta.nota}</textarea></td>
</tr>
</table>
<input type="hidden" id="accionMovimientosLlantas" name="accionMovimientosLlantas"/>
<input type="hidden" id="folio" name="folio" value="${movimientoLlanta.folio}">
<input id="submitFormId" type="submit" id="submitForm">
<input id="resetFormId" type="reset" id="resetForm">
</form>
</fieldset>

<div style="margin-left:10px;">
<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>