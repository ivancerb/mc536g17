package br.unicamp.ic.mc536.grupo17.model;

public class Museu {
	
	private int idMuseu;
	private String nome;
	private String contato;
	
	
	@Override
	public String toString() {
		return "Museu [idMuseu=" + idMuseu + ", nome=" + nome + ", contato="
				+ contato + "]";
	}
	
	public int getIdMuseu() {
		return idMuseu;
	}
	public void setIdMuseu(int idMuseu) {
		this.idMuseu = idMuseu;
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
