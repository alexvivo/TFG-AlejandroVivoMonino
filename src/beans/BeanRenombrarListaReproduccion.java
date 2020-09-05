package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanRenombrarListaReproduccion")
@SessionScoped
public class BeanRenombrarListaReproduccion {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	@ManagedProperty("#{beanListarListaReproduccion}")
	private BeanListarListaReproduccion beanListarListaReproduccion;
	
	private String nuevoNombre;
	
	public String renombrarListaReproduccion() {
		if (beanLogin != null && beanListarListaReproduccion != null && Controlador.getInstancia().renombrarLista(beanLogin.getUsuario(), 
				Integer.parseInt(beanListarListaReproduccion.getId()), nuevoNombre)) {
			setNuevoNombre(null);
			return "playlist";
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

	public String getNuevoNombre() {
		return nuevoNombre;
	}

	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
	}
}
