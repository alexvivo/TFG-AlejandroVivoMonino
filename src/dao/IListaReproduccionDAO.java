package dao;

import java.util.Collection;

import modelo.ListaReproduccion;

public interface IListaReproduccionDAO {
	
	public ListaReproduccion create(String nombre, String usuario);
	
	public ListaReproduccion read(int id);
	
	public void update(ListaReproduccion listaReproduccion);
	
	public void delete(int id);
	
	public Collection<ListaReproduccion> getResultadosListasReproduccion(String cadenaBusqueda);

}