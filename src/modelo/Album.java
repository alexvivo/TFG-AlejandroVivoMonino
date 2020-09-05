package modelo;

import java.util.List;

import javax.persistence.*;

@Entity
public class Album {
	
	@EmbeddedId
	private AlbumPK albumPK;
	private int anyo;
	@OneToMany(mappedBy="album", cascade = CascadeType.ALL)
	@OrderBy("numCancion")
	private List<Cancion> canciones;
	@MapsId("artista")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ARTISTA")
	private Artista artista;

	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public List<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	public AlbumPK getAlbumPK() {
		return albumPK;
	}
	public void setAlbumPK(AlbumPK albumPK) {
		this.albumPK = albumPK;
	}
	
	

}
