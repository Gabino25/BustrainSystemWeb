<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Revision Profundidad Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/MovimientosLlantas.css"/>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%">

<table id="TablInputs">
<tr>
<td class="xxbstdtextalign"><label for="nombre">Llanta:</label></td>
<td>
<input id="nombre" type="text" value="${movimientoLlanta.nombre}" readonly/>
</td>
<td class="xxbstdtextalign"><label for="unidad">Unidad:</label></td>
<td>
<input id="unidad" type="text" value="${movimientoLlanta.unidad}" readonly/>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha" value="${movimientoLlanta.fechaddMMyyyyV2}" readonly/></td>
<td class="xxbstdtextalign"><label for="kilometraje">Kilometraje:</label></td>
<td><input type="number" id="kilometraje" name="kilometraje" value="${movimientoLlanta.kilometraje}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="operador">Operador:</label></td>
<td>
<input id="operador" value="${movimientoLlanta.operador}" size=60 readonly/>
</td>
<td class="xxbstdtextalign"><label for="posicion">Posicion:</label></td>
<td><input id="posicion" value="${movimientoLlanta.posicion}" readonly/>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="profundidad">Profundidad:</label></td>
<td><input type="number" id="profundidad" name="profundidad" value="${movimientoLlanta.prof}" readonly/></td>
</tr>
<tr>
<td>
<label for="observaciones">Observaciones:</label>
</td>
</tr>
<tr>
<td colspan="4"><textarea rows="2" cols="80" id="observaciones" name="observaciones" readonly>${movimientoLlanta.observaciones}</textarea></td>
</tr>
</table>

</fieldset>

<form action="${pageContext.request.contextPath}/RevisionProfundidadLlantasCO?accionMovimientosLlantas=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary" >Regresar</button>
 </form>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>