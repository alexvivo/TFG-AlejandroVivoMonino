package main;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import dao.FactoriaDAO;

public class InsertarCancionesBBDD {

	private static List<File> ficherosRegulares = new LinkedList<File>();

	private static void getFicherosRegulares(File directorio) {
		if (directorio.isDirectory()) {
			File[] listFiles = directorio.listFiles();
			for (File file : listFiles) {
				if (file.isDirectory()) {
					getFicherosRegulares(file);
				} else
					ficherosRegulares.add(file);
			}
		}
	}

	public static void main(String[] args) {

		getFicherosRegulares(new File("server"));

		for (int i = 0; i < ficherosRegulares.size(); i++) {
			AudioFile f;
			try {
				
				f = AudioFileIO.read(ficherosRegulares.get(i));
				Tag tag = f.getTag();
				
				int numCancion = Integer.parseInt(tag.getFirst(FieldKey.TRACK).replaceAll("'", "''"));
				String titulo = tag.getFirst(FieldKey.TITLE).replaceAll("'", "''");
				String artista = tag.getFirst(FieldKey.ARTIST).replaceAll("'", "''");
				String album = tag.getFirst(FieldKey.ALBUM).replaceAll("'", "''");
				int anyo = Integer.parseInt(tag.getFirst(FieldKey.YEAR).replaceAll("'", "''"));
				String genero = tag.getFirst(FieldKey.GENRE).replaceAll("'", "''"); 
				
				System.out.println("Número de cancion: " + numCancion);
				System.out.println("Titulo: " + titulo);
				System.out.println("Artista: " + artista);
				System.out.println("Álbum: " + album);
				System.out.println("Año: " + anyo);
				System.out.println("Género: " + genero);
				
				FactoriaDAO.getInstancia().getCancionDAO().create(numCancion, titulo, artista, album, genero, anyo);
				
				ficherosRegulares.get(i).renameTo(new File("server/" + artista + "-" + titulo + "-" + album + 
						ficherosRegulares.get(i).getName().substring(ficherosRegulares.get(i).getName().lastIndexOf("."))));
				ficherosRegulares.get(i).renameTo(new File("D:/Documents/glassfish5/glassfish/domains/domain1/config/server/" + artista + "-" + titulo + "-" + album + 
						ficherosRegulares.get(i).getName().substring(ficherosRegulares.get(i).getName().lastIndexOf("."))));

			} catch (Exception e) {
				ficherosRegulares.get(i).delete();
			}
			

		}
		
		
		System.out.println("FIN DEL PROGRAMA!!");

	}

}