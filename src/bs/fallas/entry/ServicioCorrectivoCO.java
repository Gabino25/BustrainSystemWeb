package bs.fallas.entry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.fallas.entry.dao.FallasDao;
import bs.fallas.entry.dto.Fallas;
import bs.util.Utils;

/**
 * Servlet implementation class ServicioCorrectivoCO
 */
@WebServlet("/ServicioCorrectivoCO")
public class ServicioCorrectivoCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicioCorrectivoCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionFormName = request.getParameter("accionFormName"); 
		
		if("Salir".equals(strAccionFormName)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Reporte".equals(strAccionFormName)) {
			String strFolioRO = request.getParameter("folioRO"); 
			
			FallasDao fallasDao = Utils.getFallasInstance();
			try {
				Fallas fallasDto  = fallasDao.findByNumero(strFolioRO); 
				request.setAttribute("fallaByNumero",fallasDto);
				
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ServicioCorrectivoReporte.jsp").forward(request, response);  
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
			request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ServicioCorrectivo.jsp").include(request, response);  
		    return; 
		}else if("Guardar".equals(strAccionFormName)) {
			String strreparacion = request.getParameter("reparacion");
			String strnumeroTrx = request.getParameter("numeroTrx"); 
			FallasDao fallasDao = Utils.getFallasInstance();
			Fallas fallasDto = new Fallas(); 
			fallasDto.setNumero(Long.parseLong(strnumeroTrx));
			fallasDto.setReparacion(strreparacion);
			fallasDto.setEstado("REALIZADO");
			try {
				
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
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			request.getRequestDispatcher("/WEB-INF/bs/fallas/entry/ServicioCorrectivo.jsp").forward(request, response);
			return;		
			
		}
	}

}
