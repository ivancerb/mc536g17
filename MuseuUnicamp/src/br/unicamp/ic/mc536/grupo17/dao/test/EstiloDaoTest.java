package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.EstiloDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Estilo;

public class EstiloDaoTest {
	
	private EstiloDao estiloDao;
	
	public EstiloDaoTest(){
		this.estiloDao = new EstiloDao();
	}
	
	//ok
	//@org.junit.Test
	public void testInsert(){
		List<Estilo> artistList = generateMockupEstilos();
		for(Estilo estilo : artistList){
			try{
				estiloDao.insert(estilo);
			}
			catch (SQLException e){
				System.out.println(e.getMessage());
				System.out.println("Error code: " + e.getErrorCode());
				e.printStackTrace();	
			}
		}
	}
	
	//OK
	//@org.junit.Test 
	public void testSelectAll(){
		List<Estilo> artistList = null;
		try{
			artistList = estiloDao.select();
			for(Estilo estilo : artistList){
				System.out.println(estilo.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(artistList);
	}
	
	//OK
	//@org.junit.Test 
	public void testSelectWhere(){
		List<Estilo> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(EstiloDao.DESCRICAO, "=", "Descr 7"); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(EstiloDao.INICIO, ">", 3); filterList.add(filter2);
			
			artistList = estiloDao.selectWhere(filterList);
			
			for(Estilo estilo : artistList){
				System.out.println(estilo.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(artistList);
	}
	
	// OK
	//@org.junit.Test
	public void testDelete(){
		try{
			estiloDao.delete(EstiloDao.NOME, "=", "Estilo 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Estilo> generateMockupEstilos(){
		List<Estilo> listEstilo = new ArrayList<Estilo>();
		for(int i=1; i<11; i++){
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(i);
			estilo.setInicio(i);
			estilo.setFim(i+1);
			estilo.setNome("Estilo " + i);
			estilo.setDescricao("Descr " + i);
			listEstilo.add(estilo);
		}
		return listEstilo;
	}

}

