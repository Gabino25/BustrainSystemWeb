package bs.catalogo.dto;

import java.io.Serializable;
import bs.util.Utils;

public class CatProveedores implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int clave;
	protected String rfc;
	protected String empresa;
	protected String nombre;
	protected String direccion;
	protected String telefono;
	protected String estado;
	protected java.sql.Timestamp fecha;
	protected String fechaddMMyyyy; 
	private String fechayyyyMMdd;
	protected String descripcion;

	public CatProveedores()
	{
	}

	public int getClave()
	{
		return clave;
	}

	public void setClave(int clave)
	{
		this.clave = clave;
	}

	public String getRfc()
	{
		return rfc;
	}

	public void setRfc(String rfc)
	{
		this.rfc = rfc;
	}

	public String getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa(String empresa)
	{
		this.empresa = empresa;
	}


	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}


	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}


	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	public java.sql.Timestamp getFecha()
	{
		return fecha;
	}

	public void setFecha(java.sql.Timestamp fecha)
	{
		this.fecha = fecha;
	}
	
	public void setFechaddMMyyyy(String pFechaddMMyyyy) {
		this.fechaddMMyyyy = pFechaddMMyyyy; 
	}
	
	public String getFechaddMMyyyy() {
	  if(null!=this.fecha) {
		 return Utils.getddMMyyyysdfInstance().format(this.fecha);
	  }else {
		 return this.fechaddMMyyyy;
	  }
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}


	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof CatProveedores)) {
			return false;
		}
		
		final CatProveedores _cast = (CatProveedores) _other;
		if (clave != _cast.clave) {
			return false;
		}
		
		
		if (rfc == null ? _cast.rfc != rfc : !rfc.equals( _cast.rfc )) {
			return false;
		}
		
		
		if (empresa == null ? _cast.empresa != empresa : !empresa.equals( _cast.empresa )) {
			return false;
		}
		
		
		if (nombre == null ? _cast.nombre != nombre : !nombre.equals( _cast.nombre )) {
			return false;
		}
		
		
		if (direccion == null ? _cast.direccion != direccion : !direccion.equals( _cast.direccion )) {
			return false;
		}
		
		
		if (telefono == null ? _cast.telefono != telefono : !telefono.equals( _cast.telefono )) {
			return false;
		}
		
		
		if (estado == null ? _cast.estado != estado : !estado.equals( _cast.estado )) {
			return false;
		}
		
		
		if (fecha == null ? _cast.fecha != fecha : !fecha.equals( _cast.fecha )) {
			return false;
		}
		
		
		if (descripcion == null ? _cast.descripcion != descripcion : !descripcion.equals( _cast.descripcion )) {
			return false;
		}
		
		if (descripcion != _cast.descripcion) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + clave;
		if (rfc != null) {
			_hashCode = 29 * _hashCode + rfc.hashCode();
		}
		
		if (empresa != null) {
			_hashCode = 29 * _hashCode + empresa.hashCode();
		}
		
		if (nombre != null) {
			_hashCode = 29 * _hashCode + nombre.hashCode();
		}
		
		if (direccion != null) {
			_hashCode = 29 * _hashCode + direccion.hashCode();
		}
		
		if (telefono != null) {
			_hashCode = 29 * _hashCode + telefono.hashCode();
		}
		
		if (estado != null) {
			_hashCode = 29 * _hashCode + estado.hashCode();
		}
		
		if (fecha != null) {
			_hashCode = 29 * _hashCode + fecha.hashCode();
		}
		
		if (descripcion != null) {
			_hashCode = 29 * _hashCode + descripcion.hashCode();
		}
		
		return _hashCode;
	}


	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "bs.catalogo.dto.CatProveedores: " );
		ret.append( "clave=" + clave );
		ret.append( ", rfc=" + rfc );
		ret.append( ", empresa=" + empresa );
		ret.append( ", nombre=" + nombre );
		ret.append( ", direccion=" + direccion );
		ret.append( ", telefono=" + telefono );
		ret.append( ", estado=" + estado );
		ret.append( ", fecha=" + fecha );
		ret.append( ", descripcion=" + descripcion );
		return ret.toString();
	}

	public String getFechayyyyMMdd() {
		if(null!=this.fecha) {
			return Utils.getyyyyMMddsdfInstance().format(this.fecha);
		}else {
		return fechayyyyMMdd;
		}
	}

	public void setFechayyyyMMdd(String fechayyyyMMdd) {
		this.fechayyyyMMdd = fechayyyyMMdd;
	}
	
}
