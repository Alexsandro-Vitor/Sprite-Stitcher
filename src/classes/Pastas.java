package classes;

import java.io.File;

public class Pastas {
	public File[] corpos;
	public File[] elmos;
	public File[] cabelos;
	public File[] olhos;
	public File[] faces;
	public File[] roupas;
	public File[] costas;
	
	public Pastas() {
		corpos = abrirOuCriar("corpos");
		elmos = abrirOuCriar("elmos");
		cabelos = abrirOuCriar("cabelos");
		olhos = abrirOuCriar("olhos");
		faces = abrirOuCriar("faces");
		roupas = abrirOuCriar("roupas");
		costas = abrirOuCriar("costas");
	}
	
	private static File[] abrirOuCriar(String pasta) {
		if (new File("imagens\\"+pasta).listFiles() == null) new File("imagens\\"+pasta).mkdirs();	//Se as pastas das partes nao existem, as cria
		return new File("imagens\\"+pasta).listFiles();
	}
}
