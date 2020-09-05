package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Album;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarAlbumesFavoritos")
@SessionScoped
public class BeanListarAlbumesFavoritos {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<Album> albumesFavoritos;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
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
	
	public String quitarAlbumFavorito(String tituloAlbum, String artista) {
		if (beanLogin != null) {
			Controlador.getInstancia().quitarAlbumFavorito(beanLogin.getUsuario(), tituloAlbum, artista);
			return "albums";
		}
		return null;
	}

}
