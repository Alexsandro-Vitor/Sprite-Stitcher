package funcoes;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

import classes.CorARGB;
import classes.Dimensoes;

public class Imagem {
	private static int[][] TRANSPARENCIA;
	
	/**
	 * Filtra uma cor em escala de cinza com uma cor determinada
	 * @param cor A cor que ser� filtrada
	 * @param filtro A cor que ser� usada como filtro
	 * @return A cor filtrada
	 */
	public static int filtrarCor(CorARGB original, CorARGB filtro) {
		if (original.isGrayscale()) {
			return filtro.filtrar(original).hashCode();
		} else return original.hashCode();
	}
	
	/**
	 * Changes the hue of a color.
	 * @param cor The color to be changed.
	 * @param swap A value to be added to the hue.
	 * @return The color with the new hue.
	 */
	public static int hueSwap(CorARGB original, float swap) {
		float[] hsb = CorARGB.RGBtoHSB(original.getRed(), original.getGreen(), original.getBlue(), null);
		return (original.getAlpha() << 24) + CorARGB.HSBtoRGB(hsb[0] + swap, hsb[1], hsb[2]);
	}
	
	/**
	 * Converte uma matriz em um buffer de imagem
	 * @param matriz A matriz da imagem
	 * @return O buffer gerado
	 */
	public static BufferedImage matrizParaBuffer(int[][] matriz) {
		//TYPE_INT_ARGB: Gera a imagem com um alpha (opacidade) e cores RGB
		BufferedImage imagem = new BufferedImage(Dimensoes.LARGURA, Dimensoes.ALTURA, BufferedImage.TYPE_INT_ARGB);
		imagem.setRGB(0, 0, Dimensoes.LARGURA, Dimensoes.ALTURA, matrizParaArray(matriz), 0, Dimensoes.LARGURA);
		return imagem;
	}
	
	private static int[] matrizParaArray(int[][] matriz) {
		int[] array = new int[Dimensoes.LARGURA * Dimensoes.ALTURA];
		for (int col = 0; col < Dimensoes.LARGURA; col++) {
			for (int lin = 0; lin < Dimensoes.ALTURA; lin++) {
				array[Dimensoes.LARGURA * lin + col] = matriz[col][lin];
			}
		}
		return array;
	}
	
	public static int[][] sobreporImagem(int[][] acima, int[][] abaixo) {
		int[][] saida = new int[Dimensoes.LARGURA][Dimensoes.ALTURA];
		for (int coluna = 0; coluna < Dimensoes.LARGURA; coluna++) {
			for (int linha = 0; linha < Dimensoes.ALTURA; linha++) {
				saida[coluna][linha] = CorARGB.sobreporCor(acima[coluna][linha], abaixo[coluna][linha]);
			}
		}
		return saida;
	}
	
	public static int[][] gerarTransparencia() {
		if (TRANSPARENCIA == null) TRANSPARENCIA = new int[Dimensoes.LARGURA][Dimensoes.ALTURA];
		return TRANSPARENCIA;
	}

	public static int[][] capaAtras(int[][] entrada) {
		for (int i = 0; i < Dimensoes.LARGURA; i++) {
			for (int j = Dimensoes.ALTURA_CAPA; j < Dimensoes.ALTURA; j++) {
				entrada[i][j] = 0;
			}
		}
		return entrada;
	}
	
	public static int[][] capaFrente(int[][] entrada) {
		for (int i = 0; i < Dimensoes.LARGURA; i++) {
			for (int j = 0; j < Dimensoes.ALTURA_CAPA; j++) {
				entrada[i][j] = 0;
			}
		}
		return entrada;
	}
}
