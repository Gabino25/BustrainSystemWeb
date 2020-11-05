<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/admin/entry/Usuarios.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/entry/Usuarios.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Administrador - Usuarios
</div> 


<table id="TablRO" class="table table-striped table-bordered table-condensed" >
<thead class="xxbstheadbg">
<tr>
<th>USUARIO</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="liUsuario" items="${listUsuario}" varStatus="row">
 <tr>
 <td>${liUsuario.nombre}</td>
 </tr>
 </c:forEach>
</tbody>
</table>

<div id="CreateDiv" class="xxbstblfieldset">
<form id="CreateForm" action="${pageContext.request.contextPath}/UsuariosCO" method="post" onsubmit="return validaCreateForm(this);" autocomplete="off" >
<table>
<tr>
<td><label>Usuario</label></td>
<td><input id="createUsuario" type="text" name="usuario" maxlength="50" required></td>
</tr>
<tr>
<td><label>Contrase&ntilde;a</label></td>
<td><input id="createPassword" type="password" name="contrasenia" maxlength="50" required></td>
</tr>
</table>
<input type="submit" id="submitCreateForm">
<input id="accionCreateForm" type="hidden" name="accionUsuarios"/>
</form>
</div>

<div id="UpdateDiv">
<div class="xxbstblfieldset">
<form id="UpdateForm" action="${pageContext.request.contextPath}/UsuariosCO" method="post" onsubmit="return validaUpdateForm(this);" autocomplete="off" >
<table>
<tr>
<td><label>Usuario</label></td>
<td><input id="updateUsuario" type="text" name="usuario" maxlength="50" required></td>
</tr>
<tr>
<td><label>Contrase&ntilde;a</label></td>
<td><input id="updatePassword" type="password" name="contrasenia" maxlength="50" required></td>
</tr>
</table>
<input id="nombreUsuarioUpdateTrx"  type="hidden" name="nombreUsuarioTrx"/>
<input type="submit" id="submitUpdateForm" value="Actualizar"/>
<input id="accionUpdateForm" type="hidden" name="accionUsuarios"/>
</form>
</div>
<button  class="btn btn-primary" onclick="accionUpdateUsuarios('Guardar');">Guardar</button>
<button  class="btn btn-primary" onclick="accionUpdateUsuarios('Cancelar');">Cancelar</button>
</div>


<div id="ReadOnlyDiv" class="xxbstblfieldset">
<form id="ReadOnlyForm" action="${pageContext.request.contextPath}/UsuariosCO" method="post" >
<input id="nombreUsuarioTrx"  type="hidden" name="nombreUsuarioTrx"/>
<input id="accionReadOnlyForm" type="hidden" name="accionUsuarios"/>
</form>
</div>


<div id="BtnDiv">
<table id="buttonsTable">
<tr>
<td><button class="btn btn-primary" id="NuevaBtn"  onclick="accionUsuarios('Nueva');">Nueva</button></td>
<td><button class="btn btn-primary" id="GuardarBtn" onclick="accionUsuarios('Guardar');">Guardar</button></td>
<td><button class="btn btn-primary" id="BorrarBtn" onclick="accionUsuarios('Borrar');">Borrar</button></td>
<td><button class="btn btn-primary" id="ModificarBtn" onclick="accionUsuarios('Modificar');">Modificar</button></td>
<td><button class="btn btn-primary" id="CancelarBtn" onclick="accionUsuarios('Cancelar');">Cancelar</button></td>
<td><button class="btn btn-primary" id="SalirBtn" onclick="accionUsuarios('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>