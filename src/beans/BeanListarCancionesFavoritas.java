package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Cancion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarCancionesFavoritas")
@SessionScoped
public class BeanListarCancionesFavoritas {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<Cancion> cancionesFavoritas;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Collection<Cancion> getCancionesFavoritas() {
		cancionesFavoritas = new LinkedList<Cancion>();
		if (beanLogin != null) {
			Collection<Cancion> allCancionesFavoritas = Controlador.getInstancia().listarCancionesFavoritas(beanLogin.getUsuario());
			cancionesFavoritas.addAll(allCancionesFavoritas);
		}
		return cancionesFavoritas;
	}

	public void setCancionesFavoritas(Collection<Cancion> cancionesFavoritas) {
		this.cancionesFavoritas = cancionesFavoritas;
	}
	
	public String quitarCancionFavorita(String tituloCancion, String artista, String tituloAlbum) {
		if (beanLogin != null) {
			Controlador.getInstancia().quitarCancionFavorita(beanLogin.getUsuario(), tituloCancion, artista, tituloAlbum);
			return "favorites";
		}
		return null;
	}
	
}
