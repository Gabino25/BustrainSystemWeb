package bs.vales.combust.entry.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import bs.eis.ResourceManager;
import bs.vales.combust.entry.dao.DieselDao;
import bs.vales.combust.entry.dto.Diesel;

public class DieselDaoImpl implements DieselDao {

	protected java.sql.Connection userConn; 
	private final static int COLUMN_FECHA        =1;
    private final static int COLUMN_NOTA         =2;
    private final static int COLUMN_UNIDAD       =3;
    private final static int COLUMN_TOTAL        =4;
    private final static int COLUMN_LITROS       =5;
    private final static int COLUMN_KILOMETRAJE  =6; 
    private final static int COLUMN_HORA         =7;
    private final static int COLUMN_FECHAFACTURA =8;
    private final static int COLUMN_GASOLINERA   =9;
    private final static int COLUMN_OPERADOR     =10;
    private final static int COLUMN_COMBUSTIBLE  =11; 
    private final static int COLUMN_RENDIMIENTO  =12; 
    private final static int COLUMN_KMANTERIOR   =13;
    private final static int COLUMN_KMSRECORRIDOS=14;
    private final static int COLUMN_USUARIO      =15;
    private final static int COLUMN_EMPRESA      =16;
    
    private final String SQL_SELECT ="SELECT [FECHA]  " + 
    		",[NOTA]  " + 
    		",[UNIDAD]  " + 
    		",ROUND([TOTAL],2) TOTAL " + 
    		",[LITROS]  " + 
    		",[KILOMETRAJE]  " + 
    		",[HORA]  " + 
    		",[FECHAFACTURA] " + 
    		",[GASOLINERA] " + 
    		",[OPERADOR] " + 
    		",[COMBUSTIBLE] " + 
    		",cast(RENDIMIENTO as decimal(10,4)) [RENDIMIENTO]" + 
    		",[KMANTERIOR] " + 
    		",[KMSRECORRIDOS] " + 
    		",[USUARIO] " + 
    		",[EMPRESA] " + 
    		"from "+this.getTableName();
   
    private final String SQL_INSERT = " INSERT INTO [dbo].[DIESEL] (FECHA ,NOTA ,UNIDAD ,TOTAL,LITROS ,KILOMETRAJE,HORA ,FECHAFACTURA ,GASOLINERA ,OPERADOR ,COMBUSTIBLE,RENDIMIENTO,KMANTERIOR ,KMSRECORRIDOS,USUARIO,EMPRESA)VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    
    public String getTableName()
	{
		return "BUSTRAIN.dbo.DIESEL";
	}
	
    
	@Override
	public List<Diesel> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Diesel> listDieselDto = new ArrayList<Diesel>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " where DATEDIFF(dd,FECHA,GETDATE())=0",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Diesel dieselDto = new Diesel();
	    		
	    		dieselDto.setFecha(rs.getTimestamp(COLUMN_FECHA));           
	    		dieselDto.setNota(rs.getLong(COLUMN_NOTA));       
	    		dieselDto.setUnidad(rs.getString(COLUMN_UNIDAD));        
	    		dieselDto.setTotal(rs.getBigDecimal(COLUMN_TOTAL));          
	    		dieselDto.setLitros(rs.getDouble(COLUMN_LITROS));             
	    		dieselDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE));         
	    		dieselDto.setHora(rs.getBigDecimal(COLUMN_HORA));       
	    		dieselDto.setFechafactura(rs.getTimestamp(COLUMN_FECHAFACTURA));     
	    		dieselDto.setGasolinera(rs.getString(COLUMN_GASOLINERA));    
	    		dieselDto.setOperador(rs.getString(COLUMN_OPERADOR));      
	    		dieselDto.setCombustible(rs.getString(COLUMN_COMBUSTIBLE));   
	    		dieselDto.setRendimiento(rs.getDouble(COLUMN_RENDIMIENTO));         
	    		dieselDto.setKmanterior(rs.getDouble(COLUMN_KMANTERIOR));          
	    		dieselDto.setKmsrecorridos(rs.getDouble(COLUMN_KMSRECORRIDOS));       
	    		dieselDto.setUsuario(rs.getString(COLUMN_USUARIO));       
	    		dieselDto.setEmpresa(rs.getString(COLUMN_EMPRESA)); 
	    		
	    		listDieselDto.add(dieselDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return listDieselDto;
	}


	@Override
	public List<Diesel> filtraPorFecha(int pDay,int pMonth,int pYear) throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    List<Diesel> listDieselDto = new ArrayList<Diesel>();
			    try {
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	System.out.println("Entra filtrar por fecha "+pDay+","+pMonth+","+pYear);
			    	stmt = conn.prepareStatement(SQL_SELECT+ " where DATEDIFF(dd,FECHA,CAST('"+pYear+"-"+pMonth+"-"+pDay+"' AS DATE))=0",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	System.out.println("Se ejecuto");
			    	while(rs.next()) {
			    		Diesel dieselDto = new Diesel();
			    		
			    		dieselDto.setFecha(rs.getTimestamp(COLUMN_FECHA));           
			    		dieselDto.setNota(rs.getLong(COLUMN_NOTA));       
			    		dieselDto.setUnidad(rs.getString(COLUMN_UNIDAD));        
			    		dieselDto.setTotal(rs.getBigDecimal(COLUMN_TOTAL));          
			    		dieselDto.setLitros(rs.getDouble(COLUMN_LITROS));             
			    		dieselDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE));         
			    		dieselDto.setGasolinera(rs.getString(COLUMN_GASOLINERA));    
			    	
			    		listDieselDto.add(dieselDto);
			    	}
			    }finally {
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }
			    
				return listDieselDto;
	}


	@Override
	public List<Diesel> filtraPorFechas(int pDesdeDay
			                          , int pDesdeMonth
			                          , int pDesdeYear
			                          , int pHastaDay
			                          , int pHastaMonth
			                          , int pHastaYear
			                          , String pStrUnidad
			                          , String pStrGasolinera
			                          ) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Diesel> listDieselDto = new ArrayList<Diesel>();
	    
	    String strSqlStatement = SQL_SELECT+ " where fecha between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE)";
	    if(null!=pStrUnidad&&!"".equals(pStrUnidad)) {
	    	strSqlStatement = strSqlStatement+" AND UNIDAD='"+pStrUnidad+"'";
	    }
	    if(null!=pStrGasolinera&&!"".equals(pStrGasolinera)) {
	    	strSqlStatement = strSqlStatement+" AND GASOLINERA='"+pStrGasolinera+"'";
	    }
	    
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(strSqlStatement,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		Diesel dieselDto = new Diesel();
	    		
	    		dieselDto.setFecha(rs.getTimestamp(COLUMN_FECHA));           
	    		dieselDto.setNota(rs.getLong(COLUMN_NOTA));       
	    		dieselDto.setUnidad(rs.getString(COLUMN_UNIDAD));        
	    		dieselDto.setTotal(rs.getBigDecimal(COLUMN_TOTAL));          
	    		dieselDto.setLitros(rs.getDouble(COLUMN_LITROS));             
	    		dieselDto.setKilometraje(rs.getDouble(COLUMN_KILOMETRAJE));         
	    		dieselDto.setHora(rs.getBigDecimal(COLUMN_HORA));       
	    		dieselDto.setFechafactura(rs.getTimestamp(COLUMN_FECHAFACTURA));     
	    		dieselDto.setGasolinera(rs.getString(COLUMN_GASOLINERA));    
	    		dieselDto.setOperador(rs.getString(COLUMN_OPERADOR));      
	    		dieselDto.setCombustible(rs.getString(COLUMN_COMBUSTIBLE));   
	    		if(5867564==rs.getLong(COLUMN_NOTA)) {
	    			System.out.println(rs.getDouble(COLUMN_RENDIMIENTO));
	    		}
	    		dieselDto.setRendimiento(rs.getDouble(COLUMN_RENDIMIENTO));         
	    		dieselDto.setKmanterior(rs.getDouble(COLUMN_KMANTERIOR));          
	    		dieselDto.setKmsrecorridos(rs.getDouble(COLUMN_KMSRECORRIDOS));       
	    		dieselDto.setUsuario(rs.getString(COLUMN_USUARIO));       
	    		dieselDto.setEmpresa(rs.getString(COLUMN_EMPRESA)); 
	    		
	    		listDieselDto.add(dieselDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
		return listDieselDto;
	}


	@Override
	public String insert(Diesel dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		
		try {
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareCall( SQL_INSERT ,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		   
			stmt.setString(COLUMN_FECHA, dto.getFechaC()); /* FECHA          datetime] NULL,*/                
			stmt.setLong(COLUMN_NOTA, dto.getNota());/* NOTA           bigint] NOT NULL, */             
			stmt.setString(COLUMN_UNIDAD, dto.getUnidad());/* UNIDAD         varchar](50) NULL,*/             
			stmt.setBigDecimal(COLUMN_TOTAL, dto.getTotal());/* TOTAL          smallmoney] NULL,*/              
			stmt.setDouble(COLUMN_LITROS,dto.getLitros());/* LITROS         float] NULL,*/                   
			stmt.setDouble(COLUMN_KILOMETRAJE, dto.getKilometraje());/* KILOMETRAJE    float] NULL, */                  
			stmt.setBigDecimal(COLUMN_HORA, dto.getHora());/* HORA           numeric](18, 0) NULL, */         
			stmt.setTimestamp(COLUMN_FECHAFACTURA,dto.getFechafactura());/* FECHAFACTURA   datetime] NULL, */               
			stmt.setString(COLUMN_GASOLINERA,dto.getGasolinera());/* GASOLINERA     varchar](50) NULL,*/             
			stmt.setString(COLUMN_OPERADOR,dto.getOperador());/* OPERADOR       varchar](50) NULL,*/             
			stmt.setString(COLUMN_COMBUSTIBLE,dto.getCombustible());/* COMBUSTIBLE    varchar](50) NULL, */            
			stmt.setDouble(COLUMN_RENDIMIENTO, dto.getRendimiento());/* RENDIMIENTO    float] NULL, */                  
			stmt.setDouble(COLUMN_KMANTERIOR, dto.getKmanterior());/* KMANTERIOR     float] NULL,*/                   
			stmt.setDouble(COLUMN_KMSRECORRIDOS, dto.getKmsrecorridos());/* KMSRECORRIDOS  float] NULL, */                  
			stmt.setString(COLUMN_USUARIO,dto.getUsuario());/* USUARIO        varchar](50) NULL, */            
			stmt.setString(COLUMN_EMPRESA,dto.getEmpresa());/* EMPRESA        varchar](50) NULL, */   
			
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
			return "FalloTransacaccionMetodo insert clase DieselDaoImpl"; 
		}else {
			return null;
		}
	}


	@Override
	public String findNextNota() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextNota = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([NOTA])+1 NEXT_NOTA from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		nextNota = rs.getInt(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    if(0==nextNota) {
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumero clase DieselDaoImpl";
	    }else {
	       return ""+nextNota;
	    }
	}


	@Override
	public String validaFolio(long pNota) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strNota = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select nota from "+this.getTableName()+" where nota="+pNota,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strNota = rs.getString(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    
		return strNota;
	}


	@Override
	public double findKmAnterior(String strpUnidadValue) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    double doKmAnterior = 0d; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select  max(KILOMETRAJE) KILOMETRAJE from "+this.getTableName()+" where unidad='"+strpUnidadValue+"'",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		doKmAnterior = rs.getDouble(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    
		return doKmAnterior;
	}


	@Override
	public List<Diesel> findRendimientosCalculados() throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    List<Diesel> listDieselDto = new ArrayList<Diesel>();
			    String strSQLStatement = " SELECT UNIDAD,KMANTERIOR,KILOMETRAJE,KMSRECORRIDOS,LITROS,RENDIMIENTO " + 
							    		 " FROM DBO.DIESEL " + 
							    		 " WHERE UNIDAD IN ( SELECT ECO FROM [DBO].[AUTOBUS] " + 
							    		 " WHERE ESTADO='ACTIVO' " + 
							    		 " AND TIPO <> 'MAQUINARIA' " + 
							    		 " AND TIPO <> 'ADMON') " + 
							    		 " AND FECHA BETWEEN DATEDIFF(DAY,60,GETDATE()) AND GETDATE() " + 
							    		 " ORDER BY UNIDAD ASC " + 
							    		 " , FECHA ASC " + 
							    		 " , KILOMETRAJE ASC "; 
			    try {
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	stmt = conn.prepareStatement(strSQLStatement,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	while(rs.next()) {
			    		Diesel dieselDto = new Diesel();
			    		
			    		dieselDto.setUnidad(rs.getString("UNIDAD")); 	
			    		dieselDto.setKmanterior(rs.getDouble("KMANTERIOR"));
			    		dieselDto.setKilometraje(rs.getDouble("KILOMETRAJE"));
			    		dieselDto.setKmsrecorridos(rs.getDouble("KMSRECORRIDOS"));
			    		dieselDto.setLitros(rs.getDouble("LITROS"));
			    		dieselDto.setRendimiento(rs.getDouble("RENDIMIENTO"));
			    		
			    		listDieselDto.add(dieselDto);
			    	}
			    }finally {
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }
			    
				return listDieselDto;
	}


	@Override
	public String calculaRendimientos() throws SQLException {
		Connection conn = null; 
	    CallableStatement stmt = null;
	    ResultSet rs = null; 
	    boolean bEjecuta = false;
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareCall("{call [dbo].[ejecutaRendimientos]}");
	    	bEjecuta = stmt.execute();
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	      return null; 	
	    
	}


	@Override
	public String cargasAexcel(int pDesdeDay
				           , int pDesdeMonth
				           , int pDesdeYear
				           , int pHastaDay
				           , int pHastaMonth
				           , int pHastaYear
				           ) throws SQLServerException,SQLException,Exception {
		Connection conn = null; 
	    CallableStatement stmt = null;
	    ResultSet rs = null; 
	    String retval = "";
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareCall("{call [dbo].[CargasCombustPorFecha] (?,?) }");
	    	java.util.Date utilDateDesde = new SimpleDateFormat("dd/MM/yyyy").parse(pDesdeDay+"/"+pDesdeMonth+"/"+pDesdeYear);
	    	java.sql.Timestamp sqlDateDesde = new Timestamp(utilDateDesde.getTime());
	    	java.util.Date utilDateHasta = new SimpleDateFormat("dd/MM/yyyy").parse(pHastaDay+"/"+pHastaMonth+"/"+pHastaYear);
	    	java.sql.Timestamp sqlDateHasta = new Timestamp(utilDateHasta.getTime());
	    	
	    	stmt.setTimestamp(1,sqlDateDesde);
	    	stmt.setTimestamp(2,sqlDateHasta);
	    	
	    	rs = stmt.executeQuery();
	    	while(rs.next()) {
	    		System.out.println(rs.getString(1));
	    		retval = retval+rs.getString(1);
	    	}
	    }catch (SQLServerException sqlse) {
	    	throw new Exception("Excepcion 1 "+sqlse.toString()); 
	    }
	    catch(SQLException sqle){
	    	throw sqle; 
	    }
	    finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	   return retval;
	} /** END cargasAexcel **/
	
	@Override
	public String cargasAexcelV2(int pDesdeDay
				           , int pDesdeMonth
				           , int pDesdeYear
				           , int pHastaDay
				           , int pHastaMonth
				           , int pHastaYear
				           ) throws SQLServerException,SQLException,Exception {
		Connection conn = null; 
	    CallableStatement stmt = null;
	    ResultSet rs = null; 
	    String retval = "";
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareCall("{call [dbo].[CargasCombustPorFechaV2] (?,?) }");
	    	java.util.Date utilDateDesde = new SimpleDateFormat("dd/MM/yyyy").parse(pDesdeDay+"/"+pDesdeMonth+"/"+pDesdeYear);
	    	java.sql.Timestamp sqlDateDesde = new Timestamp(utilDateDesde.getTime());
	    	java.util.Date utilDateHasta = new SimpleDateFormat("dd/MM/yyyy").parse(pHastaDay+"/"+pHastaMonth+"/"+pHastaYear);
	    	java.sql.Timestamp sqlDateHasta = new Timestamp(utilDateHasta.getTime());
	    	
	    	stmt.setTimestamp(1,sqlDateDesde);
	    	stmt.setTimestamp(2,sqlDateHasta);
	    	
	    	rs = stmt.executeQuery();
	    	while(rs.next()) {
	    		retval = retval+rs.getString(1);
	    	}
	    }catch (SQLServerException sqlse) {
	    	throw new Exception("Excepcion 1 "+sqlse.toString()); 
	    }
	    catch(SQLException sqle){
	    	throw sqle; 
	    }
	    finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	   return retval;
	} /** END cargasAexcelV2 **/
	
	@Override
	public String cargasAexcelV3(int pDesdeDay
				           , int pDesdeMonth
				           , int pDesdeYear
				           , int pHastaDay
				           , int pHastaMonth
				           , int pHastaYear
				           ) throws SQLServerException,SQLException,Exception {
		Connection conn = null; 
	    CallableStatement stmt = null;
	    ResultSet rs = null; 
	    String retval = "";
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareCall("{call [dbo].[CargasCombustPorFechaV3] (?,?) }");
	    	java.util.Date utilDateDesde = new SimpleDateFormat("dd/MM/yyyy").parse(pDesdeDay+"/"+pDesdeMonth+"/"+pDesdeYear);
	    	java.sql.Timestamp sqlDateDesde = new Timestamp(utilDateDesde.getTime());
	    	java.util.Date utilDateHasta = new SimpleDateFormat("dd/MM/yyyy").parse(pHastaDay+"/"+pHastaMonth+"/"+pHastaYear);
	    	java.sql.Timestamp sqlDateHasta = new Timestamp(utilDateHasta.getTime());
	    	
	    	stmt.setTimestamp(1,sqlDateDesde);
	    	stmt.setTimestamp(2,sqlDateHasta);
	    	
	    	rs = stmt.executeQuery();
	    	while(rs.next()) {
	    		retval = retval+rs.getString(1);
	    	}
	    }catch (SQLServerException sqlse) {
	    	throw new Exception("Excepcion 1 "+sqlse.toString()); 
	    }
	    catch(SQLException sqle){
	    	throw sqle; 
	    }
	    finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	   return retval;
	} /** END cargasAexcelV3 
	 * @throws ParseException **/


	@Override
	public String cargasAexcelComParVehicular(int pDesdeDay
			                                , int pDesdeMonth
			                                , int pDesdeYear
			                                , int pHastaDay
			                                , int pHastaMonth
			                                , int pHastaYear) throws SQLException, ParseException,Exception {
		Connection conn = null; 
	    CallableStatement stmt = null;
	    ResultSet rs = null; 
	    String retval = "";
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareCall("{call [dbo].[combustParqueVehicular] (?,?) }");
	    	java.util.Date utilDateDesde = new SimpleDateFormat("dd/MM/yyyy").parse(pDesdeDay+"/"+pDesdeMonth+"/"+pDesdeYear);
	    	java.sql.Timestamp sqlDateDesde = new Timestamp(utilDateDesde.getTime());
	    	java.util.Date utilDateHasta = new SimpleDateFormat("dd/MM/yyyy").parse(pHastaDay+"/"+pHastaMonth+"/"+pHastaYear);
	    	java.sql.Timestamp sqlDateHasta = new Timestamp(utilDateHasta.getTime());
	    	
	    	stmt.setTimestamp(1,sqlDateDesde);
	    	stmt.setTimestamp(2,sqlDateHasta);
	    	
	    	rs = stmt.executeQuery();
	    	while(rs.next()) {
	    		retval = retval+rs.getString(1);
	    	}
	    }catch (SQLServerException sqlse) {
	    	throw new Exception("Excepcion SQLServerException "+sqlse.toString()); 
	    }
	    catch(SQLException sqle){
	    	throw sqle; 
	    }
	    finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	   return retval;
	}


	@Override
	public String deleteByFolio(int pFolio) throws SQLException {
		// TODO Auto-generated method stub
//		return null;

		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [NOTA] ="+pFolio; 
	
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
			return "FalloTransaccionMetodo delete clase DieselDaoImpl"; 
		}else {
			return null;
		}
	
	}
	
}
