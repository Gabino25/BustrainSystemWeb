package bs.vales.transpt.entry.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.eis.ResourceManager;
import bs.vales.transpt.entry.dao.ValesDao;
import bs.vales.transpt.entry.dto.Vales;

public class ValesDaoImpl implements ValesDao {

	protected java.sql.Connection userConn;
	
	private static final int COLUMN_NUMVALE     =1;  
	private static final int COLUMN_FOLIO       =2;
	private static final int COLUMN_FECHA       =3;
	private static final int COLUMN_OBSERVACIONES =4;
	private static final int COLUMN_COSTO       =5;
	private static final int COLUMN_RUTA        =6;
	private static final int COLUMN_OPERADOR    =7;
	private static final int COLUMN_ECO         =8;
	private static final int COLUMN_CLIENTE     =9;
	private static final int COLUMN_KMINICIAL   =10;
	private static final int COLUMN_HORAINICIAL =11;
	private static final int COLUMN_KMFINAL     =12;
	private static final int COLUMN_HORAFINAL   =13;
	private static final int COLUMN_TIPOVIAJE   =14;
	private static final int COLUMN_TIPOCOBRO   =15;
	private static final int COLUMN_CENTROCOSTOS =16;
	private static final int COLUMN_FACTURA     =17;
	private static final int COLUMN_USUARIO     =18;
	private static final int COLUMN_FECHACAPTURA =19;
	private static final int COLUMN_TIPOUNIDAD  =20;

	private final String SQL_SELECT ="SELECT [NUMVALE] " + 
			"      ,[FOLIO] " + 
			"      ,[FECHA] " + 
			"      ,[OBSERVACIONES] " + 
			"      ,[COSTO] " + 
			"      ,[RUTA] " + 
			"      ,[OPERADOR] " + 
			"      ,[ECO] " + 
			"      ,[CLIENTE] " + 
			"      ,[KMINICIAL] " + 
			"      ,[HORAINICIAL] " + 
			"      ,[KMFINAL] " + 
			"      ,[HORAFINAL] " + 
			"      ,[TIPOVIAJE] " + 
			"      ,[TIPOCOBRO] " + 
			"      ,[CENTROCOSTOS] " + 
			"      ,[FACTURA] " + 
			"      ,[USUARIO] " + 
			"      ,[FECHACAPTURA] " + 
			"      ,[TIPOUNIDAD] " + 
			"  FROM "+this.getTableName();
	
	private final String SQL_INSERT = "INSERT INTO [dbo].[VALES]( NUMVALE ,FOLIO ,FECHA ,OBSERVACIONES ,COSTO ,RUTA ,OPERADOR ,ECO ,CLIENTE ,KMINICIAL ,HORAINICIAL ,KMFINAL ,HORAFINAL ,TIPOVIAJE ,TIPOCOBRO ,CENTROCOSTOS ,FACTURA ,USUARIO ,FECHACAPTURA ,TIPOUNIDAD ) VALUES ( ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";
	
	public String getTableName()
	{
		return "BUSTRAIN.dbo.VALES";
	}
	
	
	@Override
	public String insert(Vales dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		
		try {
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareCall( SQL_INSERT ,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
			stmt.setLong(COLUMN_NUMVALE,dto.getNumvale());       /**     bigint] NOT **/
			stmt.setLong(COLUMN_FOLIO,dto.getFolio());       /**     bigint] NOT **/
			stmt.setTimestamp(COLUMN_FECHA,dto.getFecha());       /**     datetime] **/
			stmt.setString(COLUMN_OBSERVACIONES,dto.getObservaciones());       /**     text] **/
			stmt.setDouble(COLUMN_COSTO,dto.getCosto());       /**     float] **/
			stmt.setNString(COLUMN_RUTA,dto.getRuta());       /**     nvarchar](100) **/
			stmt.setNString(COLUMN_OPERADOR,dto.getOperador());       /**     nvarchar](50) **/
			stmt.setNString(COLUMN_ECO,dto.getEco());       /**     nvarchar](50) **/
			stmt.setNString(COLUMN_CLIENTE,dto.getCliente());       /**     nvarchar](250) **/
			stmt.setBigDecimal(COLUMN_KMINICIAL,dto.getKminicial());       /**     numeric](18, 0) **/
			stmt.setString(COLUMN_HORAINICIAL,dto.getHorainicial());       /**     varchar](50) **/
			stmt.setBigDecimal(COLUMN_KMFINAL,dto.getKmfinal());       /**     numeric](18, 0) **/
			stmt.setString(COLUMN_HORAFINAL,dto.getHorafinal());       /**     varchar](50) **/
			stmt.setString(COLUMN_TIPOVIAJE,dto.getTipoviaje());       /**     varchar](50) **/
			stmt.setString(COLUMN_TIPOCOBRO,dto.getTipocobro());       /**     varchar](50) **/
			stmt.setNString(COLUMN_CENTROCOSTOS,dto.getCentrocostos());       /**     nvarchar](50) **/
			stmt.setNString(COLUMN_FACTURA,dto.getFactura());       /**     nvarchar](50) **/
			stmt.setNString(COLUMN_USUARIO,dto.getUsuario());       /**     nvarchar](50) **/
			stmt.setTimestamp(COLUMN_FECHACAPTURA,dto.getFechacaptura());       /**     datetime] **/
			stmt.setString(COLUMN_TIPOUNIDAD,dto.getTipounidad());       /**     varchar](50) **/
			
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
			return "FalloTransacaccionMetodo insert clase ValesDaoImpl"; 
		}else {
			return null;
		}

	}

	
	@Override
	public List<Vales> findDif0() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Vales> listValesDto = new ArrayList<Vales>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " where DATEDIFF(dd,FECHACAPTURA,GETDATE())=0",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    	 Vales valesDto = new Vales();
	    	 
	    	valesDto.setNumvale(rs.getLong(COLUMN_NUMVALE));
    		valesDto.setFolio(rs.getLong(COLUMN_FOLIO));
    		valesDto.setFecha(rs.getTimestamp(COLUMN_FECHA));
    		valesDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); 
    		valesDto.setCosto(rs.getDouble(COLUMN_COSTO));
    		valesDto.setRuta(rs.getString(COLUMN_RUTA));
    		valesDto.setOperador(rs.getString(COLUMN_OPERADOR)); 
    		valesDto.setEco(rs.getNString(COLUMN_ECO));
    		valesDto.setCliente(rs.getNString(COLUMN_CLIENTE)); 
    		valesDto.setKminicial(rs.getBigDecimal(COLUMN_KMINICIAL));
    		valesDto.setHorainicial(rs.getString(COLUMN_HORAINICIAL));
    		valesDto.setKmfinal(rs.getBigDecimal(COLUMN_KMFINAL));
    		valesDto.setHorafinal(rs.getString(COLUMN_HORAFINAL));
    		valesDto.setTipoviaje(rs.getString(COLUMN_TIPOVIAJE));
    		valesDto.setTipocobro(rs.getString(COLUMN_TIPOCOBRO));
    		valesDto.setCentrocostos(rs.getNString(COLUMN_CENTROCOSTOS)); 
    		valesDto.setFactura(rs.getNString(COLUMN_FACTURA));
    		valesDto.setUsuario(rs.getNString(COLUMN_USUARIO));
    		valesDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA));
    		valesDto.setTipounidad(rs.getString(COLUMN_TIPOUNIDAD));
	    	 
	    	 listValesDto.add(valesDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listValesDto;
	}

	@Override
	public List<Vales> findByFechaDif1() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Vales> listValesDto = new ArrayList<Vales>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " where DATEDIFF(dd,FECHA,GETDATE())=1",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    	 Vales valesDto = new Vales();
	    	 
	    	valesDto.setNumvale(rs.getLong(COLUMN_NUMVALE));
    		valesDto.setFolio(rs.getLong(COLUMN_FOLIO));
    		valesDto.setFecha(rs.getTimestamp(COLUMN_FECHA));
    		valesDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); 
    		valesDto.setCosto(rs.getDouble(COLUMN_COSTO));
    		valesDto.setRuta(rs.getString(COLUMN_RUTA));
    		valesDto.setOperador(rs.getString(COLUMN_OPERADOR)); 
    		valesDto.setEco(rs.getNString(COLUMN_ECO));
    		valesDto.setCliente(rs.getNString(COLUMN_CLIENTE)); 
    		valesDto.setKminicial(rs.getBigDecimal(COLUMN_KMINICIAL));
    		valesDto.setHorainicial(rs.getString(COLUMN_HORAINICIAL));
    		valesDto.setKmfinal(rs.getBigDecimal(COLUMN_KMFINAL));
    		valesDto.setHorafinal(rs.getString(COLUMN_HORAFINAL));
    		valesDto.setTipoviaje(rs.getString(COLUMN_TIPOVIAJE));
    		valesDto.setTipocobro(rs.getString(COLUMN_TIPOCOBRO));
    		valesDto.setCentrocostos(rs.getNString(COLUMN_CENTROCOSTOS)); 
    		valesDto.setFactura(rs.getNString(COLUMN_FACTURA));
    		valesDto.setUsuario(rs.getNString(COLUMN_USUARIO));
    		valesDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA));
    		valesDto.setTipounidad(rs.getString(COLUMN_TIPOUNIDAD));
	    	 
	    	 listValesDto.add(valesDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listValesDto;
	}

	@Override
	public List<Vales> filtrarPorFechas(int pDesdeDay, int pDesdeMonth, int pDesdeYear, int pHastaDay, int pHastaMonth,
			int pHastaYear, String pEmpresa,
			   String pRuta,
			   String pOperador,
			   String pUnidad) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Vales> listValesDto = new ArrayList<Vales>();
	    String strStmt= SQL_SELECT+ " where fecha between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE) ";
	    if(null!=pEmpresa&&!"".contentEquals(pEmpresa)) {
	    strStmt=strStmt+"AND[CLIENTE]='"+pEmpresa+"'";
	    }
	    
	    if(null!=pRuta&&!"".contentEquals(pRuta)) {
		    strStmt=strStmt+"AND[RUTA]='"+pRuta+"'";
		    }
	    if(null!=pOperador&&!"".contentEquals(pOperador)) {
		    strStmt=strStmt+"AND[OPERADOR]='"+pOperador+"'";
		    }
	    if(null!=pUnidad&&!"".contentEquals(pUnidad)) {
		    strStmt=strStmt+"AND[ECO]='"+pUnidad+"'";
		    }
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	//System.out.println(strStmt);
	    	stmt = conn.prepareStatement(strStmt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    	 Vales valesDto = new Vales();
	    	 
	    	valesDto.setNumvale(rs.getLong(COLUMN_NUMVALE));
    		valesDto.setFolio(rs.getLong(COLUMN_FOLIO));
    		valesDto.setFecha(rs.getTimestamp(COLUMN_FECHA));
    		valesDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); 
    		valesDto.setCosto(rs.getDouble(COLUMN_COSTO));
    		valesDto.setRuta(rs.getString(COLUMN_RUTA));
    		valesDto.setOperador(rs.getString(COLUMN_OPERADOR)); 
    		valesDto.setEco(rs.getNString(COLUMN_ECO));
    		valesDto.setCliente(rs.getNString(COLUMN_CLIENTE)); 
    		valesDto.setKminicial(rs.getBigDecimal(COLUMN_KMINICIAL));
    		valesDto.setHorainicial(rs.getString(COLUMN_HORAINICIAL));
    		valesDto.setKmfinal(rs.getBigDecimal(COLUMN_KMFINAL));
    		valesDto.setHorafinal(rs.getString(COLUMN_HORAFINAL));
    		valesDto.setTipoviaje(rs.getString(COLUMN_TIPOVIAJE));
    		valesDto.setTipocobro(rs.getString(COLUMN_TIPOCOBRO));
    		valesDto.setCentrocostos(rs.getNString(COLUMN_CENTROCOSTOS)); 
    		valesDto.setFactura(rs.getNString(COLUMN_FACTURA));
    		valesDto.setUsuario(rs.getNString(COLUMN_USUARIO));
    		valesDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA));
    		valesDto.setTipounidad(rs.getString(COLUMN_TIPOUNIDAD));
	    	 
	    	 listValesDto.add(valesDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listValesDto;
		
	}
	
	@Override
	public String findNextNumvale() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextClave = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([NUMVALE])+1 NEXT_NUMVALE from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
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
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumero clase ValesDaoImpl";
	    }else {
	       return ""+nextClave;
	    }
	}


	@Override
	public String findByFolio(long pFolio) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strNumvale = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select folio from "+this.getTableName()+" where folio="+pFolio,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strNumvale = rs.getString(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    
		return strNumvale;
	
   }


	@Override
	public Vales findByNumVale(long pNumvale) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    Vales valesDto = new Vales();
   	 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " where NUMVALE="+pNumvale,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    	
	    	valesDto.setNumvale(rs.getLong(COLUMN_NUMVALE));
    		valesDto.setFolio(rs.getLong(COLUMN_FOLIO));
    		valesDto.setFecha(rs.getTimestamp(COLUMN_FECHA));
    		valesDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); 
    		valesDto.setCosto(rs.getDouble(COLUMN_COSTO));
    		valesDto.setRuta(rs.getString(COLUMN_RUTA));
    		valesDto.setOperador(rs.getString(COLUMN_OPERADOR)); 
    		valesDto.setEco(rs.getNString(COLUMN_ECO));
    		valesDto.setCliente(rs.getNString(COLUMN_CLIENTE)); 
    		valesDto.setKminicial(rs.getBigDecimal(COLUMN_KMINICIAL));
    		valesDto.setHorainicial(rs.getString(COLUMN_HORAINICIAL));
    		valesDto.setKmfinal(rs.getBigDecimal(COLUMN_KMFINAL));
    		valesDto.setHorafinal(rs.getString(COLUMN_HORAFINAL));
    		valesDto.setTipoviaje(rs.getString(COLUMN_TIPOVIAJE));
    		valesDto.setTipocobro(rs.getString(COLUMN_TIPOCOBRO));
    		valesDto.setCentrocostos(rs.getNString(COLUMN_CENTROCOSTOS)); 
    		valesDto.setFactura(rs.getNString(COLUMN_FACTURA));
    		valesDto.setUsuario(rs.getNString(COLUMN_USUARIO));
    		valesDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA));
    		valesDto.setTipounidad(rs.getString(COLUMN_TIPOUNIDAD));
	   
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return valesDto;
	}


	@Override
	public String update(Vales valesDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FOLIO] ="+valesDto.getFolio();
		countFields = countFields +1; 
		if(null!=valesDto.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+valesDto.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getObservaciones())&&null!=valesDto.getObservaciones()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [OBSERVACIONES] ='"+valesDto.getObservaciones()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COSTO] ="+valesDto.getCosto();
		countFields = countFields +1; 
		if(!"".equals(valesDto.getRuta())&&null!=valesDto.getRuta()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [RUTA] ='"+valesDto.getRuta()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getOperador())&&null!=valesDto.getOperador()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [OPERADOR] ='"+valesDto.getOperador()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getEco())&&null!=valesDto.getEco()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ECO] ='"+valesDto.getEco()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getCliente())&&null!=valesDto.getCliente()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CLIENTE] ='"+valesDto.getCliente()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KMINICIAL] ="+valesDto.getKminicial();
		countFields = countFields +1; 
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KMFINAL] ="+valesDto.getKmfinal();
		countFields = countFields +1; 
		if(!"".equals(valesDto.getHorainicial())&&null!=valesDto.getHorainicial()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [HORAINICIAL] ='"+valesDto.getHorainicial()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getHorafinal())&&null!=valesDto.getHorafinal()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [HORAFINAL] ='"+valesDto.getHorafinal()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getTipoviaje())&&null!=valesDto.getTipoviaje()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPOVIAJE] ='"+valesDto.getTipoviaje()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getTipocobro())&&null!=valesDto.getTipocobro()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPOCOBRO] ='"+valesDto.getTipocobro()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(valesDto.getTipounidad())&&null!=valesDto.getTipounidad()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPOUNIDAD] ='"+valesDto.getTipounidad()+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate =stmtUpdate+" WHERE [NUMVALE] ="+valesDto.getNumvale();
			
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
				return "FalloTransaccionMetodo update clase ValesDaoImpl"; 
			}else {
				return null;
			}
	}


	@Override
	public String deleteByNumVale(long pNumVale) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [NUMVALE] ="+pNumVale; 
	
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
			return "FalloTransaccionMetodo delete clase ValesDaoImpl"; 
		}else {
			return null;
		}
	}

}
