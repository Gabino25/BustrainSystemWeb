<%@page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bustrain System Web</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/valIndex.js"></script>
</head>
<body>

<div id="header">
<!--  <img alt="" src="${pageContext.request.contextPath}/resources/images/BustrainGlobal.png"> -->
</div>

<form id="formIndex" name="nFormIndex" action="${pageContext.request.contextPath}/IndexCO" method="post" onsubmit="return validarFormIndex(this);" autocomplete="off">
<fieldset style="width:25%;margin:0 auto">
<table style="margin:0 auto">
<tr>
<td align="right"><label for="nombre">Nombre</label></td>
<td><input id="nombre" style="text-transform:uppercase;" type="text" name="nombreUsuario" size="30" autofocus required/></td>
</tr>
<tr>
<td align="right"><label for="password">Contrase&ntilde;a</label></td>
<td><input id="password" style="text-transform:uppercase;" type="password" name="passwordUsuario" size="30" required/></td>
</tr>
</table>
</fieldset>
<br>
<table style="margin:0 auto">
<tr>
<td><button id="submitBtn" class="btn btn-primary" type="submit">Aceptar</button></td>
<td><button id="resetBtn" class="btn btn-primary" type="reset">Cancelar</button></td>
</tr>
</table>
</form>

 <div id="footer">
    <jsp:include page="/WEB-INF/commons/footer.jsp"/>
 </div>

</body>
</html>