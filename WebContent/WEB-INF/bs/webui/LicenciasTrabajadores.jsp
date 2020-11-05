<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Licencias Trabajadores</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/webui/styleLicenciasTrabajadores.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/webui/LicenciasTrabajadores.js"></script>


</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Archivo - Licencias Trabajadores
</div> 
 <div id="DivTablRO">
 <table id="TablRO" class="table table-striped table-bordered table-condensed" >
 <thead class="xxbstheadbg">
 <tr>
 <th>NUMERO</th>
 <th>NOMBRE</th>
 <th>PUESTO</th>
 <th>VENCIMIENTO&nbsp;LICENCIA</th>
 </tr> 
 </thead>
 <tbody class="xxbstbodybg">
  <c:forEach  var="licenTrabajadores" items="${listLicenciasTrabajadores}" varStatus="row">
 <tr>
 <td>${licenTrabajadores.numero}</td>
 <td>${licenTrabajadores.nombre}</td>
 <td>${licenTrabajadores.puesto}</td>
 <td>${licenTrabajadores.licenciaddMMyyyyV2}</td>
 </tr>
 </c:forEach>
 </tbody>

 </table>
</div>

<div id="ButtonsDiv" >
<form id="licenTrabajadoresForm" action="${pageContext.request.contextPath}/LicenciasTrabajadoresCO" method="post">
<input id="accionId" type="hidden" name="accionName"> 
</form>
<!-- <button class="btn btn-primary" onclick="accionLicTrabaj('Todos');">TODOS</button> -->
<a href="${pageContext.request.contextPath}/MainCO?accion=LicenciaTrabajadores"><button class="btn btn-primary">TODOS</button></a>
<button class="btn btn-primary" onclick="accionLicTrabaj('ProxAvencer');">PROXIMOS A VENCER</button>
<button class="btn btn-primary" onclick="accionLicTrabaj('LicenVencida');">LICENCIA VENCIDA</button>
<button class="btn btn-primary" onclick="accionLicTrabaj('Salir');">SALIR</button>
</div>

 <div id="footer">
    <jsp:include page="/WEB-INF/commons/footer.jsp"/>
 </div>
</body>
</html>