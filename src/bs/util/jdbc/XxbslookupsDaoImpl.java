package bs.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.eis.ResourceManager;
import bs.util.dao.XxbslookupsDao;
import bs.util.dto.Xxbslookups;

public class XxbslookupsDaoImpl implements XxbslookupsDao {

	protected java.sql.Connection userConn;
	protected final String SQL_SELECT = "SELECT id, NAME, CODE FROM " + getTableName() + "";
	protected int maxRows;
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( NAME, CODE ) VALUES ( ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET NAME = ?, CODE = ? WHERE id = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE id = ?";
	protected static final int COLUMN_ID = 1;
	protected static final int COLUMN_NAME = 2;
	protected static final int COLUMN_CODE = 3;
	protected static final int NUMBER_OF_COLUMNS = 3;

	public String getTableName()
	{
		return "BUSTRAIN.dbo.XXBSLOOKUPS";
	}

	
	@Override
	public String insert(Xxbslookups dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareCall( SQL_INSERT ,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1,dto.getName());
			stmt.setString(2,dto.getCode());
			stmt.execute();
		    rowsAffected = stmt.getUpdateCount();
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
			return "FalloTransacaccionMetodo insert clase XxbslookupDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String update(Xxbslookups Xxbslookupsdto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(Xxbslookupsdto.getName())&&null!=Xxbslookupsdto.getName()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NAME] ='"+Xxbslookupsdto.getName()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(Xxbslookupsdto.getCode())&&null!=Xxbslookupsdto.getCode()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CODE] ='"+Xxbslookupsdto.getCode()+"'";
			countFields = countFields +1; 
		}
		
        stmtUpdate =stmtUpdate+" WHERE [ID] ="+Xxbslookupsdto.getId(); 
		
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
			return "FalloTransaccionMetodo update clase XxbslookupsDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String delete(int pId) throws SQLException {
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
			return "FalloTransaccionMetodo delete clase XxbslookupsDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public List<Xxbslookups> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Xxbslookups findWhereIdEquals(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xxbslookups> findWhereNameEquals(String name) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Xxbslookups> listXxbslookups = new ArrayList<Xxbslookups>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Xxbslookups xxbslookups = new Xxbslookups(); 
	    		xxbslookups.setId(rs.getInt("ID"));
	    		xxbslookups.setName(rs.getString("NAME"));
	    		xxbslookups.setCode(rs.getString("CODE"));
	    		listXxbslookups.add(xxbslookups);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listXxbslookups;
	}

}
