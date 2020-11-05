package bs.fallas.entry;

import java.io.IOException;
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
import bs.fallas.entry.dao.FallasDao;
import bs.fallas.entry.dto.Fallas;
import bs.util.Utils;

/**
 * Servlet implementation class ReporteDeFallasCO
 */
@WebServlet(name="ReporteDeFallasCO", urlPatterns= {"/ReporteDeFallasCO"})
public class ReporteDeFallasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteDeFallasCO() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				response.getWriter().println("Session Invalida");
				return; 
			}
		
		String strAccionFormName = request.getParameter("accionFormName"); 
		
		if("Salir".equals(strAccionFormName)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Guardar".equals(strAccionFormName)) {
			String streco = request.getParameter("eco");
			String strreporta = request.getParameter("reporta");
			String strfecha = request.getParameter("fecha");
			String strkilometraje = request.getParameter("kilometraje");
			String strhora  = request.getParameter("hora");
			String strdescFalla = request.getParameter("descFalla");
			if(!"".equals(streco)&&null!=streco
			  &&(!"".equals(strreporta)&&null!=strreporta)
			  &&(!"".equals(strfecha)&&null!=strfecha)
			  &&(!"".equals(strkilometraje)&&null!=strkilometraje)
			  &&(!"".equals(strhora)&&null!=strhora)
			  &&(!"".equals(strdescFalla)&&null!=strdescFalla)
			  ){
				try {
				FallasDao fallasDao = Utils.getFallasInstance(); 
				Fallas fallasDto = new Fallas(); 
				String strNumero = fallasDao.findNextNumero();
				fallasDto.setNumero(Long.parseLong(strNumero));
				fallasDto.setEco(streco);
				fallasDto.setReporta(strreporta);
				
				SimpleDateFormat sdf = Utils.getyyyyMMddsdfInstance(); 
				java.util.Date utilFecha;
				utilFecha = sdf.parse(strfecha);
				java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
				fallasDto.setFecha(sqlFecha);
				
				
				SimpleDateFormat sdf1 = Utils.gethhmmsdfInstance(); 
				java.util.Date utilhora;
				utilhora = sdf1.parse(strhora);
				java.sql.Timestamp sqlhora = new java.sql.Timestamp(utilhora.getTime());
				fallasDto.setHora(sqlhora);
				
				fallasDto.setKilometraje(Double.parseDouble(strkilometraje));
				fallasDto.setEstado("PENDIENTE");
				fallasDto.setDescripcion(strdescFalla);
				fallasDto.setUsuario(strUsuario);
				
				String strInsert = fallasDao.insert(fallasDto); 
				if(null!=strInsert) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				}else {
					String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=ReporteDeFallasCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}
				
				}catch (ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				}catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				}
				
			}else {
				response.getWriter().println("Fallo al validar los paramteros.");
				return; 
			}
		}else if("Modificar".equals(strAccionFormName)) {
			 FallasDao fallasDao = 	Utils.getFallasInstance();
			 String strfolioTrx = request.getParameter("folioTrx"); 
			 try {
				Fallas fallasDto = fallasDao.findByNumero(strfolioTrx);
				request.setAttribute("falla", fallasDto);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			request.getRequestDispatcher("/WEB-INF/bs/fallas/upd/ReporteDeFallasUpd.jsp").forward(request, response);
			return;			 
		}
		else if("Buscar".equals(strAccionFormName)) {
			 FallasDao fallasDao = 	Utils.getFallasInstance();
			 String strfolioTrx = request.getParameter("folioTrx"); 
			 try {
				Fallas fallasDto = fallasDao.findByNumero(strfolioTrx);
				request.setAttribute("falla", fallasDto);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			request.getRequestDispatcher("/WEB-INF/bs/fallas/inquiry/ReporteDeFallasInq.jsp").forward(request, response);
			return;			 
		}else if("Regresar".equals(strAccionFormName)) {
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
		}else if("Update".equals(strAccionFormName)) {
			String strfolioTrx = request.getParameter("folioTrx"); 
			FallasDao fallasDao = Utils.getFallasInstance();
			String streco = request.getParameter("eco");
			String strreporta = request.getParameter("reporta");
			String strkilometraje = request.getParameter("kilometraje");
			String strdescFalla = request.getParameter("descFalla");
			
			try {
			Fallas fallasDto = new Fallas();
			fallasDto.setNumero(Long.parseLong(strfolioTrx));
			fallasDto.setEco(streco);
			fallasDto.setDescripcion(strdescFalla);
			fallasDto.setReporta(strreporta);
			fallasDto.setKilometraje(Double.parseDouble(strkilometraje));
		    
			String strUpdate = fallasDao.update(fallasDto); 
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				List<Fallas> listFallas = fallasDao.findPendientes();
				if(null!=listFallas&&listFallas.size()>0) {
					request.setAttribute("listFallas",listFallas);
				}
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
			
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
		
		}else if("Borrar".equals(strAccionFormName)) {
			String strfolioTrx = request.getParameter("folioTrx"); 
			FallasDao fallasDao = Utils.getFallasInstance();
			try {
				String strDelete = fallasDao.deleteByNumero(strfolioTrx);
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					List<Fallas> listFallas = fallasDao.findPendientes();
					if(null!=listFallas&&listFallas.size()>0) {
						request.setAttribute("listFallas",listFallas);
					}
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
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
		}else if("CapturaDesdeSemaforoTaller".equals(strAccionFormName)) {
			String strunidad = request.getParameter("unidad");
			String strfechaCaptura = request.getParameter("fechaCaptura");
			String strkilometrajeCaptura = request.getParameter("kilometrajeCaptura");
			String strdescripcionCaptura = request.getParameter("descripcionCaptura");
			try {
			FallasDao fallasDao = Utils.getFallasInstance(); 
			Fallas fallasDto = new Fallas(); 
			String strNumero = fallasDao.findNextNumero();
			fallasDto.setNumero(Long.parseLong(strNumero));
			fallasDto.setEco(strunidad);
			fallasDto.setReporta(null);
			SimpleDateFormat sdf = Utils.getyyyyMMddsdfInstance(); 
			java.util.Date utilFechaRep;
			java.util.Date utilFechaSysdate = new java.util.Date();
			utilFechaRep = sdf.parse(strfechaCaptura);
			java.sql.Timestamp sqlFechaRep = new java.sql.Timestamp(utilFechaRep.getTime());
			java.sql.Timestamp sqlFechaSysdate = new java.sql.Timestamp(utilFechaSysdate.getTime());
			fallasDto.setFecha(sqlFechaSysdate);
			fallasDto.setFecharep(sqlFechaRep);
			fallasDto.setKilometraje(Double.parseDouble(strkilometrajeCaptura));
			fallasDto.setEstado("PENDIENTE");
			fallasDto.setDescripcion(strdescripcionCaptura);
			fallasDto.setUsuario(strUsuario);
			
			String strInsert = fallasDao.insert(fallasDto); 
			
			if(null!=strInsert) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
				return;
			}else {
				 Utils.getCatAutobusInstance().updateFallas(fallasDto.getEco()
						                                   ,fallasDto.getFecharep()
						                                   ,fallasDto.getKilometraje()
						                                   ,fallasDto.getDescripcion()
						                                   );
				
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=SemaforoTallerCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			}
			
			}catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
		}
		else if("CapturaDesdeSemaforoAgencia".equals(strAccionFormName)) {
			String strunidad = request.getParameter("unidad");
			String strfechaCaptura = request.getParameter("fechaCaptura");
			String strkilometrajeCaptura = request.getParameter("kilometrajeCaptura");
			String strdescripcionCaptura = request.getParameter("descripcionCaptura");
			try {
			FallasDao fallasDao = Utils.getFallasInstance(); 
			Fallas fallasDto = new Fallas(); 
			String strNumero = fallasDao.findNextNumero();
			fallasDto.setNumero(Long.parseLong(strNumero));
			fallasDto.setEco(strunidad);
			fallasDto.setReporta(null);
			SimpleDateFormat sdf = Utils.getyyyyMMddsdfInstance(); 
			java.util.Date utilFecha;
			utilFecha = sdf.parse(strfechaCaptura);
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
			fallasDto.setFecha(sqlFecha);
			fallasDto.setKilometraje(Double.parseDouble(strkilometrajeCaptura));
			fallasDto.setEstado("PENDIENTE");
			fallasDto.setDescripcion(strdescripcionCaptura);
			fallasDto.setUsuario(strUsuario);
			
			String strInsert = fallasDao.insert(fallasDto); 
			
			if(null!=strInsert) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
				return;
			}else {
				String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=SemaforoAgenciaCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			}
			
			}catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
		}
		else {
			response.getWriter().println("Aun sin implementacion.");
			return; 
		}
		
	}

}
