package bs.catalogo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.CatRutasDao;
import bs.catalogo.dto.CatRutas;
import bs.eis.ResourceManager;

public class CatRutasDaoImpl implements CatRutasDao {

	protected java.sql.Connection userConn;
	
	private static final int COLUMN_NUMERO=1; /** [numeric](18, 0) NULL, **/
	private static final int COLUMN_CLAVE=2; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_CLIENTE=3; /** [nvarchar](max) NULL, **/
	private static final int COLUMN_RUTA=4; /** [nvarchar](max) NULL, **/
	private static final int COLUMN_COSTO=5; /** [real] NULL, **/
	private static final int COLUMN_FECHA=6; /** [datetime] NULL, **/
	private static final int COLUMN_DESCRIPCION=7; /** [nvarchar](max) NULL, **/
	private static final int COLUMN_TIPOUNIDAD=8; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_TIPOPAGO=9; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_HORAINICIO=10; /** [smalldatetime] NULL, **/
	private static final int COLUMN_HORAFIN=11; /** [smalldatetime] NULL, **/
	private static final int COLUMN_ESTADO=12; /** [numeric](18, 0) NULL **/
	
	protected final String SQL_SELECT ="select numero, clave, cliente, ruta, costo, fecha, descripcion, tipounidad, tipopago, horainicio, horafin, estado from "+this.getTableName(); 
	protected final String SQL_INSERT = "insert into [dbo].[RUTAS] ( NUMERO ,CLAVE ,CLIENTE ,RUTA  ,COSTO ,FECHA ,DESCRIPCION ,TIPOUNIDAD ,TIPOPAGO ,HORAINICIO ,HORAFIN ,ESTADO) VALUES (? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
	public String getTableName()
	{
		return "BUSTRAIN.dbo.RUTAS";
	}
	
	@Override
	public String insert(CatRutas dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;  
		try {
		conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			
		stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
	   
		stmt.setInt(COLUMN_NUMERO,dto.getNumero());     /* int](18, 0) NULL,  */          
		stmt.setNString(COLUMN_CLAVE,dto.getClave());      /* nvarchar](50) NULL,    */           
		stmt.setNString(COLUMN_CLIENTE,dto.getCliente());    /* nvarchar](max) NULL,   */         
		stmt.setNString(COLUMN_RUTA,dto.getRuta());       /* nvarchar](max) NULL,   */            
		stmt.setFloat(COLUMN_COSTO,dto.getCosto());      /* real] NULL,            */           
		stmt.setTimestamp(COLUMN_FECHA,dto.getFecha());      /* datetime] NULL,        */           
		stmt.setNString(COLUMN_DESCRIPCION,dto.getDescripcion());/* nvarchar](max) NULL,   */     
		stmt.setNString(COLUMN_TIPOUNIDAD,dto.getTipounidad()); /* nvarchar](50) NULL,    */      
		stmt.setNString(COLUMN_TIPOPAGO,dto.getTipopago());   /* nvarchar](50) NULL,    */        
		stmt.setTimestamp(COLUMN_HORAINICIO,dto.getHorainicio()); /* smalldatetime] NULL,   */      
		stmt.setTimestamp(COLUMN_HORAFIN,dto.getHorafin());    /* smalldatetime] NULL,   */         
		stmt.setBigDecimal(COLUMN_ESTADO,dto.getEstado());     /* numeric](18, 0) NULL   */
		
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
			 return "FalloTransacaccionMetodo insert clase CatRutasDaoImpl"; 
		}else {
			return null; 
		}
		
	}
	
	@Override
	public List<CatRutas> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatRutas> listCatRutas = new ArrayList<CatRutas>();
	    
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatRutas catRutasDto = new CatRutas(); 
	    		
	    		catRutasDto.setNumero(rs.getInt("numero"));
	    		catRutasDto.setClave(rs.getNString("clave"));
	    		catRutasDto.setCliente(rs.getNString("cliente"));
	    		catRutasDto.setRuta(rs.getString("ruta"));
	    		catRutasDto.setCosto(rs.getFloat("costo"));
	    		catRutasDto.setFecha(rs.getTimestamp("fecha"));
	    		catRutasDto.setDescripcion(rs.getNString("descripcion"));
	    	    catRutasDto.setTipounidad(rs.getNString("tipounidad"));
	    	    catRutasDto.setTipopago(rs.getNString("tipopago"));
	    	    catRutasDto.setHorainicio(rs.getTimestamp("horainicio"));
	    	    catRutasDto.setHorafin(rs.getTimestamp("horafin"));
	    	    catRutasDto.setEstado(rs.getBigDecimal("estado"));
	    		
	    		listCatRutas.add(catRutasDto);
	    	}
	    }finally{
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return listCatRutas;
	}
	@Override
	public List<CatRutas> findRutasList() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatRutas> listCatRutas = new ArrayList<CatRutas>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select numero, ruta from "+this.getTableName()+" order by ruta asc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatRutas catRutasDto = new CatRutas(); 
	    		
	    		catRutasDto.setNumero(rs.getInt("numero"));
	    		catRutasDto.setRuta(rs.getString("ruta"));
	    		
	    		listCatRutas.add(catRutasDto);
	    	}
	    }finally{
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return listCatRutas;
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
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumero clase CatRutasDaoImpl";
	    }else {
	       return ""+nextClave;
	    }
	}

	@Override
	public List<CatRutas> findByEmpresa(String strEmpresa) throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    List<CatRutas> listCatRutas = new ArrayList<CatRutas>();
			    try {
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	stmt = conn.prepareStatement("select numero, ruta from "+this.getTableName()+" where cliente = '"+strEmpresa+"' order by ruta asc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	while(rs.next()) {
			    		CatRutas catRutasDto = new CatRutas(); 
			    		
			    		catRutasDto.setNumero(rs.getInt("numero"));
			    		catRutasDto.setRuta(rs.getString("ruta"));
			    		
			    		listCatRutas.add(catRutasDto);
			    	}
			    }finally{
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }
			    
				return listCatRutas;
	}

	@Override
	public String findCostoByRuta(String strRuta) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strCosto = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select COSTO  from "+this.getTableName()+" where ruta = '"+strRuta+"'",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strCosto = rs.getString(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    return strCosto; 
	}

	@Override
	public CatRutas findByNumero(int pNumero) throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    CatRutas catRutasDto = new CatRutas(); 
			    
			    try {
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	stmt = conn.prepareStatement(SQL_SELECT+" where numero="+pNumero,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	if(rs.next()) {
			    		
			    		catRutasDto.setNumero(rs.getInt("numero"));
			    		catRutasDto.setClave(rs.getNString("clave"));
			    		catRutasDto.setCliente(rs.getNString("cliente"));
			    		catRutasDto.setRuta(rs.getString("ruta"));
			    		catRutasDto.setCosto(rs.getFloat("costo"));
			    		catRutasDto.setFecha(rs.getTimestamp("fecha"));
			    		catRutasDto.setDescripcion(rs.getNString("descripcion"));
			    	    catRutasDto.setTipounidad(rs.getNString("tipounidad"));
			    	    catRutasDto.setTipopago(rs.getNString("tipopago"));
			    	    catRutasDto.setHorainicio(rs.getTimestamp("horainicio"));
			    	    catRutasDto.setHorafin(rs.getTimestamp("horafin"));
			    	    catRutasDto.setEstado(rs.getBigDecimal("estado"));
			    
			    	}
			    }finally{
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }
			    
				return catRutasDto;
	}

	@Override
	public String update(CatRutas catRutasDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(catRutasDto.getClave())&&null!=catRutasDto.getClave()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CLAVE] ='"+catRutasDto.getClave()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catRutasDto.getCliente())&&null!=catRutasDto.getCliente()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CLIENTE] ='"+catRutasDto.getCliente()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catRutasDto.getRuta())&&null!=catRutasDto.getRuta()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [RUTA] ='"+catRutasDto.getRuta()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COSTO] ="+catRutasDto.getCosto();
		countFields = countFields +1; 
		
		if(null!=catRutasDto.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+catRutasDto.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(catRutasDto.getDescripcion())&&null!=catRutasDto.getDescripcion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DESCRIPCION] ='"+catRutasDto.getDescripcion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catRutasDto.getTipounidad())&&null!=catRutasDto.getTipounidad()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPOUNIDAD] ='"+catRutasDto.getTipounidad()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catRutasDto.getTipopago())&&null!=catRutasDto.getTipopago()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPOPAGO] ='"+catRutasDto.getTipopago()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate =stmtUpdate+" WHERE [NUMERO] ="+catRutasDto.getNumero(); 
		
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
			return "FalloTransaccionMetodo update clase CatRutasDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String deleteByNumero(int pNumero) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [NUMERO] ="+pNumero; 
	
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
			return "FalloTransaccionMetodo delete clase CatRutasDaoImpl"; 
		}else {
			return null;
		}
		
	}
	

}
