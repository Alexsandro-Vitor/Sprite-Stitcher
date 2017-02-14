package funcoes;

import java.awt.image.BufferedImage;

public class Imagem {
	public static final short LARGURA = 96;
	public static final short ALTURA = 128;
	
	protected static int[][] bufferParaMatriz(BufferedImage imagem, int red, int green, int blue, int alfa) {
		int[] pixels = imagem.getRGB(0, 0, LARGURA, ALTURA, null, 0, LARGURA);	//Transformação da imagem em array de pixels
		return arrayParaMatriz(pixels, red, green, blue, alfa);
	}
	
	private static int[][] arrayParaMatriz(int[] array, int red, int green, int blue, int alfa) {
		int[][] matriz = new int[LARGURA][ALTURA];
		for (int coluna = 0; coluna < LARGURA; coluna++) {
			for (int linha = 0; linha < ALTURA; linha++) {
				matriz[coluna][linha] = array[LARGURA * linha + coluna];
				if (alfa(matriz[coluna][linha]) > 0) {
					matriz[coluna][linha] = alfa * 16777216 + (Integer.remainderUnsigned(matriz[coluna][linha], 16777216));
					matriz[coluna][linha] = filtrarCor(matriz[coluna][linha], red, green, blue);
				} else matriz[coluna][linha] = 0;
			}
		}
		return matriz;
	}
	
	private static int filtrarCor(int cor, int red, int green, int blue) {
		if (red(cor) == green(cor) && red(cor) == blue(cor)) {
			red = Integer.divideUnsigned(red * red(cor), 255);
			green = Integer.divideUnsigned(green * green(cor), 255);
			blue = Integer.divideUnsigned(blue * blue(cor), 255);
			return ((alfa(cor) * 256 + red) * 256 + green) * 256 + blue;
		} else return cor;
	}
	
	public static BufferedImage matrizParaBuffer(int[][] matriz) {
		//TYPE_INT_ARGB: Gera a imagem com um alpha (opacidade) e cores RGB
		BufferedImage imagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
		imagem.setRGB(0, 0, LARGURA, ALTURA, matrizParaArray(matriz), 0, LARGURA);
		return imagem;
	}
	
	private static int[] matrizParaArray(int[][] matriz) {
		int[] array = new int[LARGURA * ALTURA];
		for (int col = 0; col < LARGURA; col++) {
			for (int lin = 0; lin < ALTURA; lin++) {
				array[LARGURA * lin + col] = matriz[col][lin];
			}
		}
		return array;
	}
	
	public static int[][] sobreporImagem(int[][] acima, int[][] abaixo) {
		int[][] saida = new int[LARGURA][ALTURA];
		for (int coluna = 0; coluna < LARGURA; coluna++) {
			for (int linha = 0; linha < ALTURA; linha++) {
				saida[coluna][linha] = sobreporCor(acima[coluna][linha], abaixo[coluna][linha]);
			}
		}
		return saida;
	}
	
	private static int sobreporCor(int acima, int abaixo) {
		int alfa = 255 - Integer.divideUnsigned((255 - alfa(acima)) * (255 - alfa(abaixo)), 255);
		int red = media(alfa(acima), red(acima), red(abaixo));
		int green = media(alfa(acima), green(acima), green(abaixo));
		int blue = media(alfa(acima), blue(acima), blue(abaixo));
		return ((alfa * 256 + red) * 256 + green) * 256 + blue;
	}
	
	private static int media(int pesoA, int valorA, int valorB) {
		return Integer.divideUnsigned((pesoA * valorA + (255 - pesoA) * valorB), 255);
	}
	
	private static int alfa(int argb) {
		return Integer.divideUnsigned(argb, 16777216);
	}
	
	private static int red(int argb) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(argb, 65536), 256);
	}
	
	private static int green(int argb) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(argb, 256), 256);
	}
	
	private static int blue(int argb) {
		return Integer.remainderUnsigned(argb, 256);
	}
	
	public static int[][] gerarTransparencia() {
		int[][] saida = new int[LARGURA][ALTURA];
		for (int i = 0; i < LARGURA; i++) {
			for (int j = 0; j < ALTURA; j++) {
				saida[i][j] = 0;
			}
		}
		return saida;
	}

	public static int[][] capaAtras(int[][] entrada) {
		int[][] saida = new int[LARGURA][ALTURA];
		for (int i = 0; i < LARGURA; i++) {
			for (int j = 0; j < ALTURA; j++) {
				saida[i][j] = (j < 96) ? entrada[i][j] : 0;
			}
		}
		return saida;
	}
	
	public static int[][] capaFrente(int[][] entrada) {
		int[][] saida = new int[LARGURA][ALTURA];
		for (int i = 0; i < LARGURA; i++) {
			for (int j = 0; j < ALTURA; j++) {
				saida[i][j] = (j >= 96) ? entrada[i][j] : 0;
			}
		}
		return saida;
	}
}
