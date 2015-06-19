package br.unicamp.ic.mc536.grupo17.process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.ArtistaDao;
import br.unicamp.ic.mc536.grupo17.dao.AudiovisualDao;
import br.unicamp.ic.mc536.grupo17.dao.EsculturaDao;
import br.unicamp.ic.mc536.grupo17.dao.EstiloDao;
import br.unicamp.ic.mc536.grupo17.dao.ObraDao;
import br.unicamp.ic.mc536.grupo17.dao.PinturaDao;
import br.unicamp.ic.mc536.grupo17.dao.SearchDao;
import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.dao.filter.SearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.ArtistSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.AudiovisualSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.EsculturaSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.PinturaSearchResult;

public class SearchProcess {
	
	private SearchDao searchDao = new SearchDao();
	private EstiloDao estiloDao = new EstiloDao();
	

	private List<CompoundSearchFilter> pinturaSearchFilterList = new ArrayList<CompoundSearchFilter>();
	private List<CompoundSearchFilter> esculturaSearchFilterList  = new ArrayList<CompoundSearchFilter>();;
	private List<CompoundSearchFilter> audiovisualSearchFilterList = new ArrayList<CompoundSearchFilter>();;
	
	public List<SearchResult> getResults(String artist, String artwork, String material){
		return mockupSearchResults(artist, artwork, material);
	}
	
	/**
	 * Retorna os resultados de Artista e Estilo para os parametros enviados na busca
	 * @param nome
	 * @param pais
	 * @param estilo
	 * @param dataInicioChecked
	 * @param dataInicio
	 * @param dataFimChecked
	 * @param dataFim
	 * @return
	 * @throws SQLException
	 */
	public List<ArtistSearchResult> getArtistaResults(String nome, String pais, String estilo, 
			 boolean dataInicioChecked, int dataInicio, boolean dataFimChecked, 
			 int dataFim) throws SQLException{
		List<CompoundSearchFilter> searchFilterList = new ArrayList<CompoundSearchFilter>();
		if(nome!=null && nome.trim()!=""){ 
			searchFilterList.add(new CompoundSearchFilter("ARTISTA", ArtistaDao.NOME, "LIKE", "%" + nome + "%", false));
		}
		if(pais!=null && pais.trim()!=""){ 
			searchFilterList.add(new CompoundSearchFilter("ARTISTA", ArtistaDao.ORIGEM, "=", pais, false));
		}
		if(estilo!=null && estilo.trim()!="" && !estilo.equals("Todos")){ 
			searchFilterList.add(new CompoundSearchFilter("ESTILO", EstiloDao.NOME, "=", estilo, false));
		}
		if(dataInicioChecked && dataInicio!=-1){ 
			searchFilterList.add(new CompoundSearchFilter("ARTISTA", ArtistaDao.DATA_NASC, ">", dataInicio, false));
		}
		if(dataFimChecked && dataFim!=-1){ 
			searchFilterList.add(new CompoundSearchFilter("ARTISTA", ArtistaDao.DATA_FALESC, "<", dataFim, false));
		}
		return searchDao.selectArtistaEstilo(searchFilterList);
	}
	
	/**
	 * Returns empty arraylist if no results found or if no filters for PINTURA
	 * @return
	 */
	public List<PinturaSearchResult> searchPinturas() throws SQLException{
		System.out.println("process p 1");
		List<PinturaSearchResult> resultList=null;
		if(!this.pinturaSearchFilterList.isEmpty()){
			resultList = searchDao.selectPinturasWhere(this.pinturaSearchFilterList); 
		}
		if(resultList==null) { resultList = new ArrayList<PinturaSearchResult>(); } 
		System.out.println("process p 2");
		return resultList;
	}
	
	/**
	 * Returns empty arraylist if no results found or if no filters for PINTURA
	 * @return
	 */
	public List<EsculturaSearchResult> searchEsculturas() throws SQLException{
		System.out.println("process e 1");
		List<EsculturaSearchResult> resultList=null;
		if(!this.esculturaSearchFilterList.isEmpty()){
			resultList = searchDao.selectEsculturasWhere(this.esculturaSearchFilterList); 
		}
		if(resultList==null) { resultList = new ArrayList<EsculturaSearchResult>(); } 
		System.out.println("process e 2");
		return resultList;
	}
	
	/**
	 * Returns empty arraylist if no results found or if no filters for PINTURA
	 * @return
	 */
	public List<AudiovisualSearchResult> searchAudiovisual() throws SQLException{
		System.out.println("process a 1");
		List<AudiovisualSearchResult> resultList=null;
		if(!this.audiovisualSearchFilterList.isEmpty()){
			resultList = searchDao.selectAudiovisualsWhere(this.audiovisualSearchFilterList); 
		}
		if(resultList==null) { resultList = new ArrayList<AudiovisualSearchResult>(); } 
		System.out.println("process a 2");
		return resultList;
	}
	
	/**
	 * 
	 * @param titulo
	 * @param autor
	 * @param isPintura
	 * @param isEscultura
	 * @param isAudiovisual
	 * @param materialSearchChecked
	 * @param material
	 * @param tipoMidiaChecked
	 * @param tipoMidia
	 * @param pais
	 * @param estilo
	 * @param dataInicioChecked
	 * @param dataInicio
	 * @param dataFimChecked
	 * @param dataFim
	 * @throws SQLException
	 */
	public void setObraSearchFilters(String titulo, String autor, boolean isPintura, 
			boolean isEscultura, boolean isAudiovisual, boolean materialSearchChecked, 
			String material, boolean tipoMidiaChecked, String tipoMidia, String pais, 
			String estilo, boolean dataInicioChecked, int dataInicio, 
			boolean dataFimChecked, int dataFim) throws SQLException{
		System.out.println("process1");
		//common filters
		List<CompoundSearchFilter> searchFilterList = new ArrayList<CompoundSearchFilter>();
		if(titulo!=null && titulo.trim()!=""){ 
			searchFilterList.add(new CompoundSearchFilter("OBRA", ObraDao.TITULO, "LIKE", "%" + titulo + "%", false));
		}
		if(autor!=null && autor.trim()!=""){ 
			searchFilterList.add(new CompoundSearchFilter("ARTISTA", ArtistaDao.NOME, "LIKE", "%" + autor + "%", false));
		}
		if(pais!=null && pais.trim()!=""){ 
			searchFilterList.add(new CompoundSearchFilter("ARTISTA", ArtistaDao.ORIGEM, "=", pais, false));
		}
		
		if(estilo!=null && estilo.trim()!="" && !estilo.equals("Todos")){ 
			searchFilterList.add(new CompoundSearchFilter("ESTILO", EstiloDao.NOME, "=", estilo, false));
		}
		if(dataInicioChecked && dataInicio!=-1){ 
			searchFilterList.add(new CompoundSearchFilter("OBRA", ObraDao.DATA, ">", dataInicio, false));
		}
		if(dataFimChecked && dataFim!=-1){ 
			searchFilterList.add(new CompoundSearchFilter("OBRA", ObraDao.DATA, "<", dataFim, false));
		}
		
		System.out.println("process2");
		
		boolean searchAllArtworkTypes = searchAllArtworkTypes(isPintura, isEscultura, isAudiovisual);
		//creating and adding specific filters to respective lists
		//material works only for PINTURA and ESCULTURA tables
		if(material!=null && !material.trim().equals("")){
			if(searchAllArtworkTypes){
				pinturaSearchFilterList.add(new CompoundSearchFilter("PINTURA", PinturaDao.MATERIAIS, "LIKE", "%" + material + "%", false));
				esculturaSearchFilterList.add(new CompoundSearchFilter("ESCULTURA", EsculturaDao.MATERIAIS, "LIKE", "%" + material + "%", false));			
			}else{
				if(isPintura){
					pinturaSearchFilterList.add(new CompoundSearchFilter("PINTURA", PinturaDao.MATERIAIS, "LIKE", "%" + material + "%", false));
				}
				if(isEscultura){
					esculturaSearchFilterList.add(new CompoundSearchFilter("ESCULTURA", EsculturaDao.MATERIAIS, "LIKE", "%" + material + "%", false));	
				}
			}
		}
		//tipo midia works only for AUDIOVISUAL table
		if(tipoMidiaChecked && tipoMidia!=null && !tipoMidia.trim().equals("")){
			this.audiovisualSearchFilterList.add(new CompoundSearchFilter("AUDIOVISUAL", AudiovisualDao.TIPO_MIDIA, "LIKE", "%" + tipoMidia + "%", false));	
		}
		
		System.out.println("process3");
		
		if(searchAllArtworkTypes){
			//adding common filters to specific filter lists
			this.pinturaSearchFilterList.addAll(searchFilterList);
			this.esculturaSearchFilterList.addAll(searchFilterList);
			this.audiovisualSearchFilterList.addAll(searchFilterList);
		}else{
			if(isPintura){
				this.pinturaSearchFilterList.addAll(searchFilterList);
			}
			if(isEscultura){
				System.out.println("isEscultura adding filters");
				this.esculturaSearchFilterList.addAll(searchFilterList);		
			}
			if(isAudiovisual){
				this.esculturaSearchFilterList.addAll(searchFilterList);
			}
		}
	}
	
	public List<String> selectNomeEstiloList() throws SQLException{
		return estiloDao.selectNomeEstiloList();
	}
	
	private boolean searchAllArtworkTypes(boolean isPintura, boolean isEscultura, boolean isAudiovisual){
		return (!isPintura && !isEscultura && !isAudiovisual) || (isPintura && isEscultura && isAudiovisual);
	}
	
//	private String composeTipoObrasSetString(boolean isPintura, boolean isEscultura, boolean isAudiovisual){
//		if(!isPintura && !isEscultura && !isAudiovisual) return ""; 
//		String tipoObrasSet = "("; boolean first = true;
//		if(isPintura) {
//			tipoObrasSet += "P";
//			first = false;
//		}
//		if(isEscultura) {
//			if(!first) tipoObrasSet += ", ";
//			tipoObrasSet += "E";
//			if(first) first = false;
//		}
//		if(isAudiovisual) {
//			if(!first) tipoObrasSet += ", ";
//			tipoObrasSet += "A";
//		}
//		tipoObrasSet+=")";
//		System.out.println(tipoObrasSet);
//		return tipoObrasSet;
//	}
	
	
	
	private List<SearchResult> mockupSearchResults(String artist, String artwork, String material){
		List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		for(int i=1; i<=10; i++){
			SearchResult result = new SearchResult();
			result.setArtistId(i);
			result.setArtworkId(i);
			result.setArtist("Artist " + i);
			result.setArtwork("Artwork " + artwork);
			searchResultList.add(result);
		}
		return searchResultList;
	}
}
