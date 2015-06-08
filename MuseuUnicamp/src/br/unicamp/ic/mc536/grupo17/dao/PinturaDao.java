package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Obra;
import br.unicamp.ic.mc536.grupo17.model.Pintura;

public class PinturaDao extends ObraDao{
	
	//TABLE COLUMNS
	//public static String ID_OBRA = "ID_OBRA";
	public static String MATERIAIS = "MATERIAIS";
	public static String ALTURA = "ALTURA";
	public static String LARGURA = "LARGURA";
	
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
	private static String SELECT_PINTURAS = "SELECT OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, " +
			"OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
			"PINTURA.MATERIAIS, PINTURA.ALTURA, PINTURA.LARGURA FROM " +
			"PINTURA, OBRA WHERE PINTURA.ID_OBRA = OBRA.ID_OBRA ";
	private static String SELECT_WHERE = SELECT_PINTURAS + " AND " ;
	private static String INSERT = "INSERT INTO PINTURA (ID_OBRA, MATERIAIS, ALTURA, " +
			"LARGURA) VALUES (?,?,?,?)";
	private static String UPDATE_BASE = "UPDATE PINTURA ";
	private static String DELETE_BASE = "DELETE FROM PINTURA WHERE";
	
	public PinturaDao(){
		super();
	}
	
	
	public List<Pintura> selectPinturas() throws SQLException{
		List<Pintura> listPintura = new ArrayList<Pintura>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT_PINTURAS);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Pintura pintura = new Pintura();
			pintura.setIdObra(rs.getInt(1));
			pintura.setIdEstilo(rs.getInt(2));
			pintura.setTitulo(rs.getString(3));
			pintura.setData(rs.getInt(4));
			pintura.setDescricao(rs.getString(5));
			pintura.setTags(rs.getString(6));
			pintura.setPathImagem(rs.getString(7));
			pintura.setTipo(rs.getString(8));
			pintura.setMateriais(rs.getString(9));
			pintura.setAltura(rs.getDouble(10));
			pintura.setLargura(rs.getDouble(11));
			listPintura.add(pintura);
		}
		return listPintura;
	}
	
	public List<Pintura> selectPinturasWhere(String column, String operator, Object value) throws SQLException{
		List<Pintura> listPintura = new ArrayList<Pintura>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Pintura pintura = new Pintura();
			pintura.setIdObra(rs.getInt(1));
			pintura.setIdEstilo(rs.getInt(2));
			pintura.setTitulo(rs.getString(3));
			pintura.setData(rs.getInt(4));
			pintura.setDescricao(rs.getString(5));
			pintura.setTags(rs.getString(6));
			pintura.setPathImagem(rs.getString(7));
			pintura.setTipo(rs.getString(8));
			pintura.setMateriais(rs.getString(9));
			pintura.setAltura(rs.getDouble(10));
			pintura.setLargura(rs.getDouble(11));
			listPintura.add(pintura);
		}
		return listPintura;
	}
	
	public List<Pintura> selectPinturasWhere(List<CompoundSearchFilter> filters) throws SQLException{
		List<Pintura> listPintura = new ArrayList<Pintura>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			if(!filters.get(i).isJoin()){
				stmt.setObject(i+1, filters.get(i).getValue());
			}
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Pintura pintura = new Pintura();
			pintura.setIdObra(rs.getInt(1));
			pintura.setIdEstilo(rs.getInt(2));
			pintura.setTitulo(rs.getString(3));
			pintura.setData(rs.getInt(4));
			pintura.setDescricao(rs.getString(5));
			pintura.setTags(rs.getString(6));
			pintura.setPathImagem(rs.getString(7));
			pintura.setTipo(rs.getString(8));
			pintura.setMateriais(rs.getString(9));
			pintura.setAltura(rs.getDouble(10));
			pintura.setLargura(rs.getDouble(11));
			listPintura.add(pintura);
		}
		return listPintura;
	}
	

	public boolean insert(Pintura pintura) throws SQLException{
		//1. inserindo na tabela obra
		Obra obra = pintura.getObra();
		super.insert(obra);
		//2. pegando o ID da ultima entrada inserida
		pintura.setIdObra(super.getMaxId());
		//3. inserindo na tabela pintura
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, pintura.getIdObra());
		stmt.setString(2, pintura.getMateriais());
		stmt.setDouble(3, pintura.getAltura());
		stmt.setDouble(4, pintura.getLargura());
		return stmt.execute();
	}
	
	/**
	 * Creates a query on the format 
	 * UPDATE PINTURA SET columnToUpdate = value WHERE columnWhere =/</>/LIKE ETC valueWhere
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
