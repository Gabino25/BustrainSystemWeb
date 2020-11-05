package bs.catalogo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dto.CatAutobus;
import bs.catalogo.dto.CatTrabajadores;
import bs.util.Utils;

/**
 * Servlet implementation class TrabajadoresCO
 */
@WebServlet(name="TrabajadoresCO", urlPatterns= {"/TrabajadoresCO"})
@MultipartConfig
public class TrabajadoresCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrabajadoresCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
//				response.getWriter().println("Fallo de inicio de Sesión. Favor de iniciar Sesión TRABAJADORES.");
				request.getRequestDispatcher("/index.jsp").include(request, response);  
				
				return; 
			}
		
        String strAccion = request.getParameter("accionName"); 
		
        if("Buscar".equals(strAccion)) {
        	String strNoEmpBuscar = request.getParameter("noEmpBuscar"); 
        	CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
        	CatTrabajadores catTrabajadores = null; 
        	try {
        		catTrabajadores = catTrabajadoresDao.findByNumero(new BigDecimal(strNoEmpBuscar));
        		request.setAttribute("trabajador",catTrabajadores);
			} catch (SQLException e) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
				 return;
			}
        	request.getRequestDispatcher("/WEB-INF/bs/catalogo/inquiry/Trabajador.jsp").forward(request, response);  
		    return; 
        }else if("Salir".equals(strAccion)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Uniformes".equals(strAccion)){
			
			CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
			List<CatTrabajadores> listCatTrabajadores=null;
			try {
				listCatTrabajadores = catTrabajadoresDao.findAll2();
				request.setAttribute("listTrabajadores2", listCatTrabajadores);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
				 return;
			}
			
			
				request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UniformesTrabajadores.jsp").include(request, response);  
				return; 
			}
//else if("Nueva1".equals(strAccion)){
//			
//						
//				request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/Nueva.jsp").include(request, response);  
//				return; 
//			}
		else if("UGuardar".equals(strAccion)) {
			String strFechaA=request.getParameter("fechaA");
			String strTrabajador=request.getParameter("trabajador");
			String strCCamisa=request.getParameter("CCamisa");
			String strCPlayera=request.getParameter("CPlayera");
			String strCPantalon=request.getParameter("CPantalon");
			String strCZapato=request.getParameter("CZapato");
			
			
//			System.out.println("entro guardarU");
//			System.out.println(strCCamisa);
//			System.out.println(strCPlayera);
//			System.out.println(strCPantalon);
//			System.out.println(strCZapato);
//			System.out.println(strFechaA);
//			System.out.println(strTrabajador);
//			System.out.println("salio guardarU");
			
			if(!"".equals(strTrabajador)&&null!=strTrabajador
					 &&!"".equals(strFechaA)&&null!=strFechaA) {
				
				
				CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
				
				try {
			
				
				CatTrabajadores catTrabajadores = new CatTrabajadores();
				catTrabajadores.setFechaA(strFechaA);
				catTrabajadores.setTrabajadorU(strTrabajador);
				catTrabajadores.setcCamisa(strCCamisa);
				catTrabajadores.setcPlayera(strCPlayera);
				catTrabajadores.setcPantalon(strCPantalon);
				catTrabajadores.setcZapato(strCZapato);
			
				
				
				
				
				
				if(null==catTrabajadoresDao.insert2(catTrabajadores)) {
//					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=TrabajadoresCO");
//					 response.sendRedirect( urlWithSessionID );
					CatTrabajadoresDao catTrabajadoresDao1 = Utils.getCatTrabajadoresInstance();
					List<CatTrabajadores> listCatTrabajadores=null;
					try {
						listCatTrabajadores = catTrabajadoresDao1.findAll2();
						request.setAttribute("listTrabajadores2", listCatTrabajadores);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
						 return;
					}
					request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UniformesTrabajadores.jsp").include(request, response); 
					 return;
				 }
				
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				}
				return; 
				
				
				
			}else
			{
				response.getWriter().println("Fallo al validar los parametros en guardar uniformes.");
				return; 
			}
				
		
		}
        else if("Guardar".equals(strAccion)) {	
		 
		 	String strVigenciaLicencia = request.getParameter("vigenciaLicencia");
		 	String strVigenciaLicenciaF = request.getParameter("vigenciaLicenciaF");
		 	String strEstado = request.getParameter("status");
			String strNombre = request.getParameter("nombre"); 
			String strNoSeguro = request.getParameter("noSeguro");
			String strPuesto = request.getParameter("puesto");
			String strDireccion = request.getParameter("direcccion"); 
			String strTelefono = request.getParameter("telefono"); 
			String strFechaIngreso = request.getParameter("fechaIngreso"); 
			String strNotas = request.getParameter("notas"); 
			Part partFileFoto = request.getPart("fileFoto"); 
			InputStream filecontent = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			String strArea = request.getParameter("area");
			String strGafete=request.getParameter("gafete");
			String strFechaGafete=request.getParameter("fechaGafete");
			String strRfc = request.getParameter("rfc"); 
			String strCurp = request.getParameter("curp");
			String strEstadoCivil = request.getParameter("estadoCivil");
			String strEstatura = request.getParameter("estatura");
			String strPeso= request.getParameter("peso");
			
			String strNtrabajador=request.getParameter("Ntrabajador");
			String strfechaNacimiento=request.getParameter("fechaNacimiento");
			String strCamisa=request.getParameter("Camisa");
			String strPlayera=request.getParameter("Playera");
			String strPantalon=request.getParameter("Pantalon");
			String strZapatos=request.getParameter("Zapatos");
			String strReingreso=request.getParameter("reingreso");
			
			
		
		/**	
		 * 	
			System.out.println(strEstado);
			System.out.println("strNtrabajador"+strNtrabajador);
			System.out.println("strfechaNacimiento"+strfechaNacimiento);
			System.out.println("strCamisa"+strCamisa);
			System.out.println("strPlayera"+strPlayera);
			System.out.println("strPantalon"+strPantalon);
			System.out.println("strZapatos"+strZapatos);
			System.out.println("strReingreso"+strReingreso);
			
			System.out.println("strVigenciaLicencia:"+strVigenciaLicencia);
			System.out.println("strNombre:"+strNombre);
			System.out.println("strNoSeguro:"+strNoSeguro);
			System.out.println("strPuesto:"+strPuesto);
			System.out.println("strDireccion:"+strDireccion);
			System.out.println("strTelefono:"+strTelefono);
			System.out.println("strFechaIngreso:"+strFechaIngreso);
			System.out.println("strEstado:"+strEstado);
			System.out.println("strNotas:"+strNotas);
			System.out.println("strArea:"+strArea);
			System.out.println("strRfc:"+strRfc);
			System.out.println("strCurp:"+strCurp);
			System.out.println("strEstadoCivil:"+strEstadoCivil);
			System.out.println("strEstatura:"+strEstatura);
			System.out.println("strPeso:"+strPeso);
			**/
		if(!"".equals(strEstado)&&null!=strEstado
		 &&!"".equals(strNombre)&&null!=strNombre
		 &&!"".equals(strNoSeguro)&&null!=strNoSeguro
		 &&!"".equals(strPuesto)&&null!=strPuesto
		 &&!"".equals(strDireccion)&&null!=strDireccion
		 &&!"".equals(strTelefono)&&null!=strTelefono
		 &&!"".equals(strFechaIngreso)&&null!=strFechaIngreso
		 &&!"".equals(strNotas)&&null!=strNotas
		 &&!"".equals(strArea)&&null!=strArea
		 &&!"".equals(strRfc)&&null!=strRfc
		 &&!"".equals(strCurp)&&null!=strCurp
		 &&!"".equals(strEstadoCivil)&&null!=strEstadoCivil
		 &&!"".equals(strEstatura)&&null!=strEstatura
		 &&!"".equals(strPeso)&&null!=strPeso
		 &&!"".equals(strNtrabajador)&&null!=strNtrabajador
		 &&!"".equals(strfechaNacimiento)&&null!=strfechaNacimiento
		 &&!"".equals(strCamisa)&&null!=strCamisa
		 &&!"".equals(strPlayera)&&null!=strPlayera
		 &&!"".equals(strPantalon)&&null!=strPantalon
		 &&!"".equals(strZapatos)&&null!=strZapatos
		 &&!"".equals(strReingreso)&&null!=strReingreso
		  ) {
			//-----------------VAN AQUI-------------------------------
			

if(null!=partFileFoto) {
				filecontent = partFileFoto.getInputStream();
			 	String partHeader = partFileFoto.getHeader("content-disposition");
				 byte[] buffer = new byte[0xFFFF];
				    for (int len = filecontent.read(buffer); len != -1; len = filecontent.read(buffer)) { 
				        os.write(buffer, 0, len);
				    }
			} /** END if(null!=partFileFoto) { **/
			
			CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
			
			try {
			String strNextNumero = catTrabajadoresDao.findNextNumero(); 
			
			CatTrabajadores catTrabajadores = new CatTrabajadores();
			catTrabajadores.setDireccion(strDireccion);
			catTrabajadores.setEstado(strEstado);
			catTrabajadores.setNombre(strNombre);
			catTrabajadores.setSeguro(strNoSeguro);
			catTrabajadores.setNota(strNotas);
			catTrabajadores.setNumero(Integer.parseInt(strNextNumero));
			catTrabajadores.setPuesto(strPuesto);
			catTrabajadores.setTelefono(strTelefono);
			SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
			java.util.Date utilFechaIngreso = yyyyMMdd.parse(strFechaIngreso); 
			java.sql.Timestamp sqlFechaIngreso = new java.sql.Timestamp(utilFechaIngreso.getTime());
			
//			java.util.Date utilLicencia = yyyyMMdd.parse(strVigenciaLicencia); 
//			java.sql.Timestamp sqlLicencia = new java.sql.Timestamp(utilLicencia.getTime());
			catTrabajadores.setLic(strVigenciaLicencia);
			catTrabajadores.setFecha(new java.sql.Timestamp(new java.util.Date().getTime()));
			catTrabajadores.setFechaingreso(sqlFechaIngreso);/** [FECHAINGRESO] [datetime] NULL,     **/    
			catTrabajadores.setArea(strArea);/** [AREA] [varchar](50) NULL,          **/
			catTrabajadores.setGafete(strGafete);
			catTrabajadores.setFechaGafete(strFechaGafete);
			catTrabajadores.setRfc(strRfc);/** [RFC] [varchar](13) NULL,           **/
			catTrabajadores.setCurp(strCurp);/** [CURP] [varchar](18) NULL,          **/
			catTrabajadores.setEstadocivil(strEstadoCivil);/** [ESTADOCIVIL] [varchar](20) NULL,   **/
			catTrabajadores.setEstatura(Double.parseDouble(strEstatura));/** [ESTATURA] [float] NULL,            **/
			catTrabajadores.setPeso(Double.parseDouble(strPeso));/** [PESO] [float] NULL,                **/
			catTrabajadores.setFechabaja(null);/** [FECHABAJA] [datetime] NULL,        **/
			catTrabajadores.setMotivo(null);/** [MOTIVO] [varchar](50) NULL,        **/
			catTrabajadores.setNEmpleado(strNtrabajador);
			catTrabajadores.setFechaNacimiento(strfechaNacimiento);
			catTrabajadores.setTcamisa(strCamisa);
			catTrabajadores.setTplayera(strPlayera);
			catTrabajadores.setTpantalon(strPantalon);
			catTrabajadores.setTzapatos(strZapatos);
			catTrabajadores.setReingreso(strReingreso);
			catTrabajadores.setLicenciaF(strVigenciaLicenciaF);
			
			
//			catTrabajadores.setLicencia(sqlLicencia);
			if(os.toByteArray().length>0) {
				catTrabajadores.setFoto(os.toByteArray());	
			}
			
//			catTrabajadores.setEstado("ACTIVO");
			
			if(null==catTrabajadoresDao.insert(catTrabajadores)) {
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=TrabajadoresCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			 }
			
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}catch(ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}
			return; 
			
		} /** Valida !"" **/
		else {
			response.getWriter().println("Fallo al validar los parametros1.");
			return; 
		}
	} /** END  if("Guardar".equals(strAccion)) {	**/
	 else if("Baja".equals(strAccion)) {
		String strNoEmpBaja = request.getParameter("noEmpBaja"); 
     	System.out.println("strNoEmpBaja:"+strNoEmpBaja);
     	CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
     	CatTrabajadores catTrabajadores = null; 
     	try {
     		catTrabajadores = catTrabajadoresDao.findByNumero(new BigDecimal(strNoEmpBaja));
     		request.setAttribute("trabajador",catTrabajadores);
			} catch (SQLException e) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
				 return;
			}
     	request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/BajaTrabajador.jsp").forward(request, response);  
		 return; 
	 }else if("Modificar".equals(strAccion)) {
		
		String strNoEmpBuscar = request.getParameter("noEmpBuscar"); 
     	CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
     	CatTrabajadores catTrabajadores = null; 
     	try {
     		catTrabajadores = catTrabajadoresDao.findByNumero(new BigDecimal(strNoEmpBuscar));
     		request.setAttribute("trabajador",catTrabajadores);
			} catch (SQLException e) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
				 return;
			}
     	request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/TrabajadoresMainUpd.jsp").forward(request, response);  
		return;
		    
	 }else if("Regresar".equals(strAccion)) {
			CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance(); /*new CatTrabajadoresDaoImpl();*/
			try {
				List<CatTrabajadores> listCatTrabajadores = catTrabajadoresDao.findAll();
				if(null!=listCatTrabajadores&&listCatTrabajadores.size()>0) {
					request.setAttribute("listTrabajadores", listCatTrabajadores);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO TRABAJADORES");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO TRABAJADORES");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/TrabajadoresMain.jsp").forward(request, response);
			return;
	 }else if("Update".equals(strAccion)) {
		    CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
	     	String strnoEmpBuscar = request.getParameter("noEmpBuscar"); 
		    String strVigenciaLicencia = request.getParameter("vigenciaLicencia");
		    String strVigenciaLicenciaF = request.getParameter("vigenciaLicenciaF");
			String strNombre = request.getParameter("nombre"); 
			String strNoSeguro = request.getParameter("noSeguro");
			String strPuesto = request.getParameter("puesto");
			String strDireccion = request.getParameter("direcccion"); 
			String strTelefono = request.getParameter("telefono"); 
			String strFechaIngreso = request.getParameter("fechaIngreso"); 
		 	String strEstado = request.getParameter("status");
			String strNotas = request.getParameter("notas"); 
			Part partFileFoto = request.getPart("fileFoto"); 
			InputStream filecontent = null;
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			String strArea = request.getParameter("area"); 
			String strGafete=request.getParameter("gafete");
			String strFechaGafete=request.getParameter("fechaGafete");
			String strRfc = request.getParameter("rfc"); 
			String strCurp = request.getParameter("curp");
			String strEstadoCivil = request.getParameter("estadoCivil");
			String strEstatura = request.getParameter("estatura");
			String strPeso= request.getParameter("peso");
			
			String strNtrabajador=request.getParameter("Ntrabajador");
			String strfechaNacimiento=request.getParameter("fechaNacimiento");
			String strCamisa=request.getParameter("Camisa");
			String strPlayera=request.getParameter("Playera");
			String strPantalon=request.getParameter("Pantalon");
			String strZapatos=request.getParameter("Zapatos");
			String strReingreso=request.getParameter("reingreso");
			
			try {
			CatTrabajadores catTrabajadores = new CatTrabajadores();
			catTrabajadores.setDireccion(strDireccion);
			catTrabajadores.setEstado(strEstado);
			catTrabajadores.setNombre(strNombre);
			catTrabajadores.setSeguro(strNoSeguro);
			catTrabajadores.setNota(strNotas);
			catTrabajadores.setNumero(Integer.parseInt(strnoEmpBuscar));
			catTrabajadores.setPuesto(strPuesto);
			catTrabajadores.setTelefono(strTelefono);
			SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
			java.util.Date utilFechaIngreso  = yyyyMMdd.parse(strFechaIngreso);
			java.sql.Timestamp sqlFechaIngreso = new java.sql.Timestamp(utilFechaIngreso.getTime());
			java.util.Date utilLicencia = yyyyMMdd.parse(strVigenciaLicencia); 
			java.sql.Timestamp sqlLicencia = new java.sql.Timestamp(utilLicencia.getTime());
			
			catTrabajadores.setFecha(new java.sql.Timestamp(new java.util.Date().getTime()));
			catTrabajadores.setFechaingreso(sqlFechaIngreso);/** [FECHAINGRESO] [datetime] NULL,     **/    
			catTrabajadores.setArea(strArea);/** [AREA] [varchar](50) NULL,          **/
			catTrabajadores.setGafete(strGafete);
			catTrabajadores.setFechaGafete(strFechaGafete);
			catTrabajadores.setRfc(strRfc);/** [RFC] [varchar](13) NULL,           **/
			catTrabajadores.setCurp(strCurp);/** [CURP] [varchar](18) NULL,          **/
			catTrabajadores.setEstadocivil(strEstadoCivil);/** [ESTADOCIVIL] [varchar](20) NULL,   **/
			catTrabajadores.setEstatura(Double.parseDouble(strEstatura));/** [ESTATURA] [float] NULL,            **/
			catTrabajadores.setPeso(Double.parseDouble(strPeso));/** [PESO] [float] NULL,                **/
			catTrabajadores.setFechabaja(null);/** [FECHABAJA] [datetime] NULL,        **/
			catTrabajadores.setMotivo(null);/** [MOTIVO] [varchar](50) NULL,        **/
			
			catTrabajadores.setNEmpleado(strNtrabajador);
			catTrabajadores.setFechaNacimiento(strfechaNacimiento);
			catTrabajadores.setTcamisa(strCamisa);
			catTrabajadores.setTplayera(strPlayera);
			catTrabajadores.setTpantalon(strPantalon);
			catTrabajadores.setTzapatos(strZapatos);
			catTrabajadores.setReingreso(strReingreso);
			catTrabajadores.setLicenciaF(strVigenciaLicenciaF);
			
			catTrabajadores.setLicencia(sqlLicencia);
			
			if(null!=partFileFoto) {
				filecontent = partFileFoto.getInputStream();
			 	String partHeader = partFileFoto.getHeader("content-disposition");
				 byte[] buffer = new byte[0xFFFF];
				    for (int len = filecontent.read(buffer); len != -1; len = filecontent.read(buffer)) { 
				        os.write(buffer, 0, len);
				    }
			} 
			
			if(os.toByteArray().length>0) {
				catTrabajadores.setFoto(os.toByteArray());	
			}
			
			String strUpdate = catTrabajadoresDao.update(catTrabajadores);
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				List<CatTrabajadores> listCatTrabajadores = catTrabajadoresDao.findAll();
				if(null!=listCatTrabajadores&&listCatTrabajadores.size()>0) {
					request.setAttribute("listTrabajadores", listCatTrabajadores);
				}
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO TRABAJADORES");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO TRABAJADORES");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/TrabajadoresMain.jsp").forward(request, response);
			return;
			
			} catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
	 }else if("Borrar".equals(strAccion)) {
		  CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
	      String strnoEmpBuscar = request.getParameter("noEmpBuscar"); 
	      try {
			String strDelete = catTrabajadoresDao.deleteByNumero(Integer.parseInt(strnoEmpBuscar));
			if(null!=strDelete) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
				return;
			}else {
				List<CatTrabajadores> listCatTrabajadores = catTrabajadoresDao.findAll();
				if(null!=listCatTrabajadores&&listCatTrabajadores.size()>0) {
					request.setAttribute("listTrabajadores", listCatTrabajadores);
				}
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO TRABAJADORES");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO TRABAJADORES");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/TrabajadoresMain.jsp").forward(request, response);
			return;
			
		  } catch (NumberFormatException nfe) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
			return;
		  } catch (SQLException sqle) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			return;
		  }
	 }
	
        
        
        
	 else {
	 response.getWriter().println("Aun sin implementacion");
	 return;
	 }
		
	} /** END protected void doPost **/

}
