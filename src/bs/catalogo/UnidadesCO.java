package bs.catalogo;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dto.CatAutobus;
import bs.util.Utils;
import bs.util.dto.Xxbslookups;



/**
 * Servlet implementation class UnidadesCO
 */
@WebServlet(name="UnidadesCO",urlPatterns= {"/UnidadesCO"})
public class UnidadesCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnidadesCO() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionName = request.getParameter("accionName");
		
		String strValidaRecargaPagina = (String)request.getAttribute("ValidaRecargasPagina");
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario")) {
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Session Invalida.");
				return;
			}
		
		if("Guardar".equals(strAccionName)) {
			
			String strEco = request.getParameter("Eco");
			String strSerie = request.getParameter("Serie");
			String strMotor = request.getParameter("Motor");
			String strCarroceria = request.getParameter("Carroceria");
			String strModelo = request.getParameter("Modelo");
			String strEmpresa = request.getParameter("Empresa"); 
			String strTipo = request.getParameter("Tipo"); 
			String strCombustible = request.getParameter("Combustible");
			String strUltimoServicio = request.getParameter("UltimoServicio"); 
			//String strKmhrs = request.getParameter("Kmhrs");
			String strAseguradora = request.getParameter("Aseguradora");
			String strFrecuencia = request.getParameter("Frecuencia");
			String strPoliza = request.getParameter("Poliza"); 
			String strVence =  request.getParameter("Vence");
			String strEstado = request.getParameter("Estado");
			/** Campos Nuevos **/
			String strPasajeros = request.getParameter("Pasajeros");
			String strPlacas = request.getParameter("Placas"); 
			String strConcesion = request.getParameter("Concesion"); 
			String strClaveVehicular = request.getParameter("ClaveVehicular"); 
			String strVigUltimoReferendo = request.getParameter("VigUltimoReferendo"); 
			String strAgencia = request.getParameter("Agencia"); 
			String strUnidad = request.getParameter("Unidad"); 
			String strAsegBroker = request.getParameter("Broker"); 
			String strInicio = request.getParameter("Inicio"); 
			String strEndoso = request.getParameter("Endoso"); 
			String strCobertura = request.getParameter("Cobertura"); 
			String strHospital = request.getParameter("Hospital"); 
			String strAsegServicio = request.getParameter("Servicio"); 
			String strNota = request.getParameter("nota");
			
			if(!"".equals(strEco)&&null!=strEco
			 &&!"".equals(strSerie)&&null!=strSerie
			 &&!"".equals(strMotor)&&null!=strMotor
			 &&!"".equals(strCarroceria)&&null!=strCarroceria
			 &&!"".equals(strModelo)&&null!=strModelo
			 &&!"".equals(strEmpresa)&&null!=strEmpresa
			 &&!"".equals(strTipo)&&null!=strTipo
			 &&!"".equals(strCombustible)&&null!=strCombustible
//			 &&!"".equals(strUltimoServicio)&&null!=strUltimoServicio
//			 &&!"".equals(strAseguradora)&&null!=strAseguradora
//			 &&!"".equals(strFrecuencia)&&null!=strFrecuencia
			 &&!"".equals(strPoliza)&&null!=strPoliza
			 &&!"".equals(strVence)&&null!=strVence
			 &&!"".equals(strEstado)&&null!=strEstado
			 ) {
				CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance(); 
			    CatAutobus catAutobusDto = new CatAutobus(); 
			    
			    try {
			    
			    String strNextFolio = catAutobusDao.findNextFolio(); 
			    catAutobusDto.setFolio(Integer.parseInt(strNextFolio));
			    catAutobusDto.setAseguradora(strAseguradora);
			    catAutobusDto.setCarroceria(strCarroceria);
			    catAutobusDto.setCombustible(strCombustible);
			    catAutobusDto.setEco(strEco);
			    catAutobusDto.setEmpresa(strEmpresa);
			    catAutobusDto.setEstado(strEstado);
			    catAutobusDto.setFrecuenciamantto(new BigDecimal(strFrecuencia)); 
			   // catAutobusDto.setKmhrs(strKmhrs);
			    catAutobusDto.setModelo(strModelo);
			    catAutobusDto.setMotor(strMotor);
			    catAutobusDto.setPoliza(strPoliza);
			    catAutobusDto.setSerie(strSerie);
			    catAutobusDto.setTipo(strTipo);
			    catAutobusDto.setNota(strNota);
				
			    SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
				java.util.Date utilServicio = yyyyMMdd.parse(strUltimoServicio); 
				java.sql.Timestamp sqlServicio = new java.sql.Timestamp(utilServicio.getTime());
				java.util.Date utilVence = yyyyMMdd.parse(strVence); 
				java.sql.Timestamp sqlVence = new java.sql.Timestamp(utilVence.getTime());
				java.util.Date utilFechaalta = new java.util.Date();
				java.sql.Timestamp sqlFechaalta = new  java.sql.Timestamp(utilFechaalta.getTime());
				
				catAutobusDto.setServicio(sqlServicio);
				catAutobusDto.setVence(sqlVence);
				catAutobusDto.setFechaalta(sqlFechaalta);
				
				catAutobusDto.setUsuario(strUsuario);
				
				/******** Parametros Nuevos ******************/
				catAutobusDto.setPasajeros(Integer.parseInt(strPasajeros));
				catAutobusDto.setPlacas(strPlacas);
				catAutobusDto.setConcesion(strConcesion);
				catAutobusDto.setClavevehicular(strClaveVehicular);
				java.util.Date utilVigUltimoReferendo = yyyyMMdd.parse(strVigUltimoReferendo);
				java.sql.Timestamp sqlVigUltimoReferendo = new java.sql.Timestamp(utilVigUltimoReferendo.getTime());
				catAutobusDto.setVigultimoreferendo(sqlVigUltimoReferendo);
				catAutobusDto.setAgencia(Integer.parseInt(strAgencia));
				catAutobusDto.setTipounidad(strUnidad);
				catAutobusDto.setAsegbroker(strAsegBroker);
				if("".equals(strInicio)) {
					strInicio = "0";
				}
				catAutobusDto.setInicio(Integer.parseInt(strInicio));
				if("".equals(strEndoso)) {
					strEndoso = "0";
				}
				catAutobusDto.setEndoso(Integer.parseInt(strEndoso));
				catAutobusDto.setCobertura(strCobertura);
				catAutobusDto.setHospital(strHospital);
				catAutobusDto.setAsegservicio(strAsegServicio);
				
				
				catAutobusDao.insert(catAutobusDto);
					    
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				}catch(ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				}
				
				System.out.println("SessionId:"+request.getSession().getId());
				
				
				String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UnidadesCO");
				response.sendRedirect( urlWithSessionID );
				return;
				 
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar los parametros.");
				return;
			}
		}/** if("Guardar".equals(strAccionName)) { **/
		else if("obtieneCombustible".equals(strAccionName)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			String strpUnidadValue = request.getParameter("pUnidadValue"); 
			CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance();
			try {
				String strCombustible = catAutobusDao.findCombustible(strpUnidadValue);
				out.print(strCombustible);
				out.close();
				return; 
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
			return; 
		}
		else if("Salir".equals(strAccionName)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}
		else if("Buscar".equals(strAccionName)) {
			String strfolioUnidadTrx = request.getParameter("folioUnidadTrx"); 
			CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance();
			try {
				CatAutobus catAutobus = catAutobusDao.findByFolio(Integer.parseInt(strfolioUnidadTrx));
				request.setAttribute("Autobus", catAutobus);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/inquiry/UnidadesMainRO.jsp").forward(request, response); 
		    return;
		}else if("Regresar".equals(strAccionName)) {
            CatAutobusDao  catAutobusDao = Utils.getCatAutobusInstance(); /*new CatAutobusDaoImpl();*/
			
			try {
				List<CatAutobus> listCatAutobus = catAutobusDao.findAll();
				if(null!=listCatAutobus&&listCatAutobus.size()>0) {
					request.setAttribute("listAutobus", listCatAutobus);
				}
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO UNIDADES");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO UNIDADES");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UnidadesMain.jsp").forward(request, response);
			return;
		}
		else if("Modificar".equals(strAccionName)) {
			String strfolioUnidadTrx = request.getParameter("folioUnidadTrx"); 
			CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance();
			try {
				CatAutobus catAutobus = catAutobusDao.findByFolio(Integer.parseInt(strfolioUnidadTrx));
				request.setAttribute("Autobus", catAutobus);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/UnidadesMainUpd.jsp").forward(request, response); 
		    return;
		}
		else if("Update".equals(strAccionName)) {
			
			CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance();
			
			String strfolioUnidadTrx = request.getParameter("folioUnidadTrx"); 
			String strEco = request.getParameter("Eco");
			String strSerie = request.getParameter("Serie");
			String strMotor = request.getParameter("Motor");
			String strCarroceria = request.getParameter("Carroceria");
			String strModelo = request.getParameter("Modelo");
			String strEmpresa = request.getParameter("Empresa"); 
			String strTipo = request.getParameter("Tipo"); 
			String strCombustible = request.getParameter("Combustible");
			String strUltimoServicio = request.getParameter("UltimoServicio"); 
			//String strKmhrs = request.getParameter("Kmhrs");
			String strAseguradora = request.getParameter("Aseguradora");
			String strFrecuencia = request.getParameter("Frecuencia");
			String strPoliza = request.getParameter("Poliza"); 
			String strVence =  request.getParameter("Vence");
			String strEstado = request.getParameter("Estado");
			
			/*** Nuevos Parametros **/
			String strPasajeros = request.getParameter("Pasajeros");
			String strPlacas = request.getParameter("Placas"); 
			String strConcesion = request.getParameter("Concesion"); 
			String strClaveVehicular = request.getParameter("ClaveVehicular"); 
			String strVigUltimoReferendo = request.getParameter("VigUltimoReferendo"); 
			String strAgencia = request.getParameter("Agencia"); 
			String strUnidad = request.getParameter("Unidad"); 
			String strAsegBroker = request.getParameter("Broker"); 
			String strInicio = request.getParameter("Inicio"); 
			String strEndoso = request.getParameter("Endoso"); 
			String strCobertura = request.getParameter("Cobertura"); 
			String strHospital = request.getParameter("Hospital"); 
			String strAsegServicio = request.getParameter("Servicio"); 
			String strNota = request.getParameter("nota");
			
			try {
				
			CatAutobus catAutobusDto = new CatAutobus(); 
			
			catAutobusDto.setFolio(Integer.parseInt(strfolioUnidadTrx));
		    catAutobusDto.setAseguradora(strAseguradora);
		    catAutobusDto.setCarroceria(strCarroceria);
		    catAutobusDto.setCombustible(strCombustible);
		    catAutobusDto.setEco(strEco);
		    catAutobusDto.setEmpresa(strEmpresa);
		    catAutobusDto.setEstado(strEstado);
		    catAutobusDto.setFrecuenciamantto(new BigDecimal(strFrecuencia)); 
		    catAutobusDto.setModelo(strModelo);
		    catAutobusDto.setMotor(strMotor);
		    catAutobusDto.setPoliza(strPoliza);
		    catAutobusDto.setNota(strNota);
		    catAutobusDto.setSerie(strSerie);
		    catAutobusDto.setTipo(strTipo);
		    java.util.Date utilServicio =  Utils.getyyyyMMddsdfInstance().parse(strUltimoServicio);
			java.sql.Timestamp sqlServicio = new java.sql.Timestamp(utilServicio.getTime());
			java.util.Date utilVence = Utils.getyyyyMMddsdfInstance().parse(strVence); 
			java.sql.Timestamp sqlVence = new java.sql.Timestamp(utilVence.getTime());
			catAutobusDto.setServicio(sqlServicio);
			catAutobusDto.setVence(sqlVence);
			
			/******** Parametros Nuevos ******************/
			catAutobusDto.setPasajeros(Integer.parseInt(strPasajeros));
			catAutobusDto.setPlacas(strPlacas);
			catAutobusDto.setConcesion(strConcesion);
			
			catAutobusDto.setClavevehicular(strClaveVehicular);
			java.util.Date utilVigUltimoReferendo =  Utils.getyyyyMMddsdfInstance().parse(strVigUltimoReferendo);
			java.sql.Timestamp sqlVigUltimoReferendo = new java.sql.Timestamp(utilVigUltimoReferendo.getTime());
			catAutobusDto.setVigultimoreferendo(sqlVigUltimoReferendo);
			if(null==strAgencia||"".equals(strAgencia)) {
				strAgencia = "2";
			}
			catAutobusDto.setAgencia(Integer.parseInt(strAgencia));
			catAutobusDto.setTipounidad(strUnidad);
			catAutobusDto.setAsegbroker(strAsegBroker);
			if("".equals(strInicio)) {
				strInicio = "0";
			}
			catAutobusDto.setInicio(Integer.parseInt(strInicio));
			if("".equals(strEndoso)) {
				strEndoso = "0";
			}
			catAutobusDto.setEndoso(Integer.parseInt(strEndoso));
			catAutobusDto.setCobertura(strCobertura);
			catAutobusDto.setHospital(strHospital);
			catAutobusDto.setAsegservicio(strAsegServicio);
			
			
			
			String strUpdate = catAutobusDao.update(catAutobusDto);
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				List<CatAutobus> listCatAutobus = catAutobusDao.findAll();
				if(null!=listCatAutobus&&listCatAutobus.size()>0) {
					request.setAttribute("listAutobus", listCatAutobus);
				}
			}
			
			} catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UnidadesCO");
			response.sendRedirect( urlWithSessionID );
			return;
			/**
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UnidadesMain.jsp").forward(request, response);
			return;
			**/
			
		}else if("Borrar".equals(strAccionName)) {
            CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance();
			String strfolioUnidadTrx = request.getParameter("folioUnidadTrx"); 
			try {
				String strDelete = catAutobusDao.deleteByFolio(Integer.parseInt(strfolioUnidadTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					List<CatAutobus> listCatAutobus = catAutobusDao.findAll();
					if(null!=listCatAutobus&&listCatAutobus.size()>0) {
						request.setAttribute("listAutobus", listCatAutobus);
					}
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO UNIDADES");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO UNIDADES");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UnidadesMain.jsp").forward(request, response);
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
		}else if("AgregarUnidades".equals(strAccionName)) {
			request.setAttribute("rAttrNameLookup", "TIPO UNIDADES");
			
			try {
				List<Xxbslookups> listXxbslookups =  Utils.getXxbslookupsInstance().findWhereNameEquals("TIPO UNIDADES");
				request.setAttribute("listXxbslookups",listXxbslookups);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/commons/xxbslookups.jsp").forward(request, response);
			return;
		}
		else {
			response.getWriter().println("Aun sin implementacion.");
			return; 
		}
		
		
		
	}/** End Method get **/

}
