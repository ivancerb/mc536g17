package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.MuseuDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Museu;

public class MuseuDaoTest {
	
	private MuseuDao museuDao;
	
	public MuseuDaoTest(){
		this.museuDao = new MuseuDao();
	}
	
	//OK
	//@org.junit.Test 
	public void testInsert(){
		List<Museu> artistList = generateMockupArtists();
		for(Museu museu : artistList){
			try{
				museuDao.insert(museu);
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
		List<Museu> artistList = null;
		try{
			artistList = museuDao.select();
			for(Museu museu : artistList){
				System.out.println(museu.toString());
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
		List<Museu> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(MuseuDao.NOME, "=", "Nome 9"); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(MuseuDao.CONTATO, "=", "Contato 9"); filterList.add(filter2);
			
			artistList = museuDao.selectWhere(filterList);
			
			for(Museu museu : artistList){
				System.out.println(museu.toString());
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
	@org.junit.Test 
	public void testDelete(){
		try{
			museuDao.delete(MuseuDao.NOME, "=", "Nome 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Museu> generateMockupArtists(){
		List<Museu> listMuseu = new ArrayList<Museu>();
		for(int i=1; i<11; i++){
			Museu museu = new Museu();
			museu.setIdMuseu(i);
			museu.setNome("Nome " + i);
			museu.setContato("Contato " + i);
			listMuseu.add(museu);
		}
		return listMuseu;
	}

}

