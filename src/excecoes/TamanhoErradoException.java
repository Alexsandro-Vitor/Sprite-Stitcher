package excecoes;

import funcoes.Imagem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TamanhoErradoException extends Exception {
	
	public TamanhoErradoException(String imagem) {
		super("A imagem " + imagem + " não possui o tamanho de " + Imagem.LARGURA + " x "+ Imagem.ALTURA +" pixels");
	}
	
	public int[][] tratar(int[][] imagem) {
		JOptionPane.showMessageDialog(null, getMessage());
		return imagem;
	}
}
