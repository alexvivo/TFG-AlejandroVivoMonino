package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Cancion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarListaReproduccion")
@SessionScoped
public class BeanListarListaReproduccion {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<Cancion> listaCanciones;
	private String id;
	private String nombre;
	private String usuario;
	
	public Collection<Cancion> getListaCanciones() {
		listaCanciones = new LinkedList<Cancion>();		
		Collection<Cancion> canciones = Controlador.getInstancia().listarListaReproduccion(Integer.parseInt(id));
		listaCanciones.addAll(canciones);
		return listaCanciones;
	}
	
	public String getNombre() {
		nombre = Controlador.getInstancia().getNombreListaReproduccion(Integer.parseInt(id));
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		usuario = Controlador.getInstancia().getUsuarioListaReproduccion(Integer.parseInt(id));
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setListaCanciones(Collection<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String listarLista(String id) {
		setId(id);
		return "playlist";
	}
	
	public BeanLogin getBeanLogin() {
		return beanLogin;
	}
	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}
	
	public void reproducirLista() {
		if (beanLogin != null)
			Controlador.getInstancia().reproducirLista(beanLogin.getUsuario(), Integer.parseInt(id));
	}

}
