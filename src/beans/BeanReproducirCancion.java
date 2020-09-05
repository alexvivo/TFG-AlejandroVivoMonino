package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
//import javazoom.jlgui.basicplayer.BasicPlayer;
@SuppressWarnings("deprecation")
@ManagedBean(name = "beanReproducirCancion")
@SessionScoped
public class BeanReproducirCancion {

	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;


	public void reproducirCancion(String artista, String titulo, String album) {
		if (beanLogin != null)
			Controlador.getInstancia().reproducirCancion(beanLogin.getUsuario(), artista, titulo, album);
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}
}
