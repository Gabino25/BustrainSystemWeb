package bs.catalogo.dto;

import java.io.Serializable;

public class CatCelulares implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String trabajador;
	private String fecha; 
	private String marca;
	private String modelo;
	private String numeroSerie;
	private String numero;
	private String nota;
	
	public String getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(String trabajador) {
		this.trabajador = trabajador;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}

}
