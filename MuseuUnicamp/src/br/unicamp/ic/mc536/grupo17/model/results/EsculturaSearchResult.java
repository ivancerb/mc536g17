package br.unicamp.ic.mc536.grupo17.model.results;

import br.unicamp.ic.mc536.grupo17.model.Artista;
import br.unicamp.ic.mc536.grupo17.model.Estilo;
import br.unicamp.ic.mc536.grupo17.model.Escultura;

public class EsculturaSearchResult {
	
	Escultura escultura;
	Artista artista;
	Estilo estilo;
	
	public Escultura getEscultura() {
		return escultura;
	}
	public void setEscultura(Escultura escultura) {
		this.escultura = escultura;
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
