package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.ListaReproduccion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarListasReproduccionFavoritas")
@SessionScoped
public class BeanListarListasReproduccionFavoritas {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<ListaReproduccion> listasReproduccionFavoritas;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Collection<ListaReproduccion> getListasReproduccionFavoritas() {
		listasReproduccionFavoritas = new LinkedList<ListaReproduccion>();
		if (beanLogin != null) {
			Collection<ListaReproduccion> allListasReproduccionFavoritas = Controlador.getInstancia().listarListasReproduccionFavoritas(beanLogin.getUsuario());
			listasReproduccionFavoritas.addAll(allListasReproduccionFavoritas);
		}
		return listasReproduccionFavoritas;
	}

	public void setListasReproduccionFavoritas(Collection<ListaReproduccion> listasReproduccionFavoritas) {
		this.listasReproduccionFavoritas = listasReproduccionFavoritas;
	}

	public String quitarListaFavorita(String idLista) {
		if (beanLogin != null) {
			Controlador.getInstancia().quitarListaFavorita(beanLogin.getUsuario(), Integer.parseInt(idLista));
			return "playlists";
		}
		return null;
	}

}
