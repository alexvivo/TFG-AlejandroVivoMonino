package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanReproductor")
@SessionScoped
public class BeanReproductor {

	private BasicPlayer player = Controlador.getInstancia().getListener().getPlayer();
	private String reproduciendo;
	private String playOPause;
	private String imgReproduciendo;
	
	public void siguienteCancion() {
		Controlador.getInstancia().siguienteCancion();
	}
	
	public void anteriorCancion() {
		Controlador.getInstancia().anteriorCancion();
	}
	
	public void playOPause() {
		try {
			if (player.getStatus() == BasicPlayer.PLAYING) {
				player.pause();
			} else if (player.getStatus() == BasicPlayer.PAUSED) {
				player.resume();
			} else {
				player.play();
			}
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	public String getReproduciendo() {
		if (Controlador.getInstancia().getCancionReproduciendo() != null)
			reproduciendo = Controlador.getInstancia().getCancionReproduciendo().getCancionPK().getArtista() + " - "
				+ Controlador.getInstancia().getCancionReproduciendo().getCancionPK().getTituloCancion();
		else
			reproduciendo = "";
		return reproduciendo;
	}

	public String getPlayOPause() {
		if (player.getStatus() == BasicPlayer.PLAYING) {
			playOPause = "img/pause_white.png";
		} else {
			playOPause = "img/play.png";
		}
		return playOPause;
	}

	public String getImgReproduciendo() {
		if (Controlador.getInstancia().getCancionReproduciendo() == null) {
			imgReproduciendo = "img/song.jpg";
		} else {
			imgReproduciendo = "img/" + Controlador.getInstancia().getCancionReproduciendo().getCancionPK().getArtista() + "-"
				+ Controlador.getInstancia().getCancionReproduciendo().getCancionPK().getTituloAlbum() + ".jpg";
		}
		return imgReproduciendo;
	}
	
	public void setReproduciendo(String reproduciendo) {
		this.reproduciendo = reproduciendo;
	}

	public void setPlayOPause(String playOPause) {
		this.playOPause = playOPause;
	}

	public void setImgReproduciendo(String imgReproduciendo) {
		this.imgReproduciendo = imgReproduciendo;
	}


}
