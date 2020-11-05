<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOLETAS</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/transpt/InquiryListadoBoletas.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vales/transpt/InquiryListadoBoletas.css">

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Vales - Listado
</div> 

<form id="formListadoBoletas" name="formListadoBoletas" method="post" action="${pageContext.request.contextPath}/ListadoBoletasCO">
<input type="hidden" name="accionListadoBoletas"  id="accion"/>
</form>

<div id="DivTablRO">
<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>FOLIO</th>
<th>FECHA</th>
<th>UNIDAD</th>
<th>RUTA</th>
<th>OPERADOR</th>
<th>COSTO</th>
<th>HR&nbsp;INICIO</th>
<th>KM&nbsp;INICIAL</th>
<th>HR&nbsp;FINAL</th>
<th>KM&nbsp;FINAL</th>
<th>EMPRESA</th>
<th>OBSERVACIONES</th>
<th>TIPO&nbsp;DE&nbsp;VIAJE</th>
<th>CONDICIONES</th>
<th>CENTRO&nbsp;DE&nbsp;COSTOS</th>
<th>USUARIO</th>
<th>FECHA&nbsp;CAPTURA</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="listaVales" items="${listVales}" varStatus="row">
<tr>
<td>${listaVales.folio}</td>     
<td>${listaVales.fechaddMMyyyyV2}</td>    
<td>${listaVales.eco}</td>   
<td>${listaVales.ruta}</td>   
<td>${listaVales.operador}</td>   
<td class="xxbstdtextalignV2">${listaVales.costo}</td>             
<td>${listaVales.horainicial}</td>
<td>${listaVales.kminicial}</td> 
<td>${listaVales.horafinal}</td> 
<td>${listaVales.kmfinal}</td>   
<td>${listaVales.cliente}</td>  
<td>${listaVales.observaciones}</td>
<td>${listaVales.tipoviaje}</td> 
<td>${listaVales.tipocobro}</td> 
<td>${listaVales.centrocostos}</td>   
<td>${listaVales.usuario}</td>   
<td>${listaVales.fechacapturaddMMyyyV3}</td>
</tr>
 </c:forEach>
</tbody>
</table>
</div>
<fieldset style="margin-left:10px;width: 98%;">

<!-- &nbsp;<label class="xxbstdtextalign" for="fechaDesde">Desde :</label><input type="text" id="fechaDesde" name="fechaDesde"/> -->
<!-- &nbsp;<label class="xxbstdtextalign" for="fechaHasta">Hasta: </label><input type="text" id="fechaHasta" name="fechaHasta"/> -->
<!-- &nbsp;<label class="xxbstdtextalign">Subtotal: </label><input id="subtotal" type="text" readonly> -->
<!-- ----------------------- -->
<table>
<tbody>
<tr>
<td class="xxbstdtextalign"><label  for="fechaDesde">Desde:</label></td>
<td><input type="text" id="fechaDesde" name="fechaDesde" required/></td>
<td class="xxbstdtextalign"><label  for="fechaHasta">Hasta:</label></td>
<td><input type="text" id="fechaHasta" name="fechaHasta" required/></td>
<td class="xxbstdtextalign"><label  for="subtotal">Subtotal:</label></td>
<td><input id="subtotal" type="text" readonly style="text-align:right"></td>
</tr>
</tbody>
</table>
<!---------------------------------------------------------------------------->
<table>
<tbody>
<tr><td class="xxbstdtextalign"><label  for="empresa"> Empresa :</label></td> <td><select id="empresa">
<option></option>
<c:forEach  var="CatClientes" items="${selectListCatClientes}" varStatus="row">
<option value="${CatClientes.empresa}">${CatClientes.empresa}</option>
</c:forEach>
</select></td></tr>
<tr><td class="xxbstdtextalign"> <label  for="ruta"> Ruta :</label></td> <td><select id="ruta">
<option></option>
<c:forEach  var="ListRutas" items="${selectListRutas}" varStatus="row">
<option value="${ListRutas.ruta}">${ListRutas.ruta}</option>
</c:forEach>
</select></td></tr>
<tr><td class="xxbstdtextalign"> <label  for="operador"> Operador :</label></td> <td><select id="operador">
<option></option>
<c:forEach  var="CatTrabajadores" items="${selectListCatTrabajadores}" varStatus="row">
<option value="${CatTrabajadores.nombre}">${CatTrabajadores.nombre}</option>
</c:forEach>
</select></td></tr>
<tr><td  class="xxbstdtextalign"> <label  for="unidad"> Unidad :</label></td> <td><select id="unidad">
<option></option>
<c:forEach  var="ListAutobus" items="${selectListAutobus}" varStatus="row">
<option value="${ListAutobus.eco}">${ListAutobus.eco}</option>
</c:forEach>
</select></td></tr>
</tbody>
</table></fieldset>
<!-- ----------------------------------------------------------------------- -->
<table>
<tr>
<td><button class="btn btn-primary" onclick="filtrarPorfechas();">Filtrar por Fechas</button></td>
<td><button class="btn btn-primary" onclick="exportarExcel();">Exportar</button></td>
<td><button class="btn btn-primary" onclick="accionListadoBoletas('Salir');">Salir</button></td>
</tr>
</table>


<script>
 $.datepicker.regional['es'] = {
 		closeText: "Cerrar",
 		prevText: "&#x3C;Ant",
 		nextText: "Sig&#x3E;",
 		currentText: "Hoy",
 		monthNames: [ "enero","febrero","marzo","abril","mayo","junio",
 		"julio","agosto","septiembre","octubre","noviembre","diciembre" ],
 		monthNamesShort: [ "ene","feb","mar","abr","may","jun",
 		"jul","ago","sep","oct","nov","dic" ],
 		dayNames: [ "domingo","lunes","martes","miércoles","jueves","viernes","sábado" ],
 		dayNamesShort: [ "dom","lun","mar","mié","jue","vie","sáb" ],
 		dayNamesMin: [ "D","L","M","X","J","V","S" ],
 		weekHeader: "Sm",
 		dateFormat: "dd/mm/yy",
 		firstDay: 1,
 		isRTL: false,
 		showMonthAfterYear: false,
 	    yearSuffix: ""
 	    };
 $.datepicker.setDefaults($.datepicker.regional['es']);
 $("#fechaDesde" ).datepicker();
 $("#fechaHasta" ).datepicker();
</script>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>