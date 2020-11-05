package bs.catalogo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import bs.catalogo.dto.CatTrabajadores;
import bs.fallas.entry.dto.Fallas;

public interface CatTrabajadoresDao {

	public String insert(CatTrabajadores pCatTrabajadores) throws SQLException;
	
	public List<CatTrabajadores> findAll() throws SQLException;
	
	public List<CatTrabajadores> findAll2() throws SQLException;
    
	public List<CatTrabajadores> findLicencTrabaj() throws SQLException;

	public List<CatTrabajadores> findTrabajadoresList() throws SQLException;

	public CatTrabajadores findByNumero(BigDecimal pNumero) throws SQLException;
	
	public String findNextNumero() throws SQLException;

	public String updateBaja(int pNumero, Timestamp pFechaBaja, String pMotivoBaja) throws SQLException;

	public String update(CatTrabajadores catTrabajadores) throws SQLException;

	public String deleteByNumero(int parseInt) throws SQLException;
	
	public List<CatTrabajadores> findLicencVencida() throws SQLException;
	
	public List<CatTrabajadores> findLicencProximosVencer() throws SQLException;
	
	public String insert2(CatTrabajadores pCatTrabajadores2) throws SQLException;


	//public List<Licencia> filter(String strlicencVencida)throws SQLException;
	
}
