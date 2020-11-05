<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rutas</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Rutas.css">
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<br>
<fieldset style="margin-top:20px;margin-left:10px;width:98%;">
<label>Empresa:</label>
<input id="selectListEmpresas" name="empresa" size="80" value="${Ruta.cliente}" readonly/>
<table>
<tr>
<td>
<table>
<tr>
<td class="xxbstdtextalign"><label for="clave">Clave:</label></td>
<td><input id="clave" type="text" name="clave" value="${Ruta.clave}"  readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="nombreRuta">Nombre&nbsp;Ruta:</label></td>
<td><input id="nombreRuta" type="text"  name="nombreRuta"  size=50 value="${Ruta.ruta}" readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
<td><input id="costo"  type="number" name="costo" size=35  value="${Ruta.costo}"  readonly/></td>
</tr>
<tr>
<td class="xxbstdtextalign"><label for="fecha">Fecha:</label></td>
<td><input id="fecha" type="date"  name="fecha" size=35  value="${Ruta.fechayyyyMMdd}" readonly/></td>
</tr>
<tr>
<td colspan=2>
<table>
<tr>
<td>
<fieldset>
  <legend>Tipo&nbsp;Servicio</legend>
  <input id="tipoServicio" name="tipoServicio" value="${Ruta.tipounidad}" readonly/>
</fieldset>
</td>
<td>
<fieldset>
  <legend>Tipo&nbsp;Pago</legend>
  <input id="tipoPago" name="tipoPago" value="${Ruta.tipopago}" readonly/>
  </fieldset>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
<td class="xxbstdvalign">
<table>
<tr>
<td><label for="descripcion">Descripcion</label></td>
</tr>
<tr>
<td>
<textarea id="descripcion" rows="6" cols="60" name="descripcion" readonly>${Ruta.descripcion}</textarea>
</td>
</tr>
</table>
</td>
</tr>
</table>
</fieldset>

 <form action="${pageContext.request.contextPath}/RutasCO?accionName=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary">Regresar</button>
 </form>

 <jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>