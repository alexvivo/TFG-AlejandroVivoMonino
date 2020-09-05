package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanAnadirCancionListaReproduccion")
@SessionScoped
public class BeanAnadirCancionListaReproduccion {
	
	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;
	
	@ManagedProperty("#{beanListarListaReproduccion}")
	private BeanListarListaReproduccion beanListarListaReproduccion;
	
	@ManagedProperty("#{beanListarListasReproduccionPropias}")
	private BeanListarListasReproduccionPropias beanListarListasReproduccionPropias;

	public String anadirCancion(String idLista) {
		if (beanLogin != null && beanListarListaReproduccion != null && beanListarListasReproduccionPropias != null) {
			Controlador.getInstancia().añadirCancion(beanLogin.getUsuario(), Integer.parseInt(idLista), 
				beanListarListasReproduccionPropias.getArtista(), 
				beanListarListasReproduccionPropias.getTituloCancion(), 
				beanListarListasReproduccionPropias.getTituloAlbum());
			return beanListarListaReproduccion.listarLista(idLista);
		}
		return null;
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

	public BeanListarListasReproduccionPropias getBeanListarListasReproduccionPropias() {
		return beanListarListasReproduccionPropias;
	}

	public void setBeanListarListasReproduccionPropias(
			BeanListarListasReproduccionPropias beanListarListasReproduccionPropias) {
		this.beanListarListasReproduccionPropias = beanListarListasReproduccionPropias;
	}
	
	

	
}
