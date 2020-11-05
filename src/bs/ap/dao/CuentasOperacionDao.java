package bs.ap.dao;

import java.sql.SQLException;
import java.util.List;
import bs.ap.dto.CuentasOperacion;

public interface CuentasOperacionDao {
   public List<CuentasOperacion> findAll() throws SQLException;
   public String insert(CuentasOperacion cuentasOperacionDto) throws SQLException;
   public String update(CuentasOperacion cuentasOperacionDto) throws SQLException;
   public String deleteById(int pId) throws SQLException;
}
