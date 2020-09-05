package controlador;

import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class Listener implements BasicPlayerListener {
	
	private BasicPlayer player;
	
	public Listener() {
		player = new BasicPlayer();
		player.addBasicPlayerListener(this);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void opened(Object arg0, Map arg1) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateUpdated(BasicPlayerEvent event) {
		if (event.getCode() == BasicPlayerEvent.EOM) {
			Controlador.getInstancia().siguienteCancion();
		}
	}

	public BasicPlayer getPlayer() {
		return player;
	}
	
	

}
