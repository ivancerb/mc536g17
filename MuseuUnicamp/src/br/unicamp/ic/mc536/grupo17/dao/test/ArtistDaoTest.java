package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ArtistaDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Artista;

public class ArtistDaoTest {
	
	private ArtistaDao artistaDao;
	
	public ArtistDaoTest(){
		this.artistaDao = new ArtistaDao();
	}
	
	//@org.junit.Test OK
	public void testInsert(){
		List<Artista> artistList = generateMockupArtists();
		for(Artista artista : artistList){
			try{
				artistaDao.insert(artista);
			}
			catch (SQLException e){
				System.out.println(e.getMessage());
				System.out.println("Error code: " + e.getErrorCode());
				e.printStackTrace();	
			}
		}
	}
	
	@org.junit.Test 
	public void testSelectAll(){
		List<Artista> artistList = null;
		try{
			artistList = artistaDao.select();
			for(Artista artista : artistList){
				System.out.println(artista.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(artistList);
	}
	
	//@org.junit.Test OK
	public void testSelectWhere(){
		List<Artista> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(ArtistaDao.ORIGEM, "=", "Origem 7"); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(ArtistaDao.DATA_NASC, ">", 3); filterList.add(filter2);
			
			artistList = artistaDao.selectWhere(filterList);
			
			for(Artista artista : artistList){
				System.out.println(artista.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(artistList);
	}
	
	//@org.junit.Test OK
	public void testDelete(){
		try{
			artistaDao.delete(ArtistaDao.NOME, "=", "Artista Nome 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Artista> generateMockupArtists(){
		List<Artista> listArtist = new ArrayList<Artista>();
		for(int i=1; i<11; i++){
			Artista artista = new Artista();
			artista.setNome("Artista Nome " + i);
			artista.setDataNasc(i);
			artista.setDataFalesc(i);
			artista.setOrigem("Origem " + i);
			artista.setBio("Bio " + i);
			listArtist.add(artista);
		}
		return listArtist;
	}

}

