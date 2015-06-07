package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ExposicaoDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Exposicao;

public class ExposicaoDaoTest {
	
	private ExposicaoDao exposicaoDao;
	
	public ExposicaoDaoTest(){
		this.exposicaoDao = new ExposicaoDao();
	}
	
	
	//@org.junit.Test 
	public void testInsert(){
		List<Exposicao> artistList = generateMockupArtists();
		for(Exposicao exposicao : artistList){
			try{
				exposicaoDao.insert(exposicao);
			}
			catch (SQLException e){
				System.out.println(e.getMessage());
				System.out.println("Error code: " + e.getErrorCode());
				e.printStackTrace();	
			}
		}
	}
	
	//OK
	@org.junit.Test 
	public void testSelectAll(){
		List<Exposicao> artistList = null;
		try{
			artistList = exposicaoDao.select();
			for(Exposicao exposicao : artistList){
				System.out.println(exposicao.toString());
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
		List<Exposicao> artistList = null;
		try{
			List<SearchFilter> filterList = new ArrayList<SearchFilter>();
			SearchFilter filter1 = new SearchFilter(ExposicaoDao.NOME, "=", "Expo 9"); filterList.add(filter1);
			SearchFilter filter2 = new SearchFilter(ExposicaoDao.DATA_FIM, ">", 3); filterList.add(filter2);
			
			artistList = exposicaoDao.selectWhere(filterList);
			
			for(Exposicao exposicao : artistList){
				System.out.println(exposicao.toString());
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
			exposicaoDao.delete(ExposicaoDao.NOME, "=", "Expo 10");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Exposicao> generateMockupArtists(){
		List<Exposicao> listExposicao = new ArrayList<Exposicao>();
		for(int i=1; i<5; i++){
			Exposicao exposicao = new Exposicao();
			exposicao.setIdExposicao(i);
			exposicao.setIdCurador(i);
			exposicao.setIdMuseu(i);
			exposicao.setNome("Expo " + i);
			exposicao.setDescricao("Descr " + i);
			exposicao.setDataInicio(new Date(10000000 * i));
			exposicao.setDataFim(new Date(10000000 * i));
			exposicao.setStatus("A");
			listExposicao.add(exposicao);
		}
		return listExposicao;
	}

}

