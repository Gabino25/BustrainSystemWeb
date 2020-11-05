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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/utils/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/upd/UnidadesUpd.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>

<fieldset style="margin-left:10px;width: 98%;">
<form id="formUpd" action="${pageContext.request.contextPath}/UnidadesCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"/> 
<table>
 <tr>
 <td>
 <table>
  <tr>
  <td class="xxbstdtextalign"><label>Eco:</label></td>
  <td><input id="Eco" name="Eco" type="text" size=20 value="${Autobus.eco}" required/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Serie:</label></td>
  <td> <input id="Serie" name="Serie" type="text" size=60 value="${Autobus.motor}" required/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Motor:</label></td>
  <td><input id="Motor" name="Motor" type="text" size=60 value="${Autobus.motor}" required/></td>
  </tr>
   <tr>
  <td class="xxbstdtextalign"><label>Carroceria:</label></td>
  <td><input id="Carroceria" name="Carroceria" type="text" size=60 value="${Autobus.carroceria}" required/></td>
  </tr>
  <tr>
   <td class="xxbstdtextalign"><label>Modelo:</label></td>
   <td> <input id="Modelo" name="Modelo" type="text" size=60 value="${Autobus.modelo}" required/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Pasajeros:</label></td>
  <td><input id="Pasajeros" name="Pasajeros" type="number" value="${Autobus.pasajeros}" required>
  </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="Placas">Placas:</label></td>
     <td><input id="Placas" name="Placas" type="text" value="${Autobus.placas}" required></td>
    </tr>
    <tr>
    <td class="xxbstdtextalign"><label for="Concesion">Concesion:</label></td>
    <td><input id="Concesion" name="Concesion" type="text" value="${Autobus.concesion}" ></td>
    </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="ClaveVehicular">Clave&nbsp;Vehicular:</label></td>
     <td><input id="ClaveVehicular" name="ClaveVehicular" type="text" value="${Autobus.clavevehicular}" required></td>
    </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="VigUltimoReferendo">Vig.&nbsp;Ultimo&nbsp;Referendo:</label></td>
     <td><input id="VigUltimoReferendo" name="VigUltimoReferendo" type="date" value="${Autobus.vigultimoreferendoyyyyMMdd}" required></td>
    </tr>
   <tr>
   <td class="xxbstdtextalign"><label>Empresa:</label></td>
   <td> 
   <input id="EmpresaTrx" type="hidden" value="${Autobus.empresa}">
   <select id="Empresa" name="Empresa" required>
   <option value=""></option>
   <option value="BOLK TRANSPORTES S.A. DE C.V.">BOLK TRANSPORTES S.A. DE C.V.</option>
   <option value="BUSTRAIN GLOBAL S.A. DE C.V.">BUSTRAIN GLOBAL S.A. DE C.V.</option>
   <option value="FINANCIADO">FINANCIADO</option>
   </select>
   </td>
  <tr>
   <td class="xxbstdtextalign"><label>Nota:</label></td>
  <td><textarea id="nota" rows="6" cols="60" name="nota"  >${Autobus.nota}</textarea></td>
  </tr>
  <tr>
   <td></td>
   <td><input id="AgenciaTrx" type="hidden" value="${Autobus.agencia}"/>
       <input id="Agencia" type="radio" name="Agencia" value="0"><label for="Agencia">Agencia</label>&nbsp;&nbsp;&nbsp;
       <input id="Taller" type="radio" name="Agencia" value="1"><label for="Taller">Taller</label>&nbsp;&nbsp;&nbsp;
       <input id="Ninguno" type="radio" name="Agencia" value="2"><label for="Ninguno">Ninguno</label>&nbsp;&nbsp;&nbsp;
   </td> 
  </tr>
 </table>
 </td>
 <td class="xxbstdvalign">
 <table>
 <tr>
 <td class="xxbstdtextalign"><label>Tipo:</label></td>
 <td>
 <input id="TipoTrx" type="hidden" value="${Autobus.tipo}">
 <select id="Tipo" name="Tipo" required>
 <option value=""></option>
 <option value="AUTOBUS">AUTOBUS</option>
 <option value="CAMIONETA">CAMIONETA</option>
 <option value="TAXI">TAXI</option>
 <option value="MAQUINARIA">MAQUINARIA</option>
 </select>
 </td>
 <td class="xxbstdtextalign"><label>Combustible:</label></td>
 <td>
 <input id="CombustibleTrx" type="hidden" value="${Autobus.combustible}">
 <select  id="Combustible" name="Combustible" required>
 <option value=""></option>
 <option value="MAGNA">MAGNA</option>
 <option value="PREMIUM">PREMIUM</option>
 <option value="DIESEL">DIESEL</option>
 <option value="GAS LP">GAS LP</option>
 </select>
 </td>
  <td class="xxbstdtextalign">
  <input type="hidden" id="tipounidadTrx" name="tipounidadTrx" value="${Autobus.tipounidad}">
  <label for="Unidad">Unidad:</label></td>
 <td><select id="Unidad" name="Unidad">
      <option value=""></option>
     <c:forEach var="ListXxbslookupsTipoUnidad" items="${selListXxbslookups}" varStatus="row">
       <option value="${ListXxbslookupsTipoUnidad.code}">${ListXxbslookupsTipoUnidad.code}</option>
      </c:forEach>  
    </select>
    </td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Ultimo&nbsp;Servico:</label></td>
 <td><input id="UltimoServicio" name="UltimoServicio" type="date" value="${Autobus.servicioyyyyMMdd}" required/></td>
<!--  <td class="xxbstdtextalign"><label>Km/hrs:</label></td> -->
<!--  <td><input id="Kmhrs" name="Kmhrs" type="number" /></td> -->
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Aseguradora:</label></td>
 <td colspan="3"><input id="Aseguradora" name="Aseguradora" type="text" size=64 value="${Autobus.aseguradora}" required/></td>
 <td class="xxbstdtextalign"><label>Broker:</label></td>
 <td><input id="Broker" name="Broker" type="text" value="${Autobus.asegbroker}"></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Frecuencia:</label></td>
 <td colspan="3"><input id="Frecuencia" name="Frecuencia" type="number" value="${Autobus.frecuenciamantto}" required/></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Poliza:</label></td>
 <td><input id="Poliza" name="Poliza" type="text" value="${Autobus.poliza}" required/></td>
 <td class="xxbstdtextalign"><label for="Inicio">Inicio:</label></td>
 <td><input id="Inicio" name="Inicio" type="number"  value="${Autobus.inicio}"></td>
 <td class="xxbstdtextalign"><label for="Endoso">Endoso:</label></td>
 <td><input id="Endoso" name="Endoso" type="number"  value="${Autobus.endoso}"></td>
 <td class="xxbstdtextalign"><label>Vigencia:</label></td>
 <td><input id="Vence" name="Vence" type="date" value="${Autobus.venceyyyyMMdd}" required/></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Estado:</label></td>
 <td><input id="EstadoTrx" type="hidden" value="${Autobus.estado}"><input id="EstadoY" type="radio" name="Estado" value="ACTIVO" ><label for="EstadoY">Activo</label>&nbsp;&nbsp;&nbsp;<input id="EstadoN" type="radio" name="Estado" value="INACTIVO"><label for="EstadoN">Inactivo</label></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="Cobertura">Cobertura:</label></td>
 <td><input id="Cobertura" name="Cobertura" type="text"  value="${Autobus.cobertura}"></td>
 <td class="xxbstdtextalign"><label for="Hospital">Hospital:</label></td>
 <td><input id="Hospital" name="Hospital" type="text"  value="${Autobus.hospital}"></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="Servicio">Servicio:</label></td>
 <td><input id="Servicio" name="Servicio" type="text"  value="${Autobus.asegservicio}"></td>
 </tr>
  </table>
 </td>
 </tr>
</table>

<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="folioUnidadTrx" name="folioUnidadTrx" value="${Autobus.folio}"/>
</form>
</fieldset>

<div style="margin-left:10px;width: 98%;">
<button id="RegresarBtn" class="btn btn-primary" onclick="Regresar();" >Regresar</button>
<button id="UpdateBtn" class="btn btn-primary" onclick="Actualizar();" >Actualizar</button>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>
</body>
</html>