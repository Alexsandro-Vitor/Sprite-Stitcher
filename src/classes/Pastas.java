package classes;

import java.io.File;

public class Pastas {
	public String nome;
	public File[] body;
	public File[] helm;
	public File[] hair;
	public File[] eyes;
	public File[] faces;
	public File[] torso;
	public File[] hands;
	public File[] legs;
	public File[] back;
	public File[] shoes;
	
	//Cria e abre as pastas com as imagens do programa
	public Pastas(String nome) {
		this.nome = nome;
		body = abrir("body");
		helm = abrir("helm");
		hair = abrir("hair");
		eyes = abrir("eyes");
		faces = abrir("faces");
		torso = abrir("torso");
		hands = abrir("hands");
		legs = abrir("legs");
		back = abrir("back");
		shoes = abrir("shoes");
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
