package bs.vales.combust.upd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.catalogo.dto.CatAutobus;
import bs.util.Utils;

/**
 * Servlet implementation class CatalogarUnidadesCO
 */
@WebServlet(name="CatalogarUnidadesCO", urlPatterns= {"/CatalogarUnidadesCO"})
public class CatalogarUnidadesCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogarUnidadesCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String straccionCatalogarUnidades = request.getParameter("accionCatalogarUnidades"); 
		if("Actualizar".equals(straccionCatalogarUnidades)) {
			
			String strfolio = request.getParameter("folio"); 
			String strunidad = request.getParameter("unidad"); 
			String strcategoria1 = request.getParameter("categoria1"); 
			String strcategoria2 = request.getParameter("categoria2");
			
			CatAutobus catAutobus = new CatAutobus(); 
			catAutobus.setFolio(Integer.parseInt(strfolio));
			catAutobus.setEco(strunidad);
			catAutobus.setCategoria1(strcategoria1);
			catAutobus.setCategoria2(strcategoria2);
			
			try {
				Utils.getCatAutobusInstance().updateCatalogarUnidades(catAutobus);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			try {
				List<CatAutobus> listCatalogarUnidades = Utils.getCatAutobusInstance().findCatalogarUnidades();
			    request.setAttribute("listCatalogarUnidades", listCatalogarUnidades);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/combust/upd/CatalogarUnidades.jsp").include(request, response);  
		    return; 
			
		}else {
		response.getWriter().println("Aun sin implementacion");
		}
	}

}
