package bs.vales.combust.entry.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import bs.vales.combust.entry.dto.Diesel;

public interface DieselDao {
 
 public List<Diesel> findAll() throws SQLException;

 public List<Diesel> filtraPorFecha(int pDay,int pMonth,int pYear) throws SQLException;

 public List<Diesel> filtraPorFechas(int pDesdeDay
		                            ,int pDesdeMonth
		                            ,int pDesdeYear
		                            ,int pHastaDay
		                            ,int pHastaMonth
		                            ,int pHastaYear
		                            ,String pStrUnidad
		                            ,String pStrGasolinera
		                            ) throws SQLException;
 
 public String insert(Diesel dto) throws SQLException;

 public String findNextNota() throws SQLException;

 public String validaFolio(long pNota) throws SQLException;

 public double findKmAnterior(String strpUnidadValue) throws SQLException;
 
 public List<Diesel> findRendimientosCalculados() throws SQLException;
 
 public String calculaRendimientos() throws SQLException;
 
 public String deleteByFolio(int pFolio) throws SQLException;

 public String cargasAexcel(int pDesdeDay
				         ,int pDesdeMonth
				         ,int pDesdeYear
				         ,int pHastaDay
				         ,int pHastaMonth
				         ,int pHastaYear
				         ) throws SQLServerException,SQLException,Exception;
 
 public String cargasAexcelV2(int pDesdeDay
         ,int pDesdeMonth
         ,int pDesdeYear
         ,int pHastaDay
         ,int pHastaMonth
         ,int pHastaYear
         ) throws SQLServerException,SQLException,Exception;

 public String cargasAexcelV3(int pDesdeDay
         ,int pDesdeMonth
         ,int pDesdeYear
         ,int pHastaDay
         ,int pHastaMonth
         ,int pHastaYear
         ) throws SQLServerException,SQLException,Exception;

public String cargasAexcelComParVehicular(int pDesdeDay
		                                , int pDesdeMonth
		                                , int pDesdeYear
		                                , int pHastaDay
		                                , int pHastaMonth
		                                , int pHastaYear) throws SQLException,ParseException,Exception;
 
}
