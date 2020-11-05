package bs.sefaforo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SemaforoTallerCO
 */
@WebServlet(name="SemaforoTallerCO",urlPatterns={"/SemaforoTallerCO"})
public class SemaforoTallerCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SemaforoTallerCO() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario")) {
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Session Invalida.");
				return;
		}
		
		String straccionSemaforoTaller = request.getParameter("accionSemaforoTaller"); 
	   if("Salir".equals(straccionSemaforoTaller)){
		   request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		   return; 
	   }else {
		   response.getWriter().println("Aun sin implemebtacion");
	   }
	}

}
