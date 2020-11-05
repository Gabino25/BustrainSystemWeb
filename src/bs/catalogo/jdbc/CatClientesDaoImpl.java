package bs.catalogo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.CatClientesDao;
import bs.catalogo.dto.CatClientes;
import bs.eis.ResourceManager;

public class CatClientesDaoImpl implements CatClientesDao {

	protected java.sql.Connection userConn; 
	
	public String getTableName()
	{
		return "BUSTRAIN.dbo.clientes";
	}
	
	protected final String SQL_SELECT = "SELECT clave, rfc, empresa, contacto, direccion, telefono, estado, fecha, descripcion FROM " + getTableName() + "";
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( clave, rfc, empresa, contacto, direccion, telefono, estado, fecha, descripcion ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET id = ?, rfc = ?, empresa = ?, contacto = ?, direccion = ?, telefono = ?, estado = ?, fecha = ?, notas = ? WHERE id = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE id = ?";
    
	protected static final int COLUMN_CLAVE = 1;
	protected static final int COLUMN_RFC = 2;
	protected static final int COLUMN_EMPRESA = 3;
	protected static final int COLUMN_CONTACTO = 4;
	protected static final int COLUMN_DIRECCION = 5;
	protected static final int COLUMN_TELEFONO = 6;
	protected static final int COLUMN_ESTADO = 7;
	protected static final int COLUMN_FECHA = 8;
	protected static final int COLUMN_DESCRIPCION = 9;
	protected static final int NUMBER_OF_COLUMNS = 9;

	
	@Override
	public String insert(CatClientes dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setInt( COLUMN_CLAVE, dto.getClave() );
			stmt.setString( COLUMN_RFC, dto.getRfc() );
			stmt.setString( COLUMN_EMPRESA, dto.getEmpresa() );
			stmt.setString( COLUMN_CONTACTO, dto.getContacto() );
			stmt.setString( COLUMN_DIRECCION, dto.getDireccion() );
			stmt.setString( COLUMN_TELEFONO, dto.getTelefono() );
			stmt.setString( COLUMN_ESTADO, dto.getEstado() );
			stmt.setTimestamp(COLUMN_FECHA, dto.getFecha());
			stmt.setString( COLUMN_DESCRIPCION, dto.getDescripcion());
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
			return "FalloTransacaccionMetodo insert clase CatClientesDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public List<CatClientes> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatClientes> listCatClientesDto = new ArrayList<CatClientes>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatClientes  catClientes = new CatClientes(); 
	    		
	    		catClientes.setClave(rs.getInt(COLUMN_CLAVE)); 
	    		catClientes.setRfc(rs.getString(COLUMN_RFC));
	    		catClientes.setContacto(rs.getString(COLUMN_CONTACTO));
	    		catClientes.setDireccion(rs.getString(COLUMN_DIRECCION));
	    		catClientes.setEstado(rs.getString(COLUMN_ESTADO));
	    		catClientes.setFecha(rs.getTimestamp(COLUMN_FECHA));
	    		catClientes.setTelefono(rs.getString(COLUMN_TELEFONO));
	    		catClientes.setEmpresa(rs.getNString(COLUMN_EMPRESA));
	    		catClientes.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
	    		
	    		listCatClientesDto.add(catClientes); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return listCatClientesDto;
	}

	public List<CatClientes> findEmpresasList() throws SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatClientes> listCatClientesDto = new ArrayList<CatClientes>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	System.out.println("Ejecutando el query:"+"select clave, empresa from "+this.getTableName());
	    	stmt = conn.prepareStatement("select clave, empresa from "+this.getTableName()+" order by empresa asc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatClientes  catClientes = new CatClientes(); 
	    		
	    		catClientes.setClave(rs.getInt("clave")); 
	    		catClientes.setEmpresa(rs.getNString("empresa"));
	    		
	    		listCatClientesDto.add(catClientes); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return listCatClientesDto;
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
	public CatClientes findByClave(int pClave) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    CatClientes catClientes = new CatClientes();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+" where clave="+pClave,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		catClientes.setClave(rs.getInt(COLUMN_CLAVE)); 
	    		catClientes.setRfc(rs.getString(COLUMN_RFC));
	    		catClientes.setContacto(rs.getString(COLUMN_CONTACTO));
	    		catClientes.setDireccion(rs.getString(COLUMN_DIRECCION));
	    		catClientes.setEstado(rs.getString(COLUMN_ESTADO));
	    		catClientes.setFecha(rs.getTimestamp(COLUMN_FECHA));
	    		catClientes.setTelefono(rs.getString(COLUMN_TELEFONO));
	    		catClientes.setEmpresa(rs.getNString(COLUMN_EMPRESA));
	    		catClientes.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
	    	
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return catClientes;
	}

	@Override
	public String update(CatClientes catClientes) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(catClientes.getRfc())&&null!=catClientes.getRfc()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [RFC] ='"+catClientes.getRfc()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catClientes.getEmpresa())&&null!=catClientes.getEmpresa()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [EMPRESA] ='"+catClientes.getEmpresa()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catClientes.getDireccion())&&null!=catClientes.getDireccion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DIRECCION] ='"+catClientes.getDireccion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catClientes.getTelefono())&&null!=catClientes.getTelefono()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TELEFONO] ='"+catClientes.getTelefono()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catClientes.getContacto())&&null!=catClientes.getContacto()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CONTACTO] ='"+catClientes.getContacto()+"'";
			countFields = countFields +1; 
		}
		if(null!=catClientes.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+catClientes.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(catClientes.getDescripcion())&&null!=catClientes.getDescripcion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DESCRIPCION] ='"+catClientes.getDescripcion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catClientes.getEstado())&&null!=catClientes.getEstado()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ESTADO] ='"+catClientes.getEstado()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate =stmtUpdate+" WHERE [CLAVE] ="+catClientes.getClave(); 
		
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
	public String deleteByClave(int parseInt) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [CLAVE] ="+parseInt; 
	
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
			return "FalloTransaccionMetodo delete clase CatClientesDaoImpl"; 
		}else {
			return null;
		}
	}
	
}
