package bs.webui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.catalogo.dto.CatTrabajadores;
import bs.fallas.entry.dto.Fallas;
import bs.util.Utils;

/**
 * Servlet implementation class LicenciasTrabajadoresCO
 */
@WebServlet(name="LicenciasTrabajadoresCO", urlPatterns= {"/LicenciasTrabajadoresCO"})

public class LicenciasTrabajadoresCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LicenciasTrabajadoresCO() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		request.setCharacterEncoding("UTF-8");
		String strAccionName = request.getParameter("accionName"); 
	 if("Salir".equals(strAccionName)) {
		 request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		 return; 
	 }
	 else if("LicenVencida".equals(strAccionName)) {
	
		
		  
		  
			 response.setContentType( "text/html; charset=UTF-8" );
		    	PrintWriter out = response.getWriter();
		    	
		    	out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
		    	out.println("<thead class=\"xxbstheadbg\">");
		    	
		    	out.println("<tr>");
		    			out.println("<th>Numero</th>");
		    			out.println("<th>Nombre</th>");
		    			out.println("<th>Puesto</th>");
		    			out.println("<th>Vencimiento&nbsp;de&nbsp;Licencia</th>");
		    			out.println("</tr>");
		    	   	out.println("</thead>");
		    	   	out.println("<tbody class=\"xxbstbodybg\">");
		    	   	try {
						List<CatTrabajadores> listFallas=Utils.getCatTrabajadoresInstance().findLicencVencida();
					
						Iterator<CatTrabajadores> iterLicenTrabajadores=listFallas.iterator();
						while(iterLicenTrabajadores.hasNext()) {
							CatTrabajadores trabajador=iterLicenTrabajadores.next();
							out.println("<tr>");
			    			out.println("<td>"+trabajador.getNumero()+"</td>");
			    			out.println("<td>"+trabajador.getNombre()+"</td>");
			    			out.println("<td>"+trabajador.getPuesto()+"</td>");
			    			out.println("<td>"+trabajador.getLicenciaddMMyyyyV2()+"</td>");
			    			out.println("</tr>");
						}
							
					}
		    	   	catch (SQLException sqle) {
						// TODO Auto-generated catch block
		    	   		
						sqle.printStackTrace();
					}
		    	  // 	${licenTrabajadores.numero}
		    	   	
		    	   	
					out.println("</tbody");
					out.println("</table>");
			    	out.close();
		  
		
	
	 }
	 //-----------------------------PROXIMOS A VENCER---------------------------------------
	 else if("ProxAvencer".equals(strAccionName)) {
		 System.out.println("entra filtrar2");
		 Date date = new Date();
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  String fecha=dateFormat.format(date);
		  System.out.println(fecha);
		  
		  
			 response.setContentType( "text/html; charset=UTF-8" );
		    	PrintWriter out = response.getWriter();
		    	
		    	out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
		    	out.println("<thead class=\"xxbstheadbg\">");
		    	
		    	out.println("<tr>");
		    			out.println("<th>Numero</th>");
		    			out.println("<th>Nombre</th>");
		    			out.println("<th>Puesto</th>");
		    			out.println("<th>Vencimiento&nbsp;de&nbsp;Licencia</th>");
		    			out.println("</tr>");
		    	   	out.println("</thead>");
		    	   	out.println("<tbody class=\"xxbstbodybg\">");
		    	   	try {
						List<CatTrabajadores> listlicenProxVencer=Utils.getCatTrabajadoresInstance().findLicencProximosVencer();
						
						Iterator<CatTrabajadores> iterLicenTrabajadores=listlicenProxVencer.iterator();
						while(iterLicenTrabajadores.hasNext()) {
							CatTrabajadores trabajador=iterLicenTrabajadores.next();
							out.println("<tr>");
			    			out.println("<td>"+trabajador.getNumero()+"</td>");
			    			out.println("<td>"+trabajador.getNombre()+"</td>");
			    			out.println("<td>"+trabajador.getPuesto()+"</td>");
			    			out.println("<td>"+trabajador.getLicenciaddMMyyyyV2()+"</td>");
			    			out.println("</tr>");
						}
					} catch (SQLException sqle) {
						// TODO Auto-generated catch block
						sqle.printStackTrace();
					}
		    	   	
		    	   	
					out.println("</tbody");
					out.println("</table>");
			    	out.close();
		  
		  System.out.println("sale filtrar2");
	
	 }
	 /*if("Todos".equals(strAccionName)) {
		System.out.println("entro"); 
		 request.getRequestDispatcher("/MainCO?accion=LicenciaTrabajadores").include(request, response);  
		 
		 return;
	 }*/
	 else {
		 response.getWriter().println("aun sin implementacion");
	 }
	 
	}

}






 













