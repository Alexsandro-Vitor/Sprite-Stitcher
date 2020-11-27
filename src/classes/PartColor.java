package classes;

import java.awt.Color;

/**
 * The color of a sprite part.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
@SuppressWarnings("serial")
public class PartColor extends Color {
	public static final PartColor WHITE = new PartColor(255, 255, 255, 255);

	public PartColor(int r, int g, int b, int a) {
		super(r, g, b, a);
	}

	public PartColor(int color) {
		super(color, true);
	}

	/**
	 * Checks if this color is grayscale.
	 * @return If this color is grayscale.
	 */
	public boolean isGrayscale() {
		return (this.getRed() == this.getGreen()) && (this.getRed() == this.getBlue());
	}

	/**
	 * Uses this color to filter another. Can also be interpreted as the original color under a light source with this color.
	 * @param original The color to be filtered.
	 * @return The filtered color.
	 */
	public PartColor filter(PartColor original) {
		return new PartColor(
				Integer.divideUnsigned(this.getRed() * original.getRed(), 255),
				Integer.divideUnsigned(this.getGreen() * original.getGreen(), 255),
				Integer.divideUnsigned(this.getBlue() * original.getBlue(), 255),
				this.getAlpha()
		);
	}

	/**
	 * Overlaps a color over another.
	 * @param over The color which will overlap.
	 * @param under The color which will be overlapped.
	 * @return The color resulting from the overlap.
	 */
	public static int overlapColor(int over, int under) {
		PartColor colorOver = new PartColor(over);
		PartColor colorUnder = new PartColor(under);
		int weightB = (255 - colorOver.getAlpha()) * colorUnder.getAlpha() / 255;
		return new PartColor(
				mean(colorOver.getAlpha(), colorOver.getRed(), weightB, colorUnder.getRed()),
				mean(colorOver.getAlpha(), colorOver.getGreen(), weightB, colorUnder.getGreen()),
				mean(colorOver.getAlpha(), colorOver.getBlue(), weightB, colorUnder.getBlue()),
				255 - Integer.divideUnsigned((255 - colorOver.getAlpha()) * (255 - colorUnder.getAlpha()), 255)
		).hashCode();
	}

	/**
	 * Calculates a weighted mean between two values.
	 * @param weightA The weight for value A.
	 * @param valueA A value for the mean.
	 * @param weightB The weight for value B.
	 * @param valueB A value for the mean.
	 * @return The mean of the two values. If weight A is 0, returns value B regardless of its weight.
	 */
	private static int mean(int weightA, int valueA, int weightB, int valueB) {
		if (weightA == 0) return valueB;
		return Integer.divideUnsigned(weightA * valueA + weightB * valueB, weightA + weightB);
	}
}
