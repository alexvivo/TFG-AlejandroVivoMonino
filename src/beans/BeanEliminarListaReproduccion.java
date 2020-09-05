package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanEliminarListaReproduccion")
@SessionScoped
public class BeanEliminarListaReproduccion {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	@ManagedProperty("#{beanListarListaReproduccion}")
	private BeanListarListaReproduccion beanListarListaReproduccion;
	
	public String eliminarListaReproduccion() {
		if (beanLogin != null && beanListarListaReproduccion != null && Controlador.getInstancia().eliminarListaReproduccion(beanLogin.getUsuario(), 
				Integer.parseInt(beanListarListaReproduccion.getId()))) {
			return "playlists";
		}
		return "error";
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public BeanListarListaReproduccion getBeanListarListaReproduccion() {
		return beanListarListaReproduccion;
	}

	public void setBeanListarListaReproduccion(BeanListarListaReproduccion beanListarListaReproduccion) {
		this.beanListarListaReproduccion = beanListarListaReproduccion;
	}
	
	

}
