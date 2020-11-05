<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/WEB-INF/manejoErrores.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Semaforo Agencia</title>
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/B.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ui/1.12.1/themes/smoothness/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/webui/Semaforos.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/webui/SemaforoAgencia.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/commons/header.jsp"/>
<div id="moduloPantallaDiv" class="modPantDiv">
 Archivo - Semaforo Agencia
</div> 

<div id="SemaforoAgenciaDiv">
<table id="TablRO" class="table table-striped table-bordered table-condensed" >
 <thead class="xxbstheadbg">
 <tr>
 <th>UNIDAD</th>
 <th>FECHA&nbsp;MANNTO</th>
 <th>KM&nbsp;MANNTO</th>
 <th>FECHA&nbsp;ACTUAL</th>
 <th>KILOMETRAJE</th>
 <th>KM&nbsp;RECORRIDO</th>
 <th>FRECUENCIA&nbsp;MANNTO</th>
 <th>DIAS&nbsp;RECORRIODOS</th>
 <th>KM/DIA</th>
 <th>FRECUENCIA&nbsp;DIAS</th>
 <th>DIAS&nbsp;P/SIGUIENTE</th>
 <th>CATEGORIAFILTRO</th>
 </tr> 
 </thead>
 <tbody>
  <c:forEach  var="semaforoAgencia" items="${listSemaforoAgencia}" varStatus="row">
 <tr>
 <td>${semaforoAgencia.eco}</td>
 <td>${semaforoAgencia.servicioddMMyyyyV2}</td>
 <td>${semaforoAgencia.kilometrajemanntoDecimalFormatV3}</td>
 <td>${semaforoAgencia.fechakmddMMyyyyV2}</td>
 <td>${semaforoAgencia.kilometrajeDecimalFormatV3}</td>
 <td>${semaforoAgencia.km_recorridosDecimalFormatV3}</td>
 <td>${semaforoAgencia.frecuenciamannto}</td>
 <td>${semaforoAgencia.dias_recorridos}</td>
 <td>${semaforoAgencia.km_por_dia}</td>
 <td>${semaforoAgencia.frecuencia_dias}</td>
 <td>${semaforoAgencia.manntosiguienteddMMyyyyV2}</td>
 <td>${semaforoAgencia.categoriafiltro}</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 
 <form id="formSemaforoAgenciaId" action="${pageContext.request.contextPath}/SemaforoAgenciaCO" method="post">
 <input type="hidden" id="accionSemaforoAgencia" name="accionSemaforoAgencia">
 </form>
 
 <table>
 <tbody>
 <tr>
 <td><label id="ultimaFechaCaptura"></label><br>
     <label id="cantidadDeRegistros"></label><br>
     <label id="unidadesEnTiempoManteniento"></label>
     </td>
 <td><fieldset>
 <legend>Filtrar</legend>
 <input id="todos" type="radio" name="filtrar" onclick="fPendientesXkms(this);"><label for="todos">Todos</label><br>
 <input id="pendientesXKms" type="radio" name="filtrar" onclick="fPendientesXkms(this);"><label for="pendientesXKms">Pendientes&nbsp;por&nbsp;Kms</label>
 <input id="proximosXKms" type="radio" name="filtrar" onclick="fPendientesXkms(this);"><label for="proximosXKms">Proximos&nbsp;por&nbsp;Kms</label><br>
 <input id="pendientesXTiempo" type="radio" name="filtrar" onclick="fPendientesXkms(this);"><label for="pendientesXTiempo">Pendientes&nbsp;por&nbsp;Tiempo</label>
 <input id="proximosXTiempo" type="radio" name="filtrar" onclick="fPendientesXkms(this);"><label for="proximosXTiempo">Proximos&nbsp;por&nbsp;Tiempo</label>
 </fieldset></td>
 <td><button id="salirBtn" class="btn btn-primary" onclick="accionSemaforoAgencia('Salir');">Salir</button></td>
 <td><button id="capturaBtn" class="btn btn-primary" onclick="accionSemaforoAgencia('Captura');">Captura</button></td>
 <td><button id="excelBtn" class="btn btn-primary" onclick="accionSemaforoAgencia('Excel');">Excel</button></td>
 </tr>
 </tbody>
 </table>
 </div>
 
 <div id="CapturaSemaforoAgenciaDiv">
  <label>DATOS DE ULTIMO MANTENIMIENTO</label>
  
  <form id="formCaptura" action="${pageContext.request.contextPath}/ReporteDeFallasCO" onsubmit="return validaCaptura(this);"  method="post" autocomplete="off"> 
  <table>
  <tr>
  <td><label>Unidad:</label></td>
  <td>
  <select id="selectUnidadId" name="unidad" required>
  <option value=""></option>
  <c:forEach var="selLisAutobus" items="${selectListAutobus}" varStatus="row">
  <option value="${selLisAutobus.eco}">${selLisAutobus.eco}</option>
  </c:forEach>  
  </select>
  </td>
  </tr>
  <tr>
  <tr>
  <td><label>Fecha:</label></td>
  <td><input id="fechaCaptura" name="fechaCaptura" type="date" required/><td>
  </tr>
  <tr>
  <td><label>Kilometraje</label></td>
  <td><input id="kilometrajeCaptura" name="kilometrajeCaptura" type="number" min="0" required></td>
  </tr> 
  <tr>
  <td><label>Descripcion:</label></td>
  <td><textarea id="descripcionCaptura" name="descripcionCaptura" rows="4" cols="40" required></textarea></td>
  </tr>
  </table>
  <input type="submit" id="submitCaptura" name="submitCaptura"/>
  <input type="reset" id="resetCaptura" name="resetCaptura"/>
  <input type="hidden" id="accionFormId" name="accionFormName" />
  </form>
  
  <table>
  <tr>
  <td><button onclick="accionCaptura('Guardar');">Guardar</button></td>
  <td><button onclick="accionCaptura('Salir');">Salir</button></td>
  </tr>
  </table>
  
 </div>
 
  <div id="footer">
    <jsp:include page="/WEB-INF/commons/footer.jsp"/>
 </div>
 
</body>
</html>