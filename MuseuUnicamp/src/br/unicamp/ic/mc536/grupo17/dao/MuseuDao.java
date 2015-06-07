package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Museu;

public class MuseuDao extends BaseDao{
	
	//TABLE COLUMNS
	public static String ID_MUSEU = "ID_MUSEU";
	public static String NOME= "NOME";
	public static String CONTATO = "CONTATO";

	//AVAILABLE QUERIES	
	private static String SELECT = "SELECT * FROM MUSEU";
	private static String SELECT_WHERE = "SELECT * FROM MUSEU WHERE ";
	private static String INSERT = "INSERT INTO MUSEU (NOME, CONTATO) VALUES (?,?)";
	private static String UPDATE_BASE = "UPDATE MUSEU ";
	private static String DELETE_BASE = "DELETE FROM MUSEU WHERE";
	
	public MuseuDao(){
		super();
	}
	
	public List<Museu> select() throws SQLException{
		List<Museu> listMuseu = new ArrayList<Museu>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Museu museu = new Museu();
			museu.setIdMuseu(rs.getInt(1));
			museu.setNome(rs.getString(2));
			museu.setContato(rs.getString(3));
			listMuseu.add(museu);
		}
		return listMuseu;
	}
	
	public List<Museu> selectWhere(String column, String operator, Object value) throws SQLException{
		List<Museu> listMuseu = new ArrayList<Museu>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Museu museu = new Museu();
			museu.setIdMuseu(rs.getInt(1));
			museu.setNome(rs.getString(2));
			museu.setContato(rs.getString(3));
			listMuseu.add(museu);
		}
		return listMuseu;
	}
	
	public List<Museu> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<Museu> listMuseu = new ArrayList<Museu>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Museu museu = new Museu();
			museu.setIdMuseu(rs.getInt(1));
			museu.setNome(rs.getString(2));
			museu.setContato(rs.getString(3));
			listMuseu.add(museu);
		}
		return listMuseu;
	}
	
	public boolean insert(Museu museu) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1, museu.getNome());
		stmt.setString(2, museu.getContato());
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
