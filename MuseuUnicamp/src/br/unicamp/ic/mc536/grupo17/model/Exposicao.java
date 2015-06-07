package br.unicamp.ic.mc536.grupo17.model;

import java.sql.Date;

public class Exposicao {
	
	private int idExposicao;
	private int idCurador;
	private int idMuseu;
	private String nome;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	private String status;
	
	@Override
	public String toString() {
		return "Exposicao [idExposicao=" + idExposicao + ", idCurador="
				+ idCurador + ", idMuseu=" + idMuseu + ", nome=" + nome
				+ ", descricao=" + descricao + ", dataInicio=" + dataInicio
				+ ", dataFim=" + dataFim + ", status=" + status + "]";
	}
	public int getIdExposicao() {
		return idExposicao;
	}
	public void setIdExposicao(int idExposicao) {
		this.idExposicao = idExposicao;
	}
	public int getIdCurador() {
		return idCurador;
	}
	public void setIdCurador(int idCurador) {
		this.idCurador = idCurador;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
