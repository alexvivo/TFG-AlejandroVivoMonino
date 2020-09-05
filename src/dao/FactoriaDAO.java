package dao;

public abstract class FactoriaDAO {
	
	private static FactoriaDAO instancia;
	
	protected FactoriaDAO() {
		
	}
	
	public static FactoriaDAO getInstancia() {
		if (instancia == null) {
			instancia = new FactoriaDAOImpl();
		}
		return instancia;
	}
	
	public abstract IArtistaDAO getArtistaDAO();
	
	public abstract IAlbumDAO getAlbumDAO();
	
	public abstract ICancionDAO getCancionDAO();
	
	public abstract IListaReproduccionDAO getListaReproduccionDAO();
	
	public abstract IUsuarioDAO getUsuarioDAO();

}
