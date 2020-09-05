package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controlador.Controlador;

@SuppressWarnings("deprecation")
@ManagedBean(name = "beanRegistrar")
@SessionScoped
public class BeanRegistrar {
	private String usuario;
	private String email;
	private String contrase�a;
	private String fecha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String registrar() {
		if (fecha != null) {
			String[] componentesFecha = fecha.split("/");
			int dia = Integer.parseInt(componentesFecha[0]);
			int mes = Integer.parseInt(componentesFecha[1]);
			int a�o = Integer.parseInt(componentesFecha[2]);
			try {
				if (Controlador.getInstancia().registrar(usuario, email, contrase�a, a�o, mes, dia)) {
					setUsuario(usuario);
					setEmail(email);
					setContrase�a(contrase�a);
					setFecha(fecha);
					return "login";
				} else {
					setUsuario(usuario);
					setEmail(email);
					setContrase�a(contrase�a);
					setFecha(fecha);
					return "registrar";
				}
			} catch (Exception e) {
				setUsuario(usuario);
				setEmail(email);
				setContrase�a(contrase�a);
				setFecha(fecha);
				return "registrar";
			}
		}
		setUsuario(usuario);
		setEmail(email);
		setContrase�a(contrase�a);
		setFecha(fecha);
		return "registrar";
	}
}
