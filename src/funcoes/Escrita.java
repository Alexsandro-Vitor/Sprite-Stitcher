package funcoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.Folders;

public class Escrita {

	//Salva o sprite gerado e exibe uma mensagem avisando
	public static String saveSprite(Folders pasta, String nomeArquivo, BufferedImage buffer) {
		String nome = Escrita.nomeSprite(pasta, nomeArquivo);
		try {
			ImageIO.write(buffer, "PNG", new File(pasta.name +"\\sprites\\" + nome));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nome;
	}

	//Determina o name com o qual o sprite será salvo
	@SuppressWarnings("resource")
	public static String nomeSprite(Folders pasta, String nomeImagem) {
		pasta.create("sprites");
		try {
			new FileReader(pasta.name +"\\sprites\\" + nomeImagem + ".png");
			for (int i = 2; ; i++) {
				try {
					new FileReader(pasta.name +"\\sprites\\" + nomeImagem + '('+ i + ").png");
				} catch (FileNotFoundException e) {
					return nomeImagem + '(' + i + ").png";
				}
			}
		} catch (FileNotFoundException e) {
			return nomeImagem + ".png";
		}
	}
}
