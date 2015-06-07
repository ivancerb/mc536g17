package br.unicamp.ic.mc536.grupo17.model.results;

import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Estilo;

public class ArtistSearchResult {

	Artista artista;
	Estilo estilo;
	
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
}
