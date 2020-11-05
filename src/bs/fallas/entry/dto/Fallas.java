package bs.fallas.entry.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import bs.util.Utils;

public class Fallas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long numero; /**] [ numeric](18, 0) null, **/
	private String eco; /**] [ nvarchar](50) null, **/
	private String reporta; /**] [ nvarchar](50) null, **/
	private double kilometraje; /**] [ float] null, **/
	private String kilometrajenf;
	private String descripcion; /**] [ text] null, **/
	private Timestamp fecharep; /**] [ datetime] null, **/
    private String fecharepyyyyMMdd; 
    private String fecharepddMMMyyyy;
	private Timestamp fecha; /**] [ datetime] null, **/
	private String fechaddMMyyyyV2;
	private String fechayyyyMMdd; 
	private String horaHHmm;
	private Timestamp hora; /**] [ datetime] null, **/
	private String horav2;
	private String estado; /**] [ nvarchar](50) null, **/
	private String reparacion; /**] [ text] null, **/
	private BigDecimal costo; /**] [ numeric](18, 0) null, **/
	private String usuario; /**] [ nvarchar](50) null, **/
	private Timestamp fechaalta; /**] [ smalldatetime] null, **/
	private String tipo; /**] [ nvarchar](50) null **/
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getEco() {
		return eco;
	}
	public void setEco(String eco) {
		this.eco = eco;
	}
	public String getReporta() {
		return reporta;
	}
	public void setReporta(String reporta) {
		this.reporta = reporta;
	}
	public double getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(double kilometraje) {
		this.kilometraje = kilometraje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Timestamp getFecharep() {
		return fecharep;
	}
	public void setFecharep(Timestamp fecharep) {
		this.fecharep = fecharep;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Timestamp getHora() {
		return hora;
	}
	public void setHora(Timestamp hora) {
		this.hora = hora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getReparacion() {
		return reparacion;
	}
	public void setReparacion(String reparacion) {
		this.reparacion = reparacion;
	}
	public BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public String getFechaddMMyyyyV2() {
		if(null!=this.fecha) {
		  return Utils.getddMMyyyysdfInstanceV2().format(this.fecha); 	
		}else {
		return fechaddMMyyyyV2;
		}
	}
	public void setFechaddMMyyyyV2(String fechaddMMyyyyV2) {
		this.fechaddMMyyyyV2 = fechaddMMyyyyV2;
	}
	public String getFecharepyyyyMMdd() {
		if(null!=this.fecharep) {
		 return Utils.getyyyyMMddsdfInstance().format(this.fecharep); 
		}else {
		return fecharepyyyyMMdd;
		}
	}
	public void setFecharepyyyyMMdd(String fecharepyyyyMMdd) {
		this.fecharepyyyyMMdd = fecharepyyyyMMdd;
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
	public String getFecharepddMMMyyyy() {
		if(null!=fecharep) {
			return Utils.getddMMMyyyysdfInstance().format(fecharep);
			
		}
		else
		{
			return fecharepddMMMyyyy;
		}
		
	}
	public void setFecharepddMMMyyyy(String fecharepddMMMyyyy) {
		this.fecharepddMMMyyyy = fecharepddMMMyyyy;
	}
	public String getKilometrajenf() {
		return Utils.getenUSFormatInstance().format(kilometraje);
	}
	public void setKilometrajenf(String kilometrajenf) {
		this.kilometrajenf = kilometrajenf;
	}
	public String getHorav2() {
		return horav2;
	}
	public void setHorav2(String horav2) {
		this.horav2 = horav2;
	}
	
	
//	public String getHoraHHmm() {
//		if(null!=horav2)
//		{
//			return Utils.gethhmmsdfInstance().format(horav2);
//		}
//		else {
//		return horaHHmm;
//		}
//	}
//	public void setHoraHHmm(String horaHHmm) {
//		this.horaHHmm = horaHHmm;
//	}
	
}



