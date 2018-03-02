package funcoes;

import java.awt.image.BufferedImage;

import classes.CorARGB;
import classes.Dimensoes;

public class Imagem {
	
	/**
	 * Filtra uma cor em escala de cinza com uma cor determinada
	 * @param cor A cor que será filtrada
	 * @param filtro A cor que será usada como filtro
	 * @return A cor filtrada
	 */
	public static int filtrarCor(int cor, CorARGB filtro) {
		CorARGB original = new CorARGB(cor);
		if (original.red == original.green && original.green == original.blue) {
			original.filtrar(filtro);
			return original.formarCor();
		} else return cor;
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
		int[][] saida = new int[Dimensoes.LARGURA][Dimensoes.ALTURA];
		for (int i = 0; i < Dimensoes.LARGURA; i++) {
			for (int j = 0; j < Dimensoes.ALTURA; j++) {
				saida[i][j] = 0;
			}
		}
		return saida;
	}

	public static int[][] capaAtras(int[][] entrada) {
		//int[][] saida = new int[Dimensoes.LARGURA][Dimensoes.ALTURA];
		int alturaCapa = (Dimensoes.ALTURA * 3) >> 2;
		for (int i = 0; i < Dimensoes.LARGURA; i++) {
			for (int j = alturaCapa; j < Dimensoes.ALTURA; j++) {
				entrada[i][j] = 0;
			}
		}
		return entrada;
	}
	
	public static int[][] capaFrente(int[][] entrada) {
		//int[][] saida = new int[Dimensoes.LARGURA][Dimensoes.ALTURA];
		int alturaCapa = (Dimensoes.ALTURA * 3) >> 2;
		System.out.println("alturaCapa = "+alturaCapa);
		for (int i = 0; i < Dimensoes.LARGURA; i++) {
			for (int j = 0; j < alturaCapa; j++) {
				entrada[i][j] = 0;
			}
		}
		return entrada;
	}
}
