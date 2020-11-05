package bs.llantas.entry.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bs.eis.ResourceManager;
import bs.llantas.entry.dao.MovimientosLlantasDao;
import bs.llantas.entry.dto.MovimientosLlantas;

public class MovimientosLlantasDaoImpl implements MovimientosLlantasDao {

	protected java.sql.Connection userConn; 
	
	private static final int COLUMN_FOLIO=1; /**[int] NULL, **/
	private static final int COLUMN_NOMBRE=2; /**[varchar](50) NULL, **/
	private static final int COLUMN_OPERADOR=3; /**[varchar](50) NULL, **/
	private static final int COLUMN_FECHA=4; /**[datetime] NULL, **/
	private static final int COLUMN_POSICION=5; /**[varchar](50) NULL, **/
	private static final int COLUMN_UNIDAD=6; /**[varchar](50) NULL, **/
	private static final int COLUMN_PRESION=7; /**[varchar](50) NULL, **/
	private static final int COLUMN_OBSERVACIONES=8; /**[varchar](max) NULL, **/
	private static final int COLUMN_TIPO_MOV=9; /**[nvarchar](50) NULL, **/
	private static final int COLUMN_KILOMETRAJE=10; /**[nchar](10) NULL, **/
	private static final int COLUMN_USUARIO=11; /**[nvarchar](50) NULL, **/
	private static final int COLUMN_FECHACAPTURA=12; /**[datetime] NULL, **/
	private static final int COLUMN_PRESIONANTERIOR=13; /**[nvarchar](50) NULL, **/
	private static final int COLUMN_COSTO=14; /**[real] NULL, **/
	private static final int COLUMN_NOTA=15; /**[text] NULL, **/
	private static final int COLUMN_KMRECORRIDO=16; /**[real] NULL, **/
	private static final int COLUMN_PROF=17; /**[real] NULL **/
	
	 private final String SQL_SELECT_TOP1000 =" SELECT TOP (1000) [FOLIO]\r\n" + 
		 	  " ,[NOMBRE]\r\n" + 
		 	  " ,[OPERADOR]\r\n" + 
		 	  " ,[FECHA]\r\n" + 
		 	  " ,[POSICION]\r\n" + 
		 	  " ,[UNIDAD]\r\n" + 
		 	  " ,[PRESION]\r\n" + 
		 	  " ,[OBSERVACIONES]\r\n" + 
		 	  " ,[TIPO_MOV]\r\n" + 
		 	  " ,[KILOMETRAJE]\r\n" + 
		 	  " ,[USUARIO]\r\n" + 
		      " ,[FECHACAPTURA]\r\n" + 
		 	  " ,[PRESIONANTERIOR]\r\n" + 
		 	  " ,[COSTO]\r\n" + 
		 	  " ,[NOTA]\r\n" + 
		 	  " ,[KMRECORRIDO]\r\n" + 
		 	  " ,[PROF] " + 
              "from "+this.getTableName();
	 
	 private final String SQL_SELECT =" SELECT [FOLIO]\r\n" + 
								 	  " ,[NOMBRE]\r\n" + 
								 	  " ,[OPERADOR]\r\n" + 
								 	  " ,[FECHA]\r\n" + 
								 	  " ,[POSICION]\r\n" + 
								 	  " ,[UNIDAD]\r\n" + 
								 	  " ,[PRESION]\r\n" + 
								 	  " ,[OBSERVACIONES]\r\n" + 
								 	  " ,[TIPO_MOV]\r\n" + 
								 	  " ,[KILOMETRAJE]\r\n" + 
								 	  " ,[USUARIO]\r\n" + 
								      " ,[FECHACAPTURA]\r\n" + 
								 	  " ,[PRESIONANTERIOR]\r\n" + 
								 	  " ,[COSTO]\r\n" + 
								 	  " ,[NOTA]\r\n" + 
								 	  " ,[KMRECORRIDO]\r\n" + 
								 	  " ,[PROF] " + 
	    		"from "+this.getTableName();
   private final String SQL_INSERT = " INSERT INTO [dbo].[MOVIMIENTOS_LLANTAS] ( FOLIO ,NOMBRE ,OPERADOR ,FECHA ,POSICION ,UNIDAD ,PRESION ,OBSERVACIONES ,TIPO_MOV ,KILOMETRAJE ,USUARIO ,FECHACAPTURA ,PRESIONANTERIOR ,COSTO ,NOTA ,KMRECORRIDO ,PROF) VALUES (  ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";	
	 
	    public String getTableName()
		{
			return "BUSTRAIN.dbo.MOVIMIENTOS_LLANTAS";
		}
	
	 private final  String REP_LLANTAS  = "REPARACIONES DE LLANTAS"; 
	 private final  String ASSIGN_UNIDAD = "ASIGNACION A UNIDAD";
	 private final  String BAJA_UNIDAD = "BAJA DE UNIDAD";
	 private final  String REV_PROF = "REVISION DE PROFUNDIDAD";     
 
	    
	@Override
	public List<MovimientosLlantas> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<MovimientosLlantas> listMovimientosLlantas = new ArrayList<MovimientosLlantas>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+" where DATEDIFF(dd,FECHA,GETDATE())=0",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		
	    		MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    		
	    		listMovimientosLlantas.add(movimientosLlantasDto);
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listMovimientosLlantas;
	}

	@Override
	public List<MovimientosLlantas> findAllRepLlantas() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<MovimientosLlantas> listMovimientosLlantas = new ArrayList<MovimientosLlantas>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT_TOP1000+" where tipo_mov='"+REP_LLANTAS+"' order by folio desc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		
	    		MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    		
	    		listMovimientosLlantas.add(movimientosLlantasDto);
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listMovimientosLlantas;
	}

	@Override
	public List<MovimientosLlantas> findAllAssignUnidad() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<MovimientosLlantas> listMovimientosLlantas = new ArrayList<MovimientosLlantas>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT_TOP1000+" where tipo_mov='"+ASSIGN_UNIDAD+"' order by folio desc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		
	    		MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    		
	    		listMovimientosLlantas.add(movimientosLlantasDto);
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listMovimientosLlantas;
	}

	@Override
	public List<MovimientosLlantas> findAllBajaUnidad() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<MovimientosLlantas> listMovimientosLlantas = new ArrayList<MovimientosLlantas>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT_TOP1000+" where tipo_mov='"+BAJA_UNIDAD+"' order by folio desc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		
	    		MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    		
	    		listMovimientosLlantas.add(movimientosLlantasDto);
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listMovimientosLlantas;
	}

	@Override
	public List<MovimientosLlantas> findAllRevDeProf() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<MovimientosLlantas> listMovimientosLlantas = new ArrayList<MovimientosLlantas>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT_TOP1000+" where tipo_mov='"+REV_PROF+"' order by folio desc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		
	    		MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    		
	    		listMovimientosLlantas.add(movimientosLlantasDto);
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listMovimientosLlantas;
	}

	@Override
	public List<MovimientosLlantas> analizarLlantas(int pDesdeDay
			                                      , int pDesdeMonth
			                                      , int pDesdeYear
			                                      , int pHastaDay
			                                      , int pHastaMonth
			                                      , int pHastaYear
			                                      , String pUnidades
			                                      , String pTipoMovimiento
			                                      , String pLlantas
			                                      )
			throws SQLException {
		
		String strLocalStmt = SQL_SELECT+" where fecha between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE)";
		if(null!=pUnidades
		   &&!"".equals(pUnidades)
		   &&!"Todas".equals(pUnidades)
		   ) {
			strLocalStmt = strLocalStmt+" and unidad='"+pUnidades+"'"; 
		}
		if(null!=pTipoMovimiento
		   &&!"".equals(pTipoMovimiento)
		   &&!"Todas".equals(pTipoMovimiento)
		   ) {
					strLocalStmt = strLocalStmt+" and tipo_mov='"+pTipoMovimiento+"'"; 
		}
		if(null!=pLlantas
		   &&!"".equals(pLlantas)
		   &&!"Todas".equals(pLlantas)
				   ) {
					strLocalStmt = strLocalStmt+" and nombre='"+pLlantas+"'"; 
		}
		
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<MovimientosLlantas> listMovimientosLlantas = new ArrayList<MovimientosLlantas>();
	    /*
	    System.out.println("strLocalStmt:"+strLocalStmt);
	    */
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(strLocalStmt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	while(rs.next()) {
	    		
	    		MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    		
	    		listMovimientosLlantas.add(movimientosLlantasDto);
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listMovimientosLlantas;
		
	}

	@Override
	public String findNextFolio() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextFolio = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([FOLIO])+1 NEXT_FOLIO from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
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
	    	 return "NoSePudoRecuperaInformacionMetodo findNextFolio clase LlantasDaoImpl";
	    }else {
	       return ""+nextFolio;
	    }
	}

	@Override
	public String insert(MovimientosLlantas dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		    
		    stmt.setInt(COLUMN_FOLIO, dto.getFolio());/* [FOLIO] [int] NULL, */
		    stmt.setString(COLUMN_NOMBRE, dto.getNombre());/* [NOMBRE] [varchar](50) NULL,*/
		    stmt.setString(COLUMN_OPERADOR, dto.getOperador());/* [OPERADOR] [varchar](50) NULL,*/
		    stmt.setTimestamp(COLUMN_FECHA,dto.getFecha());/* [FECHA] [datetime] NULL,*/
		    stmt.setString(COLUMN_POSICION, dto.getPosicion());/* [POSICION] [varchar](50) NULL,*/
		    stmt.setString(COLUMN_UNIDAD, dto.getUnidad());/* [UNIDAD] [varchar](50) NULL,*/
		    stmt.setString(COLUMN_PRESION, dto.getPresion());/* [PRESION] [varchar](50) NULL, */
		    stmt.setString(COLUMN_OBSERVACIONES,dto.getObservaciones());/* [OBSERVACIONES] [varchar](max) NULL,*/
		    stmt.setNString(COLUMN_TIPO_MOV, dto.getTipo_mov());/* [TIPO_MOV] [nvarchar](50) NULL, */
		    stmt.setNString(COLUMN_KILOMETRAJE, dto.getKilometraje());/* [KILOMETRAJE] [nchar](10) NULL, */
		    stmt.setNString(COLUMN_USUARIO, dto.getUsuario());/* [USUARIO] [nvarchar](50) NULL,*/
		    stmt.setTimestamp(COLUMN_FECHACAPTURA, dto.getFechacaptura());/* [FECHACAPTURA] [datetime] NULL, */
		    stmt.setNString(COLUMN_PRESIONANTERIOR, dto.getPresionanterior());/* [PRESIONANTERIOR] [nvarchar](50) NULL,*/
		    if(0f!=dto.getCosto()) {
		    stmt.setFloat(COLUMN_COSTO, dto.getCosto());/* [COSTO] [real] NULL,*/
		    }else {
		    	stmt.setNull(COLUMN_COSTO, java.sql.Types.REAL);	
		    }
		    stmt.setString(COLUMN_NOTA, dto.getNota());/* [NOTA] [text] NULL, */
		    if(0f!=dto.getKmrecorrido()) {
		    stmt.setFloat(COLUMN_KMRECORRIDO, dto.getKmrecorrido());/* [KMRECORRIDO] [real] NULL,*/
		    }else {
		    	stmt.setNull(COLUMN_KMRECORRIDO, java.sql.Types.REAL);	
		    }
		    if(0f!=dto.getProf()) {
		    stmt.setFloat(COLUMN_PROF, dto.getProf());/* [PROF] [real] NULL*/
		    }else {
		    	stmt.setNull(COLUMN_PROF, java.sql.Types.REAL);	
		    }
			
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
			return "FalloTransaccionMetodo insert clase MovimientosLlantasDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String findUnidadPosicion(String strpNombreLlanta) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strUnidadXXBSPosicion = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select UNIDAD+'XXBS'+POSICION from "+this.getTableName()+" where nombre='"+strpNombreLlanta+"' AND TIPO_MOV='ASIGNACION A UNIDAD' ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strUnidadXXBSPosicion = rs.getString(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    return strUnidadXXBSPosicion; 
	    
	}

	@Override
	public MovimientosLlantas findByFolio(int parseInt) throws SQLException {
    
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    MovimientosLlantas movimientosLlantasDto = new MovimientosLlantas();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+" where FOLIO="+parseInt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	
	    	if(rs.next()) {
	    		
	    		movimientosLlantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**[int] null, **/
	    		movimientosLlantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setFecha(rs.getTimestamp(COLUMN_FECHA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**[varchar](50) null, **/
	    		movimientosLlantasDto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES)); /**[varchar](max) null, **/
	    		movimientosLlantasDto.setTipo_mov(rs.getNString(COLUMN_TIPO_MOV)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setKilometraje(rs.getNString(COLUMN_KILOMETRAJE)); /**[nchar](10) null, **/
	    		movimientosLlantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**[datetime] null, **/
	    		movimientosLlantasDto.setPresionanterior(rs.getNString(COLUMN_PRESIONANTERIOR)); /**[nvarchar](50) null, **/
	    		movimientosLlantasDto.setCosto(rs.getFloat(COLUMN_COSTO)); /**[real] null, **/
	    		movimientosLlantasDto.setNota(rs.getString(COLUMN_NOTA)); /**[text] null, **/
	    		movimientosLlantasDto.setKmrecorrido(rs.getFloat(COLUMN_KMRECORRIDO)); /**[real] null, **/
	    		movimientosLlantasDto.setProf(rs.getFloat(COLUMN_PROF)); /**[real] null **/
	    	
	    	}
	    	
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }

	    return movimientosLlantasDto;
		
	}

	@Override
	public String update(MovimientosLlantas movLlantasDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(movLlantasDto.getNombre())&&null!=movLlantasDto.getNombre()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOMBRE] ='"+movLlantasDto.getNombre()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getOperador())&&null!=movLlantasDto.getOperador()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [OPERADOR] ='"+movLlantasDto.getOperador()+"'";
			countFields = countFields +1; 
		}
		if(null!=movLlantasDto.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+movLlantasDto.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getPosicion())&&null!=movLlantasDto.getPosicion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [POSICION] ='"+movLlantasDto.getPosicion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getUnidad())&&null!=movLlantasDto.getUnidad()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [UNIDAD] ='"+movLlantasDto.getUnidad()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getPresion())&&null!=movLlantasDto.getPresion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PRESION] ='"+movLlantasDto.getPresion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getKilometraje())&&null!=movLlantasDto.getKilometraje()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KILOMETRAJE] ='"+movLlantasDto.getKilometraje()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getNota())&&null!=movLlantasDto.getNota()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOTA] ='"+movLlantasDto.getNota()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(movLlantasDto.getObservaciones())&&null!=movLlantasDto.getObservaciones()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [OBSERVACIONES] ='"+movLlantasDto.getObservaciones()+"'";
			countFields = countFields +1; 
		}
		if(0f<movLlantasDto.getProf()) {
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PROF] ="+movLlantasDto.getProf();
		countFields = countFields +1; 
		}
		if(0f<movLlantasDto.getCosto()) {
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COSTO] ="+movLlantasDto.getCosto();
		countFields = countFields +1; 
		}
        stmtUpdate =stmtUpdate+" WHERE [FOLIO] ="+movLlantasDto.getFolio(); 
		
		
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
			return "FalloTransaccionMetodo update clase MovimientosLlantasDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String deleteByFolio(int pFolio) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [FOLIO] ="+pFolio; 
	
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
			return "FalloTransaccionMetodo delete clase MovimientosLlantasDaoImpl"; 
		}else {
			return null;
		}
		
	}
	
	

}
