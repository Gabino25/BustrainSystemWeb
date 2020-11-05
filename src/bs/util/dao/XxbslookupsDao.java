package bs.util.dao;

import java.sql.SQLException;
import java.util.List;

import bs.util.dto.Xxbslookups;

public interface XxbslookupsDao {
	
	/** 
	 * Inserts a new row in the XXBSLOOKUPS table.
	 */
	public String insert(Xxbslookups dto) throws SQLException;

	/** 
	 * Updates a single row in the XXBSLOOKUPS table.
	 */
	public String update(Xxbslookups dto) throws SQLException;

	/** 
	 * Deletes a single row in the XXBSLOOKUPS table.
	 */
	public String delete(int id) throws SQLException;

		/** 
	 * Returns all rows from the XXBSLOOKUPS table that match the criteria ''.
	 */
	public List<Xxbslookups> findAll() throws SQLException;

	/** 
	 * Returns all rows from the XXBSLOOKUPS table that match the criteria 'id = :id'.
	 */
	public Xxbslookups findWhereIdEquals(int id) throws SQLException;

	/** 
	 * Returns all rows from the XXBSLOOKUPS table that match the criteria 'NAME = :name'.
	 */
	public List<Xxbslookups> findWhereNameEquals(String name) throws SQLException;

}
