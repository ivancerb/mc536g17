package br.unicamp.ic.mc536.grupo17.model.results;

import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Audiovisual;

public class AudiovisualSearchResult {
	
	Audiovisual audiovisual;
	Artista artista;
	Estilo estilo;
	
	public Audiovisual getAudiovisual() {
		return audiovisual;
	}
	public void setAudiovisual(Audiovisual audiovisual) {
		this.audiovisual = audiovisual;
	}
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
