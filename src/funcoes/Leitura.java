package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.PartColor;
import classes.Dimensions;
import classes.SpritePart;
import exceptions.WrongSizeException;

public class Leitura {
	
	//Pega o name de cada arquivo de uma pasta e coloca em um array
	public static String[] nomesArquivos(File[] array) {
		String[] saida = new String[array.length + 1];
		if (array.length > 0) saida[0] = "[None]";
		else saida[0] = "[Empty]";
		for (int i = 1; i < saida.length; i++) {
			saida[i] = array[i-1].getName();
		}
		return saida;
	}
	
	//Pega a imagem selecionada pelo comboBox
	public static int[][] selecionarImagem(File[] array, SpritePart parte) throws WrongSizeException {
		int[][] matriz;
		try {
			matriz = lerImagem(array[parte.getCmb().getSelectedIndex() - 1]);
			colorImageHueSwap(matriz, parte.getHueSwap());
			colorImageFilter(matriz, parte.getColor());
		} catch (ArrayIndexOutOfBoundsException e) {
			matriz = ImageFunctions.getTransparency();
		} catch (IOException e) {
			e.printStackTrace();
			matriz = ImageFunctions.getTransparency();
		}
		return matriz;
	}

	public static int[][] lerImagem(File arquivo) throws IOException, WrongSizeException {
		BufferedImage buffer = ImageIO.read(arquivo);	//Le o arquivo
		
		if (buffer.getWidth() != Dimensions.WIDTH || buffer.getHeight() != Dimensions.HEIGHT)
			throw new WrongSizeException(arquivo.getName(), Dimensions.WIDTH, Dimensions.HEIGHT);
		return bufferToMatrix(buffer);
	}
	
	private static int[][] bufferToMatrix(BufferedImage image) {
		int[][] matrix = new int[Dimensions.WIDTH][Dimensions.HEIGHT];
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			image.getRGB(column, 0, 1, Dimensions.HEIGHT, matrix[column], 0, 1);	
		}
		return matrix;
	}
	
	private static void colorImageFilter(int[][] matriz, PartColor cor) {
		for (int coluna = 0; coluna < Dimensions.WIDTH; coluna++) {
			for (int linha = 0; linha < Dimensions.HEIGHT; linha++) {
				if ((matriz[coluna][linha] & 0xFF000000) != 0) {
					PartColor original = new PartColor((cor.getAlpha() << 24) + (matriz[coluna][linha] & 0xFFFFFF));
					matriz[coluna][linha] = ImageFunctions.filterColor(original, cor);
				}
			}
		}
	}
	
	private static void colorImageHueSwap(int[][] matrix, float swap) {
		for (int column = 0; column < Dimensions.WIDTH; column++) {
			for (int line = 0; line < Dimensions.HEIGHT; line++) {
				if ((matrix[column][line] & 0xFF000000) != 0) {
					PartColor original = new PartColor(matrix[column][line]);
					matrix[column][line] = ImageFunctions.hueSwap(original, swap);
				}
			}
		}
	}
}
