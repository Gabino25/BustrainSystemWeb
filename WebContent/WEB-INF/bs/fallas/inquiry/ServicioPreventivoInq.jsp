<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servicio Preventivo</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/InquiryFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/upd/ServicioPreventivoUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-left:10px;width:98%">
<form id="formUpd" action="${pageContext.request.contextPath}/ServicioPreventivoCO" method="post" onsubmit=" return validaFormulario(this);" autocomplete="off">
<input type="hidden" id="accionFormId" name="accionFormName" />
<table>
<tr>
<td><label>Fecha:</label></td>
<td><input type="date" id="fecha" name="fecha"  value="${falla.fechayyyyMMdd}" required disabled/></td>
</tr>
<tr>
<td><label>Eco:</label></td>
<td><input id="ecoTrx" type="hidden" value="${falla.eco}">
  <select id="selectUnidadId" name="eco" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.folio}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
<td style="padding: 15px 10px"/>
<td><label>Fecha Realizacion:</label></td>
<td><input id="fechaRealizacion" name="fechaRealizacion" type="date" value="${falla.fecharepyyyyMMdd}" required></td>
</tr>
<tr>
<td><label>Kilometraje</label></td>
<td><input type="number" id="kilometraje" name="kiolometraje" value="${falla.kilometraje}" required ></td>
<td style="padding: 15px 10px"/>
<td><label>Tipo Servicio</label></td>
<td> <input type="hidden" id="tipoTrx" value="${falla.tipo}">
    <select id="TipoServicio" name="TipoServicio" required>
     <option value=""></option>
     <option value="MECANICO">MECANICO</option>
     <option value="ELECTRICO">ELECTRICO</option>
     <option value="HOJALATERIA Y PINTURA">HOJALATERIA Y PINTURA</option>
     <option value="CLIMAS">CLIMAS</option>
     <option value="EXTERNO">EXTERNO</option>
    </select>
</td>
</tr>
<tr>
<td colspan="2"><label>Descripcion del Servicio</label></td>
</tr>
<tr>
<td colspan="5"><textarea id="descServicio" name="descServicio" rows="4" cols="70" required>${falla.descripcion}</textarea></td>
</tr>
</table>
<input type="submit" id="submitButtonId" name="submitButtonId"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="folioTrx" name="folioTrx" value="${falla.numero}"/>
</form>
</fieldset>

<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>


<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>