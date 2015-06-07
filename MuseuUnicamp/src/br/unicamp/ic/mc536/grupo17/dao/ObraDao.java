package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Obra;

public class ObraDao extends BaseDao{
	
	//TABLE COLUMNS
	public static String ID_OBRA = "ID_OBRA";
	public static String ID_ESTILO = "ID_ESTILO";
	public static String TITULO = "TITULO";
	public static String DATA = "DATA";
	public static String DESCRICAO = "DESCRICAO";
	public static String TAGS = "TAGS"; 
	public static String PATH_IMAGEM = "PATH_IMAGEM"; 
	public static String TIPO = "TIPO"; 
	
	//AVAILABLE QUERIES	
	protected static String SELECT = "SELECT * FROM OBRA";
	protected static String SELECT_WHERE = "SELECT * FROM OBRA WHERE ";
	protected static String INSERT = "INSERT INTO OBRA (ID_ESTILO, TITULO, " +
			"DATA, DESCRICAO, TAGS, PATH_IMAGEM, TIPO) VALUES (?,?,?,?,?,?,?)";
	protected static String UPDATE_BASE = "UPDATE OBRA ";
	protected static String DELETE_BASE = "DELETE FROM OBRA WHERE";
	
	public ObraDao(){
		super();
	}
	
	public List<Obra> select() throws SQLException{
		List<Obra> listObra = new ArrayList<Obra>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Obra obra = new Obra();
			obra.setIdObra(rs.getInt(1));
			obra.setIdEstilo(rs.getInt(2));
			obra.setTitulo(rs.getString(3));
			obra.setData(rs.getInt(4));
			obra.setDescricao(rs.getString(5));
			obra.setTags(rs.getString(6));
			obra.setPathImagem(rs.getString(7));
			obra.setTipo(rs.getString(8));
			listObra.add(obra);
		}
		return listObra;
	}
	
	public List<Obra> selectWhere(String column, String operator, Object value) throws SQLException{
		List<Obra> listObra = new ArrayList<Obra>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Obra obra = new Obra();
			obra.setIdObra(rs.getInt(1));
			obra.setIdEstilo(rs.getInt(2));
			obra.setTitulo(rs.getString(3));
			obra.setData(rs.getInt(4));
			obra.setDescricao(rs.getString(5));
			obra.setTags(rs.getString(6));
			obra.setPathImagem(rs.getString(7));
			obra.setTipo(rs.getString(8));

			listObra.add(obra);
		}
		return listObra;
	}
	
	public List<Obra> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<Obra> listObra = new ArrayList<Obra>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Obra obra = new Obra();
			obra.setIdObra(rs.getInt(1));
			obra.setIdEstilo(rs.getInt(2));
			obra.setTitulo(rs.getString(3));
			obra.setData(rs.getInt(4));
			obra.setDescricao(rs.getString(5));
			obra.setTags(rs.getString(6));
			obra.setPathImagem(rs.getString(7));
			obra.setTipo(rs.getString(8));

			listObra.add(obra);
		}
		return listObra;
	}
	
	public boolean insert(Obra obra) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, obra.getIdEstilo());
		stmt.setString(2, obra.getTitulo());
		stmt.setInt(3, obra.getData());
		stmt.setString(4, obra.getDescricao());
		stmt.setString(5, obra.getTags());
		stmt.setString(6, obra.getPathImagem());
		stmt.setString(7, obra.getTipo());

		return stmt.execute();
	}
	
	/**
	 * Creates a query on the format 
	 * UPDATE OBRA SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
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
	
	protected int getMaxId() throws SQLException{
		int result=-1;
		String query = "SELECT max(ID_OBRA) as MAX FROM OBRA";	
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			result = rs.getInt(1); break;
		}
		System.out.println(result);
		return result;
	}

}
