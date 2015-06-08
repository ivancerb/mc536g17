package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;
import br.unicamp.ic.mc536.grupo17.model.Escultura;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Pintura;
import br.unicamp.ic.mc536.grupo17.model.results.AudiovisualSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.EsculturaSearchResult;
import br.unicamp.ic.mc536.grupo17.model.results.PinturaSearchResult;

public class ObrasFromExpoDao {

	private static String SELECT_PINTURAS_FROM_EXPO = 
			"SELECT " +
				" OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
				" PINTURA.MATERIAIS, PINTURA.ALTURA, PINTURA.LARGURA, " +
				" ESTILO.ID_ESTILO, ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO, " +
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM " +
			" FROM " +
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO, PINTURA, OBRA_EXPO " +
			"WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA " +
				" AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND PINTURA.ID_OBRA = OBRA.ID_OBRA AND " +
				" OBRA_EXPO.ID_OBRA = OBRA.ID_OBRA and OBRA_EXPO.ID_EXPOSICAO = ? ";
	
	private static String SELECT_ESCULTURAS_FROM_EXPO = 
			"SELECT " +
				" OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
				" ESCULTURA.MATERIAIS, ESCULTURA.ALTURA, ESCULTURA.LARGURA, ESCULTURA.PROFUNDIDADE, " +
				" ESTILO.ID_ESTILO, ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO, " +
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM " +
			" FROM " +
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO, ESCULTURA, OBRA_EXPO " +
			"WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA " +
				" AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND ESCULTURA.ID_OBRA = OBRA.ID_OBRA AND " +
				" OBRA_EXPO.ID_OBRA = OBRA.ID_OBRA and OBRA_EXPO.ID_EXPOSICAO = ? ";
	
	private static String SELECT_AUDIOVISUAL_FROM_EXPO =  
			"SELECT " +
				" OBRA.ID_OBRA, OBRA.ID_ESTILO, OBRA.TITULO, OBRA.DATA, OBRA.DESCRICAO, OBRA.TAGS, OBRA.PATH_IMAGEM, OBRA.TIPO, " +
				" AUDIOVISUAL.TIPO_MIDIA, " +
				" ESTILO.ID_ESTILO, ESTILO.INICIO, ESTILO.FIM, ESTILO.NOME, ESTILO.DESCRICAO, " +
				" ARTISTA.ID_ARTISTA, ARTISTA.NOME, ARTISTA.DATA_NASC, ARTISTA.DATA_FALESC, ARTISTA.ORIGEM " +
			" FROM " +
				" ARTISTA, OBRA_ARTISTA, OBRA, ESTILO, AUDIOVISUAL, OBRA_EXPO " +
			"WHERE " +
				" ARTISTA.ID_ARTISTA = OBRA_ARTISTA.ID_ARTISTA AND OBRA_ARTISTA.ID_OBRA=OBRA.ID_OBRA " +
				" AND OBRA.ID_ESTILO=ESTILO.ID_ESTILO AND AUDIOVISUAL.ID_OBRA = OBRA.ID_OBRA AND " +
				" OBRA_EXPO.ID_OBRA = OBRA.ID_OBRA and OBRA_EXPO.ID_EXPOSICAO = ? ";
	
	public ObrasFromExpoDao(){
		
	}
	
	
	/**
	 * 
	 * @param filters
	 * @return
	 * @throws SQLException
	 */
	public List<PinturaSearchResult> selectPinturasFromExpo(int expoId) throws SQLException{
		List<PinturaSearchResult> listPinturaSearchResult = new ArrayList<PinturaSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
				
		PreparedStatement stmt = connection.prepareStatement(SELECT_PINTURAS_FROM_EXPO);
		stmt.setInt(1, expoId);
		
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
	public List<EsculturaSearchResult> selectEsculturasFromExpo(int expoId) throws SQLException{
		List<EsculturaSearchResult> listEsculturaSearchResult = new ArrayList<EsculturaSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
				
		PreparedStatement stmt = connection.prepareStatement(SELECT_ESCULTURAS_FROM_EXPO);
		stmt.setInt(1, expoId);
		
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
	public List<AudiovisualSearchResult> selectAudiovisualsFromExpo(int expoId) throws SQLException{
		List<AudiovisualSearchResult> listAudiovisualSearchResult = new ArrayList<AudiovisualSearchResult>();
		
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement stmt = connection.prepareStatement(SELECT_AUDIOVISUAL_FROM_EXPO);
		stmt.setInt(1, expoId);
		
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
