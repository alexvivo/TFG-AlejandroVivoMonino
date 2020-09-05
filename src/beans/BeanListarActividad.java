package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Cancion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarActividad")
@SessionScoped
public class BeanListarActividad {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<Cancion> cancionesEscuchadas;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Collection<Cancion> getCancionesEscuchadas() {
		cancionesEscuchadas = new LinkedList<Cancion>();
		if (beanLogin != null) {
			Collection<Cancion> allCancionesEscuchadas = Controlador.getInstancia().listarActividad(beanLogin.getUsuario());
			cancionesEscuchadas.addAll(allCancionesEscuchadas);
		}
		return cancionesEscuchadas;
	}

	public void setCancionesEscuchadas(Collection<Cancion> cancionesEscuchadas) {
		this.cancionesEscuchadas = cancionesEscuchadas;
	}
	
}
