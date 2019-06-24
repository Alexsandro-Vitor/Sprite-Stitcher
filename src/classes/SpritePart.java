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
	public JSpinner spinA;
	public JSpinner spinB;
	public JSpinner spinC;
	public JSpinner alpha;
	public JSpinner hueSwap;
	public SpinnerNumberModel red = new SpinnerNumberModel(255, 0, 255, 1);
	public SpinnerNumberModel green = new SpinnerNumberModel(255, 0, 255, 1);
	public SpinnerNumberModel blue = new SpinnerNumberModel(255, 0, 255, 1);
	private SpinnerNumberModel hue = new SpinnerNumberModel(0, 0, 360, 1);
	private SpinnerNumberModel sat = new SpinnerNumberModel(0, 0, 100, 1);
	private SpinnerNumberModel bright = new SpinnerNumberModel(0, 0, 100, 1);
	public JCheckBox locked;
	private PartColor color;

	/**
	 * Constructor of SpritePart.
	 * @param name Used for the tooltips.
	 * @param cmb The combo box where the part will be chosen.
	 * @param spinA The spinner for the spinA / hue attributes.
	 * @param spinB The spinner for the spinB / saturation attributes.
	 * @param spinC The spinner for the spinC / brightness attributes.
	 * @param alpha The spinner for the alpha attribute.
	 * @param hueSwap The spinner for the hue swap angle.
	 * @param locked The check box to select if the Randomize Parts button should change this part.
	 */
	public SpritePart(String name, JComboBox<String> cmb, JSpinner red, JSpinner green,
			JSpinner blue, JSpinner alpha, JSpinner hueSwap, JCheckBox locked) {
		this.name = name;
		this.cmb = cmb;
		this.spinA = red;
		this.spinB = green;
		this.spinC = blue;
		this.alpha = alpha;
		this.hueSwap = hueSwap;
		this.locked = locked;
		this.color = new PartColor(255, 255, 255, 255);
		System.out.println("" + (int)red.getValue() + ' ' + (int)green.getValue() + ' ' + (int)blue.getValue() + ' ' + (int)alpha.getValue());
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
		if (!this.locked.isSelected() && this.cmb.getItemCount() > 0)
			this.cmb.setSelectedIndex(random.nextInt(this.cmb.getItemCount()));
	}

	public PartColor getColor() {
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
			this.color = new PartColor((int)spinA.getValue(), (int)spinB.getValue(), (int)spinC.getValue(), (int)alpha.getValue());
		} else {
			temp = (Color.HSBtoRGB((int)spinA.getValue() / 360f, (int)spinB.getValue() / 100f, (int)spinC.getValue() / 100f) & 0x00FFFFFF)
					+ ((int)alpha.getValue() << 24);
			this.color = new PartColor(temp);
		}
	}

	public void setRGBA(boolean rgba) {
		if (rgba) {
			//HSL to RGB conversion
			int r = getRed(), g = getGreen(), b = getBlue();
			spinA.setModel(this.red);
			spinA.setValue(r);
			spinA.setToolTipText("Red color of " + this.name);
			spinB.setModel(this.green);
			spinB.setValue(g);
			spinB.setToolTipText("Green color of " + this.name);
			spinC.setModel(this.blue);
			spinC.setValue(b);
			spinC.setToolTipText("Blue color of " + this.name);
		} else {
			//RGB to HSL conversion
			float[] hsb = getHSB();
			spinA.setModel(this.hue);
			spinA.setValue(Math.round(hsb[0] * 360));
			spinA.setToolTipText("Hue of " + this.name);
			spinB.setModel(this.sat);
			spinB.setValue(Math.round(hsb[1] * 100));
			spinB.setToolTipText("Saturation of " + this.name);
			spinC.setModel(this.bright);
			spinC.setValue(Math.round(hsb[2] * 100));
			spinC.setToolTipText("Brightness of " + this.name);
		}
	}
}
