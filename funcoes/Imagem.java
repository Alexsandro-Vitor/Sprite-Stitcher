package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagem {
	public static int[][] lerImagem(File arquivo) throws IOException {
		BufferedImage imagem = ImageIO.read(arquivo);							//Le o arquivo
		int largura = imagem.getWidth(), altura = imagem.getHeight();			//Dimensoes da imagem
		int[] pixels = imagem.getRGB(0, 0, largura, altura, null, 0, largura);	//Transformação da imagem em array de pixels
		return arrayParaMatriz(pixels, largura, altura);
	}
	
	public static int[][] bufferParaMatriz(BufferedImage imagem) {
		int largura = imagem.getWidth(), altura = imagem.getHeight();			//Dimensoes da imagem
		int[] pixels = imagem.getRGB(0, 0, largura, altura, null, 0, largura);	//Transformação da imagem em array de pixels
		return arrayParaMatriz(pixels, largura, altura);
	}
	
	private static int[][] arrayParaMatriz(int[] array, int largura, int altura) {
		int[][] matriz = new int[largura][altura];
		for (int coluna = 0; coluna < largura; coluna++) {
			for (int linha = 0; linha < altura; linha++) {
				matriz[coluna][linha] = array[largura * linha + coluna];
			}
		}
		return matriz;
	}
	
	public static void produzirPng(int[][] pixels, File arquivo) throws IOException {
		BufferedImage imagem = matrizParaBuffer(pixels);
		ImageIO.write(imagem, "PNG", arquivo);
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
				saida[coluna][linha] = transparente(acima[coluna][linha]) ? abaixo[coluna][linha] : acima[coluna][linha];
			}
		}
		return saida;
	}
	
	private static boolean transparente(int argb) {
		//Checa se argb possui alguma opacidade (se os 8 bits mais significativos de argb sao diferentes de 0)
		return Integer.divideUnsigned(argb, 16777216) == 0;
	}
	
	public static int[][] gerarTransparencia() {
		int[][] saida = new int[96][128];
		for (int i = 0; i < saida.length; i++) {
			for (int j = 0; j < saida[0].length; j++) {
				saida[i][j] = 16777215;
			}
		}
		return saida;
	}
}
