package bs.llantas.entry.dao;

import java.sql.SQLException;
import java.util.List;
import bs.llantas.entry.dto.MovimientosLlantas; 

public interface MovimientosLlantasDao {
   
   public List<MovimientosLlantas> findAll() throws SQLException; 
   
   public List<MovimientosLlantas> findAllRepLlantas() throws SQLException; 
   
   public List<MovimientosLlantas> findAllAssignUnidad() throws SQLException; 
   
   public List<MovimientosLlantas> findAllBajaUnidad() throws SQLException; 
   
   public List<MovimientosLlantas> findAllRevDeProf() throws SQLException; 
   
   public List<MovimientosLlantas> analizarLlantas(int pDesdeDay
		                                          ,int pDesdeMonth
		                                          ,int pDesdeYear
		                                          ,int pHastaDay
		                                          ,int pHastaMonth
		                                          ,int pHastaYear
		                                          ,String pUnidades
		                                          ,String pTipoMovimiento
		                                          ,String pLlantas
		                                          ) throws SQLException;

   public String findNextFolio() throws SQLException;

   public String insert(MovimientosLlantas movimientosLlantasDto) throws SQLException;

   public String findUnidadPosicion(String strpNombreLlanta) throws SQLException;

   public MovimientosLlantas findByFolio(int parseInt) throws SQLException;

   public String update(MovimientosLlantas movimientosLlantasDto) throws SQLException;

   public String deleteByFolio(int parseInt) throws SQLException;
   
}
