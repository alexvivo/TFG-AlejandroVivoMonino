package dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import modelo.Cancion;
import modelo.ListaReproduccion;
import modelo.Usuario;

public class ListaReproduccionDAO implements IListaReproduccionDAO {

	private EntityManagerFactory emf;

	public ListaReproduccionDAO() {
		emf = Persistence.createEntityManagerFactory("TFG-AlejandroVivoMonino");
	}

	@Override
	public ListaReproduccion create(String nombre, String usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ListaReproduccion listaReproduccion = new ListaReproduccion();
		listaReproduccion.setCanciones(new LinkedList<Cancion>());
		listaReproduccion.setNombre(nombre);
		Usuario _usuario = em.find(Usuario.class, usuario);
		_usuario.getListasReproduccion().add(listaReproduccion);
		listaReproduccion.setUsuario(_usuario);
		em.persist(listaReproduccion);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return listaReproduccion;
	}

	@Override
	public ListaReproduccion read(int id) {
		EntityManager em = emf.createEntityManager();
		ListaReproduccion listaReproduccion = em.find(ListaReproduccion.class, id);
		em.close();
		emf.close();
		return listaReproduccion;
	}

	@Override
	public void update(ListaReproduccion listaReproduccion) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(listaReproduccion);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ListaReproduccion listaReproduccion = em.find(ListaReproduccion.class, id);
		if (em.contains(listaReproduccion))
			em.remove(listaReproduccion);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public Collection<ListaReproduccion> getResultadosListasReproduccion(String cadenaBusqueda) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String query = "SELECT l FROM ListaReproduccion l WHERE l.nombre LIKE '%" + cadenaBusqueda
				+ "%'";
		@SuppressWarnings("unchecked")
		List<ListaReproduccion> resultado = em.createQuery(query).getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return resultado;
	}

}
