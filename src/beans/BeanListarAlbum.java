package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Cancion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarAlbum")
@SessionScoped
public class BeanListarAlbum {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<Cancion> cancionesAlbum;
	private String artista;
	private String album;
	
	public Collection<Cancion> getCancionesAlbum() {
		cancionesAlbum = new LinkedList<Cancion>();
		
		if (beanLogin != null) {			
			Collection<Cancion> todasCancionesAlbum = Controlador.getInstancia().listarAlbum(artista, album);
			cancionesAlbum.addAll(todasCancionesAlbum);
		}
		return cancionesAlbum;
	}
	public void setCancionesAlbum(Collection<Cancion> cancionesAlbum) {
		this.cancionesAlbum = cancionesAlbum;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String listarAlbum(String artista, String album) {
		setArtista(artista);
		setAlbum(album);
		return "album";
	}
	public String getImagenAlbum() {
		return "img/" + artista + "-" + album + ".jpg";
	}
	
	public int getAnyo() {
		return Controlador.getInstancia().getAnyoAlbum(artista, album);
	}
	
	public BeanLogin getBeanLogin() {
		return beanLogin;
	}
	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}
	
	public void reproducirAlbum() {		
		if (beanLogin != null)
			Controlador.getInstancia().reproducirAlbum(beanLogin.getUsuario(), artista, album);
	}

}
