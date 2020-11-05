package bs.vales.combust.entry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dto.CatAutobus;
import bs.util.Utils;
import bs.vales.combust.entry.dao.DieselDao;
import bs.vales.combust.entry.dto.Diesel;

import java.util.Iterator;
import java.util.List; 
/**
 * Servlet implementation class EntryCombustibleCO
 */
@WebServlet(name="EntryCombustibleCO", urlPatterns= {"/EntryCombustibleCO"})
public class EntryCombustibleCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryCombustibleCO() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
		 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
		else {
			response.getWriter().println("Session Invalida");
			return; 
		}
		
		System.out.println("Dentro Do Post EntryCombustibleCO");
		String strAccion = request.getParameter("accionEntryCombustible"); 
		String strDay = request.getParameter("jsDay"); 
		String strMonth = request.getParameter("jsMonth"); 
		String strFullYear = request.getParameter("jsFullYear"); 
		
		
		System.out.println("strDay:"+strDay); 
		System.out.println("strMonth:"+strMonth); 
		System.out.println("strFullYear:"+strFullYear); 
		System.out.println("strAccion:"+strAccion); 
		
		if("filtraPorFecha".equals(strAccion)) {
			response.setContentType( "text/html; charset=UTF-8" );
			PrintWriter out = response.getWriter();
			DieselDao dieselDao = Utils.getDieselInstance();
			try {
				int intDay = 0; 
				int intMonth = 0; 
				int intFullYear = 0; 
				
				if(null!=strDay) {
					 intDay = Integer.parseInt(strDay); 
				}
				if(null!=strMonth) {
				   intMonth = Integer.parseInt(strMonth);
				   intMonth = intMonth +1; 
				}
				if(null!=strFullYear) {
				   intFullYear = Integer.parseInt(strFullYear);
				}
				List<Diesel> listDieselDto =  dieselDao.filtraPorFecha(intDay,intMonth,intFullYear);
				
				 out.println("<table id=\"TablRO\" class=\"table table-striped table-bordered table-condensed\">");
				 out.println("<thead class=\"xxbstheadbg\">");
				 out.println("<tr>");
				 out.println("<th>NOTA</th>");
				 out.println("<th>UNIDAD</th>");
				 out.println("<th>TOTAL</th>");
				 out.println("<th>LITROS</th>");
				 out.println("<th>KILOMENTRAJE</th>");
				 out.println("<th>GASOLINERA</th>");
				 out.println("</tr>");
				 out.println("</thead>");
				 out.println("<tbody class=\"xxbstbodybg\">");
				
				if(null!=listDieselDto&&listDieselDto.size()>0) {
				 
				 Iterator<Diesel> iterator =	listDieselDto.iterator();
				
				 while(iterator.hasNext()) {
					 Diesel dieselDto =  iterator.next(); 
					 out.print("<tr>"); 
					 out.print("<td>"+dieselDto.getNota()+"</td>");
					 out.print("<td>"+dieselDto.getUnidad()+"</td>");
					 out.print("<td>"+dieselDto.getTotal()+"</td>");
					 out.print("<td>"+dieselDto.getLitros()+"</td>");
					 out.print("<td>"+dieselDto.getKilometraje()+"</td>");
					 out.print("<td>"+dieselDto.getGasolinera()+"</td>");
					 out.println("</tr>"); 
				 }
				
				}
				
				 out.println("<tfoot>");
				 out.println("<tr>");
				 out.println("<th>NOTA</th>");
				 out.println("<th>UNIDAD</th>");
				 out.println("<th>TOTAL</th>");
				 out.println("<th>LITROS</th>");
				 out.println("<th>KILOMENTRAJE</th>");
				 out.println("<th>GASOLINERA</th>");
				 out.println("</tr>");
				 out.println("</tfoot>");
				 out.println("</tbody>");
				 out.println("</table>");
				
			} catch (SQLException sqle) {
				System.out.println("ERROOOOOOOOOOOORRRRRRRRRR:"+sqle.getMessage());
				out.println("<tr>");
				out.println("<td>");
				out.println("ERROR:"+sqle.getMessage());
				out.println("</td>");
				out.println("</tr>");
				out.println("</tbody>");
				out.println("</table>");
				return;
			}
			out.close();
			return;
		}else if("Guardar".equals(strAccion)){
			
			String strFolio = request.getParameter("folio");
			String strGasolinera= request.getParameter("gasolinera");
			String strUnidad= request.getParameter("unidad");
			String strFechaC= request.getParameter("fechaC");
			String strOperador= request.getParameter("operador");
			String strLitros= request.getParameter("litros");
			String strTotal = request.getParameter("total");
			String strKmanterior= request.getParameter("kmanterior");
			String strKmactual= request.getParameter("kmactual");
			String strCombustible = request.getParameter("combustible");
			
			System.out.println("strFolio:"+strFolio);
			System.out.println("strGasolinera:"+strGasolinera);
			System.out.println("strUnidad:"+strUnidad);
			System.out.println("strOperador:"+strOperador);
			System.out.println("strLitros:"+strLitros);
			System.out.println("strTotal:"+strTotal);
			System.out.println("strKmanterior:"+strKmanterior);
			System.out.println("strKmactual:"+strKmactual);
			System.out.println("strCombustible:"+strCombustible);
			System.out.println(strFechaC);
			
			if((!"".equals(strFolio)&&null!=strFolio)
			  &&(!"".equals(strGasolinera)&&null!=strGasolinera)
			  &&(!"".equals(strUnidad)&&null!=strUnidad)
			  &&(!"".equals(strFechaC)&&null!=strFechaC)
			  &&(!"".equals(strOperador)&&null!=strOperador)
			  &&(!"".equals(strLitros)&&null!=strLitros)
			  &&(!"".equals(strTotal)&&null!=strTotal)
			  &&(!"".equals(strKmanterior)&&null!=strKmanterior)
			  &&(!"".equals(strKmactual)&&null!=strKmactual)
			  &&(!"".equals(strCombustible)&&null!=strCombustible)
			  ) {
				
				Diesel dieselDto = new Diesel(); 
				DieselDao dieselDao = Utils.getDieselInstance(); 
				java.util.Date utilFecha = new java.util.Date(); 
				java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
				
                String strNextNota=null;
				try {
					strNextNota = dieselDao.findNextNota();
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
			
				
				dieselDto.setFechaC(strFechaC);
//				dieselDto.setFecha(sqlFecha); /**datetime] NULL, **/
				dieselDto.setNota (Long.parseLong(strFolio)/*Long.parseLong(strNextNota)*/); /** bigint] NOT NULL,**/
				dieselDto.setUnidad (strUnidad); /** varchar](50) NULL, **/
				dieselDto.setTotal(new java.math.BigDecimal(strTotal)); /**smallmoney] NULL, **/
				dieselDto.setLitros (Double.parseDouble(strLitros)); /** float] NULL, **/
				dieselDto.setKilometraje(Double.parseDouble(strKmactual)); /**float] NULL,**/
				dieselDto.setHora (null); /** numeric](18, 0) NULL,**/
				dieselDto.setFechafactura (null); /** datetime] NULL,**/
				dieselDto.setGasolinera (strGasolinera); /** varchar](50) NULL, **/
				dieselDto.setOperador (strOperador); /** varchar](50) NULL, **/
				dieselDto.setCombustible(strCombustible); /**varchar](50) NULL,**/
				dieselDto.setRendimiento(0d); /**float] NULL,**/
				dieselDto.setKmanterior (Double.parseDouble(strKmanterior)); /** float] NULL, **/
				double kmsrecorr = Double.parseDouble(strKmactual)-Double.parseDouble(strKmanterior); 
				dieselDto.setKmsrecorridos(kmsrecorr); /**float] NULL,**/
				dieselDto.setUsuario(strUsuario); /**varchar](50) NULL,**/
				dieselDto.setEmpresa(null); /**varchar](50) NULL,**/
			    
				String strInsert=null;
				try {
					strInsert = dieselDao.insert(dieselDto);
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				if(null==strInsert) {
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=EntryCombustibleCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				}
				
			}else {
			  response.getWriter().println("Fallo al validar lo parametros"); 
			  return; 
			}
			
		}else if("Salir".equals(strAccion)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("validaFolio".equals(strAccion)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			DieselDao dieselDao = Utils.getDieselInstance(); 
			String strpFolioValue = request.getParameter("pFolioValue"); 
			try {
				String strValidaFolio = dieselDao.validaFolio(Long.parseLong(strpFolioValue));
				if(null!=strValidaFolio) {
					out.print("El vale de combustible ya existe");
					out.close();
				}else {
					out.print("");
					out.close();
				}
				return; 
			} catch (NumberFormatException nfe) {
				out.print("EXCEPTION:"+nfe.toString());
				out.close();
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
			return; 
		}else if("obtieneKilometrajeAnterior".equals(strAccion)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			String strpUnidadValue = request.getParameter("pUnidadValue"); 
			DieselDao dieselDao = Utils.getDieselInstance(); 
			try {
				double doKmAnterior =  dieselDao.findKmAnterior(strpUnidadValue);
				out.print(""+doKmAnterior);
				out.close();
				return;
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
			return;
		}else if("Eliminar".equals(strAccion)) {
			  DieselDao dieselDao = Utils.getDieselInstance();
           // CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance();
			String strfolioValeTrx = request.getParameter("folioVale"); 
			System.out.println(strfolioValeTrx);
			try {
				String strDelete = dieselDao.deleteByFolio(Integer.parseInt(strfolioValeTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					List<Diesel> liDiesel = dieselDao.findAll();
					if(null!=liDiesel&&liDiesel.size()>0) {
						request.setAttribute("liDiesel", liDiesel);
					}
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"COMBUSTIBLE CARGAS");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO UNIDADES");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/vales/combust/entry/EntryCombustibleVales.jsp").forward(request, response);
				return;
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
			return; 
		}
		
		
	}

}
