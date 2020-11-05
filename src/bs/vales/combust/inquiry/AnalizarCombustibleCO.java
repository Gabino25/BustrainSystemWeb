package bs.vales.combust.inquiry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import bs.catalogo.dto.CatAutobus;
import bs.util.Utils;
import bs.vales.combust.entry.dao.DieselDao;
import bs.vales.combust.entry.dto.Diesel;

/**
 * Servlet implementation class AnalizarCombustibleCO
 */
@WebServlet(name="AnalizarCombustibleCO", urlPatterns= {"/AnalizarCombustibleCO"})
public class AnalizarCombustibleCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalizarCombustibleCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionAnalizaCombustible = request.getParameter("accionAnalizaCombustible"); 
	
		
		/*
		System.out.println("jsFechaDesdeDay:"+jsFechaDesdeDay);
		System.out.println("jsFechaDesdeMonth:"+jsFechaDesdeMonth);
		System.out.println("jsFechaDesdeFullYear:"+jsFechaDesdeFullYear);
		System.out.println("jsFechaHastaDay:"+jsFechaHastaDay);
		System.out.println("jsFechaHastaMonth:"+jsFechaHastaMonth);
		System.out.println("jsFechaHastaFullYear:"+jsFechaHastaFullYear);
		*/
		
		if("filtraPorFechas".equals(strAccionAnalizaCombustible)) {
			
			String jsFechaDesdeDay = request.getParameter("jsFechaDesdeDay"); 
			String jsFechaDesdeMonth = request.getParameter("jsFechaDesdeMonth"); 
			String jsFechaDesdeFullYear = request.getParameter("jsFechaDesdeFullYear"); 
			String jsFechaHastaDay = request.getParameter("jsFechaHastaDay"); 
			String jsFechaHastaMonth = request.getParameter("jsFechaHastaMonth"); 
			String jsFechaHastaFullYear = request.getParameter("jsFechaHastaFullYear"); 
			String jsUnidad = request.getParameter("jsUnidad"); 
			String jsGasolinera = request.getParameter("jsGasolinera"); 
			
        	response.setContentType( "text/html; charset=UTF-8" );
        	PrintWriter out = response.getWriter();
        	out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
        	out.println("<thead class=\"xxbstheadbg\">");
        	out.println("<tr>");
        	out.println("<th>FECHA</th>");
        	out.println("<th>NOTA</th>");
        	out.println("<th>UNIDAD</th>");
        	out.println("<th>TOTAL</th>");
        	out.println("<th>LITROS</th>");
        	out.println("<th>KMS</th>");
        	out.println("<th>KM&nbsp;ANTERIOR</th>");
        	out.println("<th>KM&nbsp;REC</th>");
        	out.println("<th>REND</th>");
        	out.println("<th>OPERADOR</th>");
        	out.println("<th>FECHA FACT</th>");
        	out.println("<th>GASOLINERA</th>");
        	out.println("<th>COMBUSTIBLE</th>");
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
			
			DieselDao dieselDao = Utils.getDieselInstance();
			
						
			try {
			 List<Diesel> listDiesel =	dieselDao.filtraPorFechas(pDesdeDay
					                                            , pDesdeMonth
					                                            , pDesdeYear
					                                            , pHastaDay
					                                            , pHastaMonth
					                                            , pHastaYear
					                                            , jsUnidad
					                                            , jsGasolinera
					                                            );
			 Iterator<Diesel> iteratorDiesel = listDiesel.iterator();
			 out.println("<tbody class=\"xxbstbodybg\">");
			 while(iteratorDiesel.hasNext()) {
				Diesel dieselDto =  iteratorDiesel.next();  
				out.println("<tr>");
				out.println("<td>"+dieselDto.getFechaddMMyyyyV2()+"</td>");
				out.println("<td>"+dieselDto.getNota()+"</td>");
				out.println("<td>"+dieselDto.getUnidad()+"</td>");
				out.println("<td class=\"xxbstdtextalignV2\">"+dieselDto.getEnUStotal()+"</td>");
				out.println("<td class=\"xxbstdtextalignV2\">"+dieselDto.getLitros()+"</td>");
				out.println("<td class=\"xxbstdtextalignV2\">"+dieselDto.getKilometraje()+"</td>");
				out.println("<td class=\"xxbstdtextalignV2\">"+dieselDto.getKmanterior()+"</td>");
				out.println("<td class=\"xxbstdtextalignV2\">"+dieselDto.getKmsrecorridos()+"</td>");
				out.println("<td class=\"xxbstdtextalignV2\">"+dieselDto.getRendimiento()+"</td>");
				out.println("<td>"+dieselDto.getOperador()+"</td>");
				out.println("<td>"+dieselDto.getFechafactura()+"</td>");
				out.println("<td>"+dieselDto.getGasolinera()+"</td>");
				out.println("<td>"+dieselDto.getCombustible()+"</td>");
				out.println("</tr>");
			 }
			} catch (SQLException e) {
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
			
			out.println("<tfoot>");
        	out.println("<tr>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th>UNIDAD</th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th></th>");
        	out.println("<th>GASOLINERA</th>");
        	out.println("<th>COMBUSTIBLE</th>");
        	out.println("</tr>");
        	out.println("</tfoot>");
        	
			out.println("</table>");
			out.close();
			return;
		}else if("CargasAexcel".equals(strAccionAnalizaCombustible)) {
			
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
			
			DieselDao dieselDao = Utils.getDieselInstance();
			
			response.setContentType( "text/html; charset=UTF-8" );
        	PrintWriter out = response.getWriter();
			
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
     	 	
     	 	 
        	
			try {
			
			out.println("<Worksheet ss:Name=\"COSTO\">");
	    	out.println("<Table ss:ExpandedColumnCount=\"50\" ss:ExpandedRowCount=\"10000\" x:FullColumns=\"1\"  x:FullRows=\"1\"  ss:DefaultRowHeight=\"15\">");
	    		
			String strBodyInfo	= dieselDao.cargasAexcel(pDesdeDay
						             , pDesdeMonth
						             , pDesdeYear
						             , pHastaDay
						             , pHastaMonth
						             , pHastaYear
						             );
			out.println(strBodyInfo);
			
			out.println("</Table>");
		 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		 	out.println("<Selected/>");
		 	out.println("<ProtectObjects>False</ProtectObjects>");
		 	out.println("<ProtectScenarios>False</ProtectScenarios>");
		 	out.println("</WorksheetOptions>");
		 	out.println("</Worksheet>");
		 	
		 	
		 	out.println("<Worksheet ss:Name=\"KILOMETRAJE\">");
	    	out.println("<Table ss:ExpandedColumnCount=\"50\" ss:ExpandedRowCount=\"10000\" x:FullColumns=\"1\"  x:FullRows=\"1\"  ss:DefaultRowHeight=\"15\">");
	    		
			String strBodyInfoV2	= dieselDao.cargasAexcelV2(pDesdeDay
						             , pDesdeMonth
						             , pDesdeYear
						             , pHastaDay
						             , pHastaMonth
						             , pHastaYear
						             );
			out.println(strBodyInfoV2);
			
			out.println("</Table>");
		 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		 	out.println("<Selected/>");
		 	out.println("<ProtectObjects>False</ProtectObjects>");
		 	out.println("<ProtectScenarios>False</ProtectScenarios>");
		 	out.println("</WorksheetOptions>");
		 	out.println("</Worksheet>");
		 	
		 	out.println("<Worksheet ss:Name=\"RENDIMIENTOS\">");
	    	out.println("<Table ss:ExpandedColumnCount=\"50\" ss:ExpandedRowCount=\"10000\" x:FullColumns=\"1\"  x:FullRows=\"1\"  ss:DefaultRowHeight=\"15\">");
	    		
	    	
			String strBodyInfoV3	= dieselDao.cargasAexcelV3(pDesdeDay
						             , pDesdeMonth
						             , pDesdeYear
						             , pHastaDay
						             , pHastaMonth
						             , pHastaYear
						             );
			out.println(strBodyInfoV3);
			
			out.println("</Table>");
		 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		 	out.println("<Selected/>");
		 	out.println("<ProtectObjects>False</ProtectObjects>");
		 	out.println("<ProtectScenarios>False</ProtectScenarios>");
		 	out.println("</WorksheetOptions>");
		 	out.println("</Worksheet>");
		 	
			
			}catch (SQLServerException sqlse) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqlse.toString());
				return;
			}catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}catch (Exception e) {
				System.out.println("Catch e");
				System.out.println(e.toString());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
				return;
			} 
			
			
			out.println("</Workbook>");
		 	
		 	out.close();
			
		
		}
		else if("Salir".equals(strAccionAnalizaCombustible)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("CatalogarUnidades".equals(strAccionAnalizaCombustible)){
			
			try {
				List<CatAutobus> listCatalogarUnidades = Utils.getCatAutobusInstance().findCatalogarUnidades();
			    request.setAttribute("listCatalogarUnidades", listCatalogarUnidades);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/combust/upd/CatalogarUnidades.jsp").include(request, response);  
		    return; 
		}else if("CargasAexcelCategoria".equals(strAccionAnalizaCombustible)) {
			 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("Content-Disposition", "inline; filename="+ "COMBUSTIBLE_PARQUE_VEHICULAR.xls");
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
				
				String strEtiqueta = jsFechaDesdeDay+Utils.getMes(pDesdeMonth)+jsFechaDesdeFullYear+"al"+jsFechaHastaDay+Utils.getMes(pHastaMonth)+jsFechaHastaFullYear;
				
				DieselDao dieselDao = Utils.getDieselInstance();
			 
				System.out.println("pDesdeDay:"+pDesdeDay); 
	        	System.out.println("pDesdeMonth:"+pDesdeMonth); 
	        	System.out.println("pDesdeYear:"+pDesdeYear); 
	        	System.out.println("pHastaDay:"+pHastaDay); 
	        	System.out.println("pHastaMonth:"+pHastaMonth); 
	        	System.out.println("pHastaYear:"+pHastaYear); 
	        	
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
	      	 	out.println("<Style ss:ID=\"s5\">");
	      	 	out.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>"); 
	      	 	out.println("<Borders/>"); 
	      	 	out.println("<Font ss:FontName=\"Arial\" x:Family=\"Swiss\" ss:Size=\"14\" ss:Color=\"#FFFFFF\" ss:Bold=\"1\"/>"); 
	      	 	out.println("<Interior ss:Color=\"#002060\" ss:Pattern=\"Solid\"/>"); 
	      	 	out.println("</Style>");
	      	 	out.println("<Style ss:ID=\"s6\">");
	      		out.println("<NumberFormat ss:Format=\"Short Date\"/>"); 
	      	 	out.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>"); 
	      	 	out.println("<Borders/>"); 
	      	 	out.println("<Font ss:FontName=\"Arial\" x:Family=\"Swiss\" ss:Size=\"14\" ss:Color=\"#FFFFFF\" ss:Bold=\"1\"/>"); 
	      	 	out.println("<Interior ss:Color=\"#002060\" ss:Pattern=\"Solid\"/>"); 
	      	 	out.println("</Style>");
	      	 	out.println("<Style ss:ID=\"s7\">");
	      		out.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>"); 
	      	 	out.println("<Borders>"); 
	      	 	out.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("</Borders>"); 
	      	 	out.println("<Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"14\" ss:Color=\"#000000\" ss:Bold=\"1\"/>"); 
	      	 	out.println("<Interior ss:Color=\"#FFFFFF\" ss:Pattern=\"Solid\"/>"); 
	      	 	out.println("</Style>");
	      	 	out.println("<Style ss:ID=\"s8\">");
	      		out.println("<Borders>"); 
	      	 	out.println("<Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("<Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("<Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("<Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>"); 
	      	 	out.println("</Borders>"); 
	      	 	out.println("<Font ss:FontName=\"Arial\" x:Family=\"Swiss\" ss:Size=\"12\" ss:Color=\"#000000\" ss:Bold=\"1\"/>"); 
	      	 	out.println("<Interior/>"); 
	      	 	out.println("<NumberFormat ss:Format=\"_-&quot;$&quot;* #,##0.00_-;\\-&quot;$&quot;* #,##0.00_-;_-&quot;$&quot;* &quot;-&quot;??_-;_-@_-\"/>"); 
	      	 	out.println("</Style>");
	      	 	out.println("<Style ss:ID=\"s9\">");
	      	 	out.println("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>");
	      	 	out.println("<Borders/>");
	      	 	out.println("<Font ss:FontName=\"Arial\" x:Family=\"Swiss\" ss:Size=\"14\" ss:Bold=\"1\"/>");
	      	 	out.println("<Interior ss:Color=\"#FABF8F\" ss:Pattern=\"Solid\"/>");
	      	    out.println("</Style>");
	      	 	out.println("</Styles>");
	      	 	
	      	 	
	      	 	out.println("<Worksheet ss:Name=\""+strEtiqueta+"\">");
		    	out.println("<Table ss:ExpandedColumnCount=\"30\" ss:ExpandedRowCount=\"10000\" x:FullColumns=\"1\"  x:FullRows=\"1\"  ss:DefaultRowHeight=\"15\">");
		    	
		    	String strBodyInfoComParVehicular = "";
				try {
					strBodyInfoComParVehicular = dieselDao.cargasAexcelComParVehicular(pDesdeDay
					         , pDesdeMonth
					         , pDesdeYear
					         , pHastaDay
					         , pHastaMonth
					         , pHastaYear
					         );
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} catch (ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.toString());
					return;
				}
				
				System.out.println(strBodyInfoComParVehicular);
				
                out.println(strBodyInfoComParVehicular);
		    	
		    	out.println("</Table>");
			 	out.println("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
			 	out.println("<Selected/>");
			 	out.println("<ProtectObjects>False</ProtectObjects>");
			 	out.println("<ProtectScenarios>False</ProtectScenarios>");
			 	out.println("</WorksheetOptions>");
			 	out.println("</Worksheet>");
			 	
			 	out.println("</Workbook>");
	        	
			 out.close();
			 
			 
			 System.out.println("Sale CargasAexcelCategoria"); 
		}
		else {
			response.getWriter().println("Aun sin implementacion.");
			return;
		} 
		
	}

}
