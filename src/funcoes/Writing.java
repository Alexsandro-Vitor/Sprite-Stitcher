package funcoes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import classes.Folders;

/**
 * Manages the writing of the image files.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Writing {
	/**
	 * Saves the generated sprite.
	 * @param folder The folder of the sprite parts.
	 * @param filename The name chosen by the user.
	 * @param buffer The buffer of the sprite.
	 * @return The name of the generated sprite file.
	 */
	public static String saveSprite(Folders folder, String filename, BufferedImage buffer) {
		folder.create("sprites");
		String name = Writing.generateSpriteName(Paths.get(folder.name, "sprites"), filename);
		try {
			ImageIO.write(buffer, "PNG", Paths.get(folder.name, "sprites", name).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * Generates a name for the generated file.
	 * @param folder The folder where the file will be.
	 * @param chosenName The name chosen by the user.
	 * @return The name of the file with its termination and a number if there are other files with the same name.
	 */
	public static String generateSpriteName(Path folder, String chosenName) {
		String name = chosenName + ".png";
		if (Files.exists(folder.resolve(name))) {
			for (int i = 2; ; i++) {
				name = chosenName + " (" + i + ").png";
				if (!Files.exists(folder.resolve(name))) {
					return name;
				}
			}
		} else {
			return name;
		}
	}
}
