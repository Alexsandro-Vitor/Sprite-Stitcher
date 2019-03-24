package classes;

import java.awt.Color;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Class which manages the part layers.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class SpritePart {
	public String name;
	private JComboBox<String> cmb;
	public JSpinner red;
	public JSpinner green;
	public JSpinner blue;
	public JSpinner alpha;
	public JSpinner hueSwap;
	public JCheckBox locked;
	private CorARGB color;

	/**
	 * Constructor of SpritePart.
	 * @param name Used for the tooltips.
	 * @param cmb The combo box where the part will be chosen.
	 * @param red The spinner for the red / hue attributes.
	 * @param green The spinner for the green / saturation attributes.
	 * @param blue The spinner for the blue / brightness attributes.
	 * @param alpha The spinner for the alpha attribute.
	 * @param hueSwap The spinner for the hue swap angle.
	 * @param locked The check box to select if the Randomize Parts button should change this part.
	 */
	public SpritePart(String name, JComboBox<String> cmb, JSpinner red, JSpinner green,
			JSpinner blue, JSpinner alpha, JSpinner hueSwap, JCheckBox locked) {
		this.name = name;
		this.cmb = cmb;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		this.hueSwap = hueSwap;
		this.locked = locked;
		this.color = new CorARGB((int)red.getValue(), (int)green.getValue(), (int)blue.getValue(), (int)alpha.getValue());
	}

	public JComboBox<String> getCmb() {
		return this.cmb;
	}

	int getRed() {
		return this.color.getRed();
	}

	int getGreen() {
		return this.color.getGreen();
	}

	int getBlue() {
		return this.color.getBlue();
	}

	int getAlfa() {
		return this.color.getAlpha();
	}

	/**
	 * Gets the hue swap angle.
	 * @return The hue swap angle, in degrees.
	 */
	public float getHueSwap() {
		return (int)this.hueSwap.getValue() / 360f;
	}

	/**
	 * Selects a random part in the combo box, if this part is not locked.
	 * @param random A random object for selecting the part.
	 */
	public void selectRandomPart(Random random) {
		if (!this.locked.isSelected()) this.cmb.setSelectedIndex(random.nextInt(this.cmb.getItemCount()));
	}

	public CorARGB getColor() {
		return this.color;
	}

	float[] getHSB() {
		return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
	}

	/**
	 * Reads the current values of the combo boxes to update the current color.
	 * @param rgba If the attributes are RGB (true) or HSB (false).
	 */
	public void updateColor(boolean rgba) {
		int temp;
		if (rgba) {
			this.color = new CorARGB((int)red.getValue(), (int)green.getValue(), (int)blue.getValue(), (int)alpha.getValue());
		} else {
			temp = (Color.HSBtoRGB((int)red.getValue() / 360f, (int)green.getValue() / 100f, (int)blue.getValue() / 100f) & 0x00FFFFFF)
					+ ((int)alpha.getValue() << 24);
			this.color = new CorARGB(temp);
		}
	}

	public void setRGBA(boolean rgba) {
		if (rgba) {
			//HSL to RGB conversion
			int r = getRed(), g = getGreen(), b = getBlue();
			red.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			red.setValue(r);
			red.setToolTipText("Red color of " + this.name);
			green.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			green.setValue(g);
			green.setToolTipText("Green color of " + this.name);
			blue.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			blue.setValue(b);
			blue.setToolTipText("Blue color of " + this.name);
		} else {
			//RGB to HSL conversion
			float[] hsb = getHSB();
			red.setModel(new SpinnerNumberModel(0, 0, 360, 1));
			red.setValue(Math.round(hsb[0] * 360));
			red.setToolTipText("Hue of " + this.name);
			green.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			green.setValue(Math.round(hsb[1] * 100));
			green.setToolTipText("Saturation of " + this.name);
			blue.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			blue.setValue(Math.round(hsb[2] * 100));
			blue.setToolTipText("Brightness of " + this.name);
		}
	}
}
