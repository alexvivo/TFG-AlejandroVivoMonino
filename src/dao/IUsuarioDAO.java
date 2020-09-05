package dao;

import java.util.Date;

import modelo.Usuario;

public interface IUsuarioDAO {
	
	public Usuario create(String nombre, String email, String contraseña, Date fechaNacimiento);
	
	public Usuario read(String nombre);
	
	public void update(Usuario usuario);
	
	public void delete(String nombre);

}