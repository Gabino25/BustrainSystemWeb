<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servicio Correctivo Reporte</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/fallas/InquiryFallas.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fallas/InquiryFallasReporte.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<form id="formServCorrReporte" name="formServCorrReporte" action="${pageContext.request.contextPath}/ServicioCorrectivoCO" method="post">
<input type="hidden" id="accionFormId" name="accionFormName" />
<table>
<tr>
<td class="xxbstdtextalign"><label for="folioROV2">Folio:</label></td>
<td><input  class="inputReadOnlyV2" id="folioROV2" name="folioROV2" type="text" value="${fallaByNumero.numero}" disabled/><td>
<td class="xxbstdtextalign"><label for="ecoROV2">Unidad:</label></td>
<td><input  class="inputReadOnlyV2" id="ecoROV2" name="ecoROV2" type="text" value="${fallaByNumero.eco}" disabled/></td>
<td class="xxbstdtextalign"><label for="fechaROV2">Fecha:</label></td>
<td><input  class="inputReadOnlyV2" id="fechaROV2" name="fechaROV2" type="text" value="${fallaByNumero.fechaddMMyyyyV2}" disabled/></td>
</tr>
<tr>
<td colspan="7">
<textarea rows="3" cols="80"></textarea>
</td>
</tr>
</table>
<input  type="submit" id="submitButtonId" name="submitButtonId"/>
</form>


<table>
<tr>
<td><button class="btn btn-primary" onclick="accionServicioCorrectivoReporte('Guardar');">Guardar</button></td>
<td><button class="btn btn-primary" onclick="accionServicioCorrectivoReporte('Regresar');">Regresar</button></td>
</tr>
</table>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>