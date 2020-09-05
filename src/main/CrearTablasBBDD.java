package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablasBBDD {

	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost?useSSL=false","root", "root");
			
			Statement s = conexion.createStatement();
			try {
				s.execute ("CREATE SCHEMA TFG;");
				System.out.println("Esquema TFG corréctamente creado en la base de datos");
			} catch (SQLException e) {
				System.out.println("El esquema TFG ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.ALBUM (" + 
						"	ARTISTA CHAR(255)," + 
						"    TITULO CHAR(255)," + 
						"    ANYO INT," + 
						"    PRIMARY KEY (ARTISTA, TITULO)" + 
						");");
				System.out.println("Tabla ALBUM corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla ALBUM ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.ARTISTA (" + 
						"	NOMBRE CHAR(255)," + 
						"    PRIMARY KEY (NOMBRE)" + 
						");;");
				System.out.println("Tabla ARTISTA corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla ARTISTA ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.CANCION"
						+ "("
						+ "NUMERO INT,"
						+ "TITULO CHAR(255),"
						+ "ARTISTA CHAR(255),"
						+ "ALBUM CHAR(255),"
						+ "GENERO CHAR(255),"
						+ "PRIMARY KEY(TITULO, ARTISTA, ALBUM)"
						+ ")");
				System.out.println("Tabla CANCION corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla CANCION ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.LISTAREPRODUCCION (" + 
						"	ID INT NOT NULL AUTO_INCREMENT," +
						"	NOMBRE CHAR(255)," + 
						"    USUARIO CHAR(255)," + 
						"    PRIMARY KEY (ID)" + 
						");");
				System.out.println("Tabla LISTAREPRODUCCION corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla LISTAREPRODUCCION ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.USUARIO"
						+ "("
						+ "NOMBRE CHAR(255),"
						+ "EMAIL CHAR(255),"
						+ "CONTRASEÑA CHAR(255),"
						+ "FECHANACIMIENTO DATE,"
						+ "PRIMARY KEY(NOMBRE)"
						+ ")");
				System.out.println("Tabla USUARIO corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla USUARIO ya existe en la base de datos");
			}			
			try {
				s.execute ("CREATE TABLE TFG.USUARIO_ALBUM (" + 
						"	 USUARIO_ID CHAR(255)," +
						"	 ARTISTA CHAR(255)," +
						"	 TITULO_ALBUM CHAR(255)," +
						"	 FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (NOMBRE)," + 
						"    FOREIGN KEY (ARTISTA, TITULO_ALBUM) REFERENCES ALBUM (ARTISTA, TITULO)" + 
						");");
				System.out.println("Tabla USUARIO_ALBUM corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla USUARIO_ALBUM ya existe en la base de datos");
			}
			
			try {
				s.execute ("CREATE TABLE TFG.USUARIO_ARTISTA (" + 
						"	 USUARIO_ID CHAR(255)," +
						"	 ARTISTA CHAR(255)," +
						"	 FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (NOMBRE)," + 
						"    FOREIGN KEY (ARTISTA) REFERENCES ARTISTA (NOMBRE)" + 
						");");
				System.out.println("Tabla USUARIO_ARTISTA corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla USUARIO_ARTISTA ya existe en la base de datos");
			}
			
			try {
				s.execute ("CREATE TABLE TFG.USUARIO_CANCION (" + 
						"	 USUARIO_ID CHAR(255)," +
						"	 TITULO CHAR(255)," +
						"	 ARTISTA CHAR(255)," +
						"	 ALBUM CHAR(255)," +
						"	 FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (NOMBRE)," + 
						"    FOREIGN KEY (TITULO, ARTISTA, ALBUM) REFERENCES CANCION (TITULO, ARTISTA, ALBUM)" + 
						");");
				System.out.println("Tabla USUARIO_CANCION corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla USUARIO_CANCION ya existe en la base de datos");
			}
			
			try {
				s.execute ("CREATE TABLE TFG.USUARIO_LISTA (" + 
						"	 USUARIO_ID CHAR(255)," +
						"	 ID_LISTA INT NOT NULL," +
						"	 FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (NOMBRE)," + 
						"    FOREIGN KEY (ID_LISTA) REFERENCES LISTAREPRODUCCION (ID)" + 
						");");
				System.out.println("Tabla USUARIO_LISTA corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla USUARIO_LISTA ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.CANCION_LISTA (" + 
					"	TITULO CHAR(255)," +
					"	ARTISTA CHAR(255)," +
					"	ALBUM CHAR(255)," +
					"	ID_LISTA INT NOT NULL," +
					"	FOREIGN KEY (TITULO, ARTISTA, ALBUM) REFERENCES CANCION (TITULO, ARTISTA, ALBUM)," + 
					"    FOREIGN KEY (ID_LISTA) REFERENCES LISTAREPRODUCCION (ID)" + 
					"    );");
				System.out.println("Tabla CANCION_LISTA corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla CANCION_LISTA ya existe en la base de datos");
			}
			try {
				s.execute ("CREATE TABLE TFG.CANCION_USUARIO (" + 
					"	 TITULO CHAR(255)," +
					"	 ARTISTA CHAR(255)," +
					"	 ALBUM CHAR(255)," +
					"    USUARIO_ID CHAR(255)," + 
					"	 FOREIGN KEY (TITULO, ARTISTA, ALBUM) REFERENCES CANCION (TITULO, ARTISTA, ALBUM)," + 
					"    FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (NOMBRE)" + 
					");");
				System.out.println("Tabla CANCION_USUARIO corréctamente creada en la base de datos");
			} catch (SQLException e) {
				System.out.println("La tabla CANCION_USUARIO ya existe en la base de datos");
			}		
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
