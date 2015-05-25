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

	public String getTipo_midia() {
		return tipo_midia;
	}

	public void setTipo_midia(String tipo_midia) {
		this.tipo_midia = tipo_midia;
	}
	
	
}
