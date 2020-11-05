<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rendimientos Calculados</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vales/combust/RendimientosCalculados.js"></script>
</head>

<style>
div.dataTables_wrapper {
       width:98%;
       margin-left:10px;
    }
</style>

<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Calcula - Rendimientos
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>UNIDAD</th>
<th>KILOMENTRAJE</th>
<th>KMANTERIOR</th>
<th>KMSRECORRIDOS</th>
<th>LITROS</th>
<th>RENDIMIENTO</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="liDiesel" items="${listDiesel}" varStatus="row">
<tr>
<td>${liDiesel.unidad}</td>
<td>${liDiesel.kilometraje}</td>
<td>${liDiesel.kmanterior}</td>
<td>${liDiesel.kmsrecorridos}</td>
<td>${liDiesel.enUSV2litros}</td>
<td>${liDiesel.rendimiento}</td>
</tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>