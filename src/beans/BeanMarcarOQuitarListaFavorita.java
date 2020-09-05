package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;
import modelo.ListaReproduccion;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanMarcarOQuitarListaFavorita")
@SessionScoped
public class BeanMarcarOQuitarListaFavorita {

	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	private String imagenBoton;
	
	private Collection<ListaReproduccion> listasReproduccionFavoritas;
	
	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public String getImagenBoton() {
		return imagenBoton;
	}

	public void setImagenBoton(String imagenBoton) {
		this.imagenBoton = imagenBoton;
	}
	
	public String setImagenBoton(String idLista, String idLista2) {
		
		if (idLista.equals(idLista2)) {
			if (isFavorito(idLista)) {
				imagenBoton = "img/fav.png";
			} else {
				imagenBoton = "img/star.png";
			}			
		}
		return imagenBoton;
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
	
	public boolean isFavorito(String idLista) {
		
		for (ListaReproduccion lista: getListasReproduccionFavoritas()) {
			if (lista.getId() == Integer.parseInt(idLista)) {
				return true;
			}
		}
		
		return false;
	}

	public void marcarOQuitarListaFavorita(String idLista) {
		
		if (beanLogin != null) {
			if (isFavorito(idLista)) {
				Controlador.getInstancia().quitarListaFavorita(beanLogin.getUsuario(), Integer.parseInt(idLista));
			} else {
				Controlador.getInstancia().marcarListaFavorita(beanLogin.getUsuario(), Integer.parseInt(idLista));
			}
		}
	}
	
	
}
