package bs.ap.dao;

import java.sql.SQLException;
import java.util.List;
import bs.ap.dto.Conceptos;
import bs.ap.dto.Facturas;

public interface FacturasDao {
	public List<Facturas> findAll() throws SQLException; 
	public List<Facturas> findTop1000() throws SQLException; 
	public String insert(Facturas pFacturasDto
			            ,List<Conceptos> pListConceptos
			            ) throws SQLException; 
	public String findNextNumfact() throws SQLException;
	public Facturas findByNumfact(long pNumfact) throws SQLException;
	public String update(Facturas pFactura, List<Conceptos> pListConceptos) throws SQLException;
	public String deleteByNumFact(long pNumfact) throws SQLException;

}
