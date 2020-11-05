package bs.catalogo.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.CatTrabajadoresDao;
import bs.catalogo.dto.CatTrabajadores;
import bs.eis.ResourceManager;

public class CatTrabajadoresDaoImpl extends AbstractDAO implements CatTrabajadoresDao {

	protected java.sql.Connection userConn;

	private static final int COLUMN_NUMERO=1; /** [numeric](18, 0) NULL, **/
	private static final int COLUMN_NOMBRE=2; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_DIRECCION=3; /** [text] NULL, **/
	private static final int COLUMN_PUESTO=4; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_TELEFONO=5; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_SEGURO=6; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_FECHA=7; /** [smalldatetime] NULL, **/
	private static final int COLUMN_FOTO=8; /** [image] NULL, **/
	private static final int COLUMN_ESTADO=9; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_LICENCIA=10; /** [smalldatetime] NULL, **/
	private static final int COLUMN_NOTA=11; /** [text] NULL **/
	private static final int COLUMN_FECHAINGRESO=12;       /* datetime] NULL,*/                                                            
	private static final int COLUMN_AREA=13;       /* varchar](50) NULL, */                                                              
	private static final int COLUMN_RFC =14;       /*varchar](13) NULL,*/                                                              
	private static final int COLUMN_CURP=15;       /* varchar](18) NULL, */                                                              
	private static final int COLUMN_ESTADOCIVIL =16;       /*varchar](20) NULL,*/                                                          
	private static final int COLUMN_ESTATURA=17;       /* float] NULL, */                                                              
	private static final int COLUMN_PESO=18;       /* float] NULL, */                                                              
	private static final int COLUMN_FECHABAJA =19;       /*datetime] NULL, */                                                              
	private static final int COLUMN_MOTIVO=20;       /* varchar](50) NULL, */ 
	private static final int COLUMN_NTRABAJADOR=21;
	private static final int COLUMN_FECHANACIMIENTO=22; //
	private static final int COLUMN_CAMISA=23;		//	
	private static final int COLUMN_PLAYERA=24;		//
	private static final int COLUMN_PANTALON=25;	//
	private static final int COLUMN_ZAPATOS=26;		//
	private static final int COLUMN_REINGRESO=27;	//
	private static final int COLUMN_LICENCIAF=28;
	private static final int COLUMN_GAFETE=29;
	private static final int COLUMN_FECHAGAFETE=30;
	private static final int COLUMN_TRABAJADOR=1;	 //
	private static final int COLUMN_CCAMISA=2;		//
	private static final int COLUMN_CPLAYERA=3;	//
	private static final int COLUMN_CPANTALON=4;	//
	private static final int COLUMN_CZAPATO=5;		//
	private static final int COLUMN_FECHAA=6;		//


	
	
	protected final String SQL_SELECT = " SELECT [NUMERO] " + 
			" ,[NOMBRE] " + 
			" ,[DIRECCION] " + 
			" ,[PUESTO] " + 
			" ,[TELEFONO] " + 
			" ,[SEGURO] " + 
			" ,[FECHA] " + 
		/*	" ,[FOTO]\r\n" +  */
			" ,[ESTADO] " + 
			" ,[LICENCIA] " + 
			" ,[NOTA] "+
			" ,[FECHAINGRESO] "+
			" ,[AREA] "+
			" ,[RFC] "+
			" ,[CURP] "+
			" ,[ESTADOCIVIL] "+
			" ,[ESTATURA] "+
			" ,[PESO] "+
			" ,[FECHABAJA] "+
			" ,[MOTIVO] "+
			" ,[NEMPLEADO] "+
			" ,[FECHANACIMIENTO] "+
			" ,[TCAMISA] "+
			" ,[TPLAYERA] "+
			" ,[TPANTALON] "+
			" ,[TZAPATOS] "+
			" ,[REINGRESO] "+
			" ,[LICENCIAF] "+
			" ,[GAFETE] "+
			" ,[FECHAGAFETE] "+
			" from " + getTableName();
	
	protected final String SQL_SELECTV2 = " SELECT [NUMERO] " + 
			" ,[NOMBRE] " + 
			" ,[DIRECCION] " + 
			" ,[PUESTO] " + 
			" ,[TELEFONO] " + 
			" ,[SEGURO] " + 
			" ,[FECHA] " + 
			" ,[FOTO] " +  
			" ,[ESTADO] " + 
			" ,[LICENCIA] " + 
			" ,[NOTA] "+
			" ,[FECHAINGRESO] "+
			" ,[AREA] "+
			" ,[RFC] "+
			" ,[CURP] "+
			" ,[ESTADOCIVIL] "+
			" ,[ESTATURA] "+
			" ,[PESO] "+
			" ,[FECHABAJA] "+
			" ,[MOTIVO] "+
			" ,[NEMPLEADO] "+
			" ,[FECHANACIMIENTO] "+
			" ,[TCAMISA] "+
			" ,[TPLAYERA] "+
			" ,[TPANTALON] "+
			" ,[TZAPATOS] "+
			" ,[REINGRESO] "+
			" ,[LICENCIAF] "+
			" ,[GAFETE] "+
			" ,[FECHAGAFETE] "+
			" from " + getTableName();
	
	String a ="BUSTRAIN.dbo.ASIGNACIONUNIFORMES";
	
	protected final String SQL_SELECTV3 = " SELECT [NUMERO]" + 
			" , [NOMBRE] " +
			" , [CCAMISA] " +
			" , [CPLAYERA] " +
			" , [CPANTALON] " +
			" , [CZAPATO] " +
			" , [FECHA] " +
			" FROM " +  a + " order by numero ";
	
     protected final String SQL_INSERT = " insert into "+this.getTableName()+" (numero,nombre,direccion,puesto,telefono,seguro,fecha,foto,estado,licencia,nota,fechaingreso,area,rfc,curp,estadocivil,estatura,peso,fechabaja,motivo,nempleado,fechanacimiento,tcamisa,tplayera,tpantalon,tzapatos,reingreso,licenciaf,gafete,fechagafete) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 

  //---------------------SQL_INSERT2 ES USADO EN LA LINEA 650----------------------------------------------------------------
     protected final String SQL_INSERT2 = " insert into "+this.getTableName2()+" (nombre,ccamisa,cplayera,cpantalon,czapato,fecha) values (?,?,?,?,?,?)"; 


	public String getTableName()
	{
		return "BUSTRAIN.dbo.TRABAJADOR";
	}
//----------SE DECLARA EL NOMBRE DE LA TABLA-----------------------
	public String getTableName2()
	{
		return "BUSTRAIN.dbo.ASIGNACIONUNIFORMES";
	}
 
	@Override
	
	public String insert(CatTrabajadores dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;  
		try {
		conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			
		stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
		stmt.setInt( COLUMN_NUMERO, dto.getNumero() );
		stmt.setString( COLUMN_NOMBRE, dto.getNombre() );
		stmt.setString( COLUMN_SEGURO, dto.getSeguro() );
		stmt.setString( COLUMN_PUESTO, dto.getPuesto() );
		stmt.setString( COLUMN_DIRECCION, dto.getDireccion() );
		stmt.setString( COLUMN_TELEFONO, dto.getTelefono() );
		stmt.setTimestamp(COLUMN_FECHA, dto.getFecha());
		stmt.setString(COLUMN_LICENCIA, dto.getLic());
		stmt.setString( COLUMN_ESTADO, dto.getEstado() );
		stmt.setString( COLUMN_NOTA, dto.getNota() );
		super.setBlobColumn(stmt, COLUMN_FOTO, dto.getFoto() );
		stmt.setTimestamp(COLUMN_FECHAINGRESO, dto.getFechaingreso());
		stmt.setString(COLUMN_AREA, dto.getArea());
		stmt.setString(COLUMN_RFC, dto.getRfc());
		stmt.setString(COLUMN_CURP, dto.getCurp());
		stmt.setString(COLUMN_ESTADOCIVIL, dto.getEstadocivil());
		stmt.setDouble(COLUMN_ESTATURA, dto.getEstatura());/** [ESTATURA] [float] NULL,           */
		stmt.setDouble(COLUMN_PESO, dto.getPeso());/** [PESO] [float] NULL,               */
		stmt.setTimestamp(COLUMN_FECHABAJA, dto.getFechabaja());/** [FECHABAJA] [datetime] NULL,       */
		stmt.setString(COLUMN_MOTIVO, dto.getMotivo());/** [MOTIVO] [varchar](50) NULL,       */
		stmt.setString(COLUMN_NTRABAJADOR, dto.getNEmpleado());            /**int*/
		stmt.setString(COLUMN_FECHANACIMIENTO, dto.getFechaNacimiento());  /**date*/
		stmt.setString(COLUMN_CAMISA, dto.getTcamisa());					/**varchar*/
		stmt.setString(COLUMN_PLAYERA, dto.getTplayera());					/**varchar*/
		stmt.setString(COLUMN_PANTALON, dto.getTpantalon());				/**varchar*/
		stmt.setString(COLUMN_ZAPATOS, dto.getTzapatos());					/**varchar*/
		stmt.setString(COLUMN_REINGRESO, dto.getReingreso());				/**varchar*/
		stmt.setString(COLUMN_LICENCIAF, dto.getLicenciaF());				/**varchar*/
		stmt.setString(COLUMN_GAFETE, dto.getGafete());
		stmt.setString(COLUMN_FECHAGAFETE, dto.getFechaGafete());
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
			 return "FalloTransacaccionMetodo insert clase CatTrabajadoresDaoImpl"; 
		}else {
			return null; 
		}
		
	}
    
    
	
	@Override
	public List<CatTrabajadores> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatTrabajadores> listCatTrabajadoresDto = new ArrayList<CatTrabajadores>();
	    try {
	    	
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatTrabajadores catTrabajadores = new CatTrabajadores();
	    		
	    		catTrabajadores.setNumero(rs.getInt("NUMERO"));
	    		catTrabajadores.setNombre(rs.getNString("NOMBRE"));
	    		catTrabajadores.setDireccion(rs.getString("DIRECCION"));
	    		catTrabajadores.setPuesto(rs.getNString("PUESTO"));
	    		catTrabajadores.setTelefono(rs.getNString("TELEFONO"));
	    		catTrabajadores.setSeguro(rs.getNString("SEGURO"));
	    		catTrabajadores.setFecha(rs.getTimestamp("FECHA"));
	    		catTrabajadores.setEstado(rs.getNString("ESTADO"));
	    		catTrabajadores.setLicencia(rs.getTimestamp("LICENCIA"));
	    		catTrabajadores.setNota(rs.getString("NOTA"));
	    		catTrabajadores.setFechaingreso(rs.getTimestamp("FECHAINGRESO"));
	    		catTrabajadores.setArea(rs.getString("AREA"));
	    		catTrabajadores.setFechabaja(rs.getTimestamp("FECHABAJA"));
	    		catTrabajadores.setMotivo(rs.getString("MOTIVO"));
	    		catTrabajadores.setNEmpleado(rs.getString("NEMPLEADO"));
	    		catTrabajadores.setFechaNacimiento(rs.getString("FECHANACIMIENTO"));
	    		catTrabajadores.setTcamisa(rs.getString("TCAMISA"));
	    		catTrabajadores.setTplayera(rs.getString("TPLAYERA"));
	    		catTrabajadores.setTpantalon(rs.getString("TPANTALON"));
	    		catTrabajadores.setTzapatos(rs.getString("TZAPATOS"));
	    		catTrabajadores.setReingreso(rs.getString("REINGRESO"));
	    		catTrabajadores.setLicenciaF(rs.getString("LICENCIAF"));
	    		catTrabajadores.setGafete(rs.getString("GAFETE"));
	    		catTrabajadores.setFechaGafete(rs.getString("FECHAGAFETE"));
	    		listCatTrabajadoresDto.add(catTrabajadores);
	    		
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    
		return listCatTrabajadoresDto;
	}
	
	public List<CatTrabajadores> findLicencTrabaj() throws SQLException{
		// TODO Auto-generated method stub-----------------------------------------------------------------------
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    List<CatTrabajadores> listCatTrabajadoresDto = new ArrayList<CatTrabajadores>();
			    try {
			    	
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	stmt = conn.prepareStatement("select nombre, puesto, licencia, numero from "+getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	while(rs.next()) {
			    		CatTrabajadores catTrabajadores = new CatTrabajadores();

			    		catTrabajadores.setNombre(rs.getNString("NOMBRE"));
			    		catTrabajadores.setPuesto(rs.getNString("PUESTO"));
			    		catTrabajadores.setLicencia(rs.getTimestamp("LICENCIA"));
			    		catTrabajadores.setNumero(rs.getInt("NUMERO"));
			    		
			    		listCatTrabajadoresDto.add(catTrabajadores);
			    	}
			    }finally {
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }	
			    
				return listCatTrabajadoresDto;
	}


	@Override
	public List<CatTrabajadores> findTrabajadoresList() throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    List<CatTrabajadores> listCatTrabajadoresDto = new ArrayList<CatTrabajadores>();
			    try {
			    	
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	stmt = conn.prepareStatement("SELECT numero, nombre FROM " + getTableName()+" order by nombre asc " ,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	while(rs.next()) {
			    		CatTrabajadores catTrabajadores = new CatTrabajadores();
			    		
			    		catTrabajadores.setNumero(rs.getInt("numero"));
			    		catTrabajadores.setNombre(rs.getString("nombre"));
			    		
			    		listCatTrabajadoresDto.add(catTrabajadores);
			    	}
			    }finally {
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }	
			    
				return listCatTrabajadoresDto;
	}

	@Override
	public CatTrabajadores findByNumero(BigDecimal pNumero) throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    CatTrabajadores CatTrabajadoresDto = new CatTrabajadores();
			    try {
			    	
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    
			    	stmt = conn.prepareStatement(SQL_SELECTV2+" where numero="+pNumero,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	if(rs.next()) {
			    		
			    		CatTrabajadoresDto.setNumero(rs.getInt("NUMERO"));
			    		CatTrabajadoresDto.setNombre(rs.getNString("NOMBRE"));
			    		CatTrabajadoresDto.setDireccion(rs.getString("DIRECCION"));
			    		CatTrabajadoresDto.setPuesto(rs.getNString("PUESTO"));
			    		CatTrabajadoresDto.setTelefono(rs.getNString("TELEFONO"));
			    		CatTrabajadoresDto.setSeguro(rs.getNString("SEGURO"));
			    		CatTrabajadoresDto.setFecha(rs.getTimestamp("FECHA"));
			    		CatTrabajadoresDto.setEstado(rs.getNString("ESTADO"));
			    		CatTrabajadoresDto.setLicencia(rs.getTimestamp("LICENCIA"));
			    		CatTrabajadoresDto.setNota(rs.getString("NOTA"));
			    		CatTrabajadoresDto.setFoto(rs.getBytes("FOTO"));
			    		CatTrabajadoresDto.setFechabaja(rs.getTimestamp("FECHABAJA"));
			    		CatTrabajadoresDto.setMotivo(rs.getString("MOTIVO"));
			    		CatTrabajadoresDto.setFechaingreso(rs.getTimestamp("FECHAINGRESO"));
			    		CatTrabajadoresDto.setArea(rs.getString("AREA"));
			    		CatTrabajadoresDto.setRfc(rs.getString("RFC"));
			    		CatTrabajadoresDto.setCurp(rs.getString("CURP"));
			    		CatTrabajadoresDto.setEstadocivil(rs.getString("ESTADOCIVIL"));
			    		CatTrabajadoresDto.setEstatura(rs.getDouble("ESTATURA"));
			    		CatTrabajadoresDto.setPeso(rs.getDouble("PESO"));
			    		CatTrabajadoresDto.setNEmpleado(rs.getString("NEMPLEADO"));
			    		CatTrabajadoresDto.setFechaNacimiento(rs.getString("FECHANACIMIENTO"));
			    		CatTrabajadoresDto.setTcamisa(rs.getString("TCAMISA"));
			    		CatTrabajadoresDto.setTplayera(rs.getString("TPLAYERA"));
			    		CatTrabajadoresDto.setTpantalon(rs.getString("TPANTALON"));
			    		CatTrabajadoresDto.setTzapatos(rs.getString("TZAPATOS"));
			    		CatTrabajadoresDto.setReingreso(rs.getString("REINGRESO"));
			    		CatTrabajadoresDto.setLicenciaF(rs.getString("LICENCIAF"));
			    		CatTrabajadoresDto.setGafete(rs.getString("GAFETE"));
			    		CatTrabajadoresDto.setFechaGafete(rs.getString("FECHAGAFETE"));
			    		
			    	}
			    }finally {
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }	
			    
				return CatTrabajadoresDto;
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
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumero clase CatTrabajadoresDaoImpl";
	    }else {
	       return ""+nextClave;
	    }
	}

	@Override
	public String updateBaja(int pNumero, Timestamp pFechaBaja, String pMotivoBaja) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;  
		try {
		conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			
		stmt = conn.prepareStatement("UPDATE [dbo].[TRABAJADOR] SET [FECHABAJA] = ? , [MOTIVO] = ? where [NUMERO] = ?",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		stmt.setTimestamp(1, pFechaBaja);
		stmt.setString(2, pMotivoBaja);
		stmt.setInt(3, pNumero);
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
			 return "FalloTransacaccionMetodo insert clase CatTrabajadoresDaoImpl"; 
		}else {
			return null; 
		}
	}

	@Override
	public String update(CatTrabajadores catTrabajadores) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(catTrabajadores.getNombre())&&null!=catTrabajadores.getNombre()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOMBRE] ='"+catTrabajadores.getNombre()+"'";
			countFields = countFields +1; 
		}
		
		if(!"".equals(catTrabajadores.getNEmpleado())&&null!=catTrabajadores.getNEmpleado()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NEMPLEADO] ='"+catTrabajadores.getNEmpleado()+"'";
			countFields = countFields +1; 
		}
		
		if(!"".equals(catTrabajadores.getDireccion())&&null!=catTrabajadores.getDireccion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [DIRECCION] ='"+catTrabajadores.getDireccion()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getPuesto())&&null!=catTrabajadores.getPuesto()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PUESTO] ='"+catTrabajadores.getPuesto()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getTelefono())&&null!=catTrabajadores.getTelefono()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TELEFONO] ='"+catTrabajadores.getTelefono()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getSeguro())&&null!=catTrabajadores.getSeguro()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [SEGURO] ='"+catTrabajadores.getSeguro()+"'";
			countFields = countFields +1; 
		}
		if(null!=catTrabajadores.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+catTrabajadores.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		
		if(null!=catTrabajadores.getFechaNacimiento()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHANACIMIENTO] = CONVERT(date,'"+catTrabajadores.getFechaNacimiento()+"',21)";
			countFields = countFields +1; 
		}
		
		if(null!=catTrabajadores.getLicencia()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [LICENCIA] = CONVERT(datetime,'"+catTrabajadores.getLicencia()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getNota())&&null!=catTrabajadores.getNota()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOTA] ='"+catTrabajadores.getNota()+"'";
			countFields = countFields +1; 
		}
		if(null!=catTrabajadores.getFechaingreso()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHAINGRESO] = CONVERT(datetime,'"+catTrabajadores.getFechaingreso()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getArea())&&null!=catTrabajadores.getArea()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [AREA] ='"+catTrabajadores.getArea()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getRfc())&&null!=catTrabajadores.getRfc()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [RFC] ='"+catTrabajadores.getRfc()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getCurp())&&null!=catTrabajadores.getCurp()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CURP] ='"+catTrabajadores.getCurp()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getEstadocivil())&&null!=catTrabajadores.getEstadocivil()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ESTADOCIVIL] ='"+catTrabajadores.getEstadocivil()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getEstado())&&null!=catTrabajadores.getEstado()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ESTADO] ='"+catTrabajadores.getEstado()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getTcamisa())&&null!=catTrabajadores.getTcamisa()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TCAMISA] ='"+catTrabajadores.getTcamisa()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getTplayera())&&null!=catTrabajadores.getTplayera()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TPLAYERA] ='"+catTrabajadores.getTplayera()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getTpantalon())&&null!=catTrabajadores.getTpantalon()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TPANTALON] ='"+catTrabajadores.getTpantalon()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getTzapatos())&&null!=catTrabajadores.getTzapatos()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TZAPATOS] ='"+catTrabajadores.getTzapatos()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getReingreso())&&null!=catTrabajadores.getReingreso()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [REINGRESO] ='"+catTrabajadores.getReingreso()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getLicenciaF())&&null!=catTrabajadores.getLicenciaF()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [LICENCIAF] ='"+catTrabajadores.getLicenciaF()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getGafete())&&null!=catTrabajadores.getGafete()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [GAFETE] ='"+catTrabajadores.getGafete()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catTrabajadores.getFechaGafete())&&null!=catTrabajadores.getFechaGafete()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHAGAFETE] ='"+catTrabajadores.getFechaGafete()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ESTATURA] ="+catTrabajadores.getEstatura();
		countFields = countFields +1; 
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PESO] ="+catTrabajadores.getPeso();
		countFields = countFields +1; 
		if(null!=catTrabajadores.getFoto()) {
		if(catTrabajadores.getFoto().length>0) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FOTO] =?";
			countFields = countFields +1; 
		 }
		}
        stmtUpdate =stmtUpdate+" WHERE [NUMERO] ="+catTrabajadores.getNumero(); 
		
        Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(stmtUpdate,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			if(null!=catTrabajadores.getFoto()) {
			if(catTrabajadores.getFoto().length>0) {
				stmt.setBytes(1, catTrabajadores.getFoto());
			} }
			
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
			return "FalloTransaccionMetodo delete clase CatTrabajadoresDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public List<CatTrabajadores> findLicencVencida() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatTrabajadores> listCatTrabajadoresDto = new ArrayList<CatTrabajadores>();
	    try {
	    	
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select nombre, puesto, licencia, numero from "+getTableName()+" WHERE [LICENCIA] < GETDATE()",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatTrabajadores catTrabajadores = new CatTrabajadores();

	    		catTrabajadores.setNombre(rs.getNString("NOMBRE"));
	    		catTrabajadores.setPuesto(rs.getNString("PUESTO"));
	    		catTrabajadores.setLicencia(rs.getTimestamp("LICENCIA"));
	    		catTrabajadores.setNumero(rs.getInt("NUMERO"));
	    		
	    		listCatTrabajadoresDto.add(catTrabajadores);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    return listCatTrabajadoresDto;
	}

	@Override
	public List<CatTrabajadores> findLicencProximosVencer() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatTrabajadores> listCatTrabajadoresDto = new ArrayList<CatTrabajadores>();
	    try {
	    	
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select nombre, puesto, licencia, numero from "+getTableName()+" WHERE [LICENCIA] > GETDATE()",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatTrabajadores catTrabajadores = new CatTrabajadores();

	    		catTrabajadores.setNombre(rs.getNString("NOMBRE"));
	    		catTrabajadores.setPuesto(rs.getNString("PUESTO"));
	    		catTrabajadores.setLicencia(rs.getTimestamp("LICENCIA"));
	    		catTrabajadores.setNumero(rs.getInt("NUMERO"));
	    		
	    		listCatTrabajadoresDto.add(catTrabajadores);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    return listCatTrabajadoresDto;
	}
//--------------------SE INSERTAN LOS VALORES EN LA TABLA ASIGNACIONUNIFORMES---------------------------------------
	@Override
	public String insert2(CatTrabajadores dto) throws SQLException {
		// TODO Auto-generated method stub

		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;  
		try {
		conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			
		stmt = conn.prepareStatement(SQL_INSERT2,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		stmt.setString( COLUMN_TRABAJADOR, dto.getTrabajadorU() );
		stmt.setString( COLUMN_CCAMISA, dto.getcCamisa() );
		stmt.setString( COLUMN_CPLAYERA, dto.getcPlayera() );
		stmt.setString( COLUMN_CPANTALON, dto.getcPantalon() );
		stmt.setString( COLUMN_CZAPATO, dto.getcZapato() );
		stmt.setString( COLUMN_FECHAA, dto.getFechaA() );
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
			 return "FalloTransacaccionMetodo insert2 clase CatTrabajadoresDaoImpl"; 
		}else {
			return null; 
		}
		
	}
	@Override
	public List<CatTrabajadores> findAll2() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatTrabajadores> CatTrabajadoresDto = new ArrayList<CatTrabajadores>();
	    try {
	    	
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECTV3,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatTrabajadores catTrabajadores = new CatTrabajadores();
	    		
	    		
	    		catTrabajadores.setTrabajadorU(rs.getString("NOMBRE"));
	    		catTrabajadores.setcCamisa(rs.getString("CCAMISA"));
	    		catTrabajadores.setcPlayera(rs.getString("CPLAYERA"));
	    		catTrabajadores.setcPantalon(rs.getString("CPANTALON"));
	    		catTrabajadores.setcZapato(rs.getString("CZAPATO"));
	    		catTrabajadores.setFechaA(rs.getString("FECHA"));
	    			    		
	    		CatTrabajadoresDto.add(catTrabajadores);
	    		
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }	
	    
		return CatTrabajadoresDto;
	}
	}


	


