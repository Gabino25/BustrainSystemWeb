<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unidades</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<fieldset style="margin-left:10px;width: 98%;">
<table>
 <tr>
 <td>
 <table>
  <tr>
  <td class="xxbstdtextalign"><label>Eco:</label></td>
  <td><input id="Eco" name="Eco" type="text" size=50 value="${Autobus.eco}" readonly/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Serie:</label></td>
  <td> <input id="Serie" name="Serie" type="text" size=50 value="${Autobus.serie}" readonly/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Motor:</label></td>
  <td><input id="Motor" name="Motor" type="text" size=50 value="${Autobus.motor}" readonly/></td>
  </tr>
   <tr>
  <td class="xxbstdtextalign"><label>Carroceria:</label></td>
  <td><input id="Carroceria" name="Carroceria" type="text" size=50 value="${Autobus.carroceria}" readonly/></td>
  </tr>
  <tr>
   <td class="xxbstdtextalign"><label>Modelo:</label></td>
   <td> <input id="Modelo" name="Modelo" type="text" size=50 value="${Autobus.modelo}" readonly/></td>
   </tr>
   <tr>
   <td class="xxbstdtextalign"><label for="Pasajeros">Pasajeros</label></td>
   <td><input id="Pasajeros" name="Pasajeros" type="number" value="${Autobus.pasajeros}" readonly></td>
   </tr>
   <tr>
     <td class="xxbstdtextalign"><label for="Placas">Placas:</label></td>
     <td><input id="Placas" name="Placas" type="text" size=50 value="${Autobus.placas}" readonly></td>
    </tr>
      <tr>
    <td class="xxbstdtextalign"><label for="Concesion">Concesion:</label></td>
    <td><input id="Concesion" name="Concesion" type="text" size=50 value="${Autobus.concesion}" readonly></td>
    </tr>
     <tr>
     <td class="xxbstdtextalign"><label for="ClaveVehicular">Clave&nbsp;Vehicular:</label></td>
     <td><input id="ClaveVehicular" name="ClaveVehicular" type="text" size=50 value="${Autobus.clavevehicular}" readonly></td>
    </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="VigUltimoReferendo">Vig.&nbsp;Ultimo&nbsp;Referendo:</label></td>
     <td><input id="VigUltimoReferendo" name="VigUltimoReferendo" type="date" value="${Autobus.vigultimoreferendoyyyyMMdd}" readonly></td>
    </tr>
   <tr>
   <td class="xxbstdtextalign"><label>Propietario:</label></td>
   <td> 
   <input type="text" id="Empresa" name="Empresa" value="${Autobus.empresa}" size=50 readonly/> 
   </td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Nota:</label></td>
  <td><textarea id="nota" rows="6" cols="60" name="nota" readonly >${Autobus.nota}</textarea></td>
  </tr>
   <tr>
   <td class="xxbstdtextalign"><label for="Agencia">Mantenimiento:</label></td>
   <td><input id="Agencia" type="text" name="Agencia" value="0" value="${Autobus.agencia}" readonly>
   </td> 
  </tr>
 </table>
 </td>
 <td class="xxbstdvalign">
 <table>
 <tr>
 <td class="xxbstdtextalign"><label>Tipo:</label></td>
 <td>
 <input type="text" id="Tipo" name="Tipo" value="${Autobus.tipo}" readonly>
 </td>
 <td class="xxbstdtextalign"><label>Combustible:</label></td>
 <td>
 <input type="text" id="Combustible" name="Combustible" value="${Autobus.combustible}" readonly>
 </td>
 <td class="xxbstdtextalign"><label for="Unidad">Unidad:</label></td>
 <td><input type="text" id="Unidad" name="Unidad" value="${Autobus.tipounidad}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Ultimo&nbsp;Servico:</label></td>
 <td><input id="UltimoServicio" name="UltimoServicio" type="date" value="${Autobus.servicioyyyyMMdd}" readonly/></td>
<!--  <td class="xxbstdtextalign"><label>Km/hrs:</label></td> -->
<!--  <td><input id="Kmhrs" name="Kmhrs" type="number" readonly/></td> -->
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Aseguradora:</label></td>
 <td colspan="3"><input id="Aseguradora" name="Aseguradora" type="text" size=64 value="${Autobus.aseguradora}" readonly/></td>
 <td class="xxbstdtextalign"><label>Broker:</label></td>
 <td><input id="Broker" name="Broker" type="text" value="${Autobus.asegbroker}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Frecuencia:</label></td>
 <td colspan="3"><input id="Frecuencia" name="Frecuencia" type="number" value="${Autobus.frecuenciamantto}" readonly/></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Poliza:</label></td>
 <td><input id="Poliza" name="Poliza" type="text" value="${Autobus.poliza}" readonly/></td>
 <td class="xxbstdtextalign"><label for="Inicio">Inicio:</label></td>
 <td><input id="Inicio" name="Inicio" type="number" value="${Autobus.inicio}" readonly></td>
 <td class="xxbstdtextalign"><label for="Endoso">Endoso:</label></td>
 <td><input id="Endoso" name="Endoso" type="number" value="${Autobus.endoso}" readonly></td>
 <td class="xxbstdtextalign"><label>Vigencia:</label></td>
 <td><input id="Vence" name="Vence" type="date" value="${Autobus.venceyyyyMMdd}" readonly/></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Estado:</label>
 <td><input type="text" name="estado" value="${Autobus.estado}" readonly/></td>
 </tr>
<tr>
 <td class="xxbstdtextalign"><label for="Cobertura">Cobertura:</label></td>
 <td><input id="Cobertura" name="Cobertura" type="text" value="${Autobus.cobertura}" readonly></td>
 <td class="xxbstdtextalign"><label for="Hospital">Hospital:</label></td>
 <td><input id="Hospital" name="Hospital" type="text" value="${Autobus.hospital}" readonly></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="Servicio">Servicio:</label></td>
 <td><input id="Servicio" name="Servicio" type="text" value="${Autobus.asegservicio}" readonly></td>
 </tr>
 </table>
 </td>
 </tr>
</table>
</fieldset>

<div style="margin-left:10px;width: 98%;">
 <form action="${pageContext.request.contextPath}/UnidadesCO?accionName=Regresar" method="post">
 <button id="RegresarBtn" class="btn btn-primary" >Regresar</button>
 </form>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>