package classes;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ParteSprite {
	public String nome;
	private JComboBox<String> cmb;
	public JSpinner red;
	public JSpinner green;
	public JSpinner blue;
	public JSpinner alfa;
	private CorARGB cor;

	public ParteSprite(String nome, JComboBox<String> cmb, JSpinner red, JSpinner green, JSpinner blue, JSpinner alfa) {
		this.nome = nome;
		this.setCmb(cmb);
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alfa = alfa;
		this.cor = new CorARGB(
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
		System.out.print(cor.getRed() + " ");
		return this.cor.getRed();
	}

	int getGreen() {
		System.out.print(cor.getGreen() + " ");
		return this.cor.getGreen();
	}

	int getBlue() {
		System.out.print(cor.getBlue() + " ");
		return this.cor.getBlue();
	}

	int getAlfa() {
		System.out.print(cor.getAlpha() + " ");
		return this.cor.getAlpha();
	}
	
	public CorARGB getCor() {
		return this.cor;
	}
	
	float[] getHSB() {
		return Color.RGBtoHSB(cor.getRed(), cor.getGreen(), cor.getBlue(), null);
	}
	
	public void atualizaCor(boolean rgba) {
		int temp;
		if (rgba) {
			this.cor = new CorARGB(
				red != null ? (int)red.getValue() : 255,
				green != null ? (int)green.getValue() : 255,
				blue != null ? (int)blue.getValue() : 255,
				alfa != null ? (int)alfa.getValue() : 255
			);
		} else {
			temp = (Color.HSBtoRGB(
					red != null ? (float)(int)red.getValue() / 360 : 0,
					green != null ? (float)(int)green.getValue() / 100 : 0,
					blue != null ? (float)(int)blue.getValue() / 100 : 1
				) & 0x00FFFFFF) + ((alfa != null ? (int)alfa.getValue() : 255) << 24);
			this.cor = new CorARGB(temp);
		}
	}

	public void setRGBA(boolean rgba) {
		if (rgba) {
			//Conversao HSL para RGB
			int r = getRed(), g = getGreen(), b = getBlue();
			red.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			red.setValue(r);
			red.setToolTipText("Red color of " + this.nome);
			green.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			green.setValue(g);
			green.setToolTipText("Green color of " + this.nome);
			blue.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			blue.setValue(b);
			blue.setToolTipText("Blue color of " + this.nome);
		} else {
			//Conversao RGB para HSL
			float[] hsb = getHSB();
			red.setModel(new SpinnerNumberModel(0, 0, 360, 1));
			red.setValue(Math.round(hsb[0] * 360));
			red.setToolTipText("Hue of " + this.nome);
			green.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			green.setValue(Math.round(hsb[1] * 100));
			green.setToolTipText("Saturation of " + this.nome);
			blue.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			blue.setValue(Math.round(hsb[2] * 100));
			blue.setToolTipText("Brightness of " + this.nome);
		}
	}
}
