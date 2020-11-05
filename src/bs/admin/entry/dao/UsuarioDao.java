package bs.admin.entry.dao;

import bs.admin.entry.dto.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao
{
	
	public Usuario findByNombrePass(String pNombre,String pPass) throws SQLException;
	
	public String insert(Usuario dto) throws SQLException;

	/** 
	 * Returns all rows from the USUARIO table that match the criteria ''.
	 */
	public List<Usuario> findAll() throws SQLException;

	/** 
	 * Returns all rows from the USUARIO table that match the criteria 'NOMBRE = :nombre'.
	 */
	public Usuario[] findWhereNombreEquals(String nombre) throws SQLException;

	/** 
	 * Returns all rows from the USUARIO table that match the criteria 'NIVEL = :nivel'.
	 */
	public Usuario[] findWhereNivelEquals(long nivel) throws SQLException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the USUARIO table that match the specified arbitrary SQL statement
	 */
	public Usuario[] findByDynamicSelect(String sql, Object[] sqlParams) throws SQLException;

	/** 
	 * Returns all rows from the USUARIO table that match the specified arbitrary SQL statement
	 */
	public Usuario[] findByDynamicWhere(String sql, Object[] sqlParams) throws SQLException;

	public String update(String strnombreUsuarioTrx,Usuario usuario) throws SQLException;

	public String deleteByNombre(String strnombreUsuarioTrx) throws SQLException;

}
