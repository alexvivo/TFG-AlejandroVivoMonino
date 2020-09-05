package dao;

public class FactoriaDAOImpl extends FactoriaDAO {
	
	@Override
	public IArtistaDAO getArtistaDAO() {
		return new ArtistaDAO();
	}

	@Override
	public IAlbumDAO getAlbumDAO() {
		return new AlbumDAO();
	}

	@Override
	public ICancionDAO getCancionDAO() {
		return new CancionDAO();
	}

	@Override
	public IListaReproduccionDAO getListaReproduccionDAO() {
		return new ListaReproduccionDAO();
	}

	@Override
	public IUsuarioDAO getUsuarioDAO() {
		return new UsuarioDAO();
	}

}
