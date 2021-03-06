package functions;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import classes.PartColor;
import classes.Dimensions;
import classes.Folders;
import exceptions.WrongSizeException;
import gui.PartPanel;

/**
 * Manages the reading of the image files.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Reading {
	
	public static ArrayList<Dimensions> readTemplatesData() throws IOException {
		File templateDataFile = Folders.getTemplatesDataPath().toFile();
		ArrayList<String> lines = readFileLines(templateDataFile);
		
		ArrayList<Dimensions> templates = new ArrayList<Dimensions>();
		for (String line : lines) {
			String[] splitLine = line.split("\t");
			try {
				String templateName = splitLine[0];
				int width = Integer.parseInt(splitLine[1]);
				int height = Integer.parseInt(splitLine[2]);
				int backY = Integer.parseInt(splitLine[3]);
				int backHeight = Integer.parseInt(splitLine[4]);
				String rowOrder = splitLine[5];
				templates.add(new Dimensions(templateName, width, height, backY, backHeight, rowOrder));
			} catch (NumberFormatException e) {}
		}
		return templates;
	}

	private static ArrayList<String> readFileLines(File file) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		if (file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(fileReader);
			String line;
			while ((line = buffer.readLine()) != null) {
				lines.add(line);
			}
			buffer.close();
		}
		return lines;
	}

	/**
	 * Reads an image and modifies it.
	 * @param folder The folder of all parts.
	 * @param part The type of part that was chosen.
	 * @return The image matrix of the chosen image, with the color modifications.
	 * @throws WrongSizeException If the image size is not the one specified at Dimensions.
	 */
	public static int[][] selectImage(Folders folder, PartPanel part) throws WrongSizeException {
		int[][] matrix;
		try {
			if (part.cmb.getSelectedIndex() > 0) {
				String selected = part.cmb.getSelectedItem().toString();
				matrix = readImage(Paths.get(folder.getTemplateFolderPath(), part.name.split(" ")[0], selected).toFile());
				if (part.isUsingPalettes()) {
					String originalPaletteName = part.cmbOriginal.getSelectedItem().toString();
					String newPaletteName = part.cmbNew.getSelectedItem().toString();
					int[] originalPalette = readPalette(Paths.get(folder.getPalettesPath(), originalPaletteName).toFile());
					int[] newPalette = readPalette(Paths.get(folder.getPalettesPath(), newPaletteName).toFile());
					colorImagePaletteSwap(matrix, part.color.getAlpha(), originalPalette, newPalette);
				} else {
					colorImageHueSwap(matrix, (int)part.spinHueSwap.getValue());
					colorImageFilter(matrix, part.color);
				}
			} else {
				matrix = ImageFunctions.getTransparency();
			}
		} catch (IOException e) {
			e.printStackTrace();
			matrix = ImageFunctions.getTransparency();
		}
		return matrix;
	}
	
	public static Set<Integer> detectPalette(Folders folder, PartPanel part) {
		try {
			if (part.cmb.getSelectedIndex() > 0) {
				String selected = part.cmb.getSelectedItem().toString();
				int[][] matrix = readImage(Paths.get(folder.getTemplateFolderPath(), part.name.split(" ")[0], selected).toFile());
				return getImagePalette(matrix);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WrongSizeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Set<Integer> getImagePalette(int[][] matrix) {
		Set<Integer> palette = new HashSet<Integer>();
		for (int[] line : matrix)
			for (int color : line)
				if ((color >>> 24) > 0)
					palette.add(color & 0x00ffffff);
		return palette;
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

	public static int[] readPalette(File file) throws IOException {
		List<String> lines = readFileLines(file);
		
		int[] output = new int[lines.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = Integer.parseInt(lines.get(i).substring(1), 16);
		}
		return output;
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
	
	/**
	 * Colors an image by changing all colors of a palette to colors of another one.
	 * @param matrix The image matrix.
	 * @param originalPalette The palette of the original colors.
	 * @param newPalette The palette of the new colors.
	 */
	private static void colorImagePaletteSwap(int[][] matrix, int alpha, int[] originalPalette, int[] newPalette) {
		Map<Integer, Integer> swapper = ImageFunctions.generatePaletteSwapper(originalPalette, newPalette);
		
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			for (int row = 0; row < Dimensions.HEIGHT; row++) {
				if ((matrix[column][row] & 0xFF000000) != 0) {
					int originalColor = matrix[column][row] & 0xFFFFFF;
					matrix[column][row] = (alpha << 24)
							+ (swapper.get(originalColor) != null ? swapper.get(originalColor) : originalColor);
				}
			}
		}
	}
	
	public static String getLicenses(Folders folder, PartPanel part) throws IOException {
		Object selectedObj = part.cmb.getSelectedItem();
		if (selectedObj == null) return null;
		
		String templateRoot = folder.getTemplateFolderPath();
		String partName = part.name.split(" ")[0];
		String selectedStr = ((String)selectedObj).split("\\.")[0] + ".license";
		
		Path licensePath = Paths.get(templateRoot, partName, selectedStr);
		
		if (Files.exists(licensePath)) {
			List<String> licenseContent = Files.readAllLines(licensePath);
			return String.join("\n", licenseContent);
		} else return "this file has no license";
	}
}
