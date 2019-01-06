package classes;

import java.awt.Color;

public class CorARGB extends Color {

	public CorARGB(int r, int g, int b, int a) {
		super(r, g, b, a);
	}

	public CorARGB(int cor) {
		super(cor, true);
	}

	public boolean isGrayscale() {
		return (this.getRed() == this.getGreen()) && (this.getRed() == this.getBlue());
	}

	public CorARGB filtrar(CorARGB original) {
		return new CorARGB(
				Integer.divideUnsigned(this.getRed() * original.getRed(), 255),
				Integer.divideUnsigned(this.getGreen() * original.getGreen(), 255),
				Integer.divideUnsigned(this.getBlue() * original.getBlue(), 255),
				this.getAlpha()
				);
	}

	public static int sobreporCor(int acima, int abaixo) {
		CorARGB corAcima = new CorARGB(acima);
		CorARGB corAbaixo = new CorARGB(abaixo);
		int pesoB = (255 - corAcima.getAlpha()) * corAbaixo.getAlpha() / 255;
		return new CorARGB(
				media(corAcima.getAlpha(), corAcima.getRed(), pesoB, corAbaixo.getRed()),
				media(corAcima.getAlpha(), corAcima.getGreen(), pesoB, corAbaixo.getGreen()),
				media(corAcima.getAlpha(), corAcima.getBlue(), pesoB, corAbaixo.getBlue()),
				255 - Integer.divideUnsigned((255 - corAcima.getAlpha()) * (255 - corAbaixo.getAlpha()), 255)
				).hashCode();
	}

	private static int media(int pesoA, int valorA, int pesoB, int valorB) {
		if (pesoA == 0) return valorB;
		return Integer.divideUnsigned(pesoA * valorA + pesoB * valorB, pesoA + pesoB);
	}
}
