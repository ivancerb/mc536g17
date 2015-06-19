package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.dao.filter.CompoundSearchFilter;
import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;
import br.unicamp.ic.mc536.grupo17.model.Escultura;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Pintura;
import br.unicamp.ic.mc536.grupo17.model.results.ArtistSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.AudiovisualSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.EsculturaSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.PinturaSearchResult;

public class SearchDao extends BaseDao{

	private static String SELECT_ARTISTA_E_ESTILO = 
			"SELECT DISTINCT " +  
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM, ARTISTA.BIO, ESTILO.ID_ESTILO, " +		
				" ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO " + 
			" FROM " + 
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO " +
			" WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND ";
			//+ " ARTISTA.NOME=? AND ARTISTA.PAIS=? AND  ARTISTA.DATA_NASC>? AND ARTISTA.DATA_FALESC <? AND ESTILO.NOME=?";
	
	
	private static String SELECT_PINTURAS_WHERE = 
			"SELECT " +
				" OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
				" PINTURA.MATERIAIS, PINTURA.ALTURA, PINTURA.LARGURA, " +
				" ESTILO.ID_ESTILO, ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO, " +
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM " +
			" FROM " +
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO, PINTURA " +
			"WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA " +
				" AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND PINTURA.ID_OBRA = OBRA.ID_OBRA AND ";
	
	private static String SELECT_ESCULTURAS_WHERE = 
			"SELECT " +
				" OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
				" ESCULTURA.MATERIAIS, ESCULTURA.ALTURA, ESCULTURA.LARGURA, ESCULTURA.PROFUNDIDADE, " +
				" ESTILO.ID_ESTILO, ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO, " +
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM " +
			" FROM " +
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO, ESCULTURA " +
			"WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA " +
				" AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND ESCULTURA.ID_OBRA = OBRA.ID_OBRA AND ";
	
	
	private static String SELECT_AUDIOVISUAL_WHERE = 
			"SELECT " +
				" OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
				" AUDIOVISUAL.TIPO_MIDIA, " +
				" ESTILO.ID_ESTILO, ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO, " +
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM " +
			" FROM " +
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO, AUDIOVISUAL " +
			"WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA " +
				" AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND AUDIOVISUAL.ID_OBRA = OBRA.ID_OBRA AND ";
	
	public SearchDao(){
		super();
	}
	
	/**
	 * 
	 * @param filters
	 * @return
	 * @throws SQLException
	 */
	public List<ArtistSearchResult> selectArtistaEstilo(List<CompoundSearchFilter> filters) throws SQLException{
		List<ArtistSearchResult> listArtistSearchResult = new ArrayList<ArtistSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
		
		System.out.println(composeSelectMultipleTables(SELECT_ARTISTA_E_ESTILO, filters));
		
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_ARTISTA_E_ESTILO, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			if(!filters.get(i).isJoin()){
				stmt.setObject(i+1, filters.get(i).getValue());
			}
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			ArtistSearchResult artistSearchResult = new ArtistSearchResult();
			//extraindo artista
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(1));
			artista.setNome(rs.getString(2));
			artista.setDataNasc(rs.getInt(3));
			artista.setDataFalesc(rs.getInt(4));
			artista.setOrigem(rs.getString(5));
			artista.setBio(rs.getString(6));
			//extraindo estilo
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(7));
			estilo.setInicio(rs.getInt(8));
			estilo.setFim(rs.getInt(9));
			estilo.setNome(rs.getString(10));
			estilo.setDescricao(rs.getString(11));
			//setando resultado da busca
			artistSearchResult.setArtista(artista);
			artistSearchResult.setEstilo(estilo);
			listArtistSearchResult.add(artistSearchResult);
		}
		return listArtistSearchResult;
		
	}
	
	/**
	 * 
	 * @param filters
	 * @return
	 * @throws SQLException
	 */
	public List<PinturaSearchResult> selectPinturasWhere(List<CompoundSearchFilter> filters) throws SQLException{
		List<PinturaSearchResult> listPinturaSearchResult = new ArrayList<PinturaSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
		
		System.out.println(composeSelectMultipleTables(SELECT_PINTURAS_WHERE, filters));
		
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_PINTURAS_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			if(!filters.get(i).isJoin()){
				stmt.setObject(i+1, filters.get(i).getValue());
			}
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			PinturaSearchResult pinturaSearchResult = new PinturaSearchResult();
			
			Pintura pintura = new Pintura();
			pintura.setIdObra(rs.getInt(1));
			pintura.setIdEstilo(rs.getInt(2));
			pintura.setTitulo(rs.getString(3));
			pintura.setData(rs.getInt(4));
			pintura.setDescricao(rs.getString(5));
			pintura.setTags(rs.getString(6));
			pintura.setPathImagem(rs.getString(7));
			pintura.setTipo(rs.getString(8));
			pintura.setMateriais(rs.getString(9));
			pintura.setAltura(rs.getDouble(10));
			pintura.setLargura(rs.getDouble(11));
			
			//extraindo estilo
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(12));
			estilo.setInicio(rs.getInt(13));
			estilo.setFim(rs.getInt(14));
			estilo.setNome(rs.getString(15));
			estilo.setDescricao(rs.getString(16));
			
			//extraindo artista
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(17));
			artista.setNome(rs.getString(18));
			artista.setDataNasc(rs.getInt(19));
			artista.setDataFalesc(rs.getInt(20));
			artista.setOrigem(rs.getString(21));

			
			//setando resultado da busca
			pinturaSearchResult.setPintura(pintura);
			pinturaSearchResult.setArtista(artista);
			pinturaSearchResult.setEstilo(estilo);
			listPinturaSearchResult.add(pinturaSearchResult);
			
		}
		return listPinturaSearchResult;
		
	}
	
	/**
	 * 
	 * @param filters
	 * @return
	 * @throws SQLException
	 */
	public List<EsculturaSearchResult> selectEsculturasWhere(List<CompoundSearchFilter> filters) throws SQLException{
		List<EsculturaSearchResult> listEsculturaSearchResult = new ArrayList<EsculturaSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
		
		System.out.println(composeSelectMultipleTables(SELECT_ESCULTURAS_WHERE, filters));
		
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_ESCULTURAS_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			if(!filters.get(i).isJoin()){
				stmt.setObject(i+1, filters.get(i).getValue());
			}
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			EsculturaSearchResult esculturaSearchResult = new EsculturaSearchResult();
			
			Escultura escultura = new Escultura();
			escultura.setIdObra(rs.getInt(1));
			escultura.setIdEstilo(rs.getInt(2));
			escultura.setTitulo(rs.getString(3));
			escultura.setData(rs.getInt(4));
			escultura.setDescricao(rs.getString(5));
			escultura.setTags(rs.getString(6));
			escultura.setPathImagem(rs.getString(7));
			escultura.setTipo(rs.getString(8));
			escultura.setMateriais(rs.getString(9));
			escultura.setAltura(rs.getDouble(10));
			escultura.setLargura(rs.getDouble(11));
			escultura.setProfundidade(rs.getDouble(12));
			
			//extraindo estilo
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(13));
			estilo.setInicio(rs.getInt(14));
			estilo.setFim(rs.getInt(15));
			estilo.setNome(rs.getString(16));
			estilo.setDescricao(rs.getString(17));
			
			//extraindo artista
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(18));
			artista.setNome(rs.getString(19));
			artista.setDataNasc(rs.getInt(20));
			artista.setDataFalesc(rs.getInt(21));
			artista.setOrigem(rs.getString(22));

			
			//setando resultado da busca
			esculturaSearchResult.setEscultura(escultura);
			esculturaSearchResult.setArtista(artista);
			esculturaSearchResult.setEstilo(estilo);
			listEsculturaSearchResult.add(esculturaSearchResult);
			
		}
		return listEsculturaSearchResult;
		
	}
	
	/**
	 * 
	 * @param filters
	 * @return
	 * @throws SQLException
	 */
	public List<AudiovisualSearchResult> selectAudiovisualsWhere(List<CompoundSearchFilter> filters) throws SQLException{
		List<AudiovisualSearchResult> listAudiovisualSearchResult = new ArrayList<AudiovisualSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
		
		System.out.println(composeSelectMultipleTables(SELECT_AUDIOVISUAL_WHERE, filters));

		
		PreparedStatement stmt = connection.prepareStatement(composeSelectMultipleTables(SELECT_AUDIOVISUAL_WHERE, filters));
		int size = filters.size();
		for(int i=0; i<size; i++){
			if(!filters.get(i).isJoin()){
				stmt.setObject(i+1, filters.get(i).getValue());
			}
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			AudiovisualSearchResult audiovisualSearchResult = new AudiovisualSearchResult();
			
			Audiovisual audiovisual = new Audiovisual();
			audiovisual.setIdObra(rs.getInt(1));
			audiovisual.setIdEstilo(rs.getInt(2));
			audiovisual.setTitulo(rs.getString(3));
			audiovisual.setData(rs.getInt(4));
			audiovisual.setDescricao(rs.getString(5));
			audiovisual.setTags(rs.getString(6));
			audiovisual.setPathImagem(rs.getString(7));
			audiovisual.setTipo(rs.getString(8));
			audiovisual.setTipo_midia(rs.getString(9));

			
			//extraindo estilo
			Estilo estilo = new Estilo();
			estilo.setIdEstilo(rs.getInt(10));
			estilo.setInicio(rs.getInt(11));
			estilo.setFim(rs.getInt(12));
			estilo.setNome(rs.getString(13));
			estilo.setDescricao(rs.getString(14));
			
			//extraindo artista
			Artista artista = new Artista();
			artista.setIdArtista(rs.getInt(15));
			artista.setNome(rs.getString(16));
			artista.setDataNasc(rs.getInt(17));
			artista.setDataFalesc(rs.getInt(18));
			artista.setOrigem(rs.getString(19));

			
			//setando resultado da busca
			audiovisualSearchResult.setAudiovisual(audiovisual);
			audiovisualSearchResult.setArtista(artista);
			audiovisualSearchResult.setEstilo(estilo);
			listAudiovisualSearchResult.add(audiovisualSearchResult);
			
		}
		return listAudiovisualSearchResult;
		
	}
}
