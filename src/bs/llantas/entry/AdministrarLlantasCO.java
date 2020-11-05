package bs.llantas.entry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.llantas.entry.dao.LlantasDao;
import bs.llantas.entry.dto.Llantas;
import bs.util.Utils;

/**
 * Servlet implementation class AdministrarLlantasCO
 */
@WebServlet(name="AdministrarLlantasCO",urlPatterns= {"/AdministrarLlantasCO"})
public class AdministrarLlantasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarLlantasCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionAdministrarLlantas = request.getParameter("accionAdministrarLlantas"); 
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				response.getWriter().println("Session Invalida LLANTAS ADM");
				return; 
			}
		
		if("Salir".equals(strAccionAdministrarLlantas)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Guardar".equals(strAccionAdministrarLlantas)) {
			
			String strCodigo = request.getParameter("codigo");
			String strMedida = request.getParameter("medida");
			String strMarca  = request.getParameter("marca");
			String strModelo = request.getParameter("modelo");
			String strProfundidad = request.getParameter("profundidad");
			String strEstado = request.getParameter("estado");
			String strCosto  = request.getParameter("costo");
		 	String strFechaAlta  = request.getParameter("fechaAlta");
		 	
		 	if(!"".equals(strCodigo)&&null!=strCodigo
		 	 &&(!"".equals(strMedida)&&null!=strMedida)
		 	 &&(!"".equals(strMarca)&&null!=strMarca)
		 	 &&(!"".equals(strModelo)&&null!=strModelo)
		 	 &&(!"".equals(strProfundidad)&&null!=strProfundidad)
		 	 &&(!"".equals(strEstado)&&null!=strEstado)
		 	 &&(!"".equals(strCosto)&&null!=strCosto)
		 	 &&(!"".equals(strFechaAlta)&&null!=strFechaAlta)
		     &&(!"".equals(strUsuario)&&null!=strUsuario)
		 	   ) {
		 		
		 		LlantasDao llantasDao = Utils.getLlantasInstance(); 
				Llantas llantasDto = new Llantas();
				try {
				String strNextFolio = llantasDao.findNextFolio();
				java.util.Date utilFechaAlta = Utils.getyyyyMMddsdfInstance().parse(strFechaAlta);
				java.sql.Timestamp sqlFechaAlata = new java.sql.Timestamp(utilFechaAlta.getTime());
				
				llantasDto.setFolio(Integer.parseInt(strNextFolio));/**numeric](18,0)NULL,**/
				llantasDto.setNombre(strCodigo);/**varchar](50)NULL,**/
				llantasDto.setMedida(strMedida);/**varchar](50)NULL,**/
				llantasDto.setMarca(strMarca);/**varchar](50)NULL,**/
				llantasDto.setCosto(Double.parseDouble(strCosto));/**float]NULL,**/
				llantasDto.setProfundidad_inicial(Integer.parseInt(strProfundidad));/**int]NULL,**/
				llantasDto.setProfundidad_actual(Integer.parseInt(strProfundidad));/**int]NULL,**/
				llantasDto.setFecha_alta(sqlFechaAlata);/**datetime]NULL,**/
				llantasDto.setFecha_revision(null);/**datetime]NULL,**/
				llantasDto.setEstado("ACTIVA");/**varchar](50)NULL,**/
				llantasDto.setUnidad(null);/**varchar](50)NULL,**/
				llantasDto.setPosicion(null);/**varchar](50)NULL,**/
				llantasDto.setPresion(null);/**varchar](50)NULL,**/
				llantasDto.setModelo(strModelo);/**nvarchar](50)NULL,**/
				llantasDto.setKmacumulado(0f);/**real]NULL,**/
				llantasDto.setNota(null);/**nvarchar](max)NULL,**/
				llantasDto.setCostoacumulado(0f);/**real]NULL,**/
				llantasDto.setUsuario(strUsuario);/**nvarchar](50)NULL,**/
				llantasDto.setKminicial(0f);/**real]NULL,**/
				llantasDto.setKmfinal(0f);/**real]NULL,**/
				llantasDto.setTipo(strEstado/*Estado*/);/**nchar](10)NULL,**/
				llantasDto.setFechacaptura(sqlFechaAlata);/**datetime]NULL,**/
				llantasDto.setOperador(null);/**varchar](50)NULL**/
				
				String strInsert = llantasDao.insert(llantasDto);
				 if(null==strInsert) {
						
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=AdministrarLlantasCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				 }else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				 }
				
				}catch(SQLException sqle) {
					if(sqle.toString().contains("llantas_nomb_uc")) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Ya existe una llanta con el codigo:"+strCodigo);
						return;
					}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
					}
			
				}catch(ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				}

		 		
		 	} /** END valida Campos **/
		 		
		}else if("validaCodigo".equals(strAccionAdministrarLlantas)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
		  	String strpCodigo = request.getParameter("pCodigo"); 
		  	LlantasDao llantasDao = Utils.getLlantasInstance(); 
		  	try {
				String validaCodigo = llantasDao.validaCodigo(strpCodigo);
				if(null==validaCodigo) {
					out.print("");
				}else {
					out.print("Ya existe codigo");
				}
				out.close();
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
		  	out.close();
		}else if("Buscar".equals(strAccionAdministrarLlantas)) {
			String strllantaTrx = request.getParameter("llantaTrx"); 
			LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
			try {
				Llantas llantasDto = llantasDaoImpl.findByNombre(strllantaTrx);
				request.setAttribute("llanta",llantasDto);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/llantas/inquiry/AdministrarLlantasRO.jsp").forward(request, response);
			return;
		}else if("Modificar".equals(strAccionAdministrarLlantas)) {
			String strllantaTrx = request.getParameter("llantaTrx"); 
			LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
			try {
				Llantas llantasDto = llantasDaoImpl.findByNombre(strllantaTrx);
				request.setAttribute("llanta",llantasDto);
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/llantas/upd/AdministrarLlantasUpd.jsp").forward(request, response);
			return;
		}else if("Regresar".equals(strAccionAdministrarLlantas)) {
			
			LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
			try {
			 List<Llantas> listLlantas	= llantasDaoImpl.findAll();
			 if(null!=listLlantas&&listLlantas.size()>0) {
				 request.setAttribute("listLlantas", listLlantas);
			 }
			 
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS ADMINISTRAR");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS ADMINISTRAR");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/AdministrarLlantas.jsp").include(request, response);  
		    return; 
		}else if("Update".equals(strAccionAdministrarLlantas)) {
			
			String strCodigo = request.getParameter("codigo");
			String strMedida = request.getParameter("medida");
			String strMarca  = request.getParameter("marca");
			String strModelo = request.getParameter("modelo");
			String strProfundidad = request.getParameter("profundidad");
			String strTipo = request.getParameter("tipo");
			String strCosto  = request.getParameter("costo");
		 	String strFechaAlta  = request.getParameter("fechaAlta");
			
			
			Llantas llantaDto = new Llantas(); 

			llantaDto.setNombre(strCodigo);
			llantaDto.setMedida(strMedida);
			llantaDto.setMarca(strMarca);
			llantaDto.setModelo(strModelo);
			llantaDto.setProfundidad_inicial(Integer.parseInt(strProfundidad));
			llantaDto.setTipo(strTipo);
			llantaDto.setCosto(Double.parseDouble(strCosto));
			
			try {
				java.util.Date utilFechaAlta = Utils.getyyyyMMddsdfInstance().parse(strFechaAlta);
				java.sql.Timestamp sqlFechaAlta = new java.sql.Timestamp(utilFechaAlta.getTime());
				llantaDto.setFecha_alta(sqlFechaAlta);
			} catch (ParseException pe) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}
			
			
			LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
			try {
				
				String strUpdate = llantasDaoImpl.update(llantaDto);
				if(null!=strUpdate) {
					/* TODO Auto-generated catch block sqle.printStackTrace(); */
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
					return;
				}
				
				 List<Llantas> listLlantas	= llantasDaoImpl.findAll();
				 if(null!=listLlantas&&listLlantas.size()>0) {
					 request.setAttribute("listLlantas", listLlantas);
				 }
				
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS ADMINISTRAR");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS ADMINISTRAR");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/AdministrarLlantas.jsp").include(request, response);  
		    return; 
		}else if("Borrar".equals(strAccionAdministrarLlantas)) {
			String strllantaTrx = request.getParameter("llantaTrx"); 
			LlantasDao llantasDaoImpl = Utils.getLlantasInstance(); 
			try {
				String strDelete = llantasDaoImpl.deleteByNombre(strllantaTrx);
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}
				 List<Llantas> listLlantas	= llantasDaoImpl.findAll();
				 if(null!=listLlantas&&listLlantas.size()>0) {
					 request.setAttribute("listLlantas", listLlantas);
				 }
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS ADMINISTRAR");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS ADMINISTRAR");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/AdministrarLlantas.jsp").include(request, response);  
		    return; 
		}
		else {
			response.getWriter().println("Aun sin implementacion");
			return;
		}
	}

}
