package bs.llantas.entry.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import bs.util.Utils;

public class Llantas implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int folio; /**numeric](18, 0) null,**/
	private String nombre; /**varchar](50) null,**/
	private String medida; /**varchar](50) null,**/
	private String marca; /**varchar](50) null,**/
	private double costo; /**float] null,**/
	private String enUScosto;
	private int profundidad_inicial; /**int] null,**/
	private int profundidad_actual; /**int] null,**/
	private Timestamp fecha_alta; /**datetime] null,**/
	private String fecha_altaddMMyyyyV2;
	private String fecha_altayyyyMMdd;
	private Timestamp fecha_revision; /**datetime] null,**/
	private String fecha_revisionddMMyyyyV2;
	private String estado; /**varchar](50) null,**/
	private String unidad; /**varchar](50) null,**/
	private String posicion; /**varchar](50) null,**/
	private String presion; /**varchar](50) null,**/
	private String modelo; /**nvarchar](50) null,**/
	private float kmacumulado; /**real] null,**/
	private String nota; /**nvarchar](max) null,**/
	private float costoacumulado; /**real] null,**/
	private String usuario; /**nvarchar](50) null,**/
	private float kminicial; /**real] null,**/
	private float kmfinal; /**real] null,**/
	private String tipo; /**nchar](10) null,**/
	private Timestamp fechacaptura; /**datetime] null,**/
	private String operador; /**varchar](50) NULL**/
	
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
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public int getProfundidad_inicial() {
		return profundidad_inicial;
	}
	public void setProfundidad_inicial(int profundidad_inicial) {
		this.profundidad_inicial = profundidad_inicial;
	}
	public int getProfundidad_actual() {
		return profundidad_actual;
	}
	public void setProfundidad_actual(int profundidad_actual) {
		this.profundidad_actual = profundidad_actual;
	}
	public Timestamp getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Timestamp getFecha_revision() {
		return fecha_revision;
	}
	public void setFecha_revision(Timestamp fecha_revision) {
		this.fecha_revision = fecha_revision;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getPresion() {
		return presion;
	}
	public void setPresion(String presion) {
		this.presion = presion;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public float getKmacumulado() {
		return kmacumulado;
	}
	public void setKmacumulado(float kmacumulado) {
		this.kmacumulado = kmacumulado;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public float getCostoacumulado() {
		return costoacumulado;
	}
	public void setCostoacumulado(float costoacumulado) {
		this.costoacumulado = costoacumulado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public float getKminicial() {
		return kminicial;
	}
	public void setKminicial(float kminicial) {
		this.kminicial = kminicial;
	}
	public float getKmfinal() {
		return kmfinal;
	}
	public void setKmfinal(float kmfinal) {
		this.kmfinal = kmfinal;
	}
	public String getTipo() {
		if(null!=tipo) {
			tipo = tipo.replaceAll(" ", "");
		}
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Timestamp getFechacaptura() {
		return fechacaptura;
	}
	public void setFechacaptura(Timestamp fechacaptura) {
		this.fechacaptura = fechacaptura;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getFecha_altaddMMyyyyV2() {
		if(this.fecha_alta!=null) {
		 return Utils.getddMMyyyysdfInstanceV2().format(this.fecha_alta);	
		}else {
		return fecha_altaddMMyyyyV2;
		}
	}
	public void setFecha_altaddMMyyyyV2(String fecha_altaddMMyyyyV2) {
		this.fecha_altaddMMyyyyV2 = fecha_altaddMMyyyyV2;
	}
	public String getFecha_revisionddMMyyyyV2() {
		if(this.fecha_revision!=null) {
		 return Utils.getddMMyyyysdfInstanceV2().format(this.fecha_revision);	
		}else {
		return fecha_revisionddMMyyyyV2;
		}
	}
	public void setFecha_revisionddMMyyyyV2(String fecha_revisionddMMyyyyV2) {
		this.fecha_revisionddMMyyyyV2 = fecha_revisionddMMyyyyV2;
	}
	public String getFecha_altayyyyMMdd() {
		if(null!=this.fecha_alta) {
			return Utils.getyyyyMMddsdfInstance().format(this.fecha_alta);
		}
		return fecha_altayyyyMMdd;
	}
	public void setFecha_altayyyyMMdd(String fecha_altayyyyMMdd) {
		this.fecha_altayyyyMMdd = fecha_altayyyyMMdd;
	}
	public String getEnUScosto() {
		
		return Utils.getenUSDecimalFormatInstance().format(costo);
	}
	public void setEnUScosto(String enUScosto) {
		this.enUScosto = enUScosto;
	}
	
}
