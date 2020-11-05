package bs.catalogo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dto.CatTrabajadores;
import bs.util.Utils;

/**
 * Servlet implementation class UniformesTrabajadorCO
 */
@WebServlet(name="/UniformesTrabajadorCO", urlPatterns= {"/TrabajadorCO"})
public class UniformesTrabajadorCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UniformesTrabajadorCO() {
        super();
        // TODO Auto-generated constructor stub
    }
//------------------------------ESTE ARCHIVO ESTA SIN USAR-------------SE CAMBIO CODIGO A TrabajadoresCO-----------------------------------
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				
				response.getWriter().println("Fallo de inicio de Sesión. Favor de iniciar Sesión EDICION TABAJADOR.");
				return; 
			}
		
		String strAccionName = request.getParameter("accionName"); 
		if("Regresar".equals(strAccionName)) {
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
		}else {
			response.getWriter().println("Aun sin implmentacion");
		}
	}

}
