package bs.vales.combust.entry.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import bs.util.Utils;

import java.math.BigDecimal;

public class Diesel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	private Timestamp fecha;
	private String fechaC;
	private String fechaddMMyyyyV2; 
	private long nota;
	private String unidad;
	private BigDecimal total;
	private String enUStotal;
	private double  litros;
	private String enUSV2litros;
	private double kilometraje;
	private BigDecimal hora;
	private Timestamp fechafactura;
	private String gasolinera;
	private String operador;
	private String combustible;
	private double rendimiento;
	private double kmanterior;
	private double kmsrecorridos;
	private String usuario;
	private String empresa;
	
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public long getNota() {
		return nota;
	}
	public void setNota(long nota) {
		this.nota = nota;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public double getLitros() {
		return litros;
	}
	public void setLitros(double litros) {
		this.litros = litros;
	}
	public double getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(double kilometraje) {
		this.kilometraje = kilometraje;
	}
	public BigDecimal getHora() {
		return hora;
	}
	public void setHora(BigDecimal hora) {
		this.hora = hora;
	}
	public Timestamp getFechafactura() {
		return fechafactura;
	}
	public void setFechafactura(Timestamp fechafactura) {
		this.fechafactura = fechafactura;
	}
	public String getGasolinera() {
		return gasolinera;
	}
	public void setGasolinera(String gasolinera) {
		this.gasolinera = gasolinera;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getCombustible() {
		return combustible;
	}
	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}
	public double getRendimiento() {
		return rendimiento;
	}
	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}
	public double getKmanterior() {
		return kmanterior;
	}
	public void setKmanterior(double kmanterior) {
		this.kmanterior = kmanterior;
	}
	public double getKmsrecorridos() {
		return kmsrecorridos;
	}
	public void setKmsrecorridos(double kmsrecorridos) {
		this.kmsrecorridos = kmsrecorridos;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getFechaddMMyyyyV2() {
		if(this.fecha!=null) {
			return Utils.getddMMyyyysdfInstanceV2().format(this.fecha);
		}else {
			return fechaddMMyyyyV2;	
		}
		
	}
	public void setFechaddMMyyyyV2(String fechaddMMyyyyV2) {
		this.fechaddMMyyyyV2 = fechaddMMyyyyV2;
	}
	
	public String getEnUStotal() {
		if(null!=total) {
		  return "$"+Utils.getenUSDecimalFormatInstance().format(total); 
		}
		return enUStotal;
	}
	public void setEnUStotal(String enUStotal) {
		this.enUStotal = enUStotal;
	}
	public String getEnUSV2litros() {
	 return Utils.getenUSDecimalFormatInstanceV2().format(litros); 
	}
	public void setEnUSV2litros(String enUSV2litros) {
		this.enUSV2litros = enUSV2litros;
	}
	public String getFechaC() {
		return fechaC;
	}
	public void setFechaC(String fechaC) {
		this.fechaC = fechaC;
	}
	
}
