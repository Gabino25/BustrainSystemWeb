package bs.catalogo.dao;

import java.sql.SQLException;
import java.util.List;

import bs.catalogo.dto.CatCelulares;


public interface CatCelularesDao {

	public String insert(CatCelulares CatCelulares) throws SQLException;
	public List<CatCelulares> findAll() throws SQLException;
}
