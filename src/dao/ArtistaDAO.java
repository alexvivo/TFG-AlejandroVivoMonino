package dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import modelo.Album;
import modelo.Artista;

public class ArtistaDAO implements IArtistaDAO {
	
	private EntityManagerFactory emf;
	
	public ArtistaDAO() {
		emf = Persistence.createEntityManagerFactory("TFG-AlejandroVivoMonino");
	}

	@Override
	public Artista create(String nombre) {
		EntityManager em = emf.createEntityManager();
		Artista artista = em.find(Artista.class, nombre);
		if (artista == null) {
			em.getTransaction().begin();
			artista = new Artista();
			artista.setNombre(nombre);
			artista.setDiscografia(new LinkedList<Album>());
			em.persist(artista);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
		
		return artista;
	}

	@Override
	public Artista read(String nombre) {
		EntityManager em = emf.createEntityManager();
		Artista	artista = em.find(Artista.class, nombre);
		em.close();
		emf.close();
		return artista;
	}

	@Override
	public void update(Artista artista) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(artista);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void delete(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Artista	artista = em.find(Artista.class, nombre);
		if (em.contains(artista))
			em.remove(artista);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public Collection<Artista> getResultadosArtistas(String cadenaBusqueda) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String query = "SELECT a FROM Artista a WHERE a.nombre LIKE '%" + cadenaBusqueda + "%'";
		@SuppressWarnings("unchecked")
		List<Artista> resultado = em.createQuery(query).getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return resultado;
	}
	
	

}
