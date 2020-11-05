<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="bs.admin.entry.dto.Permisos"%>

<%
if(null==request.getSession().getAttribute("sAttrNombreUsuario")||
"".equals(request.getSession().getAttribute("sAttrNombreUsuario"))){
	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo de inicio de Sesión. Favor de iniciar Sesión.");
	return;
}
String strNombreUsuario = (String)request.getSession().getAttribute("sAttrNombreUsuario");
%>

<%
java.util.List liPermisos = (java.util.ArrayList)session.getAttribute("listPermisos");
java.util.Iterator<Permisos> iterPermisos = liPermisos.iterator();
%>

<%!
String validaPermisos(String pPantalla
		             ,java.util.Iterator<Permisos> pIterator
		             ){
	String strRetval = "N";
	while(pIterator.hasNext()){
		Permisos permisos = pIterator.next(); 
		if(pPantalla.equals(permisos.getPantalla())){
			strRetval = "Y";
			break;
		}
	}
	return strRetval;
 }
%>

<div id="divHeader">
<ul id="navMain">
      <li><a href="#Archivo">Archivo</a>
      	<ul>
      	    <%
             String strValida = this.validaPermisos("LICENCIAS TRABAJADORES",iterPermisos);
             if ("Y".equals(strValida)) {
              %>
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=LicenciaTrabajadores">Licencias&nbsp;Trabajadores</a></li>
      		<%}%>  
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("SEMAFORO TALLER",iterPermisos);
             if ("Y".equals(strValida)) {
              %>
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=SemaforoTaller">Semaforo Taller</a></li>
      		<%}%> 
      		 <%
      		 iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("SEMAFORO AGENCIA",iterPermisos);
             if ("Y".equals(strValida)) {
              %>
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=SemaforoAgencia">Semaforo Agencia</a></li>
      		<%}%> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=Salir">Salir</a></li>
      	</ul>
      </li>
       <li><a href="#Catalogos">Catalogos</a>
      	<ul> 
      	     <%
      	     iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("CATALOGO CLIENTES",iterPermisos);
             if ("Y".equals(strValida)) {
              %>
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CatalogoClientes">Clientes</a></li>
      		<%}%> 
      		<%
      		 iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("CATALOGO PROVEEDORES",iterPermisos);
             if ("Y".equals(strValida)) {
              %>
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CatalogoProveedores">Proveedores</a></li>
      		<%}%>
      		<%
      		 iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("CATALOGO TRABAJADORES",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CatalogoTrabajadores">Trabajadores</a></li>
      		<%}%>
      		<%
      		 iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("CATALOGO CELULARES",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=Celulares">Celulares</a></li>
      		<%}%>
      		<%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("CATALOGO UNIDADES",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CatalogoUnidades">Unidades</a></li>
      		<%}%>
      		<%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("CATALOGO RUTAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CatalogoRutas">Rutas</a></li>
      		<%}%>
      	</ul>
      </li>
      <li><a href="#Vales">Vales</a>
      	<ul>
      	      <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("VALES CAPTURA",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CapturaValesTransporte">Captura</a></li>
      		<%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("VALES LISTADO",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=ListadoBoletas">Listado</a></li>
      		<%}%>
      	    <!--   <li><a href="#Facturas">Facturas</a></li>  Estudiar AdminPack-->
      	</ul>
      </li>
      <li><a href="#Combustible">Combustible</a>
      	<ul>
      	     <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("COMBUSTIBLE ANALIZAR",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=AnalizarCombustible">Analizar</a></li>
      		 <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("COMBUSTIBLE CARGAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CargaCombustible">Cargas</a></li>
      		  <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("COMBUSTIBLE RENDIMIENTOS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CalculaRendimientos">Calcula&nbsp;Rendimientos</a></li>
      	    <%}%>
      	</ul>
      </li>
      <li><a href="#Servicio">Servicio</a>
      	<ul>
      	    <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("SERVICIO PREVENTIVO",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=ServicioPreventivo">Preventivo</a></li>
      		 <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("SERVICIO CORRECTIVO",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=ServicioCorrectivo">Correctivo</a></li>
      		 <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("SERVICIO REPORTE DE FALLAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=ReporteDeFallas">Reporte&nbsp;de&nbsp;Fallas</a></li>
             <%}%>
              <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("SERVICIO ANALIZAR",iterPermisos);
             if ("Y".equals(strValida)) {
              %>
              <li><a href="${pageContext.request.contextPath}/MainCO?accion=AnalizarFallas">Analizar</a></li>
             <%}%>
      	</ul>
      </li>
      <li><a href="#Compras">Compras</a>
      	<ul>
      	     <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("COMPRAS CAPTURAR FACTURAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=CapturarFacturas">Capturar&nbsp;Facturas</a></li>
      		 <%}%>
      		<%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("COMPRAS FILTRADO FACTURAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=FiltradoFacturas">Filtrado&nbsp;Facturas</a></li>
      	     <%}%>
      	     <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("COMPRAS CAPTURAR CUENTAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      	    <li><a href="${pageContext.request.contextPath}/MainCO?accion=CapturaCuentas">Captura&nbsp;Cuentas</a></li>
      	     <%}%>
      	</ul>
      </li>
      <li><a href="#AsignacionVales">Asignacion&nbsp;Vales</a>
      	<ul>
      	    <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("ASIGNACION VALES",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=AsignacionValesTransporte">Asignacion</a></li>
      	     <%}%>
      	</ul>
      </li>
      <li><a href="#Llantas">Llantas</a>
      	<ul>
      	     <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("LLANTAS ADMINISTRAR",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=AdministrarLlantas">Administrar</a></li>
      		  <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("LLANTAS ASIGNACION",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=AsignacionLlantas">Asignacion</a></li>
      		  <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("LLANTAS PROFUNDIDAD",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=RevisionProfundidaLlantas">Profundidad</a></li>
      		  <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("LLANTAS REPARACIONES",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=ReparacionesLlantas">Reparaciones&nbsp;Llanta</a></li>
      		  <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("LLANTAS ANALIZAR",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=AnalizarLlantas">Analizar&nbsp;Llantas</a></li>
      		  <%}%>
      		 <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("LLANTAS BAJAS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
      		<li><a href="${pageContext.request.contextPath}/MainCO?accion=BajasLlantas">Baja&nbsp;de&nbsp;Llanta</a></li>
      	      <%}%>
      	</ul>
      </li>
       <li><a href="#Administrador">Administrador</a>
        <ul>
            <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("ADMINISTRAR USUARIOS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
          <li><a href="${pageContext.request.contextPath}/MainCO?accion=Usuarios">Usuarios</a></li>
             <%}%>
            <%
      		  iterPermisos = liPermisos.iterator();
              strValida = this.validaPermisos("ADMINISTRAR PERMISOS",iterPermisos);
             if ("Y".equals(strValida)) {
              %> 
          <li><a href="${pageContext.request.contextPath}/MainCO?accion=UsuariosPermisos">Usuarios&nbsp;Permisos</a></li>
             <%}%>
        </ul>
       </li>
       <li><a href="${pageContext.request.contextPath}/MainCO?accion=Salir"><%=strNombreUsuario%>&nbsp;(Salir)</a></li>
    </ul>
</div>    


