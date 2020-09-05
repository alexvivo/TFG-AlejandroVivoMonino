package main;

import controlador.Controlador;
import dao.FactoriaDAO;
import modelo.*;

public class ControladorTest {

	public static void main(String[] args) {
		
		// Registrar un usuario
		if (Controlador.getInstancia().registrar("alexvivo", "alexvivo77@hotmail.com", "alexvivo", 1990, 9, 17)) {
			System.out.println("Registro con éxito");
		} else {
			System.out.println("Usuario ya registrado o no válido");
		}
		
		// Login de un usuario
		if (Controlador.getInstancia().login("alexvivo", "alexvivo")) {
			System.out.println("Inicio de sesión correcto");
		} else {
			System.out.println("Error al iniciar sesión");
		}
		
		Usuario usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		CancionPK cancionPK = new CancionPK();
		cancionPK.setArtista("ADC LEVEL");
		cancionPK.setTituloAlbum("ACTARUS");
		cancionPK.setTituloCancion("ACTARUS");
		
		System.out.println("Cancion escuchadas por usuario alexvivo");
		
		for (Cancion cancion: usuario.getCancionesEscuchadas()) {
			System.out.println("Artista: " + cancion.getCancionPK().getArtista());
			System.out.println("Titulo: " + cancion.getCancionPK().getTituloCancion());
			System.out.println("Álbum: " + cancion.getCancionPK().getTituloAlbum());
			System.out.println();
		}
		
		System.out.println();
		
		for (Cancion cancion: Controlador.getInstancia().listarAlbum("Barefoot McCoy", "Ballyhoo")) {
			System.out.println(cancion.getNumCancion() + " - " + cancion.getCancionPK().getTituloCancion());
		}
		
		System.out.println();
		
		for (Album album: Controlador.getInstancia().listarArtista("ADC LEVEL")) {
			
			System.out.println("Título álbum: " + album.getAlbumPK().getTitulo());
			
		}
		
		System.out.println();
		
		int idLista = Controlador.getInstancia().crearListaReproduccion("alexvivo", "Nueva lista");
		
		if (idLista != -1) {
			System.out.println("Lista creada corréctamente");
		} else {
			System.out.println("Esta lista ya existe");
		}
		
		if (Controlador.getInstancia().añadirCancion("alexvivo", idLista, "ADC LEVEL", "ACTARUS", "ACTARUS")) {
			System.out.println("Cancion añadida corréctamente");
		} else {
			System.out.println("No se puede añadir esta canción a esta lista");
		}
		
		System.out.println();
		
		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		System.out.println("Listas de reproduccion de alexvivo");
		
		for (ListaReproduccion listaReproduccion: usuario.getListasReproduccion()) {			
			System.out.println(listaReproduccion.getNombre());
		}
		
		System.out.println();
		System.out.println("Nueva lista");
		System.out.println("-----------------------");
		for (Cancion cancion: Controlador.getInstancia().listarListaReproduccion(idLista)) {
			System.out.println(cancion.getCancionPK().getArtista() + " - " 
					+ cancion.getCancionPK().getTituloCancion() +  " - " 
					+ cancion.getCancionPK().getTituloAlbum());
		}
		
		if (Controlador.getInstancia().renombrarLista("alexvivo", idLista, "Nueva lista 2")) {
			System.out.println("Lista renombrada corréctamente");
		} else {
			System.out.println("No se puede renombrar esta lista");
		}
		
		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		System.out.println("Listas de reproduccion de alexvivo");
		
		
		for (ListaReproduccion listaReproduccion: usuario.getListasReproduccion()) {			
			System.out.println(listaReproduccion.getNombre());
		}
		
		System.out.println();
		System.out.println("Nueva lista 2");
		System.out.println("-----------------------");
		for (Cancion cancion: Controlador.getInstancia().listarListaReproduccion(idLista)) {
			System.out.println(cancion.getCancionPK().getArtista() + " - " 
					+ cancion.getCancionPK().getTituloCancion() +  " - " 
					+ cancion.getCancionPK().getTituloAlbum());
		}
		
		if (Controlador.getInstancia().renombrarLista("alexvivo", idLista, "Nueva lista")) {
			System.out.println("Lista renombrada corréctamente");
		} else {
			System.out.println("No se puede renombrar esta lista");
		}
		
		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		System.out.println("Listas de reproduccion de alexvivo");
		
		
		for (ListaReproduccion listaReproduccion: usuario.getListasReproduccion()) {			
			System.out.println(listaReproduccion.getNombre());
		}
		
		System.out.println();
		System.out.println("Nueva lista");
		System.out.println("-----------------------");
		for (Cancion cancion: Controlador.getInstancia().listarListaReproduccion(idLista)) {
			System.out.println(cancion.getCancionPK().getArtista() + " - " 
					+ cancion.getCancionPK().getTituloCancion() +  " - " 
					+ cancion.getCancionPK().getTituloAlbum());
		}
		
		if (Controlador.getInstancia().eliminarListaReproduccion("alexvivo", idLista)) {
			System.out.println("Lista de reproduccion eliminada corréctamente");
		} else {
			System.out.println("No se puede añadir esta canción a esta lista");
		}
		
		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		System.out.println("Listas de reproduccion de alexvivo");
		
		
		for (ListaReproduccion listaReproduccion: usuario.getListasReproduccion()) {
			System.out.println(listaReproduccion.getNombre());
		}
		
		int idLista2 = Controlador.getInstancia().crearListaReproduccion("alexvivo", "Nueva lista");
		
		if (idLista2 != -1) {
			System.out.println("Lista creada corréctamente");
		} else {
			System.out.println("Esta lista ya existe");
		}
		
		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		System.out.println("Listas de reproduccion de alexvivo");
		
		
		for (ListaReproduccion listaReproduccion: usuario.getListasReproduccion()) {
			System.out.println(listaReproduccion.getNombre());
		}
		
		if (Controlador.getInstancia().añadirCancion("alexvivo", idLista2, "ADC LEVEL", "ACTARUS", "ACTARUS")) {
			System.out.println("Cancion añadida corréctamente");
		} else {
			System.out.println("No se puede añadir esta canción a esta lista");
		}
		
		System.out.println();
		
//		if (Controlador.getInstancia().eliminarCancion("alexvivo", idLista2, "ADC LEVEL", "ACTARUS", "ACTARUS")) {
//			System.out.println("Cancion eliminada corréctamente de esta lista");
//		} else {
//			System.out.println("No se puede eliminar esta canción de esta lista");
//		}
//		
//		System.out.println();
		
		System.out.println("RESULTADOS ALBUMES");
		
		for (Album album: Controlador.getInstancia().buscarPorAlbum("actarus")) {
			System.out.println(album.getAlbumPK().getTitulo());
		}
		
		System.out.println("RESULTADOS ARTISTAS");
		
		for (Artista artista: Controlador.getInstancia().buscarPorArtista("adc")) {
			System.out.println(artista.getNombre());
		}
		
		System.out.println("RESULTADOS CANCIONES");
		
		for (Cancion cancion: Controlador.getInstancia().buscarPorCancion("actarus")) {
			System.out.println(cancion.getCancionPK().getTituloCancion());
		}
		
		System.out.println("RESULTADOS LISTAS DE REPRODUCCION");
		
		for (ListaReproduccion listaReproduccion: Controlador.getInstancia().buscarPorListaReproduccion("nueva")) {
			System.out.println(listaReproduccion.getNombre());
		}
		
		// Registrar un usuario
		if (Controlador.getInstancia().registrar("alexvivo2", "alexvivo77@hotmail.com", "alexvivo", 1990, 9, 17)) {
			System.out.println("Registro con éxito");
		} else {
			System.out.println("Usuario ya registrado o no válido");
		}
		
		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
		
		System.out.println("AÑADIENDO FAVORITOS...");
		
		Controlador.getInstancia().marcarAlbumFavorito("alexvivo", "ACTARUS", "ADC LEVEL");
		Controlador.getInstancia().marcarArtistaFavorito("alexvivo", "ADC LEVEL");
		Controlador.getInstancia().marcarCancionFavorita("alexvivo", "ACTARUS", "ADC LEVEL", "ACTARUS");
		Controlador.getInstancia().marcarCancionFavorita("alexvivo", "EXTERMINATOR 3000", "ADC LEVEL", "EXTERMINATOR 3000");
		Controlador.getInstancia().marcarListaFavorita("alexvivo", idLista2);
		
		System.out.println("ALBUMES FAVORITOS");
		
		
		for (Album album: Controlador.getInstancia().listarAlbumesFavoritos(usuario.getNombre())) {
			System.out.println(album.getAlbumPK().getTitulo());
			System.out.println(album.getAlbumPK().getArtista());
		}
		
		System.out.println("ARTISTAS FAVORITOS");
		
		
		for (Artista artista: Controlador.getInstancia().listarArtistasFavoritos(usuario.getNombre())) {
			System.out.println(artista.getNombre());
		}
		
		System.out.println("CANCIONES FAVORITAS");
		
		
		for (Cancion cancion: Controlador.getInstancia().listarCancionesFavoritas(usuario.getNombre())) {
			System.out.println(cancion.getCancionPK().getTituloCancion());
			System.out.println(cancion.getCancionPK().getArtista());
			System.out.println(cancion.getCancionPK().getTituloAlbum());
		}
		
		System.out.println("LISTAS FAVORITAS");
		
		
		for (ListaReproduccion listaReproduccion: Controlador.getInstancia().listarListasReproduccionFavoritas(usuario.getNombre())) {
			System.out.println(listaReproduccion.getNombre());
			System.out.println(listaReproduccion.getUsuario().getNombre());
		}
		
//		usuario = FactoriaDAO.getInstancia().getUsuarioDAO().read("alexvivo");
//		
//		System.out.println("QUITANDO FAVORITOS...");
//		
//		Controlador.getInstancia().quitarAlbumFavorito("alexvivo", "ACTARUS", "ADC LEVEL");
//		Controlador.getInstancia().quitarArtistaFavorito("alexvivo", "ADC LEVEL");
//		Controlador.getInstancia().quitarCancionFavorita("alexvivo", "ACTARUS", "ADC LEVEL", "ACTARUS");
//		Controlador.getInstancia().quitarListaFavorita("alexvivo", idLista2);
//		
//		System.out.println("ALBUMES FAVORITOS");
//		
//		for (Album album: usuario.getAlbumesFavoritos()) {
//			System.out.println(album.getAlbumPK().getTitulo());
//			System.out.println(album.getAlbumPK().getArtista());
//		}
//		
//		System.out.println("ARTISTAS FAVORITOS");
//		
//		for (Artista artista: usuario.getArtistasFavoritos()) {
//			System.out.println(artista.getNombre());
//		}
//		
//		System.out.println("CANCIONES FAVORITAS");
//		
//		for (Cancion cancion: usuario.getCancionesFavoritas()) {
//			System.out.println(cancion.getCancionPK().getTituloCancion());
//			System.out.println(cancion.getCancionPK().getArtista());
//			System.out.println(cancion.getCancionPK().getTituloAlbum());
//		}
//		
//		System.out.println("LISTAS FAVORITAS");
//		
//		for (ListaReproduccion listaReproduccion: usuario.getListasFavoritas()) {
//			System.out.println(listaReproduccion.getNombre());
//			System.out.println(listaReproduccion.getUsuario().getNombre());
//		}
	}

}
