package bs.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.admin.entry.dao.UsuarioDao;
import bs.admin.entry.dto.Pantallas;
import bs.admin.entry.dto.Permisos;
import bs.admin.entry.dto.Usuario;
import bs.ap.dao.CuentasOperacionDao;
import bs.ap.dao.FacturasDao;
import bs.ap.dto.CuentasOperacion;
import bs.ap.dto.Facturas;
import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dao.CatCelularesDao;
import bs.catalogo.dao.CatClientesDao;
import bs.catalogo.dao.CatProveedoresDao;
import bs.catalogo.dao.CatRutasDao;
import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dto.CatAutobus;
import bs.catalogo.dto.CatCelulares;
import bs.catalogo.dto.CatClientes;
import bs.catalogo.dto.CatProveedores;
import bs.catalogo.dto.CatRutas;
import bs.catalogo.dto.CatTrabajadores;
import bs.fallas.entry.dao.FallasDao;
import bs.fallas.entry.dto.Fallas;
import bs.llantas.entry.dao.LlantasDao;
import bs.llantas.entry.dao.MovimientosLlantasDao;
import bs.llantas.entry.dto.Llantas;
import bs.llantas.entry.dto.MovimientosLlantas;
import bs.sefaforo.dto.SemaforoAgencia;
import bs.sefaforo.dto.SemaforoTaller;
import bs.util.dto.Xxbslookups;
import bs.vales.combust.entry.dao.DieselDao;
import bs.vales.combust.entry.dto.Diesel;
import bs.vales.transpt.assign.dao.AsignacionValesDao;
import bs.vales.transpt.assign.dto.AsignacionVales;
import bs.vales.transpt.entry.dao.ValesDao;
import bs.vales.transpt.entry.dto.Vales;

/**
 * Servlet implementation class UtilsCO
 */
@WebServlet("/UtilsCO")
public class UtilsCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilsCO() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("doGet");
    	
    	System.out.println("SessionId:"+request.getSession().getId());
    	
    	String strUsuario = (String) request.getSession().getAttribute("sAttrNombreUsuario");
    	
    	if(null==strUsuario||"".equals(strUsuario)) {
    		request.getRequestDispatcher("/index.jsp").include(request, response); 
//    		response.getWriter().println("Session Invalida");
    		return;
    	}
    	
    	
    	String strUtilAccion = (String) request.getParameter("utilAccion");
 	    String strUtilCO = (String) request.getParameter("utilCO");
 	
 	    
 	    System.out.println("strUtilAccion:"+strUtilAccion);
 	    System.out.println("strUtilCO:"+strUtilCO);
 	
 		if("PRG".equals(strUtilAccion)) {
 		if("SemaforoTallerCO".equals(strUtilCO)) {
 			CatAutobusDao catAutobusDao =	Utils.getCatAutobusInstance(); 
			try {
				List<SemaforoTaller> listSemaforoTaller =  catAutobusDao.findAllSemaforoTaller();
				if(null!=listSemaforoTaller&&listSemaforoTaller.size()>0) {
					Iterator<SemaforoTaller> iterTaller  = listSemaforoTaller.iterator();
					while(iterTaller.hasNext()) {
						SemaforoTaller semafTaller = iterTaller.next();
						if(semafTaller.getKm_recorridos()>semafTaller.getFrecuenciamannto().floatValue()) {
							semafTaller.setCategoriafiltro("xxbsbg-danger");
						}else if((semafTaller.getFrecuenciamannto().floatValue()-semafTaller.getKm_recorridos())<1000) {
							semafTaller.setCategoriafiltro("xxbsbg-warning");
						}
						else {
						    if(semafTaller.getMeses_recorridos()>=6) {
						    	semafTaller.setCategoriafiltro("xxbsbg-primary");
						    }else if(semafTaller.getMeses_recorridos()>=5&&semafTaller.getMeses_recorridos()<6) {
						    	semafTaller.setCategoriafiltro("xxbsbg-pink");
						    }
						    else {	
							semafTaller.setCategoriafiltro("xxbsbg-success");
						    }
						}
					}
					request.setAttribute("listSemaforoTaller", listSemaforoTaller);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"No se pudo recuperar la informacion de Semaforo Taller.");
					return;
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			request.getRequestDispatcher("/WEB-INF/bs/webui/SemaforoTaller.jsp").forward(request, response);
			return;
 		}else if("SemaforoAgenciaCO".equals(strUtilCO)) {
 			CatAutobusDao catAutobusDao =	Utils.getCatAutobusInstance(); 
			try {
				List<SemaforoAgencia> listSemaforoAgencia =  catAutobusDao.findAllSemaforoAgencia();
				if(null!=listSemaforoAgencia&&listSemaforoAgencia.size()>0) {
					Iterator<SemaforoAgencia> iterAgencia  = listSemaforoAgencia.iterator();
					while(iterAgencia.hasNext()) {
						SemaforoAgencia semafAgencia = iterAgencia.next();
						if(semafAgencia.getKm_recorridos()>semafAgencia.getFrecuenciamannto().floatValue()) {
							semafAgencia.setCategoriafiltro("xxbsbg-danger");
						}else if((semafAgencia.getFrecuenciamannto().floatValue()-semafAgencia.getKm_recorridos())<1000) {
							semafAgencia.setCategoriafiltro("xxbsbg-warning");
						}
						else {
						    if(semafAgencia.getMeses_recorridos()>=6) {
						    	semafAgencia.setCategoriafiltro("xxbsbg-primary");
						    }else if(semafAgencia.getMeses_recorridos()>=5&&semafAgencia.getMeses_recorridos()<6) {
						    	semafAgencia.setCategoriafiltro("xxbsbg-pink");
						    }
						    else {	
						    	semafAgencia.setCategoriafiltro("xxbsbg-success");
						    }
						}
					}
					request.setAttribute("listSemaforoAgencia", listSemaforoAgencia);
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"No se pudo recuperar la informacion de Semaforo Taller.");
					return;
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			request.getRequestDispatcher("/WEB-INF/bs/webui/SemaforoAgencia.jsp").forward(request, response);
			return;
 		}
 		else if("ProveedoresCO".equals(strUtilCO)) {
 				CatProveedoresDao catProveedoresDao = Utils.getCatProveedoresInstance();
 				try {
 					 List<CatProveedores> listCatProveedores = catProveedoresDao.findAll();
					  if(null!=listCatProveedores&&listCatProveedores.size()>0) {
					  request.setAttribute("listCatProveedores", listCatProveedores);
					  }
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
 				}
 			
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO PROVEEDORES");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO PROVEEDORES");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 		    		
 			 request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/ProveedoresMain.jsp").forward(request, response);
 			 return;
 			}else if("ClientesCO".equals(strUtilCO)) {
 				CatClientesDao catClientesDao = Utils.getCatClientesInstance();
 				try {
 				List<CatClientes> listCatClientes = catClientesDao.findAll(); 
				if(null!=listCatClientes&&listCatClientes.size()>0) {
					request.setAttribute("listCatClientes", listCatClientes);
				}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO CLIENTES");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO CLIENTES");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 	
 				
 			  request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/ClientesMain.jsp").forward(request, response); 
 			 return;
 			}else if("TrabajadoresCO".equals(strUtilCO)) {
 				try {
 				CatTrabajadoresDao catTrabajadoresDaoImpl = Utils.getCatTrabajadoresInstance(); 
				List<CatTrabajadores> listCatTrabajadores = catTrabajadoresDaoImpl.findAll();
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
 			}
 			else if("CelularesTrabajadoresCO".equals(strUtilCO)) {
 				try {
 				CatCelularesDao catCelularesDaoImpl = Utils.getCatCelularesInstance(); 
				List<CatCelulares> list = catCelularesDaoImpl.findAll();
				if(null!=list&&list.size()>0) {
					request.setAttribute("list", list);
				}
				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
 				}
 				
 			 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO CELULARES");
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
 				
 				request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/CelularesTrabajadores.jsp").forward(request, response);  
	 		      return;  
 			}else if("UnidadesCO".equals(strUtilCO)) {
 	    		CatAutobusDao  catAutobusDao = Utils.getCatAutobusInstance();
 	    		List<CatAutobus> listCatAutobus;
 				try {
 					listCatAutobus = catAutobusDao.findAll();
 					if(null!=listCatAutobus&&listCatAutobus.size()>0) {
 						request.setAttribute("listAutobus", listCatAutobus);
 					}
 				} catch (SQLException sqle) {
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
 			
 				/*http://www.javapractices.com/topic/TopicAction.do?Id=181*/
 		 	      request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UnidadesMain.jsp").forward(request, response);	
 		 	    return;
 	    	}else if("RutasCO".equals(strUtilCO)) {
 	    		CatRutasDao catRutasDao = Utils.getCatRutasInstance(); 
 				try {
 					List<CatRutas> listCatRutas = catRutasDao.findAll();
 					if(null!=listCatRutas&&listCatRutas.size()>0) {
 						request.setAttribute("listRutas", listCatRutas);
 					}
 				}catch(SQLException sqle){
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO RUTAS");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO RUTAS");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/RutasMain.jsp").forward(request, response);
 				return;
 	    	} /** END if("UnidadesCO".equals(strUtilCO)) { **/
 	    	else if("EntryTransporteValesCO".equals(strUtilCO)) {
 	    		ValesDao valesDao = Utils.getValesInstance();
 				try {
 				List<Vales> listVales =  valesDao.findDif0();
 				if(null!=listVales&&listVales.size()>0) {
 					request.setAttribute("listVales", listVales);
 				}
 				}catch(SQLException sqle){
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"VALES CAPTURA");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla VALES CAPTURA");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				
 				request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/entry/EntryTransporteVales.jsp").forward(request, response);
 				return;
 	    	}
 	    	else if("EntryCombustibleCO".equals(strUtilCO)) {
 	    		
 	    		DieselDao dieselDao = Utils.getDieselInstance(); 
 				try {
 					List<Diesel> listDiesel = dieselDao.findAll();
 					if(null!=listDiesel&&listDiesel.size()>0) {
 						request.setAttribute("listDiesel",listDiesel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				request.getRequestDispatcher("/WEB-INF/bs/vales/combust/entry/EntryCombustibleVales.jsp").forward(request, response);
 				return;		
 	    	}
 	    	else if("CombMainCO".equals(strUtilCO)) {
 	    		AsignacionValesDao asignacionValesDaoImpl = Utils.getAsignacionValesInstance();
 	    		try {
 	    		List<AsignacionVales> listAsignacionVales = asignacionValesDaoImpl.findAll();
				if(null!=listAsignacionVales&&listAsignacionVales.size()>0) {
					request.setAttribute("listAsignacionVales", listAsignacionVales);
				}
 	    		} catch (SQLException sqle) {
 	    			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
 				}
 	    		/*http://www.javapractices.com/topic/TopicAction.do?Id=181*/
 			  	request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/assign/TransporteAssignMain.jsp").forward(request, response);
 			   return;
 	    	} /** END else if("CombMainCO".equals(strUtilCO)) { **/
 	    	else if("ServicioPreventivoCO".equals(strUtilCO)) {
 	    		FallasDao fallasDao = Utils.getFallasInstance();
 				try {
 					List<Fallas> listFallas = fallasDao.findRealizadosTop1000();
 					if(null!=listFallas&&listFallas.size()>0) {
 						request.setAttribute("listFallas",listFallas);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"SERVICIO PREVENTIVO");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla SERVICIO PREVENTIVO");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ServicioPreventivo.jsp").forward(request, response);
 				return;		
 	    	}else if("ReporteDeFallasCO".equals(strUtilCO)) {
 	    		FallasDao fallasDao = Utils.getFallasInstance();
 				try {
 					List<Fallas> listFallas = fallasDao.findPendientes();
 					if(null!=listFallas&&listFallas.size()>0) {
 						request.setAttribute("listFallas",listFallas);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"SERVICIO REPORTE DE FALLAS");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla SERVICIO REPORTE DE FALLAS");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ReporteDeFallas.jsp").forward(request, response);
 				return;		
 	    	}else if("FacturasCO".equals(strUtilCO)) {
 	    		FacturasDao facturasDao = Utils.getFacturasInstance();
 				try {
 					List<Facturas> listfacturas = facturasDao.findTop1000();
 					if(null!=listfacturas&&listfacturas.size()>0) {
 						request.setAttribute("listFacturas", listfacturas);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"COMPRAS CAPTURAR FACTURAS");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla COMPRAS CAPTURAR FACTURAS");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.getRequestDispatcher("/WEB-INF/bs/ap/entry/Facturas.jsp").forward(request, response);
 				return;
 	    	}else if("CuentasCO".equals(strUtilCO)) {
 	    		
 	    		CuentasOperacionDao cuentasOperacionDao = Utils.getCuentasOperacionInstance();
 				try {
 					List<CuentasOperacion> listCuentasOperacion = cuentasOperacionDao.findAll();
 					if(null!=listCuentasOperacion&&listCuentasOperacion.size()>0) {
 					  request.setAttribute("listCuentasOperacionAll", listCuentasOperacion);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				request.getRequestDispatcher("/WEB-INF/bs/ap/entry/Cuentas.jsp").forward(request, response);
 				return;
 				
 	    	}
 	    	else if("AdministrarLlantasCO".equals(strUtilCO)) {
 	    		LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
 				try {
 				 List<Llantas> listLlantas	= llantasDaoImpl.findAll();
 				 if(null!=listLlantas&&listLlantas.size()>0) {
 					 request.setAttribute("listLlantas", listLlantas);
 				 }
 				 
 				 List<Llantas> selectListLlantas = llantasDaoImpl.findSelectList();
 					if(null!=selectListLlantas&&selectListLlantas.size()>0) {
 						request.getSession().removeAttribute("selectListLlantas");
 						request.getSession().setAttribute("selectListLlantas", selectListLlantas);
 					}
 				 
 				} catch (SQLException sqle) {
 					/* TODO Auto-generated catch block sqle.printStackTrace(); */
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS ADMINISTRAR");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS ADMINISTRAR");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/AdministrarLlantas.jsp").forward(request, response);
 				return;
 	    	}else if("AsignacionLlantasCO".equals(strUtilCO)) {
 	    		MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance(); 
 				try {
 				List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllAssignUnidad(); 
 				if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
 					request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
 				}
 				} catch (SQLException sqle) {
 					/* TODO Auto-generated catch block sqle.printStackTrace(); */
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS ASIGNACION");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS ADMINISTRAR");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/AsignacionLlantas.jsp").forward(request, response);
 				return;
 			}else if("RevisionProfundidadLlantasCO".equals(strUtilCO)) {
 				MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance(); 
 				try {
 				List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllRevDeProf(); 
 				if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
 					request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
 				}
 				} catch (SQLException sqle) {
 					/* TODO Auto-generated catch block sqle.printStackTrace(); */
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS PROFUNDIDAD");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS PROFUNDIDAD");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/RevisionProfundidadLlantas.jsp").forward(request, response);
 				return;
 	    	}else if("ReparacionesLlantasCO".equals(strUtilCO)) {
 	    		MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance(); 
 				try {
 				List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllRepLlantas(); 
 				if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
 					request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
 				}
 				} catch (SQLException sqle) {
 					/* TODO Auto-generated catch block sqle.printStackTrace(); */
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS REPARACIONES");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS REPARACIONES");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/ReparacionesLlantas.jsp").forward(request, response);
 				return;
 	    	}else if("BajasLlantasCO".equals(strUtilCO)) {
 	    		MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance(); 
 				try {
 				List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllBajaUnidad(); 
 				if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
 					request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
 				}
 				} catch (SQLException sqle) {
 					/* TODO Auto-generated catch block sqle.printStackTrace(); */
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				request.setAttribute("reqAttrOtherTransaction","Y");
 				
 				PermisosDao permisosDao = Utils.getPermisosInstance(); 
 				try {
 					String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS BAJAS");
 					if(null==strNivel) {
 						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS BAJAS");
 						return;
 					}else {
 					  request.setAttribute("rAttrNivel", strNivel);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				} 
 				
 				request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/BajasLlantas.jsp").forward(request, response);
 				return;
 	    	}else if("UsuariosCO".equals(strUtilCO)) {
 	    		UsuarioDao usuarioDao = Utils.getUsuarioInstance();
 				try {
 					List<Usuario> listUsuario = usuarioDao.findAll();
 					if(null!=listUsuario&&listUsuario.size()>0) {
 					request.setAttribute("listUsuario",listUsuario);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				request.getRequestDispatcher("/WEB-INF/bs/admin/entry/Usuarios.jsp").forward(request, response);
 				return;
 	    	}else if("UsuariosPermisosCO".equals(strUtilCO)) {
 	    	
 	    		UsuarioDao usuarioDao = Utils.getUsuarioInstance();
 				try {
 					List<Usuario> listUsuario = usuarioDao.findAll();
 					if(null!=listUsuario&&listUsuario.size()>0) {
 					request.setAttribute("listUsuario",listUsuario);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				List<Pantallas> listPantallas = Utils.getPantallas(); 
 				request.setAttribute("listPantallas",listPantallas);
 				
 				PermisosDao  permisosDao = Utils.getPermisosInstance();
 				
 				try {
 					List<Permisos> listPermisosAll = permisosDao.findAll();
 					if(null!=listPermisosAll&&listPermisosAll.size()>0) {
 						request.setAttribute("listPermisosAll", listPermisosAll);
 					}
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 				
 				request.getRequestDispatcher("/WEB-INF/bs/admin/entry/UsuariosPermisos.jsp").forward(request, response);
 				return;
 				
 	    	}else if("XxbslookupsCO".equals(strUtilCO)) {
 	    		String strrAttrNameLookup = request.getParameter("rAttrNameLookup"); 
 	    		strrAttrNameLookup = strrAttrNameLookup.replaceAll("_", " ");
 	    		request.setAttribute("rAttrNameLookup", strrAttrNameLookup);
 	    		
 	    		try {
 					List<Xxbslookups> listXxbslookups =  Utils.getXxbslookupsInstance().findWhereNameEquals(strrAttrNameLookup);
 					request.setAttribute("listXxbslookups",listXxbslookups);
 					
 					request.getSession().removeAttribute("selListXxbslookups");
 					request.getSession().setAttribute("selListXxbslookups", listXxbslookups);
 					
 				} catch (SQLException sqle) {
 					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
 					return;
 				}
 	    		
 				request.getRequestDispatcher("/WEB-INF/commons/xxbslookups.jsp").forward(request, response);
 				return;
 	    	}
 			
 	    } /** END if("PRG".equals(strUtilAccion)) { **/
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		doGet(request,response);
	}
	

}
