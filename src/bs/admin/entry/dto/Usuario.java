
package bs.admin.entry.dto;
import java.io.Serializable;
import java.math.BigDecimal;


public class Usuario implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String nombre;
	protected boolean nombreModified = false;
	protected BigDecimal nivel;
	protected boolean nivelNull = true;
	protected boolean nivelModified = false;
	protected String pass; 
	public Usuario()
	{
	}

	/**
	 * Method 'getNombre'
	 * 
	 * @return String
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Method 'setNombre'
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
		this.nombreModified = true;
	}

	/** 
	 * Sets the value of nombreModified
	 */
	public void setNombreModified(boolean nombreModified)
	{
		this.nombreModified = nombreModified;
	}

	/** 
	 * Gets the value of nombreModified
	 */
	public boolean isNombreModified()
	{
		return nombreModified;
	}

	/**
	 * Method 'getNivel'
	 * 
	 * @return long
	 */
	public BigDecimal getNivel()
	{
		return nivel;
	}

	/**
	 * Method 'setNivel'
	 * 
	 * @param nivel
	 */
	public void setNivel(BigDecimal nivel)
	{
		this.nivel = nivel;
		this.nivelNull = false;
		this.nivelModified = true;
	}

	/**
	 * Method 'setNivelNull'
	 * 
	 * @param value
	 */
	public void setNivelNull(boolean value)
	{
		this.nivelNull = value;
		this.nivelModified = true;
	}

	/**
	 * Method 'isNivelNull'
	 * 
	 * @return boolean
	 */
	public boolean isNivelNull()
	{
		return nivelNull;
	}

	/** 
	 * Sets the value of nivelModified
	 */
	public void setNivelModified(boolean nivelModified)
	{
		this.nivelModified = nivelModified;
	}

	/** 
	 * Gets the value of nivelModified
	 */
	public boolean isNivelModified()
	{
		return nivelModified;
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
		
		if (!(_other instanceof Usuario)) {
			return false;
		}
		
		final Usuario _cast = (Usuario) _other;
		if (nombre == null ? _cast.nombre != nombre : !nombre.equals( _cast.nombre )) {
			return false;
		}
		
		if (nombreModified != _cast.nombreModified) {
			return false;
		}
		
		if (nivel != _cast.nivel) {
			return false;
		}
		
		if (nivelNull != _cast.nivelNull) {
			return false;
		}
		
		if (nivelModified != _cast.nivelModified) {
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
		if (nombre != null) {
			_hashCode = 29 * _hashCode + nombre.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (nombreModified ? 1 : 0);
		_hashCode = 29 * _hashCode + (nivelNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (nivelModified ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "bs.admin.entry.dto.Usuario: " );
		ret.append( "nombre=" + nombre );
		ret.append( ", nivel=" + nivel );
		return ret.toString();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
