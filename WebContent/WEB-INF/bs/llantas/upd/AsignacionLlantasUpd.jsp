<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Asignacion Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/MovimientosLlantas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/upd/AsignacionLlantasUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%;">
<form id="formUpd" action="${pageContext.request.contextPath}/AsignacionLlantasCO" method="post">
<table id="TablInputs">
<tr>
<td class="xxbstdtextalign"><label for="selectLlantaId">Llanta:</label></td>
<td>
<input id="selectLlantaIdTrx" type="hidden" value="${movimientoLlanta.nombre}">
<select id="selectLlantaId" name="llanta">
<option value=""></option>
<c:forEach var="selLisLlantas" items="${selectListLlantas}" varStatus="row">
<option value="${selLisLlantas.nombre}">${selLisLlantas.nombre}</option>
</c:forEach>  
</select>
</td>
<td class="xxbstdtextalign"><label for="selectOperadorId">Operador:</label></td>
<td>
<input id="selectOperadorIdTrx" type="hidden" value="${movimientoLlanta.operador}">
<select id="selectOperadorId" name="operador" style="width: 400px" >
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" value="${movimientoLlanta.fechayyyyMMdd}" required/></td>
<td class="xxbstdtextalign"><label for="selectPosicionId">Posicion:</label></td>
<td>
<input id="selectPosicionIdTrx" type="hidden" value="${movimientoLlanta.posicion}"/>
<select id="selectPosicionId" name="posicion" required>
<option value=""></option>
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
<td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td>
<td>
<input id="selectUnidadIdTrx" type="hidden" value="${movimientoLlanta.unidad}">
<select id="selectUnidadId" name="unidad" >
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
</select>	
</td>
<td class="xxbstdtextalign"><label for="profundidad">Profundidad:</label></td>
<td><input type="number" id="profundidad" name="profundidad" value="${movimientoLlanta.prof}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kilometraje">Kilometraje:</label></td>
<td><input type="number" id="kilometraje" name="kilometraje" value="${movimientoLlanta.kilometraje}" required/></td>
<td class="xxbstdtextalign"><label for="presionActual">Presion&nbsp;Actual:</label></td>
<td><input type="number" id="presionActual" name="presionActual" value="${movimientoLlanta.presion}" required/></td>
</tr>
<tr>
<td>
<label for="nota">Observaciones:</label>
</td>
</tr>
<tr>
<td colspan="4"><textarea rows="2" cols="80" id="nota" name="nota">${movimientoLlanta.observaciones}</textarea></td>
</tr>
</table>
<input type="hidden" id="accionMovimientosLlantas" name="accionMovimientosLlantas"/>
<input type="hidden" id="folio" name="folio" value="${movimientoLlanta.folio}">
<input id="submitFormId" type="submit" id="submitForm">
<input id="resetFormId" type="reset" id="resetForm">
</form>
</fieldset>

<div style="margin-left:10px">
<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
</div>
 
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>