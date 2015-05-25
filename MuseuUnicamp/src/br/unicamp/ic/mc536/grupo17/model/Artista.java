package br.unicamp.ic.mc536.grupo17.model;

import java.sql.Timestamp;

public class Artista {
	
	public int idArtista;
	public String nome;
	public Timestamp dataNasc;
	public Timestamp dataFalesc;
	public String origem;
	public String bio;
	
	
	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Timestamp getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Timestamp dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Timestamp getDataFalesc() {
		return dataFalesc;
	}
	public void setDataFalesc(Timestamp dataFalesc) {
		this.dataFalesc = dataFalesc;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}	
}


