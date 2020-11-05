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
 * Servlet implementation class ServicioPreventivoCO
 */
@WebServlet(name="ServicioPreventivoCO", urlPatterns= {"/ServicioPreventivoCO"})
public class ServicioPreventivoCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicioPreventivoCO() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionFormName = request.getParameter("accionFormName"); 
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				response.getWriter().println("Session Invalida");
				return; 
			}
		
		if("Salir".equals(strAccionFormName)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Guardar".equals(strAccionFormName)) {
			String strfecha = request.getParameter("fecha"); 
			String streco = request.getParameter("eco"); 
			String strfechaRealizacion = request.getParameter("fechaRealizacion"); 
			String strkiolometraje = request.getParameter("kiolometraje"); 
			String strTipoServicio = request.getParameter("TipoServicio"); 
			String strdescServicio = request.getParameter("descServicio"); 
			if(!"".equals(strfecha)&&null!=strfecha
			  &&(!"".equals(streco)&&null!=streco)
			  &&(!"".equals(strfechaRealizacion)&&null!=strfechaRealizacion)
			  &&(!"".equals(strkiolometraje)&&null!=strkiolometraje)
			  &&(!"".equals(strTipoServicio)&&null!=strTipoServicio)
		//	  &&(!"".equals(strdescServicio)&&null!=strdescServicio)
			  ) {
				try {
					
				Fallas fallasDto = new Fallas();
				SimpleDateFormat sdf = Utils.getyyyyMMddsdfInstance(); 
				fallasDto.setEco(streco);
				fallasDto.setTipo(strTipoServicio);
				fallasDto.setUsuario(strUsuario);
				fallasDto.setDescripcion(strdescServicio);
				fallasDto.setKilometraje(Double.parseDouble(strkiolometraje));
				java.util.Date utilFecha = sdf.parse(strfecha);
				java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
				fallasDto.setFecha(sqlFecha);
				java.util.Date utilfechaRealizacion = sdf.parse(strfechaRealizacion);
				java.sql.Timestamp sqlfechaRealizacion = new java.sql.Timestamp(utilfechaRealizacion.getTime());
				fallasDto.setFecharep(sqlfechaRealizacion);
				
				FallasDao fallasDao = Utils.getFallasInstance();
				
				String strNextNumero = fallasDao.findNextNumero(); 
				fallasDto.setNumero(Long.parseLong(strNextNumero));
				fallasDto.setEstado("REALIZADO");
				String strInsert = fallasDao.insert(fallasDto);
				if(null!=strInsert) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				}else {
					String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=ServicioPreventivoCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}
				
				} catch (ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				}  catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				
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
			request.getRequestDispatcher("/WEB-INF/bs/fallas/upd/ServicioPreventivoUpd.jsp").forward(request, response);
			return;			 
		}else if("Buscar".equals(strAccionFormName)) {
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
		request.getRequestDispatcher("/WEB-INF/bs/fallas/inquiry/ServicioPreventivoInq.jsp").forward(request, response);
		return;			 
	}else if("Regresar".equals(strAccionFormName)) {
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
		}else if("Update".equals(strAccionFormName)) {
			String strfolioTrx = request.getParameter("folioTrx"); 
			FallasDao fallasDao = Utils.getFallasInstance();
			String streco = request.getParameter("eco"); 
			String strfechaRealizacion = request.getParameter("fechaRealizacion"); 
			String strkiolometraje = request.getParameter("kiolometraje"); 
			String strTipoServicio = request.getParameter("TipoServicio"); 
			String strdescServicio = request.getParameter("descServicio"); 
			
			try {
			Fallas fallasDto = new Fallas();
			SimpleDateFormat sdf = Utils.getyyyyMMddsdfInstance(); 
			fallasDto.setNumero(Long.parseLong(strfolioTrx));
			fallasDto.setEco(streco);
			fallasDto.setTipo(strTipoServicio);
			fallasDto.setDescripcion(strdescServicio);
			java.util.Date utilfechaRealizacion = sdf.parse(strfechaRealizacion);
			java.sql.Timestamp sqlfechaRealizacion = new java.sql.Timestamp(utilfechaRealizacion.getTime());
			fallasDto.setFecharep(sqlfechaRealizacion);
			fallasDto.setKilometraje(Double.parseDouble(strkiolometraje));
		    
			String strUpdate = fallasDao.update(fallasDto); 
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				List<Fallas> listFallas = fallasDao.findRealizadosTop1000();
				if(null!=listFallas&&listFallas.size()>0) {
					request.setAttribute("listFallas",listFallas);
				}
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
			
			} catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}  catch (SQLException sqle) {
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
					List<Fallas> listFallas = fallasDao.findRealizadosTop1000();
					if(null!=listFallas&&listFallas.size()>0) {
						request.setAttribute("listFallas",listFallas);
					}
				}
				request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ServicioPreventivo.jsp").forward(request, response);
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
