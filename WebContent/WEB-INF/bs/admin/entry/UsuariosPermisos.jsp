<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios Permisos</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/admin/entry/UsuariosPermisos.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/entry/UsuariosPermisos.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Administrador - Usuarios Permisos
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>ID</th>
<th>Usuario</th>
<th>Pantalla</th>
<th>Permiso</th>
<th>Nivel</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
 
 <c:forEach  var="liPermisosAll" items="${listPermisosAll}" varStatus="row">
 <tr>
  <td>${liPermisosAll.id}</td>
 <td>${liPermisosAll.usuario}</td>
 <td>${liPermisosAll.pantalla}</td>
 <td>${liPermisosAll.permiso}</td>
 <td>${liPermisosAll.niveldesc}</td>
 </tr>
 </c:forEach>
 
</tbody>
</table>

<div id="CreateDiv"  class="xxbstblfieldset">
<form id="formCreate" action="${pageContext.request.contextPath}/UsuariosPermisosCO" method="post" onsubmit="return validaCreateForm(this);" autocomplete="off" >
<table>
<tr>
<td class="xxbstdtextalign"><label for="createUsuario">Usuario:</label></td>
<td>
<select id="createUsuario" name="usuario"  required>
<option value=""></option>
<c:forEach  var="liUsuario" items="${listUsuario}" varStatus="row">
 <option value="${liUsuario.nombre}">${liUsuario.nombre}</option>
 </c:forEach>
</select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="createPantalla">Pantalla:</label></td>
<td><select id="createPantalla" name="pantalla" required>
    <option value=""></option>
    <c:forEach  var="liPantallas" items="${listPantallas}" varStatus="row">
     <option value="${liPantallas.nombre}">${liPantallas.nombre}</option>
    </c:forEach>
    </select></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Permiso:</label></td>
<td><input id="permisoSi" type="radio" name="permiso" value="SI" checked><label for="permisoSi">SI</label>&nbsp;&nbsp;&nbsp;
    <input id="permisoNo" type="radio" name="permiso" value="NO"><label for="permisoNo">NO</label>
   </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="createNivel">Nivel:</label></td>
<td><select id="createNivel" name="nivel" required>
    <option></option>
    <option value="0">CREAR MODIFICAR LECTURA</option>
    <option value="1">LECTURA</option>
    </select>
</td>
</tr>
</table>
<input id="submitCreateForm" type="submit">
<input id="resetCreateForm" type="reset"/>
<input id="accionCreateForm" type="hidden" name="accionUsuariosPermisos"/>
</form>
</div>

<div id="ReadOnlyDiv"  class="xxbstblfieldset">
<form id="ReadOnlyForm" action="${pageContext.request.contextPath}/UsuariosPermisosCO" method="post" >
<input id="accionReadOnlyForm" type="hidden" name="accionUsuariosPermisos"/>
<input id="idROTrx" type="hidden" name="idTrx">
</form>
</div>

<div id="UpdateDiv">
<div  class="xxbstblfieldset">
<form id="formUpdate" action="${pageContext.request.contextPath}/UsuariosPermisosCO" method="post" onsubmit="return validaUpdateForm(this);" autocomplete="off" >
<table>
<tr>
<td class="xxbstdtextalign"><label for="updateUsuario">Usuario:</label></td>
<td>
<input id="updateUsuario" name="usuario"  required readonly/>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="updatePantalla">Pantalla:</label></td>
<td>
    <select id="updatePantalla" name="pantalla" required>
    <option value=""></option>
    <c:forEach  var="liPantallas" items="${listPantallas}" varStatus="row">
     <option value="${liPantallas.nombre}">${liPantallas.nombre}</option>
    </c:forEach>
    </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Permiso:</label></td>
<td><input id="updatePermisoSi" type="radio" name="permiso" value="SI"><label for="updatePermisoSi">SI</label>&nbsp;&nbsp;&nbsp;
    <input id="updatePermisoNo" type="radio" name="permiso" value="NO"><label for="updatePermisoNo">NO</label>
   </td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="updateNivel">Nivel:</label></td>
<td><select id="updateNivel" name="nivel" required>
    <option></option>
    <option value="0">CREAR MODIFICAR LECTURA</option>
    <option value="1">LECTURA</option>
    </select>
</td>
</tr>
</table>
<input id="submitUpdateForm" type="submit">
<input id="resetUpdateForm" type="reset"/>
<input id="accionUpdateForm" type="hidden" name="accionUsuariosPermisos"/>
<input id="idTrx" type="hidden" name="idTrx">
</form>
</div>
<button  class="btn btn-primary" onclick="accionUpdateUsuariosPermisos('Guardar');">Guardar</button>
<button  class="btn btn-primary" onclick="accionUpdateUsuariosPermisos('Cancelar');">Cancelar</button>
</div>

<div id="BtnDiv">
<table id="buttonsTable">
<tr>
<td><button class="btn btn-primary" id="NuevaBtn"  onclick="accionUsuariosPermisos('Nueva');">Nueva</button></td>
<td><button class="btn btn-primary" id="GuardarBtn" onclick="accionUsuariosPermisos('Guardar');">Guardar</button></td>
<td><button class="btn btn-primary" id="BorrarBtn" onclick="accionUsuariosPermisos('Borrar');">Borrar</button></td>
<td><button class="btn btn-primary" id="ModificarBtn" onclick="accionUsuariosPermisos('Modificar');">Modificar</button></td>
<td><button class="btn btn-primary" id="CancelarBtn" onclick="accionUsuariosPermisos('Cancelar');">Cancelar</button></td>
<td><button class="btn btn-primary" id="SalirBtn" onclick="accionUsuariosPermisos('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>