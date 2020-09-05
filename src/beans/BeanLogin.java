package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;


@SuppressWarnings("deprecation")
@ManagedBean(name = "beanLogin")
@SessionScoped
public class BeanLogin {
	private String usuario;
	private String clave;
	
	private BasicPlayer player = Controlador.getInstancia().getListener().getPlayer();

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}

	public String login() {
		try {
			if (Controlador.getInstancia().login(usuario, clave)) {
				System.out.println();
				setUsuario(usuario);
				setClave(clave);
				return "index";
			} else {
				System.out.println();
				setUsuario(new String());
				setClave(new String());
				return "login";
			}
		} catch (Exception e) {
			System.out.println();
			setClave(new String());
			setUsuario(new String());
			return "login";
		}
	}
	
	public String logout() {
		try {
			player.stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		setUsuario(null);
		setClave(null);
		return "login";
	}
}