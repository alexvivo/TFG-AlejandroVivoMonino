package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanCrearListaReproduccion")
@SessionScoped
public class BeanCrearListaReproduccion {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private String nombre;
	
	public String crearListaReproduccion() {
		if (beanLogin != null && Controlador.getInstancia().crearListaReproduccion(beanLogin.getUsuario(), nombre) != -1)
			return "playlists";
		return null;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
