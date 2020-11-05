<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Uniformes</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Trabajadores.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/UniformesTrabajadores.js"></script>
<script type="text/javascript">
</script>
<jsp:include page="/WEB-INF/commons/header.jsp"/>
</head>
<body>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogos - Trabajadores - Uniformes
</div> 
<table>
<tr>
 <td><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3></td>
<td>
 <div>
 <form id="UniformeTrabajadorId" action="${pageContext.request.contextPath}/TrabajadoresCO" method="post" enctype="multipart/form-data" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr><th colspan="2" > <H3 align=center >Asigancion&nbsp;de&nbsp;Uniformes</H3></th></tr>
<tr>
<td class="xxbstdtextalign"><label for="fechaA">Fecha:</label></td>
<td><input id="fechaA" name="fechaA" type="date" required /> </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="trabajador">Trabajador:</label></td>
<td>

<select id="trabajador" name="trabajador" required >
<option value=""></option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
<!-- <input id="trabajador" name="trabajador" type="text" size="30" required/> -->
</td>
</tr>
<!-- ------------------------------------------------------------------- -->
<tr><td align="right" > <H4> Camisa </H4></td><td></td></tr>
 <tr>
 <td class="xxbstdtextalign"><label for="CCamisa">Cantidad:</label></td>
<td>
<select  id="CCamisa" name="CCamisa" >
<option value=""></option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>

</select></td>
</tr>
<tr><td align="right" ><H4>Playera</H4></td><td> </td></tr>
<tr>
<td class="xxbstdtextalign"><label for="CPlayera">Cantidad:</label></td>
<td>
<select  id="CPlayera" name="CPlayera" >
<option value=""></option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select></td>
</tr>
<tr><td align="right"  ><H4>Pantalon</H4></td><td> </td></tr>
<tr>
<td class="xxbstdtextalign"><label for="CPantalon">Cantidad:</label></td>
<td>
<select  id="CPantalon" name="CPantalon" >
<option value=""></option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select></td>
</tr>
<tr><td align="right" ><H4>Zapatos</H4></td><td> </td></tr>
<tr>
<td class="xxbstdtextalign"><label for="CZapato">Cantidad:</label></td>
<td>
<select  id="CZapato" name="CZapato" >
<option value=""></option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="NA">NA</option>
</select></td>
</tr>
<tr><th colspan="2" align=center > <H3></H3></th></tr>
</table> <br>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
</form>
 </div>
 </td><td><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3></td>
<td>
<table  id="TableRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<!-- <th>#EMPLEADO</th> -->
<th>NOMBRE</th>
<th>CAMISA</th>
<th>PLAYERA</th>
<th>PANTALON</th>
<th>ZAPATOS</th>
<th>FECHA</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
 <c:forEach var="selLisCatTrab" items="${listTrabajadores2}" varStatus="row">
<tr>
<td>${selLisCatTrab.trabajadorU}</td>
<td align =center>${selLisCatTrab.cCamisa}</td>
<td align =center>${selLisCatTrab.cPlayera}</td>
<td align =center>${selLisCatTrab.cPantalon}</td>
<td align =center>${selLisCatTrab.cZapato}</td>
<td>${selLisCatTrab.fechaA}</td>
</tr>
</c:forEach>

</tbody>
</table></td></tr></table>
 <table  id="buttonsTablev2" >
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="accionCatTrabaj('Nueva');">Nueva</button></td>
<td><button id="UGuardarBtn" class="btn btn-primary" onclick="accionCatTrabaj('UGuardar');">Guardar</button></td>
<td><button id="CancelarBtn" class="btn btn-primary" onclick="accionCatTrabaj('Cancelar');">Cancelar</button></td>
 <td><button id="excelBtn" class="btn btn-primary" onclick="accionCatTrabaj('Excel');">Excel</button></td>
<td>
<form action="${pageContext.request.contextPath}/TrabajadoresCO?accionName=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary">Regresar</button>
 </form>
<%-- <a href="${pageContext.request.contextPath}/MainCO?accion=CatalogoTrabajadores"><button class="btn btn-primary">Regresar</button></a> --%>
</td>
</tr>
</table>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>