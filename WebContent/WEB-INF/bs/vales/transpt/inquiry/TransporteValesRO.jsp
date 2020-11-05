<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transporte Vales</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%">
<table>
<tr>
<td class="xxbstdtextalign"><label for="folio">Folio:</label></td>
<td><input id="folio" name="folio" type="number" value="${vale.folio}" readonly/><label id="folioMsg"></label></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" name="fecha" type="date" value="${vale.fechayyyyMMdd}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectOperadorId">Operador:</label></td>
<td colspan="3"> 
<div id="selectOperadorDivId">
<input type="text" id="selectOperadorId" name="operador" size="80" value="${vale.operador}" readonly/>
</div>  
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td>
<td>
<input type="text" id="selectUnidadId" name="unidad" value="${vale.eco}" readonly/>
  </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectEmpresaId">Cliente:</label></td>
<td colspan="3">
<input type="text" id="selectEmpresaId" name="empresa"  size="80" value="${vale.cliente}" readonly/>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectRutaId">Ruta:</label></td>
<td colspan="3">
<div id="selectRutaDiv">
<input type="text" id="selectRutaId" name="ruta" size="80" value="${vale.ruta}" readonly>
</div>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kmInicial">Km&nbsp;Inicial:</label></td>
<td><input id="kmInicial" name="kmInicial" type="number" min="0" value="${vale.kminicial}" readonly/></td>
<td class="xxbstdtextalign"><label for="hrInicial">Hora&nbsp;Inicial:</label></td>
<td><input id="hrInicial" name="hrInicial" type="time" value="${vale.horainicial}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign" ><label for="kmFinal">Km Final:</label></td>
<td><input id="kmFinal" name="kmFinal" type="number" min="0" value="${vale.kmfinal}" readonly/></td>
<td class="xxbstdtextalign"><label for="hrFinal">Hora&nbsp;Final:</label></td>
<td><input id="hrFinal" name="hrFinal" type="time" value="${vale.horafinal}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="tipoDeViaje">Tipo&nbsp;De&nbsp;Viaje:</label></td>
<td><input id="tipoDeViaje" type="text" value="${vale.tipoviaje}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Tipo&nbsp;De&nbsp;Cobro:</label></td>
<td><input id="tipoDeCobro" type="text" value="${vale.tipocobro}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Tipo&nbsp;De&nbsp;Unidad:</label></td>
<td><input id="tipoDeUnidad" type="text" value="${vale.tipounidad}" readonly></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costoViaje">Costo Viaje:</label></td>
<td><input id="costoViaje" name="costoViaje" type="number" step="0.01" min="0" value="${vale.costo}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="observaciones">Observaciones:</label></td>
<td><textarea rows="2" cols="40" id="observaciones" name="observaciones" readonly>${vale.observaciones}</textarea></td>
</tr>
</table>
</fieldset>

 <form action="${pageContext.request.contextPath}/EntryTransporteValesCO?accionEntryVales=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary">Regresar</button>
 </form>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>