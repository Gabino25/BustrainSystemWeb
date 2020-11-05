package bs.vales.transpt.entry;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatRutasDao;
import bs.catalogo.dto.CatRutas;
import bs.util.Utils;
import bs.vales.transpt.assign.dao.AsignacionValesDao;
import bs.vales.transpt.entry.dao.ValesDao;
import bs.vales.transpt.entry.dto.Vales;

/**
 * Servlet implementation class EntryTransporteValesCO
 */
@WebServlet(name="EntryTransporteValesCO",urlPatterns= {"/EntryTransporteValesCO"})
public class EntryTransporteValesCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryTransporteValesCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strAccionEntryVales = request.getParameter("accionEntryVales"); 
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
		 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
		else {
			response.getWriter().println("Session Invalida");
			return; 
		}
		
		
		if("obtieneOperador".equals(strAccionEntryVales)) {
			String strpFolioValue = request.getParameter("pFolioValue"); 
			ValesDao valesDao = Utils.getValesInstance();
			
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			
			try {
				String strFolio = valesDao.findByFolio(Long.parseLong(strpFolioValue));
				if(null!=strFolio) {
					
					out.print("Ya se capturo vale"); /** Msg Fijo **/
		        	out.close();
					return; 
				}
			} catch (NumberFormatException nfe) {
				out.print("EXCEPTION:"+nfe.toString());
				out.close();
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			} 
			
			AsignacionValesDao asignacionValesDao = Utils.getAsignacionValesInstance();
			try {
			 String strFolioBetweenIniFin = asignacionValesDao.findFolioBetweenIniFin(Long.parseLong(strpFolioValue));
			 if(null==strFolioBetweenIniFin) {
				 out.print("Folio aun no asignado"); /** Msg Fijo **/
	        	 out.close();
				 return; 
			 }else {
				 out.print(strFolioBetweenIniFin/*"MANUEL BAUTISTA DE LA CRUZ"*/);
				 out.close();
				 return;
			 }
			} catch (NumberFormatException nfe) {
				out.print("EXCEPTION:"+nfe.toString());
				out.close();
			} catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			} 
			 	
		}else if("Guardar".equals(strAccionEntryVales)) {
			
			String strFolio = request.getParameter("folio"); 
			String strFecha = request.getParameter("fecha"); 
			String strOperador = request.getParameter("operador"); 
			String strUnidad = request.getParameter("unidad"); 
			String strEmpresa = request.getParameter("empresa"); 
			String strRuta = request.getParameter("ruta"); 
			String strKmInicial = request.getParameter("kmInicial"); 
			String strHrInicial = request.getParameter("hrInicial"); 
			String strKmFinal = request.getParameter("kmFinal"); 
			String strHrFinal = request.getParameter("hrFinal"); 
			String strTipoViaje = request.getParameter("tipoViaje"); 
			String strTipoCobro = request.getParameter("tipoCobro"); 
			String strTipoUnidad = request.getParameter("tipoUnidad"); 
			String strCostoViaje = request.getParameter("costoViaje"); 
			String strObservaciones = request.getParameter("observaciones"); 
			String strCentroCostos = request.getParameter("centroCostos"); /** SeSolicitoRemover **/
			
			if(!"".equals(strFolio)&&null!=strFolio
			  &&!"".equals(strFecha)&&null!=strFecha
			  &&!"".equals(strOperador)&&null!=strOperador
			  &&!"".equals(strUnidad)&&null!=strUnidad
			  &&!"".equals(strEmpresa)&&null!=strEmpresa
			  &&!"".equals(strRuta)&&null!=strRuta
			  &&!"".equals(strKmInicial)&&null!=strKmInicial
			  &&!"".equals(strHrInicial)&&null!=strHrInicial
			  &&!"".equals(strKmFinal)&&null!=strKmFinal
			  &&!"".equals(strHrFinal)&&null!=strHrFinal
			  &&!"".equals(strTipoViaje)&&null!=strTipoViaje
			  &&!"".equals(strTipoCobro)&&null!=strTipoCobro
			  &&!"".equals(strTipoUnidad)&&null!=strTipoUnidad
			  &&!"".equals(strCostoViaje)&&null!=strCostoViaje
			 /** &&!"".equals(strObservaciones)&&null!=strObservaciones CampoNoObligatorio **/
			 /* &&!"".equals(strCentroCostos)&&null!=strCentroCostos Se solicitoRemover*/
			  ) {
				
				ValesDao valesDao = Utils.getValesInstance();
				try {
					String strNextNumvale = valesDao.findNextNumvale();
					Vales valesDto = new Vales(); 
					
					valesDto.setNumvale(Long.parseLong(strNextNumvale));         /* [bigint] not null,          */
					valesDto.setFolio(Long.parseLong(strFolio));           /* [bigint] not null,            */
					java.util.Date utilFecha = Utils.getyyyyMMddsdfInstance().parse(strFecha); 
					java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime()); 
					valesDto.setFecha(sqlFecha);           /* [datetime] null,              */
					valesDto.setObservaciones(strObservaciones);   /* [text] null,          */
					valesDto.setCosto(Double.parseDouble(strCostoViaje));           /* [float] null,                 */
					valesDto.setRuta(strRuta);            /* [nvarchar](100) null,          */
					valesDto.setOperador(strOperador);        /* [nvarchar](50) null,       */
					valesDto.setEco(strUnidad);             /* [nvarchar](50) null,            */
					valesDto.setCliente(strEmpresa);         /* [nvarchar](250) null,       */
					valesDto.setKminicial(new BigDecimal(strKmInicial));       /* [numeric](18, 0) null,    */
					valesDto.setHorainicial(strHrInicial);     /* [varchar](50) null,     */
					valesDto.setKmfinal(new BigDecimal(strKmFinal));         /* [numeric](18, 0) null,      */
					valesDto.setHorafinal(strHrFinal);       /* [varchar](50) null,       */
					valesDto.setTipoviaje(strTipoViaje);       /* [varchar](50) null,       */
					valesDto.setTipocobro(strTipoCobro);       /* [varchar](50) null,       */
					valesDto.setCentrocostos(strCentroCostos);    /* [nvarchar](50) null,   */
					valesDto.setFactura(null);         /* [nvarchar](50) null,        */
					valesDto.setUsuario(strUsuario);         /* [nvarchar](50) null,        */
					java.util.Date utilFechaCaptura = new java.util.Date();
					java.sql.Timestamp sqlFechaCaptura = new java.sql.Timestamp(utilFechaCaptura.getTime());
					valesDto.setFechacaptura(sqlFechaCaptura);    /* [datetime] null,       */
					valesDto.setTipounidad(strTipoUnidad);      /* [varchar](50) null,      */
					
					String strInsert = valesDao.insert(valesDto); 
					if(null==strInsert) {
						 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=EntryTransporteValesCO");
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
				 response.getWriter().println("Fallo al validar los parametros");
				 return;
			 }
			
		   }else if("Salir".equals(strAccionEntryVales)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		   }else if("selectRuta".equals(strAccionEntryVales)) {
			String strEmpresa = request.getParameter("empresa"); 
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			CatRutasDao catRutasDao = Utils.getCatRutasInstance(); 
			
			try {
				 out.println("<select id='selectRutaId' name='ruta' onchange='selectCosto();' required>");
				 out.println("<option value=''></option>"); 	
			java.util.List<CatRutas> listCatRutas = catRutasDao.findByEmpresa(strEmpresa); 
			if(null!=listCatRutas&&listCatRutas.size()>0) {
			 Iterator<CatRutas> iterator =listCatRutas.iterator();
			 while(iterator.hasNext()) {
				 CatRutas catRutasDto =  iterator.next(); 
				 out.println("<option value='"+catRutasDto.getRuta()+"'>"+catRutasDto.getRuta()+"</option>");
			 }
			}
			out.println("</select>");
			}catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			} 
		
			out.close();
			return; 
		}else if("selectCosto".equals(strAccionEntryVales)) {
			String strRuta = request.getParameter("ruta"); 
			PrintWriter out = response.getWriter();
			response.setContentType( "text/html; charset=UTF-8" );
			CatRutasDao catRutasDao = Utils.getCatRutasInstance();
			try {
			String strCosto = catRutasDao.findCostoByRuta(strRuta);
			out.print(strCosto);
			}catch (SQLException sqle) {
				out.print("EXCEPTION:"+sqle.toString());
				out.close();
			} 
			out.close();
			return;
		}else if("Buscar".equals(strAccionEntryVales)) {
			String strnumValeTrx = request.getParameter("numValeTrx"); 
			ValesDao valesDao = Utils.getValesInstance();
			try {
				Vales valesDto = valesDao.findByNumVale(Long.parseLong(strnumValeTrx));
				request.setAttribute("vale", valesDto);
				
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/inquiry/TransporteValesRO.jsp").forward(request, response);
			return;
		}else if("Regresar".equals(strAccionEntryVales)) {
			ValesDao valesDao = Utils.getValesInstance();
			try {
			    List<Vales> listVales = valesDao.findDif0();
				if(null!=listVales&&listVales.size()>0) {
					request.setAttribute("listVales", listVales);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"VALES CAPTURA");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla VALES CAPTURA");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/entry/EntryTransporteVales.jsp").forward(request, response);
			return;
		}else if("Modificar".equals(strAccionEntryVales)) {
			String strnumValeTrx = request.getParameter("numValeTrx"); 
			ValesDao valesDao = Utils.getValesInstance();
			try {
				Vales valesDto = valesDao.findByNumVale(Long.parseLong(strnumValeTrx));
				request.setAttribute("vale", valesDto);
				
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/upd/TransporteValesUpd.jsp").forward(request, response);
			return;
		}else if("Update".equals(strAccionEntryVales)) {
			String strnumValeTrx = request.getParameter("numValeTrx"); 
			ValesDao valesDao = Utils.getValesInstance();
			String strFolio = request.getParameter("folio"); 
			String strFecha = request.getParameter("fecha"); 
			String strOperador = request.getParameter("operador"); 
			String strUnidad = request.getParameter("unidad"); 
			String strEmpresa = request.getParameter("empresa"); 
			String strRuta = request.getParameter("ruta"); 
			String strKmInicial = request.getParameter("kmInicial"); 
			String strHrInicial = request.getParameter("hrInicial"); 
			String strKmFinal = request.getParameter("kmFinal"); 
			String strHrFinal = request.getParameter("hrFinal"); 
			String strTipoViaje = request.getParameter("tipoViaje"); 
			String strTipoCobro = request.getParameter("tipoCobro"); 
			String strTipoUnidad = request.getParameter("tipoUnidad"); 
			String strCostoViaje = request.getParameter("costoViaje"); 
			String strObservaciones = request.getParameter("observaciones"); 
			String strCentroCostos = request.getParameter("centroCostos"); 
			
			Vales valesDto = new Vales(); 
			
			try {
			valesDto.setNumvale(Long.parseLong(strnumValeTrx));         /* [bigint] not null,          */
			valesDto.setFolio(Long.parseLong(strFolio));           /* [bigint] not null,            */
			java.util.Date utilFecha = Utils.getyyyyMMddsdfInstance().parse(strFecha);
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime()); 
			valesDto.setFecha(sqlFecha);           /* [datetime] null,              */
			valesDto.setObservaciones(strObservaciones);   /* [text] null,          */
			valesDto.setCosto(Double.parseDouble(strCostoViaje));           /* [float] null,                 */
			valesDto.setRuta(strRuta);            /* [nvarchar](100) null,          */
			valesDto.setOperador(strOperador);        /* [nvarchar](50) null,       */
			valesDto.setEco(strUnidad);             /* [nvarchar](50) null,            */
			valesDto.setCliente(strEmpresa);         /* [nvarchar](250) null,       */
			valesDto.setKminicial(new BigDecimal(strKmInicial));       /* [numeric](18, 0) null,    */
			valesDto.setHorainicial(strHrInicial);     /* [varchar](50) null,     */
			valesDto.setKmfinal(new BigDecimal(strKmFinal));         /* [numeric](18, 0) null,      */
			valesDto.setHorafinal(strHrFinal);       /* [varchar](50) null,       */
			valesDto.setTipoviaje(strTipoViaje);       /* [varchar](50) null,       */
			valesDto.setTipocobro(strTipoCobro);       /* [varchar](50) null,       */
			valesDto.setCentrocostos(strCentroCostos);    /* [nvarchar](50) null,   */
			valesDto.setFactura(null);         /* [nvarchar](50) null,        */
			valesDto.setUsuario(strUsuario);         /* [nvarchar](50) null,        */
			java.util.Date utilFechaCaptura = new java.util.Date();
			java.sql.Timestamp sqlFechaCaptura = new java.sql.Timestamp(utilFechaCaptura.getTime());
			valesDto.setFechacaptura(sqlFechaCaptura);    /* [datetime] null,       */
			valesDto.setTipounidad(strTipoUnidad);      
			
			String strUpdate = valesDao.update(valesDto);
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				
				try {
				List<Vales> listVales =  valesDao.findDif0();
				if(null!=listVales&&listVales.size()>0) {
					request.setAttribute("listVales", listVales);
				}
				}catch(SQLException sqle){
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"VALES CAPTURA");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla VALES CAPTURA");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/entry/EntryTransporteVales.jsp").forward(request, response);
				return;
			}
			
			
			} catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
		}else if("Borrar".equals(strAccionEntryVales)) {
			String strnumValeTrx = request.getParameter("numValeTrx"); 
			ValesDao valesDao = Utils.getValesInstance();
			try {
				String strDelete = valesDao.deleteByNumVale(Long.parseLong(strnumValeTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					 List<Vales> listVales = valesDao.findDif0();
						if(null!=listVales&&listVales.size()>0) {
							request.setAttribute("listVales", listVales);
						}
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"VALES CAPTURA");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla VALES CAPTURA");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/vales/transpt/entry/EntryTransporteVales.jsp").forward(request, response);
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
		response.getWriter().println("Aun sin implementacion");
		return;
		}
		
	}

}
