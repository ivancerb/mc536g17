package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Exposicao;

public class ExposicaoDao extends BaseDao{
	
	//TABLE COLUMNS
	public static String ID_EXPOSICAO = "ID_EXPOSICAO";
	public static String ID_CURADOR = "ID_CURADOR";
	public static String ID_MUSEU = "ID_MUSEU";
	public static String NOME = "NOME";
	public static String DESCRICAO = "DESCRICAO"; 
	public static String DATA_INICIO = "DATA_INICIO";
	public static String DATA_FIM = "DATA_FIM";
	public static String STATUS_CD = "STATUS_CD";

	//AVAILABLE QUERIES	
	private static String SELECT = "SELECT * FROM EXPOSICAO";
	private static String SELECT_ORDERED_BY_END_DATE = "SELECT * FROM EXPOSICAO ORDER BY DATA_FIM DESC";
	private static String SELECT_WHERE = "SELECT * FROM EXPOSICAO WHERE ";
	private static String INSERT = "INSERT INTO EXPOSICAO (ID_CURADOR, ID_MUSEU, NOME, " +
			"DESCRICAO, DATA_INICIO, DATA_FIM, STATUS_CD) VALUES (?,?,?,?,?,?,?)";
	private static String UPDATE_BASE = "UPDATE EXPOSICAO ";
	private static String DELETE_BASE = "DELETE FROM EXPOSICAO WHERE";
		
	
	public ExposicaoDao(){
		super();
	}
	
	public List<Exposicao> select() throws SQLException{
		List<Exposicao> listExposicao = new ArrayList<Exposicao>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Exposicao exposicao = new Exposicao();
			exposicao.setIdExposicao(rs.getInt(1));
			exposicao.setIdCurador(rs.getInt(2));
			exposicao.setIdMuseu(rs.getInt(3));
			exposicao.setNome(rs.getString(4));
			exposicao.setDescricao(rs.getString(5));
			exposicao.setDataInicio(rs.getDate(6));
			exposicao.setDataFim(rs.getDate(7));
			exposicao.setStatus(rs.getString(8));
			listExposicao.add(exposicao);
		}
		return listExposicao;
	}
	
	public List<Exposicao> selectAllOrderedByEndDate() throws SQLException{
		List<Exposicao> listExposicao = new ArrayList<Exposicao>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT_ORDERED_BY_END_DATE);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Exposicao exposicao = new Exposicao();
			exposicao.setIdExposicao(rs.getInt(1));
			exposicao.setIdCurador(rs.getInt(2));
			exposicao.setIdMuseu(rs.getInt(3));
			exposicao.setNome(rs.getString(4));
			exposicao.setDescricao(rs.getString(5));
			exposicao.setDataInicio(rs.getDate(6));
			exposicao.setDataFim(rs.getDate(7));
			exposicao.setStatus(rs.getString(8));
			listExposicao.add(exposicao);
		}
		return listExposicao;
	}
	
	public List<Exposicao> selectWhere(String column, String operator, Object value) throws SQLException{
		List<Exposicao> listExposicao = new ArrayList<Exposicao>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Exposicao exposicao = new Exposicao();
			exposicao.setIdExposicao(rs.getInt(1));
			exposicao.setIdCurador(rs.getInt(2));
			exposicao.setIdMuseu(rs.getInt(3));
			exposicao.setNome(rs.getString(4));
			exposicao.setDescricao(rs.getString(5));
			exposicao.setDataInicio(rs.getDate(6));
			exposicao.setDataFim(rs.getDate(7));
			exposicao.setStatus(rs.getString(8));
			listExposicao.add(exposicao);
		}
		return listExposicao;
	}
	
	public List<Exposicao> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<Exposicao> listExposicao = new ArrayList<Exposicao>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Exposicao exposicao = new Exposicao();
			exposicao.setIdExposicao(rs.getInt(1));
			exposicao.setIdCurador(rs.getInt(2));
			exposicao.setIdMuseu(rs.getInt(3));
			exposicao.setNome(rs.getString(4));
			exposicao.setDescricao(rs.getString(5));
			exposicao.setDataInicio(rs.getDate(6));
			exposicao.setDataFim(rs.getDate(7));
			exposicao.setStatus(rs.getString(8));
			listExposicao.add(exposicao);
		}
		return listExposicao;
	}

	
	public boolean insert(Exposicao exposicao) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, exposicao.getIdCurador());
		stmt.setInt(2, exposicao.getIdMuseu());
		stmt.setString(3, exposicao.getNome());
		stmt.setString(4, exposicao.getDescricao());
		stmt.setDate(5, exposicao.getDataInicio());
		stmt.setDate(6, exposicao.getDataFim());
		stmt.setString(7, exposicao.getStatus());
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
