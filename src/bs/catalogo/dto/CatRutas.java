package bs.catalogo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import bs.util.Utils;

public class CatRutas implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private int numero;
private String clave;
private String cliente; 
private String ruta; 
private float costo;
private Timestamp fecha ;
private String fechaddMMyyyyV2;
private String fechayyyyMMdd;
private String descripcion; 
private String tipounidad; 
private String tipopago; 
private Timestamp horainicio;
private Timestamp horafin;
private BigDecimal estado;

public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
}
public String getClave() {
    if(null!=clave)
	clave = clave.replaceAll(" ", ""); 
	return clave;
}
public void setClave(String clave) {
	this.clave = clave;
}
public String getCliente() {
	return cliente;
}
public void setCliente(String cliente) {
	this.cliente = cliente;
}
public String getRuta() {
	return ruta;
}
public void setRuta(String ruta) {
	this.ruta = ruta;
}
public float getCosto() {
	return costo;
}
public void setCosto(float costo) {
	this.costo = costo;
}
public Timestamp getFecha() {
	return fecha;
}
public void setFecha(Timestamp fecha) {
	this.fecha = fecha;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getTipounidad() {
	return tipounidad;
}
public void setTipounidad(String tipounidad) {
	this.tipounidad = tipounidad;
}
public String getTipopago() {
	return tipopago;
}
public void setTipopago(String tipopago) {
	this.tipopago = tipopago;
}
public Timestamp getHorainicio() {
	return horainicio;
}
public void setHorainicio(Timestamp horainicio) {
	this.horainicio = horainicio;
}
public Timestamp getHorafin() {
	return horafin;
}
public void setHorafin(Timestamp horafin) {
	this.horafin = horafin;
}
public BigDecimal getEstado() {
	return estado;
}
public void setEstado(BigDecimal estado) {
	this.estado = estado;
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
