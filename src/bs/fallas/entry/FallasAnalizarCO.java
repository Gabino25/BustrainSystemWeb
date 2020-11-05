package bs.fallas.entry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.fallas.entry.dto.Fallas;
import bs.util.Utils;

import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class FallasAnalizarCO
 */
@WebServlet(name="FallasAnalizarCO", urlPatterns= {"/FallasAnalizarCO"})
public class FallasAnalizarCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FallasAnalizarCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		 String strAccionName = request.getParameter("accionName"); 
		 if("Salir".equals(strAccionName)) {
			 request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
			 return; 
			 
		 }
	
		 else if("Filtrar".equals(strAccionName)) {
			
			 String jsFechaDesdeDay =request.getParameter("jsFechaDesdeDay");
			 System.out.println(jsFechaDesdeDay);
			 String jsFechaDesdeMonth =request.getParameter("jsFechaDesdeMonth");
			 System.out.println(jsFechaDesdeMonth);
			 String jsFechaDesdeFullYear =request.getParameter("jsFechaDesdeFullYear");
			 System.out.println(jsFechaDesdeFullYear);
			 String jsFechaHastaDay =request.getParameter("jsFechaHastaDay");
			 System.out.println(jsFechaHastaDay);
			 String jsFechaHastaMonth =request.getParameter("jsFechaHastaMonth");
			 System.out.println(jsFechaHastaMonth);
			 String jsFechaHastaFullYear =request.getParameter("jsFechaHastaFullYear");
			 System.out.println(jsFechaHastaFullYear);
			 String unidad =request.getParameter("unidad");
			 System.out.println(unidad);
			 String strestado =request.getParameter("estado");
			 System.out.println(strestado);
			 int pDesdeDay = 0; 
		    	int pDesdeMonth = 0; 
		    	int pDesdeYear = 0; 
		    	int pHastaDay = 0; 
		    	int pHastaMonth = 0; 
		    	int pHastaYear = 0; 
		    	
		    	//SE EVALUA QUE NO ESTEN VACIOS LOS PARAMETROS
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
			
				
			 response.setContentType( "text/html; charset=UTF-8" );
		    	PrintWriter out = response.getWriter();
		    	
		    	out.println("<table id=\"TablRO\"  class=\"table table-striped table-bordered table-condensed\">");
		    	out.println("<thead class=\"xxbstheadbg\">");
		    	
		    	out.println("<tr>");
		    	out.println("<th>Numero</th>");
		    			out.println("<th>Unidad</th>");
		    			out.println("<th>Reparaci&oacute;n</th>");
		    			out.println("<th>Kilometraje</th>");
		    			out.println("<th>Fecha&nbsp;de&nbsp;Reparaci&oacute;n</th>");
		    			out.println("</tr>");
		    	   	out.println("</thead>");
		    	
		    	
		    	
		    	//SE DECLARA LA VARIABLE A UTILIZAR EN EN FALLASDAO.JAVA Y FALLASDAOIMPL.JAVA  
		    	//EN EL CUAL SE REALIZAN LAS CONSULTAS Y DESPUES RE DECLARA AQUI
		    	try {
					List<Fallas> listFallas=Utils.getFallasInstance().filter(pDesdeDay
																			,pDesdeMonth
																			,pDesdeYear
																			,pHastaDay
																			,pHastaMonth
																			,pHastaYear
																			,unidad
																			,strestado);
					
					out.println("<tbody class=\"xxbstbodybg\">");
					Iterator<Fallas> iterFallas=listFallas.iterator();
					while(iterFallas.hasNext()) {
						Fallas fallas=new Fallas();
						fallas=iterFallas.next();
						out.println("<tr>");
						out.println("<td>"+fallas.getNumero()+"</td>");
						out.println("<td class=\"xxbstdtextalignV3\">"+fallas.getEco()+"</td>");
						out.println("<td>"+fallas.getDescripcion()+"</td>");
						out.println("<td class=\"xxbstdtextalignV3\">"+fallas.getKilometrajenf()+"</td>");
						out.println("<td class=\"xxbstdtextalignV3\">"+fallas.getFechaddMMyyyyV2()+"</td>");
						;
						
					
						out.println("</tr>");
					}
				
					out.println("</tbody");
					
					
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					out.println(sqle.getMessage());
			 }
		    	
		    	out.println("</table>");
		    	out.close();
		    	
		 }
		 else {
			 response.getWriter().println("aun sin implementacion");
		 }
	}

}
