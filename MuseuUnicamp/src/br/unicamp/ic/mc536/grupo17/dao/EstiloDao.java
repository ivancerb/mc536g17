package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Estilo;

public class EstiloDao extends BaseDao{
	
	//TABLE COLUMNS
	public static String ID_ESTILO = "ID_ESTILO";
	public static String INICIO = "INICIO";
	public static String FIM = "FIM";
	public static String NOME = "NOME";
	public static String DESCRICAO = "DESCRICAO";

	
	//AVAILABLE QUERIES	
	private static String SELECT = "SELECT * FROM ESTILO";
	private static String SELECT_NOMES_ESTILOS = "SELECT NOME FROM ESTILO";
	private static String SELECT_WHERE = "SELECT * FROM ESTILO WHERE ";
	private static String INSERT = "INSERT INTO ESTILO (ID_ESTILO, INICIO, FIM, " +
			"NOME, DESCRICAO) VALUES (?,?,?,?,?)";
	private static String UPDATE_BASE = "UPDATE ESTILO ";
	private static String DELETE_BASE = "DELETE FROM ESTILO WHERE";
	
	public EstiloDao(){
		super();
	}
	
	public List<Estilo> select() throws SQLException{
		List<Estilo> listEstilo = new ArrayList<Estilo>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(1));
			estilo.setInicio(rs.getInt(2));
			estilo.setFim(rs.getInt(3));
			estilo.setNome(rs.getString(4));
			estilo.setDescricao(rs.getString(5));
			listEstilo.add(estilo);
		}
		return listEstilo;
	}
	
	public List<Estilo> selectWhere(String column, String operator, Object value) throws SQLException{
		List<Estilo> listEstilo = new ArrayList<Estilo>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(1));
			estilo.setInicio(rs.getInt(2));
			estilo.setFim(rs.getInt(3));
			estilo.setNome(rs.getString(4));
			estilo.setDescricao(rs.getString(5));
			listEstilo.add(estilo);
		}
		return listEstilo;
	}
	
	public List<Estilo> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<Estilo> listEstilo = new ArrayList<Estilo>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(1));
			estilo.setInicio(rs.getInt(2));
			estilo.setFim(rs.getInt(3));
			estilo.setNome(rs.getString(4));
			estilo.setDescricao(rs.getString(5));
			listEstilo.add(estilo);
		}
		return listEstilo;
	}
	
	public boolean insert(Estilo estilo) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, estilo.getIdEstilo());
		stmt.setInt(2, estilo.getInicio());
		stmt.setInt(3, estilo.getFim());
		stmt.setString(4, estilo.getNome());
		stmt.setString(5, estilo.getDescricao());
		return stmt.execute();
	}
	
	/**
	 * Creates a query on the format 
	 * UPDATE ESTILO SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
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

	public List<String> selectNomeEstiloList() throws SQLException{
		List<String> listEstiloNome = new ArrayList<String>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT_NOMES_ESTILOS);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			String estilo = rs.getString(1);
			listEstiloNome.add(estilo);
		}
		return listEstiloNome;
	}
}
