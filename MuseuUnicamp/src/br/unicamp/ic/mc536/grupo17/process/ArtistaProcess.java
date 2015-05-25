package br.unicamp.ic.mc536.grupo17.process;

import java.sql.Timestamp;
import java.util.Date;

import br.unicamp.ic.mc536.grupo17.model.Artista;

public class ArtistaProcess {

	public Artista getArtistInfo(String artistId){
		//TODO substituir pela chamada ao DAO
		return generateMockupArtistInfo(artistId);
		
	}
	
	private Artista generateMockupArtistInfo(String artistId){
		Artista artista = new Artista();
		artista.setNome("John Doe");
		artista.setDataNasc(new Timestamp(new Date().getTime()));
		artista.setDataFalesc(new Timestamp(new Date().getTime()));
		artista.setOrigem("Brasil");
		artista.setBio("Imperdiet vel tortor praesent convallis turpis imperdiet " +
				"faucibus euismod aenean, lacus felis ante venenatis sodales nullam " +
				"duis congue diam, donec sodales aliquam venenatis dictum urna quam " +
				"fermentum. suscipit sagittis congue dolor lobortis consectetur");
		return artista;
	}
}
