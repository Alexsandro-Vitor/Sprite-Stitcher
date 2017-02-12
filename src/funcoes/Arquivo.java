package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Arquivo {
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
	public static int[][] selecionarImagem(File[] array, JComboBox<String> cmb, int alfa) throws IOException {
		int[][] matriz;
		try {
			matriz = Imagem.lerImagem(array[cmb.getSelectedIndex() - 1], alfa);
		} catch (ArrayIndexOutOfBoundsException e) {
			matriz = Imagem.gerarTransparencia();
		}
		return matriz;
	}

	//Salva o sprite gerado e exibe uma mensagem avisando
	public static String salvarSprite(String nomeArquivo, BufferedImage buffer) throws IOException {
		String nome = Arquivo.nomeSprite(nomeArquivo);
		ImageIO.write(buffer, "PNG", new File("imagens\\sprites\\" + nome));
		return nome;
	}

	//Determina o nome com o qual o sprite será salvo
	@SuppressWarnings("resource")
	public static String nomeSprite(String nomeImagem) {
		Pastas.criar("sprites");
		try {
			new FileReader("imagens\\sprites\\" + nomeImagem + ".png");
			for (int i = 2; ; i++) {
				try {
					new FileReader("imagens\\sprites\\" + nomeImagem + '('+ i + ").png");
				} catch (FileNotFoundException e) {
					return nomeImagem + '(' + i + ").png";
				}
			}
		} catch (FileNotFoundException e) {
			return nomeImagem + ".png";
		}
	}
}
