package classes;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class ParteSprite {
	public JComboBox<String> cmb;
	public JSpinner red;
	public JSpinner green;
	public JSpinner blue;
	public JSpinner alfa;
	
	public ParteSprite(JComboBox<String> cmb, JSpinner red, JSpinner green, JSpinner blue, JSpinner alfa) {
		this.cmb = cmb;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alfa = alfa;
	}
	
	int getRed() {
		return (red == null) ? 255 : (int)red.getValue();
	}
	
	int getGreen() {
		return (green == null) ? 255 : (int)green.getValue();
	}
	
	int getBlue() {
		return (blue == null) ? 255 : (int)blue.getValue();
	}
	
	int getAlfa() {
		return (alfa == null) ? 255 : (int)alfa.getValue();
	}
}
