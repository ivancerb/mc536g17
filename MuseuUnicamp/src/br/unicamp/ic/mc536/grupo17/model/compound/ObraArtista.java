package br.unicamp.ic.mc536.grupo17.model.compound;

public class ObraArtista {
	private int idObra;
	private int idArtista;
	
	
	@Override
	public String toString() {
		return "ObraArtista [idObra=" + idObra + ", idArtista=" + idArtista
				+ "]";
	}
	
	
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}

}
