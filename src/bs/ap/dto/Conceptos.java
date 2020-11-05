package bs.ap.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import bs.util.Utils;

public class Conceptos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long numconcepto; /* [numeric](18, 0) NULL,*/
	private String folio; /* [varchar](50) NULL, */
	private String proveedor; /* [varchar](80) NULL, */
	private String concepto ; /*[text] NULL, */
	private long numfact; /* [numeric](18, 0) NULL,*/
	private String eco; /* [nvarchar](50) NULL,*/
	private double costo; /* [float] NULL, */
	private Timestamp fecha; /* [datetime] NULL,*/
	private String fechaddMMyyyyV2;
	private String fechayyyyMMdd;
	private String gasto; /* [varchar](50) NULL, */
	private Timestamp fechaalta; /* [datetime] NULL,*/
	private String tipo ; /*[varchar](50) NULL,*/
	private BigDecimal orden; /* [numeric](18, 0) NULL,*/
	private String usuario; /* [nvarchar](50) NULL */
	
	public long getNumconcepto() {
		return numconcepto;
	}
	public void setNumconcepto(long numconcepto) {
		this.numconcepto = numconcepto;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		if(null!=concepto) {
		this.concepto = concepto.toUpperCase();
		}
	}
	public long getNumfact() {
		return numfact;
	}
	public void setNumfact(long numfact) {
		this.numfact = numfact;
	}
	public String getEco() {
		return eco;
	}
	public void setEco(String eco) {
		this.eco = eco;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getGasto() {
		return gasto;
	}
	public void setGasto(String gasto) {
		this.gasto = gasto;
	}
	public Timestamp getFechaalta() {
		return fechaalta;
	}
	public void setFechaalta(Timestamp fechaalta) {
		this.fechaalta = fechaalta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getOrden() {
		return orden;
	}
	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFechaddMMyyyyV2() {
		if(null!=fecha) {
		  return Utils.getddMMyyyysdfInstanceV2().format(fecha);	
		}else {
		return fechaddMMyyyyV2;
		}
	}
	public void setFechaddMMyyyyV2(String fechaddMMyyyyV2) {
		this.fechaddMMyyyyV2 = fechaddMMyyyyV2;
	}
	public String getFechayyyyMMdd() {
		if(null!=this.fecha) {
		 return Utils.getyyyyMMddsdfInstance().format(this.fecha);	
		}else {
		return fechayyyyMMdd;
		}
	}
	public void setFechayyyyMMdd(String fechayyyyMMdd) {
		this.fechayyyyMMdd = fechayyyyMMdd;
	}

}
