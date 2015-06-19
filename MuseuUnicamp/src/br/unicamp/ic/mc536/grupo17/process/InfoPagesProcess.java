package br.unicamp.ic.mc536.grupo17.process;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.ArtistaDao;
import br.unicamp.ic.mc536.grupo17.dao.AudiovisualDao;
import br.unicamp.ic.mc536.grupo17.dao.CuradorDao;
import br.unicamp.ic.mc536.grupo17.dao.EsculturaDao;
import br.unicamp.ic.mc536.grupo17.dao.EstiloDao;
import br.unicamp.ic.mc536.grupo17.dao.ExposicaoDao;
import br.unicamp.ic.mc536.grupo17.dao.ObraDao;
import br.unicamp.ic.mc536.grupo17.dao.ObrasFromExpoDao;
import br.unicamp.ic.mc536.grupo17.dao.PinturaDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;
import br.unicamp.ic.mc536.grupo17.model.Curador;
import br.unicamp.ic.mc536.grupo17.model.Escultura;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Exposicao;
import br.unicamp.ic.mc536.grupo17.model.Pintura;
import br.unicamp.ic.mc536.grupo17.model.results.AudiovisualSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.EsculturaSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.PinturaSearchResult;

public class InfoPagesProcess {

	
	public InfoPagesProcess(){
		
	}
	
	/**
	 * 
	 * @param artistId
	 * @return
	 * @throws SQLException
	 */
	public List<Artista> getArtistInfo(String artistId) throws SQLException{
		ArtistaDao artistaDao = new ArtistaDao();
		//filtrando por id
		SearchFilter sf = new SearchFilter(ArtistaDao.ID_ARTISTA, "=", artistId); 
		List<SearchFilter> filterList  = new ArrayList<SearchFilter>();
		filterList.add(sf);
		//executa a busca no banco
		return artistaDao.selectWhere(filterList);
	}
	
	/**
	 * 
	 * @param pinturaId
	 * @return
	 * @throws SQLException
	 */
	public List<Pintura> getPinturaInfo(String pinturaId) throws SQLException{
		PinturaDao pinturaDao = new PinturaDao();
		//filtrando por id
		CompoundSearchFilter sf = new CompoundSearchFilter("OBRA", ObraDao.ID_OBRA, "=", pinturaId, false); 
		List<CompoundSearchFilter> filterList  = new ArrayList<CompoundSearchFilter>();
		filterList.add(sf);
		//executa a busca no banco
		return pinturaDao.selectPinturasWhere(filterList);
	}

    /**
     * 
     * @param esculturaId
     * @return
     * @throws SQLException
     */
	public List<Escultura> getEsculturaInfo(String esculturaId) throws SQLException{
		EsculturaDao esculturaDao = new EsculturaDao();
		//filtrando por id
		CompoundSearchFilter sf = new CompoundSearchFilter("OBRA", ObraDao.ID_OBRA, "=", esculturaId, false); 
		List<CompoundSearchFilter> filterList  = new ArrayList<CompoundSearchFilter>();
		filterList.add(sf);
		//executa a busca no banco
		return esculturaDao.selectEsculturasWhere(filterList);
	}
	
	/**
	 * 
	 * @param audiovisualId
	 * @return
	 * @throws SQLException
	 */
	public List<Audiovisual> getAudiovisualInfo(String audiovisualId) throws SQLException{
		AudiovisualDao audiovisualDao = new AudiovisualDao();
		//filtrando por id
		CompoundSearchFilter sf = new CompoundSearchFilter("OBRA", ObraDao.ID_OBRA, "=", audiovisualId, false); 
		List<CompoundSearchFilter> filterList  = new ArrayList<CompoundSearchFilter>();
		filterList.add(sf);
		//executa a busca no banco
		return audiovisualDao.selectAudiovisualsWhere(filterList);
	}
	
	/**
	 * 
	 * @param estiloId
	 * @return
	 * @throws SQLException
	 */
	public List<Estilo> getEstiloInfo(String estiloId) throws SQLException{
		EstiloDao estiloDao = new EstiloDao();
		//filtrando por id
		SearchFilter sf = new SearchFilter(EstiloDao.ID_ESTILO, "=", estiloId); 
		List<SearchFilter> filterList  = new ArrayList<SearchFilter>();
		filterList.add(sf);
		//executa a busca no banco
		return estiloDao.selectWhere(filterList);
	}
	
	/**
	 * 
	 * @param estiloId
	 * @return
	 * @throws SQLException
	 */
	public List<Exposicao> getAllExpos() throws SQLException{
		ExposicaoDao exposicaoDao = new ExposicaoDao();
		return exposicaoDao.selectAllOrderedByEndDate();
	}
	
	public List<Exposicao> getExpoInfo(int expoId) throws SQLException{
		ExposicaoDao exposicaoDao = new ExposicaoDao();
		SearchFilter sf = new SearchFilter(ExposicaoDao.ID_EXPOSICAO, "=", expoId);
		List<SearchFilter> filterList = new ArrayList<SearchFilter>();
		filterList.add(sf);
		List<Exposicao> listExpo = exposicaoDao.selectWhere(filterList);
		return listExpo;
	}
	
	public String getCuradorFromExpo(int curadorId) throws SQLException{
		CuradorDao curadorDao = new CuradorDao();
		SearchFilter sf = new SearchFilter(CuradorDao.ID_CURADOR, "=", curadorId);
		List<SearchFilter> filterList = new ArrayList<SearchFilter>();
		filterList.add(sf);
		List<Curador> listCurador = curadorDao.selectWhere(filterList);
		return listCurador.get(0).getNome();
	}
	
	public List<Exposicao> getActiveExpos() throws SQLException{
		ExposicaoDao exposicaoDao = new ExposicaoDao();
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		SearchFilter sf = new SearchFilter(ExposicaoDao.DATA_FIM, ">", currentTimestamp);
		List<SearchFilter> filterList = new ArrayList<SearchFilter>();
		filterList.add(sf);
		List<Exposicao> listExpo = exposicaoDao.selectWhere(filterList);
		return listExpo;
	}
	
	public List<PinturaSearchResult> getPinturasFromExpo(int expoId) throws SQLException{
		ObrasFromExpoDao obrasFromExpoDao = new ObrasFromExpoDao();
		return obrasFromExpoDao.selectPinturasFromExpo(expoId);
	}
	
	public List<EsculturaSearchResult> getEsculturasFromExpo(int expoId) throws SQLException{
		ObrasFromExpoDao obrasFromExpoDao = new ObrasFromExpoDao();
		return obrasFromExpoDao.selectEsculturasFromExpo(expoId);
	}
	
	public List<AudiovisualSearchResult> getAudiovisualFromExpo(int expoId) throws SQLException{
		ObrasFromExpoDao obrasFromExpoDao = new ObrasFromExpoDao();
		return obrasFromExpoDao.selectAudiovisualsFromExpo(expoId);
	}
	
}
