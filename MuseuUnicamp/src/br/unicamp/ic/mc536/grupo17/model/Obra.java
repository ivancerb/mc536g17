package br.unicamp.ic.mc536.grupo17.model;

public class Obra {
	private int id_obra;
	private int id_estilo;
	private String titulo;
	private String data; 
	private String descricao;
	private String tags;
	private String path_imagem;
	
	public Obra(){
		
	}
	
	public Obra(int id_obra, int id_estilo, String titulo, String data,
			String descricao, String tags, String path_imagem) {
		super();
		this.id_obra = id_obra;
		this.id_estilo = id_estilo;
		this.titulo = titulo;
		this.data = data;
		this.descricao = descricao;
		this.tags = tags;
		this.path_imagem = path_imagem;
	}

	public Obra(Obra oldObra) {
		super();
		this.id_obra = oldObra.getId_obra();
		this.id_estilo = oldObra.getId_estilo();
		this.titulo = oldObra.getTitulo();
		this.data = oldObra.getData();
		this.descricao = oldObra.getDescricao();
		this.tags = oldObra.getTags();
		this.path_imagem = oldObra.getPath_imagem();
	}
	
	public int getId_obra() {
		return id_obra;
	}
	public void setId_obra(int id_obra) {
		this.id_obra = id_obra;
	}
	public int getId_estilo() {
		return id_estilo;
	}
	public void setId_estilo(int id_estilo) {
		this.id_estilo = id_estilo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
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
	public String getPath_imagem() {
		return path_imagem;
	}
	public void setPath_imagem(String path_imagem) {
		this.path_imagem = path_imagem;
	}
}

