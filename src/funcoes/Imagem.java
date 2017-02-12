package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {
	public static final short LARGURA = 96;
	public static final short ALTURA = 128;
	
	public static int[][] lerImagem(File arquivo, int alfa) throws IOException {
		BufferedImage imagem = ImageIO.read(arquivo);							//Le o arquivo
		return bufferParaMatriz(imagem, alfa);
	}
	
	private static int[][] bufferParaMatriz(BufferedImage imagem, int alfa) {
		int largura = imagem.getWidth(), altura = imagem.getHeight();			//Dimensoes da imagem
		int[] pixels = imagem.getRGB(0, 0, largura, altura, null, 0, largura);	//Transformação da imagem em array de pixels
		return arrayParaMatriz(pixels, largura, altura, alfa);
	}
	
	private static int[][] arrayParaMatriz(int[] array, int largura, int altura, int alfa) {
		int[][] matriz = new int[largura][altura];
		for (int coluna = 0; coluna < largura; coluna++) {
			for (int linha = 0; linha < altura; linha++) {
				matriz[coluna][linha] = array[largura * linha + coluna];
				if (alfa(matriz[coluna][linha]) > 0) 
					matriz[coluna][linha] = alfa * 16777216 + (Integer.remainderUnsigned(matriz[coluna][linha], 16777216));
				else matriz[coluna][linha] = 0;
			}
		}
		return matriz;
	}
	
	public static BufferedImage matrizParaBuffer(int[][] matriz) {
		int largura = matriz.length, altura = matriz[0].length;
		//TYPE_INT_ARGB: Gera a imagem com um alpha (opacidade) e cores RGB
		BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
		imagem.setRGB(0, 0, largura, altura, matrizParaArray(matriz), 0, largura);
		return imagem;
	}
	
	private static int[] matrizParaArray(int[][] matriz) {
		int largura = matriz.length, altura = matriz[0].length;
		int[] array = new int[largura * altura];
		for (int col = 0; col < largura; col++) {
			for (int lin = 0; lin < altura; lin++) {
				array[largura * lin + col] = matriz[col][lin];
			}
		}
		return array;
	}
	
	public static int[][] sobreporImagem(int[][] acima, int[][] abaixo) {
		int largura = acima.length, altura = acima[0].length;
		int[][] saida = new int[largura][altura];
		for (int coluna = 0; coluna < largura; coluna++) {
			for (int linha = 0; linha < altura; linha++) {
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
		int[][] saida = new int[96][128];
		for (int i = 0; i < saida.length; i++) {
			for (int j = 0; j < saida[0].length; j++) {
				saida[i][j] = 0;
			}
		}
		return saida;
	}
	
	public static int[][] capaAtras(int[][] entrada) {
		int[][] saida = new int[entrada.length][entrada[0].length];
		for (int i = 0; i < entrada.length; i++) {
			for (int j = 0; j < entrada[0].length; j++) {
				saida[i][j] = (j < 96) ? entrada[i][j] : 0;
			}
		}
		return saida;
	}
	
	public static int[][] capaFrente(int[][] entrada) {
		int[][] saida = new int[entrada.length][entrada[0].length];
		for (int i = 0; i < entrada.length; i++) {
			for (int j = 0; j < entrada[0].length; j++) {
				saida[i][j] = (j >= 96) ? entrada[i][j] : 0;
			}
		}
		return saida;
	}
}
