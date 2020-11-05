package bs.catalogo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.admin.entry.dao.PermisosDao;
import bs.catalogo.dao.CatProveedoresDao;
import bs.catalogo.dto.CatProveedores;
import bs.util.Utils;

/**
 * Servlet implementation class ProveedoresCO
 */
@WebServlet(name="ProveedoresCO", urlPatterns= {"/ProveedoresCO"})
public class ProveedoresCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveedoresCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		
		String strUsuario = null; 
		if(null!=request.getSession().getAttribute("sAttrNombreUsuario"))
			 strUsuario = request.getSession().getAttribute("sAttrNombreUsuario").toString();
			else {
				request.getRequestDispatcher("/index.jsp").include(request, response);
//				response.getWriter().println("Fallo de inicio de Sesión. Favor de iniciar Sesión.");
				return; 
			}
		
		String strAccion = request.getParameter("accionName"); 
		
		if("Salir".equals(strAccion)) {
			request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").include(request, response);  
		    return; 
		} /** END if("Regresar".equals(strAccion)) { **/
		else if("Guardar".equals(strAccion)) {
			
			String strRfc = request.getParameter("rfc"); 
			String strNombreEmpresa = request.getParameter("nombreEmpresa"); 
			String strNombreRepresentante = request.getParameter("nombreRepresentante"); 
			String strDireccion = request.getParameter("direccion"); 
			String strTelefono = request.getParameter("telefono"); 
			String strFecha = request.getParameter("fecha"); 
			String strNotas = request.getParameter("notas"); 
			
			
			
			if(!"".equals(strRfc)&&null!=strRfc
			 &&!"".equals(strNombreEmpresa)&&null!=strNombreEmpresa
			 &&!"".equals(strNombreRepresentante)&&null!=strNombreRepresentante
			 &&!"".equals(strDireccion)&&null!=strDireccion
			 &&!"".equals(strTelefono)&&null!=strTelefono
			 &&!"".equals(strFecha)&&null!=strFecha
				  ) {
						
						 CatProveedoresDao catProveedoresDao = Utils.getCatProveedoresInstance();
				         CatProveedores catProveedores = new CatProveedores();
				         
				         try {
				         String strNextClave = catProveedoresDao.findNextClave(); 
				         
				         catProveedores.setClave(Integer.parseInt(strNextClave)); 
				         catProveedores.setRfc(strRfc);
				         catProveedores.setEmpresa(strNombreEmpresa);
				         catProveedores.setNombre(strNombreRepresentante);
				         catProveedores.setDireccion(strDireccion);
				         catProveedores.setTelefono(strTelefono);
				         
				         SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
						 java.util.Date utilFecha = yyyyMMdd.parse(strFecha); 
						 java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
							
				         catProveedores.setFecha(sqlFecha);
				         catProveedores.setDescripcion(strNotas);
				         
				         String strInsert = catProveedoresDao.insert(catProveedores); 
				         if(null==strInsert) {
				         String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=ProveedoresCO");
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
						}catch(ParseException pe) {
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
							return;
						}
				         
				} /** END valida parametros **/
			else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"No se pudo recuperar el formulario.");
				return;
			}
			 
		} /** END if("Guardar".equals(strAccion)) { **/
		else if("Buscar".equals(strAccion)) {
			String strclaveProveedorTrx = request.getParameter("claveProveedorTrx"); 
			CatProveedoresDao  catProveedoresDao = Utils.getCatProveedoresInstance();
			try {
				CatProveedores catProveedoresDto = catProveedoresDao.findByClave(Integer.parseInt(strclaveProveedorTrx));
			    request.setAttribute("Proveedor", catProveedoresDto);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/inquiry/ProveedoresMainRO.jsp").forward(request, response); 
		    return;
		}else if("Regresar".equals(strAccion)) {
			CatProveedoresDao catProveedoresDao = Utils.getCatProveedoresInstance(); /*new CatProveedoresDaoImpl();*/
			try {
				List<CatProveedores> listCatProveedores = catProveedoresDao.findAll();
			  if(null!=listCatProveedores&&listCatProveedores.size()>0) {
			  request.setAttribute("listCatProveedores", listCatProveedores);
			  }
			 } catch (SQLException sqle) {
				/* TODO Auto-generated catch block sqle.printStackTrace(); */
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			
			PermisosDao permisosDao = Utils.getPermisosInstance(); 
			try {
				String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO PROVEEDORES");
				if(null==strNivel) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO PROVEEDORES");
					return;
				}else {
				  request.setAttribute("rAttrNivel", strNivel);
				}
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			} 
			
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/ProveedoresMain.jsp").forward(request, response);
			return;
		}else if("Modificar".equals(strAccion)) {
			String strclaveProveedorTrx = request.getParameter("claveProveedorTrx"); 
			CatProveedoresDao  catProveedoresDao = Utils.getCatProveedoresInstance();
			try {
				CatProveedores catProveedoresDto = catProveedoresDao.findByClave(Integer.parseInt(strclaveProveedorTrx));
			    request.setAttribute("Proveedor", catProveedoresDto);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/ProveedoresMainUpd.jsp").forward(request, response); 
		    return;
		}else if("Update".equals(strAccion)) {
			String strClaveProveedorTrx = request.getParameter("claveProveedorTrx");
			String strRfc = request.getParameter("rfc"); 
			String strNombreEmpresa = request.getParameter("nombreEmpresa"); 
			String strNombreRepresentante = request.getParameter("nombreRepresentante"); 
			String strDireccion = request.getParameter("direccion"); 
			String strTelefono = request.getParameter("telefono"); 
			String strFecha = request.getParameter("fecha"); 
			String strNotas = request.getParameter("notas"); 
			CatProveedores catProveedoresDto = new CatProveedores();
			
			try {
			CatProveedoresDao  catProveedoresDao = Utils.getCatProveedoresInstance();
			catProveedoresDto.setClave(Integer.parseInt(strClaveProveedorTrx));
			catProveedoresDto.setRfc(strRfc);
			catProveedoresDto.setEmpresa(strNombreEmpresa);
			catProveedoresDto.setNombre(strNombreRepresentante);
			catProveedoresDto.setDireccion(strDireccion);
			catProveedoresDto.setTelefono(strTelefono);
			java.util.Date utilFecha = Utils.getyyyyMMddsdfInstance().parse(strFecha);
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
			catProveedoresDto.setFecha(sqlFecha);
			catProveedoresDto.setDescripcion(strNotas);
			String strUpdate = catProveedoresDao.update(catProveedoresDto);
			if(null!=strUpdate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
				return;
			}else {
				     String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=ProveedoresCO");
					 response.sendRedirect( urlWithSessionID );
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
			
		} else if("Borrar".equals(strAccion)) {
			String strClaveProveedorTrx = request.getParameter("claveProveedorTrx");
			CatProveedoresDao  catProveedoresDao = Utils.getCatProveedoresInstance();
			try {
				String strDelete = catProveedoresDao.deleteByClave(Integer.parseInt(strClaveProveedorTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					List<CatProveedores> listCatProveedores = catProveedoresDao.findAll();
					  if(null!=listCatProveedores&&listCatProveedores.size()>0) {
					  request.setAttribute("listCatProveedores", listCatProveedores);
					  }
				}
				
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO PROVEEDORES");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO PROVEEDORES");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 
				
				request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/ProveedoresMain.jsp").forward(request, response);
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
		response.getWriter().println("Aun sin implementacion ProveedoresCO ");
		}
		
	 }/** END protected void doPost( **/

}
