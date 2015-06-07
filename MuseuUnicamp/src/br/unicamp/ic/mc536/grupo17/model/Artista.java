package br.unicamp.ic.mc536.grupo17.model;

public class Artista {
	
	public int idArtista;
	public String nome;
	public int dataNasc;
	public int dataFalesc;
	public String origem;
	public String bio;
	
	public Artista(){
		
	}
	
	public Artista(int idArtista, String nome, int dataNasc, int dataFalesc,
			String origem, String bio) {
		super();
		this.idArtista = idArtista;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.dataFalesc = dataFalesc;
		this.origem = origem;
		this.bio = bio;
	}
	
	
	@Override
	public String toString() {
		return "Artista [idArtista=" + idArtista + ", nome=" + nome
				+ ", dataNasc=" + dataNasc + ", dataFalesc=" + dataFalesc
				+ ", origem=" + origem + ", bio=" + bio + "]";
	}

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
	public int getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(int dataNasc) {
		this.dataNasc = dataNasc;
	}
	public int getDataFalesc() {
		return dataFalesc;
	}
	public void setDataFalesc(int dataFalesc) {
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


