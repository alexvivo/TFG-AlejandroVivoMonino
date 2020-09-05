package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

public class ObtenerImagenesAlbum {

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

				String artista = tag.getFirst(FieldKey.ARTIST).replaceAll("'", "''");
				String album = tag.getFirst(FieldKey.ALBUM).replaceAll("'", "''");		
				Artwork artwork = tag.getFirstArtwork();
				String extension = artwork.getMimeType().split("/")[1];
				java.awt.image.BufferedImage imagen = (BufferedImage) artwork.getImage();
				
				System.out.println("Artista: " + artista);
				System.out.println("Álbum: " + album);
				
				ImageIO.write(imagen, extension, new File("./WebContent/img/" + artista + "-" + album  + ".jpg"));

			} catch (Exception e) {

			}
			

		}
		
		
		System.out.println("FIN DEL PROGRAMA!!");

	}

}
