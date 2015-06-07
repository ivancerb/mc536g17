package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.CuradorDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Curador;

public class CuradorDaoTest {
	
	private CuradorDao curadorDao;
	
	public CuradorDaoTest(){
		this.curadorDao = new CuradorDao();
	}
	
	//OK
	//@org.junit.Test 
	public void testInsert(){
		List<Curador> artistList = generateMockupArtists();
		for(Curador curador : artistList){
			try{
				curadorDao.insert(curador);
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
		List<Curador> artistList = null;
		try{
			artistList = curadorDao.select();
			for(Curador curador : artistList){
				System.out.println(curador.toString());
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
		List<Curador> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(CuradorDao.NOME, "=", "Nome 9"); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(CuradorDao.CONTATO, "=", "Contato 9"); filterList.add(filter2);
			
			artistList = curadorDao.selectWhere(filterList);
			
			for(Curador curador : artistList){
				System.out.println(curador.toString());
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
	public void testDelete(){
		try{
			curadorDao.delete(CuradorDao.NOME, "=", "Nome 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Curador> generateMockupArtists(){
		List<Curador> listCurador = new ArrayList<Curador>();
		for(int i=1; i<11; i++){
			Curador curador = new Curador();
			curador.setIdCurador(i);
			curador.setNome("Nome " + i);
			curador.setContato("Contato " + i);
			listCurador.add(curador);
		}
		return listCurador;
	}

}

