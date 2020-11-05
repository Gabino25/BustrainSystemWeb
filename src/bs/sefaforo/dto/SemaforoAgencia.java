package bs.sefaforo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import bs.util.Utils;

public class SemaforoAgencia {
	private String eco;
	private Timestamp servicio;
	private String servicioddMMyyyyV2;
	private double kilometrajemannto;
	private String kilometrajemanntoDecimalFormatV3; 
	private Timestamp fechakm;
	private String fechakmddMMyyyyV2;
	private double kilometraje;
	private String kilometrajeDecimalFormatV3;
	private double km_recorridos;
	private String km_recorridosDecimalFormatV3;
	private BigDecimal frecuenciamannto;
	private int dias_recorridos;
	private String categoriafiltro;
	private int meses_recorridos;
	private int km_por_dia; 
	private int frecuencia_dias;
	private Timestamp manntosiguiente;
	private String manntosiguienteddMMyyyyV2;
	
	public String getEco() {
		return eco;
	}
	public void setEco(String eco) {
		this.eco = eco;
	}
	public Timestamp getServicio() {
		return servicio;
	}
	public void setServicio(Timestamp servicio) {
		this.servicio = servicio;
	}
	public double getKilometrajemannto() {
		return kilometrajemannto;
	}
	public void setKilometrajemannto(double kilometrajemannto) {
		this.kilometrajemannto = kilometrajemannto;
	}
	public Timestamp getFechakm() {
		return fechakm;
	}
	public void setFechakm(Timestamp fechakm) {
		this.fechakm = fechakm;
	}
	public double getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(double kilometraje) {
		this.kilometraje = kilometraje;
	}
	public double getKm_recorridos() {
		return km_recorridos;
	}
	public void setKm_recorridos(double km_recorridos) {
		this.km_recorridos = km_recorridos;
	}
	public BigDecimal getFrecuenciamannto() {
		return frecuenciamannto;
	}
	public void setFrecuenciamannto(BigDecimal frecuenciamannto) {
		this.frecuenciamannto = frecuenciamannto;
	}
	public int getDias_recorridos() {
		return dias_recorridos;
	}
	public void setDias_recorridos(int dias_recorridos) {
		this.dias_recorridos = dias_recorridos;
	}
	public String getServicioddMMyyyyV2() {
		if(null!=servicio) {
		   return Utils.getddMMyyyysdfInstanceV2().format(servicio);	
		}else {
		return servicioddMMyyyyV2;
		}
	}
	public void setServicioddMMyyyyV2(String servicioddMMyyyyV2) {
		this.servicioddMMyyyyV2 = servicioddMMyyyyV2;
	}
	public String getKilometrajemanntoDecimalFormatV3() {
		return Utils.getenUSDecimalFormatInstanceV3().format(kilometrajemannto);
	}
	public void setKilometrajemanntoDecimalFormatV3(String kilometrajemanntoDecimalFormatV3) {
		this.kilometrajemanntoDecimalFormatV3 = kilometrajemanntoDecimalFormatV3;
	}
	public String getFechakmddMMyyyyV2() {
		 if(null!=this.fechakm) {
		  return Utils.getddMMyyyysdfInstanceV2().format(fechakm); 
		 }else {
		  return fechakmddMMyyyyV2;
		 }
	}
	public void setFechakmddMMyyyyV2(String fechakmddMMyyyyV2) {
		this.fechakmddMMyyyyV2 = fechakmddMMyyyyV2;
	}
	public String getKilometrajeDecimalFormatV3() {
		return Utils.getenUSDecimalFormatInstanceV3().format(kilometraje);
	}
	public void setKilometrajeDecimalFormatV3(String kilometrajeDecimalFormatV3) {
		this.kilometrajeDecimalFormatV3 = kilometrajeDecimalFormatV3;
	}
	public String getKm_recorridosDecimalFormatV3() {
		return Utils.getenUSDecimalFormatInstanceV3().format(km_recorridos);
	}
	public void setKm_recorridosDecimalFormatV3(String km_recorridosDecimalFormatV3) {
		this.km_recorridosDecimalFormatV3 = km_recorridosDecimalFormatV3;
	}
	public String getCategoriafiltro() {
		return categoriafiltro;
	}
	public void setCategoriafiltro(String categoriafiltro) {
		this.categoriafiltro = categoriafiltro;
	}
	public int getMeses_recorridos() {
		return meses_recorridos;
	}
	public void setMeses_recorridos(int meses_recorridos) {
		this.meses_recorridos = meses_recorridos;
	}
	public int getKm_por_dia() {
		return km_por_dia;
	}
	public void setKm_por_dia(int km_por_dia) {
		this.km_por_dia = km_por_dia;
	}
	public int getFrecuencia_dias() {
		return frecuencia_dias;
	}
	public void setFrecuencia_dias(int frecuencia_dias) {
		this.frecuencia_dias = frecuencia_dias;
	}
	public Timestamp getManntosiguiente() {
		return manntosiguiente;
	}
	public void setManntosiguiente(Timestamp manntosiguiente) {
		this.manntosiguiente = manntosiguiente;
	}
	public String getManntosiguienteddMMyyyyV2() {
		if(null!=this.manntosiguiente) {
		  return Utils.getddMMyyyysdfInstanceV2().format(manntosiguiente);	
		}else {
		return manntosiguienteddMMyyyyV2;
		}
	}
	public void setManntosiguienteddMMyyyyV2(String manntosiguienteddMMyyyyV2) {
		this.manntosiguienteddMMyyyyV2 = manntosiguienteddMMyyyyV2;
	}
}
