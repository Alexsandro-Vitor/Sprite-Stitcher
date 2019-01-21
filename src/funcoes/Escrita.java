package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.Pastas;

public class Escrita {

	//Salva o sprite gerado e exibe uma mensagem avisando
	public static String salvarSprite(Pastas pasta, String nomeArquivo, BufferedImage buffer) {
		String nome = Escrita.nomeSprite(pasta, nomeArquivo);
		try {
			ImageIO.write(buffer, "PNG", new File(pasta.nome +"\\sprites\\" + nome));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nome;
	}

	//Determina o name com o qual o sprite será salvo
	@SuppressWarnings("resource")
	public static String nomeSprite(Pastas pasta, String nomeImagem) {
		pasta.criar("sprites");
		try {
			new FileReader(pasta.nome +"\\sprites\\" + nomeImagem + ".png");
			for (int i = 2; ; i++) {
				try {
					new FileReader(pasta.nome +"\\sprites\\" + nomeImagem + '('+ i + ").png");
				} catch (FileNotFoundException e) {
					return nomeImagem + '(' + i + ").png";
				}
			}
		} catch (FileNotFoundException e) {
			return nomeImagem + ".png";
		}
	}
}
