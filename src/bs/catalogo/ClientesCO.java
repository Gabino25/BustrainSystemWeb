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
import bs.catalogo.dao.CatClientesDao;
import bs.catalogo.dto.CatClientes;
import bs.util.Utils;

/**
 * Servlet implementation class ClientesCO
 */
@WebServlet(name="ClientesCO",  urlPatterns= {"/ClientesCO"})
public class ClientesCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesCO() {
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
		}else if("Guardar".equals(strAccion)) {
		String strRfc = request.getParameter("rfc"); 
		String strNombreEmpresa = request.getParameter("nombreEmpresa"); 
		String strNombreRepresentante = request.getParameter("nombreRepresentante"); 
		String strDireccion = request.getParameter("direccion"); 
		String strTelefono = request.getParameter("telefono"); 
		String strFecha = request.getParameter("fecha"); 
		String strEstado = request.getParameter("Estado"); 
		String strNotas = request.getParameter("notas"); 
		
		
		if(!"".equals(strRfc)&&null!=strRfc
		  &&!"".equals(strNombreEmpresa)&&null!=strNombreEmpresa
		  &&!"".equals(strNombreRepresentante)&&null!=strNombreRepresentante
		  &&!"".equals(strDireccion)&&null!=strDireccion
		  &&!"".equals(strTelefono)&&null!=strTelefono
		  &&!"".equals(strFecha)&&null!=strFecha
		  &&!"".equals(strEstado)&&null!=strEstado
		  ) {
			
			
			CatClientesDao catClientesDao = Utils.getCatClientesInstance();
			
			try {
				
		    String strNextClave = catClientesDao.findNextClave();
		    	System.out.println(strNextClave);
		    CatClientes catClientes = new CatClientes();
		    catClientes.setClave(Integer.parseInt(strNextClave));
			catClientes.setRfc(strRfc);
			catClientes.setEmpresa(strNombreEmpresa);
			catClientes.setContacto(strNombreRepresentante);
			catClientes.setDireccion(strDireccion);
			catClientes.setTelefono(strTelefono);
			SimpleDateFormat yyyyMMdd = Utils.getyyyyMMddsdfInstance();
			java.util.Date utilFecha = yyyyMMdd.parse(strFecha); 
			java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
			catClientes.setFecha(sqlFecha);
			catClientes.setEstado(strEstado);
			catClientes.setDescripcion(strNotas);
		
			 if(null==catClientesDao.insert(catClientes)) {
				
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=ClientesCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			 }
			}catch(SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}catch(ParseException pe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,pe.toString());
				return;
			}
			 
		  }  /** END valida campos vacios **/
		else {
			response.getWriter().println("fallo al validar los parametros");
			return;
		}
		} 
		
		/** END if("Guardar".equals(strAccion)) { **/
		else if("Buscar".equals(strAccion)) {
		  String strclaveClienteTrx = request.getParameter("claveClienteTrx"); 	
		  CatClientesDao catClientesDao = Utils.getCatClientesInstance();
		  try {
			CatClientes catClientesDto = catClientesDao.findByClave(Integer.parseInt(strclaveClienteTrx));
			request.setAttribute("Cliente", catClientesDto);
		} catch (NumberFormatException nfe) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
			return;
		} catch (SQLException sqle) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			return;
		}
		  request.getRequestDispatcher("/WEB-INF/bs/catalogo/inquiry/ClientesMainRO.jsp").forward(request, response); 
	 	  return;
		}
		else if("Regresar".equals(strAccion)) {
			CatClientesDao catClientesDao = Utils.getCatClientesInstance();
				try {
				List<CatClientes> listCatClientes = catClientesDao.findAll(); 
			if(null!=listCatClientes&&listCatClientes.size()>0) {
				request.setAttribute("listCatClientes", listCatClientes);
			}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
				}
			
				PermisosDao permisosDao = Utils.getPermisosInstance(); 
				try {
					String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO CLIENTES");
					if(null==strNivel) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO CLIENTES");
						return;
					}else {
					  request.setAttribute("rAttrNivel", strNivel);
					}
				} catch (SQLException sqle) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				} 	
				
			 request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/ClientesMain.jsp").forward(request, response); 
			 return;
		}else if("Modificar".equals(strAccion)) {
			  String strclaveClienteTrx = request.getParameter("claveClienteTrx"); 	
			  CatClientesDao catClientesDao = Utils.getCatClientesInstance();
			  try {
				CatClientes catClientesDto = catClientesDao.findByClave(Integer.parseInt(strclaveClienteTrx));
				request.setAttribute("Cliente", catClientesDto);
			} catch (NumberFormatException nfe) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,nfe.toString());
				return;
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			request.getRequestDispatcher("/WEB-INF/bs/catalogo/upd/ClientesMainUpd.jsp").forward(request, response); 
		 	return;
		}else if("Update".equals(strAccion)) {
			CatClientesDao catClientesDao = Utils.getCatClientesInstance();
			String strClaveClienteTrx = request.getParameter("claveClienteTrx");
			String strRfc = request.getParameter("rfc"); 
			String strNombreEmpresa = request.getParameter("nombreEmpresa"); 
			String strNombreRepresentante = request.getParameter("nombreRepresentante"); 
			String strDireccion = request.getParameter("direccion"); 
			String strTelefono = request.getParameter("telefono"); 
			String strFecha = request.getParameter("fecha"); 
			String strEstado = request.getParameter("Estado"); 
			String strNotas = request.getParameter("notas"); 
			try {
			CatClientes catClientes = new CatClientes();
			catClientes.setClave(Integer.parseInt(strClaveClienteTrx));
			catClientes.setRfc(strRfc);
			catClientes.setEmpresa(strNombreEmpresa);
			catClientes.setContacto(strNombreRepresentante);
			catClientes.setDireccion(strDireccion);
			catClientes.setTelefono(strTelefono);
		    java.util.Date utilFecha = Utils.getyyyyMMddsdfInstance().parse(strFecha);
		    java.sql.Timestamp sqlFecha = new java.sql.Timestamp(utilFecha.getTime());
		    catClientes.setFecha(sqlFecha);
			catClientes.setEstado(strEstado);
			catClientes.setDescripcion(strNotas);
			
			String strUpdate = catClientesDao.update(catClientes);
			if(null==strUpdate) {
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=ClientesCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strUpdate);
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
			
		}else if("Borrar".equals(strAccion)) {
			CatClientesDao catClientesDao = Utils.getCatClientesInstance();
			String strClaveClienteTrx = request.getParameter("claveClienteTrx");
		   try {
				String strDelete = catClientesDao.deleteByClave(Integer.parseInt(strClaveClienteTrx));
				if(null!=strDelete) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,strDelete);
					return;
				}else {
					List<CatClientes> listCatClientes = catClientesDao.findAll(); 
					if(null!=listCatClientes&&listCatClientes.size()>0) {
						request.setAttribute("listCatClientes", listCatClientes);
					}
					
					PermisosDao permisosDao = Utils.getPermisosInstance(); 
					try {
						String strNivel = permisosDao.findNivel(strUsuario,"CATALOGO CLIENTES");
						if(null==strNivel) {
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fallo al recuperar el Nivel de permiso sobre la pantalla CATALOGO CLIENTES");
							return;
						}else {
						  request.setAttribute("rAttrNivel", strNivel);
						}
					} catch (SQLException sqle) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
						return;
					}
					
					request.getRequestDispatcher("/WEB-INF/bs/catalogo/entry/ClientesMain.jsp").forward(request, response); 
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
		response.getWriter().println("Aun sin implementacion ClientesCO");
		}
		
	} /** END metodo protected void doPost **/

}
