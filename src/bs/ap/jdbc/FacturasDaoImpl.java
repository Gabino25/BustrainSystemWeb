package bs.ap.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bs.ap.dao.FacturasDao;
import bs.ap.dto.Conceptos;
import bs.ap.dto.Facturas;
import bs.eis.ResourceManager;

public class FacturasDaoImpl implements FacturasDao {
	
	protected java.sql.Connection userConn;
	
	private static final int COLUMN_NUMFACT= 1; /*[numeric](18, 0) NULL,*/
	private static final int COLUMN_PROVEEDOR= 2; /*[varchar](80) NULL, */
	private static final int COLUMN_ORDEN= 3; /*[numeric](18, 0) NULL,*/
	private static final int COLUMN_FOLIO= 4; /*[varchar](50) NULL, */
	private static final int COLUMN_COSTO= 5; /*[float] NULL, */
	private static final int COLUMN_FECHA= 6; /*[smalldatetime] NULL, */
	private static final int COLUMN_TIPO = 7; /* [nvarchar](50) NULL, */
	private static final int COLUMN_USUARIO= 8; /*[nvarchar](50) NULL,*/
	private static final int COLUMN_ESTADO = 9; /* [varchar](50) NULL */
	
	public String getTableName()
	{
		return "BUSTRAIN.dbo.FACTURAS";
	}
	
	protected final String SQL_SELECT = " SELECT [NUMFACT] ,[PROVEEDOR] ,[ORDEN] ,[FOLIO] ,[COSTO] ,[FECHA] ,[TIPO] ,[USUARIO] ,[ESTADO]  from "+getTableName();
	protected final String SQL_SELECT_TOP1000 = " SELECT TOP(1000) [NUMFACT] ,[PROVEEDOR] ,[ORDEN] ,[FOLIO] ,[COSTO] ,[FECHA] ,[TIPO] ,[USUARIO] ,[ESTADO]  from "+getTableName();
	
	
	@Override
	public List<Facturas> findAll() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
		List<Facturas> listFacturas = new ArrayList<Facturas>();
		 try {
			    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    	stmt = conn.prepareStatement(SQL_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
		    	rs = stmt.executeQuery(); 
		    	while(rs.next()) {
		    		Facturas factura = new Facturas(); 
		    		
		    		factura.setNumfact(rs.getLong(COLUMN_NUMFACT)); /** [NUMFACT] [numeric](18, 0) NULL, **/
		    		factura.setProveedor(rs.getString(COLUMN_PROVEEDOR)); /** [PROVEEDOR] [varchar](80) NULL, **/
		    		factura.setOrden(rs.getBigDecimal(COLUMN_ORDEN)); /** [ORDEN] [numeric](18, 0) NULL, **/
		    		factura.setFolio(rs.getString(COLUMN_FOLIO)); /** [FOLIO] [varchar](50) NULL, **/
		    		factura.setCosto(rs.getDouble(COLUMN_COSTO)); /** [COSTO] [float] NULL, **/
		    		factura.setFecha(rs.getTimestamp(COLUMN_FECHA));/** [FECHA] [smalldatetime] NULL, **/
		    		factura.setTipo(rs.getNString(COLUMN_TIPO));/** [TIPO] [nvarchar](50) NULL, **/
		    		factura.setUsuario(rs.getNString(COLUMN_USUARIO));/** [USUARIO] [nvarchar](50) NULL, **/
		    		factura.setEstado(rs.getString(COLUMN_ESTADO));/** [ESTADO] [varchar](50) NULL **/
		    		
		    		listFacturas.add(factura);
		    		
		    	 }
		    }
		    finally {
			   	ResourceManager.close(rs);
			   	ResourceManager.close(stmt);
			   	if(null==this.userConn) {
			  	ResourceManager.close(conn);
			   	}
			 }
		    
		
		return listFacturas;
	}

	@Override
	public List<Facturas> findTop1000() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
		List<Facturas> listFacturas = new ArrayList<Facturas>();
		 try {
			    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			    stmt = conn.prepareStatement(SQL_SELECT_TOP1000+"  order by [NUMFACT] desc ",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
		    	rs = stmt.executeQuery(); 
		    	while(rs.next()) {
		    		Facturas factura = new Facturas(); 
		    		
		    		factura.setNumfact(rs.getLong(COLUMN_NUMFACT)); /** [NUMFACT] [numeric](18, 0) NULL, **/
		    		factura.setProveedor(rs.getString(COLUMN_PROVEEDOR)); /** [PROVEEDOR] [varchar](80) NULL, **/
		    		factura.setOrden(rs.getBigDecimal(COLUMN_ORDEN)); /** [ORDEN] [numeric](18, 0) NULL, **/
		    		factura.setFolio(rs.getString(COLUMN_FOLIO)); /** [FOLIO] [varchar](50) NULL, **/
		    		factura.setCosto(rs.getDouble(COLUMN_COSTO)); /** [COSTO] [float] NULL, **/
		    		factura.setFecha(rs.getTimestamp(COLUMN_FECHA));/** [FECHA] [smalldatetime] NULL, **/
		    		factura.setTipo(rs.getNString(COLUMN_TIPO));/** [TIPO] [nvarchar](50) NULL, **/
		    		factura.setUsuario(rs.getNString(COLUMN_USUARIO));/** [USUARIO] [nvarchar](50) NULL, **/
		    		factura.setEstado(rs.getString(COLUMN_ESTADO));/** [ESTADO] [varchar](50) NULL **/
		    		
		    		listFacturas.add(factura);
		    		
		    	 }
		    }
		    finally {
			   	ResourceManager.close(rs);
			   	ResourceManager.close(stmt);
			   	if(null==this.userConn) {
			  	ResourceManager.close(conn);
			   	}
			 }
		    
		
		return listFacturas;
	}

	
	protected final String SQL_INSERT_FACTURAS = "insert into [dbo].[FACTURAS] ( NUMFACT ,PROVEEDOR ,ORDEN ,FOLIO ,COSTO ,FECHA ,TIPO ,USUARIO ,ESTADO ) VALUES (  ? ,? ,? ,? ,? ,? ,? ,? ,? )";
	
	protected final String SQL_INSERT_CONCEPTOS = " insert into [dbo].[CONCEPTOS] ( NUMCONCEPTO ,FOLIO ,PROVEEDOR ,CONCEPTO  ,NUMFACT ,ECO ,COSTO ,FECHA ,GASTO ,FECHAALTA ,TIPO  ,ORDEN ,USUARIO)  VALUES (  ? ,?  ,?  ,?  ,?  ,?  ,?  ,?  ,?  ,?  ,?  ,?  ,?  ) "; 
	
	@Override
	public String insert(Facturas pFacturasDto 
			            ,List<Conceptos> pListConceptos
			            ) throws SQLException {
	
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
		try {
			
			conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			stmt = conn.prepareStatement(SQL_INSERT_FACTURAS,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setLong(COLUMN_NUMFACT, pFacturasDto.getNumfact());/** NUMFACT] [bigint] NOT NULL,**/
			stmt.setString(COLUMN_PROVEEDOR, pFacturasDto.getProveedor());/** PROVEEDOR] [varchar](80) NULL, **/
			stmt.setBigDecimal(COLUMN_ORDEN, pFacturasDto.getOrden());/** ORDEN] [numeric](18, 0) NULL,**/
			stmt.setString(COLUMN_FOLIO, pFacturasDto.getFolio());/** FOLIO] [varchar](50) NULL, **/
			stmt.setDouble(COLUMN_COSTO, pFacturasDto.getCosto());/** COSTO] [float] NULL, **/
			stmt.setTimestamp(COLUMN_FECHA, pFacturasDto.getFecha());/** FECHA] [smalldatetime] NULL, **/
			stmt.setNString(COLUMN_TIPO, pFacturasDto.getTipo());/** TIPO] [nvarchar](50) NULL, **/
			stmt.setNString(COLUMN_USUARIO, pFacturasDto.getUsuario());/** USUARIO] [nvarchar](50) NULL,**/
			stmt.setString(COLUMN_ESTADO, pFacturasDto.getEstado());/** ESTADO] [varchar](50) NULL,**/
			
			stmt.executeUpdate();
			
			Iterator<Conceptos> iterConceptos = pListConceptos.iterator();
			while(iterConceptos.hasNext()) {
				stmt = null; 
				Conceptos concepto = iterConceptos.next(); 
				stmt = conn.prepareStatement(SQL_INSERT_CONCEPTOS,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
				
				stmt.setLong(1, concepto.getNumconcepto());/** [NUMCONCEPTO] [bigint] NOT NULL, **/
				stmt.setString(2, concepto.getFolio());/** [FOLIO] [varchar](50) NULL, **/
				stmt.setString(3, concepto.getProveedor());/** [PROVEEDOR] [varchar](80) NULL, **/
				stmt.setString(4, concepto.getConcepto());/** [CONCEPTO] [text] NULL, **/
				stmt.setLong(5, concepto.getNumfact());/** [NUMFACT] [bigint] NOT NULL, **/
				stmt.setNString(6, concepto.getEco());/** [ECO] [nvarchar](50) NULL, **/
				stmt.setDouble(7, concepto.getCosto());/** [COSTO] [float] NULL, **/
				stmt.setTimestamp(8, concepto.getFecha());/** [FECHA] [datetime] NULL, **/
				stmt.setString(9, concepto.getGasto());/** [GASTO] [varchar](50) NULL, **/
				stmt.setTimestamp(10, concepto.getFechaalta());/** [FECHAALTA] [datetime] NULL, **/
				stmt.setString(11, concepto.getTipo());/** [TIPO] [varchar](50) NULL, **/
				stmt.setBigDecimal(12, concepto.getOrden());/** [ORDEN] [numeric](18, 0) NULL, **/
				stmt.setString(13, concepto.getUsuario());/** [USUARIO] [nvarchar](50) NULL, **/
				
				stmt.executeUpdate();
				
			}
			
			conn.commit();
			
		}catch(SQLException sqle) {
			conn.rollback();
			throw sqle;
		} finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
		return null; 
	}

	@Override
	public String findNextNumfact() throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    int nextNumFact = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareStatement("select max([NUMFACT])+1 NEXT_NUMFACT from "+this.getTableName(),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
	    	rs = stmt.executeQuery(); 
	    	if(rs.next()) {
	    		nextNumFact = rs.getInt(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    if(0==nextNumFact) {
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumfact clase FacturasDaoImpl";
	    }else {
	       return ""+nextNumFact;
	    }
	}

	@Override
	public Facturas findByNumfact(long pNumfact) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
		Facturas factura = new Facturas(); 
		 try {
			    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    	stmt = conn.prepareStatement(SQL_SELECT+" where numfact="+pNumfact,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
		    	rs = stmt.executeQuery(); 
		    	if(rs.next()) {
		    	
		    		
		    		factura.setNumfact(rs.getLong(COLUMN_NUMFACT)); /** [NUMFACT] [numeric](18, 0) NULL, **/
		    		factura.setProveedor(rs.getString(COLUMN_PROVEEDOR)); /** [PROVEEDOR] [varchar](80) NULL, **/
		    		factura.setOrden(rs.getBigDecimal(COLUMN_ORDEN)); /** [ORDEN] [numeric](18, 0) NULL, **/
		    		factura.setFolio(rs.getString(COLUMN_FOLIO)); /** [FOLIO] [varchar](50) NULL, **/
		    		factura.setCosto(rs.getDouble(COLUMN_COSTO)); /** [COSTO] [float] NULL, **/
		    		factura.setFecha(rs.getTimestamp(COLUMN_FECHA));/** [FECHA] [smalldatetime] NULL, **/
		    		factura.setTipo(rs.getNString(COLUMN_TIPO));/** [TIPO] [nvarchar](50) NULL, **/
		    		factura.setUsuario(rs.getNString(COLUMN_USUARIO));/** [USUARIO] [nvarchar](50) NULL, **/
		    		factura.setEstado(rs.getString(COLUMN_ESTADO));/** [ESTADO] [varchar](50) NULL **/
		   
		    		
		    	 }
		    }
		    finally {
			   	ResourceManager.close(rs);
			   	ResourceManager.close(stmt);
			   	if(null==this.userConn) {
			  	ResourceManager.close(conn);
			   	}
			 }
		    
		
		return factura;
	}

	@Override
	public String update(Facturas pFactura, List<Conceptos> pListConceptos) throws SQLException {
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    try {
	    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		if(conn.getAutoCommit()) {
			conn.setAutoCommit(false);
		}
		
		Iterator<Conceptos> iteConceptos =  pListConceptos.iterator();
		while(iteConceptos.hasNext()) {
			Conceptos concepto = iteConceptos.next(); 
			int countFieldsConceptos = 0; 
			String stmtUpdateConceptos =" UPDATE [dbo].[CONCEPTOS] SET ";  
			if(!"".equals(concepto.getConcepto())&&null!=concepto.getConcepto()) {
				stmtUpdateConceptos = stmtUpdateConceptos+((countFieldsConceptos>0)?",":"")+" [CONCEPTO] ='"+concepto.getConcepto()+"'";
				countFieldsConceptos = countFieldsConceptos +1; 
			}
			if(concepto.getCosto()>0d) {
				stmtUpdateConceptos = stmtUpdateConceptos+((countFieldsConceptos>0)?",":"")+" [COSTO] ="+concepto.getCosto();
				countFieldsConceptos = countFieldsConceptos +1; 
			}
			if(!"".equals(concepto.getGasto())&&null!=concepto.getGasto()) {
				stmtUpdateConceptos = stmtUpdateConceptos+((countFieldsConceptos>0)?",":"")+" [GASTO] ='"+concepto.getGasto()+"'";
				countFieldsConceptos = countFieldsConceptos +1; 
			}
			if(!"".equals(concepto.getEco())&&null!=concepto.getEco()) {
				stmtUpdateConceptos = stmtUpdateConceptos+((countFieldsConceptos>0)?",":"")+" [ECO] ='"+concepto.getEco()+"'";
				countFieldsConceptos = countFieldsConceptos +1; 
			}
			
			stmtUpdateConceptos =stmtUpdateConceptos+" WHERE [NUMCONCEPTO] ="+concepto.getNumconcepto(); 
			
			stmt = null; 
			stmt = conn.prepareStatement(stmtUpdateConceptos,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate();
		} /** END LOOP **/
		
		
		int countFields = 0; 
		String stmtUpdate =" UPDATE "+this.getTableName()+" SET ";
		if(!"".equals(pFactura.getProveedor())&&null!=pFactura.getProveedor()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [PROVEEDOR] ='"+pFactura.getProveedor()+"'";
			countFields = countFields +1; 
		}
		if(null!=pFactura.getOrden()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [ORDEN] ="+pFactura.getOrden();
			countFields = countFields +1; 
		}
		if(!"".equals(pFactura.getFolio())&&null!=pFactura.getFolio()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FOLIO] ='"+pFactura.getFolio()+"'";
			countFields = countFields +1; 
		}
		if(pFactura.getCosto()>0d) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [COSTO] ="+pFactura.getCosto();
			countFields = countFields +1; 
		}
		if(null!=pFactura.getFecha()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [FECHA] = CONVERT(datetime,'"+pFactura.getFecha()+"',21)";
			countFields = countFields +1; 
		}
		if(!"".equals(pFactura.getTipo())&&null!=pFactura.getTipo()) {
			stmtUpdate = stmtUpdate+((countFields>0)?",":"")+" [TIPO] ='"+pFactura.getTipo()+"'";
			countFields = countFields +1; 
		}
		
		stmtUpdate = stmtUpdate+" WHERE NUMFACT="+pFactura.getNumfact();
		
		stmt = null; 
		stmt = conn.prepareStatement(stmtUpdate,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate();
		
		conn.commit();
		
	    }catch(SQLException sqle) {
			conn.rollback();
			throw sqle;
		} finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
	    
	    return null; 
	}

	@Override
	public String deleteByNumFact(long pNumfact) throws SQLException {
		final boolean isConnSupplied = (userConn != null);
		String stmtDeleteConceptos =" DELETE from [dbo].[CONCEPTOS] WHERE [NUMFACT] ="+pNumfact; 
		String stmtDeleteFacturas =" DELETE from [dbo].[FACTURAS] WHERE [NUMFACT] ="+pNumfact;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0; 
		try {
			
			conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			stmt = conn.prepareStatement(stmtDeleteConceptos,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			rowsAffected = stmt.executeUpdate();
			stmt = null; 
			stmt = conn.prepareStatement(stmtDeleteFacturas,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate();
			rowsAffected = rowsAffected +stmt.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException sqle) {
			conn.rollback();
			throw sqle;
		} finally {
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
		
		System.out.println("Se borraron "+rowsAffected+" registros.");
		return null; 
	}
	

}
