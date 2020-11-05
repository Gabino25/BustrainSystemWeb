package bs.admin.entry.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bs.admin.entry.dao.UsuarioDao;
import bs.admin.entry.dto.Usuario;
import bs.eis.ResourceManager;

public class UsuarioDaoImpl  implements UsuarioDao{

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
	protected final String SQL_SELECT = "SELECT NOMBRE, PASS, NIVEL FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( NOMBRE,PASS, NIVEL ) VALUES ( ?, ?, ? )";

	/** 
	 * Index of column NOMBRE
	 */
	protected static final int COLUMN_NOMBRE = 1;
	protected static final int COLUMN_PASS = 2; 
	protected static final int COLUMN_NIVEL = 3;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 3;

	@Override
	public Usuario findByNombrePass(String pNombre
			                      , String pPass) throws SQLException {
		
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strValidaUsuarioPass = null; 
	    Usuario usuarioDto = new Usuario(); 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select nombre, pass, nivel from "+getTableName()+" where nombre=? and pass=?",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	stmt.setString(1, pNombre);
	    	stmt.setString(2, pPass);
	    	rs = stmt.executeQuery(); 
	        while(rs.next()) {
	        	usuarioDto.setNombre(rs.getString("nombre"));
	        	usuarioDto.setPass(rs.getString("pass"));
	        	usuarioDto.setNivel(rs.getBigDecimal("nivel"));
	        	break;
	        }
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    return usuarioDto; 
	}
	
	public String insert(Usuario dto) throws SQLException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		int rows = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareCall( SQL_INSERT );
			stmt.setString( COLUMN_NOMBRE, dto.getNombre() );
			stmt.setNString(COLUMN_PASS, dto.getPass());
			if (dto.isNivelNull()) {
				stmt.setNull( COLUMN_NIVEL, java.sql.Types.INTEGER );
			} else {
				stmt.setBigDecimal( COLUMN_NIVEL, dto.getNivel() );
			}
		
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			stmt.execute();
			rows = stmt.getUpdateCount();
			System.out.println( rows + " rows affected" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new SQLException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
		if(0==rows) {
			return "FalloTransacaccionMetodo insert clase UsuarioDaoImpl"; 
		}else {
			return null;
		}
		
		
	}

	/** 
	 * Returns all rows from the USUARIO table that match the criteria ''.
	 */
	public List<Usuario> findAll() throws SQLException
	{
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Usuario> listUsuario = new ArrayList<Usuario>();
	   
	    try {
		    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Usuario usuario = new Usuario(); 
	    		usuario.setNombre(rs.getString("NOMBRE"));
	    		listUsuario.add(usuario);
	    	}
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
	    
	    return listUsuario; 
	    
	}

	/** 
	 * Returns all rows from the USUARIO table that match the criteria 'NOMBRE = :nombre'.
	 */
	public Usuario[] findWhereNombreEquals(String nombre) throws SQLException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NOMBRE = ? ORDER BY NOMBRE", new Object[] { nombre } );
	}

	/** 
	 * Returns all rows from the USUARIO table that match the criteria 'NIVEL = :nivel'.
	 */
	public Usuario[] findWhereNivelEquals(long nivel) throws SQLException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NIVEL = ? ORDER BY NIVEL", new Object[] {  new Long(nivel) } );
	}

	/**
	 * Method 'UsuarioDaoImpl'
	 * 
	 */
	public UsuarioDaoImpl()
	{
	}

	/**
	 * Method 'UsuarioDaoImpl'
	 * 
	 * @param userConn
	 */
	public UsuarioDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "BUSTRAIN.dbo.USUARIO";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Usuario fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Usuario dto = new Usuario();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Usuario[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Usuario dto = new Usuario();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Usuario ret[] = new Usuario[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Usuario dto, ResultSet rs) throws SQLException
	{
		dto.setNombre( rs.getString( COLUMN_NOMBRE ) );
		dto.setNivel( rs.getBigDecimal( COLUMN_NIVEL ) );
		if (rs.wasNull()) {
			dto.setNivelNull( true );
		}
		
		reset(dto);
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Usuario dto)
	{
		dto.setNombreModified( false );
		dto.setNivelModified( false );
	}

	/** 
	 * Returns all rows from the USUARIO table that match the specified arbitrary SQL statement
	 */
	public Usuario[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new SQLException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the USUARIO table that match the specified arbitrary SQL statement
	 */
	public Usuario[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new SQLException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	@Override
	public String update(String strnombreUsuarioTrx,Usuario usuarioDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(null!=usuarioDto.getNombre()&&!"".equals(usuarioDto.getNombre())) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOMBRE] ='"+usuarioDto.getNombre()+"'";
			countFields = countFields +1; 
		}
		if(null!=usuarioDto.getPass()&&!"".equals(usuarioDto.getPass())) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PASS] ='"+usuarioDto.getPass()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate = stmtUpdate+ " WHERE NOMBRE='"+strnombreUsuarioTrx+"'";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			System.out.println(stmtUpdate);
			stmt = conn.prepareStatement(stmtUpdate,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.execute();
		    rowsAffected = stmt.getUpdateCount();
			
		}finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
	    }
		
		if(0==rowsAffected) {
			return "FalloTransaccionMetodo update clase UsuariosDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String deleteByNombre(String strnombreUsuarioTrx) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [NOMBRE] ='"+strnombreUsuarioTrx+"'"; 
	
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(stmtDelete,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.execute();
		    rowsAffected = stmt.getUpdateCount();
			
		}finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
	    }
		
		if(0==rowsAffected) {
			return "FalloTransaccionMetodo delete clase UsuariosDaoImpl"; 
		}else {
			return null;
		}
	}
	
}
