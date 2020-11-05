package bs.catalogo.dao;

import java.sql.SQLException;
import java.util.List;

import bs.catalogo.dto.Gasolinera;

public interface GasolineraDao {
  List<Gasolinera> findGasolineraList() throws SQLException;	
}
