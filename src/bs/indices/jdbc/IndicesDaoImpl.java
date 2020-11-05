package bs.indices.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bs.eis.ResourceManager;
import bs.indices.dao.IndicesDao;

public class IndicesDaoImpl implements IndicesDao {
	
	protected java.sql.Connection userConn; 
	
	private static final int COLUMN_NUMFACT=1; /** [int] NULL, **/
	private static final int COLUMN_NUMFALLA=2; /** [int] NULL, **/
	private static final int COLUMN_NUMVALE=3; /** [numeric](18, 0) NULL, **/
	private static final int COLUMN_NUMASIGNACION=4; /** [int] NULL, **/
	private static final int COLUMN_NUMERORUTA=5; /** [int] NULL, **/
	private static final int COLUMN_NUMFACTSERV=6; /** [int] NULL, **/
	private static final int COLUMN_FOLIOLLANTAS=7; /** [int] NULL, **/
	private static final int COLUMN_FOLIOMOVLLANTAS=8; /** [int] NULL, **/
	private static final int COLUMN_CLAVESEGURIDAD=9; /** [nchar](10) NULL, **/
	private static final int COLUMN_CLAVEBORRAR=10; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_NUMPROVEEDOR=11; /** [int] NULL **/
	
	  public String getTableName()
			{
				return "BUSTRAIN.dbo.INDICES";
			}
	  
	@Override
	public String findNextNumfact() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int numfact = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("SELECT NUMFACT from "+getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    	  numfact =	rs.getInt(1); 
	    	  numfact = numfact +1; 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    if(0==numfact) {
	      return "NoSePudoRecuperaInformacionMetodo findNextNumfact clase IndicesDaoImpl";	
	    }else {
		return ""+numfact;
	    }
	}
	@Override
	public String findNextNumfalla() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextNumvale() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextNumasignacion() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextNumeroruta() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextNumfactserv() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextFoliollantas() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextFoliomovllantas() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextClaveseguridad() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextClaveborrar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findNextNumproveedor() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
