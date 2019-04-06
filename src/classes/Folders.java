package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class which manages the folders used by the application.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Folders {
	public String name;
	public Path body;
	public Path helm;
	public Path hair;
	public Path eyes;
	public Path faces;
	public Path torso;
	public Path hands;
	public Path legs;
	public Path back;
	public Path shoes;
	public Path sprites;

	/**
	 * Creates or opens the folders with the part images.
	 * @param name The name of the folder containing the other ones.
	 */
	public Folders(String name) {
		this.name = name;
		try {
			body = getPath("body");
			helm = getPath("helm");
			hair = getPath("hair");
			eyes = getPath("eyes");
			faces = getPath("faces");
			torso = getPath("torso");
			hands = getPath("hands");
			legs = getPath("legs");
			back = getPath("back");
			shoes = getPath("shoes");
			sprites = getPath("sprites");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a folder with the name given.
	 * @param folder The folder to be created.
	 * @return The path of the created folder.
	 * @throws IOException 
	 */
	private Path getPath(String folder) throws IOException {
		Path folderPath = Paths.get(name, folder);
		Files.createDirectories(folderPath);
		return folderPath;
	}
}
