<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrar Llantas Modo Consulta</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/llantas/AdministrarLlantas.css">
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<fieldset style="margin-left:10px;">
 <table>
 <tbody>
 <tr>
 <td class="xxbstdtextalign"><label for="nombre">Nombre:</label></td>
 <td><input id="nombre" value="${llanta.nombre}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="medida">Medida:</label></td>
 <td><input id="medida" value="${llanta.medida}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="marca">Marca:</label></td>
 <td><input id="marca" value="${llanta.marca}" readonly></td>
 </tr>
  <tr>
 <td class="xxbstdtextalign"><label for="modelo">Modelo:</label></td>
 <td><input id="modelo" value="${llanta.modelo}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="profundidad_inicial">Profunidad Inicial:</label></td>
 <td><input id="profundidad_inicial" value="${llanta.profundidad_inicial}" readonly></td>
 </tr>
  <tr>
  <td class="xxbstdtextalign"><label for="profundidad_actual">Profunidad Actual:</label></td>
  <td><input id="profundidad_actual" value="${llanta.profundidad_actual}" readonly></td>
 </tr>
 <tr>
  <td class="xxbstdtextalign"><label for="estado">Estado:</label></td>
  <td><input id="estado" value="${llanta.estado}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="costo">Costo:</label></td>
  <td><input id="costo" value="${llanta.costo}" readonly></td>
  </tr>
  <tr>
   <td class="xxbstdtextalign"><label>Fecha Alta:</label></td>
   <td><input id="fecha_alta" value="${llanta.fecha_altaddMMyyyyV2}" readonly></td>
   </tr>
 </tbody>
 </table>
 </fieldset>
 
 <form action="${pageContext.request.contextPath}/AdministrarLlantasCO?accionAdministrarLlantas=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary" >Regresar</button>
 </form>
 <jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>