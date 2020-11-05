package bs.vales.transpt.assign;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.util.Utils;
import bs.vales.transpt.assign.dao.AsignacionValesDao;
import bs.vales.transpt.assign.dto.AsignacionVales;
import bs.vales.transpt.assign.dto.AsignacionValesPk;

/**
 * Servlet implementation class CombMainCO
 */
@WebServlet(name="ValesAssignMainCO", urlPatterns= {"/ValesAssignMainCO"})
public class ValesAssignMainCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValesAssignMainCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strAccion = request.getParameter("formAssignAccion"); 
		String strFolios = request.getParameter("idFolios"); 
		
	 
	    System.out.println("strAccion:"+strAccion);
	    
	    if(null==strAccion||"".equals(strAccion)) {
	    	response.getWriter().println("Accion no definida!");
	    	return;
	    }
	    
		if("Salir".equals(strAccion)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Guardar".equals(strAccion)) {	
		  String strOperador = request.getParameter("operador"); 
		  String strFolioInicial = request.getParameter("folioInicial"); 
		  String strFolioFinal = request.getParameter("folioFinal"); 
			
		if( !"".equals(strOperador)
		  &&!"".equals(strFolioInicial)
		  &&!"".equals(strFolioFinal)) {
			
		  	AsignacionValesDao asignacionValesDaoImpl = Utils.getAsignacionValesInstance();
		  	AsignacionVales asignacionVales = new  AsignacionVales();
		  	try {
		  	String strNextNumero = asignacionValesDaoImpl.findNextNumero(); 
		  	asignacionVales.setNumero(Long.parseLong(strNextNumero));
		  	asignacionVales.setNombre(strOperador);
		  	asignacionVales.setFolioinicial(Long.parseLong(strFolioInicial));
		  	asignacionVales.setFoliofinal(Long.parseLong(strFolioFinal));
		    java.util.Date utilFecha = new java.util.Date(); 
		    java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
		  	asignacionVales.setFecha(sqlFecha);
		  	
		  	if(null==asignacionValesDaoImpl.insert(asignacionVales)) {
		  	 	String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=CombMainCO");
				response.sendRedirect( urlWithSessionID );
				return;	
		  	}
		  	
		  	} catch (SQLException sqle) {
		  		
		  			 if(sqle.toString().contains("asignvales_folioini_uc")) {
		  				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Folio Inicial:"+strFolioInicial+" Duplicado no se realiza transaccion.");
						return;
		  			 }
		  			 else if(sqle.toString().contains("asignvales_foliofin_uc")) {
			  				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Folio Final:"+strFolioFinal+" Duplicado no se realiza transaccion.");
							return;
			  			 }
		  			 else {
		  				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
						 return;
		  			 }	
			}
		  
		}
	  }	/** END if("Guardar".equals(strAccion)) {	 **/
		else  if("Eliminar".equals(strAccion)) {
		  AsignacionValesDao asignacionValesDaoImpl = Utils.getAsignacionValesInstance();
		  System.out.println("strFolios:"+strFolios);
		  AsignacionValesPk asignacionValesPk = new AsignacionValesPk(Long.parseLong(strFolios));
		  try {
			asignacionValesDaoImpl.delete(asignacionValesPk);
		} catch (SQLException sqle) {
			 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			 return;
		}
		  
		  try {
		  List<AsignacionVales> listAsignacionVales = asignacionValesDaoImpl.findAll();
			if(null!=listAsignacionVales&&listAsignacionVales.size()>0) {
				request.setAttribute("listAsignacionVales", listAsignacionVales);
			}
			} catch (SQLException sqle) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				 return;
			}
		  
		  request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/assign/TransporteAssignMain.jsp").forward(request, response);
	  } /** END  if("Elimninar".equals(strAccion)) { **/
		else if("validaFolioInicial".equals(strAccion)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			String strpFolioInicial = request.getParameter("pFolioInicial"); 
			 AsignacionValesDao asignacionValesDaoImpl = Utils.getAsignacionValesInstance();
			 try {
				String strValFolioInicial = asignacionValesDaoImpl.findByFolioInicial(Long.parseLong(strpFolioInicial));
				if(null==strValFolioInicial) {
					out.print("");
				}else {
					out.print("Ya existe Folio Inicial");
				}
				out.close();
				return;
			} catch (NumberFormatException nfe) {
				out.print("EXCEPTION:"+nfe.toString());
				out.close();
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
		}else if("validaFolioFinal".equals(strAccion)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			 String strpFolioFinal = request.getParameter("pFolioFinal"); 
			 AsignacionValesDao asignacionValesDaoImpl = Utils.getAsignacionValesInstance();
			 try {
				String strValFolioFinal = asignacionValesDaoImpl.findByFolioFinal(Long.parseLong(strpFolioFinal));
				if(null==strValFolioFinal) {
					out.print("");
				}else {
					out.print("Ya existe Folio Final");
				}
				out.close();
				return;
			} catch (NumberFormatException nfe) {
				out.print("EXCEPTION:"+nfe.toString());
				out.close();
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
		}else {
			response.getWriter().println("Aun sin implementacion.");
		}
		
	}

}
