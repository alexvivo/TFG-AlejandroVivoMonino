package dao;

import java.util.Collection;

import modelo.Album;
import modelo.AlbumPK;

public interface IAlbumDAO {
	
	public Album create(String artista, String titulo, int anyo);
	
	public Album read(AlbumPK albumPK);
	
	public void update(Album album);
	
	public void delete(AlbumPK albumPK);
	
	public Collection<Album> getResultadosAlbumes(String cadenaBusqueda);

}
