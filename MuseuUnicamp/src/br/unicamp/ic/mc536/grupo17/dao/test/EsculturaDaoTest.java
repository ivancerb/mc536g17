package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ObraDao;
import br.unicamp.ic.mc536.grupo17.dao.EsculturaDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Escultura;

public class EsculturaDaoTest {
	
	private EsculturaDao esculturaDao;
	
	public EsculturaDaoTest(){
		this.esculturaDao = new EsculturaDao();
	}
	
	//@org.junit.Test 
	public void testInsert(){
		List<Escultura> esculturaList = generateMockupEsculturas();
		for(Escultura escultura : esculturaList){
			try{
				esculturaDao.insert(escultura);
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
		List<Escultura> esculturaList = null;
		try{
			esculturaList = esculturaDao.selectEsculturas();
			for(Escultura escultura : esculturaList){
				System.out.println(escultura.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(esculturaList);
	}
	
	@org.junit.Test
	public void testSelectWhere(){
		List<Escultura> esculturaList = null;
		try{
			List<CompoundSearchFilter> filterList = new ArrayList<CompoundSearchFilter>();
			CompoundSearchFilter filter1 = new CompoundSearchFilter("ESCULTURA", EsculturaDao.MATERIAIS, "=", "material 2", false); filterList.add(filter1);
			CompoundSearchFilter filter2 = new CompoundSearchFilter("OBRA", ObraDao.DATA, ">", 1, false); filterList.add(filter2);
			
			esculturaList = esculturaDao.selectEsculturasWhere(filterList);
			
			for(Escultura escultura : esculturaList){
				System.out.println(escultura.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(esculturaList);
	}
	
	//@org.junit.Test 
	public void testDelete(){
		try{
			esculturaDao.delete(EsculturaDao.TITULO, "=", "Titulo 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Escultura> generateMockupEsculturas(){
		List<Escultura> listEscultura = new ArrayList<Escultura>();
		for(int i=1; i<7; i++){
			Escultura escultura = new Escultura();
			escultura.setIdObra(i);
			escultura.setIdEstilo(i/2 + 1);
			escultura.setTitulo("Titulo " + i);
			escultura.setData(i);
			escultura.setDescricao("Descricao " + i);
			escultura.setTags("Tags " + i);
			escultura.setPathImagem("path/" + i);
			escultura.setPathImagem("P");
			escultura.setMateriais("material " + i);
			escultura.setAltura(i);
			escultura.setLargura(i);
			listEscultura.add(escultura);
		}
		return listEscultura;
	}

}

