<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cuentas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ap/entry/Cuentas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ap/entry/Cuentas.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Compras - Captura Cuentas
</div> 


<table id="TablRO" class="table table-striped table-bordered table-condensed" >
<thead class="xxbstheadbg">
<tr>
<th>ID</th>
<th>CUENTA</th>
<th>NOMBRE CUENTA</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
 <c:forEach  var="liCuentasOperacionAll" items="${listCuentasOperacionAll}" varStatus="row">
 <tr>
  <td>${liCuentasOperacionAll.id}</td>
 <td>${liCuentasOperacionAll.cuenta}</td>
 <td>${liCuentasOperacionAll.nombrecuenta}</td>
 </tr>
 </c:forEach>

</tbody>
</table>

<div id="CreateDiv">
<form id="CreateForm" action="${pageContext.request.contextPath}/CuentasCO" method="post" onsubmit="return validaCreateForm(this);" autocomplete="off">
<fieldset style="margin-left:10px;width: 98%;">
<table>
<tr>
<td><label>Cuenta:</label></td>
<td><input id="createCuenta" name="cuenta" size="40" required/></td>
</tr>
<tr>
<td><label>Nombre&nbsp;Cuenta:</label></td>
<td><input id="createNombreCuenta" name="nombreCuenta" size="60" required></td>
<td></td>
</tr>
</table>
</fieldset>
<input type="submit" id="submitCreateForm">
<input type="reset" id="resetCreateForm">
<input id="accionCreateForm" type="hidden" name="accionCuentas"/>
</form>
</div>

<div id="ReadOnlyDiv">
<form id="ReadOnlyForm" action="${pageContext.request.contextPath}/CuentasCO" method="post" >
<input id="accionReadOnlyForm" type="hidden" name="accionCuentas"/>
<input id="idROTrx" type="hidden" name="idTrx">
</form>
</div>

<div id="UpdateDiv">
<form id="UpdateForm" action="${pageContext.request.contextPath}/CuentasCO" method="post" onsubmit="return validaUpdateForm(this);" autocomplete="off">
<table>
<tr>
<td><label>Cuenta:</label></td>
<td><input id="updateCuenta" name="cuenta" size="40" required/></td>
</tr>
<tr>
<td><label>Nombre&nbsp;Cuenta:</label></td>
<td><input id="updateNombreCuenta" name="nombreCuenta" size="60" required></td>
<td></td>
</tr>
</table>
<input type="submit" id="submitUpdateForm">
<input type="reset" id="resetUpdateForm">
<input id="accionUpdateForm" type="hidden" name="accionCuentas"/>
<input id="idTrx" type="hidden" name="idTrx">
</form>
<button  class="btn btn-primary" onclick="accionUpdateCuentas('Guardar');">Guardar</button>
<button  class="btn btn-primary" onclick="accionUpdateCuentas('Cancelar');">Cancelar</button>
</div>

<div id="BtnDiv">
<table id="buttonsTable">
<tr>
<td><button class="btn btn-primary" id="NuevaBtn"  onclick="accionCuentas('Nueva');">Nueva</button></td>
<td><button class="btn btn-primary" id="GuardarBtn" onclick="accionCuentas('Guardar');">Guardar</button></td>
<td><button class="btn btn-primary" id="BorrarBtn" onclick="accionCuentas('Borrar');">Borrar</button></td>
<td><button class="btn btn-primary" id="ModificarBtn" onclick="accionCuentas('Modificar');">Modificar</button></td>
<td><button class="btn btn-primary" id="CancelarBtn" onclick="accionCuentas('Cancelar');">Cancelar</button></td>
<td><button class="btn btn-primary" id="SalirBtn" onclick="accionCuentas('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>