package bs.llantas.entry.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import bs.util.Utils;

public class MovimientosLlantas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int folio;  /**[int] null, **/
	private String nombre;  /**[varchar](50) null, **/
	private String operador;  /**[varchar](50) null, **/
	private Timestamp fecha;  /**[datetime] null, **/
	private String fechaddMMyyyyV2;
	private String fechayyyyMMdd;
	private String posicion;  /**[varchar](50) null, **/
	private String unidad;  /**[varchar](50) null, **/
	private String presion;  /**[varchar](50) null, **/
	private String observaciones;  /**[varchar](max) null, **/
	private String tipo_mov;  /**[nvarchar](50) null, **/
	private String kilometraje;  /**[nchar](10) null, **/
	private String usuario;  /**[nvarchar](50) null, **/
	private Timestamp fechacaptura;  /**[datetime] null, **/
	private String fechacapturaddMMyyyyV3;
	private String presionanterior;  /**[nvarchar](50) null, **/
	private float costo;  /**[real] null, **/
	private String nota;  /**[text] null, **/
	private float kmrecorrido;  /**[real] null, **/
	private float prof;  /**[real] null **/
	
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getPresion() {
		return presion;
	}
	public void setPresion(String presion) {
		this.presion = presion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getTipo_mov() {
		return tipo_mov;
	}
	public void setTipo_mov(String tipo_mov) {
		this.tipo_mov = tipo_mov;
	}
	public String getKilometraje() {
		if(null!=this.kilometraje) {
		  return this.kilometraje.replaceAll(" ", ""); 
		}else {
		return kilometraje;
		}
	}
	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Timestamp getFechacaptura() {
		return fechacaptura;
	}
	public void setFechacaptura(Timestamp fechacaptura) {
		this.fechacaptura = fechacaptura;
	}
	public String getPresionanterior() {
		return presionanterior;
	}
	public void setPresionanterior(String presionanterior) {
		this.presionanterior = presionanterior;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public float getKmrecorrido() {
		return kmrecorrido;
	}
	public void setKmrecorrido(float kmrecorrido) {
		this.kmrecorrido = kmrecorrido;
	}
	public float getProf() {
		return prof;
	}
	public void setProf(float prof) {
		this.prof = prof;
	}
	
	public String getFechaddMMyyyyV2() {
		if(this.fecha!=null) {
		return Utils.getddMMyyyysdfInstanceV2().format(this.fecha);
		}else {
		return fechaddMMyyyyV2;	
		}
	}
	
	public void setFechaddMMyyyyV2(String fechaddMMyyyV2) {
		this.fechaddMMyyyyV2 = fechaddMMyyyV2;
	}
	public String getFechacapturaddMMyyyyV3() {
		if(this.fechacaptura!=null) {
		  return Utils.getddMMyyyysdfInstanceV3().format(this.fechacaptura);
		}else {
			return fechacapturaddMMyyyyV3;
		}
	}
	public void setFechacapturaddMMyyyyV3(String fechacapturaddMMyyyyV3) {
		this.fechacapturaddMMyyyyV3 = fechacapturaddMMyyyyV3;
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
