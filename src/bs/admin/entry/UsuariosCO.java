package bs.admin.entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.UsuarioDao;
import bs.admin.entry.dto.Usuario;
import bs.util.Utils;

/**
 * Servlet implementation class UsuariosCO
 */
@WebServlet(name="UsuariosCO", urlPatterns= {"/UsuariosCO"})
public class UsuariosCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
	  String straccionUsuarios = request.getParameter("accionUsuarios"); 
	  System.out.println("straccionUsuarios:"+straccionUsuarios);
	  if("Guardar".equals(straccionUsuarios)) {
		  String strusuario = request.getParameter("usuario"); 
		  String strcontrasenia = request.getParameter("contrasenia"); 
		  System.out.println("strusuario:"+strusuario);
		  System.out.println("strcontrasenia:"+strcontrasenia);
		  if((null!=strusuario&&!"".equals(strusuario))
		    &&(null!=strcontrasenia&&!"".equals(strcontrasenia))
		    ) {
			  Usuario usuario = new Usuario(); 
			  usuario.setNombre(strusuario);
			  usuario.setPass(strcontrasenia);
			  
			  UsuarioDao usuariosDao = Utils.getUsuarioInstance();
			  
			  try {
				String strInsert = usuariosDao.insert(usuario);
				if(null!=strInsert) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				}else {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UsuariosCO");
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
	  }else if("Update".equals(straccionUsuarios)) {
		  
		  String strusuario = request.getParameter("usuario"); 
		  String strcontrasenia = request.getParameter("contrasenia"); 
		  String strnombreUsuarioTrx = request.getParameter("nombreUsuarioTrx"); 
		  System.out.println("strusuario:"+strusuario);
		  System.out.println("strcontrasenia:"+strcontrasenia);
		  if((null!=strusuario&&!"".equals(strusuario))
		    &&(null!=strcontrasenia&&!"".equals(strcontrasenia))
		    ) {
			  Usuario usuario = new Usuario(); 
			  usuario.setNombre(strusuario);
			  usuario.setPass(strcontrasenia);
			  
			  UsuarioDao usuariosDao = Utils.getUsuarioInstance();
			  
			  try {
				String strUpdate = usuariosDao.update(strnombreUsuarioTrx,usuario);
				if(null!=strUpdate) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
					return;
				}else {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UsuariosCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}
				
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
		  }	  
		  
	  }else if("Borrar".equals(straccionUsuarios)) {
		  String strnombreUsuarioTrx = request.getParameter("nombreUsuarioTrx"); 
		  UsuarioDao usuariosDao = Utils.getUsuarioInstance();
		  String strDelete;
		  try {
			strDelete = usuariosDao.deleteByNombre(strnombreUsuarioTrx);
			 if(null!=strDelete) {
				    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
			  }else {
				  String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=UsuariosCO");
				  response.sendRedirect( urlWithSessionID );
				  return;
			  }
		  } catch (SQLException sqle) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			return;
		  }
		 
	  }else if("Salir".equals(straccionUsuarios)) {
		  request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
	  }
	  else{
		  response.getWriter().println("Aun sin implementacion.");
		  return;
	  }
	} /** END protected void doPost(HttpServletRequest request, HttpServletResponse response) **/

}
