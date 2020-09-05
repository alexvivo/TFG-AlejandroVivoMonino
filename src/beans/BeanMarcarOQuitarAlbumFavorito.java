package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Album;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanMarcarOQuitarAlbumFavorito")
@SessionScoped
public class BeanMarcarOQuitarAlbumFavorito {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private String imagenBoton;

	private Collection<Album> albumesFavoritos;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}
	
	public String getImagenBoton() {
		return imagenBoton;
	}

	public void setImagenBoton(String imagenBoton) {
		this.imagenBoton = imagenBoton;
	}

	public String setImagenBoton(String tituloAlbum, String artista) {
		
		if (isFavorito(tituloAlbum, artista)) {
			imagenBoton = "img/fav.png";
		} else {
			imagenBoton = "img/star.png";
		}
		return imagenBoton;
	}
	
	public Collection<Album> getAlbumesFavoritos() {
		albumesFavoritos = new LinkedList<Album>();
		if (beanLogin != null) {
			Collection<Album> allAlbumesFavoritos = Controlador.getInstancia().listarAlbumesFavoritos(beanLogin.getUsuario());
			albumesFavoritos.addAll(allAlbumesFavoritos);
		}
		return albumesFavoritos;
	}

	public void setAlbumesFavoritos(Collection<Album> albumesFavoritos) {
		this.albumesFavoritos = albumesFavoritos;
	}
	
	public boolean isFavorito(String tituloAlbum, String artista) {
		
		for (Album album: getAlbumesFavoritos()) {
			if (album.getAlbumPK().getTitulo().equals(tituloAlbum)
					&& album.getAlbumPK().getArtista().equals(artista)) {
				return true;
			}
		}
		return false;
	}

	public void marcarOQuitarAlbumFavorito(String tituloAlbum, String artista) {
		
		if (beanLogin != null) {
			if (isFavorito(tituloAlbum, artista)) {
				Controlador.getInstancia().quitarAlbumFavorito(beanLogin.getUsuario(), tituloAlbum, artista);
			} else {
				Controlador.getInstancia().marcarAlbumFavorito(beanLogin.getUsuario(), tituloAlbum, artista);
			}
		}
	}

}
