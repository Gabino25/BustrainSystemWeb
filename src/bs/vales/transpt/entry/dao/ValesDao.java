package bs.vales.transpt.entry.dao;

import java.sql.SQLException;
import java.util.List;
import bs.vales.transpt.entry.dto.Vales;

public interface ValesDao {
   public List<Vales> findDif0() throws SQLException;
   public List<Vales> findByFechaDif1() throws SQLException;
   public List<Vales> filtrarPorFechas(int pDesdeDay,
		   int pDesdeMonth,
		   int pDesdeYear,           
		   int pHastaDay,
		   int pHastaMonth,
		   int pHastaYear,
		   String pEmpresa,
		   String pRuta,
		   String pOperador,
		   String pUnidad
		                               ) throws SQLException;
   public String findNextNumvale() throws SQLException;   
   
   public String insert(Vales dto) throws SQLException;
   
   public String findByFolio(long pFolio) throws SQLException;
   
   public Vales findByNumVale(long pNumvale) throws SQLException;
   
   public String update(Vales valesDto) throws SQLException;
   
   public String deleteByNumVale(long pNumVale) throws SQLException;
}
