package bs.catalogo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bs.catalogo.dao.CatAutobusDao;
import bs.catalogo.dto.CatAutobus;
import bs.eis.ResourceManager;
import bs.sefaforo.dto.SemaforoAgencia;
import bs.sefaforo.dto.SemaforoTaller;

public class CatAutobusDaoImpl implements CatAutobusDao {

	protected java.sql.Connection userConn;
	
	private static final int COLUMN_FOLIO=1; /** [numeric](18, 0) NULL, **/
	private static final int COLUMN_ECO=2; /** [varchar](50) NULL, **/
	private static final int COLUMN_COMBUSTIBLE=3; /** [varchar](50) NULL, **/
	private static final int COLUMN_EMPRESA=4; /** [varchar](50) NULL, **/
	private static final int COLUMN_ESTADO=5; /** [varchar](50) NULL, **/
	private static final int COLUMN_KILOMETRAJE=6; /** [float] NULL, **/
	private static final int COLUMN_FECHAKM=7; /** [datetime] NULL, **/
	private static final int COLUMN_TIPO=8; /** [varchar](50) NULL, **/
	private static final int COLUMN_SERIE=9; /** [varchar](50) NULL, **/
	private static final int COLUMN_CARROCERIA=10; /** [varchar](50) NULL, **/
	private static final int COLUMN_MODELO=11; /** [varchar](50) NULL, **/
	private static final int COLUMN_MOTOR=12; /** [varchar](50) NULL, **/
	private static final int COLUMN_SERVICIO=13; /** [datetime] NULL, **/
	private static final int COLUMN_KILOMETRAJEMANTTO=14; /** [numeric](18, 0) NULL, **/
	private static final int COLUMN_POLIZA=15; /** [varchar](50) NULL, **/
	private static final int COLUMN_VENCE=16; /** [datetime] NULL, **/
	private static final int COLUMN_FRECUENCIAMANTTO=17; /** [numeric](18, 0) NULL, **/
	private static final int COLUMN_ASEGURADORA=18; /** [varchar](max) NULL, **/
	private static final int COLUMN_KMSRECORRIDOS=19; /** [float] NULL, **/
	private static final int COLUMN_DIASRECORRIDOS=20; /** [float] NULL, **/
	private static final int COLUMN_KMDIA=21; /** [float] NULL, **/
	private static final int COLUMN_DIASFRECUENCIA=22; /** [float] NULL, **/
	private static final int COLUMN_DIASSIGUIENTE=23; /** [float] NULL, **/
	private static final int COLUMN_AGENCIA=24; /** [int] NULL, **/
	private static final int COLUMN_USUARIO=25; /** [nvarchar](50) NULL, **/
	private static final int COLUMN_FECHAALTA=26; /** [smalldatetime] NULL, **/
	private static final int COLUMN_DESCRIPCION=27; /** [text] NULL **/
	/****************** PLACAS NUEVAS ********************************/
	private static final int COLUMN_PASAJEROS = 28; /*[int] NULL, */
	private static final int COLUMN_PLACAS = 29; /* [varchar](50) NULL, */
	private static final int COLUMN_CONCECION = 30; /*[varchar](50) NULL, */
	private static final int COLUMN_CLAVEVEHICULAR = 31; /* [varchar](50) NULL, */
	private static final int COLUMN_VIGULTIMOREFERENDO = 32; /* [datetime] NULL, */
	private static final int COLUMN_TIPOUNIDAD = 33; /* [varchar](50) NULL, */
	private static final int COLUMN_INICIO = 34; /* [int] NULL, */
	private static final int COLUMN_ENDOSO = 35; /* [int] NULL, */
	private static final int COLUMN_COBERTURA = 36; /*[varchar](50) NULL, */
	private static final int COLUMN_HOSPITAL = 37; /* [varchar](200) NULL, */
	private static final int COLUMN_ASEGSERVICIO = 38; /* [varchar](50) NULL, */
	private static final int COLUMN_ASEGBROKER = 39; /* [varchar](50) NULL, */
	private static final int COLUMN_NOTA = 40;
	protected final String SQL_SELECT ="select folio,eco,combustible,empresa,estado,kilometraje,fechakm,tipo,serie,carroceria,modelo,motor,servicio,kilometrajemantto,poliza,vence,frecuenciamantto,aseguradora,kmsrecorridos,diasrecorridos,kmdia,diasfrecuencia,diassiguiente,agencia,usuario,fechaalta,descripcion,pasajeros, placas, concecion, clavevehicular, vigultimoreferendo, tipounidad, inicio, endoso, cobertura, hospital, asegservicio, asegbroker, nota from "+this.getTableName();
	
	protected final String SQL_INSERT = "insert into [dbo].[AUTOBUS](folio,eco,combustible,empresa,estado,kilometraje,fechakm,tipo,serie,carroceria,modelo,motor,servicio,kilometrajemantto,poliza,vence,frecuenciamantto,aseguradora,kmsrecorridos,diasrecorridos,kmdia,diasfrecuencia,diassiguiente,agencia,usuario,fechaalta,descripcion,pasajeros, placas, concecion, clavevehicular, vigultimoreferendo, tipounidad, inicio, endoso, cobertura, hospital, asegservicio, asegbroker, nota) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public String getTableName()
	{
		return "BUSTRAIN.dbo.AUTOBUS";
	}
	
	@Override
	public List<CatAutobus> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatAutobus> listCatAutobus = new ArrayList<CatAutobus>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatAutobus catAutobusDto = new CatAutobus();
	    		
	    		catAutobusDto.setFolio(rs.getInt("folio"));
	    		catAutobusDto.setEco(rs.getString("eco"));
	    		catAutobusDto.setCombustible(rs.getString("combustible"));
	    		catAutobusDto.setEmpresa(rs.getString("empresa"));
	    		catAutobusDto.setEstado(rs.getString("estado"));
	    		catAutobusDto.setKilometraje(rs.getDouble("kilometraje"));
	    		catAutobusDto.setFechakm(rs.getTimestamp("fechakm"));
	    		catAutobusDto.setTipo(rs.getString("tipo"));
	    		catAutobusDto.setSerie(rs.getString("serie"));
	    		catAutobusDto.setCarroceria(rs.getString("carroceria"));
	    		catAutobusDto.setModelo(rs.getString("modelo"));
	    		catAutobusDto.setMotor(rs.getString("motor"));
	    		catAutobusDto.setServicio(rs.getTimestamp("servicio"));
	    		catAutobusDto.setKilometrajemantto(rs.getBigDecimal("kilometrajemantto"));
	    		catAutobusDto.setPoliza(rs.getString("poliza"));
	    		catAutobusDto.setVence(rs.getTimestamp("vence"));
	    		catAutobusDto.setFrecuenciamantto(rs.getBigDecimal("frecuenciamantto"));
	    		catAutobusDto.setAseguradora(rs.getString("aseguradora"));
	    		catAutobusDto.setKmsrecorridos(rs.getDouble("kmsrecorridos"));
	    		catAutobusDto.setDiasrecorridos(rs.getDouble("diasrecorridos"));
	    		catAutobusDto.setKmdia(rs.getDouble("kmdia"));
	    		catAutobusDto.setDiasfrecuencia(rs.getDouble("diasfrecuencia"));
	    		catAutobusDto.setDiassiguiente(rs.getDouble("diassiguiente"));
	    		catAutobusDto.setAgencia(rs.getInt("agencia"));
	    		catAutobusDto.setUsuario(rs.getString("usuario"));
	    		catAutobusDto.setFechaalta(rs.getTimestamp("fechaalta"));
	    		catAutobusDto.setDescripcion(rs.getString("descripcion"));
	    		
	    		catAutobusDto.setPasajeros(rs.getInt("PASAJEROS"));/** [PASAJEROS] [int] NULL,                     **/
	    		catAutobusDto.setPlacas(rs.getString("PLACAS"));/** [PLACAS] [varchar](50) NULL,                **/
	    		catAutobusDto.setConcesion(rs.getString("CONCECION"));/** [CONCECION] [varchar](50) NULL,             **/
	    		catAutobusDto.setClavevehicular(rs.getString("CLAVEVEHICULAR"));/** [CLAVEVEHICULAR] [varchar](50) NULL,        **/
	    		catAutobusDto.setVigultimoreferendo(rs.getTimestamp("VIGULTIMOREFERENDO"));/** [VIGULTIMOREFERENDO] [datetime] NULL,       **/
	    		catAutobusDto.setTipounidad(rs.getString("TIPOUNIDAD"));/** [TIPOUNIDAD] [varchar](50) NULL,            **/
	    		catAutobusDto.setInicio(rs.getInt("INICIO"));/** [INICIO] [int] NULL,                        **/
	    		catAutobusDto.setEndoso(rs.getInt("ENDOSO"));/** [ENDOSO] [int] NULL,                        **/
	    		catAutobusDto.setCobertura(rs.getString("COBERTURA"));/** [COBERTURA] [varchar](50) NULL,             **/
	    		catAutobusDto.setHospital(rs.getString("HOSPITAL"));/** [HOSPITAL] [varchar](200) NULL,             **/
	    		catAutobusDto.setAsegservicio(rs.getString("ASEGSERVICIO"));/** [ASEGSERVICIO] [varchar](50) NULL,          **/
	    		catAutobusDto.setAsegbroker(rs.getString("ASEGBROKER"));/** [ASEGBROKER] [varchar](50) NULL,            **/
	    		catAutobusDto.setNota(rs.getString("NOTA"));
	    		
	    		listCatAutobus.add(catAutobusDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listCatAutobus;
	}

	@Override
	public List<CatAutobus> findUnidadesList() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<CatAutobus> listCatAutobus = new ArrayList<CatAutobus>();
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(" select folio,eco from "+this.getTableName()+" order by eco asc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatAutobus catAutobusDto = new CatAutobus();
	    		catAutobusDto.setFolio(rs.getInt("folio"));
	    		catAutobusDto.setEco(rs.getString("eco"));
	    		listCatAutobus.add(catAutobusDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
		return listCatAutobus;
	 
	}

	@Override
	public String insert(CatAutobus dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
			stmt.setInt(COLUMN_FOLIO,dto.getFolio()); /** [numeric](18, 0) NULL, **/
			stmt.setString(COLUMN_ECO,dto.getEco()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_COMBUSTIBLE,dto.getCombustible()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_EMPRESA,dto.getEmpresa()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_ESTADO,dto.getEstado()); /** [varchar](50) NULL, **/
			if(null!=dto.getKilometraje()) {
			stmt.setDouble(COLUMN_KILOMETRAJE,dto.getKilometraje()); /** [float] NULL, **/
			}else {
				stmt.setDouble(COLUMN_KILOMETRAJE,new Double(0));	
			}
			if(null!=dto.getFechakm()) {
			stmt.setTimestamp(COLUMN_FECHAKM,dto.getFechakm());  /** [datetime] NULL, **/
			}else {
				stmt.setTimestamp(COLUMN_FECHAKM,null); 	
			}
			stmt.setString(COLUMN_TIPO,dto.getTipo()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_SERIE,dto.getSerie()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_CARROCERIA,dto.getCarroceria()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_MODELO,dto.getModelo()); /** [varchar](50) NULL, **/
			stmt.setString(COLUMN_MOTOR,dto.getMotor()); /** [varchar](50) NULL, **/
			stmt.setTimestamp(COLUMN_SERVICIO,dto.getServicio()); /** [datetime] NULL, **/
			stmt.setBigDecimal(COLUMN_KILOMETRAJEMANTTO,dto.getKilometrajemantto()); /** [numeric](18, 0) NULL, **/
			stmt.setString(COLUMN_POLIZA,dto.getPoliza()); /** [varchar](50) NULL, **/
			stmt.setTimestamp(COLUMN_VENCE,dto.getVence()); /** [datetime] NULL, **/
			stmt.setBigDecimal(COLUMN_FRECUENCIAMANTTO,dto.getFrecuenciamantto()); /** [numeric](18, 0) NULL, **/
			stmt.setString(COLUMN_ASEGURADORA,dto.getAseguradora()); /** [varchar](max) NULL, **/
			if(null!=dto.getKmsrecorridos()) {
			stmt.setDouble(COLUMN_KMSRECORRIDOS,dto.getKmsrecorridos()); /** [float] NULL, **/
			}else {
				stmt.setDouble(COLUMN_KMSRECORRIDOS,new Double(0));	
			}
			if(null!=dto.getDiasrecorridos()) {
			stmt.setDouble(COLUMN_DIASRECORRIDOS,dto.getDiasrecorridos()); /** [float] NULL, **/
			}else {
				stmt.setDouble(COLUMN_DIASRECORRIDOS,new Double(0));
			}
			if(null!=dto.getKmdia()) {
			stmt.setDouble(COLUMN_KMDIA,dto.getKmdia()); /** [float] NULL, **/
			}else {
				stmt.setDouble(COLUMN_KMDIA,new Double(0));	
			}
			if(null!=dto.getDiasfrecuencia()) {
			stmt.setDouble(COLUMN_DIASFRECUENCIA,dto.getDiasfrecuencia()); /** [float] NULL, **/
			}else {
			stmt.setDouble(COLUMN_DIASFRECUENCIA,new Double(0));
			}
			if(null!=dto.getDiassiguiente()) {
			stmt.setDouble(COLUMN_DIASSIGUIENTE,dto.getDiassiguiente()); /** [float] NULL, **/
			}else {
				stmt.setDouble(COLUMN_DIASSIGUIENTE,new Double(0));	
			}
			stmt.setInt(COLUMN_AGENCIA,dto.getAgencia()); /** [int] NULL, **/
			stmt.setString(COLUMN_USUARIO,dto.getUsuario()); /** [nvarchar](50) NULL, **/
			stmt.setTimestamp(COLUMN_FECHAALTA,dto.getFechaalta()); /** [smalldatetime] NULL, **/
			if(null!=dto.getDescripcion()) {
			stmt.setString(COLUMN_DESCRIPCION,dto.getDescripcion()); /** [text] NULL **/
			}else {
				stmt.setString(COLUMN_DESCRIPCION,null);
			}
			
			stmt.setInt(COLUMN_PASAJEROS, dto.getPasajeros());/** [PASAJEROS] [int] NULL,               **/
			stmt.setString(COLUMN_PLACAS, dto.getPlacas());/** [PLACAS] [varchar](50) NULL,          **/
			stmt.setString(COLUMN_CONCECION, dto.getConcesion());/** [CONCECION] [varchar](50) NULL,       **/
			stmt.setString(COLUMN_CLAVEVEHICULAR, dto.getClavevehicular());/** [CLAVEVEHICULAR] [varchar](50) NULL,  **/
			stmt.setTimestamp(COLUMN_VIGULTIMOREFERENDO, dto.getVigultimoreferendo());/** [VIGULTIMOREFERENDO] [datetime] NULL, **/
			stmt.setString(COLUMN_TIPOUNIDAD, dto.getTipounidad());/** [TIPOUNIDAD] [varchar](50) NULL,      **/
			stmt.setInt(COLUMN_INICIO, dto.getInicio());/** [INICIO] [int] NULL,                  **/
			stmt.setInt(COLUMN_ENDOSO, dto.getEndoso());/** [ENDOSO] [int] NULL,                  **/
			stmt.setString(COLUMN_COBERTURA, dto.getCobertura());/** [COBERTURA] [varchar](50) NULL,       **/
			stmt.setString(COLUMN_HOSPITAL, dto.getHospital());/** [HOSPITAL] [varchar](200) NULL,       **/
			stmt.setString(COLUMN_ASEGSERVICIO, dto.getAsegservicio());/** [ASEGSERVICIO] [varchar](50) NULL,    **/
			stmt.setString(COLUMN_ASEGBROKER, dto.getAsegbroker());/** [ASEGBROKER] [varchar](50) NULL,      **/ 
			stmt.setString(COLUMN_NOTA, dto.getNota());
			
			stmt.execute();
			
			rowsAffected = stmt.getUpdateCount();
			System.out.println( rowsAffected + " rows affected" );
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
			return "FalloTransacaccionMetodo insert clase CatClientesDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String findNextFolio() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextClave = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([FOLIO])+1 NEXT_FOLIO from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
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
	    	 return "NoSePudoRecuperaInformacionMetodo findNextFolio clase CatAutobusDaoImpl";
	    }else {
	       return ""+nextClave;
	    }
	}

	@Override
	public String findCombustible(String strpUnidadValue) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strCombustible = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select COMBUSTIBLE from "+this.getTableName()+" where eco='"+strpUnidadValue+"'",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strCombustible = rs.getString(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    return strCombustible; 
	    
	}

	@Override
	public CatAutobus findByFolio(int pFolio) throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    CatAutobus catAutobusDto = new CatAutobus();
			    try {
			    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    	stmt = conn.prepareStatement(SQL_SELECT+" where folio="+pFolio,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
			    	rs = stmt.executeQuery(); 
			    	if(rs.next()) {
			    		catAutobusDto.setFolio(rs.getInt("folio"));
			    		catAutobusDto.setEco(rs.getString("eco"));
			    		catAutobusDto.setCombustible(rs.getString("combustible"));
			    		catAutobusDto.setEmpresa(rs.getString("empresa"));
			    		catAutobusDto.setEstado(rs.getString("estado"));
			    		catAutobusDto.setKilometraje(rs.getDouble("kilometraje"));
			    		catAutobusDto.setFechakm(rs.getTimestamp("fechakm"));
			    		catAutobusDto.setTipo(rs.getString("tipo"));
			    		catAutobusDto.setCarroceria(rs.getString("carroceria"));
			    		catAutobusDto.setModelo(rs.getString("modelo"));
			    		catAutobusDto.setMotor(rs.getString("motor"));
			    		catAutobusDto.setServicio(rs.getTimestamp("servicio"));
			    		catAutobusDto.setKilometrajemantto(rs.getBigDecimal("kilometrajemantto"));
			    		catAutobusDto.setPoliza(rs.getString("poliza"));
			    		catAutobusDto.setVence(rs.getTimestamp("vence"));
			    		catAutobusDto.setFrecuenciamantto(rs.getBigDecimal("frecuenciamantto"));
			    		catAutobusDto.setAseguradora(rs.getString("aseguradora"));
			    		catAutobusDto.setKmsrecorridos(rs.getDouble("kmsrecorridos"));
			    		catAutobusDto.setDiasrecorridos(rs.getDouble("diasrecorridos"));
			    		catAutobusDto.setKmdia(rs.getDouble("kmdia"));
			    		catAutobusDto.setDiasfrecuencia(rs.getDouble("diasfrecuencia"));
			    		catAutobusDto.setDiassiguiente(rs.getDouble("diassiguiente"));
			    		catAutobusDto.setAgencia(rs.getInt("agencia"));
			    		catAutobusDto.setUsuario(rs.getString("usuario"));
			    		catAutobusDto.setFechaalta(rs.getTimestamp("fechaalta"));
			    		catAutobusDto.setDescripcion(rs.getString("descripcion"));
			    		
			    		catAutobusDto.setSerie(rs.getString("serie"));
			    		catAutobusDto.setPasajeros(rs.getInt("PASAJEROS"));/** [PASAJEROS] [int] NULL,                     **/
			    		catAutobusDto.setPlacas(rs.getString("PLACAS"));/** [PLACAS] [varchar](50) NULL,                **/
			    		catAutobusDto.setConcesion(rs.getString("CONCECION"));/** [CONCECION] [varchar](50) NULL,             **/
			    		catAutobusDto.setClavevehicular(rs.getString("CLAVEVEHICULAR"));/** [CLAVEVEHICULAR] [varchar](50) NULL,        **/
			    		catAutobusDto.setVigultimoreferendo(rs.getTimestamp("VIGULTIMOREFERENDO"));/** [VIGULTIMOREFERENDO] [datetime] NULL,       **/
			    		catAutobusDto.setTipounidad(rs.getString("TIPOUNIDAD"));/** [TIPOUNIDAD] [varchar](50) NULL,            **/
			    		catAutobusDto.setInicio(rs.getInt("INICIO"));/** [INICIO] [int] NULL,                        **/
			    		catAutobusDto.setEndoso(rs.getInt("ENDOSO"));/** [ENDOSO] [int] NULL,                        **/
			    		catAutobusDto.setCobertura(rs.getString("COBERTURA"));/** [COBERTURA] [varchar](50) NULL,             **/
			    		catAutobusDto.setHospital(rs.getString("HOSPITAL"));/** [HOSPITAL] [varchar](200) NULL,             **/
			    		catAutobusDto.setAsegservicio(rs.getString("ASEGSERVICIO"));/** [ASEGSERVICIO] [varchar](50) NULL,          **/
			    		catAutobusDto.setAsegbroker(rs.getString("ASEGBROKER"));/** [ASEGBROKER] [varchar](50) NULL,            **/
			    		catAutobusDto.setNota(rs.getString("NOTA"));
			    	
			    	}
			    }finally {
			    	ResourceManager.close(rs);
			    	ResourceManager.close(stmt);
			    	if(null==this.userConn) {
			    		ResourceManager.close(conn);
			    	}
			    }
		return catAutobusDto;
	}

	@Override
	public String update(CatAutobus catAutobusDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(catAutobusDto.getAseguradora())&&null!=catAutobusDto.getAseguradora()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ASEGURADORA] ='"+catAutobusDto.getAseguradora()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getCarroceria())&&null!=catAutobusDto.getCarroceria()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CARROCERIA] ='"+catAutobusDto.getCarroceria()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getCombustible())&&null!=catAutobusDto.getCombustible()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COMBUSTIBLE] ='"+catAutobusDto.getCombustible()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getEco())&&null!=catAutobusDto.getEco()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ECO] ='"+catAutobusDto.getEco()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getEmpresa())&&null!=catAutobusDto.getEmpresa()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [EMPRESA] ='"+catAutobusDto.getEmpresa()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getEstado())&&null!=catAutobusDto.getEstado()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ESTADO] ='"+catAutobusDto.getEstado()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FRECUENCIAMANTTO] ="+catAutobusDto.getFrecuenciamantto();
		countFields = countFields +1; 
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [AGENCIA] ="+catAutobusDto.getAgencia();
		countFields = countFields +1; 
		
		if(!"".equals(catAutobusDto.getModelo())&&null!=catAutobusDto.getModelo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [MODELO] ='"+catAutobusDto.getModelo()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getMotor())&&null!=catAutobusDto.getMotor()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [MOTOR] ='"+catAutobusDto.getMotor()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getPoliza())&&null!=catAutobusDto.getPoliza()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [POLIZA] ='"+catAutobusDto.getPoliza()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getSerie())&&null!=catAutobusDto.getSerie()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [SERIE] ='"+catAutobusDto.getSerie()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getTipo())&&null!=catAutobusDto.getTipo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPO] ='"+catAutobusDto.getTipo()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getNota())&&null!=catAutobusDto.getNota()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [NOTA] ='"+catAutobusDto.getNota()+"'";
			countFields = countFields +1; 
		}
		if(null!=catAutobusDto.getServicio()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [SERVICIO] = CONVERT(datetime,'"+catAutobusDto.getServicio()+"',21)";
			countFields = countFields +1; 
		}
		if(null!=catAutobusDto.getVence()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [VENCE] = CONVERT(datetime,'"+catAutobusDto.getVence()+"',21)";
			countFields = countFields +1; 
		}
		
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PASAJEROS] ="+catAutobusDto.getPasajeros();
		countFields = countFields +1;  /** [PASAJEROS] [int] NULL, **/
		if(!"".equals(catAutobusDto.getPlacas())&&null!=catAutobusDto.getPlacas()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PLACAS] ='"+catAutobusDto.getPlacas()+"'";
			countFields = countFields +1;  
		}/** [PLACAS] [varchar](50) NULL,          **/
		if(!"".equals(catAutobusDto.getConcesion())&&null!=catAutobusDto.getConcesion()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CONCECION] ='"+catAutobusDto.getConcesion()+"'";
			countFields = countFields +1;  
		}/** [CONCECION] [varchar](50) NULL,       **/
		if(!"".equals(catAutobusDto.getClavevehicular())&&null!=catAutobusDto.getClavevehicular()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CLAVEVEHICULAR] ='"+catAutobusDto.getClavevehicular()+"'";
			countFields = countFields +1;  
		}/** [CLAVEVEHICULAR] [varchar](50) NULL,  **/
		if(null!=catAutobusDto.getVigultimoreferendo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [VIGULTIMOREFERENDO] = CONVERT(datetime,'"+catAutobusDto.getVigultimoreferendo()+"',21)";
			countFields = countFields +1; 
		}/** [VIGULTIMOREFERENDO] [datetime] NULL, **/
		System.out.println("AutobusDaoImpl:"+catAutobusDto.getTipounidad());
		if(!"".equals(catAutobusDto.getTipounidad())&&null!=catAutobusDto.getTipounidad()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPOUNIDAD] ='"+catAutobusDto.getTipounidad()+"'";
			countFields = countFields +1;  
		}/** [TIPOUNIDAD] [varchar](50) NULL,      **/
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [INICIO] ="+catAutobusDto.getInicio();
		countFields = countFields +1;  /** [INICIO] [int] NULL,                  **/
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ENDOSO] ="+catAutobusDto.getEndoso();
		countFields = countFields +1;/** [ENDOSO] [int] NULL,                  **/
		if(!"".equals(catAutobusDto.getCobertura())&&null!=catAutobusDto.getCobertura()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COBERTURA] ='"+catAutobusDto.getCobertura()+"'";
			countFields = countFields +1;  
		}/** [COBERTURA] [varchar](50) NULL,       **/
		if(!"".equals(catAutobusDto.getHospital())&&null!=catAutobusDto.getHospital()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [HOSPITAL] ='"+catAutobusDto.getHospital()+"'";
			countFields = countFields +1;  
		}/** [HOSPITAL] [varchar](200) NULL,       **/
		if(!"".equals(catAutobusDto.getAsegservicio())&&null!=catAutobusDto.getAsegservicio()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ASEGSERVICIO] ='"+catAutobusDto.getAsegservicio()+"'";
			countFields = countFields +1;  
		}/** [ASEGSERVICIO] [varchar](50) NULL,    **/
		if(!"".equals(catAutobusDto.getAsegbroker())&&null!=catAutobusDto.getAsegbroker()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ASEGBROKER] ='"+catAutobusDto.getAsegbroker()+"'";
			countFields = countFields +1;  
		}/** [ASEGBROKER] [varchar](50) NULL,      **/ 
		
	
		stmtUpdate =stmtUpdate+" WHERE [FOLIO] ="+catAutobusDto.getFolio(); 
			
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
			return "FalloTransaccionMetodo update clase CatUnidadesDaoImpl"; 
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
			return "FalloTransaccionMetodo delete clase CatUnidadesDaoImpl"; 
		}else {
			return null;
		}
	}

	protected final String SQL_SEL_SEMAF_TALLER = " SELECT [ECO] " + 
												  "      ,[SERVICIO] " + 
												  "      ,[KILOMETRAJEMANTTO] " + 
												  "	  ,[FECHAKM] " + 
												  "	  ,[KILOMETRAJE] " + 
												  "	  ,([KILOMETRAJE]-[KILOMETRAJEMANTTO]) KM_RECORRIDOS " + 
												  "	  ,FRECUENCIAMANTTO " + 
												  "	  ,DATEDIFF(day,[SERVICIO],[FECHAKM]) DIAS_RECORRIDOS " + 
												  "	  ,DATEDIFF(month,[SERVICIO],[FECHAKM]) MESES_RECORRIDOS " + 
												  "   ,ROUND(([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM]),0) KM_POR_DIA " + 
												  "	  ,ROUND(FRECUENCIAMANTTO/(([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM])),0) FRECUENCIA_DIAS " + 
												  "	  ,DATEADD(day,ROUND(FRECUENCIAMANTTO/(([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM])),0),[SERVICIO]) FECHA_SIG_MANNTO "+
												  "  FROM [dbo].[AUTOBUS] " + 
												  " where estado='ACTIVO' " + 
												  " and agencia = 0 "+
												  " and ([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM]) !=0 ";
	@Override
	public List<SemaforoTaller> findAllSemaforoTaller() throws SQLException {
	    	List<SemaforoTaller> listSemaforoTaller = new ArrayList<SemaforoTaller>();
			Connection conn = null; 
			PreparedStatement stmt = null;
		    ResultSet rs = null; 
		    try {
		    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    	stmt = conn.prepareStatement(SQL_SEL_SEMAF_TALLER,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
		    	rs = stmt.executeQuery(); 
		    	while(rs.next()) {
		    		SemaforoTaller semaforoTaller = new SemaforoTaller(); 
		    		semaforoTaller.setEco(rs.getString("ECO"));
		    		semaforoTaller.setServicio(rs.getTimestamp("SERVICIO"));
		    		semaforoTaller.setKilometrajemannto(rs.getDouble("KILOMETRAJEMANTTO"));
		    		semaforoTaller.setFechakm(rs.getTimestamp("FECHAKM"));
		    		semaforoTaller.setKilometraje(rs.getDouble("KILOMETRAJE"));
		    		semaforoTaller.setKm_recorridos(rs.getDouble("KM_RECORRIDOS"));
		    		semaforoTaller.setFrecuenciamannto(rs.getBigDecimal("FRECUENCIAMANTTO"));
		    		semaforoTaller.setDias_recorridos(rs.getInt("DIAS_RECORRIDOS"));
		    		semaforoTaller.setMeses_recorridos(rs.getInt("MESES_RECORRIDOS"));
		    		semaforoTaller.setKm_por_dia(rs.getInt("KM_POR_DIA"));
		    		semaforoTaller.setFrecuencia_dias(rs.getInt("FRECUENCIA_DIAS"));
		    		semaforoTaller.setManntosiguiente(rs.getTimestamp("FECHA_SIG_MANNTO"));
		    		listSemaforoTaller.add(semaforoTaller);
		     	}
		    }finally {
		    	ResourceManager.close(rs);
		    	ResourceManager.close(stmt);
		    	if(null==this.userConn) {
		    		ResourceManager.close(conn);
		    	}
		    }
		return listSemaforoTaller;
	}

	protected final String SQL_SEL_SEMAF_AGENCIA = " SELECT [ECO] " + 
												   "      ,[SERVICIO] " + 
												   "      ,[KILOMETRAJEMANTTO] " + 
												   "	  ,[FECHAKM] " + 
												   "	  ,[KILOMETRAJE] " + 
												   "	  ,([KILOMETRAJE]-[KILOMETRAJEMANTTO]) KM_RECORRIDOS " + 
												   "	  ,FRECUENCIAMANTTO " + 
												   "	  ,DATEDIFF(day,[SERVICIO],[FECHAKM]) DIAS_RECORRIDOS " + 
												   "	  ,DATEDIFF(month,[SERVICIO],[FECHAKM]) MESES_RECORRIDOS " +
												   "      ,ROUND(([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM]),0) KM_POR_DIA " + 
												   "	  ,ROUND(FRECUENCIAMANTTO/(([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM])),0) FRECUENCIA_DIAS " + 
												   "	  ,DATEADD(day,ROUND(FRECUENCIAMANTTO/(([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM])),0),[SERVICIO]) FECHA_SIG_MANNTO " +
												   "  FROM [dbo].[AUTOBUS] " + 
												   " where estado='ACTIVO' " + 
												   " and agencia = 1 "+
												   " and FRECUENCIAMANTTO is not null "+
												   " and ([KILOMETRAJE]-[KILOMETRAJEMANTTO])/DATEDIFF(day,[SERVICIO],[FECHAKM]) !=0 "; 
	@Override
	public List<SemaforoAgencia> findAllSemaforoAgencia() throws SQLException {
		List<SemaforoAgencia> listSemaforoAgencia = new ArrayList<SemaforoAgencia>();
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SEL_SEMAF_AGENCIA,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		SemaforoAgencia semaforoAgencia = new SemaforoAgencia(); 
	    		semaforoAgencia.setEco(rs.getString("ECO"));
	    		semaforoAgencia.setServicio(rs.getTimestamp("SERVICIO"));
	    		semaforoAgencia.setKilometrajemannto(rs.getDouble("KILOMETRAJEMANTTO"));
	    		semaforoAgencia.setFechakm(rs.getTimestamp("FECHAKM"));
	    		semaforoAgencia.setKilometraje(rs.getDouble("KILOMETRAJE"));
	    		semaforoAgencia.setKm_recorridos(rs.getDouble("KM_RECORRIDOS"));
	    		semaforoAgencia.setFrecuenciamannto(rs.getBigDecimal("FRECUENCIAMANTTO"));
	    		semaforoAgencia.setDias_recorridos(rs.getInt("DIAS_RECORRIDOS"));
	    		semaforoAgencia.setMeses_recorridos(rs.getInt("MESES_RECORRIDOS"));
	    		semaforoAgencia.setKm_por_dia(rs.getInt("KM_POR_DIA"));
	    		semaforoAgencia.setFrecuencia_dias(rs.getInt("FRECUENCIA_DIAS"));
	    		semaforoAgencia.setManntosiguiente(rs.getTimestamp("FECHA_SIG_MANNTO"));
	    		listSemaforoAgencia.add(semaforoAgencia);
	     	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	return listSemaforoAgencia;
	}

	@Override
	public List<CatAutobus> findCatalogarUnidades() throws SQLException {
		List<CatAutobus> listCatalogarUnidades = new ArrayList<CatAutobus>();
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("SELECT FOLIO, ECO, CATEGORIA1, CATEGORIA2 FROM "+getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		CatAutobus catAutobus = new CatAutobus(); 
	    		catAutobus.setFolio(rs.getInt("FOLIO"));
	    		catAutobus.setEco(rs.getString("ECO"));
	    		catAutobus.setCategoria1(rs.getString("CATEGORIA1"));
	    		catAutobus.setCategoria2(rs.getString("CATEGORIA2"));
	    		listCatalogarUnidades.add(catAutobus); 
	     	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   return listCatalogarUnidades;
	}

	@Override
	public String updateCatalogarUnidades(CatAutobus catAutobusDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(catAutobusDto.getCategoria1())&&null!=catAutobusDto.getCategoria1()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CATEGORIA1] ='"+catAutobusDto.getCategoria1()+"'";
			countFields = countFields +1; 
		}
		if(!"".equals(catAutobusDto.getCategoria2())&&null!=catAutobusDto.getCategoria1()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [CATEGORIA2] ='"+catAutobusDto.getCategoria2()+"'";
			countFields = countFields +1; 
		}
		stmtUpdate =stmtUpdate+" WHERE [FOLIO] ="+catAutobusDto.getFolio(); 
		
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
			return "FalloTransaccionMetodo updateCatalogarUnidades clase CatUnidadesDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public void updateFallas(String eco
			               , Timestamp fecharep
			               , double kilometraje
			               , String descripcion) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		
		System.out.println("Entra Fallas");
		
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET SERVICIO = ? ,KILOMETRAJEMANTTO="+kilometraje+" ,DESCRIPCION='"+descripcion+"'";
		stmtUpdate = stmtUpdate+" WHERE ECO='"+eco+"'"; 
		stmtUpdate = stmtUpdate+" AND SERVICIO < CONVERT(datetime,'"+fecharep+"',21)";
		stmtUpdate = stmtUpdate+" AND KILOMETRAJEMANTTO < "+kilometraje;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(stmtUpdate,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setTimestamp(1, fecharep);
			stmt.execute();
		    rowsAffected = stmt.getUpdateCount();
		}finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
	    }
		System.out.println("rowsAffected:"+rowsAffected); 
		
		
		System.out.println("Sale updateFallas");
		
	}
	

}
