package classes;

import java.io.File;

public class Pastas {
	private String nome;
	public File[] corpos;
	public File[] elmos;
	public File[] cabelos;
	public File[] olhos;
	public File[] faces;
	public File[] camisas;
	public File[] calcas;
	public File[] costas;
	
	//Cria e abre as pastas com as imagens do programa
	public Pastas(String nome) {
		this.nome = nome;
		corpos = abrir("corpos");
		elmos = abrir("elmos");
		cabelos = abrir("cabelos");
		olhos = abrir("olhos");
		faces = abrir("faces");
		camisas = abrir("camisas");
		calcas = abrir("calcas");
		costas = abrir("costas");
	}
	
	//Abre a pasta
	private File[] abrir(String pasta) {
		criar(pasta);
		return new File(nome + '\\' + pasta).listFiles();
	}
	
	//Cria a pasta se ela nao existir
	public void criar(String pasta) {
		if (new File(nome + '\\' + pasta).listFiles() == null) new File(nome + '\\' + pasta).mkdirs();	
	}
}
