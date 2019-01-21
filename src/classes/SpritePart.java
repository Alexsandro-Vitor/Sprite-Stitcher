package classes;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SpritePart {
	public String name;
	private JComboBox<String> cmb;
	public JSpinner red;
	public JSpinner green;
	public JSpinner blue;
	public JSpinner alfa;
	public JSpinner hueSwap;
	private CorARGB color;

	public SpritePart(String name, JComboBox<String> cmb, JSpinner red, JSpinner green, JSpinner blue, JSpinner alfa, JSpinner hueSwap) {
		this.name = name;
		this.setCmb(cmb);
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alfa = alfa;
		this.hueSwap = hueSwap;
		this.color = new CorARGB(
			red != null ? (int)red.getValue() : 255,
			green != null ? (int)green.getValue() : 255,
			blue != null ? (int)blue.getValue() : 255,
			alfa != null ? (int)alfa.getValue() : 255
		);
	}
	
	public JComboBox<String> getCmb() {
		return this.cmb;
	}
	
	public void setCmb(JComboBox<String> cmb) {
		this.cmb = cmb;
		if (this.cmb.getItemAt(0).equals("[Empty]")) this.cmb.setEnabled(false);
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
	
	public float getHueSwap() {
		if (this.hueSwap == null) return 0;
		return (int)this.hueSwap.getValue() / 360f;
	}
	
	public CorARGB getColor() {
		return this.color;
	}
	
	float[] getHSB() {
		return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
	}
	
	public void updateColor(boolean rgba) {
		int temp;
		if (rgba) {
			this.color = new CorARGB(
				red != null ? (int)red.getValue() : 255,
				green != null ? (int)green.getValue() : 255,
				blue != null ? (int)blue.getValue() : 255,
				alfa != null ? (int)alfa.getValue() : 255
			);
		} else {
			temp = (Color.HSBtoRGB(
					red != null ? (int)red.getValue() / 360f : 0,
					green != null ? (int)green.getValue() / 100f : 0,
					blue != null ? (int)blue.getValue() / 100f : 1
				) & 0x00FFFFFF) + ((alfa != null ? (int)alfa.getValue() : 255) << 24);
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
