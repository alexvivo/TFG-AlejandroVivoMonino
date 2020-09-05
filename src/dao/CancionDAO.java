package dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import modelo.Album;
import modelo.AlbumPK;
import modelo.Artista;
import modelo.Cancion;
import modelo.CancionPK;

public class CancionDAO implements ICancionDAO {

	private EntityManagerFactory emf;

	public CancionDAO() {
		emf = Persistence.createEntityManagerFactory("TFG-AlejandroVivoMonino");
	}

	@Override
	public Cancion create(int numCancion, String titulo, String artista, String album, String genero, int anyo) {
		EntityManager em = emf.createEntityManager();
		CancionPK cancionPK = new CancionPK();
		cancionPK.setTituloCancion(titulo);
		cancionPK.setArtista(artista);
		cancionPK.setTituloAlbum(album);
		Cancion cancion = em.find(Cancion.class, cancionPK);
		if (cancion == null) {
			em.getTransaction().begin();
			cancion = new Cancion();
			cancion.setCancionPK(cancionPK);
			cancion.setGenero(genero);
			AlbumPK albumPK = new AlbumPK();
			albumPK.setArtista(artista);
			albumPK.setTitulo(album);
			Album _album = em.find(Album.class, albumPK);
			if (_album == null) {
				_album = new Album();
				_album.setAnyo(anyo);
				_album.setAlbumPK(albumPK);
				Artista _artista = em.find(Artista.class, artista);
				if (_artista == null) {
					_artista = new Artista();
					_artista.setNombre(artista);
					_artista.setDiscografia(new LinkedList<Album>());
				}
				_artista.getDiscografia().add(_album);
				_album.setArtista(_artista);
				_album.setCanciones(new LinkedList<Cancion>());
			}
			_album.getCanciones().add(cancion);
			cancion.setNumCancion(numCancion);
			cancion.setAlbum(_album);
			em.persist(cancion);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
		
		return cancion;
	}

	@Override
	public Cancion read(CancionPK cancionPK) {
		EntityManager em = emf.createEntityManager();
		Cancion cancion = em.find(Cancion.class, cancionPK);
		em.close();
		emf.close();
		return cancion;
	}

	@Override
	public void update(Cancion cancion) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(cancion);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void delete(CancionPK cancionPK) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Cancion cancion = em.find(Cancion.class, cancionPK);
		if (em.contains(cancion))
			em.remove(cancion);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public Collection<Cancion> getResultadosCanciones(String cadenaBusqueda) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String query = "SELECT c FROM Cancion c WHERE c.cancionPK.tituloCancion LIKE '%" + cadenaBusqueda + "%'";
		@SuppressWarnings("unchecked")
		List<Cancion> resultado = em.createQuery(query).getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return resultado;
	}

}
