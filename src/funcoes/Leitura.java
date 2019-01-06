package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.CorARGB;
import classes.Dimensoes;
import classes.ParteSprite;
import excecoes.TamanhoErradoException;

public class Leitura {
	
	//Pega o nome de cada arquivo de uma pasta e coloca em um array
	public static String[] nomesArquivos(File[] array) {
		String[] saida = new String[array.length + 1];
		saida[0] = "[Vazio]";
		for (int i = 1; i < saida.length; i++) {
			saida[i] = array[i-1].getName();
		}
		return saida;
	}
	
	//Pega a imagem selecionada pelo comboBox
	public static int[][] selecionarImagem(File[] array, ParteSprite parte) throws TamanhoErradoException {
		int[][] matriz;
		try {
			matriz = lerImagem(array[parte.cmb.getSelectedIndex() - 1], new CorARGB(parte));
		} catch (ArrayIndexOutOfBoundsException e) {
			matriz = Imagem.gerarTransparencia();
		} catch (IOException e) {
			e.printStackTrace();
			matriz = Imagem.gerarTransparencia();
		}
		return matriz;
	}

	public static int[][] lerImagem(File arquivo, CorARGB cor) throws IOException, TamanhoErradoException {
		BufferedImage buffer = ImageIO.read(arquivo);	//Le o arquivo
		if (buffer.getWidth() != Dimensoes.LARGURA || buffer.getHeight() != Dimensoes.ALTURA)
			throw new TamanhoErradoException(arquivo.getName(), Dimensoes.LARGURA, Dimensoes.ALTURA);
		return bufferParaMatriz(buffer, cor);
	}

	private static int[][] bufferParaMatriz(BufferedImage imagem, CorARGB cor) {
		int[] pixels = imagem.getRGB(0, 0, Dimensoes.LARGURA, Dimensoes.ALTURA, null, 0, Dimensoes.LARGURA);	//Transformação da imagem em array de pixels
		return arrayParaMatriz(pixels, cor);
	}

	private static int[][] arrayParaMatriz(int[] array, CorARGB cor) {
		int[][] matriz = new int[Dimensoes.LARGURA][Dimensoes.ALTURA];
		for (int coluna = 0; coluna < Dimensoes.LARGURA; coluna++) {
			for (int linha = 0; linha < Dimensoes.ALTURA; linha++) {
				matriz[coluna][linha] = array[Dimensoes.LARGURA * linha + coluna];
				if (CorARGB.alfa(matriz[coluna][linha]) > 0) {
					matriz[coluna][linha] = cor.alfa * 16777216 + (Integer.remainderUnsigned(matriz[coluna][linha], 16777216));
					matriz[coluna][linha] = Imagem.filtrarCor(matriz[coluna][linha], cor);
				} else matriz[coluna][linha] = 0;
			}
		}
		return matriz;
	}
}
