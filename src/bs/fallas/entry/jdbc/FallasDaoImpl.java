package bs.fallas.entry.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bs.eis.ResourceManager;
import bs.fallas.entry.dao.FallasDao;
import bs.fallas.entry.dto.Fallas;
import bs.util.Utils;


public class FallasDaoImpl implements FallasDao {

	protected java.sql.Connection userConn; 
	
	private static final int COLUMN_NUMERO=1; /**numeric](18, 0) NULL, **/
	private static final int COLUMN_ECO=2; /**nvarchar](50) NULL, **/
	private static final int COLUMN_REPORTA=3; /**nvarchar](50) NULL, **/
	private static final int COLUMN_KILOMETRAJE=4; /**float] NULL, **/
	private static final int COLUMN_DESCRIPCION=5; /**text] NULL, **/
	private static final int COLUMN_FECHAREP=6; /**datetime] NULL, **/
	private static final int COLUMN_FECHA=7; /**datetime] NULL, **/
	private static final int COLUMN_HORA=8; /**datetime] NULL, **/
	private static final int COLUMN_ESTADO=9; /**nvarchar](50) NULL, **/
	private static final int COLUMN_REPARACION=10; /**text] NULL, **/
	private static final int COLUMN_COSTO=11; /**numeric](18, 0) NULL, **/
	private static final int COLUMN_USUARIO=12; /**nvarchar](50) NULL, **/
	private static final int COLUMN_FECHAALTA=13; /**smalldatetime] NULL, **/
	private static final int COLUMN_TIPO=14; /**nvarchar](50) NULL **/ 
	
	private final String SQL_SELECT ="SELECT [NUMERO] " + 
			"      ,[ECO] " + 
			"      ,[REPORTA] " + 
			"      ,[KILOMETRAJE] " + 
			"      ,[DESCRIPCION] " + 
			"      ,[FECHAREP] " + 
			"      ,[FECHA] " + 
			"      ,[HORAA] " + 
			"      ,[ESTADO] " + 
			"      ,[REPARACION] " + 
			"      ,[COSTO] " + 
			"      ,[usuario] " + 
			"      ,[fechaalta] " + 
			"      ,[TIPO] " + 
			"  FROM "+getTableName(); 
	
	private final String SQL_SELECT_TOP1000 ="SELECT TOP(1000) [NUMERO] " + 
			"      ,[ECO] " + 
			"      ,[REPORTA] " + 
			"      ,[KILOMETRAJE] " + 
			"      ,[DESCRIPCION] " + 
			"      ,[FECHAREP] " + 
			"      ,[FECHA] " + 
			"      ,[HORAA] " + 
			"      ,[ESTADO] " + 
			"      ,[REPARACION] " + 
			"      ,[COSTO] " + 
			"      ,[usuario] " + 
			"      ,[fechaalta] " + 
			"      ,[TIPO] " + 
			"  FROM "+getTableName(); 
	
	private final String SQL_INSERT= " INSERT INTO [dbo].[FALLAS] ([NUMERO]  ,[ECO] ,[REPORTA] ,[KILOMETRAJE] ,[DESCRIPCION] ,[FECHAREP]  ,[FECHA] ,[HORAA]  ,[ESTADO]  ,[REPARACION]  ,[COSTO] ,[usuario] ,[fechaalta] ,[TIPO]  )  VALUES (  ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";
	
	   public String getTableName()
			{
				return "BUSTRAIN.dbo.FALLAS";
			}
			
	@Override
	public List<Fallas> findRealizados() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Fallas> listFallas = new ArrayList<Fallas>(); 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " WHERE ESTADO='REALIZADO' ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Fallas fallasDto = new Fallas();
	    		
	    		fallasDto.setNumero(rs.getLong(COLUMN_NUMERO)); /** numeric](18, 0) null, **/
	    		fallasDto.setEco(rs.getNString(COLUMN_ECO)); /** nvarchar](50) null, **/
	    		fallasDto.setReporta(rs.getNString(COLUMN_REPORTA)); /** nvarchar](50) null, **/
	    		fallasDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE)); /** float] null, **/
	    		fallasDto.setDescripcion(rs.getString(COLUMN_DESCRIPCION)); /** text] null, **/
	    		fallasDto.setFecharep(rs.getTimestamp(COLUMN_FECHAREP)); /** datetime] null, **/
	    		fallasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /** datetime] null, **/
	    		fallasDto.setHora(rs.getTimestamp(COLUMN_HORA)); /** datetime] null, **/
	    		fallasDto.setEstado(rs.getNString(COLUMN_ESTADO)); /** nvarchar](50) null, **/
	    		fallasDto.setReparacion(rs.getString(COLUMN_REPARACION)); /** text] null, **/
	    		fallasDto.setCosto(rs.getBigDecimal(COLUMN_COSTO)); /** numeric](18, 0) null, **/
	    		fallasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /** nvarchar](50) null, **/
	    		fallasDto.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA)); /** smalldatetime] null, **/
	    		fallasDto.setTipo(rs.getNString(COLUMN_TIPO)); /** nvarchar](50) null **/
	    		
	    		listFallas.add(fallasDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return listFallas; 
	}

	@Override
	public List<Fallas> findRealizadosTop1000() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Fallas> listFallas = new ArrayList<Fallas>(); 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT_TOP1000+ " WHERE ESTADO='REALIZADO' ORDER BY NUMERO DESC",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Fallas fallasDto = new Fallas();
	    		
	    		fallasDto.setNumero(rs.getLong(COLUMN_NUMERO)); /** numeric](18, 0) null, **/
	    		fallasDto.setEco(rs.getNString(COLUMN_ECO)); /** nvarchar](50) null, **/
	    		fallasDto.setReporta(rs.getNString(COLUMN_REPORTA)); /** nvarchar](50) null, **/
	    		fallasDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE)); /** float] null, **/
	    		fallasDto.setDescripcion(rs.getString(COLUMN_DESCRIPCION)); /** text] null, **/
	    		fallasDto.setFecharep(rs.getTimestamp(COLUMN_FECHAREP)); /** datetime] null, **/
	    		fallasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /** datetime] null, **/
	    		fallasDto.setHorav2(rs.getString(COLUMN_HORA)); /** datetime] null, **/
	    		fallasDto.setEstado(rs.getNString(COLUMN_ESTADO)); /** nvarchar](50) null, **/
	    		fallasDto.setReparacion(rs.getString(COLUMN_REPARACION)); /** text] null, **/
	    		fallasDto.setCosto(rs.getBigDecimal(COLUMN_COSTO)); /** numeric](18, 0) null, **/
	    		fallasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /** nvarchar](50) null, **/
	    		fallasDto.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA)); /** smalldatetime] null, **/
	    		fallasDto.setTipo(rs.getNString(COLUMN_TIPO)); /** nvarchar](50) null **/
	    		
	    		listFallas.add(fallasDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return listFallas; 
	}
	
	@Override
	public List<Fallas> findPendientes() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Fallas> listFallas = new ArrayList<Fallas>(); 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " WHERE ESTADO='PENDIENTE' ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Fallas fallasDto = new Fallas();
	    		
	    		fallasDto.setNumero(rs.getLong(COLUMN_NUMERO)); /** numeric](18, 0) null, **/
	    		fallasDto.setEco(rs.getNString(COLUMN_ECO)); /** nvarchar](50) null, **/
	    		fallasDto.setReporta(rs.getNString(COLUMN_REPORTA)); /** nvarchar](50) null, **/
	    		fallasDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE)); /** float] null, **/
	    		fallasDto.setDescripcion(rs.getString(COLUMN_DESCRIPCION)); /** text] null, **/
	    		fallasDto.setFecharep(rs.getTimestamp(COLUMN_FECHAREP)); /** datetime] null, **/
	    		fallasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /** datetime] null, **/
	    		fallasDto.setHorav2(rs.getString(COLUMN_HORA)); /** datetime] null, **/
	    		fallasDto.setEstado(rs.getNString(COLUMN_ESTADO)); /** nvarchar](50) null, **/
	    		fallasDto.setReparacion(rs.getString(COLUMN_REPARACION)); /** text] null, **/
	    		fallasDto.setCosto(rs.getBigDecimal(COLUMN_COSTO)); /** numeric](18, 0) null, **/
	    		fallasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /** nvarchar](50) null, **/
	    		fallasDto.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA)); /** smalldatetime] null, **/
	    		fallasDto.setTipo(rs.getNString(COLUMN_TIPO)); /** nvarchar](50) null **/
	    		
	    		listFallas.add(fallasDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return listFallas; 
	}

	@Override
	public Fallas findByNumero(String pNumero) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    Fallas fallasDto = new Fallas();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " WHERE NUMERO="+pNumero,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		
	    		
	    		fallasDto.setNumero(rs.getLong(COLUMN_NUMERO)); /** numeric](18, 0) null, **/
	    		fallasDto.setEco(rs.getNString(COLUMN_ECO)); /** nvarchar](50) null, **/
	    		fallasDto.setReporta(rs.getNString(COLUMN_REPORTA)); /** nvarchar](50) null, **/
	    		fallasDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE)); /** float] null, **/
	    		fallasDto.setDescripcion(rs.getString(COLUMN_DESCRIPCION)); /** text] null, **/
	    		fallasDto.setFecharep(rs.getTimestamp(COLUMN_FECHAREP)); /** datetime] null, **/
	    		fallasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /** datetime] null, **/
	    		fallasDto.setHorav2(rs.getString(COLUMN_HORA)); /** datetime] null, **/
	    		fallasDto.setEstado(rs.getNString(COLUMN_ESTADO)); /** nvarchar](50) null, **/
	    		fallasDto.setReparacion(rs.getString(COLUMN_REPARACION)); /** text] null, **/
	    		fallasDto.setCosto(rs.getBigDecimal(COLUMN_COSTO)); /** numeric](18, 0) null, **/
	    		fallasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /** nvarchar](50) null, **/
	    		fallasDto.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA)); /** smalldatetime] null, **/
	    		fallasDto.setTipo(rs.getNString(COLUMN_TIPO)); /** nvarchar](50) null **/
	    		
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return fallasDto; 
	}

	@Override
	public String insert(Fallas fallasDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		int rowsAffected = 0; 
			try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setLong(COLUMN_NUMERO, fallasDto.getNumero());/** [NUMERO][bigint](18, 0) NULL,**/
			stmt.setNString(COLUMN_ECO, fallasDto.getEco());/** [ECO][nvarchar](50) NULL, **/
			stmt.setNString(COLUMN_REPORTA, fallasDto.getReporta());/** [REPORTA][nvarchar](50) NULL, **/
			stmt.setDouble(COLUMN_KILOMETRAJE, fallasDto.getKilometraje());/** [KILOMETRAJE][float] NULL,**/
			stmt.setString(COLUMN_DESCRIPCION, fallasDto.getDescripcion());/** [DESCRIPCION][text] NULL, **/
			/** [FECHAREP][datetime] NULL,**/
			if(null!=fallasDto.getFecharep()) {
				stmt.setTimestamp(COLUMN_FECHAREP, fallasDto.getFecharep());
			}else {
				stmt.setNull(COLUMN_FECHAREP, java.sql.Types.TIMESTAMP);
			}
			/** [FECHA][datetime] NULL, **/
			if(null!=fallasDto.getFecha()) {
				stmt.setTimestamp(COLUMN_FECHA, fallasDto.getFecha());
			}else {
				stmt.setNull(COLUMN_FECHA, java.sql.Types.TIMESTAMP);
			}
			/** [HORA][datetime] NULL,**/
			if(null!=fallasDto.getHora()) {
				stmt.setTimestamp(COLUMN_HORA, fallasDto.getHora());
			}else {
				stmt.setNull(COLUMN_HORA, java.sql.Types.TIMESTAMP);
			}
			stmt.setNString(COLUMN_ESTADO, fallasDto.getEstado());/** [ESTADO][nvarchar](50) NULL,**/
			stmt.setString(COLUMN_REPARACION, fallasDto.getReparacion());/** [REPARACION][text] NULL,**/
			/** [COSTO][numeric](18, 0) NULL, **/
			if(null!=fallasDto.getCosto()) {
				stmt.setBigDecimal(COLUMN_COSTO, fallasDto.getCosto());
			}else {
				stmt.setNull(COLUMN_COSTO, java.sql.Types.NUMERIC);
			}
			stmt.setNString(COLUMN_USUARIO, fallasDto.getUsuario());/** [usuario][nvarchar](50) NULL, **/
			/** [fechaalta][smalldatetime] NULL,**/
			if(null!=fallasDto.getFechaalta()) {
				stmt.setTimestamp(COLUMN_FECHAALTA, fallasDto.getFechaalta());
			}else {
				stmt.setNull(COLUMN_FECHAALTA, java.sql.Types.TIMESTAMP);
			}
			stmt.setNString(COLUMN_TIPO, fallasDto.getTipo());/** [TIPO][nvarchar](50) NULL **/
			
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
				return "FalloTransaccionMetodo insert clase FallasDaoImpl"; 
			}else {
				return null;
			}
	
	}

	
	@Override
	public String findNextNumero() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextFolio = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([NUMERO])+1 NEXT_NUMERO from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		nextFolio = rs.getInt(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    if(0==nextFolio) {
	    	 return "NoSePudoRecuperaInformacionMetodo findNextFolio clase FallasDaoImpl";
	    }else {
	       return ""+nextFolio;
	    }
	}

	@Override
	public String update(Fallas fallasDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
        
		if(!"".equals(fallasDto.getEco())&&null!=fallasDto.getEco()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ECO] ='"+fallasDto.getEco()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(fallasDto.getReporta())&&null!=fallasDto.getReporta()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [REPORTA] ='"+fallasDto.getReporta()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(fallasDto.getTipo())&&null!=fallasDto.getTipo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPO] ='"+fallasDto.getTipo()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(fallasDto.getDescripcion())&&null!=fallasDto.getDescripcion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DESCRIPCION] ='"+fallasDto.getDescripcion()+"'";
			countFields = countFields +1; 
		}
		if(null!=fallasDto.getFecharep()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHAREP] = CONVERT(datetime,'"+fallasDto.getFecharep()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(fallasDto.getEstado())&&null!=fallasDto.getEstado()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ESTADO] ='"+fallasDto.getEstado()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(fallasDto.getReparacion())&&null!=fallasDto.getReparacion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [REPARACION] ='"+fallasDto.getReparacion()+"'";
			countFields = countFields +1; 
		}
		if(fallasDto.getKilometraje()!=0d) {
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KILOMETRAJE] ="+fallasDto.getKilometraje();
		countFields = countFields +1; 
		}
		
		stmtUpdate =stmtUpdate+" WHERE [NUMERO] ="+fallasDto.getNumero(); 
		
		
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
			return "FalloTransaccionMetodo update clase FallasDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String deleteByNumero(String pNumero) throws SQLException {
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
			return "FalloTransaccionMetodo delete clase FallasDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public List<Fallas> findTop1000() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Fallas> listFallas = new ArrayList<Fallas>(); 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT_TOP1000+ " ORDER BY NUMERO DESC",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Fallas fallasDto = new Fallas();
	    		
	    		fallasDto.setNumero(rs.getLong(COLUMN_NUMERO)); /** numeric](18, 0) null, **/
	    		fallasDto.setEco(rs.getNString(COLUMN_ECO)); /** nvarchar](50) null, **/
	    		fallasDto.setReporta(rs.getNString(COLUMN_REPORTA)); /** nvarchar](50) null, **/
	    		fallasDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE)); /** float] null, **/
	    		fallasDto.setDescripcion(rs.getString(COLUMN_DESCRIPCION)); /** text] null, **/
	    		fallasDto.setFecharep(rs.getTimestamp(COLUMN_FECHAREP)); /** datetime] null, **/
	    		fallasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /** datetime] null, **/
	    		fallasDto.setHorav2(rs.getString(COLUMN_HORA)); /** datetime] null, **/
	    		fallasDto.setEstado(rs.getNString(COLUMN_ESTADO)); /** nvarchar](50) null, **/
	    		fallasDto.setReparacion(rs.getString(COLUMN_REPARACION)); /** text] null, **/
	    		fallasDto.setCosto(rs.getBigDecimal(COLUMN_COSTO)); /** numeric](18, 0) null, **/
	    		fallasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /** nvarchar](50) null, **/
	    		fallasDto.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA)); /** smalldatetime] null, **/
	    		fallasDto.setTipo(rs.getNString(COLUMN_TIPO)); /** nvarchar](50) null **/
	    		
	    		listFallas.add(fallasDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return listFallas;
	}

	@Override
	//SE REALIZA LA CONSULTA A LA BASE DE DATOS Y SE CONCATENA CON EL NUEVO PARAMETRO ENVIADO DE FALLASANALIZARCO.JAVA
	public List<Fallas> filter(int pDesdeDay
								, int pDesdeMonth
								, int pDesdeYear
								, int pHastaDay
								, int pHastaMonth
								,int pHastaYear
								,String strUnidad
								,String strestado
								) throws SQLException {
		String strLocalStmt = SQL_SELECT+" where fecharep between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE) ";
		
		if(null!=strUnidad&&!"".equals(strUnidad)) {
			strLocalStmt=strLocalStmt+" AND ECO='"+strUnidad+"'";
		}
		//SE EVALUA QUE EL VALOR SEA DIFERENTE DE NULL Y SI ES ASI DESPUES SE CONCATENA EL PARAMETRO A LA CADENA
		if(null!=strestado&&!"".equals(strestado)) {
				strLocalStmt=strLocalStmt+" AND ESTADO='"+strestado+"'";
		}
		
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Fallas> listFallas = new ArrayList<Fallas>();
	    /*
	    System.out.println("strLocalStmt:"+strLocalStmt);
	    */
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(strLocalStmt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		Fallas fallas=new Fallas();
	    		fallas.setNumero(rs.getLong("NUMERO"));
	    		fallas.setEco(rs.getString("ECO"));
	    		fallas.setDescripcion(rs.getString("DESCRIPCION"));
	    		fallas.setKilometraje(rs.getDouble("KILOMETRAJE"));
	    		fallas.setFecha(rs.getTimestamp("FECHAREP"));
	    		listFallas.add(fallas);
}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listFallas;
	}
	
}
