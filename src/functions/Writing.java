package functions;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import classes.Folders;
import classes.Folders.PartTypes;

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
	 * @throws IOException 
	 */
	public static String saveSprite(Folders folder, String filename, BufferedImage buffer) throws IOException {
		String name = Writing.generateSpriteName(folder.subFolders.get(PartTypes.SPRITES), filename);
		ImageIO.write(buffer, "PNG", folder.subFolders.get(PartTypes.SPRITES).resolve(name).toFile());
		return name;
	}

	/**
	 * Generates a name for the generated file.
	 * @param folder The folder where the file will be.
	 * @param chosenName The name chosen by the user.
	 * @return The name of the file with its termination and a number if there are other files with the same name.
	 */
	private static String generateSpriteName(Path folder, String chosenName) {
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
	
	public static void saveLicense(Folders folder, String spriteFileName, String licenseText) throws IOException {
		String licenseFileName = spriteFileName.split("\\.")[0] + ".license";
		Path licensePath = folder.subFolders.get(PartTypes.SPRITES).resolve(licenseFileName);
		Files.write(licensePath, licenseText.getBytes());
	}
}
