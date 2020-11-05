package bs.vales.transpt.entry.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;

import bs.util.Utils;

public class Vales implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long numvale;
	private long folio; 
	private Timestamp fecha;
	private String fechaddMMyyyyV2;
	private String fechayyyyMMdd;
	private String observaciones; 
	private Double costo; 
	private String enUSCosto;
	private String ruta; 
	private String operador; 
	private String eco; 
	private String cliente; 
	private BigDecimal kminicial; 
	private String horainicial;
	private String hhmmssahorainicial;
	private BigDecimal kmfinal; 
	private String horafinal;
	private String hhmmssahorafinal;
	private String tipoviaje; 
	private String tipocobro; 
	private String centrocostos; 
	private String factura; 
	private String usuario; 
	private Timestamp fechacaptura; 
	private String fechacapturaddMMyyyV3;
	private String tipounidad; 
	
	public long getNumvale() {
		return numvale;
	}
	public void setNumvale(long numvale) {
		this.numvale = numvale;
	}
	public long getFolio() {
		return folio;
	}
	public void setFolio(long folio) {
		this.folio = folio;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		if(!"".equals(observaciones)&&null!=observaciones) {
		  this.observaciones = observaciones.toUpperCase();
		}else {
	      this.observaciones = observaciones;
		}
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getEco() {
		return eco;
	}
	public void setEco(String eco) {
		this.eco = eco;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getKminicial() {
		return kminicial;
	}
	public void setKminicial(BigDecimal kminicial) {
		this.kminicial = kminicial;
	}
	public String getHorainicial() {
		return horainicial;
	}
	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}
	public BigDecimal getKmfinal() {
		return kmfinal;
	}
	public void setKmfinal(BigDecimal kmfinal) {
		this.kmfinal = kmfinal;
	}
	public String getHorafinal() {
		return horafinal;
	}
	public void setHorafinal(String horafinal) {
		this.horafinal = horafinal;
	}
	public String getTipoviaje() {
		return tipoviaje;
	}
	public void setTipoviaje(String tipoviaje) {
		this.tipoviaje = tipoviaje;
	}
	public String getTipocobro() {
		return tipocobro;
	}
	public void setTipocobro(String tipocobro) {
		this.tipocobro = tipocobro;
	}
	public String getCentrocostos() {
		return centrocostos;
	}
	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Timestamp getFechacaptura() {
		return fechacaptura;
	}
	public void setFechacaptura(Timestamp fechacaptura) {
		this.fechacaptura = fechacaptura;
	}
	public String getTipounidad() {
		return tipounidad;
	}
	public void setTipounidad(String tipounidad) {
		this.tipounidad = tipounidad;
	}
	public String getFechaddMMyyyyV2() {
		if(this.fecha!=null) {
		return  Utils.getddMMyyyysdfInstanceV2().format(this.fecha);
		}else {
		 return fechaddMMyyyyV2; 	
		}
	}
	public void setFechaddMMyyyyV2(String fechaddMMyyyyV2) {
		this.fechaddMMyyyyV2 = fechaddMMyyyyV2;
	}
	public String getFechacapturaddMMyyyV3() {
		if(this.fechacaptura!=null) {
			 return Utils.getddMMyyyysdfInstanceV3().format(this.fechacaptura);
		}else {
			return fechacapturaddMMyyyV3;	
		}
		
	}
	public void setFechacapturaddMMyyyV3(String fechacapturaddMMyyyV3) {
		this.fechacapturaddMMyyyV3 = fechacapturaddMMyyyV3;
	}
	public String getFechayyyyMMdd() {
		if(null!=fecha) {
		  return Utils.getyyyyMMddsdfInstance().format(fecha); 
		}else {
		return fechayyyyMMdd;
		}
	}
	public void setFechayyyyMMdd(String fechayyyyMMdd) {
		this.fechayyyyMMdd = fechayyyyMMdd;
	}
	public String getEnUSCosto() {
		return "$"+Utils.getenUSDecimalFormatInstance().format(costo);
	}
	public void setEnUSCosto(String enUSCosto) {
		this.enUSCosto = enUSCosto;
	}
	public String getHhmmssahorainicial() {
		java.util.Date dateHoraInicial=null;
		if(null!=horainicial) {
			try {
			dateHoraInicial=Utils.gethhmmsdfInstance().parse(horainicial);
			hhmmssahorainicial=Utils.gethhmmssasdfInstance().format(dateHoraInicial);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				try {
					dateHoraInicial=Utils.gethhmmsdfInstance().parse("00:00");
					hhmmssahorainicial=Utils.gethhmmssasdfInstance().format(dateHoraInicial);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					System.out.println("Exception Paso 1 getHhmmssahorainicial");
				}
			}
			}
		if(null!=hhmmssahorainicial) {
			hhmmssahorainicial=hhmmssahorainicial.replaceAll("AM", "a.m.").replaceAll("PM","p.m.");
		}
		
		return hhmmssahorainicial;
	
	}
	public void setHhmmssahorainicial(String hhmmssahorainicial) {
		
		this.hhmmssahorainicial = hhmmssahorainicial;
	}
	public String getHhmmssahorafinal() {
		
		java.util.Date dateHoraFinal=null;
		if(null!=horafinal) {
			try {
			dateHoraFinal=Utils.gethhmmsdfInstance().parse(horafinal);
			hhmmssahorafinal=Utils.gethhmmssasdfInstance().format(dateHoraFinal);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				try {
					dateHoraFinal=Utils.gethhmmsdfInstance().parse("00:00");
					hhmmssahorafinal=Utils.gethhmmssasdfInstance().format(dateHoraFinal);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					System.out.println("Exception Paso 1 getHhmmssahorafinal");
				}
			}
			}
		if(null!=hhmmssahorafinal) {
			hhmmssahorafinal=hhmmssahorafinal.replaceAll("AM", "a.m.").replaceAll("PM","p.m.");
		}
		return hhmmssahorafinal;
	}
	public void setHhmmssahorafinal(String hhmmssahorafinal) {
		this.hhmmssahorafinal = hhmmssahorafinal;
	}
	
	
	
}
