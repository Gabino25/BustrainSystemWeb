package bs.ap.entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.ap.dao.CuentasOperacionDao;
import bs.ap.dto.CuentasOperacion;
import bs.util.Utils;

/**
 * Servlet implementation class CuentasCO
 */
@WebServlet(name="CuentasCO", urlPatterns= {"/CuentasCO"})
public class CuentasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentasCO() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String straccionCuentas = request.getParameter("accionCuentas");
	 if("Salir".equals(straccionCuentas)) {
		  request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		  return; 
	 }else if("Guardar".equals(straccionCuentas)) {
		 String strcuenta = request.getParameter("cuenta"); 
		 String strnombreCuenta = request.getParameter("nombreCuenta");
		 if(null!=strcuenta&&!"".equals(strcuenta)
		  &&(null!=strnombreCuenta&&!"".equals(strnombreCuenta))) {
		    CuentasOperacion cuentasOperacion = new CuentasOperacion();  
		    cuentasOperacion.setCuenta(strcuenta);
		    cuentasOperacion.setNombrecuenta(strnombreCuenta);
		    
		    CuentasOperacionDao cuentasOperacionDao = Utils.getCuentasOperacionInstance();
		    try {
				String strInsert = cuentasOperacionDao.insert(cuentasOperacion);
				if(null!=strInsert) {
					 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					 return;
				}else {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=CuentasCO");
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
		 
	 }else if("Update".equals(straccionCuentas)) {
		 String stridTrx = request.getParameter("idTrx");
		 String strcuenta = request.getParameter("cuenta"); 
		 String strnombreCuenta = request.getParameter("nombreCuenta");
		 CuentasOperacion cuentasOperacion = new CuentasOperacion();  
		 cuentasOperacion.setId(Integer.parseInt(stridTrx));
		 cuentasOperacion.setCuenta(strcuenta);
		 cuentasOperacion.setNombrecuenta(strnombreCuenta);
		 CuentasOperacionDao cuentasOperacionDao = Utils.getCuentasOperacionInstance();
		 try {
			String strUpdate = cuentasOperacionDao.update(cuentasOperacion);
			
			if(null!=strUpdate) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				 return;
			}else {
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=CuentasCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			}
			
		} catch (SQLException sqle) {
			 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			 return;
		} 
		 
	 }else if("Borrar".equals(straccionCuentas)) {
		 String stridTrx = request.getParameter("idTrx");
		 CuentasOperacionDao cuentasOperacionDao = Utils.getCuentasOperacionInstance();
		 try {
			String strDelete = cuentasOperacionDao.deleteById(Integer.parseInt(stridTrx));
			if(null!=strDelete) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
				return;
			}else {
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=CuentasCO");
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
