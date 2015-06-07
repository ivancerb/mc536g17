package br.unicamp.ic.mc536.grupo17.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.unicamp.ic.mc536.grupo17.dao.ObraDao;
import br.unicamp.ic.mc536.grupo17.dao.PinturaDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Pintura;

public class PinturaDaoTest {
	
	private PinturaDao pinturaDao;
	
	public PinturaDaoTest(){
		this.pinturaDao = new PinturaDao();
	}
	
	//@org.junit.Test 
	public void testInsert(){
		List<Pintura> pinturaList = generateMockupPinturas();
		for(Pintura pintura : pinturaList){
			try{
				pinturaDao.insert(pintura);
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
		List<Pintura> pinturaList = null;
		try{
			pinturaList = pinturaDao.selectPinturas();
			for(Pintura pintura : pinturaList){
				System.out.println(pintura.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(pinturaList);
	}
	
	//@org.junit.Test
	public void testSelectWhere(){
		List<Pintura> pinturaList = null;
		try{
			List<CompoundSearchFilter> filterList = new ArrayList<CompoundSearchFilter>();
			CompoundSearchFilter filter1 = new CompoundSearchFilter("PINTURA", PinturaDao.MATERIAIS, "=", "material 2", false); filterList.add(filter1);
			CompoundSearchFilter filter2 = new CompoundSearchFilter("OBRA", ObraDao.DATA, ">", 1, false); filterList.add(filter2);
			
			pinturaList = pinturaDao.selectPinturasWhere(filterList);
			
			for(Pintura pintura : pinturaList){
				System.out.println(pintura.toString());
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
		Assert.assertNotNull(pinturaList);
	}
	
	//@org.junit.Test 
	public void testDelete(){
		try{
			pinturaDao.delete(PinturaDao.TITULO, "=", "Titulo 6");
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			System.out.println("Error code: " + e.getErrorCode());
			e.printStackTrace();	
		}
	}
	
	public List<Pintura> generateMockupPinturas(){
		List<Pintura> listPintura = new ArrayList<Pintura>();
		for(int i=1; i<7; i++){
			Pintura pintura = new Pintura();
			pintura.setIdObra(i);
			pintura.setIdEstilo(i/2 + 1);
			pintura.setTitulo("Titulo " + i);
			pintura.setData(i);
			pintura.setDescricao("Descricao " + i);
			pintura.setTags("Tags " + i);
			pintura.setPathImagem("path/" + i);
			pintura.setPathImagem("P");
			pintura.setMateriais("material " + i);
			pintura.setAltura(i);
			pintura.setLargura(i);
			listPintura.add(pintura);
		}
		return listPintura;
	}

}

