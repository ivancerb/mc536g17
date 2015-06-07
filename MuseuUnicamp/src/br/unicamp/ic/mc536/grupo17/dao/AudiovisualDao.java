package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Obra;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;

public class AudiovisualDao extends ObraDao{
	
	//TABLE COLUMNS
	//public static String ID_OBRA = "ID_OBRA";
	public static String TIPO_MIDIA = "TIPO_MIDIA";


	//AVAILABLE QUERIES	
	private static String SELECT_AUDIOVISUAL = "SELECT OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, " +
			"OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, AUDIOVISUAL.TIPO_MIDIA FROM " +
			"AUDIOVISUAL, OBRA WHERE AUDIOVISUAL.ID_OBRA = OBRA.ID_OBRA";
	private static String SELECT_WHERE = SELECT_AUDIOVISUAL + " AND " ;
	private static String INSERT = "INSERT INTO AUDIOVISUAL (ID_OBRA, TIPO_MIDIA) VALUES (?,?)";
	private static String UPDATE_BASE = "UPDATE AUDIOVISUAL ";
	private static String DELETE_BASE = "DELETE FROM AUDIOVISUAL WHERE";
	
	public AudiovisualDao(){
		super();
	}
	
	
	public List<Audiovisual> selectAudiovisuals() throws SQLException{
		List<Audiovisual> listAudiovisual = new ArrayList<Audiovisual>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT_AUDIOVISUAL);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Audiovisual audiovisual = new Audiovisual();
			audiovisual.setIdObra(rs.getInt(1));
			audiovisual.setIdEstilo(rs.getInt(2));
			audiovisual.setTitulo(rs.getString(3));
			audiovisual.setData(rs.getInt(4));
			audiovisual.setDescricao(rs.getString(5));
			audiovisual.setTags(rs.getString(6));
			audiovisual.setPathImagem(rs.getString(7));
			audiovisual.setTipo(rs.getString(8));
			audiovisual.setTipo_midia(rs.getString(9));
			listAudiovisual.add(audiovisual);
		}
		return listAudiovisual;
	}
	
	public List<Audiovisual> selectAudiovisualsWhere(String column, String operator, Object value) throws SQLException{
		List<Audiovisual> listAudiovisual = new ArrayList<Audiovisual>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Audiovisual audiovisual = new Audiovisual();
			audiovisual.setIdObra(rs.getInt(1));
			audiovisual.setIdEstilo(rs.getInt(2));
			audiovisual.setTitulo(rs.getString(3));
			audiovisual.setData(rs.getInt(4));
			audiovisual.setDescricao(rs.getString(5));
			audiovisual.setTags(rs.getString(6));
			audiovisual.setPathImagem(rs.getString(7));
			audiovisual.setTipo(rs.getString(8));
			audiovisual.setTipo_midia(rs.getString(9));
			listAudiovisual.add(audiovisual);
		}
		return listAudiovisual;
	}
	
	public List<Audiovisual> selectAudiovisualsWhere(List<CompoundSearchFilter> filters) throws SQLException{
		List<Audiovisual> listAudiovisual = new ArrayList<Audiovisual>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Audiovisual audiovisual = new Audiovisual();
			audiovisual.setIdObra(rs.getInt(1));
			audiovisual.setIdEstilo(rs.getInt(2));
			audiovisual.setTitulo(rs.getString(3));
			audiovisual.setData(rs.getInt(4));
			audiovisual.setDescricao(rs.getString(5));
			audiovisual.setTags(rs.getString(6));
			audiovisual.setPathImagem(rs.getString(7));
			audiovisual.setTipo(rs.getString(8));
			audiovisual.setTipo_midia(rs.getString(9));
			listAudiovisual.add(audiovisual);
		}
		return listAudiovisual;
	}
	

	public boolean insert(Audiovisual audiovisual) throws SQLException{
		//1. inserindo na tabela obra
		Obra obra = audiovisual.getObra();
		super.insert(obra);
		//2. pegando o ID da ultima entrada inserida
		audiovisual.setIdObra(super.getMaxId());
		//3. inserindo na tabela audiovisual
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, audiovisual.getIdObra());
		stmt.setString(2, audiovisual.getTipo_midia());
		return stmt.execute();
	}
	
	/**
	 * Creates a query on the format 
	 * UPDATE AUDIOVISUAL SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
	 * @param columnToUpdate
	 * @param value
	 * @param columnWhere
	 * @throws SQLException
	 */
	public boolean update(String columnToUpdate, String value, String columnWhere, String criteria, String valueWhere) throws SQLException{
		return update(UPDATE_BASE, columnToUpdate, value, columnWhere, criteria, valueWhere);
	}

	public boolean delete(String columnToDelete, String criteria, String value) throws SQLException{
		return delete(DELETE_BASE, columnToDelete, criteria, value);
	}

}
