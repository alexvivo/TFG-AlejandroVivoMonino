package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Artista;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanMarcarOQuitarArtistaFavorito")
@SessionScoped
public class BeanMarcarOQuitarArtistaFavorito {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private String imagenBoton;
	
	private Collection<Artista> artistasFavoritos;
	
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
	
	public String setImagenBoton(String nombreArtista, String nombreArtista2) {
		
		if (nombreArtista.equals(nombreArtista2)) {
			if (isFavorito(nombreArtista)) {
				imagenBoton = "img/fav.png";
			} else {
				imagenBoton = "img/star.png";
			}			
		}
		return imagenBoton;
	}
	
	public Collection<Artista> getArtistasFavoritos() {
		artistasFavoritos = new LinkedList<Artista>();
		if (beanLogin != null) {
			Collection<Artista> allArtistasFavoritos = Controlador.getInstancia().listarArtistasFavoritos(beanLogin.getUsuario());
			artistasFavoritos.addAll(allArtistasFavoritos);
		}
		return artistasFavoritos;
	}

	public void setArtistasFavoritos(Collection<Artista> artistasFavoritos) {
		this.artistasFavoritos = artistasFavoritos;
	}
	
	public boolean isFavorito(String nombreArtista) {
		
		for (Artista artista: getArtistasFavoritos()) {
			if (artista.getNombre().equals(nombreArtista)) {
				return true;
			}
		}
		
		return false;
	}

	public void marcarOQuitarArtistaFavorito(String nombreArtista) {
		
		if (beanLogin != null) {
			if (isFavorito(nombreArtista)) {
				Controlador.getInstancia().quitarArtistaFavorito(beanLogin.getUsuario(), nombreArtista);
			} else {
				Controlador.getInstancia().marcarArtistaFavorito(beanLogin.getUsuario(), nombreArtista);
			}
		}
	}
}
