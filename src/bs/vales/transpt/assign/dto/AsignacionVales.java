package bs.vales.transpt.assign.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import bs.util.Utils;

public class AsignacionVales implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long numero;
	private String nombre;
	private long folioinicial;
	private long foliofinal;
	private Timestamp fecha;
	private String fechaddMMyyyyV2;
	
	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getFolioinicial() {
		return folioinicial;
	}

	public void setFolioinicial(long folioinicial) {
		this.folioinicial = folioinicial;
	}

	public long getFoliofinal() {
		return foliofinal;
	}

	public void setFoliofinal(long foliofinal) {
		this.foliofinal = foliofinal;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public AsignacionVales()
	{
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

	
}
