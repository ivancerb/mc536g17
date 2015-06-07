package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ObraExpoDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.compound.ObraExpo;

public class ObraExpoDaoTest {
	
	private ObraExpoDao obraExpoDao;
	
	public ObraExpoDaoTest(){
		this.obraExpoDao = new ObraExpoDao();
	}
	
	//OK
	@org.junit.Test 
	public void testInsert(){
		List<ObraExpo> artistList = generateMockupArtists();
		for(ObraExpo obraExpo : artistList){
			try{
				obraExpoDao.insert(obraExpo);
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
		List<ObraExpo> artistList = null;
		try{
			artistList = obraExpoDao.select();
			for(ObraExpo obraExpo : artistList){
				System.out.println(obraExpo.toString());
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
		List<ObraExpo> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(ObraExpoDao.ID_EXPOSICAO, ">", 2); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(ObraExpoDao.ID_OBRA, "=", 4); filterList.add(filter2);
			
			artistList = obraExpoDao.selectWhere(filterList);
			
			for(ObraExpo obraExpo : artistList){
				System.out.println(obraExpo.toString());
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
			obraExpoDao.delete(ObraExpoDao.ID_EXPOSICAO, "=", 6);
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<ObraExpo> generateMockupArtists(){
		List<ObraExpo> listObraExpo = new ArrayList<ObraExpo>();
		for(int i=1; i<5; i++){
			ObraExpo obraExpo = new ObraExpo();
			obraExpo.setIdObra(i);
			obraExpo.setIdExposicao(i);
			listObraExpo.add(obraExpo);
		}
		return listObraExpo;
	}

}

