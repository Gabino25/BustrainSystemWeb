<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xxbs Lookups</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/commons/Xxbslookups.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/commons/Xxbslookups.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<table id="TablRO" class="table table-striped table-bordered table-condensed">
 <thead>
 <tr>
 <th>ID</th>
 <th>NOMBRE</th>
 <th>CODIGO</th>
 </tr>
 </thead>
 <tbody>
  <c:forEach  var="liXxbslookups" items="${listXxbslookups}" varStatus="row">
 <tr>
  <td>${liXxbslookups.id}</td>
 <td>${liXxbslookups.name}</td>
 <td>${liXxbslookups.code}</td>
 </tr>
 </c:forEach>
 </tbody>
</table>

  
  <div id="CreateDiv">
  <form id="formCreate" action="${pageContext.request.contextPath}/XxbslookupsCO" method="post" onsubmit="return validaCreateForm(this);" autocomplete="off" >
  <table>
   <tbody>
   <tr>
   <td class="xxbstdtextalign"><label for="CreateNombre">Nombre:</label></td>
   <td><input id="CreateNombre" name="Nombre" type="text" size="60" maxlength="200" value="<%=request.getAttribute("rAttrNameLookup")%>" readonly></td>
   </tr>
   <tr>
   <td class="xxbstdtextalign"><label for="CreateCodigo">Codigo:</label></td>
   <td><input id="CreateCodigo" name="Codigo" type="text" size="60" maxlength="200" required></td>
   </tr>
   </tbody>
  </table>
	<input id="submitCreateForm" type="submit">
	<input id="resetCreateForm" type="reset"/>
	<input id="accionCreateForm" type="hidden" name="accionXxbslookups"/>
  </form>
  </div>
  
 <div id="ReadOnlyDiv">
<form id="ReadOnlyForm" action="${pageContext.request.contextPath}/XxbslookupsCO" method="post" >
<input id="accionReadOnlyForm" type="hidden" name="accionXxbslookups"/>
<input id="nameROTrx" type="hidden" name="nameROTrx"/>
<input id="idROTrx" type="hidden" name="idTrx">
</form>
</div>

    
<div id="UpdateDiv">
  <form id="formUpdate" action="${pageContext.request.contextPath}/XxbslookupsCO" method="post" onsubmit="return validaUpdateForm(this);" autocomplete="off" >
  <table>
   <tbody>
   <tr>
   <td class="xxbstdtextalign"><label for="UpdateNombre">Nombre:</label></td>
   <td><input id="UpdateNombre" name="Nombre" type="text" size="60" maxlength="200" value="<%=request.getAttribute("rAttrNameLookup")%>" readonly></td>
   </tr>
   <tr>
   <td class="xxbstdtextalign"><label for="UpdateCodigo">Codigo:</label></td>
   <td><input id="UpdateCodigo" name="Codigo" type="text" size="60" maxlength="200" required></td>
   </tr>
   </tbody>
  </table>
	<input id="submitUpdateForm" type="submit">
	<input id="resetUpdateForm" type="reset"/>
	<input id="accionUpdateForm" type="hidden" name="accionXxbslookups"/>
	<input id="idTrx" type="hidden" name="idTrx">
  </form>
  <button  class="btn btn-primary" onclick="accionUpdateLookups('Guardar');">Guardar</button>
  <button  class="btn btn-primary" onclick="accionUpdateLookups('Cancelar');">Cancelar</button>
  </div>

<div id="BtnDiv">
<table id="buttonsTable">
<tr>
<td><button class="btn btn-primary" id="NuevaBtn"  onclick="accionLookups('Nueva');">Nueva</button></td>
<td><button class="btn btn-primary" id="GuardarBtn" onclick="accionLookups('Guardar');">Guardar</button></td>
<td><button class="btn btn-primary" id="BorrarBtn" onclick="accionLookups('Borrar');">Borrar</button></td>
<td><button class="btn btn-primary" id="ModificarBtn" onclick="accionLookups('Modificar');">Modificar</button></td>
<td><button class="btn btn-primary" id="CancelarBtn" onclick="accionLookups('Cancelar');">Cancelar</button></td>
<td><button class="btn btn-primary" id="SalirBtn" onclick="accionLookups('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>