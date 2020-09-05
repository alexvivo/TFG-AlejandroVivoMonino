package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.ListaReproduccion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarListasReproduccionPropias")
@SessionScoped
public class BeanListarListasReproduccionPropias {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<ListaReproduccion> listasReproduccionPropias;
	
	private String tituloCancion;
	private String artista;
	private String tituloAlbum;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Collection<ListaReproduccion> getListasReproduccionPropias() {
		listasReproduccionPropias = new LinkedList<ListaReproduccion>();
		if (beanLogin != null) {
			Collection<ListaReproduccion> allListasReproduccionPropias = Controlador.getInstancia().listarListasReproduccionPropias(beanLogin.getUsuario());
			listasReproduccionPropias.addAll(allListasReproduccionPropias);
		}
		return listasReproduccionPropias;
	}

	public void setListasReproduccionPropias(Collection<ListaReproduccion> listasReproduccionPropias) {
		this.listasReproduccionPropias = listasReproduccionPropias;
	}
	
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

	public String listarListas(String tituloCancion, String artista, String tituloAlbum) {
		this.tituloCancion = tituloCancion;
		this.artista = artista;
		this.tituloAlbum = tituloAlbum;
		return "myPlaylists";
	}

}
