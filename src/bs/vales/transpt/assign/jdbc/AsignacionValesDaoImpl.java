package bs.vales.transpt.assign.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.eis.ResourceManager;
import bs.vales.transpt.assign.dao.AsignacionValesDao;
import bs.vales.transpt.assign.dto.AsignacionVales;
import bs.vales.transpt.assign.dto.AsignacionValesPk;


public class AsignacionValesDaoImpl implements AsignacionValesDao{

	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT TOP (2000) numero, nombre, folioinicial, foliofinal, fecha FROM " + getTableName() + " order by numero desc ";

	

	/** 
	 * SQL INSERT statement for this table
	 */
	
	/*protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( numero, nombre, folioinicial, foliofinal, fecha ) VALUES ( NEXT VALUE FOR xxbs.dbo.asignacion_vales_s, ?, ?, ?, ? )";*/
	
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( numero, nombre, folioinicial, foliofinal, fecha ) VALUES ( ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET id = ?, nombre = ?, folio_inicial = ?, folio_final = ?, fecha = ? WHERE id = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE numero = ?";
	protected static final int COLUMN_NUMERO = 1;
	protected static final int COLUMN_NOMBRE = 2;
	protected static final int COLUMN_FOLIOINICIAL = 3;
	protected static final int COLUMN_FOLIOFINAL = 4;
	protected static final int COLUMN_FECHA = 5;
	protected static final int NUMBER_OF_COLUMNS = 5;
	protected static final int PK_COLUMN_ID = 1;
	public String getTableName()
	{
		return "BUSTRAIN.dbo.ASIGNACIONVALES";
	}
	
	@Override
	public String insert(AsignacionVales dto) throws SQLException {
		
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareCall( SQL_INSERT ,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			/*stmt.setLong( COLUMN_ID, dto.getId() ); IDENTITY(1,1)*/
			stmt.setLong(COLUMN_NUMERO, dto.getNumero());
			stmt.setString( COLUMN_NOMBRE, dto.getNombre() );
			stmt.setLong( COLUMN_FOLIOINICIAL, dto.getFolioinicial() );
			stmt.setLong( COLUMN_FOLIOFINAL, dto.getFoliofinal() );
			stmt.setTimestamp(COLUMN_FECHA, dto.getFecha());
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			stmt.execute();
		     rowsAffected = stmt.getUpdateCount();
			System.out.println( rowsAffected + " rows affected" );
		
		}
		catch (SQLException sqle) {
			throw 	sqle;
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
		if(0==rowsAffected) {
			return "FalloTransacaccionMetodo insert clase AsignacionValesDaoImpl"; 
		}else {
			return null;
		}

	}


	@Override
	public void delete(AsignacionValesPk pk) throws SQLException {
		// declare variables
				final boolean isConnSupplied = (userConn != null);
				Connection conn = null;
				PreparedStatement stmt = null;
			
				
				try {
					// get the user-specified connection or get a connection from the ResourceManager
					conn = isConnSupplied ? userConn : ResourceManager.getConnection();
				
					stmt = conn.prepareCall( SQL_DELETE ,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
					stmt.setLong( 1, pk.getId() );
				
					System.out.println( "Executing " + SQL_DELETE + " with DTO: " + pk );
					stmt.execute();
					int rows = stmt.getUpdateCount();
					System.out.println( rows + " rows affected" );
				
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
		
	}



	@Override
	public List<AsignacionVales> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<AsignacionVales> listAsignacionValesDto = new ArrayList<AsignacionVales>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	System.out.println("Ejecutando el query:"+SQL_SELECT);
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		AsignacionVales assignacionVales = new AsignacionVales(); 
	    		
	    		assignacionVales.setNumero(rs.getLong(COLUMN_NUMERO));
	    		assignacionVales.setNombre(rs.getNString(COLUMN_NOMBRE));
	    		assignacionVales.setFolioinicial(rs.getLong(COLUMN_FOLIOINICIAL));
	    		assignacionVales.setFoliofinal(rs.getLong(COLUMN_FOLIOFINAL));
	    		assignacionVales.setFecha(rs.getTimestamp(COLUMN_FECHA));
	    		
	    		listAsignacionValesDto.add(assignacionVales);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    return listAsignacionValesDto; 
	}

	@Override
	public String findNextNumero() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextClave = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([NUMERO])+1 NEXT_NUMERO from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		nextClave = rs.getInt(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    if(0==nextClave) {
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumero clase AsignacionValesDaoImpl";
	    }else {
	       return ""+nextClave;
	    }
	}

	@Override
	public String findFolioBetweenIniFin(long pFolio) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String folioBetweenIniFin = null;  
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select NOMBRE from "+this.getTableName()+" where "+pFolio+" between FOLIOINICIAL and FOLIOFINAL ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		folioBetweenIniFin = rs.getString(1);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	   return folioBetweenIniFin; 
	}

	@Override
	public String findByFolioInicial(long pFolioInicial) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String folioInicial = null;  
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select folioinicial from "+this.getTableName()+" where FOLIOINICIAL="+pFolioInicial,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		folioInicial = rs.getString(1);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	   return folioInicial; 
	}

	@Override
	public String findByFolioFinal(long pFolioFinal) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String folioFinal = null;  
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select foliofinal from "+this.getTableName()+" where FOLIOFINAL="+pFolioFinal,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		folioFinal = rs.getString(1);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	   return folioFinal; 
	}
	
}
