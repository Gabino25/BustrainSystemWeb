package bs.ap.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import bs.util.Utils;

public class Facturas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long numfact ; /* [numeric](18, 0) NULL, */
	private String proveedor ; /* [varchar](80) NULL,*/
	private BigDecimal orden ; /* [numeric](18, 0) NULL, */
	private String  folio ; /* [varchar](50) NULL,*/
	private double costo ; /* [float] NULL,*/
	private String costoenUSPatternV4;
	private Timestamp fecha ; /* [smalldatetime] NULL,*/
	private String fechaddMMyyyyV2; 
	private String fechayyyyMMdd;
	private String  tipo; /*[nvarchar](50) NULL,*/
	private String  usuario ; /* [nvarchar](50) NULL, */
	private String  estado; /*[varchar](50) NULL*/
	
	public long getNumfact() {
		return numfact;
	}
	public void setNumfact(long numfact) {
		this.numfact = numfact;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public BigDecimal getOrden() {
		return orden;
	}
	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCostoenUSPatternV3() {
		return Utils.getenUSDecimalFormatInstanceV4().format(this.costo);
	}
	public void setCostoenUSPatternV3(String costoenUSPatternV4) {
		this.costoenUSPatternV4 = costoenUSPatternV4;
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
		if(null!=fecha) {
			return Utils.getyyyyMMddsdfInstance().format(fecha);
		}else {
		return fechayyyyMMdd;
		}
	}
	public void setFechayyyyMMdd(String fechayyyyMMdd) {
		this.fechayyyyMMdd = fechayyyyMMdd;
	}
	

}
