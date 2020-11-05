package bs.admin.entry.dao;

import java.sql.SQLException;
import java.util.List;
import bs.admin.entry.dto.Permisos;

public interface PermisosDao {
   
	public List<Permisos> findAll() throws SQLException;
	public String insert(Permisos permisosDto) throws SQLException;
	public List<Permisos> findPantallasByUser(String pAttrNombreUsuario) throws SQLException;
	public String update(Permisos permisos) throws SQLException;
	public String deleteById(int pId) throws SQLException;
	public String findNivel(String strUsuario, String pStrPantalla) throws SQLException;
	
}
