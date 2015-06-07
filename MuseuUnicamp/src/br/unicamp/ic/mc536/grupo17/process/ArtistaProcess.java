package br.unicamp.ic.mc536.grupo17.process;

import br.unicamp.ic.mc536.grupo17.model.Artista;

public class ArtistaProcess {

	public Artista getArtistInfo(String artistId){
		//TODO substituir pela chamada ao DAO
		return generateMockupArtistInfo(artistId);
		
	}
	
	private Artista generateMockupArtistInfo(String artistId){
		Artista artista = new Artista();
		artista.setNome("John Doe");
		artista.setDataNasc(1);
		artista.setDataFalesc(2);
		artista.setOrigem("Brasil");
		artista.setBio("Imperdiet vel tortor praesent convallis turpis imperdiet " +
				"faucibus euismod aenean, lacus felis ante venenatis sodales nullam " +
				"duis congue diam, donec sodales aliquam venenatis dictum urna quam " +
				"fermentum. suscipit sagittis congue dolor lobortis consectetur");
		return artista;
	}
}
