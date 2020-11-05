package bs.fallas.entry.dao;

import java.sql.SQLException;
import java.util.List;

import bs.fallas.entry.dto.Fallas;

public interface FallasDao {
   public List<Fallas> findRealizados() throws SQLException; 
   public List<Fallas> findRealizadosTop1000() throws SQLException; 
   public List<Fallas> findPendientes() throws SQLException; 
   public Fallas findByNumero(String pNumero) throws SQLException;
   public String insert(Fallas fallasDto) throws SQLException;
   public String findNextNumero() throws SQLException;
   public String update(Fallas fallasDto) throws SQLException;
   public String deleteByNumero(String pNumero) throws SQLException;
   public List<Fallas> findTop1000() throws SQLException;
   //SE DECLARAN LAS VARIABLES A UTILIZAR EN FALLASANALIZARCO,FALLASDAOIMPL
public List<Fallas> filter(int pDesdeDay,
							int pDesdeMonth, 
							int pDesdeYear, 
							int pHastaDay,
							int pHastaMonth,
							int pHastaYear,
							String strUnidad
							,String strestado) throws SQLException;
}
