package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.Artista;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanListarArtistasFavoritos")
@SessionScoped
public class BeanListarArtistasFavoritos {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private Collection<Artista> artistasFavoritos;

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Collection<Artista> getArtistasFavoritos() {
		artistasFavoritos = new LinkedList<Artista>();
		if (beanLogin != null) {
			Collection<Artista> allArtistasFavoritos = Controlador.getInstancia().listarArtistasFavoritos(beanLogin.getUsuario());
			artistasFavoritos.addAll(allArtistasFavoritos);
		}
		return artistasFavoritos;
	}

	public void setArtistasFavoritos(Collection<Artista> artistasFavoritos) {
		this.artistasFavoritos = artistasFavoritos;
	}
	
	public String quitarArtistaFavorito(String artista) {
		if (beanLogin != null) {
			Controlador.getInstancia().quitarArtistaFavorito(beanLogin.getUsuario(), artista);
			return "artists";
		}
		return null;
	}

}
