package bs.admin.entry.dto;

import java.io.Serializable;

public class Permisos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; 
	private String usuario;
	private String pantalla;
	private String permiso;
	private String nivel; 
	private String niveldesc; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPantalla() {
		return pantalla;
	}
	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}
	public String getPermiso() {
		return permiso;
	}
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getNiveldesc() {
		if(null!=nivel) {
		  if("0".equals(nivel)) {
			  return "CREAR MODIFICAR LECTURA";
		  }else if("1".equals(nivel)) {
			  return "LECTURA";
		  }
		}
		return niveldesc;
	}
	public void setNiveldesc(String niveldesc) {
		this.niveldesc = niveldesc;
	}
	
	
}
