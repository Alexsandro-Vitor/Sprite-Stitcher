package functions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import classes.PartColor;
import classes.Dimensions;
import classes.Folders;
import classes.SpritePart;
import exceptions.WrongSizeException;

/**
 * Manages the reading of the image files.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Reading {
	
	/**
	 * Reads an image and modifies it.
	 * @param folder The folder of all parts.
	 * @param part The type of part that was chosen.
	 * @return The image matrix of the chosen image, with the color modifications.
	 * @throws WrongSizeException If the image size is not the one specified at Dimensions.
	 */
	public static int[][] selectImage(Folders folder, SpritePart part) throws WrongSizeException {
		int[][] matrix;
		try {
			if (part.getCmb().getItemCount() > 0) {
				String selected = part.getCmb().getSelectedItem().toString();
				matrix = readImage(Paths.get(folder.name, part.name.split(" ")[0], selected).toFile());
				colorImageHueSwap(matrix, part.getHueSwap());
				colorImageFilter(matrix, part.getColor());
			} else {
				matrix = ImageFunctions.getTransparency();
			}
		} catch (IOException e) {
			e.printStackTrace();
			matrix = ImageFunctions.getTransparency();
		}
		return matrix;
	}

	/**
	 * Reads an image file.
	 * @param file The image file to be read.
	 * @return The matrix of the file image.
	 * @throws IOException
	 * @throws WrongSizeException If the image size is not the one specified at Dimensions.
	 */
	public static int[][] readImage(File file) throws IOException, WrongSizeException {
		BufferedImage buffer = ImageIO.read(file);
		
		if (buffer.getWidth() != Dimensions.WIDTH || buffer.getHeight() != Dimensions.HEIGHT)
			throw new WrongSizeException(file.getName(), Dimensions.WIDTH, Dimensions.HEIGHT);
		return bufferToMatrix(buffer);
	}
	
	/**
	 * Converts a image buffer to a matrix.
	 * @param image The image buffer.
	 * @return A matrix which represents the image.
	 */
	private static int[][] bufferToMatrix(BufferedImage image) {
		int[][] matrix = new int[Dimensions.WIDTH][Dimensions.HEIGHT];
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			image.getRGB(column, 0, 1, Dimensions.HEIGHT, matrix[column], 0, 1);	
		}
		return matrix;
	}
	
	/**
	 * Colors image by filtering grayscale parts with a color.
	 * @param matrix The image matrix.
	 * @param color The filter color.
	 */
	private static void colorImageFilter(int[][] matrix, PartColor color) {
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			for (int row = 0; row < Dimensions.HEIGHT; row++) {
				if ((matrix[column][row] & 0xFF000000) != 0) {
					PartColor original = new PartColor((color.getAlpha() << 24) + (matrix[column][row] & 0xFFFFFF));
					matrix[column][row] = ImageFunctions.filterColor(original, color);
				}
			}
		}
	}
	
	/**
	 * Colors image by rotating the hue of its pixels.
	 * @param matrix The image matrix.
	 * @param swap The angle of the rotation.
	 */
	private static void colorImageHueSwap(int[][] matrix, float swap) {
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			for (int row = 0; row < Dimensions.HEIGHT; row++) {
				if ((matrix[column][row] & 0xFF000000) != 0) {
					PartColor original = new PartColor(matrix[column][row]);
					matrix[column][row] = ImageFunctions.hueSwap(original, swap);
				}
			}
		}
	}
}
