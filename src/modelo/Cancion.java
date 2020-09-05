package modelo;

import javax.persistence.*;

@Entity
public class Cancion {
	
	@EmbeddedId
	private CancionPK cancionPK;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "ARTISTA", referencedColumnName = "ARTISTA", insertable = false, updatable = false), 
		@JoinColumn(name = "ALBUM", referencedColumnName = "TITULO", insertable = false, updatable = false)})
	private Album album;
	private String genero;
	@Column(name = "NUMERO")
	private int numCancion;
	
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public CancionPK getCancionPK() {
		return cancionPK;
	}
	public void setCancionPK(CancionPK cancionPK) {
		this.cancionPK = cancionPK;
	}
	public int getNumCancion() {
		return numCancion;
	}
	public void setNumCancion(int numCancion) {
		this.numCancion = numCancion;
	}
	

}
