package bs.ap.dto;

import java.io.Serializable;

/**
 * Cuentas Operacion porque 
 * tambien existen cuentas para Nomina 
 * y solo se quieren cuentas para compras
 * @author Lenovo02
 *
 */

public class CuentasOperacion implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
private int id;
private String cuenta; 
private String nombrecuenta;
private String descripcioncta; 
  
public String getCuenta() {
	return cuenta;
}
public void setCuenta(String cuenta) {
	this.cuenta = cuenta;
}
public String getNombrecuenta() {
	return nombrecuenta;
}
public void setNombrecuenta(String nombrecuenta) {
	this.nombrecuenta = nombrecuenta;
}
public String getDescripcioncta() {
	if(null!=this.cuenta&&null!=this.nombrecuenta) {
	  return this.cuenta+" "+this.nombrecuenta;
	}else {
	  return descripcioncta;
	}
}
public void setDescripcioncta(String descripcioncta) {
	this.descripcioncta = descripcioncta;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

}
