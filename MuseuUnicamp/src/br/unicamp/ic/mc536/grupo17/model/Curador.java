package br.unicamp.ic.mc536.grupo17.model;

public class Curador {
	
	private int idCurador;
	private String nome;
	private String contato;
	

	@Override
	public String toString() {
		return "Curador [idCurador=" + idCurador + ", nome=" + nome
				+ ", contato=" + contato + "]";
	}
	
	public int getIdCurador() {
		return idCurador;
	}
	public void setIdCurador(int idCurador) {
		this.idCurador = idCurador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}

}
