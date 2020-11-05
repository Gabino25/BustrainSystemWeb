package bs.llantas.entry.jdbc;

/** Referencias https://docs.microsoft.com/en-us/sql/t-sql/functions/cast-and-convert-transact-sql?view=sql-server-2017 **/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bs.eis.ResourceManager;
import bs.llantas.entry.dao.LlantasDao;
import bs.llantas.entry.dto.Llantas;

public class LlantasDaoImpl implements LlantasDao {
    
	protected java.sql.Connection userConn; 
	
	private static final int COLUMN_FOLIO=1; /**numeric](18, 0) **/
	private static final int COLUMN_NOMBRE=2; /**varchar](50) **/
	private static final int COLUMN_MEDIDA=3; /**varchar](50) **/
	private static final int COLUMN_MARCA=4; /**varchar](50) **/
	private static final int COLUMN_COSTO=5; /**float] **/
	private static final int COLUMN_PROFUNDIDAD_INICIAL=6; /**int] **/
	private static final int COLUMN_PROFUNDIDAD_ACTUAL=7; /**int] **/
	private static final int COLUMN_FECHA_ALTA=8; /**datetime] **/
	private static final int COLUMN_FECHA_REVISION=9; /**datetime] **/
	private static final int COLUMN_ESTADO=10; /**varchar](50) **/
	private static final int COLUMN_UNIDAD=11; /**varchar](50) **/
	private static final int COLUMN_POSICION=12; /**varchar](50) **/
	private static final int COLUMN_PRESION=13; /**varchar](50) **/
	private static final int COLUMN_MODELO=14; /**nvarchar](50) **/
	private static final int COLUMN_KMACUMULADO=15; /**real] **/
	private static final int COLUMN_NOTA=16; /**nvarchar](max) **/
	private static final int COLUMN_COSTOACUMULADO=17; /**real] **/
	private static final int COLUMN_USUARIO=18; /**nvarchar](50) **/
	private static final int COLUMN_KMINICIAL=19; /**real] **/
	private static final int COLUMN_KMFINAL=20; /**real] **/
	private static final int COLUMN_TIPO=21; /**nchar](10) **/
	private static final int COLUMN_FECHACAPTURA=22; /**datetime] **/
	private static final int COLUMN_OPERADOR=23; /**varchar](50) NULL **/
	
	private final String SQL_SELECT ="SELECT [FOLIO] " + 
	 		" ,[NOMBRE] " + 
	 		" ,[MEDIDA] " + 
	 		" ,[MARCA] " + 
	 		" ,[COSTO] " + 
	 		" ,[PROFUNDIDAD_INICIAL] " + 
	 		" ,[PROFUNDIDAD_ACTUAL] " + 
	 		" ,[FECHA_ALTA] " + 
	 		" ,[FECHA_REVISION] " + 
	 		" ,[ESTADO] " + 
	 		" ,[UNIDAD] " + 
	 		" ,[POSICION] " + 
	 		" ,[PRESION] " + 
	 		" ,[MODELO] " + 
	 		" ,[KMACUMULADO] " + 
	 		" ,[NOTA] " + 
	 		" ,[COSTOACUMULADO] " + 
	 		" ,[USUARIO] " + 
	 		" ,[KMINICIAL] " + 
	 		" ,[KMFINAL] " + 
	 		" ,[TIPO] " + 
	 		" ,[FECHACAPTURA] " + 
	 		" ,[OPERADOR] "+
	 		"  FROM "+getTableName();
	
	 private static final String  SQL_INSERT = "INSERT INTO [dbo].[LLANTAS] ( FOLIO ,NOMBRE ,MEDIDA ,MARCA ,COSTO ,PROFUNDIDAD_INICIAL ,PROFUNDIDAD_ACTUAL  ,FECHA_ALTA  ,FECHA_REVISION ,ESTADO ,UNIDAD ,POSICION ,PRESION ,MODELO ,KMACUMULADO ,NOTA ,COSTOACUMULADO ,USUARIO ,KMINICIAL ,KMFINAL ,TIPO ,FECHACAPTURA ,OPERADOR ) VALUES(  ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )"; 
	    
	    public String getTableName()
		{
			return "BUSTRAIN.dbo.LLANTAS";
		}
	    
	@Override
	public List<Llantas> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Llantas> listLlantasDto = new ArrayList<Llantas>(); 
	    
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " WHERE ESTADO='ACTIVA' ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		
	    		Llantas llantasDto = new Llantas(); 
	    		
	    		llantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**numeric](18, 0) null,**/
	    		llantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**varchar](50) null,**/
	    		llantasDto.setMedida(rs.getString(COLUMN_MEDIDA)); /**varchar](50) null,**/
	    		llantasDto.setMarca(rs.getString(COLUMN_MARCA)); /**varchar](50) null,**/
	    		llantasDto.setCosto(rs.getDouble(COLUMN_COSTO)); /**float] null,**/
	    		llantasDto.setProfundidad_inicial(rs.getInt(COLUMN_PROFUNDIDAD_INICIAL)); /**int] null,**/
	    		llantasDto.setProfundidad_actual(rs.getInt(COLUMN_PROFUNDIDAD_ACTUAL)); /**int] null,**/
	    		llantasDto.setFecha_alta(rs.getTimestamp(COLUMN_FECHA_ALTA)); /**datetime] null,**/
	    		llantasDto.setFecha_revision(rs.getTimestamp(COLUMN_FECHA_REVISION)); /**datetime] null,**/
	    		llantasDto.setEstado(rs.getString(COLUMN_ESTADO)); /**varchar](50) null,**/
	    		llantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**varchar](50) null,**/
	    		llantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**varchar](50) null,**/
	    		llantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**varchar](50) null,**/
	    		llantasDto.setModelo(rs.getNString(COLUMN_MODELO)); /**nvarchar](50) null,**/
	    		llantasDto.setKmacumulado(rs.getFloat(COLUMN_KMACUMULADO)); /**real] null,**/
	    		llantasDto.setNota(rs.getNString(COLUMN_NOTA)); /**nvarchar](max) null,**/
	    		llantasDto.setCostoacumulado(rs.getFloat(COLUMN_COSTOACUMULADO)); /**real] null,**/
	    		llantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**nvarchar](50) null,**/
	    		llantasDto.setKminicial(rs.getFloat(COLUMN_KMINICIAL)); /**real] null,**/
	    		llantasDto.setKmfinal(rs.getFloat(COLUMN_KMFINAL)); /**real] null,**/
	    		llantasDto.setTipo(rs.getNString(COLUMN_TIPO)); /**nchar](10) null,**/
	    		llantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**datetime] null,**/
	    		llantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**varchar](50) NULL**/
	    		
	    		listLlantasDto.add(llantasDto);
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return listLlantasDto;
	    
	}

	@Override
	public List<Llantas> findSelectList() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Llantas> listLlantasDto = new ArrayList<Llantas>(); 
	    
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(" SELECT folio, nombre from "+getTableName()+" WHERE ESTADO='ACTIVA' order by nombre asc",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	while(rs.next()) {
	    		
	    		Llantas llantasDto = new Llantas(); 
	    		llantasDto.setFolio(rs.getInt("folio"));
	    		llantasDto.setNombre(rs.getString("nombre"));
	    		listLlantasDto.add(llantasDto); 
	    		
	    	}	
	    	  }finally {
	  	    	ResourceManager.close(rs);
	  	    	ResourceManager.close(stmt);
	  	    	if(null==this.userConn) {
	  	    		ResourceManager.close(conn);
	  	    	}
	  	   }
	  	    
	  return listLlantasDto;
	  	    
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
	public String insert(Llantas dto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		 
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
			stmt.setInt(COLUMN_FOLIO,dto.getFolio());/* [FOLIO] [int] NULL,                   */
			stmt.setString(COLUMN_NOMBRE, dto.getNombre());/* [NOMBRE] [varchar](50) NULL,          */
			stmt.setString(COLUMN_MEDIDA, dto.getMedida());/* [MEDIDA] [varchar](50) NULL,          */
			stmt.setString(COLUMN_MARCA, dto.getMarca());/* [MARCA] [varchar](50) NULL,           */
			stmt.setDouble(COLUMN_COSTO, dto.getCosto());/* [COSTO] [float] NULL,                 */
			stmt.setInt(COLUMN_PROFUNDIDAD_INICIAL, dto.getProfundidad_inicial());/* [PROFUNDIDAD_INICIAL] [int] NULL,     */
			stmt.setInt(COLUMN_PROFUNDIDAD_ACTUAL, dto.getProfundidad_actual());/* [PROFUNDIDAD_ACTUAL] [int] NULL,      */
			stmt.setTimestamp(COLUMN_FECHA_ALTA,dto.getFecha_alta());/* [FECHA_ALTA] [datetime] NULL,         */
			stmt.setTimestamp(COLUMN_FECHA_REVISION, dto.getFecha_revision());/* [FECHA_REVISION] [datetime] NULL,     */
			stmt.setString(COLUMN_ESTADO, dto.getEstado());/* [ESTADO] [varchar](50) NULL,          */
			stmt.setString(COLUMN_UNIDAD, dto.getUnidad());/* [UNIDAD] [varchar](50) NULL,          */
			stmt.setString(COLUMN_POSICION, dto.getPosicion());/* [POSICION] [varchar](50) NULL,        */
			stmt.setString(COLUMN_PRESION, dto.getPresion());/* [PRESION] [varchar](50) NULL,         */
			stmt.setNString(COLUMN_MODELO, dto.getModelo());/* [MODELO] [nvarchar](50) NULL,         */
			stmt.setFloat(COLUMN_KMACUMULADO, dto.getKmacumulado());/* [KMACUMULADO] [real] NULL,            */
			stmt.setNString(COLUMN_NOTA, dto.getNota());/* [NOTA] [nvarchar](max) NULL,          */
			stmt.setFloat(COLUMN_COSTOACUMULADO, dto.getCostoacumulado());/* [COSTOACUMULADO] [real] NULL,         */
			stmt.setNString(COLUMN_USUARIO, dto.getUsuario());/* [USUARIO] [nvarchar](50) NULL,        */
			stmt.setFloat(COLUMN_KMINICIAL, dto.getKminicial());/* [KMINICIAL] [real] NULL,              */
			stmt.setFloat(COLUMN_KMFINAL, dto.getKmfinal());/* [KMFINAL] [real] NULL,                */
			stmt.setNString(COLUMN_TIPO, dto.getTipo());/* [TIPO] [nchar](10) NULL,              */
			stmt.setTimestamp(COLUMN_FECHACAPTURA, dto.getFechacaptura());/* [FECHACAPTURA] [datetime] NULL,       */
			stmt.setString(COLUMN_OPERADOR, dto.getOperador());/* [OPERADOR] [varchar](50) NULL         */
			
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
			return "FalloTransaccionMetodo insert clase LlantasDaoImpl"; 
		}else {
			return null;
		}
		
	}

	@Override
	public String validaCodigo(String strpCodigo) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    String strNombre = null; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select NOMBRE from "+this.getTableName()+" where NOMBRE='"+strpCodigo+"'",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		strNombre = rs.getString(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    return strNombre; 
	}

	@Override
	public Llantas findByNombre(String strllantaTrx) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    Llantas llantasDto =new Llantas();
	    
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement(SQL_SELECT+ " WHERE NOMBRE='"+strllantaTrx+"' ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		
	        	llantasDto.setFolio(rs.getInt(COLUMN_FOLIO)); /**numeric](18, 0) null,**/
	    		llantasDto.setNombre(rs.getString(COLUMN_NOMBRE)); /**varchar](50) null,**/
	    		llantasDto.setMedida(rs.getString(COLUMN_MEDIDA)); /**varchar](50) null,**/
	    		llantasDto.setMarca(rs.getString(COLUMN_MARCA)); /**varchar](50) null,**/
	    		llantasDto.setCosto(rs.getDouble(COLUMN_COSTO)); /**float] null,**/
	    		llantasDto.setProfundidad_inicial(rs.getInt(COLUMN_PROFUNDIDAD_INICIAL)); /**int] null,**/
	    		llantasDto.setProfundidad_actual(rs.getInt(COLUMN_PROFUNDIDAD_ACTUAL)); /**int] null,**/
	    		llantasDto.setFecha_alta(rs.getTimestamp(COLUMN_FECHA_ALTA)); /**datetime] null,**/
	    		llantasDto.setFecha_revision(rs.getTimestamp(COLUMN_FECHA_REVISION)); /**datetime] null,**/
	    		llantasDto.setEstado(rs.getString(COLUMN_ESTADO)); /**varchar](50) null,**/
	    		llantasDto.setUnidad(rs.getString(COLUMN_UNIDAD)); /**varchar](50) null,**/
	    		llantasDto.setPosicion(rs.getString(COLUMN_POSICION)); /**varchar](50) null,**/
	    		llantasDto.setPresion(rs.getString(COLUMN_PRESION)); /**varchar](50) null,**/
	    		llantasDto.setModelo(rs.getNString(COLUMN_MODELO)); /**nvarchar](50) null,**/
	    		llantasDto.setKmacumulado(rs.getFloat(COLUMN_KMACUMULADO)); /**real] null,**/
	    		llantasDto.setNota(rs.getNString(COLUMN_NOTA)); /**nvarchar](max) null,**/
	    		llantasDto.setCostoacumulado(rs.getFloat(COLUMN_COSTOACUMULADO)); /**real] null,**/
	    		llantasDto.setUsuario(rs.getNString(COLUMN_USUARIO)); /**nvarchar](50) null,**/
	    		llantasDto.setKminicial(rs.getFloat(COLUMN_KMINICIAL)); /**real] null,**/
	    		llantasDto.setKmfinal(rs.getFloat(COLUMN_KMFINAL)); /**real] null,**/
	    		llantasDto.setTipo(rs.getNString(COLUMN_TIPO)); /**nchar](10) null,**/
	    		llantasDto.setFechacaptura(rs.getTimestamp(COLUMN_FECHACAPTURA)); /**datetime] null,**/
	    		llantasDto.setOperador(rs.getString(COLUMN_OPERADOR)); /**varchar](50) NULL**/
	    	
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	    
	    return llantasDto;
	}

	@Override
	public String update(Llantas llantaDto) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		
		if(!"".equals(llantaDto.getMedida())&&null!=llantaDto.getMedida()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [MEDIDA] ='"+llantaDto.getMedida()+"'";
			countFields = countFields +1; 
		}
	
		if(!"".equals(llantaDto.getMarca())&&null!=llantaDto.getMarca()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [MARCA] ='"+llantaDto.getMarca()+"'";
			countFields = countFields +1; 
		}
	
		if(!"".equals(llantaDto.getModelo())&&null!=llantaDto.getModelo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [MODELO] ='"+llantaDto.getModelo()+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PROFUNDIDAD_INICIAL] ="+llantaDto.getProfundidad_inicial();
		
		if(!"".equals(llantaDto.getTipo())&&null!=llantaDto.getTipo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPO] ='"+llantaDto.getTipo()+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COSTO] ="+llantaDto.getCosto();
		
		
	
		if(null!=llantaDto.getFecha_alta()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA_ALTA] = CONVERT(datetime,'"+llantaDto.getFecha_alta()+"',21)";
			countFields = countFields +1; 
		}
		
		stmtUpdate =stmtUpdate+" WHERE [NOMBRE] ='"+llantaDto.getNombre()+"'"; 
		
		
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
			return "FalloTransaccionMetodo update clase LlantasDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String deleteByNombre(String strllantaTrx) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDelete =" DELETE from "+this.getTableName()+" WHERE [NOMBRE] ='"+strllantaTrx+"'"; 
	
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
			return "FalloTransaccionMetodo delete clase LlantasDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String updateAsignacionAunidad( String pSrtPosicion,
			   String pStrPresion,
			   float pFkminicial, 
			   float pFkmfinal,
			   String pStrunidad,
			   String pStrNombre) throws SQLException {
		// TODO Auto-generated method stub
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0;
		
		
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(null!=pSrtPosicion&&!"".contentEquals(pSrtPosicion)) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [POSICION] ='"+pSrtPosicion+"'";
			countFields = countFields +1; 
		}
		if(null!=pStrPresion&&!"".contentEquals(pStrPresion)) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PRESION] ='"+pStrPresion+"'";
			countFields = countFields +1; 
		}
		if(0f!=pFkminicial)
		 {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KMINICIAL] ='"+pFkminicial+"'";
		 
			countFields = countFields +1; 
		 }
		if(0f!=pFkmfinal)
		 {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KMFINAL] ='"+pFkmfinal+"'";
		 
			countFields = countFields +1; 
		 }
		if(null!=pStrunidad&&!"".contentEquals(pStrunidad)) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [UNIDAD] ='"+pStrunidad+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate =stmtUpdate+" WHERE [NOMBRE] ='"+pStrNombre+"'"; 
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
			return "FalloTransaccionMetodo delete updateAsignacionAunidad clase LlantasDaoImpl"; 
		}else {
			return null;
		}
	
	}

	@Override
	public String updateProfundidad(float pIntProfundidadActual, 
			float pFloatKmFinal,
			Timestamp pTimeFechaRevision,
			String pStrNombre)
	
			throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0;
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		stmtUpdate=stmtUpdate+((countFields>0)?",":"")+"[KMACUMULADO]=[KMFINAL]-[KMINICIAL]";
		
		countFields=countFields+1;
		if(0f!=pIntProfundidadActual) {
		
		
		stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PROFUNDIDAD_ACTUAL] ="+pIntProfundidadActual;
		countFields=countFields+1;
		}
		 if(0f!=pFloatKmFinal)
		 {
			 stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KMFINAL] ="+pFloatKmFinal;
				countFields=countFields+1;
		 }
		 if(null!=pTimeFechaRevision) {
		 stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA_REVISION] = CONVERT(datetime,'"+pTimeFechaRevision+"',21)";
			countFields = countFields +1;
		 }
		stmtUpdate =stmtUpdate+" WHERE [NOMBRE] ='"+pStrNombre+"'"; 
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
			return "FalloTransaccionMetodo  updateProfundidad clase LlantasDaoImpl"; 
		}else {
			return null;
		}
	}

	@Override
	public String updateReparaciones(Double pDCosto, float pFloatKmFinal, String pStrNombre) throws SQLException {
		// TODO Auto-generated method stub
		final boolean isConnSupplied = (userConn != null);
		int countFields = 0;
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(null!=pDCosto)
		{
			stmtUpdate=stmtUpdate+((countFields>0)?",":"")+"[COSTOACUMULADO]="+pDCosto.floatValue();
			countFields=countFields+1;
			stmtUpdate=stmtUpdate+((countFields>0)?",":"")+"[COSTO]=[COSTO]+"+pDCosto.doubleValue();
			countFields=countFields+1;
		}
      
		 if(0f!=pFloatKmFinal)
		 {
			 stmtUpdate=stmtUpdate+((countFields>0)?",":"")+"[KMACUMULADO]=[KMFINAL]-[KMINICIAL]";
				countFields=countFields+1;
				
			 stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [KMFINAL] ="+pFloatKmFinal;
				countFields=countFields+1;
		 }
		stmtUpdate =stmtUpdate+" WHERE [NOMBRE] ='"+pStrNombre+"'"; 
		
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
			return "FalloTransaccionMetodo  updateProfundidad clase LlantasDaoImpl"; 
		}else {
			return null;
		}
	
	}


}
