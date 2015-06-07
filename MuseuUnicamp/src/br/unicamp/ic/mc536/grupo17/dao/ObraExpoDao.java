package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.compound.ObraExpo;

public class ObraExpoDao extends BaseDao{

	//TABLE COLUMNS
	public static String ID_OBRA = "ID_OBRA";
	public static String ID_EXPOSICAO= "ID_EXPOSICAO";


	//AVAILABLE QUERIES	
	private static String SELECT = "SELECT * FROM OBRA_EXPO";
	private static String SELECT_WHERE = "SELECT * FROM OBRA_EXPO WHERE ";
	private static String INSERT = "INSERT INTO OBRA_EXPO (ID_OBRA, ID_EXPOSICAO) VALUES (?,?)";
	private static String UPDATE_BASE = "UPDATE OBRA_EXPO ";
	private static String DELETE_BASE = "DELETE FROM OBRA_EXPO WHERE";
	
	public ObraExpoDao(){
		super();
	}
	
	public List<ObraExpo> select() throws SQLException{
		List<ObraExpo> listObraExpo = new ArrayList<ObraExpo>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			ObraExpo obraExpo = new ObraExpo();
			obraExpo.setIdObra(rs.getInt(1));
			obraExpo.setIdExposicao(rs.getInt(2));
			listObraExpo.add(obraExpo);
		}
		return listObraExpo;
	}
	
	public List<ObraExpo> selectWhere(String column, String operator, Object value) throws SQLException{
		List<ObraExpo> listObraExpo = new ArrayList<ObraExpo>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelectByString(SELECT_WHERE, column, operator));
		stmt.setObject(1, value);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			ObraExpo obraExpo = new ObraExpo();
			obraExpo.setIdObra(rs.getInt(1));
			obraExpo.setIdExposicao(rs.getInt(2));
			listObraExpo.add(obraExpo);
		}
		return listObraExpo;
	}
	
	public List<ObraExpo> selectWhere(List<SearchFilter> filters) throws SQLException{
		List<ObraExpo> listObraExpo = new ArrayList<ObraExpo>();
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(composeSelect(SELECT_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			stmt.setObject(i+1, filters.get(i).getValue());
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			ObraExpo obraExpo = new ObraExpo();
			obraExpo.setIdObra(rs.getInt(1));
			obraExpo.setIdExposicao(rs.getInt(2));
			listObraExpo.add(obraExpo);
		}
		return listObraExpo;
	}
	
	public boolean insert(ObraExpo obraExpo) throws SQLException{
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, obraExpo.getIdObra());
		stmt.setInt(2, obraExpo.getIdExposicao());
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
	public boolean update(String columnToUpdate, Object value, String columnWhere, String criteria, Object valueWhere) throws SQLException{
		return super.update(UPDATE_BASE, columnToUpdate, value, columnWhere, criteria, valueWhere);
	}

	public boolean delete(String columnToDelete, String criteria, Object value) throws SQLException{
		return super.delete(DELETE_BASE, columnToDelete, criteria, value);
	}
}
