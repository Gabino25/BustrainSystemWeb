package bs.catalogo;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dto.CatTrabajadores;
import bs.util.Utils;

/**
 * Servlet implementation class BajaTrabajadorCO
 */
@WebServlet(name="BajaTrabajadorCO",urlPatterns= {"/BajaTrabajadorCO"})
public class BajaTrabajadorCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaTrabajadorCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionName = request.getParameter("accionName"); 
		if("Cancelar".equals(strAccionName)) {
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
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/TrabajadoresMain.jsp").forward(request, response);
			return;
		}else if("Actualizar".equals(strAccionName)) {
			String strNumero = request.getParameter("numero"); 
			String strFechaBaja = request.getParameter("fechaBaja"); 
			String strMotivoBaja = request.getParameter("motivoBaja"); 
			CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
			try {
				java.util.Date utilFechaBaja = Utils.getyyyyMMddsdfInstance().parse(strFechaBaja);
				java.sql.Timestamp sqlFechaBaja = new java.sql.Timestamp(utilFechaBaja.getTime());
				String strUpdate = catTrabajadoresDao.updateBaja(Integer.parseInt(strNumero)
						                     ,sqlFechaBaja
						                     ,strMotivoBaja
						                     );
				if(null==strUpdate) {
				 	CatTrabajadores catTrabajadores = null; 
			     	try {
			     		catTrabajadores = catTrabajadoresDao.findByNumero(new BigDecimal(strNumero));
			     		request.setAttribute("trabajador",catTrabajadores);
						} catch (SQLException e) {
							 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
							 return;
						}
			     	request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/BajaTrabajador.jsp").forward(request, response);  
					return; 
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
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
		}else {
		response.getWriter().println("Aun sin implementacion.");
		}
	}

}
