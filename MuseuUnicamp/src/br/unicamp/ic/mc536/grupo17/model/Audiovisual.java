package br.unicamp.ic.mc536.grupo17.model;

public class Audiovisual extends Obra{

	private String tipo_midia;
	
	public Audiovisual(){
		
	}

	public Audiovisual(String tipo_midia) {
		super();
		this.tipo_midia = tipo_midia;
	}

	public Audiovisual(Obra obra, String tipo_midia) {
		super(obra);
		this.tipo_midia = tipo_midia;
	}
	
	
	@Override
	public String toString() {
		return "Audiovisual" + super.toString() + "[tipo_midia=" + tipo_midia + "]";
	}

	public Obra getObra(){
		Obra obra = new Obra();
		obra.setIdObra(this.getIdObra());
		obra.setIdEstilo(this.getIdEstilo());
		obra.setTitulo(this.getTitulo());
		obra.setData(this.getData());
		obra.setDescricao(this.getDescricao());
		obra.setTags(this.getTags());
		obra.setPathImagem(this.getPathImagem());
		obra.setTipo(this.getTipo());
		return obra;
	}

	public String getTipo_midia() {
		return tipo_midia;
	}

	public void setTipo_midia(String tipo_midia) {
		this.tipo_midia = tipo_midia;
	}
	
	
}
