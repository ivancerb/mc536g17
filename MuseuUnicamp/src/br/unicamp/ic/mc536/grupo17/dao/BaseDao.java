package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;


public abstract class BaseDao {
	
	protected String composeSelectByString(String statement, String column, String criteria){
		return statement + " " + column.toUpperCase() + " " + criteria + " ? ";
	}
	
	protected String composeSelect(String baseStatement, List<SearchFilter> filterList){
		String query = baseStatement;
		boolean first = true;
		for(SearchFilter filter : filterList){
			if(first){
				first = false;
			}else{
				query += " AND ";
			}
			query+= filter.getColumn() + filter.getFilter() + " ? ";
		}
		return query;
	}
	
	protected String composeSelectMultipleTables(String baseStatement, List<CompoundSearchFilter> filterList){
		//String query = baseStatement;
//		String query = "SELECT * FROM ";
//		int size = tablesList.size();
//		//adicionando tabelas
//		for(int i=0; i<size; i++){
//			if(!(i==size-1)){
//				query+=tablesList.get(i)+", ";
//			}else{
//				query+=tablesList.get(i) + " ";
//			}
//		}
//		query+=" WHERE ";
		String query = baseStatement; 
		boolean first = true;
		for(CompoundSearchFilter filter : filterList){
			if(first){
				first = false;
			}else{
				query += " AND ";
			}
			query+=filter.getTable()+ "." + filter.getColumn() + " " + filter.getFilter() + " ";
			if(filter.isJoin()){
				query+=filter.getValue() + " ";
			}
			else{
				query+=" ? ";
			}
		}
		return query;
	}
	
	/**
	 * Execute a query on the format 
	 * UPDATE ARTISTA SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
	 * @param columnToUpdate
	 * @param value
	 * @param columnWhere
	 * @throws SQLException
	 */
	protected boolean update(String baseStatement, String columnToUpdate, Object value, String columnWhere, String criteria, Object valueWhere) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = 
				connection.prepareStatement(composeUpdateString(baseStatement, columnToUpdate, columnWhere, criteria));
		stmt.setObject(1, value);
		stmt.setObject(2, valueWhere);
		return stmt.execute();
	}
	
	/**
	 * Execute a query on the format
	 * DELETE FROM ARTISTA WHERE columnToUpdate criteria(=/</>/LIKE) value 
	 * @param baseStatement
	 * @param columnToDelete
	 * @param criteria
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	protected boolean delete(String baseStatement, String columnToDelete, String criteria, Object value) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = 
				connection.prepareStatement(composeDeleteString(baseStatement, columnToDelete, criteria));
		stmt.setObject(1, value);
		return stmt.execute();
	}
	
	/**
	 * Create a query on the format 
	 * UPDATE ARTISTA SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
	 * @param columnToUpdate
	 * @param value
	 * @param columnWhere
	 * @throws SQLException
	 */
	private String composeUpdateString(String updateBaseStatement, String columnToUpdate, String columnWhere, String criteria){
		String updateQuery = updateBaseStatement;
		updateQuery += " SET " + columnToUpdate + "=? WHERE " + columnWhere + criteria + "?";
		return updateQuery;
	}
	
	/**
	 * Create a query on the format 
	 * DELETE FROM ARTISTA WHERE columnToUpdate criteria(=/</>/LIKE) value 
	 * @param deleteBaseStatement
	 * @param columnToDelete
	 * @param criteria
	 * @return
	 */
	private String composeDeleteString(String deleteBaseStatement, String columnToDelete, String criteria){
		String deleteQuery = deleteBaseStatement;
		deleteQuery += " " + columnToDelete + criteria + " ? ";
		return deleteQuery;
	}
}
