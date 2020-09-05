package modelo;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class CancionPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="TITULO")
	private String tituloCancion;
	private String artista;
	@Column(name="ALBUM")
	private String tituloAlbum;
	public String getTituloCancion() {
		return tituloCancion;
	}
	public void setTituloCancion(String tituloCancion) {
		this.tituloCancion = tituloCancion;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getTituloAlbum() {
		return tituloAlbum;
	}
	public void setTituloAlbum(String tituloAlbum) {
		this.tituloAlbum = tituloAlbum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artista == null) ? 0 : artista.hashCode());
		result = prime * result + ((tituloAlbum == null) ? 0 : tituloAlbum.hashCode());
		result = prime * result + ((tituloCancion == null) ? 0 : tituloCancion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CancionPK other = (CancionPK) obj;
		if (artista == null) {
			if (other.artista != null)
				return false;
		} else if (!artista.equals(other.artista))
			return false;
		if (tituloAlbum == null) {
			if (other.tituloAlbum != null)
				return false;
		} else if (!tituloAlbum.equals(other.tituloAlbum))
			return false;
		if (tituloCancion == null) {
			if (other.tituloCancion != null)
				return false;
		} else if (!tituloCancion.equals(other.tituloCancion))
			return false;
		return true;
	}

	
	
	
	
	
	

}
