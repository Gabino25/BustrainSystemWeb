<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte de Fallas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/InquiryFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/upd/ReporteDeFallasUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<br>
<fieldset style="margin-left:10px;width:98%">
<form id="formUpd" action="${pageContext.request.contextPath}/ReporteDeFallasCO" method="post" onsubmit=" return validaFormulario(this);" autocomplete="off">
<input type="hidden" id="accionFormId" name="accionFormName" />
<table>
<tr>
<td><label>Eco:</label></td>
<td><select id="selectUnidadId" name="eco" required>
  <option value="${falla.eco}">${falla.eco}</option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
</td>
<td style="padding: 5px 10px"></td>
</tr>
<tr>
<td><label>Reporta:</label></td>
<td><select id="selectListCatTrab" name="reporta" required>
  <option value="${falla.reporta}">${falla.reporta}</option>
  <c:forEach var="selLisCatTrab" items="${selectListCatTrabajadores}" varStatus="row">
  <option value="${selLisCatTrab.nombre}">${selLisCatTrab.nombre}</option>
  </c:forEach>  
  </select>
</td>
<td style="padding: 5px 10px"></td>
<td><label>Fecha:</label></td>
<td><input id="fecha" name="fecha" type="text" value="${falla.fechaddMMyyyyV2}" readonly required /></td>
</tr>
<tr>
<td><label>Kilometraje:</label></td>
<td><input id="kilometraje" name="kilometraje" value="${falla.kilometraje}" required></td>
<td style="padding: 5px 10px"></td>
<td><label>Hora:</label></td>
<td><input id="hora" type="text" name="hora" value="${falla.horav2}" readonly required /></td>
</tr>
<tr>
<td rowspan="3" colspan="5">
<label>Descripcion de la falla:</label><br>
<textarea id="descFalla" name="descFalla" cols="60"  required>${falla.descripcion}</textarea>
</td>
</tr>
</table>

<input type="submit" id="submitButtonId" name="submitButtonId"/>
<input type="reset" id="resetFormId" name="resetFormId"/>
<input type="hidden" id="folioTrx" name="folioTrx" value="${falla.numero}"/>
</form>
</fieldset>


<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>