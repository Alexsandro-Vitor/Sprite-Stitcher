package excecoes;

import javax.swing.JOptionPane;

import funcoes.Imagem;

public class TamanhoErradoException extends Exception {
	
	public TamanhoErradoException(String imagem) {
		super("A imagem " + imagem + " não possui o tamanho de " + Imagem.LARGURA + " x "+ Imagem.ALTURA +" pixels");
	}
	
	public int[][] tratar(int[][] imagem) {
		JOptionPane.showMessageDialog(null, getMessage());
		printStackTrace();
		return imagem;
	}
}
