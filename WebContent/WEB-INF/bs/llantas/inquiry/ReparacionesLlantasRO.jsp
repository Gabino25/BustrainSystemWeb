<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reparaciones Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/MovimientosLlantas.css"/>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%;">
<table id="TablInputs">
<tr>
<td class="xxbstdtextalign"><label>Llanta:</label></td>
<td>
<input type="text" value="${movimientoLlanta.nombre}" readonly/>
</td>
<td class="xxbstdtextalign"><label>Unidad:</label></td>
<td>
<input type="text" value="${movimientoLlanta.unidad}" readonly/>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" value="${movimientoLlanta.fechaddMMyyyyV2}" readonly/></td>
<td class="xxbstdtextalign"><label>Kilometraje:</label></td>
<td><input type="number" id="kilometraje" name="kilometraje" value="${movimientoLlanta.kilometraje}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Operador:</label></td>
<td>
<input value="${movimientoLlanta.operador}" size=60 readonly/>
</td>
<td class="xxbstdtextalign"><label>Posicion:</label></td>
<td><input value="${movimientoLlanta.posicion}" readonly/>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Costo:</label></td>
<td><input type="number" id="costo" name="costo" value="${movimientoLlanta.costo}" readonly/></td>
</tr>
<tr>
<td>
<label>Observaciones:</label>
</td>
</tr>
<tr>
<td colspan="4"><textarea rows="2" cols="80" id="nota" name="nota" readonly>${movimientoLlanta.nota}</textarea></td>
</tr>
</table>
</fieldset>

<form action="${pageContext.request.contextPath}/ReparacionesLlantasCO?accionMovimientosLlantas=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary">Regresar</button>
 </form>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>