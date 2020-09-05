package controlador;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dao.FactoriaDAO;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import modelo.*;

public class Controlador {
	private static Controlador instancia;

	private FactoriaDAO dao;

	private Listener listener;

	private List<Cancion> colaCanciones;

	private int indReproduciendo;

	private Cancion cancionReproduciendo;

	private Controlador() {
		dao = FactoriaDAO.getInstancia();
		listener = new Listener();
		colaCanciones = new LinkedList<Cancion>();
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public void reproducirCola() {

		try {
			listener.getPlayer().stop();
			while (indReproduciendo < colaCanciones.size()) {
				cancionReproduciendo = colaCanciones.get(indReproduciendo);
				if (listener.getPlayer().getStatus() == BasicPlayer.UNKNOWN
						|| listener.getPlayer().getStatus() == BasicPlayer.STOPPED) {
					listener.getPlayer()
							.open(new File("server/" + colaCanciones.get(indReproduciendo).getCancionPK().getArtista()
									+ "-" + colaCanciones.get(indReproduciendo).getCancionPK().getTituloCancion() + "-"
									+ colaCanciones.get(indReproduciendo).getCancionPK().getTituloAlbum() + ".mp3"));
					listener.getPlayer().play();
				}
			}
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public Cancion getCancionReproduciendo() {
		return cancionReproduciendo;
	}

	public void setCancionReproduciendo(Cancion cancionReproduciendo) {
		this.cancionReproduciendo = cancionReproduciendo;
	}

	public Listener getListener() {
		return listener;
	}

	public void siguienteCancion() {
		if (indReproduciendo < colaCanciones.size()) {
			indReproduciendo++;
			reproducirCola();
		} 
	}

	public void anteriorCancion() {
		if (indReproduciendo > 0) {
			indReproduciendo--;
			reproducirCola();
		} 
	}

	public void reproducirCancion(String usuario, String artista, String titulo, String album) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu != null) {
			CancionPK cancionPK = new CancionPK();
			cancionPK.setArtista(artista);
			cancionPK.setTituloCancion(titulo);
			cancionPK.setTituloAlbum(album);

			Cancion cancion = dao.getCancionDAO().read(cancionPK);

			colaCanciones = new LinkedList<Cancion>();

			colaCanciones.add(cancion);
			
			indReproduciendo = 0;

			reproducirCola();
			
			usu.getCancionesEscuchadas().add(cancion);

			dao.getUsuarioDAO().update(usu);
		}
	}

	public void reproducirAlbum(String usuario, String artista, String tituloAlbum) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu != null) {
			AlbumPK albumPK = new AlbumPK();
			albumPK.setArtista(artista);
			albumPK.setTitulo(tituloAlbum);

			Album album = dao.getAlbumDAO().read(albumPK);

			colaCanciones = new LinkedList<Cancion>();

			if (album != null) {

				colaCanciones.addAll(album.getCanciones());
				
				indReproduciendo = 0;

				reproducirCola();
				
				usu.getCancionesEscuchadas().addAll(album.getCanciones());

				dao.getUsuarioDAO().update(usu);

			}
		}

	}

	public void reproducirLista(String usuario, int idLista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu != null) {

			ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

			colaCanciones = new LinkedList<Cancion>();

			if (listaReproduccion != null) {

				colaCanciones.addAll(listaReproduccion.getCanciones());
				
				indReproduciendo = 0;

				reproducirCola();
				
				usu.getCancionesEscuchadas().addAll(listaReproduccion.getCanciones());

				dao.getUsuarioDAO().update(usu);
			}
		}

	}

	public boolean login(String usuario, String contraseña) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu != null && usu.getContraseña().equals(contraseña)) {
			return true;
		}

		return false;
	}

	public boolean registrar(String usuario, String email, String contraseña, int anyoNacimiento, int mesNacimiento,
			int diaNacimiento) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usuario == null || usuario.equals("") || usu != null) {
			return false;
		}

		@SuppressWarnings("deprecation")
		Date fechaNacimiento = new Date(anyoNacimiento - 1900, mesNacimiento - 1, diaNacimiento);

		usu = dao.getUsuarioDAO().create(usuario, email, contraseña, fechaNacimiento);

		return true;

	}

	public int crearListaReproduccion(String usuario, String nombre) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return -1;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().create(nombre, usuario);
		dao.getUsuarioDAO().update(usu);

		return listaReproduccion.getId();
	}

	public boolean añadirCancion(String usuario, int idLista, String artista, String titulo, String album) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		CancionPK cancionPK = new CancionPK();
		cancionPK.setArtista(artista);
		cancionPK.setTituloCancion(titulo);
		cancionPK.setTituloAlbum(album);

		Cancion cancion = dao.getCancionDAO().read(cancionPK);

		if (cancion == null) {
			return false;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion == null) {
			return false;
		}

		if (!listaReproduccion.getUsuario().getNombre().equals(usuario)) {
			return false;
		}

		listaReproduccion.getCanciones().add(cancion);

		dao.getListaReproduccionDAO().update(listaReproduccion);
		dao.getUsuarioDAO().update(usu);

		return true;
	}

	public boolean eliminarCancion(String usuario, int idLista, String artista, String titulo, String album) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		CancionPK cancionPK = new CancionPK();
		cancionPK.setArtista(artista);
		cancionPK.setTituloCancion(titulo);
		cancionPK.setTituloAlbum(album);

		Cancion cancion = dao.getCancionDAO().read(cancionPK);

		if (cancion == null) {
			return false;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion == null) {
			return false;
		}

		if (!listaReproduccion.getUsuario().getNombre().equals(usuario)) {
			return false;
		}

		Iterator<Cancion> it = listaReproduccion.getCanciones().iterator();

		while (it.hasNext()) {
			Cancion cancion1 = it.next();
			if (cancion1.getCancionPK().getArtista().equals(artista)
					&& cancion1.getCancionPK().getTituloCancion().equals(titulo)
					&& cancion1.getCancionPK().getTituloAlbum().equals(album)) {
				it.remove();
			}
		}

		dao.getListaReproduccionDAO().update(listaReproduccion);
		dao.getUsuarioDAO().update(usu);

		return true;
	}

	public Collection<Cancion> listarListaReproduccion(int idLista) {

		LinkedList<Cancion> lista = new LinkedList<Cancion>();

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion != null) {
			lista.addAll(listaReproduccion.getCanciones());
		}

		return lista;
	}

	public String getNombreListaReproduccion(int idLista) {

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion != null) {
			return listaReproduccion.getNombre();
		}

		return null;
	}

	public String getUsuarioListaReproduccion(int idLista) {

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion != null) {
			return listaReproduccion.getUsuario().getNombre();
		}

		return null;
	}

	public Collection<Cancion> listarAlbum(String artista, String tituloAlbum) {

		LinkedList<Cancion> cancionesAlbum = new LinkedList<Cancion>();

		AlbumPK albumPK = new AlbumPK();
		albumPK.setArtista(artista);
		albumPK.setTitulo(tituloAlbum);

		Album album = dao.getAlbumDAO().read(albumPK);

		if (album != null) {
			cancionesAlbum.addAll(album.getCanciones());
		}

		return cancionesAlbum;
	}

	public int getAnyoAlbum(String artista, String tituloAlbum) {

		AlbumPK albumPK = new AlbumPK();
		albumPK.setArtista(artista);
		albumPK.setTitulo(tituloAlbum);

		Album album = dao.getAlbumDAO().read(albumPK);

		if (album != null) {
			return album.getAnyo();
		}

		return 0;
	}

	public Collection<Album> listarArtista(String nombreArtista) {

		LinkedList<Album> albumesArtista = new LinkedList<Album>();

		Artista artista = dao.getArtistaDAO().read(nombreArtista);

		if (artista != null) {

			albumesArtista.addAll(artista.getDiscografia());
		}

		return albumesArtista;
	}

	public boolean eliminarListaReproduccion(String usuario, int idLista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion == null) {
			return false;
		}

		if (!listaReproduccion.getUsuario().getNombre().equals(usuario)) {
			return false;
		}

		dao.getListaReproduccionDAO().delete(idLista);
		dao.getUsuarioDAO().update(usu);

		return true;
	}

	public boolean renombrarLista(String usuario, int idLista, String nuevoNombre) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion == null) {
			return false;
		}

		if (!listaReproduccion.getUsuario().getNombre().equals(usuario)) {
			return false;
		}

		listaReproduccion.setNombre(nuevoNombre);
		dao.getListaReproduccionDAO().update(listaReproduccion);
		dao.getUsuarioDAO().update(usu);

		return true;
	}

	public Collection<Album> buscarPorAlbum(String cadenaBusqueda) {
		return dao.getAlbumDAO().getResultadosAlbumes(cadenaBusqueda);
	}

	public Collection<Artista> buscarPorArtista(String cadenaBusqueda) {
		return dao.getArtistaDAO().getResultadosArtistas(cadenaBusqueda);
	}

	public Collection<Cancion> buscarPorCancion(String cadenaBusqueda) {
		return dao.getCancionDAO().getResultadosCanciones(cadenaBusqueda);
	}

	public Collection<ListaReproduccion> buscarPorListaReproduccion(String cadenaBusqueda) {
		return dao.getListaReproduccionDAO().getResultadosListasReproduccion(cadenaBusqueda);
	}

	public boolean marcarAlbumFavorito(String usuario, String tituloAlbum, String artista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		AlbumPK albumPK = new AlbumPK();
		albumPK.setTitulo(tituloAlbum);
		albumPK.setArtista(artista);

		Album album = dao.getAlbumDAO().read(albumPK);

		if (album == null) {
			return false;
		}

		boolean favorito = false;

		for (Album album1 : usu.getAlbumesFavoritos()) {
			if (album1.getAlbumPK().getTitulo().equals(tituloAlbum)
					&& album1.getAlbumPK().getArtista().equals(artista)) {
				favorito = true;
				break;
			}
		}

		if (!favorito) {
			usu.getAlbumesFavoritos().add(album);
			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean marcarArtistaFavorito(String usuario, String artista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		Artista art = dao.getArtistaDAO().read(artista);

		if (art == null) {
			return false;
		}

		boolean favorito = false;

		for (Artista art1 : usu.getArtistasFavoritos()) {
			if (art1.getNombre().equals(artista)) {
				favorito = true;
				break;
			}
		}

		if (!favorito) {
			usu.getArtistasFavoritos().add(art);
			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean marcarCancionFavorita(String usuario, String tituloCancion, String artista, String tituloAlbum) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		CancionPK cancionPK = new CancionPK();
		cancionPK.setTituloCancion(tituloCancion);
		cancionPK.setArtista(artista);
		cancionPK.setTituloAlbum(tituloAlbum);

		Cancion cancion = dao.getCancionDAO().read(cancionPK);

		if (cancion == null) {
			return false;
		}

		boolean favorito = false;

		for (Cancion cancion1 : usu.getCancionesFavoritas()) {
			if (cancion1.getCancionPK().getTituloCancion().equals(tituloCancion)
					&& cancion1.getCancionPK().getArtista().equals(artista)
					&& cancion1.getCancionPK().getTituloAlbum().equals(tituloAlbum)) {
				favorito = true;
				break;
			}
		}

		if (!favorito) {
			usu.getCancionesFavoritas().add(cancion);
			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean marcarListaFavorita(String usuario, int idLista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion == null || listaReproduccion.getUsuario().getNombre().equals(usuario)) {
			return false;
		}

		boolean favorito = false;

		for (ListaReproduccion listaReproduccion1 : usu.getListasFavoritas()) {
			if (listaReproduccion1.getId() == idLista) {
				favorito = true;
				break;
			}
		}

		if (!favorito) {
			usu.getListasFavoritas().add(listaReproduccion);
			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean quitarAlbumFavorito(String usuario, String tituloAlbum, String artista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		AlbumPK albumPK = new AlbumPK();
		albumPK.setTitulo(tituloAlbum);
		albumPK.setArtista(artista);

		Album album = dao.getAlbumDAO().read(albumPK);

		if (album == null) {
			return false;
		}

		boolean favorito = false;

		for (Album album1 : usu.getAlbumesFavoritos()) {
			if (album1.getAlbumPK().getTitulo().equals(tituloAlbum)
					&& album1.getAlbumPK().getArtista().equals(artista)) {
				favorito = true;
				break;
			}
		}

		if (favorito) {

			Iterator<Album> albumesFavoritos = usu.getAlbumesFavoritos().iterator();

			while (albumesFavoritos.hasNext()) {
				Album album1 = albumesFavoritos.next();

				if (album1.getAlbumPK().getTitulo().equals(tituloAlbum)
						&& album1.getAlbumPK().getArtista().equals(artista)) {
					albumesFavoritos.remove();
				}
			}

			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean quitarArtistaFavorito(String usuario, String artista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		Artista art = dao.getArtistaDAO().read(artista);

		if (art == null) {
			return false;
		}

		boolean favorito = false;

		for (Artista art1 : usu.getArtistasFavoritos()) {
			if (art1.getNombre().equals(artista)) {
				favorito = true;
				break;
			}
		}

		if (favorito) {

			Iterator<Artista> artistasFavoritos = usu.getArtistasFavoritos().iterator();

			while (artistasFavoritos.hasNext()) {
				Artista artista1 = artistasFavoritos.next();

				if (artista1.getNombre().equals(artista)) {
					artistasFavoritos.remove();
				}
			}

			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean quitarCancionFavorita(String usuario, String tituloCancion, String artista, String tituloAlbum) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		CancionPK cancionPK = new CancionPK();
		cancionPK.setTituloCancion(tituloCancion);
		cancionPK.setArtista(artista);
		cancionPK.setTituloAlbum(tituloAlbum);

		Cancion cancion = dao.getCancionDAO().read(cancionPK);

		if (cancion == null) {
			return false;
		}

		boolean favorito = false;

		for (Cancion cancion1 : usu.getCancionesFavoritas()) {
			if (cancion1.getCancionPK().getTituloCancion().equals(tituloCancion)
					&& cancion1.getCancionPK().getArtista().equals(artista)
					&& cancion1.getCancionPK().getTituloAlbum().equals(tituloAlbum)) {
				favorito = true;
				break;
			}
		}

		if (favorito) {

			Iterator<Cancion> cancionesFavoritas = usu.getCancionesFavoritas().iterator();

			while (cancionesFavoritas.hasNext()) {

				Cancion cancion1 = cancionesFavoritas.next();

				if (cancion1.getCancionPK().getTituloCancion().equals(tituloCancion)
						&& cancion1.getCancionPK().getArtista().equals(artista)
						&& cancion1.getCancionPK().getTituloAlbum().equals(tituloAlbum)) {
					cancionesFavoritas.remove();
				}
			}

			dao.getUsuarioDAO().update(usu);
		}

		return true;
	}

	public boolean quitarListaFavorita(String usuario, int idLista) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return false;
		}

		ListaReproduccion listaReproduccion = dao.getListaReproduccionDAO().read(idLista);

		if (listaReproduccion == null) {
			return false;
		}

		boolean favorito = false;

		for (ListaReproduccion listaReproduccion1 : usu.getListasFavoritas()) {
			if (listaReproduccion1.getId() == idLista) {
				favorito = true;
				break;
			}
		}

		if (favorito) {

			Iterator<ListaReproduccion> listasFavoritas = usu.getListasFavoritas().iterator();

			while (listasFavoritas.hasNext()) {

				ListaReproduccion listaReproduccion1 = listasFavoritas.next();

				if (listaReproduccion1.getId() == idLista) {
					listasFavoritas.remove();
				}
			}

			dao.getUsuarioDAO().update(usu);
		}

		if (listaReproduccion.getUsuario().getNombre().equals(usuario)) {
			return eliminarListaReproduccion(usuario, idLista);
		}

		return true;
	}

	public Collection<Artista> listarArtistasFavoritos(String usuario) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return null;
		}

		LinkedList<Artista> artistas = new LinkedList<Artista>();

		artistas.addAll(usu.getArtistasFavoritos());

		return artistas;
	}

	public Collection<Album> listarAlbumesFavoritos(String usuario) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return null;
		}

		LinkedList<Album> albumes = new LinkedList<Album>();

		albumes.addAll(usu.getAlbumesFavoritos());

		return albumes;
	}

	public Collection<Cancion> listarCancionesFavoritas(String usuario) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return null;
		}

		LinkedList<Cancion> canciones = new LinkedList<Cancion>();

		canciones.addAll(usu.getCancionesFavoritas());

		return canciones;
	}

	public Collection<ListaReproduccion> listarListasReproduccionFavoritas(String usuario) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return null;
		}

		LinkedList<ListaReproduccion> listasReproduccion = new LinkedList<ListaReproduccion>();

		listasReproduccion.addAll(usu.getListasFavoritas());
		listasReproduccion.addAll(usu.getListasReproduccion());

		return listasReproduccion;
	}

	public Collection<ListaReproduccion> listarListasReproduccionPropias(String usuario) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return null;
		}

		LinkedList<ListaReproduccion> listasReproduccion = new LinkedList<ListaReproduccion>();

		listasReproduccion.addAll(usu.getListasReproduccion());

		return listasReproduccion;
	}

	public Collection<Cancion> listarActividad(String usuario) {

		Usuario usu = dao.getUsuarioDAO().read(usuario);

		if (usu == null) {
			return null;
		}

		LinkedList<Cancion> canciones = new LinkedList<Cancion>();

		canciones.addAll(usu.getCancionesEscuchadas());

		return canciones;
	}

}
