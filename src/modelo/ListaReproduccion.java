package modelo;

import java.util.List;

import javax.persistence.*;

@Entity
public class ListaReproduccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany
	@JoinTable(name = "CANCION_LISTA",
		joinColumns = @JoinColumn(name = "ID_LISTA", referencedColumnName = "ID"),
		inverseJoinColumns =  {
				@JoinColumn(name = "TITULO", referencedColumnName = "TITULO"),
				@JoinColumn(name = "ARTISTA", referencedColumnName = "ARTISTA"),
				@JoinColumn(name = "ALBUM", referencedColumnName = "ALBUM")})
	private List<Cancion> canciones;
	@ManyToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuario;
	private String nombre;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
