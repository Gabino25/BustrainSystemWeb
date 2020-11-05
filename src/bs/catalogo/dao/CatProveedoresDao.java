package bs.catalogo.dao;

import java.sql.SQLException;
import java.util.List;
import bs.catalogo.dto.CatProveedores;

public interface CatProveedoresDao {

	/** 
	 * Inserts a new row in the cat_proveedores table.
	 */
	public String insert(CatProveedores dto) throws SQLException;

	public List<CatProveedores> findAll() throws SQLException;
	
	public String findNextClave() throws SQLException;

	public CatProveedores findByClave(int parseInt) throws SQLException;

	public String update(CatProveedores catProveedoresDto) throws SQLException;

	public String deleteByClave(int pClave) throws SQLException;
	
	public List<CatProveedores> findProveedoresList() throws SQLException; 
	
}
