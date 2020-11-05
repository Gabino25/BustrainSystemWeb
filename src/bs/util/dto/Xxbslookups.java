package bs.util.dto;

import java.io.Serializable;

public class Xxbslookups implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8955457557920404219L;
	
	private int id; 
	
	private String name;
	
	private String code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(null!=name) {
			name = name.toUpperCase();	
		}
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(null!=code) {
			code = code.toUpperCase();
		}
		this.code = code;
	} 
	
}
