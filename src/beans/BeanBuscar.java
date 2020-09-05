package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.ListaReproduccion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanBuscar")
@SessionScoped
public class BeanBuscar {

	private String cadenaBusqueda;
	private Collection<Album> resultadosAlbumes;
	private Collection<Artista> resultadosArtistas;
	private Collection<Cancion> resultadosCanciones;
	private Collection<ListaReproduccion> resultadosListas;
	
	public String getCadenaBusqueda() {
		return cadenaBusqueda;
	}

	public void setCadenaBusqueda(String cadenaBusqueda) {
		this.cadenaBusqueda = cadenaBusqueda;
	}
	
	public String buscar() {
		return "songSearch";
	}
	
	public Collection<Album> getResultadosAlbumes() {
		resultadosAlbumes = new LinkedList<Album>();
		Collection<Album> allResultadosAlbumes = Controlador.getInstancia().buscarPorAlbum(cadenaBusqueda);
		resultadosAlbumes.addAll(allResultadosAlbumes);
		return resultadosAlbumes;
	}

	public void setResultadosAlbumes(Collection<Album> resultadosAlbumes) {
		this.resultadosAlbumes = resultadosAlbumes;
	}

	public Collection<Artista> getResultadosArtistas() {
		resultadosArtistas = new LinkedList<Artista>();
		Collection<Artista> allResultadosArtistas = Controlador.getInstancia().buscarPorArtista(cadenaBusqueda);
		resultadosArtistas.addAll(allResultadosArtistas);
		return resultadosArtistas;
	}

	public void setResultadosArtistas(Collection<Artista> resultadosArtistas) {
		this.resultadosArtistas = resultadosArtistas;
	}

	public Collection<Cancion> getResultadosCanciones() {
		resultadosCanciones = new LinkedList<Cancion>();
		Collection<Cancion> allResultadosCanciones = Controlador.getInstancia().buscarPorCancion(cadenaBusqueda);
		resultadosCanciones.addAll(allResultadosCanciones);
		return resultadosCanciones;
	}

	public void setResultadosCanciones(Collection<Cancion> resultadosCanciones) {
		this.resultadosCanciones = resultadosCanciones;
	}

	public Collection<ListaReproduccion> getResultadosListas() {
		resultadosListas = new LinkedList<ListaReproduccion>();
		Collection<ListaReproduccion> allResultadosListas = Controlador.getInstancia().buscarPorListaReproduccion(cadenaBusqueda);
		resultadosListas.addAll(allResultadosListas);
		return resultadosListas;
	}

	public void setResultadosListas(Collection<ListaReproduccion> resultadosListas) {
		this.resultadosListas = resultadosListas;
	}

}
