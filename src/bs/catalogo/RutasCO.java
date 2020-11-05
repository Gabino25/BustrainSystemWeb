package bs.catalogo;

import java.io.IOException;
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
import bs.catalogo.dao.CatRutasDao;
import bs.catalogo.dto.CatRutas;
import bs.util.Utils;

/**
 * Servlet implementation class RutasCO
 */
@WebServlet(name="RutasCO", urlPatterns= {"/RutasCO"})
public class RutasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RutasCO() {
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
				request.getRequestDispatcher("/index.jsp").include(request, response);
//				response.getWriter().println("Fallo de inicio de Sesión. Favor de iniciar Sesión.");
				return; 
			}
		
		// TODO Auto-generated method stub
		String strAccionName = request.getParameter("accionName");
		
		if("Guardar".equals(strAccionName)) {
			
			String strEmpresa      = request.getParameter("empresa");   
			String strClave        = request.getParameter("clave");          
			String strNombreRuta   = request.getParameter("nombreRuta");     
			String strCosto        = request.getParameter("costo");          
			String strFecha        = request.getParameter("fecha");          
			String strTipoServicio = request.getParameter("tipoServicio");  
			String strTipoPago     = request.getParameter("tipoPago");       
			String strDescripcion  = request.getParameter("descripcion");
			
			if(!"".equals(strEmpresa)&&null!=strEmpresa
			  &&!"".equals(strClave)&&null!=strClave
			  &&!"".equals(strNombreRuta)&&null!=strNombreRuta
			  &&!"".equals(strCosto)&&null!=strCosto
			  &&!"".equals(strFecha)&&null!=strFecha
			  &&!"".equals(strTipoServicio)&&null!=strTipoServicio
			  &&!"".equals(strTipoPago)&&null!=strTipoPago
//			  &&!"".equals(strDescripcion)&&null!=strDescripcion
			  ) {
				
				try {
					CatRutasDao catRutasDao = Utils.getCatRutasInstance();
					CatRutas catRutasDto = new CatRutas(); 
					String strNextNumero = catRutasDao.findNextNumero();
					catRutasDto.setNumero(Integer.parseInt(strNextNumero));
					catRutasDto.setClave(strClave);
					catRutasDto.setCliente(strEmpresa);
					catRutasDto.setRuta(strNombreRuta);
					catRutasDto.setCosto(Float.parseFloat(strCosto));
					catRutasDto.setDescripcion(strDescripcion);
					catRutasDto.setTipounidad(strTipoServicio);
					catRutasDto.setTipopago(strTipoPago);
					catRutasDto.setEstado(new BigDecimal(0));
					
					
					
					SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
					java.util.Date utilfecha = yyyyMMdd.parse(strFecha); 
					java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilfecha.getTime());
					catRutasDto.setFecha(sqlFecha);
					catRutasDto.setHorainicio(null);
					catRutasDto.setHorafin(null);
					
					String strInsert = catRutasDao.insert(catRutasDto); 
					
					if(null==strInsert) {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=RutasCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				    }else {
				    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
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
				
			}

			
		}else if("Salir".equals(strAccionName)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}
		else if("Buscar".equals(strAccionName)) {
			CatRutasDao catRutasDao = Utils.getCatRutasInstance();
			String strnumeroRutaTrx = request.getParameter("numeroRutaTrx");
			try {
				CatRutas catRutas = catRutasDao.findByNumero(Integer.parseInt(strnumeroRutaTrx));
				request.setAttribute("Ruta", catRutas);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/inquiry/RutasMainRO.jsp").forward(request, response);
			return;
		}else if("Regresar".equals(strAccionName)) {
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
		}else if("Modificar".equals(strAccionName)) {
			CatRutasDao catRutasDao = Utils.getCatRutasInstance();
			String strnumeroRutaTrx = request.getParameter("numeroRutaTrx");
			try {
				CatRutas catRutas = catRutasDao.findByNumero(Integer.parseInt(strnumeroRutaTrx));
				request.setAttribute("Ruta", catRutas);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/RutasMainUpd.jsp").forward(request, response);
			return;
		}else if("Update".equals(strAccionName)) {
			
			String strnumeroRutaTrx = request.getParameter("numeroRutaTrx");
			String strEmpresa      = request.getParameter("empresa");   
			String strClave        = request.getParameter("clave");          
			String strNombreRuta   = request.getParameter("nombreRuta");     
			String strCosto        = request.getParameter("costo");          
			String strFecha        = request.getParameter("fecha");          
			String strTipoServicio = request.getParameter("tipoServicio");  
			String strTipoPago     = request.getParameter("tipoPago");       
			String strDescripcion  = request.getParameter("descripcion");
			
			try {
			CatRutasDao catRutasDao = Utils.getCatRutasInstance();
			CatRutas catRutasDto = new CatRutas(); 
			catRutasDto.setNumero(Integer.parseInt(strnumeroRutaTrx));
			catRutasDto.setClave(strClave);
			catRutasDto.setCliente(strEmpresa);
			catRutasDto.setRuta(strNombreRuta);
			catRutasDto.setCosto(Float.parseFloat(strCosto));
			catRutasDto.setDescripcion(strDescripcion);
			catRutasDto.setTipounidad(strTipoServicio);
			catRutasDto.setTipopago(strTipoPago);
			catRutasDto.setEstado(new BigDecimal(0));
			java.util.Date utilfecha = Utils.getyyyyMMddsdfInstance().parse(strFecha);
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilfecha.getTime());
			catRutasDto.setFecha(sqlFecha);
			catRutasDto.setHorainicio(null);
			catRutasDto.setHorafin(null);
			String strUpdate = catRutasDao.update(catRutasDto);
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=RutasCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
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
			
		}else if("Borrar".equals(strAccionName)){
			String strnumeroRutaTrx = request.getParameter("numeroRutaTrx");
			CatRutasDao catRutasDao = Utils.getCatRutasInstance();
			try {
				String strDelete = catRutasDao.deleteByNumero(Integer.parseInt(strnumeroRutaTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					List<CatRutas> listCatRutas = catRutasDao.findAll();
					if(null!=listCatRutas&&listCatRutas.size()>0) {
						request.setAttribute("listRutas", listCatRutas);
					}	
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
		}
	}

}
