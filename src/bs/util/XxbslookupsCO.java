package bs.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dto.CatAutobus;
import bs.util.dao.XxbslookupsDao;
import bs.util.dto.Xxbslookups;

/**
 * Servlet implementation class XxbslookupsCO
 */
@WebServlet(name="XxbslookupsCO",urlPatterns= {"/XxbslookupsCO"})
public class XxbslookupsCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XxbslookupsCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String strUsuario = (String) request.getSession().getAttribute("sAttrNombreUsuario");
    	
    	if(null==strUsuario||"".equals(strUsuario)) {
    		response.getWriter().println("Session Invalida");
    		return;
    	}
		
		// TODO Auto-generated method stub
         String straccionXxbslookups = request.getParameter("accionXxbslookups"); 
         if("Guardar".equals(straccionXxbslookups)) {
        	 String strNombre = request.getParameter("Nombre"); 
        	 String strCodigo = request.getParameter("Codigo"); 
        	 Xxbslookups xxbslookups = new Xxbslookups();
        	 xxbslookups.setName(strNombre);
        	 xxbslookups.setCode(strCodigo);
        	 XxbslookupsDao xxbslookupsdao = Utils.getXxbslookupsInstance();
        	 try {
				String strInsert = xxbslookupsdao.insert(xxbslookups);
				if(null!=strInsert) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				}else {
					strNombre = strNombre.replaceAll(" ", "_");
					String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=XxbslookupsCO&rAttrNameLookup="+strNombre);
					response.sendRedirect( urlWithSessionID );
					return;
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
        	 
         }else if("Borrar".equals(straccionXxbslookups)){
        	 String stridTrx = request.getParameter("idTrx");
        	 String strnameROTrx = request.getParameter("nameROTrx"); 
        	 try {
				String strDelete = Utils.getXxbslookupsInstance().delete(Integer.parseInt(stridTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					strnameROTrx = strnameROTrx.replaceAll(" ", "_");
					String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=XxbslookupsCO&rAttrNameLookup="+strnameROTrx);
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
         }else if("Salir".equals(straccionXxbslookups)){
        	 
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
        	 
         }else if("Update".equals(straccionXxbslookups)){
        	 String stridTrx = request.getParameter("idTrx"); 
        	 String strNombre = request.getParameter("Nombre"); 
        	 String strCodigo = request.getParameter("Codigo"); 
        	 Xxbslookups xxbslookups = new Xxbslookups();
        	 xxbslookups.setId(Integer.parseInt(stridTrx));
        	 xxbslookups.setName(strNombre);
        	 xxbslookups.setCode(strCodigo);
        	 XxbslookupsDao xxbslookupsdao = Utils.getXxbslookupsInstance();
        	 
        	 try {
				String strUpdate = xxbslookupsdao.update(xxbslookups);
				if(null!=strUpdate) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
					return;
				}else {
					strNombre = strNombre.replaceAll(" ", "_");
					String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=XxbslookupsCO&rAttrNameLookup="+strNombre);
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
			 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Aun sin implementacion.");
		 	return;
         }
	}

}
