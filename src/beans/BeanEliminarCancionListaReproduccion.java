package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanEliminarCancionListaReproduccion")
@SessionScoped
public class BeanEliminarCancionListaReproduccion {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	@ManagedProperty("#{beanListarListaReproduccion}")
	private BeanListarListaReproduccion beanListarListaReproduccion;

	public String eliminarCancion(String artista, String titulo, String album) {
		if (beanLogin != null && beanListarListaReproduccion != null) {
			Controlador.getInstancia().eliminarCancion(beanLogin.getUsuario(), Integer.parseInt(beanListarListaReproduccion.getId()), artista, titulo, album);
			return "playlist";
		}
		return null;
	}

	public BeanListarListaReproduccion getBeanListarListaReproduccion() {
		return beanListarListaReproduccion;
	}

	public void setBeanListarListaReproduccion(BeanListarListaReproduccion beanListarListaReproduccion) {
		this.beanListarListaReproduccion = beanListarListaReproduccion;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	
}
