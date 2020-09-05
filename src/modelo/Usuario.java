package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario {
	
	@Id
	private String nombre;
	private String email;
	private String contraseña;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	@OneToMany(mappedBy="usuario")
	private List<ListaReproduccion> listasReproduccion;
	@OneToMany
	@JoinTable(name = "CANCION_USUARIO", 
		joinColumns = @JoinColumn(name = "USUARIO_ID", referencedColumnName = "NOMBRE"),
		inverseJoinColumns =  {
				@JoinColumn(name = "TITULO", referencedColumnName = "TITULO"),
				@JoinColumn(name = "ARTISTA", referencedColumnName = "ARTISTA"),
				@JoinColumn(name = "ALBUM", referencedColumnName = "ALBUM")})
	private List<Cancion> cancionesEscuchadas;
	@OneToMany
	@JoinTable(name = "USUARIO_ALBUM",
			joinColumns =  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "NOMBRE"),
			inverseJoinColumns = {@JoinColumn(name = "ARTISTA", referencedColumnName = "ARTISTA"),
								@JoinColumn(name = "TITULO_ALBUM", referencedColumnName = "TITULO")})
	private List<Album> albumesFavoritos;	
	@OneToMany
	@JoinTable(name = "USUARIO_ARTISTA",
			joinColumns =  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "NOMBRE"),
			inverseJoinColumns = {@JoinColumn(name = "ARTISTA", referencedColumnName = "NOMBRE")})
	private List<Artista> artistasFavoritos;
	@OneToMany
	@JoinTable(name = "USUARIO_CANCION",
			joinColumns =  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "NOMBRE"),
			inverseJoinColumns = {@JoinColumn(name = "TITULO", referencedColumnName = "TITULO"),
								@JoinColumn(name = "ARTISTA", referencedColumnName = "ARTISTA"),
								@JoinColumn(name = "ALBUM", referencedColumnName = "ALBUM")})
	private List<Cancion> cancionesFavoritas;
	@OneToMany
	@JoinTable(name = "USUARIO_LISTA",
			joinColumns =  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "NOMBRE"),
			inverseJoinColumns = @JoinColumn(name = "ID_LISTA", referencedColumnName = "ID"))
	private List<ListaReproduccion> listasFavoritas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public List<ListaReproduccion> getListasReproduccion() {
		return listasReproduccion;
	}
	public void setListasReproduccion(List<ListaReproduccion> listasReproduccion) {
		this.listasReproduccion = listasReproduccion;
	}
	public List<Cancion> getCancionesEscuchadas() {
		return cancionesEscuchadas;
	}
	public void setCancionesEscuchadas(List<Cancion> cancionesEscuchadas) {
		this.cancionesEscuchadas = cancionesEscuchadas;
	}
	public List<Album> getAlbumesFavoritos() {
		return albumesFavoritos;
	}
	public void setAlbumesFavoritos(List<Album> albumesFavoritos) {
		this.albumesFavoritos = albumesFavoritos;
	}
	public List<Artista> getArtistasFavoritos() {
		return artistasFavoritos;
	}
	public void setArtistasFavoritos(List<Artista> artistasFavoritos) {
		this.artistasFavoritos = artistasFavoritos;
	}
	public List<Cancion> getCancionesFavoritas() {
		return cancionesFavoritas;
	}
	public void setCancionesFavoritas(List<Cancion> cancionesFavoritas) {
		this.cancionesFavoritas = cancionesFavoritas;
	}
	public List<ListaReproduccion> getListasFavoritas() {
		return listasFavoritas;
	}
	public void setListasFavoritas(List<ListaReproduccion> listasFavoritas) {
		this.listasFavoritas = listasFavoritas;
	}
	

}
