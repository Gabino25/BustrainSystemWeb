<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Analizar Fallas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/AnalizarFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/inquiry/FallasAnalizar.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 ANALIZAR-FALLAS
</div> 
<form id="AnalizarFallasForm" action="${pageContext.request.contextPath}/FallasAnalizarCO" method="post">
<input id="accionId" type="hidden" name="accionName"> 
</form>

<table>
<tbody>
<tr>
<td class="xxbstdvalign">
                 <!--PARTE IZQUIERDA -->
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr>
<td><div id="desdeDatePicker" ></div></td>
<td><div id="hastaDatePicker" ></div></td>
</tr>

<!--  <tr><td align=center><input type="radio" id="preventivoId" name="tipo"><label for="preventivoId">Preventivo</label></td> 
	<td align=center><input type="radio" id="corrrectivoId" name="tipo"><label for="corrrectivoId">Correctivo</label></td> 
	</tr>-->
	<!-- AQUI INICIA EL FUJO DEL COGIGO CON LO ELEMENTOS EN HTML LOS CUALES TIENEN UN ID EL CUAL SIRVE PARA OBTENER SU VALOR
	    DE AQUI CONTINUA EN FALLASANALIZAR.JS -->
<tr><td align=center><input type="radio" id="realizadoId" name="tipo" value="REALIZADO"><label for="realizadoId">Realizado</label></td> 
	<td  align=center><input type="radio" id="pendienteId" name="tipo" value="PENDIENTE"><label for="pendienteId">Pendiente</label></td> 
	</tr>
<tr>
<td class="xxbstdtextalign"><label for="selectUnidadId">Unidad:</label></td>
<td>
<select id="selectUnidadId" name="unidad" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
	</tr>


</table>
</fieldset>
<div id="ButtonsDiv" >
<button class="btn btn-primary" onclick="accionFallasAnalizar('Filtrar');">Filtrar</button>
<button  class="btn btn-primary" id="ExportarExcel" onclick="accionFallasAnalizar('ExportarExcel');">Exportar a Excel</button>
<button class="btn btn-primary" onclick="accionFallasAnalizar('Salir');">SALIR</button>
</div>
</td>
                     <!-- PARTE DERECHA -->
<td class="xxbstdvalign">
<div id="DivTablRO">
<table id="TablRO"  class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg"><tr>
<th>Numero</th>
<th>Unidad</th>
<th>Reparaci&oacute;n</th>
<th>Kilometraje</th>
<th>Fecha&nbsp;de&nbsp;Reparaci&oacute;n</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="liFallasTop1000" items="${listFallasTop1000}" varStatus="row">
<tr>
<td>${liFallasTop1000.numero}</td>
<td  class="xxbstdtextalignV3">${liFallasTop1000.eco}</td>
<td>${liFallasTop1000.descripcion}</td>
<td class="xxbstdtextalignV3">${liFallasTop1000.kilometrajenf}</td>
<td class="xxbstdtextalignV3">${liFallasTop1000.fechaddMMyyyyV2}</td>
</tr>
</c:forEach>

</tbody>
</table>
</div>
</td>
</tr>
</tbody>
</table>



<div id="footer">
    <jsp:include page="/WEB-INF/commons/footer.jsp"/>
 </div>
</body>
</html>