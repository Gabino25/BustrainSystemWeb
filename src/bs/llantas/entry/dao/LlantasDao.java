package bs.llantas.entry.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import bs.llantas.entry.dto.Llantas;

public interface LlantasDao {
   public List<Llantas> findAll() throws SQLException;
   public List<Llantas> findSelectList() throws SQLException;
   public String findNextFolio() throws SQLException;
   public String insert(Llantas dto) throws SQLException;
   public String validaCodigo(String strpCodigo) throws SQLException;
   public Llantas findByNombre(String strllantaTrx) throws SQLException;
   public String update(Llantas llantaDto) throws SQLException;
   public String deleteByNombre(String strllantaTrx) throws SQLException;
   public String updateAsignacionAunidad( String pSrtPosicion,
		   String pStrPresion,
		   float pFkminicial, 
		   float pFkmfinal,
		   String pStrunidad,
		   String pStrNombre
		   ) throws SQLException;

public String updateProfundidad(float pIntProfundidadActual,
		float pFloatKmFinal
		,Timestamp pTimeFechaRevision,
		String pStrNombre) throws SQLException;
public String updateReparaciones(
		Double pDCosto
		,float pFloatKmFinal
		,String pStrNombre)
				throws SQLException;

}
