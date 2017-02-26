package excecoes;

import funcoes.Imagem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TamanhoErradoException extends Exception {
	
	public TamanhoErradoException(String imagem, int largura, int altura) {
		super("A imagem " + imagem + " não possui o tamanho de " + largura + " x "+ altura +" pixels");
	}
	
	public int[][] tratar(int[][] imagem) {
		JOptionPane.showMessageDialog(null, getMessage());
		return imagem;
	}
}
