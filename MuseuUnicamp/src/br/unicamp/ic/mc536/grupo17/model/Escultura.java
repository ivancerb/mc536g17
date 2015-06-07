package br.unicamp.ic.mc536.grupo17.model;

public class Escultura extends Obra{

	private String materiais;
	private double altura;
	private double largura;
	private double profundidade;
	
	public Escultura(){
		
	}
	
	public Escultura(String materiais, double altura, double largura,
			double profundidade) {
		super();
		this.materiais = materiais;
		this.altura = altura;
		this.largura = largura;
		this.profundidade = profundidade;
	}
	
	public Escultura(Obra obra, String materiais, double altura, double largura,
			double profundidade) {
		super(obra);
		this.materiais = materiais;
		this.altura = altura;
		this.largura = largura;
		this.profundidade = profundidade;
	}
	
	
	@Override
	public String toString() {
		return "Escultura" + super.toString() + "[materiais=" + materiais + ", altura=" + altura
				+ ", largura=" + largura + ", profundidade=" + profundidade
				+ "]";
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
	
	public String getMateriais() {
		return materiais;
	}
	public void setMateriais(String materiais) {
		this.materiais = materiais;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		this.largura = largura;
	}
	public double getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}
}