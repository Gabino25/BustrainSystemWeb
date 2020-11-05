package bs.catalogo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bs.catalogo.dao.CatCelularesDao;
import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dto.CatCelulares;
import bs.catalogo.dto.CatTrabajadores;
import bs.util.Utils;

/**
 * Servlet implementation class CelularesTrabajadoresCO
 */
@WebServlet(name="CelularesTrabajadoresCO", urlPatterns= {"/CelularesTrabajadoresCO"})
@MultipartConfig
public class CelularesTrabajadoresCO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CelularesTrabajadoresCO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
//			response.getWriter().println("Fallo de inicio de Sesión. Favor de iniciar Sesión CELULARES.");
			request.getRequestDispatcher("/index.jsp").include(request, response);  
			
			return; 
		}
		
        String strAccion = request.getParameter("accionName"); 
		
        if("Guardar".equals(strAccion)) {	
   		 
		 	String strfecha = request.getParameter("fecha");
		 	String strtrabajador = request.getParameter("trabajador");
		 	String strmarca = request.getParameter("marca");
			String strmodelo = request.getParameter("modelo"); 
			String strnumeroSerie = request.getParameter("numeroSerie");
			String strnumero = request.getParameter("numero");
			String strnota = request.getParameter("nota");
											
			System.out.println("entro1");
			System.out.println(strfecha);
			System.out.println(strtrabajador);
			System.out.println(strmarca);
			System.out.println(strmodelo);
			System.out.println(strnumero);
			System.out.println(strnota);
			System.out.println("salio2");
			/**	
			 * 	System.out.println("strPantalon"+strPantalon);
			System.out.println("strZapatos"+strZapatos);
			System.out.println("strReingreso"+strReingreso);
			**/
		if(!"".equals(strfecha)&&null!=strfecha
		 &&!"".equals(strtrabajador)&&null!=strtrabajador
		 &&!"".equals(strmarca)&&null!=strmarca
		 &&!"".equals(strmodelo)&&null!=strmodelo
		 &&!"".equals(strnumeroSerie)&&null!=strnumeroSerie
		 &&!"".equals(strnumero)&&null!=strnumero
		
		  ) {
			//-----------------VAN AQUI-------------------------------
						
			CatCelularesDao catCelularesDao =Utils.getCatCelularesInstance();
			
			
			try {

			
			CatCelulares catCelulares = new CatCelulares();
			
			catCelulares.setFecha(strfecha);
			catCelulares.setTrabajador(strtrabajador);
			catCelulares.setMarca(strmarca);
			catCelulares.setModelo(strmodelo);
			catCelulares.setNumeroSerie(strnumeroSerie);
			catCelulares.setNumero(strnumero);
			catCelulares.setNota(strnota);
			
			if(null==catCelularesDao.insert(catCelulares)) {
				 String urlWithSessionID = response.encodeRedirectURL(request.getContextPath()+"/UtilsCO?utilAccion=PRG&utilCO=CelularesTrabajadoresCO");
				 response.sendRedirect( urlWithSessionID );
				 return;
			 }
			
			} catch (SQLException sqle) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sqle.toString());
				return;
			}
			return; 
			
		} /** Valida !"" **/
		else {
			response.getWriter().println("Fallo al validar los parametros1.");
			return; 
		}
	}
        
        
        else {
       	 response.getWriter().println("Aun sin implementacion");
       	 return;
       	 }
	}

}
