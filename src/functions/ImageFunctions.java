package functions;

import java.awt.image.BufferedImage;

import classes.PartColor;
import classes.Dimensions;

/**
 * Functions for image manipulation.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class ImageFunctions {
	private static int[][] TRANSPARENCY;
	
	/**
	 * Filters a grayscale color with another one.
	 * @param original The color to be filtered.
	 * @param filter The color which will be used as filter.
	 * @return The filtered color.
	 */
	public static int filterColor(PartColor original, PartColor filter) {
		if (original.isGrayscale()) {
			return filter.filter(original).hashCode();
		} else return original.hashCode();
	}
	
	/**
	 * Changes the hue of a color.
	 * @param cor The color to be changed.
	 * @param swap A value to be added to the hue, in degrees.
	 * @return The color with the new hue.
	 */
	public static int hueSwap(PartColor original, float swap) {
		float[] hsb = PartColor.RGBtoHSB(original.getRed(), original.getGreen(), original.getBlue(), null);
		return (original.getAlpha() << 24) + PartColor.HSBtoRGB(hsb[0] + swap / 360f, hsb[1], hsb[2]);
	}
	
	/**
	 * Converts a matrix in an image buffer.
	 * @param matrix The image matrix.
	 * @return The image buffer.
	 */
	public static BufferedImage matrixToBuffer(int[][] matrix) {
		BufferedImage image = new BufferedImage(Dimensions.WIDTH, Dimensions.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0, 0, Dimensions.WIDTH, Dimensions.HEIGHT, matrixToArray(matrix), 0, Dimensions.WIDTH);
		return image;
	}
	
	private static int[] matrixToArray(int[][] matrix) {
		int[] array = new int[Dimensions.WIDTH * Dimensions.HEIGHT];
		for (int col = 0; col < Dimensions.WIDTH; col++) {
			for (int lin = 0; lin < Dimensions.HEIGHT; lin++) {
				array[Dimensions.WIDTH * lin + col] = matrix[col][lin];
			}
		}
		return array;
	}
	
	/**
	 * Overlaps two images.
	 * @param over The image overlapping.
	 * @param under The image being overlapped.
	 * @return The resulting image after the overlap.
	 */
	public static int[][] overlapImage(int[][] over, int[][] under) {
		int[][] output = new int[Dimensions.WIDTH][Dimensions.HEIGHT];
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			for (int line = 0; line < Dimensions.HEIGHT; line++) {
				output[column][line] = PartColor.overlapColor(over[column][line], under[column][line]);
			}
		}
		return output;
	}
	
	/**
	 * Gets a transparent image the size of the spritesheet.
	 * @return The transparent image, as a matrix.
	 */
	public static int[][] getTransparency() {
		if (TRANSPARENCY == null) TRANSPARENCY = new int[Dimensions.WIDTH][Dimensions.HEIGHT];
		return TRANSPARENCY;
	}

	/**
	 * Filters a image to get only the parts of an cape where it is under the rest of the sprite.
	 * @param input The cape image, before the filter.
	 * @return The filtered image.
	 */
	public static int[][] capeBack(int[][] input) {
		for (int i = 0; i < Dimensions.WIDTH; i++) {
			for (int j = Dimensions.BACK_HEIGHT; j < Dimensions.HEIGHT; j++) {
				input[i][j] = 0;
			}
		}
		return input;
	}
	
	/**
	 * Filters a image to get only the parts of an cape where it is over the rest of the sprite.
	 * @param input The cape image, before the filter.
	 * @return The filtered image.
	 */
	public static int[][] capeFront(int[][] input) {
		for (int i = 0; i < Dimensions.WIDTH; i++) {
			for (int j = 0; j < Dimensions.BACK_HEIGHT; j++) {
				input[i][j] = 0;
			}
		}
		return input;
	}
}
