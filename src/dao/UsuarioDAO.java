package dao;

import java.util.Date;
import java.util.LinkedList;

import javax.persistence.*;

import modelo.Album;
import modelo.Artista;
import modelo.Cancion;
import modelo.ListaReproduccion;
import modelo.Usuario;

public class UsuarioDAO implements IUsuarioDAO {
	
	private EntityManagerFactory emf;
	
	public UsuarioDAO() {
		emf = Persistence.createEntityManagerFactory("TFG-AlejandroVivoMonino");
	}

	@Override
	public Usuario create(String nombre, String email, String contraseña, Date fechaNacimiento) {
		EntityManager em = emf.createEntityManager();
		Usuario usuario = em.find(Usuario.class, nombre);
		if (usuario == null) {
			em.getTransaction().begin();
			usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setContraseña(contraseña);
			usuario.setEmail(email);
			usuario.setFechaNacimiento(fechaNacimiento);
			usuario.setListasReproduccion(new LinkedList<ListaReproduccion>());
			usuario.setCancionesEscuchadas(new LinkedList<Cancion>());
			usuario.setAlbumesFavoritos(new LinkedList<Album>());
			usuario.setArtistasFavoritos(new LinkedList<Artista>());
			usuario.setCancionesFavoritas(new LinkedList<Cancion>());
			usuario.setListasFavoritas(new LinkedList<ListaReproduccion>());
			em.persist(usuario);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
		
		return usuario;
	}

	@Override
	public Usuario read(String nombre) {
		EntityManager em = emf.createEntityManager();
		Usuario usuario = em.find(Usuario.class, nombre);
		em.close();
		emf.close();
		return usuario;
	}

	@Override
	public void update(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void delete(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, nombre);
		if (em.contains(usuario))
			em.remove(usuario);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	

}
