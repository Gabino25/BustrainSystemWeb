package bs.catalogo.dao;

import bs.catalogo.dto.CatAutobus;
import bs.sefaforo.dto.SemaforoAgencia;
import bs.sefaforo.dto.SemaforoTaller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface CatAutobusDao {
 
	public String insert(CatAutobus dto) throws SQLException;
	
	public List<CatAutobus> findAll() throws SQLException;
 
	public List<CatAutobus> findUnidadesList() throws SQLException; 
 
	public String findNextFolio() throws SQLException;

	public String findCombustible(String strpUnidadValue) throws SQLException;
 
	public CatAutobus findByFolio(int pFolio) throws SQLException;

	public String update(CatAutobus catAutobusDto) throws SQLException;

	public String deleteByFolio(int pFolio) throws SQLException;
	
	public List<SemaforoTaller> findAllSemaforoTaller() throws SQLException;
    
	public List<SemaforoAgencia> findAllSemaforoAgencia() throws SQLException;
	
	public List<CatAutobus> findCatalogarUnidades() throws SQLException;

	public String updateCatalogarUnidades(CatAutobus catAutobus)  throws SQLException;

	public void updateFallas(String eco
			               , Timestamp fecharep
			               , double kilometraje
			               , String descripcion
			               ) throws SQLException;
	
 }
