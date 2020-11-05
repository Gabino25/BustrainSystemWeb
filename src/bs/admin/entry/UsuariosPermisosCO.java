package bs.admin.entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.admin.entry.dto.Permisos;
import bs.util.Utils;

/**
 * Servlet implementation class UsuariosPermisosCO
 */
@WebServlet(name="UsuariosPermisosCO" ,urlPatterns= {"/UsuariosPermisosCO"})
public class UsuariosPermisosCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosPermisosCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String straccionUsuariosPermisos = request.getParameter("accionUsuariosPermisos"); 
		
		if("Guardar".equals(straccionUsuariosPermisos)) {
			
			String strusuario = request.getParameter("usuario"); 
			String strpantalla = request.getParameter("pantalla"); 
			String strpermiso = request.getParameter("permiso"); 
			String strnivel = request.getParameter("nivel"); 
			
			if(null!=strusuario&&!"".equals(strusuario)
			  &&(null!=strpantalla&&!"".equals(strpantalla))
			  &&(null!=strpermiso&&!"".equals(strpermiso))
			  &&(null!=strnivel&&!"".equals(strnivel))
			  ) {
				
				PermisosDao permisosDao = Utils.getPermisosInstance();
				Permisos permisos = new Permisos(); 
				permisos.setUsuario(strusuario);
				permisos.setPantalla(strpantalla);
				permisos.setPermiso(strpermiso);
				permisos.setNivel(strnivel);
				
				try {
					String strInsert = permisosDao.insert(permisos);
					if(null!=strInsert) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
						return;
					}else {
						 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UsuariosPermisosCO");
						 response.sendRedirect( urlWithSessionID );
						 return;
					}
					
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar los parametros.");
				return;
			}
		}else if("Salir".equals(straccionUsuariosPermisos)) {
			  request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
			  return; 
		 }else if("Update".equals(straccionUsuariosPermisos)) {
			 
			 String stridTrx = request.getParameter("idTrx");
			 String strusuario = request.getParameter("usuario"); 
			 String strpantalla = request.getParameter("pantalla"); 
			 String strpermiso = request.getParameter("permiso"); 
		     String strnivel = request.getParameter("nivel");
			 
		 	if(null!=strusuario&&!"".equals(strusuario)
					  &&(null!=strpantalla&&!"".equals(strpantalla))
					  &&(null!=strpermiso&&!"".equals(strpermiso))
					  &&(null!=strnivel&&!"".equals(strnivel))
					  ) {
		     
			 Permisos permisos = new Permisos();
			 permisos.setId(Integer.parseInt(stridTrx));
			 permisos.setUsuario(strusuario);
			 permisos.setPantalla(strpantalla);
			 permisos.setPermiso(strpermiso);
			 permisos.setNivel(strnivel);
			 PermisosDao permisosDao = Utils.getPermisosInstance();
			 try {
				String strUpdate = permisosDao.update(permisos);
				if(null!=strUpdate) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
					return;
				}else {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UsuariosPermisosCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			 
		 	}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar los parametros.");
				return;
			} 
			 
		 }else if("Borrar".equals(straccionUsuariosPermisos)) {
			 String stridTrx = request.getParameter("idTrx");
			 PermisosDao permisosDao = Utils.getPermisosInstance();
			 try {
				String strDelete = permisosDao.deleteById(Integer.parseInt(stridTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UsuariosPermisosCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}
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
			response.getWriter().println("Aun sin implementacion.");
		}
	
	}

}
