package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Obra;
import br.unicamp.ic.mc536.grupo17.model.Escultura;

public class EsculturaDao extends ObraDao{
	
	//TABLE COLUMNS
	//public static String ID_OBRA = "ID_OBRA";
	public static String MATERIAIS = "MATERIAIS";
	public static String ALTURA = "ALTURA";
	public static String LARGURA = "LARGURA";
	public static String PROFUNDIDADE = "PROFUNDIDADE";
	
//	//TABLE COLUMNS
//	public static String ID_OBRA = "ID_OBRA";
//	public static String ID_ESTILO = "ID_ESTILO";
//	public static String TITULO = "TITULO";
//	public static String DATA = "DATA";
//	public static String DESCRICAO = "DESCRICAO";
//	public static String TAGS = "TAGS"; 
//	public static String PATH_IMAGEM = "PATH_IMAGEM"; 
//	public static String TIPO = "TIPO"; 

	//AVAILABLE QUERIES	
	private static String SELECT_ESCULTURAS = "SELECT OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, " +
			"OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
			"ESCULTURA.MATERIAIS, ESCULTURA.ALTURA, ESCULTURA.LARGURA, ESCULTURA.PROFUNDIDADE FROM " +
			"ESCULTURA, OBRA WHERE ESCULTURA.ID_OBRA = OBRA.ID_OBRA";
	private static String SELECT_ESCULTURAS_WHERE = SELECT_ESCULTURAS + " AND " ;
	private static String INSERT = "INSERT INTO ESCULTURA (ID_OBRA, MATERIAIS, ALTURA, " +
			"LARGURA, PROFUNDIDADE) VALUES (?,?,?,?,?)";
	private static String UPDATE_BASE = "UPDATE ESCULTURA ";
	private static String DELETE_BASE = "DELETE FROM ESCULTURA WHERE";
	
	public EsculturaDao(){
		super();
	}
	
	
	public List<Escultura> selectEsculturas() throws SQLException{
		List<Escultura> listEscultura = new ArrayList<Escultura>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT_ESCULTURAS);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Escultura escultura = new Escultura();
			escultura.setIdObra(rs.getInt(1));
			escultura.setIdEstilo(rs.getInt(2));
			escultura.setTitulo(rs.getString(3));
			escultura.setData(rs.getInt(4));
			escultura.setDescricao(rs.getString(5));
			escultura.setTags(rs.getString(6));
			escultura.setPathImagem(rs.getString(7));
			escultura.setTipo(rs.getString(8));
			escultura.setMateriais(rs.getString(9));
			escultura.setAltura(rs.getDouble(10));
			escultura.setLargura(rs.getDouble(11));
			escultura.setProfundidade(rs.getDouble(12));
			listEscultura.add(escultura);
		}
		return listEscultura;
	}
	
	public List<Escultura> selectEsculturasWhere(String column, String operator, Object value) throws SQLException{
		List<Escultura> listEscultura = new ArrayList<Escultura>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Escultura escultura = new Escultura();
			escultura.setIdObra(rs.getInt(1));
			escultura.setIdEstilo(rs.getInt(2));
			escultura.setTitulo(rs.getString(3));
			escultura.setData(rs.getInt(4));
			escultura.setDescricao(rs.getString(5));
			escultura.setTags(rs.getString(6));
			escultura.setPathImagem(rs.getString(7));
			escultura.setTipo(rs.getString(8));
			escultura.setMateriais(rs.getString(9));
			escultura.setAltura(rs.getDouble(10));
			escultura.setLargura(rs.getDouble(11));
			escultura.setProfundidade(rs.getDouble(12));
			listEscultura.add(escultura);
		}
		return listEscultura;
	}
	
	public List<Escultura> selectEsculturasWhere(List<CompoundSearchFilter> filters) throws SQLException{
		List<Escultura> listEscultura = new ArrayList<Escultura>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_ESCULTURAS_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Escultura escultura = new Escultura();
			escultura.setIdObra(rs.getInt(1));
			escultura.setIdEstilo(rs.getInt(2));
			escultura.setTitulo(rs.getString(3));
			escultura.setData(rs.getInt(4));
			escultura.setDescricao(rs.getString(5));
			escultura.setTags(rs.getString(6));
			escultura.setPathImagem(rs.getString(7));
			escultura.setTipo(rs.getString(8));
			escultura.setMateriais(rs.getString(9));
			escultura.setAltura(rs.getDouble(10));
			escultura.setLargura(rs.getDouble(11));
			escultura.setProfundidade(rs.getDouble(12));
			listEscultura.add(escultura);
		}
		return listEscultura;
	}
	

	public boolean insert(Escultura escultura) throws SQLException{
		//1. inserindo na tabela obra
		Obra obra = escultura.getObra();
		super.insert(obra);
		//2. pegando o ID da ultima entrada inserida
		escultura.setIdObra(super.getMaxId());
		//3. inserindo na tabela escultura
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, escultura.getIdObra());
		stmt.setString(2, escultura.getMateriais());
		stmt.setDouble(3, escultura.getAltura());
		stmt.setDouble(4, escultura.getLargura());
		stmt.setDouble(5, escultura.getProfundidade());
		return stmt.execute();
	}
	
	/**
	 * Creates a query on the format 
	 * UPDATE ESCULTURA SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
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
