package br.unicamp.ic.mc536.grupo17.model.results;

import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Pintura;

public class PinturaSearchResult {
	
	Pintura pintura;
	Artista artista;
	Estilo estilo;
	
	public Pintura getPintura() {
		return pintura;
	}
	public void setPintura(Pintura pintura) {
		this.pintura = pintura;
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
