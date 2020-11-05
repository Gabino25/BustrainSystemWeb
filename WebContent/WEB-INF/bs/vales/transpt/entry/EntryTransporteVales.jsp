<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Captura Vales de Transporte</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/transpt/EntryValesTransporte.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vales/transpt/EntryValesTransporte.css">


</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Vales - Captura
</div> 


<table id="entryInfoTbl">
<tr>
<td class="xxbstdvalign">
<form id="formEntryVales" name="formEntryVales" method="post" action="${pageContext.request.contextPath}/EntryTransporteValesCO" onsubmit="return validaFormulario(this);" autocomplete="off" >
<input type="hidden" name="accionEntryVales"  id="accion"/>
<input type="hidden" name="otherTransaction"  id="otherTransaction" value="${reqAttrOtherTransaction}"/>
<table class="xxbstblfieldset">
<tr>
<td class="xxbstdtextalign"><label for="folio">Folio:</label></td>
<td><input id="folio" name="folio" type="number" required/><label id="folioMsg"></label></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" name="fecha" type="date" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectOperadorId">Operador:</label></td>
<td colspan="3"> 
<div id="selectOperadorDivId">
<select id="selectOperadorId" name="operador">
  <option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</div>  
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td>
<td>

<select id="selectUnidadId" name="unidad">
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
<select id="selectEmpresaId" name="empresa" onchange="selectRuta();" >
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
<option value=""></option>
</select>
</div>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="kmInicial">Km&nbsp;Inicial:</label></td>
<td><input id="kmInicial" name="kmInicial" type="number" min="0" required/></td>
<td class="xxbstdtextalign"><label for="hrInicial">Hora&nbsp;Inicial:</label></td>
<td><input id="hrInicial" name="hrInicial" type="time" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign" style="margin-bottom:10px;padding-bottom:10px;border-bottom"><label for="kmFinal">Km Final:</label></td>
<td style="margin-bottom:10px;padding-bottom:10px;border-bottom"><input id="kmFinal" name="kmFinal" type="number" min="0" required/></td>
<td class="xxbstdtextalign"><label for="hrFinal">Hora&nbsp;Final:</label></td>
<td><input id="hrFinal" name="hrFinal" type="time" required/></td>
<td><label for="">Horas de Recorrido:</label></td>
<td><input /></td>
</tr>
<tr>
<td></td>
<td style="margin-top:10px; padding-top: 10px; border-top:  1px solid #abb2b9; "><input type="radio" id="sensilloId" value="SENSILLO" name="tipoViaje" checked><label for="sensilloId">Sencillo</label></td>
<td style="margin-top:10px; padding-top: 10px;border-top:  1px solid #abb2b9; "><input type="radio" id="redondoId" value="REDONDO" name="tipoViaje"><label for="redondoId">Redondo</label></td>
<td></td>
</tr>
<tr>
<td></td>
<td><input id="contadoId" type="radio" value="CONTADO" name="tipoCobro" checked><label for="contadoId">Contado</label></td>
<td><input id="creditoId" type="radio" value="CREDITO" name="tipoCobro"><label for="creditoId">Credito</label></td>
<td></td>
</tr>
<tr>
<td></td>
<td style="margin-bottom:10px;padding-bottom:10px;border-bottom:  1px solid #abb2b9; "><input id="autobusId" type="radio" value="AUTOBUS" name="tipoUnidad" checked><label for="autobusId">Autobus</label></td>
<td style="margin-bottom:10px;padding-bottom:10px;border-bottom:  1px solid #abb2b9; "><input id="taxiId" type="radio" value="TAXI" name="tipoUnidad"><label for="taxiId">Taxi</label></td>
<td></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costoViaje">Costo Viaje:</label></td>
<td><input id="costoViaje" name="costoViaje" type="number" step="0.01" min="0" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="observaciones">Observaciones:</label></td>
<td><textarea rows="2" cols="40" id="observaciones" name="observaciones"></textarea></td>
</tr>
</table>

<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="numValeTrx" name="numValeTrx"/>

</form> <!-- Final Formulario -->

</td> <!-- Final td1 tabla1 -->
<td>
<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>NUMVALE</th>
<th>FOLIO</th>
<th>FECHA</th>
<th>OBSERVACIONES</th>
<th>COSTO</th>
<th>RUTA</th>
<th>OPERADOR</th>
<th>ECO</th>
<th>CLIENTE</th>
<th>KMINICIAL</th>
<th>HORAINICIAL</th>
<th>KMFINAL</th>
<th>HORAFINAL</th>
<th>TIPOVIAJE</th>
<th>TIPOCOBRO</th>
<th>CENTROCOSTOS</th>
<th>FACTURA</th>
<th>USUARIO</th>
<th>FECHACAPTURA</th>
<th>TIPOUNIDAD</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
 <c:forEach  var="listaVales" items="${listVales}" varStatus="row">
<tr>
<td>${listaVales.numvale}</td>   
<td>${listaVales.folio}</td>     
<td>${listaVales.fechaddMMyyyyV2}</td>     
<td>${listaVales.observaciones}</td>
<td>${listaVales.costo}</td>     
<td>${listaVales.ruta}</td>      
<td>${listaVales.operador}</td>  
<td>${listaVales.eco}</td>       
<td>${listaVales.cliente}</td>   
<td>${listaVales.kminicial}</td> 
<td>${listaVales.horainicial}</td>
<td>${listaVales.kmfinal}</td>   
<td>${listaVales.horafinal}</td> 
<td>${listaVales.tipoviaje}</td> 
<td>${listaVales.tipocobro}</td> 
<td>${listaVales.centrocostos}</td>
<td>${listaVales.factura}</td>   
<td>${listaVales.usuario}</td>   
<td>${listaVales.fechacapturaddMMyyyV3}</td>
<td>${listaVales.tipounidad}</td>
</tr>
 </c:forEach>
</tbody>
</table> <!-- Finaliza tabla Read Only -->
</td>
</tr>
</table>

<div style=" margin-top: 1px; margin-bottom: 1px; margin-left: 150px;margin-right: 150px;">
<table style="margin: 0px auto;">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="acccionEntryVales('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="acccionEntryVales('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="acccionEntryVales('Borrar');">Borrar</button></td>
 <td><input type=pass name=contra2 id=contra2 ></td>
 <td><button id="Entrar2" class="btn btn-primary" onclick="acccionEntryVales('Entrar2');">Confirmar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="acccionEntryVales('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<!-- <td><button id="ModificarBtn" class="btn btn-primary" onclick="acccionEntryVales('Modificar');">Modificar</button></td> -->
<td><button id="Btn" class="btn btn-primary" onclick="acccionEntryVales('Btn');">Modificar</button></td>
 <td><input type=pass name=contra id=contra ></td>
 <td><button id="Entrar" class="btn btn-primary" onclick="acccionEntryVales('Entrar');">Entrar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="acccionEntryVales('Cancelar');">Cancelar</button></td>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="acccionEntryVales('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>