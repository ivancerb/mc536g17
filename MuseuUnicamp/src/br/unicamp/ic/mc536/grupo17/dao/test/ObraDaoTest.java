package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ObraDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Obra;

public class ObraDaoTest {
	
	private ObraDao obraDao;
	
	public ObraDaoTest(){
		this.obraDao = new ObraDao();
	}
	
	@org.junit.Test 
	public void testInsert(){
		List<Obra> artistList = generateMockupArtists();
		for(Obra obra : artistList){
			try{
				obraDao.insert(obra);
			}
			catch (SQLException e){
				System.out.println(e.getMessage());
				System.out.println("Error code: " + e.getErrorCode());
				e.printStackTrace();	
			}
		}
	}
	
	//@org.junit.Test 
	public void testSelectAll(){
		List<Obra> artistList = null;
		try{
			artistList = obraDao.select();
			for(Obra obra : artistList){
				System.out.println(obra.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(artistList);
	}
	
	//@org.junit.Test
	public void testSelectWhere(){
		List<Obra> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(ObraDao.DESCRICAO, "=", "Descricao 7"); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(ObraDao.DATA, ">", 3); filterList.add(filter2);
			
			artistList = obraDao.selectWhere(filterList);
			
			for(Obra obra : artistList){
				System.out.println(obra.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(artistList);
	}
	
	//@org.junit.Test 
	public void testDelete(){
		try{
			obraDao.delete(ObraDao.TITULO, "=", "Titulo 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Obra> generateMockupArtists(){
		List<Obra> listObra = new ArrayList<Obra>();
		for(int i=1; i<7; i++){
			Obra obra = new Obra();
			obra.setIdObra(i);
			obra.setIdEstilo(i/2 + 1);
			obra.setTitulo("Titulo " + i);
			obra.setData(i);
			obra.setDescricao("Descricao " + i);
			obra.setTags("Tags " + i);
			obra.setPathImagem("path/" + i);
			obra.setPathImagem("P");
			listObra.add(obra);
		}
		return listObra;
	}

}

