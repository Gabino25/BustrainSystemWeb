package bs.ap.entry;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.ap.dao.ConceptosDao;
import bs.ap.dao.FacturasDao;
import bs.ap.dto.Conceptos;
import bs.ap.dto.Facturas;
import bs.util.Utils;

/**
 * Servlet implementation class FacturasCO
 */
@WebServlet(name="FacturasCO",urlPatterns= {"/FacturasCO"})
public class FacturasCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacturasCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario")) {
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Session Invalida.");
				return;
			}
		
		
		String straccionFactura = request.getParameter("accionFactura"); 
		String straccionFacturaL = request.getParameter("accionFacturaL"); 
		
		
		if("GuardarFactura".equals(straccionFacturaL)) {
			String strproveedorV2 = request.getParameter("proveedorV2"); 
			String strfolioV2 = request.getParameter("folioV2"); 
			String strcostoV2 = request.getParameter("costoV2"); 
			String strfechaV2 = request.getParameter("fechaV2"); 
			String strordenV2 = request.getParameter("ordenV2"); 
			String strtipoV2 = request.getParameter("tipoV2");
			
			if((!"".equals(strproveedorV2)&&null!=strproveedorV2)
			  &&(!"".equals(strfolioV2)&&null!=strfolioV2)
			  &&(!"".equals(strcostoV2)&&null!=strcostoV2)
			  &&(!"".equals(strfechaV2)&&null!=strfechaV2)
			  &&(!"".equals(strordenV2)&&null!=strordenV2) 
			  &&(!"".equals(strtipoV2)&&null!=strtipoV2)
			  ) {
				
				FacturasDao facturas = Utils.getFacturasInstance(); 
				ConceptosDao conceptos = Utils.getConceptosFacturasInstance();
				Facturas factura = new Facturas(); 
				try {
				String strNextNumfact = facturas.findNextNumfact(); 
				factura.setNumfact(Long.parseLong(strNextNumfact));/** [NUMFACT] [bigint] NOT NULL, **/
				factura.setProveedor(strproveedorV2);/** [PROVEEDOR] [varchar](80) NULL, **/
				if(!"".equals(strordenV2)&&null!=strordenV2) {
				factura.setOrden(new BigDecimal(strordenV2));/** [ORDEN] [numeric](18, 0) NULL, **/
				}
				factura.setFolio(strfolioV2);/** [FOLIO] [varchar](50) NULL, **/
				factura.setCosto(Double.parseDouble(strcostoV2));/** [COSTO] [float] NULL, **/
				SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
				java.util.Date utilFecha;
				utilFecha = yyyyMMdd.parse(strfechaV2);
				java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
				factura.setFecha(sqlFecha); /** [FECHA] [smalldatetime] NULL, **/
				factura.setTipo(strtipoV2);/** [TIPO] [nvarchar](50) NULL, **/
				factura.setUsuario(strUsuario);/** [USUARIO] [nvarchar](50) NULL, **/
				factura.setEstado(strtipoV2);/** [ESTADO] [varchar](50) NULL, **/
				
				String strinputCountRows = request.getParameter("inputCountRows"); 
				List<Conceptos> lisConceptos = new ArrayList<Conceptos>(); 
				if(null!=strinputCountRows&&!"".equals(strinputCountRows)) {
				   int intInputCountRows = Integer.parseInt(strinputCountRows);
				   for(int i=2;i<intInputCountRows;i++) {
					 String strConcepto  = request.getParameter("concepto"+i);
					 String strUnidad  = request.getParameter("unidad"+i);
					 String strPrecio  = request.getParameter("precio"+i);
					 String strGasto  = request.getParameter("gasto"+i);
					 
					 System.out.println("strConcepto:"+strConcepto);
					 System.out.println("strUnidad:"+strUnidad);
					 System.out.println("strPrecio:"+strPrecio);
					 System.out.println("strGasto:"+strGasto);
					 
					 Conceptos concepto = new Conceptos(); 
					 String strNextNumconcepto = conceptos.findNextNumconcepto(); 
					 concepto.setNumconcepto(Long.parseLong(strNextNumconcepto));/** [NUMCONCEPTO] [bigint] NOT NULL, **/
					 concepto.setFolio(factura.getFolio());/** [FOLIO] [varchar](50) NULL, **/
					 concepto.setProveedor(factura.getProveedor());/** [PROVEEDOR] [varchar](80) NULL, **/
					 concepto.setConcepto(strConcepto);/** [CONCEPTO] [text] NULL, **/
					 concepto.setNumfact(factura.getNumfact());/** [NUMFACT] [bigint] NOT NULL, **/
					 concepto.setEco(strUnidad);/** [ECO] [nvarchar](50) NULL, **/
					 concepto.setCosto(Double.parseDouble(strPrecio));/** [COSTO] [float] NULL, **/
					 concepto.setFecha(factura.getFecha());/** [FECHA] [datetime] NULL, **/
					 concepto.setGasto(strGasto);/** [GASTO] [varchar](50) NULL, **/
					 concepto.setFechaalta(factura.getFecha());/** [FECHAALTA] [datetime] NULL, **/
					 concepto.setTipo(factura.getTipo());/** [TIPO] [varchar](50) NULL, **/
					 concepto.setOrden(factura.getOrden());/** [ORDEN] [numeric](18, 0) NULL, **/
					 concepto.setUsuario(strUsuario);/** [USUARIO] [nvarchar](50) NULL, **/
					 
					 lisConceptos.add(concepto); 
					 
				   }
				}
				
				 if(null!=lisConceptos&&lisConceptos.size()>0) {
					String strInsert = facturas.insert(factura, lisConceptos); 
					if(null!=strInsert) {
						 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strInsert);
						 return;
					}else {
						 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=FacturasCO");
						 response.sendRedirect( urlWithSessionID );
						 return;
					}
				 }else {
					 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"No se pudieron recuperar las lineas de la factura");
					 return;
				 }
				
				} catch (ParseException pe) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
					return;
				} 
				catch(SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString()+" - "+sqle.getErrorCode());
					return;
				}
				
			 }else {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al validar Parametros FacturasCO");
				 return;
			 }
			
		}else if("Buscar".equals(straccionFactura)) {
			String strfacturaTrx = request.getParameter("facturaTrx"); 
			FacturasDao facturasDao = Utils.getFacturasInstance();
			ConceptosDao conceptosDao = Utils.getConceptosFacturasInstance();
			
			try {
				Facturas facturaDto = facturasDao.findByNumfact(Long.parseLong(strfacturaTrx));
				List<Conceptos> listConceptos = conceptosDao.findByNumfact(Long.parseLong(strfacturaTrx));
				request.setAttribute("facturaDto", facturaDto);
				request.setAttribute("listConceptosFactura", listConceptos);
			} catch (NumberFormatException nfe) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				 return;
			} catch (SQLException sqle) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				 return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/ap/inquiry/FacturasRO.jsp").forward(request, response);
			return;
			
		}else if("Modificar".equals(straccionFactura)) {
			
			String strfacturaTrx = request.getParameter("facturaTrx"); 
			FacturasDao facturasDao = Utils.getFacturasInstance();
			ConceptosDao conceptosDao = Utils.getConceptosFacturasInstance();
			
			try {
				Facturas facturaDto = facturasDao.findByNumfact(Long.parseLong(strfacturaTrx));
				List<Conceptos> listConceptos = conceptosDao.findByNumfact(Long.parseLong(strfacturaTrx));
				request.setAttribute("facturaDto", facturaDto);
				request.setAttribute("listConceptosFactura", listConceptos);
			} catch (NumberFormatException nfe) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				 return;
			} catch (SQLException sqle) {
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				 return;
			}
			
			request.getRequestDispatcher("/WEB-INF/bs/ap/upd/FacturasUpd.jsp").forward(request, response);
			return;
			
		}
		else if("Regresar".equals(straccionFactura)) {
			FacturasDao facturasDao = Utils.getFacturasInstance();
			try {
				List<Facturas> listfacturas = facturasDao.findTop1000();
				if(null!=listfacturas&&listfacturas.size()>0) {
					request.setAttribute("listFacturas", listfacturas);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"COMPRAS CAPTURAR FACTURAS");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla COMPRAS CAPTURAR FACTURAS");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/ap/entry/Facturas.jsp").forward(request, response);
			return;
		}else if("Update".equals(straccionFactura)) {
			
			String strnumfact = request.getParameter("numfact");
			String strproveedor = request.getParameter("proveedor");
			String strfolio = request.getParameter("folio");
			String strcosto = request.getParameter("costo");
			String strorden = request.getParameter("orden");
			String strfecha = request.getParameter("fecha"); 
			String strtipo = request.getParameter("tipo");
			
		
			
			try {
			Facturas facturas = new Facturas(); 
			facturas.setNumfact(Long.parseLong(strnumfact)); /** [NUMFACT] [bigint] NOT NULL, **/
			facturas.setProveedor(strproveedor); /** [PROVEEDOR] [varchar](80) NULL, **/
			facturas.setOrden(new BigDecimal(strorden));/** [ORDEN] [numeric](18, 0) NULL, **/
			facturas.setFolio(strfolio);/** [FOLIO] [varchar](50) NULL, **/
			facturas.setCosto(Double.parseDouble(strcosto));/** [COSTO] [float] NULL, **/
			SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
			java.util.Date utilFecha;
			utilFecha = yyyyMMdd.parse(strfecha);
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
			facturas.setFecha(sqlFecha);  /** [FECHA] [smalldatetime] NULL, **/
			facturas.setTipo(strtipo);/** [TIPO] [nvarchar](50) NULL, **/
			facturas.setUsuario(null);/** [USUARIO] [nvarchar](50) NULL, **/
			facturas.setEstado(null);/** [ESTADO] [varchar](50) NULL, **/
			
			
			String strinputCountRows = request.getParameter("inputCountRows");
			int intinputCountRows = Integer.parseInt(strinputCountRows); 
			List<Conceptos> listConceptos = new ArrayList<Conceptos>(); 
			for(int i =1;i<=intinputCountRows-1;i++) {
				
				
				String strNumconcepto = request.getParameter("numconcepto"+i);
				String strConcepto = request.getParameter("concepto"+i);
				String strUnidad = request.getParameter("unidad"+i);
				String strPrecio = request.getParameter("precio"+i);
				String strGasto = request.getParameter("gasto"+i);
				
				Conceptos concepto = new Conceptos(); 
				concepto.setNumconcepto(Long.parseLong(strNumconcepto));/** [NUMCONCEPTO] [bigint] NOT NULL, **/
				concepto.setFolio(null);/** [FOLIO] [varchar](50) NULL,**/
				concepto.setProveedor(null);/** [PROVEEDOR] [varchar](80) NULL,**/
				concepto.setConcepto(strConcepto);/** [CONCEPTO] [text] NULL,**/
				concepto.setNumfact(-1l);/** [NUMFACT] [bigint] NOT NULL, **/
				concepto.setEco(strUnidad);/** [ECO] [nvarchar](50) NULL, **/
				concepto.setCosto(Double.parseDouble(strPrecio));/** [COSTO] [float] NULL,**/
				concepto.setFecha(null);/** [FECHA] [datetime] NULL, **/
				concepto.setGasto(strGasto);/** [GASTO] [varchar](50) NULL,**/
				concepto.setFechaalta(null);/** [FECHAALTA] [datetime] NULL, **/
				concepto.setTipo(null);/** [TIPO] [varchar](50) NULL, **/
				concepto.setOrden(null);/** [ORDEN] [numeric](18, 0) NULL, **/
				concepto.setUsuario(null);/** [USUARIO] [nvarchar](50) NULL, **/
				
				listConceptos.add(concepto);
			  } /** END for **/
			
			FacturasDao facturasDao = Utils.getFacturasInstance();
			String strUpdate = facturasDao.update(facturas,listConceptos); 
			
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				try {
					List<Facturas> listfacturas = facturasDao.findTop1000();
					if(null!=listfacturas&&listfacturas.size()>0) {
						request.setAttribute("listFacturas", listfacturas);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"COMPRAS CAPTURAR FACTURAS");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla COMPRAS CAPTURAR FACTURAS");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/ap/entry/Facturas.jsp").forward(request, response);
				return;
			 }
			
			}
			catch (ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			
			
		}else if("Salir".equals(straccionFactura)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		}else if("Borrar".equals(straccionFactura)) {
			String strfacturaTrx = request.getParameter("facturaTrx"); 
			FacturasDao facturasDao = Utils.getFacturasInstance();
			try {
				String strdelete = facturasDao.deleteByNumFact(Long.parseLong(strfacturaTrx));
				if(null!=strdelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strdelete);
					return;
				}else {
					
					try {
						List<Facturas> listfacturas = facturasDao.findTop1000();
						if(null!=listfacturas&&listfacturas.size()>0) {
							request.setAttribute("listFacturas", listfacturas);
						}
					} catch (SQLException sqle) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
						return;
					}
					
					PermisosDao permisosDao = Utils.getPermisosInstance(); 
					try {
						String strNivel = permisosDao.findNivel(strUsuario,"COMPRAS CAPTURAR FACTURAS");
						if(null==strNivel) {
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla COMPRAS CAPTURAR FACTURAS");
							return;
						}else {
						  request.setAttribute("rAttrNivel", strNivel);
						}
					} catch (SQLException sqle) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
						return;
					} 
					
					request.getRequestDispatcher("/WEB-INF/bs/ap/entry/Facturas.jsp").forward(request, response);
					return;
					
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
		}
		else {
			response.getWriter().println("Aun sin implementacion");
		}
	}

}
