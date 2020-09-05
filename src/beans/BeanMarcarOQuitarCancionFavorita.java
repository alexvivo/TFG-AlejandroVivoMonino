package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Cancion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanMarcarOQuitarCancionFavorita")
@SessionScoped
public class BeanMarcarOQuitarCancionFavorita {

	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private String imagenBoton;
	
	private Collection<Cancion> cancionesFavoritas;
	
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
	
	public String setImagenBoton(String tituloCancion, String artista, String tituloAlbum) {
		
		if (isFavorito(tituloCancion, artista, tituloAlbum)) {
			imagenBoton = "img/fav.png";
		} else {
			imagenBoton = "img/star.png";
		}
		return imagenBoton;
	}
	
	public Collection<Cancion> getCancionesFavoritas() {
		cancionesFavoritas = new LinkedList<Cancion>();
		if (beanLogin != null) {
			Collection<Cancion> allCancionesFavoritas = Controlador.getInstancia().listarCancionesFavoritas(beanLogin.getUsuario());
			cancionesFavoritas.addAll(allCancionesFavoritas);
		}
		return cancionesFavoritas;
	}

	public void setCancionesFavoritas(Collection<Cancion> cancionesFavoritas) {
		this.cancionesFavoritas = cancionesFavoritas;
	}
	
	public boolean isFavorito(String tituloCancion, String artista, String tituloAlbum) {
		
		for (Cancion cancion: getCancionesFavoritas()) {
			if (cancion.getCancionPK().getTituloCancion().equals(tituloCancion) 
					&& cancion.getCancionPK().getArtista().equals(artista)
					&& cancion.getCancionPK().getTituloAlbum().equals(tituloAlbum)) {
				return true;
			}
		}
		return false;
	}
	
	public void marcarOQuitarCancionFavorita(String tituloCancion, String artista, String tituloAlbum) {
		
		if (beanLogin != null) {
			if (isFavorito(tituloCancion, artista, tituloAlbum)) {
				Controlador.getInstancia().quitarCancionFavorita(beanLogin.getUsuario(), tituloCancion, artista, tituloAlbum);
			} else {
				Controlador.getInstancia().marcarCancionFavorita(beanLogin.getUsuario(), tituloCancion, artista, tituloAlbum);
			}
		}
	}
}
