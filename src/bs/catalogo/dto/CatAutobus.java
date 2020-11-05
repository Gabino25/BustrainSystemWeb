package bs.catalogo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import bs.util.Utils;

public class CatAutobus implements Serializable {

	private static final long serialVersionUID = 1L;

	private int folio;
	private String eco;
	private String combustible;
	private String empresa;
	private String estado;
	private Double kilometraje;
	private java.sql.Timestamp fechakm;
	private String fechakmddMMyyyy;
	private String tipo;
	private String serie;
	private String carroceria;
	private String modelo;
	private String motor;
	private java.sql.Timestamp servicio;
	private String servicioddMMyyyy;
	private String servicioyyyyMMdd;
	private BigDecimal kilometrajemantto;
	private String poliza;
	private java.sql.Timestamp vence;
	private String venceddMMyyyy;
	private String venceyyyyMMdd;
	private BigDecimal frecuenciamantto;
	private String aseguradora;
	private Double kmsrecorridos;
	private Double diasrecorridos;
	private Double kmdia;
	private String Kmhrs;
	private Double diasfrecuencia;
	private Double diassiguiente;
	private int agencia;
	private String usuario;
	private java.sql.Timestamp fechaalta;
	private String descripcion;
	/********************** Nuevas Columnas *************************/
	private int pasajeros;
	private String placas;
	private String concesion;
	private String clavevehicular;
	private java.sql.Timestamp vigultimoreferendo;
	private String vigultimoreferendoyyyyMMdd;
	private String tipounidad;
	private String asegbroker;
	private int inicio;
	private int endoso;
	private String cobertura;
	private String hospital;
	private String asegservicio;
	private String nota;

	private String categoria1;
	private String categoria2;

	public String getEco() {
		return eco;
	}

	public void setEco(String eco) {
		this.eco = eco;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Double kilometraje) {
		this.kilometraje = kilometraje;
	}

	public java.sql.Timestamp getFechakm() {
		return fechakm;
	}

	public void setFechakm(java.sql.Timestamp fechakm) {
		this.fechakm = fechakm;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCarroceria() {
		return carroceria;
	}

	public void setCarroceria(String carroceria) {
		this.carroceria = carroceria;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public java.sql.Timestamp getServicio() {
		return servicio;
	}

	public void setServicio(java.sql.Timestamp servicio) {
		this.servicio = servicio;
	}

	public BigDecimal getKilometrajemantto() {
		return kilometrajemantto;
	}

	public void setKilometrajemantto(BigDecimal kilometrajemantto) {
		this.kilometrajemantto = kilometrajemantto;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public java.sql.Timestamp getVence() {
		return vence;
	}

	public void setVence(java.sql.Timestamp vence) {
		this.vence = vence;
	}

	public BigDecimal getFrecuenciamantto() {
		return frecuenciamantto;
	}

	public void setFrecuenciamantto(BigDecimal frecuenciamantto) {
		this.frecuenciamantto = frecuenciamantto;
	}

	public String getAseguradora() {
		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	public Double getKmsrecorridos() {
		return kmsrecorridos;
	}

	public void setKmsrecorridos(Double kmsrecorridos) {
		this.kmsrecorridos = kmsrecorridos;
	}

	public Double getDiasrecorridos() {
		return diasrecorridos;
	}

	public void setDiasrecorridos(Double diasrecorridos) {
		this.diasrecorridos = diasrecorridos;
	}

	public Double getKmdia() {
		return kmdia;
	}

	public void setKmdia(Double kmdia) {
		this.kmdia = kmdia;
	}

	public Double getDiasfrecuencia() {
		return diasfrecuencia;
	}

	public void setDiasfrecuencia(Double diasfrecuencia) {
		this.diasfrecuencia = diasfrecuencia;
	}

	public Double getDiassiguiente() {
		return diassiguiente;
	}

	public void setDiassiguiente(Double diassiguiente) {
		this.diassiguiente = diassiguiente;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public java.sql.Timestamp getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(java.sql.Timestamp fechaalta) {
		this.fechaalta = fechaalta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getFechakmddMMyyyy() {
		if (this.fechakm != null) {
			return Utils.getddMMyyyysdfInstance().format(this.fechakm);
		} else {
			return fechakmddMMyyyy;
		}
	}

	public void setFechakmddMMyyyy(String fechakmddMMyyyy) {
		this.fechakmddMMyyyy = fechakmddMMyyyy;
	}

	public String getServicioddMMyyyy() {
		if (this.servicio != null) {
			return Utils.getddMMyyyysdfInstance().format(this.servicio);
		} else {
			return servicioddMMyyyy;
		}
	}

	public void setServicioddMMyyyy(String servicioddMMyyyy) {
		this.servicioddMMyyyy = servicioddMMyyyy;
	}

	public String getVenceddMMyyyy() {
		if (this.vence != null) {
			return Utils.getddMMyyyysdfInstance().format(this.vence);
		} else {
			return venceddMMyyyy;
		}
	}

	public void setVenceddMMyyyy(String venceddMMyyyy) {
		this.venceddMMyyyy = venceddMMyyyy;
	}

	public String getServicioyyyyMMdd() {
		if (null != servicio) {
			return Utils.getyyyyMMddsdfInstance().format(servicio);
		} else {
			return servicioyyyyMMdd;
		}
	}

	public void setServicioyyyyMMdd(String servicioyyyyMMdd) {
		this.servicioyyyyMMdd = servicioyyyyMMdd;
	}

	public String getVenceyyyyMMdd() {
		if (null != this.vence) {
			return Utils.getyyyyMMddsdfInstance().format(this.vence);
		} else {
			return venceyyyyMMdd;
		}
	}

	public void setVenceyyyyMMdd(String venceyyyyMMdd) {
		this.venceyyyyMMdd = venceyyyyMMdd;
	}

	/********************** Nuevas Columnas *************************/

	public int getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(int pasajeros) {
		this.pasajeros = pasajeros;
	}

	public String getPlacas() {
		return placas;
	}

	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public String getConcesion() {
		return concesion;
	}

	public void setConcesion(String concesion) {
		this.concesion = concesion;
	}

	public String getClavevehicular() {
		return clavevehicular;
	}

	public void setClavevehicular(String clavevehicular) {
		this.clavevehicular = clavevehicular;
	}

	public java.sql.Timestamp getVigultimoreferendo() {
		return vigultimoreferendo;
	}

	public void setVigultimoreferendo(java.sql.Timestamp vigultimoreferendo) {
		this.vigultimoreferendo = vigultimoreferendo;
	}

	public String getTipounidad() {
		return tipounidad;
	}

	public void setTipounidad(String tipounidad) {
		this.tipounidad = tipounidad;
	}

	public String getAsegbroker() {
		return asegbroker;
	}

	public void setAsegbroker(String asegbroker) {
		this.asegbroker = asegbroker;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getEndoso() {
		return endoso;
	}

	public void setEndoso(int endoso) {
		this.endoso = endoso;
	}

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getAsegservicio() {
		return asegservicio;
	}

	public void setAsegservicio(String asegservicio) {
		this.asegservicio = asegservicio;
	}

	public String getVigultimoreferendoyyyyMMdd() {
		if (null != this.vigultimoreferendo) {
			return Utils.getyyyyMMddsdfInstance().format(vigultimoreferendo);
		} else {
			return vigultimoreferendoyyyyMMdd;
		}
	}

	public void setVigultimoreferendoyyyyMMdd(String vigultimoreferendoyyyyMMdd) {
		this.vigultimoreferendoyyyyMMdd = vigultimoreferendoyyyyMMdd;
	}

	public String getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(String categoria1) {
		this.categoria1 = categoria1;
	}

	public String getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}

	public String getKmhrs() {
		return Kmhrs;
	}

	public void setKmhrs(String kmhrs) {
		Kmhrs = kmhrs;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}
