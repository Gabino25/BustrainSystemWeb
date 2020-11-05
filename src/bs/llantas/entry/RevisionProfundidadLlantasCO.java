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
import bs.llantas.entry.dao.MovimientosLlantasDao;
import bs.llantas.entry.dto.MovimientosLlantas;
import bs.util.Utils;

/**
 * Servlet implementation class RevisionProfundidadLlantas
 */
@WebServlet(name="RevisionProfundidadLlantasCO",urlPatterns= {"/RevisionProfundidadLlantasCO"})
public class RevisionProfundidadLlantasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevisionProfundidadLlantasCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionMovimientosLlantas = request.getParameter("accionMovimientosLlantas"); 
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				response.getWriter().println("Session Invalida REVISION LLANTAS");
				return; 
			}
		
		if("Salir".equals(strAccionMovimientosLlantas)) {
		
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
	
		}else if("Guardar".equals(strAccionMovimientosLlantas)){
			
			String strUnidad = request.getParameter("unidad");                        
			String strLlanta = request.getParameter("llanta");                        
			String strKilometraje = request.getParameter("kilometraje");              
			String strFecha = request.getParameter("fecha");                          
			String strPosicion = request.getParameter("posicion");                    
			String strOperador = request.getParameter("operador");                    
			String strProfundidad = request.getParameter("profundidad");              
			String strObservaciones = request.getParameter("observaciones");      
			
		
			
			if((!"".equals(strUnidad)&&null!=strUnidad)
			  &&(!"".equals(strLlanta)&&null!=strLlanta)
			  &&(!"".equals(strKilometraje)&&null!=strKilometraje)
			  &&(!"".equals(strFecha)&&null!=strFecha)
			  &&(!"".equals(strPosicion)&&null!=strPosicion)
			  &&(!"".equals(strOperador)&&null!=strOperador)
			  &&(!"".equals(strProfundidad)&&null!=strProfundidad)
			  //&&(!"".equals(strObservaciones)&&null!=strObservaciones)
			  ) {
				
				MovimientosLlantasDao movimientosLlantasDao = Utils.getMovimientosLlantasInstance(); 
				try {
				String strNextFolio = movimientosLlantasDao.findNextFolio();
				MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
				
				movimientosLlantasDto.setFolio(Integer.parseInt(strNextFolio));/*[FOLIO] [int] NULL, */
				movimientosLlantasDto.setNombre(strLlanta);/*[NOMBRE] [varchar](50) NULL,*/
				movimientosLlantasDto.setOperador(strOperador);/*[OPERADOR] [varchar](50) NULL,*/
				java.util.Date utilFecha = Utils.getyyyyMMddsdfInstance().parse(strFecha); 
				java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
				movimientosLlantasDto.setFecha(sqlFecha);/*[FECHA] [datetime] NULL,*/
				movimientosLlantasDto.setPosicion(strPosicion);/*[POSICION] [varchar](50) NULL,*/
				movimientosLlantasDto.setUnidad(strUnidad);/*[UNIDAD] [varchar](50) NULL,*/
				movimientosLlantasDto.setPresion(null);/*[PRESION] [varchar](50) NULL, */
				movimientosLlantasDto.setObservaciones(strObservaciones);/*[OBSERVACIONES] [varchar](max) NULL,*/
				movimientosLlantasDto.setTipo_mov("REVISION DE PROFUNDIDAD");/*[TIPO_MOV] [nvarchar](50) NULL, */
				movimientosLlantasDto.setKilometraje(strKilometraje);/*[KILOMETRAJE] [nchar](10) NULL, */
				movimientosLlantasDto.setUsuario(strUsuario);/*[USUARIO] [nvarchar](50) NULL,*/
				java.sql.Timestamp sqlFechacaptura = new java.sql.Timestamp(new java.util.Date().getTime());
				movimientosLlantasDto.setFechacaptura(sqlFechacaptura);/*[FECHACAPTURA] [datetime] NULL, */
				movimientosLlantasDto.setPresionanterior(null);/*[PRESIONANTERIOR] [nvarchar](50) NULL,*/
				movimientosLlantasDto.setCosto(0f);/*[COSTO] [real] NULL,*/
				movimientosLlantasDto.setNota(null/**Solo Observaciones strNota**/);/*[NOTA] [text] NULL, */
				movimientosLlantasDto.setKmrecorrido(0f);/*[KMRECORRIDO] [real] NULL,*/
				movimientosLlantasDto.setProf(Float.parseFloat(strProfundidad));/*[PROF] [real] NULL*/
				
				//FALTA AGREGAR OPERADOR
				String strInsert = movimientosLlantasDao.insert(movimientosLlantasDto);
				if(null==strInsert) {
					String strUpdate= Utils.getLlantasInstance().updateProfundidad(movimientosLlantasDto.getProf()
							, Float.parseFloat(movimientosLlantasDto.getKilometraje())
							,movimientosLlantasDto.getFecha()
							,movimientosLlantasDto.getNombre());
					 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=RevisionProfundidadLlantasCO");
					 response.sendRedirect( urlWithSessionID );
					 return;
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
					return;
				}
				
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} catch(ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				}
				
			}else {
				response.getWriter().println("No se pudieron validar los parametros.");
			}

		}else if("obtieneUnidadPosicion".equals(strAccionMovimientosLlantas)) {
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			MovimientosLlantasDao movimientosLlantasDao = Utils.getMovimientosLlantasInstance(); 
			String strpNombreLlanta = request.getParameter("pNombreLlanta");
			try {
				String strUnidadPosicion = movimientosLlantasDao.findUnidadPosicion(strpNombreLlanta);
				if(null==strUnidadPosicion) {
					out.print("");
				}else {
					out.print(strUnidadPosicion);
				}
				out.close();
				return; 
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			}
		}else if("Buscar".equals(strAccionMovimientosLlantas)) {
			MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance();
			String strFolio = request.getParameter("movLlantaTrx"); 
			try {
				MovimientosLlantas asignacionLlantaDto = movimientosLlantasDaoImpl.findByFolio(Integer.parseInt(strFolio));
				request.setAttribute("movimientoLlanta",asignacionLlantaDto);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/llantas/inquiry/RevisionProfundidadLlantasRO.jsp").forward(request, response);
			return;
		}else if("Regresar".equals(strAccionMovimientosLlantas)) {
			MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance(); 
			try {
			List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllRevDeProf(); 
			if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
				request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
			}
			} catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS PROFUNDIDAD");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS PROFUNDIDAD");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/RevisionProfundidadLlantas.jsp").forward(request, response);
			return;
		}else if("Modificar".equals(strAccionMovimientosLlantas)) {
			MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance();
			String strFolio = request.getParameter("movLlantaTrx"); 
			try {
				MovimientosLlantas asignacionLlantaDto = movimientosLlantasDaoImpl.findByFolio(Integer.parseInt(strFolio));
				request.setAttribute("movimientoLlanta",asignacionLlantaDto);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/llantas/upd/RevisionProfundidadLlantasUpd.jsp").forward(request, response);
			return;
		}else if("Update".equals(strAccionMovimientosLlantas)) {
		
			String strFolio = request.getParameter("folio"); 
			String strOperador = request.getParameter("operador");
			String strLlanta = request.getParameter("llanta");
			String strPosicion = request.getParameter("posicion");
			String strFecha = request.getParameter("fecha");
			String strUnidad = request.getParameter("unidad");
			String strKilometraje = request.getParameter("kilometraje");
			String strObservaciones = request.getParameter("observaciones");
			String strProfundidad = request.getParameter("profundidad"); 
			
			MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance();
			
			
			try {
			MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
			
			movimientosLlantasDto.setFolio(Integer.parseInt(strFolio));
			movimientosLlantasDto.setOperador(strOperador);
			movimientosLlantasDto.setNombre(strLlanta);
			movimientosLlantasDto.setPosicion(strPosicion);
			java.util.Date utilFecha = Utils.getyyyyMMddsdfInstance().parse(strFecha); 
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
			movimientosLlantasDto.setFecha(sqlFecha);
			movimientosLlantasDto.setUnidad(strUnidad);
			movimientosLlantasDto.setKilometraje(strKilometraje);
			movimientosLlantasDto.setProf(Float.parseFloat(strProfundidad));
			movimientosLlantasDto.setObservaciones(strObservaciones);			
			
		
				String strUpdate = movimientosLlantasDaoImpl.update(movimientosLlantasDto);
				if(null==strUpdate) {
					List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllRevDeProf();
					if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
						request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
					}
				}else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
					return;
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS PROFUNDIDAD");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS PROFUNDIDAD");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/RevisionProfundidadLlantas.jsp").forward(request, response);
				return;
				
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}catch(ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}
			
		}else if("Borrar".equals(strAccionMovimientosLlantas)) {
			String strmovLlantaTrx = request.getParameter("movLlantaTrx"); 
			MovimientosLlantasDao movimientosLlantasDaoImpl = Utils.getMovimientosLlantasInstance();
			try {
				String strDelete = movimientosLlantasDaoImpl.deleteByFolio(Integer.parseInt(strmovLlantaTrx));
			    if(null!=strDelete) {
			    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
			    }else {
			    	List<MovimientosLlantas> listMovimientosLlantas =  movimientosLlantasDaoImpl.findAllRevDeProf(); 
					if(null!=listMovimientosLlantas&&listMovimientosLlantas.size()>0) {
						request.setAttribute("listMovimientosLlantas", listMovimientosLlantas);
					}
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
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"LLANTAS PROFUNDIDAD");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla LLANTAS PROFUNDIDAD");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/llantas/entry/RevisionProfundidadLlantas.jsp").forward(request, response);
			return;
		}
		else {
			response.getWriter().println("Aun sin implementacion");
			return; 
		}
	}

}
