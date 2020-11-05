<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Actualizar Llantas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/AdministrarLlantas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/llantas/upd/AdministrarLlantasUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<fieldset style="margin-left:10px">
<form id="formUpd" action="${pageContext.request.contextPath}/AdministrarLlantasCO" method="post" autocomplete="off">
 <table>
 <tbody>
 <tr>
<td class="xxbstdtextalign"><label for="codigo">Codigo:</label></td>
<td><input type="text" id="codigo" name="codigo" value="${llanta.nombre}" readonly/><label id="msgCodigo"></label></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="medida">Medida:</label></td>
<td><input type="text" id="medida" name="medida" value="${llanta.medida}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="marca">Marca:</label></td>
<td><input type="text" id="marca" name="marca" value="${llanta.marca}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="modelo">Modelo:</label></td>
<td><input type="text" id="modelo" name="modelo" value="${llanta.modelo}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="profundidad">Profundidad&nbsp;(mm):</label></td>
<td><input type="number" id="profundidad" name="profundidad" value="${llanta.profundidad_inicial}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="tipo">Estado:</label></td>
<td><input type="hidden" id="tipoTrx" value="${llanta.tipo}">
    <select id="tipo" name="tipo" required>
    <option value=""></option>
    <option value="NUEVA">NUEVA</option>
    <option value="USADA">USADA</option>
    <option value="RENOVADA">RENOVADA</option>
    </select>
</td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Costo:</label></td>
<td><input type="number" id="costo" name="costo" step="0.01" min="0" value="${llanta.costo}" required/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label>Fecha&nbsp;Alta &nbsp;:</label></td>
<td><input type="date" id="fechaAlta" name="fechaAlta" value="${llanta.fecha_altayyyyMMdd}" required/></td>
</tr>
 
 </tbody>
 </table>
<input type="hidden" id="accionAdministrarLlantas" name="accionAdministrarLlantas"/>
</form>
</fieldset>


<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
 
<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>