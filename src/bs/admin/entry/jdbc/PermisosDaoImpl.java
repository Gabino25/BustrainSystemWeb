package bs.admin.entry.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.admin.entry.dao.PermisosDao;
import bs.admin.entry.dto.Permisos;
import bs.eis.ResourceManager;

public class PermisosDaoImpl implements PermisosDao {
	protected java.sql.Connection userConn;
	
	private static final int COLUMN_ID = 1; 
	private static final int COLUMN_USUARIO = 2; 
	private static final int COLUMN_PANTALLA = 3; 
	private static final int COLUMN_PERMISO = 4; 
	
	private final String SQL_SELECT = "SELECT ID, USUARIO, PANTALLA, PERMISO, NIVEL from "+this.getTableName();
	private final String SQL_INSERT = " INSERT INTO "+this.getTableName()+" (USUARIO, PANTALLA, PERMISO, NIVEL) VALUES (?,?,?,?) ";
	public String getTableName()
	{
		return "BUSTRAIN.dbo.PERMISOS";
	}

	@Override
	public List<Permisos> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Permisos> listPermisos = new ArrayList<Permisos>();
	    try {
		    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		
	    		Permisos permisos = new Permisos(); 
	    		permisos.setId(rs.getInt("ID"));
	    		permisos.setUsuario(rs.getString("USUARIO"));
	    		permisos.setPantalla(rs.getString("PANTALLA"));
	    		permisos.setPermiso(rs.getString("PERMISO"));
	    		permisos.setNivel(rs.getString("NIVEL"));
	    		
	    		listPermisos.add(permisos);
	    		
	    	}
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
	    return listPermisos; 
	}

	@Override
	public String insert(Permisos permisosDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, permisosDto.getUsuario());
			stmt.setString(2, permisosDto.getPantalla());
			stmt.setString(3, permisosDto.getPermiso());
			stmt.setString(4, permisosDto.getNivel());
			
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
			return "FalloTransacaccionMetodo insert clase PermisosDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public List<Permisos> findPantallasByUser(String pAttrNombreUsuario) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Permisos> listPermisos = new ArrayList<Permisos>();
	    try {
		    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    stmt = conn.prepareStatement(SQL_SELECT+" where [USUARIO]='"+pAttrNombreUsuario+"' AND PERMISO='SI' ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		
	    		Permisos permisos = new Permisos(); 
	    		permisos.setUsuario(rs.getString("USUARIO"));
	    		permisos.setPantalla(rs.getString("PANTALLA"));
	    		permisos.setPermiso(rs.getString("PERMISO"));
	    		
	    		listPermisos.add(permisos);
	    		
	    	}
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
	    return listPermisos; 
	}

	@Override
	public String update(Permisos permisoDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(permisoDto.getPantalla())&&null!=permisoDto.getPantalla()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PANTALLA] ='"+permisoDto.getPantalla()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(permisoDto.getPermiso())&&null!=permisoDto.getPermiso()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PERMISO] ='"+permisoDto.getPermiso()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(permisoDto.getNivel())&&null!=permisoDto.getNivel()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NIVEL] ='"+permisoDto.getNivel()+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate =stmtUpdate+" WHERE [ID] ="+permisoDto.getId(); 
		
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
			return "FalloTransaccionMetodo update clase PermisosDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String deleteById(int pId) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [ID] ="+pId; 
	
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
			return "FalloTransaccionMetodo delete clase PermisosDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String findNivel(String strUsuario, String pStrPantalla) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strNivel = null; 
	    try {
		    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    stmt = conn.prepareStatement(SQL_SELECT+" where [USUARIO]='"+strUsuario+"' AND PERMISO='SI' AND PANTALLA ='"+pStrPantalla+"'",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strNivel = rs.getString("NIVEL");
	    	}
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
	    return strNivel; 
	    
	}

	
}
