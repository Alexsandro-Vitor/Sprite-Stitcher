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
		return argb >>> 24;
	}
	
	public static int red(int argb) {
		return (argb << 8) >>> 24;
	}
	
	public static int green(int argb) {
		return (argb << 16) >>> 24;
	}
	
	public static int blue(int argb) {
		return (argb << 24) >>> 24;
	}
	
	public void filtrar(CorARGB original) {
		this.red = Integer.divideUnsigned(this.red * original.red, 255);
		this.green = Integer.divideUnsigned(this.green * original.green, 255);
		this.blue = Integer.divideUnsigned(this.blue * original.blue, 255);
	}
	
	public int formarCor() {
		return (((alfa << 8) + red << 8) + green << 8) + blue;
	}
	
	public static int sobreporCor(int acima, int abaixo) {
		CorARGB novaCor = new CorARGB(0);
		novaCor.alfa = 255 - Integer.divideUnsigned((255 - alfa(acima)) * (255 - alfa(abaixo)), 255);
		int pesoB = (255 - alfa(acima)) * alfa(abaixo) / 255;
		novaCor.red = media(alfa(acima), red(acima), pesoB, red(abaixo));
		novaCor.green = media(alfa(acima), green(acima), pesoB, green(abaixo));
		novaCor.blue = media(alfa(acima), blue(acima), pesoB, blue(abaixo));
		return novaCor.formarCor();
	}
	
	private static int media(int pesoA, int valorA, int pesoB, int valorB) {
		if (pesoA == 0) return valorB;
		return Integer.divideUnsigned(pesoA * valorA + pesoB * valorB, pesoA + pesoB);
	}
}
