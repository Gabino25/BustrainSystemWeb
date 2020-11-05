package bs.catalogo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.CatProveedoresDao;
import bs.catalogo.dto.CatProveedores;
import bs.eis.ResourceManager;

public class CatProveedoresDaoImpl implements CatProveedoresDao {
	
	protected java.sql.Connection userConn;

	protected final String SQL_SELECT = "SELECT clave, rfc, empresa, nombre, direccion, telefono, estado, fecha, descripcion FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( clave, rfc, empresa, nombre, direccion, telefono, estado, fecha, descripcion ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET id = ?, rfc = ?, empresa = ?, contacto = ?, direccion = ?, telefono = ?, estado = ?, fecha = ?, notas = ? WHERE id = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE id = ?";

	protected static final int COLUMN_CLAVE = 1;
	protected static final int COLUMN_RFC = 2;
	protected static final int COLUMN_EMPRESA = 3;
	protected static final int COLUMN_NOMBRE = 4;
	protected static final int COLUMN_DIRECCION = 5;
	protected static final int COLUMN_TELEFONO = 6;
	protected static final int COLUMN_ESTADO = 7;
	protected static final int COLUMN_FECHA = 8;
	protected static final int COLUMN_DESCRIPCION = 9;

	protected static final int NUMBER_OF_COLUMNS = 9;

	protected static final int PK_COLUMN_ID = 1;


	public String getTableName()
	{
		return "BUSTRAIN.dbo.PROVEEDOR";
	}
	
	@Override
	public String insert(CatProveedores dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			stmt.setInt( COLUMN_CLAVE, dto.getClave());
			stmt.setString( COLUMN_RFC, dto.getRfc() );
			stmt.setString( COLUMN_EMPRESA, dto.getEmpresa() );
			stmt.setString( COLUMN_NOMBRE, dto.getNombre() );
			stmt.setString( COLUMN_DIRECCION, dto.getDireccion() );
			stmt.setString( COLUMN_TELEFONO, dto.getTelefono() );
			stmt.setString( COLUMN_ESTADO, dto.getEstado() );
			stmt.setTimestamp(COLUMN_FECHA, dto.getFecha());
			stmt.setString( COLUMN_DESCRIPCION, dto.getDescripcion() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			stmt.execute();
			rowsAffected = stmt.getUpdateCount();
		
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();			
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
		if(0==rowsAffected) {
			return "FalloTransacaccionMetodo insert clase CatProveedoresDaoImpl"; 
		}else {
		return null; 
		}
	}


	@Override
	public List<CatProveedores> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatProveedores> lisCatProveedoresDto = new ArrayList<CatProveedores>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		
	    		CatProveedores catProveedores = new CatProveedores(); 
	    		
	    		catProveedores.setClave(rs.getShort(COLUMN_CLAVE));
	    		catProveedores.setNombre(rs.getNString(COLUMN_NOMBRE));
	    		catProveedores.setDireccion(rs.getString(COLUMN_DIRECCION));
	    		catProveedores.setEmpresa(rs.getNString(COLUMN_EMPRESA));
	    		catProveedores.setEstado(rs.getString(COLUMN_ESTADO));
	    		catProveedores.setFecha(rs.getTimestamp(COLUMN_FECHA));
	    		catProveedores.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
	    		catProveedores.setTelefono(rs.getNString(COLUMN_TELEFONO));
	    		catProveedores.setRfc(rs.getString(COLUMN_RFC));
	    		
	    		lisCatProveedoresDto.add(catProveedores);
	        	
	    	}
	     
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    return lisCatProveedoresDto; 	
	}
	
	@Override
	public String findNextClave() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextClave = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([CLAVE])+1 NEXT_CLAVE from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
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
	    	 return "NoSePudoRecuperaInformacionMetodo findNextClave clase CatClientesDaoImpl";
	    }else {
	       return ""+nextClave;
	    }
	}

	@Override
	public CatProveedores findByClave(int parseInt) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    CatProveedores catProveedoresDto = new CatProveedores();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+" where CLAVE="+parseInt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		
	    		catProveedoresDto.setClave(rs.getShort(COLUMN_CLAVE));
	    		catProveedoresDto.setNombre(rs.getNString(COLUMN_NOMBRE));
	    		catProveedoresDto.setDireccion(rs.getString(COLUMN_DIRECCION));
	    		catProveedoresDto.setEmpresa(rs.getNString(COLUMN_EMPRESA));
	    		catProveedoresDto.setEstado(rs.getString(COLUMN_ESTADO));
	    		catProveedoresDto.setFecha(rs.getTimestamp(COLUMN_FECHA));
	    		catProveedoresDto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
	    		catProveedoresDto.setTelefono(rs.getNString(COLUMN_TELEFONO));
	    		catProveedoresDto.setRfc(rs.getString(COLUMN_RFC));
	    		
	    	}
	     
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    return catProveedoresDto; 	
	}

	@Override
	public String update(CatProveedores catProveedoresDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(catProveedoresDto.getRfc())&&null!=catProveedoresDto.getRfc()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [RFC] ='"+catProveedoresDto.getRfc()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catProveedoresDto.getEmpresa())&&null!=catProveedoresDto.getEmpresa()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [EMPRESA] ='"+catProveedoresDto.getEmpresa()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catProveedoresDto.getNombre())&&null!=catProveedoresDto.getNombre()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOMBRE] ='"+catProveedoresDto.getNombre()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catProveedoresDto.getDireccion())&&null!=catProveedoresDto.getDireccion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DIRECCION] ='"+catProveedoresDto.getDireccion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catProveedoresDto.getTelefono())&&null!=catProveedoresDto.getTelefono()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TELEFONO] ='"+catProveedoresDto.getTelefono()+"'";
			countFields = countFields +1; 
		}
		if(null!=catProveedoresDto.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+catProveedoresDto.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(catProveedoresDto.getDescripcion())&&null!=catProveedoresDto.getDescripcion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DESCRIPCION] ='"+catProveedoresDto.getDescripcion()+"'";
			countFields = countFields +1; 
		}
        stmtUpdate =stmtUpdate+" WHERE [CLAVE] ="+catProveedoresDto.getClave(); 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
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
			return "FalloTransaccionMetodo update clase CatClientesDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String deleteByClave(int pClave) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [CLAVE] ="+pClave; 
	
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
			return "FalloTransaccionMetodo delete clase CatProveedoresDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public List<CatProveedores> findProveedoresList() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatProveedores> lisCatProveedoresDto = new ArrayList<CatProveedores>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatProveedores catProveedores = new CatProveedores(); 
	    		
	    		catProveedores.setClave(rs.getShort(COLUMN_CLAVE));
	    		catProveedores.setEmpresa(rs.getNString(COLUMN_EMPRESA));
	    		
	    		lisCatProveedoresDto.add(catProveedores);
	       }
	     
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    return lisCatProveedoresDto; 	
	}
	
}
