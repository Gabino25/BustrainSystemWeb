package bs.admin.entry.dto;

import java.io.Serializable;

public class Pantallas implements Serializable {
  /**
	 * 
	 */
public Pantallas() {
	
}
public Pantallas(String pNombre) {
	this.nombre = pNombre; 
}
private static final long serialVersionUID = 1L;
private String nombre;

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
}
