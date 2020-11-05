package bs.catalogo.dao;

import java.util.List;
import bs.catalogo.dto.CatRutas;
import java.sql.SQLException;

public interface CatRutasDao {
	
 public String insert(CatRutas dto) throws SQLException;	

 public List<CatRutas> findAll() throws SQLException;

 public List<CatRutas> findRutasList() throws SQLException;
 
 public String findNextNumero() throws SQLException;

public List<CatRutas> findByEmpresa(String strEmpresa) throws SQLException;

public String findCostoByRuta(String strRuta) throws SQLException;

public CatRutas findByNumero(int pNumero) throws SQLException;

public String update(CatRutas catRutasDto) throws SQLException;

public String deleteByNumero(int pNumero) throws SQLException;

}
