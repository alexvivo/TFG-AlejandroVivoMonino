package dao;

import java.util.Collection;

import modelo.Artista;

public interface IArtistaDAO {
	
	public Artista create(String nombre);
	
	public Artista read(String nombre);
	
	public void update(Artista artista);
	
	public void delete(String nombre);
	
	public Collection<Artista> getResultadosArtistas(String cadenaBusqueda);

}