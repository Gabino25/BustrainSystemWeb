package bs.catalogo.dto;

import java.io.Serializable;

import java.sql.Timestamp;
import bs.util.Utils;
import java.util.Base64;

public class CatTrabajadores implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;	
	private String nombre;
	private String direccion; 
	private String puesto;
	private String telefono;
	private String seguro;
	private Timestamp fecha;
	private String lic;
	private String fechaddMMyyyyV4;
	private byte[] foto;
	private String estado;
	private Timestamp licencia;
	private String licenciaddMMyyyyV2;
	private String licenciaddMMyyyyV4;
	private String licenciayyyyMMdd; 
	private String nota;
	private String fotoBase64;
	private Timestamp fechaingreso;
	private String fechaingresoddMMyyyyV4; 
	private String fechaingresoyyyyMMdd;
	private String area;
	private String gafete;
	private String fechaGafete;
	private String rfc;
	private String curp;
	private String estadocivil;
	private Double estatura;
	private Double peso;
	private Timestamp fechabaja;
	private String fechabajaddMMyyyyV4;  
	private String motivo;
	private String NEmpleado;
	private String licenciaF;
	private String fechaNacimiento;
	private String tcamisa;
	private String tplayera;
	private String tpantalon;
	private String tzapatos;
	private String reingreso;
	
	private String trabajadorU;
	private String cCamisa;
	private String cPlayera;
	private String cPantalon;
	private String cZapato;
	private String fechaA;
	
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getSeguro() {
		return seguro;
	}
	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		if(null!=foto) {
		System.out.println("foto.length>0:"+foto.length);
		if(foto.length>0) {
			this.setFotoBase64(new String(Base64.getEncoder().encode(foto)));
		}
     	}
		this.foto = foto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Timestamp getLicencia() {
		return licencia;
	}
	public void setLicencia(Timestamp licencia) {
		this.licencia = licencia;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public String getFechaddMMyyyyV4() {
		if(null!=this.fecha) {
			return Utils.getddMMyyyysdfInstanceV4().format(this.fecha); 
		}else {
		return fechaddMMyyyyV4;
		}
	}
	public void setFechaddMMyyyyV4(String fechaddMMyyyyV4) {
		this.fechaddMMyyyyV4 = fechaddMMyyyyV4;
	}
	
	public String getLicenciaddMMyyyyV2() {
		if(this.licencia!=null) {
		  return Utils.getddMMyyyysdfInstanceV2().format(licencia); 	
		}else {
		return licenciaddMMyyyyV2;
		}
	}
	public void setLicenciaddMMyyyyV2(String licenciaddMMyyyyV2) {
		this.licenciaddMMyyyyV2 = licenciaddMMyyyyV2;
	}
	
	public String getLicenciaddMMyyyyV4() {
		if(null!=this.licencia) {
			return Utils.getddMMyyyysdfInstanceV4().format(this.licencia);
		}else {
		return licenciaddMMyyyyV4;
		}
	}
	public void setLicenciaddMMyyyyV4(String licenciaddMMyyyyV4) {
		this.licenciaddMMyyyyV4 = licenciaddMMyyyyV4;
	}
	
	public Timestamp getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Timestamp fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getEstadocivil() {
		return estadocivil;
	}
	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}
	public Double getEstatura() {
		return estatura;
	}
	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Timestamp getFechabaja() {
		return fechabaja;
	}
	public void setFechabaja(Timestamp fechabaja) {
		this.fechabaja = fechabaja;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getFechaingresoddMMyyyyV4() {
		if(null!=this.fechaingreso) {
			return Utils.getddMMyyyysdfInstanceV4().format(this.fechaingreso); 
		}else {
		return fechaingresoddMMyyyyV4;
		}
	}
	public void setFechaingresoddMMyyyyV4(String fechaingresoddMMyyyyV4) {
		this.fechaingresoddMMyyyyV4 = fechaingresoddMMyyyyV4;
	}
	public String getFechabajaddMMyyyyV4() {
		if(null!=this.fechabaja) {
			return Utils.getddMMyyyysdfInstanceV4().format(this.fechabaja);
		}else {
		return fechabajaddMMyyyyV4;
		}
	}
	public void setFechabajaddMMyyyyV4(String fechabajaddMMyyyyV4) {
		this.fechabajaddMMyyyyV4 = fechabajaddMMyyyyV4;
	}
	public String getLicenciayyyyMMdd() {
		if(null!=this.licencia) {
		  return Utils.getyyyyMMddsdfInstance().format(licencia); 
		}else {
		  return licenciayyyyMMdd;
		}
	}
	public void setLicenciayyyyMMdd(String licenciayyyyMMdd) {
		this.licenciayyyyMMdd = licenciayyyyMMdd;
	}
	public String getFechaingresoyyyyMMdd() {
		if(null!=this.fechaingreso) {
		  return Utils.getyyyyMMddsdfInstance().format(fechaingreso);	
		}else {
		  return fechaingresoyyyyMMdd;
		}
	}
	public void setFechaingresoyyyyMMdd(String fechaingresoyyyyMMdd) {
		this.fechaingresoyyyyMMdd = fechaingresoyyyyMMdd;
	}
	public String getNEmpleado() {
		return NEmpleado;
	}
	public void setNEmpleado(String nEmpleado) {
		NEmpleado = nEmpleado;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTcamisa() {
		return tcamisa;
	}
	public void setTcamisa(String tcamisa) {
		this.tcamisa = tcamisa;
	}
	public String getTplayera() {
		return tplayera;
	}
	public void setTplayera(String tplayera) {
		this.tplayera = tplayera;
	}
	public String getTpantalon() {
		return tpantalon;
	}
	public void setTpantalon(String tpantalon) {
		this.tpantalon = tpantalon;
	}
	public String getTzapatos() {
		return tzapatos;
	}
	public void setTzapatos(String tzapatos) {
		this.tzapatos = tzapatos;
	}
	public String getReingreso() {
		return reingreso;
	}
	public void setReingreso(String reingreso) {
		this.reingreso = reingreso;
	}
	//---------UNIFORMES-----------------------------------------
	public String getcPlayera() {
		return cPlayera;
	}
	public void setcPlayera(String cPlayera) {
		this.cPlayera = cPlayera;
	}
	public String getTrabajadorU() {
		return trabajadorU;
	}
	public void setTrabajadorU(String trabajadorU) {
		this.trabajadorU = trabajadorU;
	}
	public String getcCamisa() {
		return cCamisa;
	}
	public void setcCamisa(String cCamisa) {
		this.cCamisa = cCamisa;
	}
	public String getcZapato() {
		return cZapato;
	}
	public void setcZapato(String cZapato) {
		this.cZapato = cZapato;
	}
	public String getcPantalon() {
		return cPantalon;
	}
	public void setcPantalon(String cPantalon) {
		this.cPantalon = cPantalon;
	}
	public String getFechaA() {
		return fechaA;
	}
	public void setFechaA(String fechaA) {
		this.fechaA = fechaA;
	}
	public String getLicenciaF() {
		return licenciaF;
	}
	public void setLicenciaF(String licenciaF) {
		this.licenciaF = licenciaF;
	}
	public String getGafete() {
		return gafete;
	}
	public void setGafete(String gafete) {
		this.gafete = gafete;
	}
	public String getFechaGafete() {
		return fechaGafete;
	}
	public void setFechaGafete(String fechaGafete) {
		this.fechaGafete = fechaGafete;
	}
	public String getLic() {
		return lic;
	}
	public void setLic(String lic) {
		this.lic = lic;
	}


	
	
}
