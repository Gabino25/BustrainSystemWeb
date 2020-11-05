package bs.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs.ap.dao.ConceptosDao;
import bs.ap.dao.CuentasOperacionDao;
import bs.ap.dao.FacturasDao;
import bs.ap.dto.CuentasOperacion;
import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dao.CatCelularesDao;
import bs.catalogo.dao.CatClientesDao;
import bs.catalogo.dao.CatProveedoresDao;
import bs.catalogo.dao.CatRutasDao;
import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dao.GasolineraDao;
import bs.catalogo.dto.CatAutobus;
import bs.catalogo.dto.CatCelulares;
import bs.catalogo.dto.CatClientes;
import bs.catalogo.dto.CatProveedores;
import bs.catalogo.dto.CatTrabajadores;
import bs.catalogo.dto.Gasolinera;
import bs.llantas.entry.dao.LlantasDao;
import bs.llantas.entry.dto.Llantas;
import bs.catalogo.dto.CatRutas;
import bs.admin.entry.dao.PermisosDao;
import bs.admin.entry.dao.UsuarioDao;
import bs.admin.entry.dto.Pantallas;
import bs.admin.entry.dto.Permisos;
import bs.admin.entry.dto.Usuario;
import bs.util.Utils;

/**
 * Servlet implementation class IndexCO
 */
@WebServlet(name="IndexCO",urlPatterns={"/IndexCO"})
public class IndexCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); /** Problemas Tildes **/
		String strNombreUsuario = request.getParameter("nombreUsuario"); 
		String strPasswordUsuario = request.getParameter("passwordUsuario"); 
		strNombreUsuario = strNombreUsuario.toUpperCase(); 
		strPasswordUsuario = strPasswordUsuario.toUpperCase();
//		System.out.println("debug Francisco"+strNombreUsuario);
		
	
		UsuarioDao usuarioDao = Utils.getUsuarioInstance();
		Usuario usuarioDto = null; 
		try {
			usuarioDto = usuarioDao.findByNombrePass(strNombreUsuario, strPasswordUsuario);
		} catch (SQLException sqle) {
			//response.sendError(response.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
			return;
		} 
		
		if(null!=usuarioDto) {
			if(null!=usuarioDto.getNombre()&&!"".equals(usuarioDto.getNombre())
			 &&null!=usuarioDto.getPass()&&!"".equals(usuarioDto.getPass())) {
				request.getSession().setAttribute("sAttrNombreUsuario", strNombreUsuario);
				try {
					environmentUtilsList(request,response);
				} catch (SQLException sqle) {
					//response.sendError(response.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
					return;
				}
				request.getRequestDispatcher("/WEB-INF/bs/webui/main.jsp").forward(request, response);	
				return; 
			}else {
				response.setContentType("text/html");
				response.getWriter().print("No se pudo recuperar Usuario y Password");
				request.getRequestDispatcher("/index.jsp").include(request, response);
				return; 
			}
		}else {
			response.setContentType("text/html");
			response.getWriter().print("Usuario y Password Incorrectos.");
			request.getRequestDispatcher("/index.jsp").include(request, response);
			return; 
		} /** END if(null!=usuarioDto) { **/
		
		
	}

	private void environmentUtilsList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		CatTrabajadoresDao catTrabajadoresDao = Utils.getCatTrabajadoresInstance();
		
		try {
			List<CatTrabajadores> listCatTrabajadores = catTrabajadoresDao.findTrabajadoresList();
			if(null!=listCatTrabajadores&&listCatTrabajadores.size()>0) {
				
				request.getSession().setAttribute("selectListCatTrabajadores",listCatTrabajadores);	
			}
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		
		CatCelularesDao catCelularesDaoImpl = Utils.getCatCelularesInstance();
	
				try {
				 
			List<CatCelulares> list = catCelularesDaoImpl.findAll();
			if(null!=list&&list.size()>0) {
				request.setAttribute("list", list);
			}
			} catch (SQLException sqle) {
				throw sqle;
				}
				
				
		
		
		
		CatClientesDao catClientesDao = Utils.getCatClientesInstance();
		try {
		   List<CatClientes> listCatClientes = catClientesDao.findEmpresasList();
		   if(null!=listCatClientes&&listCatClientes.size()>0) {
			   request.getSession().setAttribute("selectListCatClientes",listCatClientes);	
		   }
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		CatProveedoresDao catProveedores = Utils.getCatProveedoresInstance();
		try {
		List<CatProveedores> listCatProveedores = catProveedores.findProveedoresList(); 
		if(null!=listCatProveedores&&listCatProveedores.size()>0) {
			request.getSession().setAttribute("selectListCatProveedores",listCatProveedores);	
		}
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		
		CatAutobusDao catAutobusDao = Utils.getCatAutobusInstance(); 
		try {
		List<CatAutobus> listCatAutobus = catAutobusDao.findUnidadesList();
		 if(null!=listCatAutobus&&listCatAutobus.size()>0) {
			 request.getSession().setAttribute("selectListAutobus",listCatAutobus);	
		 }
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		CatRutasDao catRutasDao = Utils.getCatRutasInstance();
		try {
		  List<CatRutas> listCatRutas = catRutasDao.findRutasList();
		  if(null!=listCatRutas&&listCatRutas.size()>0) {
			  request.getSession().setAttribute("selectListRutas",listCatRutas);	
		  }
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		GasolineraDao gasolineraDao = Utils.getGasolineraInstance(); 
		
		try {
			List<Gasolinera> listGasolineras = gasolineraDao.findGasolineraList();
			if(null!=listGasolineras&&listGasolineras.size()>0) {
				request.getSession().setAttribute("selectListGasolineras", listGasolineras);
			}
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		LlantasDao llantasDao = Utils.getLlantasInstance(); 
		try {
			List<Llantas> listLlantas = llantasDao.findSelectList();
			if(null!=listLlantas&&listLlantas.size()>0) {
				request.getSession().setAttribute("selectListLlantas", listLlantas);
			}
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		CuentasOperacionDao cuentasOperacionDao =  Utils.getCuentasOperacionInstance();
		try {
		List<CuentasOperacion> listCuentasOperacion = cuentasOperacionDao.findAll(); 
		  if(null!=listCuentasOperacion&&listCuentasOperacion.size()>0) {		
		    request.getSession().setAttribute("selectListCtasOper", listCuentasOperacion);
		  }
		}catch (SQLException sqle) {
			// TODO Auto-generated catch block
			/*sqle.printStackTrace();*/
			throw sqle;
		}
		
		PermisosDao permisosDao = Utils.getPermisosInstance();
		request.getSession().setAttribute("listPermisos", null);
		List<Permisos> listPermisos = permisosDao.findPantallasByUser((String)request.getSession().getAttribute("sAttrNombreUsuario"));
		
		request.getSession().setAttribute("listPermisos", listPermisos);
		
	}

}
