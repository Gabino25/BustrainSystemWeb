package bs.ap.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bs.ap.dao.ConceptosDao;
import bs.ap.dto.Conceptos;
import bs.eis.ResourceManager;

public class ConceptosDaoImpl implements ConceptosDao {
	
	protected java.sql.Connection userConn;
	
	private static final int COLUMN_NUMCONCEPTO = 1; /*[numeric](18, 0) NULL, */
	private static final int COLUMN_FOLIO = 2; /*[varchar](50) NULL,*/
	private static final int COLUMN_PROVEEDOR = 3; /*[varchar](80) NULL,*/
	private static final int COLUMN_CONCEPTO= 4; /* [text] NULL,*/
	private static final int COLUMN_NUMFACT = 5; /*[numeric](18, 0) NULL, */
	private static final int COLUMN_ECO = 6; /*[nvarchar](50) NULL, */
	private static final int COLUMN_COSTO = 7; /*[float] NULL,*/
	private static final int COLUMN_FECHA = 8; /*[datetime] NULL, */
	private static final int COLUMN_GASTO = 9; /*[varchar](50) NULL,*/
	private static final int COLUMN_FECHAALTA = 10; /*[datetime] NULL, */
	private static final int COLUMN_TIPO= 11; /* [varchar](50) NULL, */
	private static final int COLUMN_ORDEN = 12; /*[numeric](18, 0) NULL, */
	private static final int COLUMN_USUARIO = 13; /*[nvarchar](50) NULL*/

	public String getTableName()
	{
		return "BUSTRAIN.dbo.CONCEPTOS";
	}
	
	protected final String SLQ_SELECT = " SELECT [NUMCONCEPTO] ,[FOLIO] ,[PROVEEDOR] ,[CONCEPTO] ,[NUMFACT] ,[ECO] ,[COSTO] ,[FECHA] ,[GASTO] ,[FECHAALTA] ,[TIPO] ,[ORDEN] ,[USUARIO] from "+getTableName();
	
	
	@Override
	public List<Conceptos> findAll() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    List<Conceptos> listConceptos = new ArrayList<Conceptos>();
	    try {
	    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
    	stmt = conn.prepareStatement(SLQ_SELECT,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
    	rs = stmt.executeQuery(); 
    	while(rs.next()) {
    		Conceptos conceptos = new Conceptos(); 
    		conceptos.setNumconcepto(rs.getLong(COLUMN_NUMCONCEPTO)); /** [NUMCONCEPTO] [numeric](18, 0) NULL, **/ 
    		conceptos.setFolio(rs.getString(COLUMN_FOLIO)); /** [FOLIO] [varchar](50) NULL,**/
    		conceptos.setProveedor(rs.getString(COLUMN_PROVEEDOR)); /** [PROVEEDOR] [varchar](80) NULL,**/
    		conceptos.setConcepto(rs.getString(COLUMN_CONCEPTO)); /** [CONCEPTO] [text] NULL,**/
    		conceptos.setNumfact(rs.getLong(COLUMN_NUMFACT)); /** [NUMFACT] [numeric](18, 0) NULL, **/
    		conceptos.setEco(rs.getNString(COLUMN_ECO));/** [ECO] [nvarchar](50) NULL, **/
    		conceptos.setCosto(rs.getDouble(COLUMN_COSTO));/** [COSTO] [float] NULL,**/
    		conceptos.setFecha(rs.getTimestamp(COLUMN_FECHA));/** [FECHA] [datetime] NULL, **/
    		conceptos.setGasto(rs.getString(COLUMN_GASTO));/** [GASTO] [varchar](50) NULL,**/
    		conceptos.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA));/** [FECHAALTA] [datetime] NULL, **/
    		conceptos.setTipo(rs.getString(COLUMN_TIPO));/** [TIPO] [varchar](50) NULL, **/
    		conceptos.setOrden(rs.getBigDecimal(COLUMN_ORDEN));/** [ORDEN] [numeric](18, 0) NULL, **/
    		conceptos.setUsuario(rs.getNString(COLUMN_USUARIO));/** [USUARIO] [nvarchar](50) NULL**/
    		listConceptos.add(conceptos); 
		 }
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
	    
	    return listConceptos; 
	}
	
	@Override
	public String findNextNumconcepto() throws SQLException {
		/**********************************************************************
		Connection conn = null; 
	    Statement stmt = null;
	    ResultSet rs = null; 
	    int nextNumConcepto = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.createStatement(); 
	    	rs = stmt.executeQuery(" select next value for [dbo].[conceptos_s] from sys.sequences where name = 'conceptos_s'; "); 
	    	if(rs.next()) {
	    		nextNumConcepto = rs.getInt(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    if(0==nextNumConcepto) {
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumconcepto clase ConceptosDaoImpl";
	    }else {
	       return ""+nextNumConcepto;
	    }
	    *************************************************************************/
		Connection conn = null; 
	    CallableStatement stmt = null;
	    ResultSet rs = null; 
	    long nextNumConcepto = 0; 
	    try {
	    	conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
	    	stmt = conn.prepareCall("{call [dbo].[getConceptosSequenceValue]}");
	    	rs = stmt.executeQuery();
	    	if(rs.next()) {
	    		nextNumConcepto = rs.getLong(1); 
	    	}
	    }finally {
	    	ResourceManager.close(rs);
	    	ResourceManager.close(stmt);
	    	if(null==this.userConn) {
	    		ResourceManager.close(conn);
	    	}
	    }
	   
	    if(0==nextNumConcepto) {
	    	 return "NoSePudoRecuperaInformacionMetodo findNextNumconcepto clase ConceptosDaoImpl";
	    }else {
	       return ""+nextNumConcepto;
	    }
	    
	}

	@Override
	public List<Conceptos> findByNumfact(long pNumfact) throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = null; 
				PreparedStatement stmt = null;
			    ResultSet rs = null; 
			    List<Conceptos> listConceptos = new ArrayList<Conceptos>();
			    try {
			    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
		    	stmt = conn.prepareStatement(SLQ_SELECT+" where numfact="+pNumfact,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
		    	rs = stmt.executeQuery(); 
		    	while(rs.next()) {
		    		Conceptos conceptos = new Conceptos(); 
		    		conceptos.setNumconcepto(rs.getLong(COLUMN_NUMCONCEPTO)); /** [NUMCONCEPTO] [numeric](18, 0) NULL, **/ 
		    		conceptos.setFolio(rs.getString(COLUMN_FOLIO)); /** [FOLIO] [varchar](50) NULL,**/
		    		conceptos.setProveedor(rs.getString(COLUMN_PROVEEDOR)); /** [PROVEEDOR] [varchar](80) NULL,**/
		    		conceptos.setConcepto(rs.getString(COLUMN_CONCEPTO)); /** [CONCEPTO] [text] NULL,**/
		    		conceptos.setNumfact(rs.getLong(COLUMN_NUMFACT)); /** [NUMFACT] [numeric](18, 0) NULL, **/
		    		conceptos.setEco(rs.getNString(COLUMN_ECO));/** [ECO] [nvarchar](50) NULL, **/
		    		conceptos.setCosto(rs.getDouble(COLUMN_COSTO));/** [COSTO] [float] NULL,**/
		    		conceptos.setFecha(rs.getTimestamp(COLUMN_FECHA));/** [FECHA] [datetime] NULL, **/
		    		conceptos.setGasto(rs.getString(COLUMN_GASTO));/** [GASTO] [varchar](50) NULL,**/
		    		conceptos.setFechaalta(rs.getTimestamp(COLUMN_FECHAALTA));/** [FECHAALTA] [datetime] NULL, **/
		    		conceptos.setTipo(rs.getString(COLUMN_TIPO));/** [TIPO] [varchar](50) NULL, **/
		    		conceptos.setOrden(rs.getBigDecimal(COLUMN_ORDEN));/** [ORDEN] [numeric](18, 0) NULL, **/
		    		conceptos.setUsuario(rs.getNString(COLUMN_USUARIO));/** [USUARIO] [nvarchar](50) NULL**/
		    		listConceptos.add(conceptos); 
				 }
			    }
			    finally {
				   	ResourceManager.close(rs);
				   	ResourceManager.close(stmt);
				   	if(null==this.userConn) {
				  	ResourceManager.close(conn);
				   	}
				 }
			    
			    return listConceptos; 
	}
	
	
	@Override
	public List<Conceptos> filtradoFacturas(int pDesdeDay
			                             , int pDesdeMonth
			                             , int pDesdeYear
			                             , int pHastaDay
			                             , int pHastaMonth
			                             , int pHastaYear
			                             , String strjsUnidad
			                             , String strjsProveedor
			                             , String strjsDescripcion
			                             ) throws SQLException {
		
		List<Conceptos> listConceptos = new ArrayList<Conceptos>();
		
		String strLocalStmt = "SELECT FOLIO, PROVEEDOR, CONCEPTO, ECO, COSTO, FECHA from [dbo].[CONCEPTOS] where fecha between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE)";
		if(null!=strjsUnidad
		   &&!"".equals(strjsUnidad)
		   ) {
			strLocalStmt = strLocalStmt+" and ECO='"+strjsUnidad+"'"; 
		}
		if(null!=strjsProveedor
		   &&!"".equals(strjsProveedor)
		   ) {
					strLocalStmt = strLocalStmt+" and PROVEEDOR='"+strjsProveedor+"'"; 
		}
		if(null!=strjsDescripcion
		   &&!"".equals(strjsDescripcion)
	      ) {
					strLocalStmt = strLocalStmt+" and CONCEPTO='"+strjsDescripcion+"'"; 
		}
		
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    try {
	    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
    	stmt = conn.prepareStatement(strLocalStmt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
    	rs = stmt.executeQuery(); 
    	while(rs.next()) {
    		Conceptos conceptos = new Conceptos(); 
    		conceptos.setFolio(rs.getString("FOLIO")); /** [FOLIO] [varchar](50) NULL,**/
    		conceptos.setProveedor(rs.getString("PROVEEDOR")); /** [PROVEEDOR] [varchar](80) NULL,**/
    		conceptos.setConcepto(rs.getString("CONCEPTO")); /** [CONCEPTO] [text] NULL,**/
    		conceptos.setEco(rs.getNString("ECO"));/** [ECO] [nvarchar](50) NULL, **/
    		conceptos.setCosto(rs.getDouble("COSTO"));/** [COSTO] [float] NULL,**/
    		conceptos.setFecha(rs.getTimestamp("FECHA"));/** [FECHA] [datetime] NULL, **/
    		
    		listConceptos.add(conceptos); 
		 }
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
		
		return listConceptos; 
	}

	@Override
	public List<Conceptos> filtradoFacturasBustrain(int pDesdeDay
			                                      , int pDesdeMonth
			                                      , int pDesdeYear
			                                      , int pHastaDay
			                                      , int pHastaMonth
			                                      , int pHastaYear) throws SQLException {
	
         List<Conceptos> listConceptos = new ArrayList<Conceptos>();
		
		String strLocalStmt = " SELECT [ECO] " + 
				              " ,[PROVEEDOR] " + 
	           			      " ,[CONCEPTO] " + 
				              " ,[COSTO] " + 
				              " ,[FECHA] " + 
				              " ,[FOLIO] " + 
				              " ,[GASTO] " + 
		  		              " from [dbo].[CONCEPTOS] where fecha between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE)"+
		  		              " and (eco not like '%M-%' " + 
		  		              "      and eco not like '%MA-%' " + 
		  		              "		 and eco not like '%MT-%' " + 
		  		              "		 and eco not like '%MR-%' " + 
		  		              "		 and eco not like '%ABSORMEX%' " + 
		  		              "		 and eco not like '%ATP%' " + 
		  		              "		 and eco not like '%MONTACARGAS%' "+
		  		              " ) " +
		  		              " order by eco asc";
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    try {
	    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
    	stmt = conn.prepareStatement(strLocalStmt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
    	rs = stmt.executeQuery(); 
    	while(rs.next()) {
    		Conceptos conceptos = new Conceptos(); 
    		conceptos.setFolio(rs.getString("FOLIO")); /** [FOLIO] [varchar](50) NULL,**/
    		conceptos.setProveedor(rs.getString("PROVEEDOR")); /** [PROVEEDOR] [varchar](80) NULL,**/
    		conceptos.setConcepto(rs.getString("CONCEPTO")); /** [CONCEPTO] [text] NULL,**/
    		conceptos.setEco(rs.getNString("ECO"));/** [ECO] [nvarchar](50) NULL, **/
    		conceptos.setCosto(rs.getDouble("COSTO"));/** [COSTO] [float] NULL,**/
    		conceptos.setFecha(rs.getTimestamp("FECHA"));/** [FECHA] [datetime] NULL, **/
    		conceptos.setGasto(rs.getString("GASTO"));
    		
    		listConceptos.add(conceptos); 
		 }
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
		
		return listConceptos; 
	
	}
	
	
	@Override
	public List<Conceptos> filtradoFacturasMontacargas(int pDesdeDay
			                                      , int pDesdeMonth
			                                      , int pDesdeYear
			                                      , int pHastaDay
			                                      , int pHastaMonth
			                                      , int pHastaYear) throws SQLException {
	
         List<Conceptos> listConceptos = new ArrayList<Conceptos>();
		
		String strLocalStmt = " SELECT [ECO] " + 
				              " ,[PROVEEDOR] " + 
	           			      " ,[CONCEPTO] " + 
				              " ,[COSTO] " + 
				              " ,[FECHA] " + 
				              " ,[FOLIO] " + 
				              " ,[GASTO] " + 
		  		              " from [dbo].[CONCEPTOS] where fecha between CAST('"+pDesdeYear+"-"+pDesdeMonth+"-"+pDesdeDay+"' AS DATE) and CAST('"+pHastaYear+"-"+pHastaMonth+"-"+pHastaDay+"' AS DATE)"+
		  		              " and (eco  like '%M-%' " + 
		  		              "      or eco like '%MA-%' " + 
		  		              "		 or eco like '%MT-%' " + 
		  		              "		 or eco like '%MR-%' " + 
		  		              "		 or eco like '%ABSORMEX%' " + 
		  		              "		 or eco like '%ATP%' " + 
		  		              "		 or eco like '%MONTACARGAS%' "+
		  		              " ) " +
		  		              " order by eco asc";
		Connection conn = null; 
		PreparedStatement stmt = null;
	    ResultSet rs = null; 
	    try {
	    conn =(null!=this.userConn)?this.userConn:ResourceManager.getConnection();
    	stmt = conn.prepareStatement(strLocalStmt,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY); 
    	rs = stmt.executeQuery(); 
    	while(rs.next()) {
    		Conceptos conceptos = new Conceptos(); 
    		conceptos.setFolio(rs.getString("FOLIO")); /** [FOLIO] [varchar](50) NULL,**/
    		conceptos.setProveedor(rs.getString("PROVEEDOR")); /** [PROVEEDOR] [varchar](80) NULL,**/
    		conceptos.setConcepto(rs.getString("CONCEPTO")); /** [CONCEPTO] [text] NULL,**/
    		conceptos.setEco(rs.getNString("ECO"));/** [ECO] [nvarchar](50) NULL, **/
    		conceptos.setCosto(rs.getDouble("COSTO"));/** [COSTO] [float] NULL,**/
    		conceptos.setFecha(rs.getTimestamp("FECHA"));/** [FECHA] [datetime] NULL, **/
    		conceptos.setGasto(rs.getString("GASTO"));
    		
    		listConceptos.add(conceptos); 
		 }
	    }
	    finally {
		   	ResourceManager.close(rs);
		   	ResourceManager.close(stmt);
		   	if(null==this.userConn) {
		  	ResourceManager.close(conn);
		   	}
		 }
		
		return listConceptos; 
	
	}

	

}
