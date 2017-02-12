package funcoes;

import java.io.File;

public class Pastas {
	public File[] corpos;
	public File[] elmos;
	public File[] cabelos;
	public File[] olhos;
	public File[] faces;
	public File[] roupas;
	public File[] costas;
	
	//Cria e abre as pastas com as imagens do programa
	public Pastas() {
		corpos = abrir("corpos");
		elmos = abrir("elmos");
		cabelos = abrir("cabelos");
		olhos = abrir("olhos");
		faces = abrir("faces");
		roupas = abrir("roupas");
		costas = abrir("costas");
	}
	
	//Abre a pasta
	private static File[] abrir(String pasta) {
		criar(pasta);
		return new File("imagens\\"+pasta).listFiles();
	}
	
	//Cria a pasta se ela nao existir
	protected static void criar(String pasta) {
		if (new File("imagens\\"+pasta).listFiles() == null) new File("imagens\\"+pasta).mkdirs();	
	}
}
