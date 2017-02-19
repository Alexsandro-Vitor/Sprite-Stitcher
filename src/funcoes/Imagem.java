package funcoes;

import java.awt.image.BufferedImage;

import classes.CorARGB;

public class Imagem {
	public static final short LARGURA = 96;
	public static final short ALTURA = 128;
	
	protected static int[][] bufferParaMatriz(BufferedImage imagem, CorARGB cor) {
		int[] pixels = imagem.getRGB(0, 0, LARGURA, ALTURA, null, 0, LARGURA);	//Transformação da imagem em array de pixels
		return arrayParaMatriz(pixels, cor);
	}
	
	private static int[][] arrayParaMatriz(int[] array, CorARGB cor) {
		int[][] matriz = new int[LARGURA][ALTURA];
		for (int coluna = 0; coluna < LARGURA; coluna++) {
			for (int linha = 0; linha < ALTURA; linha++) {
				matriz[coluna][linha] = array[LARGURA * linha + coluna];
				if (CorARGB.alfa(matriz[coluna][linha]) > 0) {
					matriz[coluna][linha] = cor.alfa * 16777216 + (Integer.remainderUnsigned(matriz[coluna][linha], 16777216));
					matriz[coluna][linha] = filtrarCor(matriz[coluna][linha], cor);
				} else matriz[coluna][linha] = 0;
			}
		}
		return matriz;
	}
	
	private static int filtrarCor(int cor, CorARGB filtro) {
		CorARGB original = new CorARGB(cor);
		if (original.red == original.green && original.green == original.blue) {
			original.filtrar(filtro);
			return original.formarCor();
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
				saida[coluna][linha] = CorARGB.sobreporCor(acima[coluna][linha], abaixo[coluna][linha]);
			}
		}
		return saida;
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
