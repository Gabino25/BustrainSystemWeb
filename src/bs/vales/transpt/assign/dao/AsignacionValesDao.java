package bs.vales.transpt.assign.dao;

import java.sql.SQLException;
import java.util.List;

import bs.vales.transpt.assign.dto.AsignacionVales;
import bs.vales.transpt.assign.dto.AsignacionValesPk;

public interface AsignacionValesDao {
	/** 
	 * Inserts a new row in the asignacion_vales table.
	 */
	public String insert(AsignacionVales dto) throws SQLException;

	public void delete(AsignacionValesPk pk) throws SQLException;

	public List<AsignacionVales> findAll() throws SQLException;
	
	public String findNextNumero() throws SQLException; 
	
	public String findFolioBetweenIniFin(long pFolio) throws SQLException;

	public String findByFolioInicial(long pFolioInicial) throws SQLException;
	
	public String findByFolioFinal(long pFolioFinal) throws SQLException;
}
