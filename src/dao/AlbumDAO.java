package dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import modelo.Album;
import modelo.AlbumPK;
import modelo.Artista;
import modelo.Cancion;

public class AlbumDAO implements IAlbumDAO {
	
	private EntityManagerFactory emf;
	
	public AlbumDAO() {
		emf = Persistence.createEntityManagerFactory("TFG-AlejandroVivoMonino");
	}

	@Override
	public Album create(String artista, String titulo, int anyo) {
		EntityManager em = emf.createEntityManager();
		AlbumPK albumPK = new AlbumPK();
		albumPK.setTitulo(titulo);
		albumPK.setArtista(artista);
		Album album = em.find(Album.class, albumPK);
		if (album == null) {
			em.getTransaction().begin();
			album = new Album();
			album.setAnyo(anyo);
			album.setAlbumPK(albumPK);
			Artista _artista = em.find(Artista.class, artista);
			if (_artista == null) {
				_artista = new Artista();
				_artista.setNombre(artista);
				_artista.setDiscografia(new LinkedList<Album>());
			}
			_artista.getDiscografia().add(album);
			album.setArtista(_artista);
			album.setCanciones(new LinkedList<Cancion>());
			em.persist(album);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
		
		return album;
	}

	@Override
	public Album read(AlbumPK albumPK) {
		EntityManager em = emf.createEntityManager();
		Album album = em.find(Album.class, albumPK);
		em.close();
		emf.close();
		return album;
	}

	@Override
	public void update(Album album) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(album);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void delete(AlbumPK albumPK) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Album album = em.find(Album.class, albumPK);
		if (em.contains(albumPK))
			em.remove(album);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public Collection<Album> getResultadosAlbumes(String cadenaBusqueda) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String query = "SELECT a FROM Album a WHERE a.albumPK.titulo LIKE '%" + cadenaBusqueda + "%'";
		@SuppressWarnings("unchecked")
		List<Album> resultado = em.createQuery(query).getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return resultado;
	}
	
	

}
