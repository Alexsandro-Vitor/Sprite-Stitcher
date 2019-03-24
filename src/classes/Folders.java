package classes;

import java.io.File;

/**
 * Class which manages the folders used by the application.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Folders {
	public String name;
	public File[] body;
	public File[] helm;
	public File[] hair;
	public File[] eyes;
	public File[] faces;
	public File[] torso;
	public File[] hands;
	public File[] legs;
	public File[] back;
	public File[] shoes;

	/**
	 * Creates or opens the folders with the part images.
	 * @param name The name of the folder containing the other ones.
	 */
	public Folders(String name) {
		this.name = name;
		body = open("body");
		helm = open("helm");
		hair = open("hair");
		eyes = open("eyes");
		faces = open("faces");
		torso = open("torso");
		hands = open("hands");
		legs = open("legs");
		back = open("back");
		shoes = open("shoes");
	}

	/**
	 * Gets all the names of the files in a folder.
	 * @param folder The folder to be read.
	 * @return An array with the names of all files in a folder.
	 */
	private File[] open(String folder) {
		create(folder);
		return new File(name + '\\' + folder).listFiles();
	}

	/**
	 * Creates a new folder if it doesn't exist.
	 * @param folder The path of the new folder.
	 */
	public void create(String folder) {
		if (new File(name + '\\' + folder).listFiles() == null) new File(name + '\\' + folder).mkdirs();	
	}
}
