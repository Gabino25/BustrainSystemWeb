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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/catalogo/Unidades.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/catalogo/Unidades.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Catalogos - Unidades
</div> 

<table id="TablRO" class="table table-striped table-bordered table-condensed">
<thead class="xxbstheadbg">
<tr>
<th>FOLIO</th>
<th>ECO</th>
<th>COMBUSTIBLE</th>
<th>EMPRESA</th>
<th>ESTADO</th>
<th>KILOMETRAJE</th>
<th>FECHAKM</th>
<th>TIPO</th>
<th>SERIE</th>
<th>CARROCERIA</th>
<th>MODELO</th>
<th>MOTOR</th>
<th>SERVICIO</th>
<th>KILOMETRAJEMANTTO</th>
<th>POLIZA</th>
<th>VENCE&nbsp;&nbsp;&nbsp;&nbsp;</th>
<th>FRECUENCIAMANTTO</th>
<th>ASEGURADORA</th>
<th>KMSRECORRIDOS</th>
<th>DIASRECORRIDOS</th>
<th>KMDIA</th>
<th>DIASFRECUENCIA</th>
<th>DIASSIGUIENTE</th>
<th>AGENCIA</th>
<th>USUARIO</th>
<th>FECHAALTA</th>
<th>DESCRIPCION</th>
</tr>
</thead>
<tbody class="xxbstbodybg">
<c:forEach  var="catAutobus" items="${listAutobus}" varStatus="row">
<tr>
<td>${catAutobus.folio}</td>
<td>${catAutobus.eco}</td>
<td>${catAutobus.combustible}</td>
<td>${catAutobus.empresa}</td>
<td>${catAutobus.estado}</td>
<td>${catAutobus.kilometraje}</td>
<td>${catAutobus.fechakmddMMyyyy}</td>
<td>${catAutobus.tipo}</td>
<td>${catAutobus.serie}</td>
<td>${catAutobus.carroceria}</td>
<td>${catAutobus.modelo}</td>
<td>${catAutobus.motor}</td>
<td>${catAutobus.servicioddMMyyyy}</td>
<td>${catAutobus.kilometrajemantto}</td>
<td>${catAutobus.poliza}</td>
<td>${catAutobus.venceddMMyyyy}</td>
<td>${catAutobus.frecuenciamantto}</td>
<td>${catAutobus.aseguradora}</td>
<td>${catAutobus.kmsrecorridos}</td>
<td>${catAutobus.diasrecorridos}</td>
<td>${catAutobus.kmdia}</td>
<td>${catAutobus.diasfrecuencia}</td>
<td>${catAutobus.diassiguiente}</td>
<td>${catAutobus.agencia}</td>
<td>${catAutobus.usuario}</td>
<td>${catAutobus.fechaalta}</td>
<!-- <td><div style="white-space: nowrap; 
  width: 100px; 
  overflow: hidden;
  text-overflow:ellipsis;
  border: 1px solid #000000;" >${catAutobus.descripcion}</div></td>-->
<td>${catAutobus.descripcion}</td> 
</tr>
</c:forEach>
</tbody>
</table>
<form id="catUnidadesForm" action="${pageContext.request.contextPath}/UnidadesCO" method="post" onsubmit="return validaFormulario(this);" autocomplete="off">
<input id="accionId" type="hidden" name="accionName"> 
<fieldset style="margin-left:10px;width: 98%;">
<table>
 <tr>
 <td>
 <table>
  <!--<tr>
  <td class="tdLab"><label>Folio:</label></td>
  <td><input id="Folio" name="Folio" type="number" size=20 required/></td>
  </tr>-->
  <tr>
  <td class="xxbstdtextalign"><label for="Eco">Eco:</label></td>
  <td><input id="Eco" name="Eco" type="text" size=50 maxlength="50" required/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label for="Serie">Serie:</label></td>
  <td> <input id="Serie" name="Serie" type="text" size=50 maxlength="50" required/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label for="Motor">Motor:</label></td>
  <td><input id="Motor" name="Motor" type="text" size=50 maxlength="50" required/></td>
  </tr>
   <tr>
  <td class="xxbstdtextalign"><label for="Carroceria">Carroceria:</label></td>
  <td><input id="Carroceria" name="Carroceria" type="text" size=50 maxlength="50" required/></td>
  </tr>
  <tr>
   <td class="xxbstdtextalign"><label for="Modelo">Modelo:</label></td>
   <td> <input id="Modelo" name="Modelo" type="text" size=50 maxlength="50" required/></td>
  </tr>
  <tr>
  <td class="xxbstdtextalign"><label>Pasajeros:</label></td>
  <td><input id="Pasajeros" name="Pasajeros" type="number" min="0" required>
  </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="Placas">Placas:</label></td>
     <td><input id="Placas" name="Placas" type="text" size=50 maxlength="50" ></td>
    </tr>
    <tr>
    <td class="xxbstdtextalign"><label for="Concesion">Concesion:</label></td>
    <td><input id="Concesion" name="Concesion" type="text" size=50 maxlength="50" ></td>
    </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="ClaveVehicular">Clave&nbsp;Vehicular:</label></td>
     <td><input id="ClaveVehicular" name="ClaveVehicular" type="text" size=50 maxlength="50" required></td>
    </tr>
    <tr>
     <td class="xxbstdtextalign"><label for="VigUltimoReferendo">Vig.&nbsp;Ultimo&nbsp;Referendo:</label></td>
     <td><input id="VigUltimoReferendo" name="VigUltimoReferendo" type="date" required></td>
    </tr>
   <tr>
   <td class="xxbstdtextalign"><label for="Empresa">Propietario:</label></td>
   <td> 
   <select id="Empresa" name="Empresa" required>
   <option value=""></option>
   <option value="BOLK TRANSPORTES S.A. DE C.V.">BOLK TRANSPORTES S.A. DE C.V.</option>
   <option value="BUSTRAIN GLOBAL S.A. DE C.V.">BUSTRAIN GLOBAL S.A. DE C.V.</option>
   <option value="FINANCIADO">FINANCIADO</option>
   </select>
   </td>
  </tr>
  <tr>
  <td>Nota:</td>
  <td><textarea id="nota" rows="6" cols="60" name="nota" ></textarea></td>
  </tr>
  <tr>
   <td></td>
   <td><input id="Agencia" type="radio" name="Agencia" value="0"><label for="Agencia">Agencia</label>&nbsp;&nbsp;&nbsp;
       <input id="Taller" type="radio" name="Agencia" value="1"><label for="Taller">Taller</label>&nbsp;&nbsp;&nbsp;
       <input id="Ninguno" type="radio" name="Agencia" value="2" checked><label for="Ninguno">Ninguno</label>&nbsp;&nbsp;&nbsp;
   </td> 
  </tr>
 </table>
 </td>
 <td class="xxbstdvalign">
 <table>
 <tr>
 <td class="xxbstdtextalign"><label for="Tipo">Tipo:</label></td>
 <td>
 <select id="Tipo" name="Tipo" required>
 <option value=""></option>
 <option value="AUTOBUS">AUTOBUS</option>
 <option value="CAMIONETA">CAMIONETA</option>
 <option value="TAXI">TAXI</option>
 <option value="MAQUINARIA">MAQUINARIA</option>
 </select>
 </td>
 <td class="xxbstdtextalign"><label for="Combustible">Combustible:</label></td>
 <td>
 <select  id="Combustible" name="Combustible" required>
 <option value=""></option>
 <option value="MAGNA">MAGNA</option>
 <option value="PREMIUM">PREMIUM</option>
 <option value="DIESEL">DIESEL</option>
 <option value="GAS LP">GAS LP</option>
 </select>
 </td>
 <td class="xxbstdtextalign"><label for="Unidad">Unidad:</label></td>
 <td><select id="Unidad" name="Unidad">
      <option value=""></option>
     <c:forEach var="ListXxbslookupsTipoUnidad" items="${selListXxbslookups}" varStatus="row">
       <option value="${ListXxbslookupsTipoUnidad.code}">${ListXxbslookupsTipoUnidad.code}</option>
      </c:forEach>  
    </select>
    </td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="UltimoServicio">Ultimo&nbsp;Servico:</label></td>
 <td><input id="UltimoServicio" name="UltimoServicio" type="date" required/></td>
<!--  <td class="xxbstdtextalign"><label>Km/hrs:</label></td> -->
<!--  <td><input id="Kmhrs" name="Kmhrs" type="number" min="0" /></td> -->
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Aseguradora:</label></td>
 <td colspan="3"><input id="Aseguradora" name="Aseguradora" type="text" size=64 maxlength="250" required/></td>
 <td class="xxbstdtextalign"><label>Broker:</label></td>
 <td><input id="Broker" name="Broker" type="text" maxlength="50"></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Frecuencia:</label></td>
 <td colspan="3"><input id="Frecuencia" name="Frecuencia" type="number" min="0" required/></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="Poliza">Poliza:</label></td>
 <td><input id="Poliza" name="Poliza" type="text"  maxlength="50" required/></td>
 <td class="xxbstdtextalign"><label for="Inicio">Inicio:</label></td>
 <td><input id="Inicio" name="Inicio" type="number" min="0"></td>
 <td class="xxbstdtextalign"><label for="Endoso">Endoso:</label></td>
 <td><input id="Endoso" name="Endoso" type="number" min="0"></td>
 <td class="xxbstdtextalign"><label for="Vence">Vigencia:</label></td>
 <td><input id="Vence" name="Vence" type="date" required/></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label>Estado:</label>
 <td><input id="EstadoY" type="radio" name="Estado" value="ACTIVO" checked><label for="EstadoY">Activo</label>&nbsp;&nbsp;&nbsp;<input id="EstadoN" type="radio" name="Estado" value="INACTIVO"><label for="EstadoN">Inactivo</label></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="Cobertura">Cobertura:</label></td>
 <td><input id="Cobertura" name="Cobertura" type="text" maxlength="50"></td>
 <td class="xxbstdtextalign"><label for="Hospital">Hospital:</label></td>
 <td><input id="Hospital" name="Hospital" type="text" maxlength="200"></td>
 </tr>
 <tr>
 <td class="xxbstdtextalign"><label for="Servicio">Servicio:</label></td>
 <td><input id="Servicio" name="Servicio" type="text" maxlength="50"></td>
 </tr>
 </table>
</td>
</tr> 
</table>
</fieldset>
<input id="submitFormId" type="submit"/>
<input id="resetFormId" type="reset"/>
<input type="hidden" id="folioUnidadTrx" name="folioUnidadTrx"/>
</form>

<div style=" margin-top: 1px; margin-bottom: 1px; margin-left: 150px;margin-right: 150px;">
<table style="margin: 0px auto;">
<tr>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="NuevaBtn" class="btn btn-primary" onclick="acccionCatUnidades('Nueva');">Nueva</button></td>
<td><button id="GuardarBtn" class="btn btn-primary" onclick="acccionCatUnidades('Guardar');">Guardar</button></td>
<td><button id="BorrarBtn" class="btn btn-primary" onclick="acccionCatUnidades('Borrar');">Borrar</button></td>
<% } %>
<td><button id="BuscarBtn" class="btn btn-primary" onclick="acccionCatUnidades('Buscar');">Buscar</button></td>
<% if ("0".equals((String)request.getAttribute("rAttrNivel"))) { %>
<td><button id="ModificarBtn" class="btn btn-primary" onclick="acccionCatUnidades('Modificar');">Modificar</button></td>

<td><button id="CancelarBtn" class="btn btn-primary" onclick="acccionCatUnidades('Cancelar');">Cancelar</button></td>
<td><button id="AgregarUnidadesBtn" class="btn btn-primary" onclick="acccionCatUnidades('AgregarUnidades');">Agregar&nbsp;Unidades</button>
<% } %>
<td><button id="SalirBtn" class="btn btn-primary" onclick="acccionCatUnidades('Salir');">Salir</button></td>
</tr>
</table>
</div>

<jsp:include page="/WEB-INF/commons/footer.jsp"/>

</body>
</html>