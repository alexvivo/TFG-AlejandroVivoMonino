package dao;

import java.util.Collection;

import modelo.Cancion;
import modelo.CancionPK;

public interface ICancionDAO {
	
	public Cancion create(int numCancion, String titulo, String artista, String album, String genero, int anyo);
	
	public Cancion read(CancionPK cancionPK);
	
	public void update(Cancion cancion);
	
	public void delete(CancionPK cancionPK);
	
	public Collection<Cancion> getResultadosCanciones(String cadenaBusqueda);

}