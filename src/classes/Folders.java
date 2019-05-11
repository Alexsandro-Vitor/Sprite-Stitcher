package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Class which manages the folders used by the application.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Folders {
	public enum PartTypes {
		BODY("body"), HELM("helm"), HAIR("hair"), EYES("eyes"), FACES("faces"), TORSO("torso"),
		HANDS("hands"), LEGS("legs"), BACK("back"), SHOES("shoes"), SPRITES("sprites");
		public final String folder;
		PartTypes(String name) {
			this.folder = name;
		}
	}
	public String name;
	public HashMap<PartTypes, Path> subFolders;

	/**
	 * Creates or opens the folders with the part images.
	 * @param name The name of the folder containing the other ones.
	 */
	public Folders(String name) {
		this.name = name;
		try {
			this.subFolders = new HashMap<PartTypes, Path>();
			for (PartTypes type : PartTypes.values()) {
				this.subFolders.put(type, getPath(type.folder));
			}
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
	
	/**
	 * Lists the files in one of its subfolders, leaving null in the first index. 
	 * @param part The sprite part type of the folder.
	 * @return The list of files in the subfolder, preceded by a null value.
	 */
	public String[] files(PartTypes part) {
		String[] files = this.subFolders.get(part).toFile().list();
		String[] output = new String[files.length + 1];
		System.arraycopy(files, 0, output, 1, files.length);
		return output;
	}
}
