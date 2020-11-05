package bs.llantas.inquiry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.llantas.entry.dao.MovimientosLlantasDao;
import bs.llantas.entry.dto.MovimientosLlantas;
import bs.util.Utils;

import java.util.List;
import java.util.Iterator;

/**
 * Servlet implementation class AnalizarLlantasCO
 */
@WebServlet(name="AnalizarLlantasCO",urlPatterns= {"/AnalizarLlantasCO"})
public class AnalizarLlantasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalizarLlantasCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionAnalizarLlantas = request.getParameter("accionAnalizarLlantas"); 
		String jsFechaDesdeDay = request.getParameter("jsFechaDesdeDay"); 
		String jsFechaDesdeMonth = request.getParameter("jsFechaDesdeMonth"); 
		String jsFechaDesdeFullYear = request.getParameter("jsFechaDesdeFullYear"); 
		String jsFechaHastaDay = request.getParameter("jsFechaHastaDay"); 
		String jsFechaHastaMonth = request.getParameter("jsFechaHastaMonth"); 
		String jsFechaHastaFullYear = request.getParameter("jsFechaHastaFullYear");
		String pUnidades = request.getParameter("Unidades"); 
		String pTipoMovimiento = request.getParameter("TipoMovimiento"); 
		String pLlantas = request.getParameter("Llantas"); 
		
		
		if("Salir".equals(strAccionAnalizarLlantas)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}
		
		
		if("analizarLlantas".equals(strAccionAnalizarLlantas)) {
		
		response.setContentType( "text/html; charset=UTF-8" );
    	PrintWriter out = response.getWriter();
		out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
		out.println("<thead class=\"xxbstheadbg\">");
		out.println("<tr>");
		out.println("<th>FOLIO</th>");
		out.println("<th>NOMBRE</th>");
		out.println("<th>OPERADOR</th>");
		out.println("<th>FECHA</th>");
		out.println("<th>POSICION</th>");
		out.println("<th>UNIDAD</th>");
		out.println("<th>PRESION</th>");
		out.println("<th>OBSERVACIONES</th>");
		out.println("<th>TIPO&nbsp;MOV</th>");
		out.println("<th>KILOMETRAJE</th>");
		out.println("<th>USUARIO</th>");
		out.println("<th>FECHACAPTURA</th>");
		out.println("<th>PRESIONANTERIOR</th>");
		out.println("<th>COSTO</th>");
		out.println("<th>NOTA</th>");
		out.println("<th>KMRECORRIDO</th>");
		out.println("<th>PROF</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody class=\"xxbstbodybg\">");
		
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
		
		if(!"".equals(pTipoMovimiento)) {
			pTipoMovimiento = pTipoMovimiento.replaceAll("_", " ");
		}
		
		MovimientosLlantasDao movimientosLlantasDao = Utils.getMovimientosLlantasInstance(); 
		
		try {
			List<MovimientosLlantas> listMovimientosLlantas = movimientosLlantasDao.analizarLlantas(pDesdeDay
					                            , pDesdeMonth
					                            , pDesdeYear
					                            , pHastaDay
					                            , pHastaMonth
					                            , pHastaYear
					                            , pUnidades
					                            , pTipoMovimiento
					                            , pLlantas
					                            );
		
		   if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
		  		Iterator<MovimientosLlantas> iterator = listMovimientosLlantas.iterator();
		  		while(iterator.hasNext()) {
		  		 MovimientosLlantas   movimientosLlantasDto =	iterator.next(); 
		  		out.println("<tr>");
		  		out.println("<td>"+movimientosLlantasDto.getFolio()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getNombre()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getOperador()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getFechaddMMyyyyV2()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getPosicion()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getUnidad()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getPresion()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getObservaciones()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getTipo_mov()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getKilometraje()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getUsuario()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getFechacapturaddMMyyyyV3()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getPresionanterior()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getCosto()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getNota()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getKmrecorrido()+"</td>");
		  		out.println("<td>"+movimientosLlantasDto.getProf()+"</td>");
		  		out.println("</tr>");
		  		}
		  	
		  	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 out.println("<tbody>");
			 out.println("<tr>");
			 out.println("<td>");
			 out.println(e.toString());
			 out.println("</td>");
			 out.println("</tr>");
			 out.println("</tbody>");
			 return;
		}
		
		out.println("</tbody>");
		out.println("</table>");
		out.close();
		return;
		}
		
	}

}
