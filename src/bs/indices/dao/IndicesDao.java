package bs.indices.dao;

import java.sql.SQLException;

public interface IndicesDao {
	public String findNextNumfact() throws SQLException; /** [int] null, **/
	public String findNextNumfalla() throws SQLException; /** [int] null, **/
	public String findNextNumvale() throws SQLException; /** [numeric](18, 0) null, **/
	public String findNextNumasignacion() throws SQLException; /** [int] null, **/
	public String findNextNumeroruta() throws SQLException; /** [int] null, **/
	public String findNextNumfactserv() throws SQLException; /** [int] null, **/
	public String findNextFoliollantas() throws SQLException; /** [int] null, **/
	public String findNextFoliomovllantas() throws SQLException; /** [int] null, **/
	public String findNextClaveseguridad() throws SQLException; /** [nchar](10) null, **/
	public String findNextClaveborrar() throws SQLException; /** [nvarchar](50) null, **/
	public String findNextNumproveedor() throws SQLException; /** [int] null **/
}
