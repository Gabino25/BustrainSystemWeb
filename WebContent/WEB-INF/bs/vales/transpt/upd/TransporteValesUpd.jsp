<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vales Tranporte</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/transpt/upd/ValesTransporteUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%">
<form id="formUpd" name="formEntryVales" method="post" action="${pageContext.request.contextPath}/EntryTransporteValesCO" onsubmit="return validaFormulario(this);" autocomplete="off" >
<input type="hidden" name="accionEntryVales"  id="accion"/>
<input type="hidden" name="otherTransaction"  id="otherTransaction" value="${reqAttrOtherTransaction}"/>
<table>
<tr>
<td class="xxbstdtextalign"><label for="folio">Folio:</label></td>
<td><input id="folio" name="folio" type="number" value="${vale.folio}" readonly/><label id="folioMsg"></label></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" name="fecha" type="date" value="${vale.fechayyyyMMdd}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectOperadorId">Operador:</label></td>
<td colspan="3"> 
<div id="selectOperadorDivId">
<input id="selectOperadorIdTrx" type="hidden" value="${vale.operador}"/>
<select id="selectOperadorId" name="operador" required>
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</div>  
</td>
</tr>
<tr><td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td><td>
<input id="selectUnidadIdTrx" type="hidden" value="${vale.eco}"/>
<select id="selectUnidadId" name="unidad" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
  </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectEmpresaId">Cliente:</label></td>
<td colspan="3">
<input id="selectEmpresaIdTrx" type="hidden" value="${vale.cliente}"/>
<select id="selectEmpresaId" name="empresa" onchange="selectRuta();" required>
<option value=""></option>
<c:forEach var="selListCatClients" items="${selectListCatClientes}" varStatus="row">
  <option value="${selListCatClients.empresa}">${selListCatClients.empresa}</option>
  </c:forEach>  
</select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectRutaId">Ruta:</label></td>
<td colspan="3">
<div id="selectRutaDiv">
<select id="selectRutaId" name="ruta" onchange="selectCosto();" required>
<option value="${vale.ruta}">${vale.ruta}</option>
</select>
</div>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kmInicial">Km&nbsp;Inicial:</label></td>
<td><input id="kmInicial" name="kmInicial" type="number" min="0" value="${vale.kminicial}" required/></td>
<td class="xxbstdtextalign"><label for="hrInicial">Hora&nbsp;Inicial:</label></td>
<td><input id="hrInicial" name="hrInicial" type="time" value="${vale.horainicial}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign" style="margin-bottom:10px;padding-bottom:10px;border-bottom"><label for="kmFinal">Km Final:</label></td>
<td style="margin-bottom:10px;padding-bottom:10px;border-bottom"><input id="kmFinal" name="kmFinal" type="number" min="0" value="${vale.kmfinal}" required/></td>
<td class="xxbstdtextalign"><label for="hrFinal">Hora&nbsp;Final:</label></td>
<td><input id="hrFinal" name="hrFinal" type="time" value="${vale.horafinal}" required/></td>
</tr>
<tr>
<td><input id="tipoDeViajeTrx" type="hidden" value="${vale.tipoviaje}"></td>
<td style="margin-top:10px; padding-top: 10px; border-top:  1px solid #abb2b9; "><input type="radio" id="sensilloId" value="SENSILLO" name="tipoViaje"><label for="sensilloId">Sencillo</label></td>
<td style="margin-top:10px; padding-top: 10px;border-top:  1px solid #abb2b9; "><input type="radio" id="redondoId" value="REDONDO" name="tipoViaje"><label for="redondoId">Redondo</label></td>
<td></td>
</tr>
<tr>
<td><input id="tipoDeCobroTrx" type="hidden" value="${vale.tipocobro}"></td>
<td><input id="contadoId" type="radio" value="CONTADO" name="tipoCobro" checked><label for="contadoId">Contado</label></td>
<td><input id="creditoId" type="radio" value="CREDITO" name="tipoCobro"><label for="creditoId">Credito</label></td>
<td></td>
</tr>
<tr>
<td><input id="tipoDeUnidadTrx" type="hidden" value="${vale.tipounidad}"></td>
<td style="margin-bottom:10px;padding-bottom:10px;border-bottom:  1px solid #abb2b9; "><input id="autobusId" type="radio" value="AUTOBUS" name="tipoUnidad" checked><label for="autobusId">Autobus</label></td>
<td style="margin-bottom:10px;padding-bottom:10px;border-bottom:  1px solid #abb2b9; "><input id="taxiId" type="radio" value="TAXI" name="tipoUnidad"><label for="taxiId">Taxi</label></td>
<td></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costoViaje">Costo Viaje:</label></td>
<td><input id="costoViaje" name="costoViaje" type="number" step="0.01" min="0" value="${vale.costo}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="observaciones">Observaciones:</label></td>
<td><textarea rows="2" cols="40" id="observaciones" name="observaciones">${vale.observaciones}</textarea></td>
</tr>
</table>

<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="numValeTrx" name="numValeTrx" value="${vale.numvale}"/>

</form> <!-- Final Formulario -->

</fieldset>

<div style="margin-left:10px;width:98%">
<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn"  class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>