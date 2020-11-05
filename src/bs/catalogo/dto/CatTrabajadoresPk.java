package bs.catalogo.dto;

import java.io.Serializable;

public class CatTrabajadoresPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int numero;

	/** 
	 * This attribute represents whether the primitive attribute numero is null.
	 */
	protected boolean numeroNull;

	/** 
	 * Sets the value of numero
	 */
	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	/** 
	 * Gets the value of numero
	 */
	public int getNumero()
	{
		return numero;
	}

	/**
	 * Method 'CatTrabajadoresPk'
	 * 
	 */
	public CatTrabajadoresPk()
	{
	}

	/**
	 * Method 'CatTrabajadoresPk'
	 * 
	 * @param numero
	 */
	public CatTrabajadoresPk(final int numero)
	{
		this.numero = numero;
	}

	/** 
	 * Sets the value of numeroNull
	 */
	public void setNumeroNull(boolean numeroNull)
	{
		this.numeroNull = numeroNull;
	}

	/** 
	 * Gets the value of numeroNull
	 */
	public boolean isNumeroNull()
	{
		return numeroNull;
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
		
		if (!(_other instanceof CatTrabajadoresPk)) {
			return false;
		}
		
		final CatTrabajadoresPk _cast = (CatTrabajadoresPk) _other;
		if (numero != _cast.numero) {
			return false;
		}
		
		if (numeroNull != _cast.numeroNull) {
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
		_hashCode = 29 * _hashCode + numero;
		_hashCode = 29 * _hashCode + (numeroNull ? 1 : 0);
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
		ret.append( "bs.catalogo.dto.CatTrabajadoresPk: " );
		ret.append( "numero=" + numero );
		return ret.toString();
	}
}
