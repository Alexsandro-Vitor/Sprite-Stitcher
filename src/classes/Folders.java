package classes;

import java.io.File;
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
		BODY("body"), HELM("helm"), HAIR("hair"), EYES("eyes"), FACE("face"), TORSO("torso"),
		HANDS("hands"), LEGS("legs"), BACK("back"), SHOES("shoes"), PALETTES("palettes"), SPRITES("sprites");
		public final String folder;
		PartTypes(String name) {
			this.folder = name;
		}
	}
	
	/**
	 * The name of the folder containing all template folders and their assets.
	 */
	public final String ASSETS_FOLDER_NAME = "Assets";
	private String templateName;
	public HashMap<PartTypes, Path> subFolders;

	/**
	 * Creates or opens the folders with the part images.
	 * @param templateName The name of the template folder, which contains the other ones.
	 */
	public Folders(String name) {
		this.templateName = name;
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
		Path folderPath = Paths.get(ASSETS_FOLDER_NAME, this.templateName, folder);
		Files.createDirectories(folderPath);
		return folderPath;
	}
	
	/**
	 * Gets the path of the template folder.
	 * @return The path of the template folder.
	 */
	public String getTemplateFolderPath() {
		return ASSETS_FOLDER_NAME + File.separator + this.templateName;
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
	
	public String getPalettesPath() {
		return Paths.get(this.templateName, "palettes").toString();
	}
}
