package bs.ap.dao;

import java.sql.SQLException;
import java.util.List;
import bs.ap.dto.Conceptos;

public interface ConceptosDao {
   public List<Conceptos> findAll() throws SQLException; 
   public String findNextNumconcepto() throws SQLException;
   public List<Conceptos> findByNumfact(long pNumfact) throws SQLException;
   public List<Conceptos> filtradoFacturas(int pDesdeDay 
		                                 , int pDesdeMonth
		                                 , int pDesdeYear
		                                 , int pHastaDay
		                                 , int pHastaMonth
		                                 , int pHastaYear
		                                 , String strjsUnidad
		                                 , String strjsProveedor
		                                 , String strjsDescripcion
		                                 ) throws SQLException;
   
public List<Conceptos> filtradoFacturasBustrain(int pDesdeDay
		                                      , int pDesdeMonth
		                                      , int pDesdeYear
		                                      , int pHastaDay
		                                      , int pHastaMonth
		                                      , int pHastaYear
		                                      ) throws SQLException;

public List<Conceptos> filtradoFacturasMontacargas(int pDesdeDay
										         , int pDesdeMonth
										         , int pDesdeYear
										         , int pHastaDay
										         , int pHastaMonth
										         , int pHastaYear
										         ) throws SQLException;

}
