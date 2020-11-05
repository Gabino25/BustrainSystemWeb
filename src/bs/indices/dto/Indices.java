package bs.indices.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Indices implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numfact; /** ] [ int] null, **/
	private int numfalla; /** ] [ int] null, **/
	private BigDecimal numvale; /** ] [ numeric](18, 0) null, **/
	private int numasignacion; /** ] [ int] null, **/
	private int numeroruta; /** ] [ int] null, **/
	private int numfactserv; /** ] [ int] null, **/
	private int foliollantas; /** ] [ int] null, **/
	private int foliomovllantas; /** ] [ int] null, **/
	private BigDecimal claveseguridad; /** ] [ nchar](10) null, **/
	private BigDecimal claveborrar; /** ] [ nvarchar](50) null, **/
	private int numproveedor; /** ] [ int] null **/
	
	public int getNumfact() {
		return numfact;
	}
	public void setNumfact(int numfact) {
		this.numfact = numfact;
	}
	public int getNumfalla() {
		return numfalla;
	}
	public void setNumfalla(int numfalla) {
		this.numfalla = numfalla;
	}
	public BigDecimal getNumvale() {
		return numvale;
	}
	public void setNumvale(BigDecimal numvale) {
		this.numvale = numvale;
	}
	public int getNumasignacion() {
		return numasignacion;
	}
	public void setNumasignacion(int numasignacion) {
		this.numasignacion = numasignacion;
	}
	public int getNumeroruta() {
		return numeroruta;
	}
	public void setNumeroruta(int numeroruta) {
		this.numeroruta = numeroruta;
	}
	public int getNumfactserv() {
		return numfactserv;
	}
	public void setNumfactserv(int numfactserv) {
		this.numfactserv = numfactserv;
	}
	public int getFoliollantas() {
		return foliollantas;
	}
	public void setFoliollantas(int foliollantas) {
		this.foliollantas = foliollantas;
	}
	public int getFoliomovllantas() {
		return foliomovllantas;
	}
	public void setFoliomovllantas(int foliomovllantas) {
		this.foliomovllantas = foliomovllantas;
	}
	public BigDecimal getClaveseguridad() {
		return claveseguridad;
	}
	public void setClaveseguridad(BigDecimal claveseguridad) {
		this.claveseguridad = claveseguridad;
	}
	public BigDecimal getClaveborrar() {
		return claveborrar;
	}
	public void setClaveborrar(BigDecimal claveborrar) {
		this.claveborrar = claveborrar;
	}
	public int getNumproveedor() {
		return numproveedor;
	}
	public void setNumproveedor(int numproveedor) {
		this.numproveedor = numproveedor;
	}
	
}
