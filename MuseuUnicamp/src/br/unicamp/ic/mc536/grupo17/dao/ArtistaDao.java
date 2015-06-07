package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Artista;

public class ArtistaDao extends BaseDao{
	
	//TABLE COLUMNS
	public static String ID_ARTISTA = "ID_ARTISTA";
	public static String NOME = "NOME";
	public static String DATA_NASC = "DATA_NASC";
	public static String DATA_FALESC = "DATA_FALESC";
	public static String ORIGEM = "ORIGEM";
	public static String BIO = "BIO"; 
	
	//AVAILABLE QUERIES	
	private static String SELECT = "SELECT * FROM ARTISTA";
	private static String SELECT_WHERE = "SELECT * FROM ARTISTA WHERE ";
	private static String INSERT = "INSERT INTO ARTISTA (NOME, DATA_NASC, DATA_FALESC, " +
			"ORIGEM, BIO) VALUES (?,?,?,?,?)";
	private static String UPDATE_BASE = "UPDATE ARTISTA ";
	private static String DELETE_BASE = "DELETE FROM ARTISTA WHERE";
	
	
	public ArtistaDao(){
		super();
	}
	
	public List<Artista> select() throws SQLException{
		List<Artista> listArtist = new ArrayList<Artista>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(1));
			artista.setNome(rs.getString(2));
			artista.setDataNasc(rs.getInt(3));
			artista.setDataFalesc(rs.getInt(4));
			artista.setOrigem(rs.getString(5));
			artista.setBio(rs.getString(6));
			listArtist.add(artista);
		}
		return listArtist;
	}
	
	public List<Artista> selectWhere(String column, String operator, Object value) throws SQLException{
		List<Artista> listArtist = new ArrayList<Artista>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(1));
			artista.setNome(rs.getString(2));
			artista.setDataNasc(rs.getInt(3));
			artista.setDataFalesc(rs.getInt(4));
			artista.setOrigem(rs.getString(5));
			artista.setBio(rs.getString(6));
			listArtist.add(artista);
		}
		return listArtist;
	}
	
	public List<Artista> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<Artista> listArtist = new ArrayList<Artista>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(1));
			artista.setNome(rs.getString(2));
			artista.setDataNasc(rs.getInt(3));
			artista.setDataFalesc(rs.getInt(4));
			artista.setOrigem(rs.getString(5));
			artista.setBio(rs.getString(6));
			listArtist.add(artista);
		}
		return listArtist;
	}
	
	public boolean insert(Artista artista) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1, artista.getNome());
		stmt.setInt(2, artista.getDataNasc());
		stmt.setInt(3, artista.getDataFalesc());
		stmt.setString(4, artista.getOrigem());
		stmt.setString(5, artista.getBio());
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
