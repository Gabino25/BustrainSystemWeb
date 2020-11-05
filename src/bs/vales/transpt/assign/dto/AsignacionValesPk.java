package bs.vales.transpt.assign.dto;

import java.io.Serializable;

public class AsignacionValesPk implements Serializable {
	protected long id;

	/** 
	 * This attribute represents whether the primitive attribute id is null.
	 */
	protected boolean idNull;

	/** 
	 * Sets the value of id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/** 
	 * Gets the value of id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Method 'AsignacionValesPk'
	 * 
	 */
	public AsignacionValesPk()
	{
	}

	/**
	 * Method 'AsignacionValesPk'
	 * 
	 * @param id
	 */
	public AsignacionValesPk(final long id)
	{
		this.id = id;
	}

	/** 
	 * Sets the value of idNull
	 */
	public void setIdNull(boolean idNull)
	{
		this.idNull = idNull;
	}

	/** 
	 * Gets the value of idNull
	 */
	public boolean isIdNull()
	{
		return idNull;
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
		
		if (!(_other instanceof AsignacionValesPk)) {
			return false;
		}
		
		final AsignacionValesPk _cast = (AsignacionValesPk) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (idNull != _cast.idNull) {
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
		_hashCode = 29 * _hashCode + (int) (id ^ (id >>> 32));
		_hashCode = 29 * _hashCode + (idNull ? 1 : 0);
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
		ret.append( "bs.vales.combust.dto.AsignacionValesPk: " );
		ret.append( "id=" + id );
		return ret.toString();
	}


}
