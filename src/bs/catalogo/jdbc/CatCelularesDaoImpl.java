package bs.catalogo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.CatCelularesDao;

import bs.catalogo.dto.CatCelulares;
import bs.catalogo.dto.CatTrabajadores;
import bs.eis.ResourceManager;

public class CatCelularesDaoImpl extends AbstractDAO implements CatCelularesDao{

	protected java.sql.Connection userConn;

	private static final int COLUMN_FECHA=1; 
	private static final int COLUMN_NOMBRE=2;
	private static final int COLUMN_MARCA=3;
	private static final int COLUMN_MODELO=4;
	private static final int COLUMN_NUMEROSERIE=5;
	private static final int COLUMN_NUMERO=6;
	private static final int COLUMN_NOTA=7;
	
	protected final String SQL_SELECT = " SELECT [NOMBRE] " + 
			" ,[MARCA] " + 
			" ,[MODELO] " + 
			" ,[NS] " + 
			" ,[NUMEROCEL] " + 
			" ,[NOTA] " + 
			" ,[FECHA] " + 
			" from " + getTableName();
	
	
	protected final String SQL_INSERT = " insert into "+this.getTableName()+" (fecha,nombre,marca,modelo,ns,numerocel,nota) values (?,?,?,?,?,?,?)"; 


	public String getTableName()
	{
		return "BUSTRAIN.dbo.CELULARESTRABAJADORES";
	}
	
	
	
	@Override
	public String insert(CatCelulares CatCelulares) throws SQLException {
		// TODO Auto-generated method stub
//		return null;
String a= CatCelulares.getFecha();
String b = CatCelulares.getTrabajador();
String c= CatCelulares.getMarca();
String d =CatCelulares.getModelo();
String e= CatCelulares.getNumeroSerie();
String f= CatCelulares.getNumero();
String g= CatCelulares.getNota();
System.out.println("entro3");
System.out.println(a);
System.out.println(b);		
System.out.println(c);
System.out.println(d);
System.out.println(e);
System.out.println(f);
System.out.println(g);
System.out.println("entro4");

		
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;  
		try {
		conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			
		stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
		stmt.setString(COLUMN_FECHA, CatCelulares.getFecha());
		
		stmt.setString( COLUMN_NOMBRE, CatCelulares.getTrabajador() );
		stmt.setString( COLUMN_MARCA, CatCelulares.getMarca() );
		stmt.setString( COLUMN_MODELO, CatCelulares.getModelo() );
		stmt.setString( COLUMN_NUMEROSERIE, CatCelulares.getNumeroSerie() );
		stmt.setString( COLUMN_NUMERO, CatCelulares.getNumero() );
		stmt.setString( COLUMN_NOTA, CatCelulares.getNota() );
		
		stmt.execute();
		rowsAffected = stmt.getUpdateCount();
		
		}
		catch (SQLException sqle) {
			throw sqle;
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
		if(0==rowsAffected) {
			 return "FalloTransacaccionMetodo insert clase CatCelularesDaoImpl"; 
		}else {
			return null; 
		}
		
	
	
	}



	@Override
	public List<CatCelulares> findAll() throws SQLException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatCelulares> Dto = new ArrayList<CatCelulares>();
	    try {
	    	
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatCelulares catCelulares = new CatCelulares();
	    		catCelulares.setTrabajador(rs.getString("NOMBRE"));
	    		catCelulares.setMarca(rs.getString("MARCA"));
	    		catCelulares.setModelo(rs.getString("MODELO"));
	    		catCelulares.setNumeroSerie(rs.getString("NS"));
	    		catCelulares.setNumero(rs.getString("NUMEROCEL"));
	    		catCelulares.setNota(rs.getString("NOTA"));
	    		catCelulares.setFecha(rs.getString("FECHA"));
	       		Dto.add(catCelulares);
	    		
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    
		return Dto;
	
	}

}
