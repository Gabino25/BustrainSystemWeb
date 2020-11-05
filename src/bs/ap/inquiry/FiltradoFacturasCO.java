package bs.ap.inquiry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.ap.dao.ConceptosDao;
import bs.ap.dao.FacturasDao;
import bs.ap.dto.Conceptos;
import bs.ap.dto.Facturas;
import bs.util.Utils;

import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class FiltradoFacturas
 */
@WebServlet(name="FiltradoFacturasCO", urlPatterns= {"/FiltradoFacturasCO"})
public class FiltradoFacturasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltradoFacturasCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String straccion = request.getParameter("accion"); 
	 if("Salir".equals(straccion)) {
		 request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		 return; 
	 }else if("filtrarFacturas".equals(straccion)){
		    response.setContentType( "text/html; charset=UTF-8" );
	    	PrintWriter out = response.getWriter();
	    	
		    String jsFechaDesdeDay = request.getParameter("jsFechaDesdeDay"); 
			String jsFechaDesdeMonth = request.getParameter("jsFechaDesdeMonth"); 
			String jsFechaDesdeFullYear = request.getParameter("jsFechaDesdeFullYear"); 
			String jsFechaHastaDay = request.getParameter("jsFechaHastaDay"); 
			String jsFechaHastaMonth = request.getParameter("jsFechaHastaMonth"); 
			String jsFechaHastaFullYear = request.getParameter("jsFechaHastaFullYear");
			String strjsUnidad = request.getParameter("jsUnidad"); 
			String strjsProveedor = request.getParameter("jsProveedor"); 
			String strjsDescripcion = request.getParameter("jsDescripcion"); 
			
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
			
			System.out.println("pDesdeDay:"+pDesdeDay);
			System.out.println("pDesdeMonth:"+pDesdeMonth);
			System.out.println("pDesdeYear:"+pDesdeYear);
			System.out.println("pHastaDay:"+pHastaDay);
			System.out.println("pHastaMonth:"+pHastaMonth);
			System.out.println("pHastaYear:"+pHastaYear);
			System.out.println("strjsUnidad:"+strjsUnidad);
			System.out.println("strjsProveedor:"+strjsProveedor);
			System.out.println("strjsDescripcion:"+strjsDescripcion);
			
			out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
			out.println("<thead class=\"xxbstheadbg\">");
			out.println("<tr>");
			out.println("<th>FOLIO</th>");
			out.println("<th>PROVEEDOR</th>");
			out.println("<th>DESCRIPCION</th>");
			out.println("<th>UNIDAD</th>");
			out.println("<th>COSTO</th>");
			out.println("<th>FECHA</th>");
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody class=\"xxbstbodybg\">");
			ConceptosDao conceptos =  Utils.getConceptosFacturasInstance();
			try {
				List<Conceptos> listFacturas = conceptos.filtradoFacturas(pDesdeDay
						                   , pDesdeMonth
						                   , pDesdeYear
						                   , pHastaDay
						                   , pHastaMonth
						                   , pHastaYear
						                   , strjsUnidad
						                   , strjsProveedor
						                   , strjsDescripcion
						                   );
				if(null!=listFacturas&&listFacturas.size()>0) {
					Iterator<Conceptos> iterConceptos = listFacturas.iterator();
					while(iterConceptos.hasNext()) {
						Conceptos concepto = iterConceptos.next();
						out.println("<tr>");
						out.println("<td>"+concepto.getFolio()+"</td>");
						out.println("<td>"+concepto.getProveedor()+"</td>");
						out.println("<td>"+concepto.getConcepto()+"</td>");
						out.println("<td>"+concepto.getEco()+"</td>");
						out.println("<td>"+concepto.getCosto()+"</td>");
						out.println("<td>"+concepto.getFechaddMMyyyyV2()+"</td>");
						out.println("</tr>");
					}
				}
			} catch (SQLException sqle) {
				out.println("<tr><td>"+sqle.getMessage()+" - "+sqle.getErrorCode()+"</td></tr>");
				out.println("</tbody>");
				out.println("</table>");
				out.close();
				return;
			}
			out.println("</tbody>");
			out.println("</table>");
			out.close();
			return;
			
	 }else if("gastoMensualExcel".equals(straccion)) {
		 response.setContentType( "text/html; charset=UTF-8" );
	     PrintWriter out = response.getWriter();
        
	 	
		    String jsFechaDesdeDay = request.getParameter("jsFechaDesdeDay"); 
			String jsFechaDesdeMonth = request.getParameter("jsFechaDesdeMonth"); 
			String jsFechaDesdeFullYear = request.getParameter("jsFechaDesdeFullYear"); 
			String jsFechaHastaDay = request.getParameter("jsFechaHastaDay"); 
			String jsFechaHastaMonth = request.getParameter("jsFechaHastaMonth"); 
			String jsFechaHastaFullYear = request.getParameter("jsFechaHastaFullYear");
			
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
	     
	    out.println("<?xml version=\"1.0\"?>");
	 	out.println("<?mso-application progid=\"Excel.Sheet\"?>");
	 	out.println("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
	 	out.println(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
	 	out.println(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
	 	out.println(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
	 	out.println(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">\n");
	 	out.println(" <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">");
	 	out.println("  <Version>14.00</Version>");
	 	out.println(" </DocumentProperties>\n");
	 	out.println("<OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">");
	 	out.println("  <AllowPNG/>");
	 	out.println(" </OfficeDocumentSettings>");
	 	out.println("  <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">");
	 	out.println("  <WindowHeight>10005</WindowHeight>");
	 	out.println("  <WindowWidth>10005</WindowWidth>");
	 	out.println("  <WindowTopX>120</WindowTopX>");
	 	out.println("  <WindowTopY>135</WindowTopY>");
	 	out.println("  <ProtectStructure>False</ProtectStructure>");
	 	out.println("  <ProtectWindows>False</ProtectWindows>");
	 	out.println("</ExcelWorkbook>");
	 	out.println("<Styles>");
	 	out.println("<Style ss:ID=\"s1\">");
	 	out.println("<Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\" ss:Bold=\"1\"/>");
	 	out.println("</Style>");
	 	out.println("<Style ss:ID=\"s2\">");
	 	out.println("<NumberFormat ss:Format=\"Short Date\"/>"); 
	 	out.println("</Style>");
	 	out.println("<Style ss:ID=\"s3\">");
	 	out.println("<NumberFormat ss:Format=\"Standard\"/>"); 
	 	out.println("</Style>");
	 	out.println("<Style ss:ID=\"s4\">");
	 	out.println("<NumberFormat ss:Format=\"General\"/>"); 
	 	out.println("</Style>");
	 	out.println("</Styles>");

	 	out.println("<Worksheet ss:Name=\"BUSTRAIN\">");
	 	out.println("<Table ss:ExpandedColumnCount=\"30\" ss:ExpandedRowCount=\"10000\" x:FullColumns=\"1\"  x:FullRows=\"1\"  ss:DefaultRowHeight=\"15\">");
	    out.println("<Row>");
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">UNIDAD</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">PROVEEDOR</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">DESCRIPCION</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">COSTO</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">FECHA</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">FACTURA</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">CENTRO COSTOS</Data></Cell>"); 
	 	out.println("</Row>");
	 	
	 	ConceptosDao conceptos =  Utils.getConceptosFacturasInstance();
	 	
	 	try {
			List<Conceptos> listFacturasBustrain = conceptos.filtradoFacturasBustrain(pDesdeDay
															                , pDesdeMonth
															                , pDesdeYear
															                , pHastaDay
															                , pHastaMonth
															                , pHastaYear
															                );
			Iterator<Conceptos> iterConceptosBustrain = listFacturasBustrain.iterator();
			while(iterConceptosBustrain.hasNext()) {
				Conceptos concepto = iterConceptosBustrain.next();
				 out.println("<Row>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getEco()+"</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getProveedor()+"</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getConcepto()+"</Data></Cell>");
				 out.println("<Cell ss:StyleID=\"s3\"><Data ss:Type=\"Number\">"+concepto.getCosto()+"</Data></Cell>");
				 out.println("<Cell ss:StyleID=\"s2\"><Data ss:Type=\"DateTime\">"+concepto.getFechayyyyMMdd()+"T00:00:00.000</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getFolio()+"</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getGasto()+"</Data></Cell>");
				 out.println("</Row>");
			}
		} catch (SQLException sqle) {
			out.println("<Row><Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">"+sqle.toString()+"</Data></Row>");
			out.println("</Table>");
		 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		 	out.println("<Selected/>");
		 	out.println("<ProtectObjects>False</ProtectObjects>");
		 	out.println("<ProtectScenarios>False</ProtectScenarios>");
		 	out.println("</WorksheetOptions>");
		 	out.println("</Worksheet>");
		 	out.println("</Workbook>");
		 	out.close();
			return;
		}
	 	
	 	
	 	out.println("</Table>");
	 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
	 	out.println("<Selected/>");
	 	out.println("<ProtectObjects>False</ProtectObjects>");
	 	out.println("<ProtectScenarios>False</ProtectScenarios>");
	 	out.println("</WorksheetOptions>");
	 	out.println("</Worksheet>");
	 	
	 	out.println("<Worksheet ss:Name=\"MONTACARGAS\">");
	 	out.println("<Table ss:ExpandedColumnCount=\"30\" ss:ExpandedRowCount=\"10000\" x:FullColumns=\"1\"  x:FullRows=\"1\"  ss:DefaultRowHeight=\"15\">");
	    out.println("<Row>");
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">UNIDAD</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">PROVEEDOR</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">DESCRIPCION</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">COSTO</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">FECHA</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">FACTURA</Data></Cell>"); 
	 	out.println("<Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">CENTRO COSTOS</Data></Cell>"); 
	 	out.println("</Row>");
	 	
	 	try {
			List<Conceptos> listFacturasMontacargas = conceptos.filtradoFacturasMontacargas(pDesdeDay
															                , pDesdeMonth
															                , pDesdeYear
															                , pHastaDay
															                , pHastaMonth
															                , pHastaYear
															                );
			Iterator<Conceptos> iterConceptosMontacargas = listFacturasMontacargas.iterator();
			while(iterConceptosMontacargas.hasNext()) {
				Conceptos concepto = iterConceptosMontacargas.next();
				 out.println("<Row>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getEco()+"</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getProveedor()+"</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getConcepto()+"</Data></Cell>");
				 out.println("<Cell ss:StyleID=\"s3\"><Data ss:Type=\"Number\">"+concepto.getCosto()+"</Data></Cell>");
				 out.println("<Cell ss:StyleID=\"s2\"><Data ss:Type=\"DateTime\">"+concepto.getFechayyyyMMdd()+"T00:00:00.000</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getFolio()+"</Data></Cell>");
				 out.println("<Cell><Data ss:Type=\"String\">"+concepto.getGasto()+"</Data></Cell>");
				 out.println("</Row>");
			}
		} catch (SQLException sqle) {
			out.println("<Row><Cell ss:StyleID=\"s1\"><Data ss:Type=\"String\">"+sqle.toString()+"</Data></Row>");
			out.println("</Table>");
		 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		 	out.println("<Selected/>");
		 	out.println("<ProtectObjects>False</ProtectObjects>");
		 	out.println("<ProtectScenarios>False</ProtectScenarios>");
		 	out.println("</WorksheetOptions>");
		 	out.println("</Worksheet>");
		 	out.println("</Workbook>");
		 	out.close();
			return;
		}
	 	
	 	out.println("</Table>");
	 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
	 	out.println("<Selected/>");
	 	out.println("<ProtectObjects>False</ProtectObjects>");
	 	out.println("<ProtectScenarios>False</ProtectScenarios>");
	 	out.println("</WorksheetOptions>");
	 	out.println("</Worksheet>");
	 	out.println("</Workbook>");
	 	out.close();
		return;
	 }
	 else {
		 response.getWriter().println("Aun sin implementacion");
	 }
	}

}
