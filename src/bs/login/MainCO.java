package bs.login;

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
import bs.util.Utils;
import bs.util.dto.Xxbslookups;
import bs.vales.combust.entry.dao.DieselDao;
import bs.vales.combust.entry.dto.Diesel;
import bs.vales.transpt.assign.dao.AsignacionValesDao;
import bs.vales.transpt.assign.dto.AsignacionVales;
import bs.vales.transpt.entry.dao.ValesDao;
import bs.vales.transpt.entry.dto.Vales;

/**
 * Servlet implementation class MainCO
 */
@WebServlet(name="MainCO", urlPatterns= { "/MainCO"})
public class MainCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strAccion = request.getParameter("accion"); 
		
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				request.getRequestDispatcher("/index.jsp").include(request, response); 
//				response.getWriter().println("Fallo de inicio de Sesión. Favor de iniciar Sesión.");
				return; 
			}
		
		if("LicenciaTrabajadores".equals(strAccion)) {
			CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance(); /* new CatTrabajadoresDaoImpl(); */
			
			try {
				List<CatTrabajadores> listCatTrabajadoresDto = catTrabajadoresDao.findLicencTrabaj();
				if(null!=listCatTrabajadoresDto&&listCatTrabajadoresDto.size()>0) {
					request.setAttribute("listLicenciasTrabajadores", listCatTrabajadoresDto);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
				/*sqle.printStackTrace();*/
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/webui/LicenciasTrabajadores.jsp").forward(request, response);
			return;
		}else if("SemaforoTaller".equals(strAccion)) {
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
		}else if("SemaforoAgencia".equals(strAccion)) {
			CatAutobusDao catAutobusDao =	Utils.getCatAutobusInstance(); 
			try {
				List<SemaforoAgencia> listSemaforoAgencia =  catAutobusDao.findAllSemaforoAgencia();
				if(null!=listSemaforoAgencia&&listSemaforoAgencia.size()>0) {
					Iterator<SemaforoAgencia> iterAgencia  = listSemaforoAgencia.iterator();
					while(iterAgencia.hasNext()) {
						SemaforoAgencia semafAgencia = iterAgencia.next();
						if(null!=semafAgencia.getFrecuenciamannto()) {
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
					  }/** END if(null!=semafAgencia.getFrecuenciamannto()) { **/ 	
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
		else if("Salir".equals(strAccion)) {
			request.getRequestDispatcher("/index.jsp").include(request, response);
		}
		 else if("CatalogoClientes".equals(strAccion)) {
			CatClientesDao catClientesDao = Utils.getCatClientesInstance(); /* new CatClientesDaoImpl(); */
			try {
				List<CatClientes> listCatClientesDto = catClientesDao.findAll();
				if(null!=listCatClientesDto&&listCatClientesDto.size()>0) {
					request.setAttribute("listCatClientes", listCatClientesDto);
				}
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
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
		}else if("CatalogoProveedores".equals(strAccion)) {
			CatProveedoresDao catProveedoresDao = Utils.getCatProveedoresInstance(); /*new CatProveedoresDaoImpl();*/
			try {
				List<CatProveedores> listCatProveedores = catProveedoresDao.findAll();
			  if(null!=listCatProveedores&&listCatProveedores.size()>0) {
			  request.setAttribute("listCatProveedores", listCatProveedores);
			  }
			 } catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
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
		}else if("CatalogoTrabajadores".equals(strAccion)) {
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
		}
		else if("Celulares".equals(strAccion)) {
			CatCelularesDao catCelularesDaoImpl = Utils.getCatCelularesInstance(); 
			try {
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
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO CELULARES");
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
		}else if("CatalogoUnidades".equals(strAccion)) {
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
			
			try {
				List<Xxbslookups> listXxbslookups =  Utils.getXxbslookupsInstance().findWhereNameEquals("TIPO UNIDADES");
				request.getSession().setAttribute("selListXxbslookups",listXxbslookups);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/UnidadesMain.jsp").forward(request, response);
			return;
		}else if("CatalogoRutas".equals(strAccion)) {
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
		}else if("CapturaValesTransporte".equals(strAccion)){
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
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/entry/EntryTransporteVales.jsp").forward(request, response);
			return;
		}else if("ListadoBoletas".equals(strAccion)) {	
			ValesDao valesDao = Utils.getValesInstance();
			try {
			List<Vales> listVales =  valesDao.findByFechaDif1();
			if(null!=listVales&&listVales.size()>0) {
				request.setAttribute("listVales", listVales);
			}
			}catch(SQLException sqle){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/inquiry/ListadoBoletas.jsp").forward(request, response);
			return;
		}else if("AnalizarCombustible".equals(strAccion)) {
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
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/combust/inquiry/AnalizarCombustible.jsp").forward(request, response);
			return;		
			
		}
		else if("CargaCombustible".equals(strAccion)){	
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
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/combust/entry/EntryCombustibleVales.jsp").forward(request, response);
			return;		
		}else if("CalculaRendimientos".equals(strAccion)) {
			
			DieselDao dieselDao = Utils.getDieselInstance(); 
			try {
				
				String strCalculaRendimientos = dieselDao.calculaRendimientos();
				if(null!=strCalculaRendimientos) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strCalculaRendimientos);
					return;
				}
				List<Diesel> listDiesel = dieselDao.findRendimientosCalculados();
				if(null!=listDiesel&&listDiesel.size()>0) {
					request.setAttribute("listDiesel",listDiesel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/combust/inquiry/RendimientosCalculados.jsp").forward(request, response);
			return;		
		}
		else if("ServicioPreventivo".equals(strAccion)){	
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
		}
		else if("ServicioCorrectivo".equals(strAccion)){	
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
			
			request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ServicioCorrectivo.jsp").forward(request, response);
			return;		
		}
		else if("ReporteDeFallas".equals(strAccion)){	
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
		}
		else if("CapturarFacturas".equals(strAccion)) {
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
		}
		else if("FiltradoFacturas".equals(strAccion)) {
			request.getRequestDispatcher("/WEB-INF/bs/ap/inquiry/FiltradoFacturas.jsp").forward(request, response);
			return;
		}else if("CapturaCuentas".equals(strAccion)) {
			
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
		else if("AsignacionValesTransporte".equals(strAccion)) {
			AsignacionValesDao asignacionValesDaoImpl = Utils.getAsignacionValesInstance(); /* new AsignacionValesDaoImpl(); */
			try {
				List<AsignacionVales> listAsignacionVales = asignacionValesDaoImpl.findAll();
				if(null!=listAsignacionVales&&listAsignacionVales.size()>0) {
					request.setAttribute("listAsignacionVales", listAsignacionVales);
				}
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/assign/TransporteAssignMain.jsp").forward(request, response);
			return;
		}else if("AdministrarLlantas".equals(strAccion)) {
			LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
			try {
			 List<Llantas> listLlantas	= llantasDaoImpl.findAll();
			 if(null!=listLlantas&&listLlantas.size()>0) {
				 request.setAttribute("listLlantas", listLlantas);
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
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/AdministrarLlantas.jsp").forward(request, response);
			return;
		}else if("AsignacionLlantas".equals(strAccion)) {
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
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS ASIGNACION");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS ASIGNACION");
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
		}else if("RevisionProfundidaLlantas".equals(strAccion)) {
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
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/RevisionProfundidadLlantas.jsp").forward(request, response);
			return;
		}else if("ReparacionesLlantas".equals(strAccion)) {
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
		}else if("AnalizarLlantas".equals(strAccion)) {
			MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance(); 
			try {
			List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAll(); 
			if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
				request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
			}
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/inquiry/AnalizarLlantas.jsp").forward(request, response);
			return;
		}
		else if("BajasLlantas".equals(strAccion)) {
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
		}
		else if("Usuarios".equals(strAccion)) {
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
		}else if("UsuariosPermisos".equals(strAccion)) {
			
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
			
			List<Pantallas> listPantallas = getPantallas(); 
			request.setAttribute("listPantallas",listPantallas);
			
			PermisosDao  permisosDao = Utils.getPermisosInstance();
			
			try {
				List<Permisos> listPermisos = permisosDao.findAll();
				if(null!=listPermisos&&listPermisos.size()>0) {
					request.setAttribute("listPermisosAll", listPermisos);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/admin/entry/UsuariosPermisos.jsp").forward(request, response);
			return;
		}
		else if("AnalizarFallas".equals(strAccion))
		{
			try {
				List<Fallas> listFallasTop1000 = Utils.getFallasInstance().findTop1000();
				request.setAttribute("listFallasTop1000", listFallasTop1000);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/fallas/inquiry/AnalizarFallas.jsp").forward(request, response);
			return;
		}
		else if("Celulares".equals(strAccion))
		{
//			try {
//				List<Fallas> listFallasTop1000 = Utils.getFallasInstance().findTop1000();
//				request.setAttribute("listFallasTop1000", listFallasTop1000);
//			} catch (SQLException sqle) {
//				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
//				return;
//			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/CelularesTrabajadores.jsp").forward(request, response);
			return;
		}
		else
		{
			response.getWriter().println("Accion aun sin implementación");
		}
		
		
	
	} /** END protected void doGet **/

	private List<Pantallas> getPantallas() {
		return Utils.getPantallas();
	}

}
