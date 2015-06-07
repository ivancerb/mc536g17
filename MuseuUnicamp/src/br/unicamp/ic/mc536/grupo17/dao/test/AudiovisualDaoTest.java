package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ObraDao;
import br.unicamp.ic.mc536.grupo17.dao.AudiovisualDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;

public class AudiovisualDaoTest {
	
	private AudiovisualDao audiovisualDao;
	
	public AudiovisualDaoTest(){
		this.audiovisualDao = new AudiovisualDao();
	}
	
	//@org.junit.Test 
	public void testInsert(){
		List<Audiovisual> audiovisualList = generateMockupAudiovisuals();
		for(Audiovisual audiovisual : audiovisualList){
			try{
				audiovisualDao.insert(audiovisual);
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
		List<Audiovisual> audiovisualList = null;
		try{
			audiovisualList = audiovisualDao.selectAudiovisuals();
			for(Audiovisual audiovisual : audiovisualList){
				System.out.println(audiovisual.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(audiovisualList);
	}
	
	//@org.junit.Test
	public void testSelectWhere(){
		List<Audiovisual> audiovisualList = null;
		try{
			List<CompoundSearchFilter> filterList = new ArrayList<CompoundSearchFilter>();
			CompoundSearchFilter filter1 = new CompoundSearchFilter("AUDIOVISUAL", AudiovisualDao.TIPO_MIDIA, "=", "midia 2", false); filterList.add(filter1);
			CompoundSearchFilter filter2 = new CompoundSearchFilter("OBRA", ObraDao.DATA, ">", 1, false); filterList.add(filter2);
			
			audiovisualList = audiovisualDao.selectAudiovisualsWhere(filterList);
			
			for(Audiovisual audiovisual : audiovisualList){
				System.out.println(audiovisual.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(audiovisualList);
	}
	
	//@org.junit.Test 
	public void testDelete(){
		try{
			audiovisualDao.delete(AudiovisualDao.TITULO, "=", "Titulo 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Audiovisual> generateMockupAudiovisuals(){
		List<Audiovisual> listAudiovisual = new ArrayList<Audiovisual>();
		for(int i=1; i<7; i++){
			Audiovisual audiovisual = new Audiovisual();
			audiovisual.setIdObra(i);
			audiovisual.setIdEstilo(i/2 + 1);
			audiovisual.setTitulo("Titulo " + i);
			audiovisual.setData(i);
			audiovisual.setDescricao("Descricao " + i);
			audiovisual.setTags("Tags " + i);
			audiovisual.setPathImagem("path/" + i);
			audiovisual.setTipo("A");
			audiovisual.setTipo_midia("midia " + i);
			listAudiovisual.add(audiovisual);
		}
		return listAudiovisual;
	}

}

