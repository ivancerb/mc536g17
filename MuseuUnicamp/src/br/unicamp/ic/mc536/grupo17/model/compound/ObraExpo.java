package br.unicamp.ic.mc536.grupo17.model.compound;

public class ObraExpo {
	private int idObra;
	private int idExposicao;
	
	@Override
	public String toString() {
		return "ObraExpo [idObra=" + idObra + ", idExposicao=" + idExposicao
				+ "]";
	}
	
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	public int getIdExposicao() {
		return idExposicao;
	}
	public void setIdExposicao(int idExposicao) {
		this.idExposicao = idExposicao;
	}

}
