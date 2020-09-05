package modelo;

import java.util.List;

import javax.persistence.*;

@Entity
public class Artista {
	
	@Id
	private String nombre;
	@OneToMany(mappedBy="artista", cascade = CascadeType.ALL)
	@OrderBy("anyo DESC")
	private List<Album> discografia;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Album> getDiscografia() {
		return discografia;
	}
	public void setDiscografia(List<Album> discografia) {
		this.discografia = discografia;
	}
	

}
