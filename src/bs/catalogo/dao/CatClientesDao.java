package bs.catalogo.dao;

import java.sql.SQLException;
import java.util.List;
import bs.catalogo.dto.CatClientes;

public interface CatClientesDao
{
	public String insert(CatClientes dto) throws SQLException;

	public List<CatClientes> findAll() throws SQLException;

	public List<CatClientes> findEmpresasList() throws SQLException; 
	
	public String findNextClave() throws SQLException;

	public CatClientes findByClave(int pClave) throws SQLException;
	
	public String update(CatClientes catClientes) throws SQLException;

	public String deleteByClave(int parseInt) throws SQLException;
	
}

