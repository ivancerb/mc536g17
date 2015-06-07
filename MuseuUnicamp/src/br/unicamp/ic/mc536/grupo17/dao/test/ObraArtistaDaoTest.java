package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ObraArtistaDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.compound.ObraArtista;

public class ObraArtistaDaoTest {
	
	private ObraArtistaDao obraArtistaDao;
	
	public ObraArtistaDaoTest(){
		this.obraArtistaDao = new ObraArtistaDao();
	}
	
	//OK
	@org.junit.Test 
	public void testInsert(){
		List<ObraArtista> artistList = generateMockupArtists();
		for(ObraArtista obraArtista : artistList){
			try{
				obraArtistaDao.insert(obraArtista);
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
		List<ObraArtista> artistList = null;
		try{
			artistList = obraArtistaDao.select();
			for(ObraArtista obraArtista : artistList){
				System.out.println(obraArtista.toString());
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
		List<ObraArtista> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(ObraArtistaDao.ID_OBRA, ">", 2); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(ObraArtistaDao.ID_OBRA, "=", 4); filterList.add(filter2);
			
			artistList = obraArtistaDao.selectWhere(filterList);
			
			for(ObraArtista obraArtista : artistList){
				System.out.println(obraArtista.toString());
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
			obraArtistaDao.delete(ObraArtistaDao.ID_ARTISTA, "=", 6);
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<ObraArtista> generateMockupArtists(){
		List<ObraArtista> listObraArtista = new ArrayList<ObraArtista>();
		for(int i=1; i<5; i++){
			ObraArtista obraArtista = new ObraArtista();
			obraArtista.setIdObra(i);
			obraArtista.setIdArtista(i);
			listObraArtista.add(obraArtista);
		}
		return listObraArtista;
	}

}

