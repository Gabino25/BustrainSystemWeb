package bs.vales.transpt.inquiry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.util.Utils;
import bs.vales.transpt.entry.dao.ValesDao;
import bs.vales.transpt.entry.dto.Vales;

/**
 * Servlet implementation class ListadoBoletasCO
 */
@WebServlet(name="ListadoBoletasCO", urlPatterns={"/ListadoBoletasCO"})
public class ListadoBoletasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoBoletasCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		// TODO Auto-generated method stub
		String strAccionListadoBoletas = request.getParameter("accionListadoBoletas");
   
        if("filtraPorFechas".equals(strAccionListadoBoletas)) {
        	 
    		String jsFechaDesdeDay = request.getParameter("jsFechaDesdeDay"); 
    		String jsFechaDesdeMonth = request.getParameter("jsFechaDesdeMonth"); 
    		String jsFechaDesdeFullYear = request.getParameter("jsFechaDesdeFullYear"); 
    		String jsFechaHastaDay = request.getParameter("jsFechaHastaDay"); 
    		String jsFechaHastaMonth = request.getParameter("jsFechaHastaMonth"); 
    		String jsFechaHastaFullYear = request.getParameter("jsFechaHastaFullYear"); 
    		String jsEmpresa = request.getParameter("jsEmpresa");
    		String jsRuta = request.getParameter("jsRuta"); 
    		String jsOperador = request.getParameter("jsOperador"); 
    		String jsUnidad = request.getParameter("jsUnidad");
    		
        	response.setContentType( "text/html; charset=UTF-8" );
        	PrintWriter out = response.getWriter();
        	out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
        	out.println("<thead class=\"xxbstheadbg\">");
        	out.println("<tr>");
        	out.println("<th>FOLIO</th>");
        	out.println("<th>FECHA</th>");
        	out.println("<th>UNIDAD</th>");
        	out.println("<th>RUTA</th>");
        	out.println("<th>OPERADOR</th>");
        	out.println("<th>COSTO</th>");
        	out.println("<th>HR&nbsp;INICIO</th>");
        	out.println("<th>KM&nbsp;INICIAL</th>");
        	out.println("<th>HR&nbsp;FINAL</th>");
        	out.println("<th>KM&nbsp;FINAL</th>");
        	out.println("<th>EMPRESA</th>");
        	out.println("<th>OBSERVACIONES</th>");
        	out.println("<th>TIPO&nbsp;DE&nbsp;VIAJE</th>");
        	out.println("<th>CONDICIONES</th>");
        	out.println("<th>CENTRO&nbsp;DE&nbsp;COSTOS</th>");
        	out.println("<th>USUARIO</th>");
        	out.println("<th>FECHA&nbsp;CAPTURA</th>");
        	out.println("</tr>");
        	out.println("</thead>");
        	
        	int pDesdeDay = 0; 
        	int pDesdeMonth = 0; 
        	int pDesdeYear = 0; 
        	int pHastaDay = 0; 
        	int pHastaMonth = 0; 
        	int pHastaYear = 0; 
        	
        	if(null!=jsFechaDesdeDay) {
        		pDesdeDay = Integer.parseInt(jsFechaDesdeDay); 
        	}
        	
			if(null!=jsFechaDesdeMonth) {
				pDesdeMonth = Integer.parseInt(jsFechaDesdeMonth);
				pDesdeMonth = pDesdeMonth +1; 
			}
			if(null!=jsFechaDesdeFullYear) {
				pDesdeYear = Integer.parseInt(jsFechaDesdeFullYear);
			}
			if(null!=jsFechaHastaDay) {
				pHastaDay = Integer.parseInt(jsFechaHastaDay);
			}
			if(null!=jsFechaHastaMonth) {
				pHastaMonth = Integer.parseInt(jsFechaHastaMonth);
				pHastaMonth = pHastaMonth +1; 
			}
			if(null!=jsFechaHastaFullYear) {
				pHastaYear = Integer.parseInt(jsFechaHastaFullYear);
			}
        	
			ValesDao valesDao = Utils.getValesInstance(); 
        	try {
        		List<Vales> listVales = valesDao.filtrarPorFechas(pDesdeDay
						                                        , pDesdeMonth
						                                        , pDesdeYear
						                                        , pHastaDay
						                                        , pHastaMonth
						                                        , pHastaYear
						                                        , jsEmpresa
						                                        , jsRuta
						                                        , jsOperador
						                                        , jsUnidad
						                                        );
				if(null!=listVales&&listVales.size()>0) {
					Iterator<Vales> iterator= listVales.iterator();
					out.println("<tbody class=\"xxbstbodybg\">");
					while(iterator.hasNext()) {
						
						Vales valesDto = iterator.next(); 
						out.println("<tr>");
						out.println("<td>"+valesDto.getFolio()+"</td>");
						out.println("<td>"+valesDto.getFechaddMMyyyyV2()+"</td>");    
						out.println("<td>"+valesDto.getEco()+"</td>");   
						out.println("<td>"+valesDto.getRuta()+"</td>");   
						out.println("<td>"+valesDto.getOperador()+"</td>");   
						/*out.println("<td class=\"xxbstdtextalignV2\">"+valesDto.getCosto()+"</td>");*/   
						out.println("<td class=\"xxbstdtextalignV2\">"+valesDto.getEnUSCosto()+"</td>");
						/*out.println("<td>"+valesDto.getHorainicial()+"</td>");*/
						out.println("<td>"+valesDto.getHhmmssahorainicial()+"</td>");
						out.println("<td>"+valesDto.getKminicial()+"</td>");
						/*out.println("<td>"+valesDto.getHorafinal()+"</td>");*/
						out.println("<td>"+valesDto.getHhmmssahorafinal()+"</td>"); 
						out.println("<td>"+valesDto.getKmfinal()+"</td>");   
						out.println("<td>"+valesDto.getCliente()+"</td>");  
						out.println("<td>"+valesDto.getObservaciones()+"</td>");
						out.println("<td>"+valesDto.getTipoviaje()+"</td>"); 
						out.println("<td>"+valesDto.getTipocobro()+"</td>"); 
						out.println("<td>"+valesDto.getCentrocostos()+"</td>");   
						out.println("<td>"+valesDto.getUsuario()+"</td>");   
						out.println("<td>"+valesDto.getFechacapturaddMMyyyV3()+"</td>");
						out.println("</tr>");
					}
					out.println("</tbody>");	
				}
			} catch (SQLException e) {
				out.println("<tbody>");
				out.println("<tr>");
				out.println("<td>");
				out.println(e.toString());
				out.println("</td>");
				out.println("</tr>");
				out.println("</tbody>");
			}
        	
        	out.println("</table>");
			out.close();
        }else if("Salir".equals(strAccionListadoBoletas)) {
        	request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
        }
        
	}

}