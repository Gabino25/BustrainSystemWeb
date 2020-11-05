package bs.ap.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.ap.dao.CuentasOperacionDao;
import bs.ap.dto.CuentasOperacion;
import bs.eis.ResourceManager;

public class CuentasOperacionDaoImpl implements CuentasOperacionDao {
	
    protected java.sql.Connection userConn;
	
	private static final int COLUMN_ID = 1; 
	private static final int COLUMN_CUENTA = 2; 
	private static final int COLUMN_NOMBRE = 3; 
	
	private final String SQL_SELECT = "SELECT ID, CUENTA, NOMBRE from "+this.getTableName();
	private final String SQL_INSERT = " INSERT INTO "+this.getTableName()+" (CUENTA, NOMBRE) VALUES (?,?) ";
	public String getTableName()
	{
		return "BUSTRAIN.dbo.CUENTAS";
	}

	@Override
	public List<CuentasOperacion> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
		List<CuentasOperacion> listCuentasOperacion = new ArrayList<CuentasOperacion>();
		  try {
			    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
		    	rs = stmt.executeQuery(); 
		    	while(rs.next()) {
		    		
		    		CuentasOperacion cuentasOperacion = new CuentasOperacion(); 
		    		cuentasOperacion.setId(rs.getInt("ID"));
		    		cuentasOperacion.setCuenta(rs.getString("CUENTA"));
		    		cuentasOperacion.setNombrecuenta(rs.getString("NOMBRE"));
		    		
		    		listCuentasOperacion.add(cuentasOperacion);
		    		
		    	}
		    }
		    finally {
			   	ResourceManager.close(rs);
			   	ResourceManager.close(stmt);
			   	if(null==this.userConn) {
			  	ResourceManager.close(conn);
			   	}
			 }
		  
		return listCuentasOperacion;
	}

	@Override
	public String insert(CuentasOperacion cuentasOperacionDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, cuentasOperacionDto.getCuenta());
			stmt.setString(2, cuentasOperacionDto.getNombrecuenta());
			
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
			return "FalloTransacaccionMetodo insert clase CuentasOperacionDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String update(CuentasOperacion cuentasOperacionDto) throws SQLException {
		
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(cuentasOperacionDto.getCuenta())&&null!=cuentasOperacionDto.getCuenta()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CUENTA] ='"+cuentasOperacionDto.getCuenta()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(cuentasOperacionDto.getNombrecuenta())&&null!=cuentasOperacionDto.getNombrecuenta()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOMBRE] ='"+cuentasOperacionDto.getNombrecuenta()+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate =stmtUpdate+" WHERE [ID] ="+cuentasOperacionDto.getId(); 
		
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
			return "FalloTransaccionMetodo update clase CuentasOperacionDaoImpl"; 
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
			return "FalloTransaccionMetodo delete clase CuentasOperacionDaoImpl"; 
		}else {
			return null;
		}
	}

}
