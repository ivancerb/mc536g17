package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Curador;

public class CuradorDao extends BaseDao{

	//TABLE COLUMNS
	public static String ID_CURADOR	= "ID_CURADOR";
	public static String NOME= "NOME";
	public static String CONTATO = "CONTATO";

	//AVAILABLE QUERIES	
	private static String SELECT = "SELECT * FROM CURADOR";
	private static String SELECT_WHERE = "SELECT * FROM CURADOR WHERE ";
	private static String INSERT = "INSERT INTO CURADOR (NOME, CONTATO) VALUES (?,?)";
	private static String UPDATE_BASE = "UPDATE CURADOR ";
	private static String DELETE_BASE = "DELETE FROM CURADOR WHERE";
	
	public CuradorDao(){
		super();
	}
	
	public List<Curador> select() throws SQLException{
		List<Curador> listCurador = new ArrayList<Curador>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Curador curador = new Curador();
			curador.setIdCurador(rs.getInt(1));
			curador.setNome(rs.getString(2));
			curador.setContato(rs.getString(3));
			listCurador.add(curador);
		}
		return listCurador;
	}
	
	public List<Curador> selectWhere(String column, String operator, Object value) throws SQLException{
		List<Curador> listCurador = new ArrayList<Curador>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Curador curador = new Curador();
			curador.setIdCurador(rs.getInt(1));
			curador.setNome(rs.getString(2));
			curador.setContato(rs.getString(3));
			listCurador.add(curador);
		}
		return listCurador;
	}
	
	public List<Curador> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<Curador> listCurador = new ArrayList<Curador>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Curador curador = new Curador();
			curador.setIdCurador(rs.getInt(1));
			curador.setNome(rs.getString(2));
			curador.setContato(rs.getString(3));
			listCurador.add(curador);
		}
		return listCurador;
	}
	
	public boolean insert(Curador curador) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1, curador.getNome());
		stmt.setString(2, curador.getContato());
		return stmt.execute();
	}
	
	/**
	 * Creates a query on the format 
	 * UPDATE ARTISTA SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
	 * @param columnToUpdate
	 * @param value
	 * @param columnWhere
	 * @throws SQLException
	 */
	public boolean update(String columnToUpdate, String value, String columnWhere, String criteria, String valueWhere) throws SQLException{
		return super.update(UPDATE_BASE, columnToUpdate, value, columnWhere, criteria, valueWhere);
	}

	public boolean delete(String columnToDelete, String criteria, String value) throws SQLException{
		return super.delete(DELETE_BASE, columnToDelete, criteria, value);
	}
}
