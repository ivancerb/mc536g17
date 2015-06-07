package br.unicamp.ic.mc536.grupo17.model;

public class Obra {
	private int idObra;
	private int idEstilo;
	private String titulo;
	private int data; 
	private String descricao;
	private String tags;
	private String pathImagem;
	private String tipo;
	
	public Obra(){
		
	}
	
	public Obra(int idObra, int idEstilo, String titulo, int data,
			String descricao, String tags, String pathImagem, String tipo) {
		super();
		this.idObra = idObra;
		this.idEstilo = idEstilo;
		this.titulo = titulo;
		this.data = data;
		this.descricao = descricao;
		this.tags = tags;
		this.pathImagem = pathImagem;
		this.tipo = tipo;
	}

	public Obra(Obra oldObra) {
		super();
		this.idObra = oldObra.getIdObra();
		this.idEstilo = oldObra.getIdEstilo();
		this.titulo = oldObra.getTitulo();
		this.data = oldObra.getData();
		this.descricao = oldObra.getDescricao();
		this.tags = oldObra.getTags();
		this.pathImagem = oldObra.getPathImagem();
		this.tipo = oldObra.getTipo();
	}
	
	
	@Override
	public String toString() {
		return "Obra [idObra=" + idObra + ", idEstilo=" + idEstilo
				+ ", titulo=" + titulo + ", data=" + data + ", descricao="
				+ descricao + ", tags=" + tags + ", pathImagem=" + pathImagem
				+ ", tipo=" + tipo + "]";
	}

	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	public int getIdEstilo() {
		return idEstilo;
	}
	public void setIdEstilo(int idEstilo) {
		this.idEstilo = idEstilo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getPathImagem() {
		return pathImagem;
	}
	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}

