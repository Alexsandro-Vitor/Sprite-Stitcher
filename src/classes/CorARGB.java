package classes;

public class CorARGB {
	public int alfa, red, green, blue;
	
	public CorARGB(int cor) {
		this.alfa = alfa(cor);
		this.red = red(cor);
		this.green = green(cor);
		this.blue = blue(cor);
	}
	
	public CorARGB(ParteSprite parte) {
		alfa = parte.getAlfa();
		red = parte.getRed();
		green = parte.getGreen();
		blue = parte.getBlue();
	}
	
	public static int alfa(int argb) {
		return Integer.divideUnsigned(argb, 16777216);
	}
	
	public static int red(int argb) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(argb, 65536), 256);
	}
	
	public static int green(int argb) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(argb, 256), 256);
	}
	
	public static int blue(int argb) {
		return Integer.remainderUnsigned(argb, 256);
	}
	
	public void filtrar(CorARGB original) {
		this.red = Integer.divideUnsigned(this.red * original.red, 255);
		this.green = Integer.divideUnsigned(this.green * original.green, 255);
		this.blue = Integer.divideUnsigned(this.blue * original.blue, 255);
	}
	
	public int formarCor() {
		return ((alfa * 256 + red) * 256 + green) * 256 + blue;
	}
	
	public static int sobreporCor(int acima, int abaixo) {
		CorARGB novaCor = new CorARGB(0);
		novaCor.alfa = 255 - Integer.divideUnsigned((255 - alfa(acima)) * (255 - alfa(abaixo)), 255);
		novaCor.red = media(alfa(acima), red(acima), red(abaixo));
		novaCor.green = media(alfa(acima), green(acima), green(abaixo));
		novaCor.blue = media(alfa(acima), blue(acima), blue(abaixo));
		return novaCor.formarCor();
	}
	
	private static int media(int pesoA, int valorA, int valorB) {
		return Integer.divideUnsigned((pesoA * valorA + (255 - pesoA) * valorB), 255);
	}
}
