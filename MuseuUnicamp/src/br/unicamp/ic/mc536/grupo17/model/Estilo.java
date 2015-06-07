package br.unicamp.ic.mc536.grupo17.model;

public class Estilo {
	
	private int idEstilo;
	private int inicio;
	private int fim;
	private String nome;
	private String descricao;
	
	
	@Override
	public String toString() {
		return "Estilo [idEstilo=" + idEstilo + ", inicio=" + inicio + ", fim="
				+ fim + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	public int getIdEstilo() {
		return idEstilo;
	}
	public void setIdEstilo(int idEstilo) {
		this.idEstilo = idEstilo;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public int getFim() {
		return fim;
	}
	public void setFim(int fim) {
		this.fim = fim;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
