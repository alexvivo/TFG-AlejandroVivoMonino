package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Album;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarArtista")
@SessionScoped
public class BeanListarArtista {
	
	private Collection<Album> albumesArtista;
	private String nombreArtista;

	@ManagedProperty("#{beanListarAlbum}")
	private BeanListarAlbum beanListarAlbum;
	
	public Collection<Album> getAlbumesArtista() {
		albumesArtista = new LinkedList<Album>();
		albumesArtista.addAll(Controlador.getInstancia().listarArtista(nombreArtista));
		return albumesArtista;
	}
	public void setAlbumesArtista(Collection<Album> albumesArtista) {
		this.albumesArtista = albumesArtista;
	}
	public String getNombreArtista() {
		return nombreArtista;
	}
	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}
	
	public String listarArtista(String nombreArtista) {
		setNombreArtista(nombreArtista);
		return "artist";
	}
	
	public String listarArtista() {
		if (beanListarAlbum != null)
			return listarArtista(beanListarAlbum.getArtista());
		return null;
	}

	public BeanListarAlbum getBeanListarAlbum() {
		return beanListarAlbum;
	}
	public void setBeanListarAlbum(BeanListarAlbum beanListarAlbum) {
		this.beanListarAlbum = beanListarAlbum;
	}
}
