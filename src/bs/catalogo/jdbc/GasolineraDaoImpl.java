package bs.catalogo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.GasolineraDao;
import bs.catalogo.dto.Gasolinera;
import bs.eis.ResourceManager;

public class GasolineraDaoImpl implements GasolineraDao {
	protected java.sql.Connection userConn;
	private final static int ID_COLUMN =1;
	private final static int NOMBRE_COLUMN =2;
	private final static int DIRECCION_COLUMN =3;
	private final static int TELEFONO_COLUMN =4;
	private final static int ESTADO_COLUMN =5;
	private final static int CORREO_COLUMN =6;
	protected final String SQL_SELECT ="select id, nombre, direccion, telefono, estado, correo from "+this.getTableName(); 
	public String getTableName()
	{
		return "BUSTRAIN.dbo.GASOLINERA";
	}
	@Override
	public List<Gasolinera> findGasolineraList() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Gasolinera> listGasolinera = new ArrayList<Gasolinera>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+" order by nombre asc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    	 Gasolinera gasolineraDto = new Gasolinera();
	    	 
	    	 gasolineraDto.setId(rs.getInt("id"));
	    	 gasolineraDto.setNombre(rs.getNString("nombre"));
	    	 
	    	 listGasolinera.add(gasolineraDto);
	    	}	
	    }finally{
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    return listGasolinera; 
	 
	} /** ListGasolinera **/
}
