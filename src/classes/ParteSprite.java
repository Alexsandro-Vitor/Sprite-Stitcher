package classes;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ParteSprite {
	public String nome;
	public JComboBox<String> cmb;
	public JSpinner red;
	public JSpinner green;
	public JSpinner blue;
	public JSpinner alfa;
	private static final int I_MAX = 255;
	private int c, x, m;

	public ParteSprite(String nome, JComboBox<String> cmb, JSpinner red, JSpinner green, JSpinner blue, JSpinner alfa) {
		this.nome = nome;
		this.cmb = cmb;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alfa = alfa;
	}

	private double abs(double x) {
		return (x >= 0) ? x : -x;
	}
	
	public void atualizaCXM() {
		int h = (int)red.getValue(), s = (int)green.getValue(), l = (int)blue.getValue();
		c = (I_MAX - (int) abs(2*l - I_MAX)) * s;
		x = (int) (c * (1 - abs(((double)h / 60) % 2 - 1)));
		m = l * I_MAX - c/2;
	}
	
	int getRed(boolean rgba) {
		if (red == null) return I_MAX;	//Caso do corpo, que não tem spinners
		if (rgba) return (int)red.getValue();
		else {
			int h = (int)red.getValue();
			if (h < 60) return (c + m)/I_MAX;
			else if (h < 120) return (x + m)/I_MAX;
			else if (h < 240) return m/I_MAX;
			else if (h < 300) return (x + m)/I_MAX;
			else return (c + m)/I_MAX;
		}
	}

	int getGreen(boolean rgba) {
		if (green == null) return I_MAX;
		if (rgba) return (int)green.getValue();
		else {
			int h = (int)red.getValue();
			if (h < 60) return (x + m)/I_MAX;
			else if (h < 180) return (c + m)/I_MAX;
			else if (h < 240) return (x + m)/I_MAX;
			else return m/I_MAX;
		}
	}

	int getBlue(boolean rgba) {
		if (blue == null) return I_MAX;
		if (rgba) return (int)blue.getValue();
		else {
			int h = (int)red.getValue();
			if (h < 120) return m/I_MAX;
			else if (h < 180) return (x + m)/I_MAX;
			else if (h < 300) return (c + m)/I_MAX;
			else return (x + m)/I_MAX;
		}
	}

	int getAlfa() {
		return (alfa == null) ? 255 : (int)alfa.getValue();
	}

	public void setRGBA(boolean rgba) {
		if (rgba) {
			atualizaCXM();
			//Conversao HSL para RGB
			//System.out.println("Convertendo HSL para RGB");
			int r = getRed(!rgba), g = getGreen(!rgba), b = getBlue(!rgba);
			//System.out.println("Red: "+r+", Green: "+g+", Blue: "+b);
			//int h = (int)red.getValue(), s = (int)green.getValue(), i = (int)blue.getValue();
			red.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			red.setValue(r);
			red.setToolTipText("Cor vermelha de " + nome);
			green.setValue(g);
			green.setToolTipText("Cor verde de " + nome);
			blue.setValue(b);
			blue.setToolTipText("Cor azul de " + nome);
		} else {
			//Conversao RGB para HSL
			//System.out.println("Convertendo RGB para HSL");
			//int r = (int)getRed(!rgba), g = (int)getGreen(!rgba), b = (int)getBlue(!rgba);
			//System.out.println("Red: "+r+", Green: "+g+", Blue: "+b);
			int m = getMatiz(), s = getSaturacao(), l = getLuz();
			blue.setValue(l);
			blue.setToolTipText("Luminância de " + nome);
			green.setValue(s);
			green.setToolTipText("Saturação de " + nome);
			red.setModel(new SpinnerNumberModel(0, 0, 359, 1));
			red.setValue(m);
			red.setToolTipText("Matiz de " + nome);
			//System.out.println("M: "+getRed(!rgba)+", S: "+getGreen(!rgba)+", L: "+getBlue(!rgba));
			//System.out.println("R: "+getRed(rgba)+", G: "+getGreen(rgba)+", B: "+getBlue(rgba));
			
			//int c = (I_MAX - (int) abs(2*l - I_MAX)) * s;	//Mult por i_max^2
			//int x = (int) (c * (1 - abs(((double)m / 60) % 2 - 1)));//Mult por i_max^2
			//int m1 = l * I_MAX - c/2;//Mult por i_max^2
			
			//System.out.println("C = "+ c/I_MAX + ", X = " + x/I_MAX + ", m = " + m1/I_MAX);
		}
	}
	
	private int getMatiz() {
		int r = (int)red.getValue(), g = (int)green.getValue(), b = (int)blue.getValue();
		int matiz = 0;
		if (delta() == 0) return matiz;
		if (r >= g && r >= b) matiz = (int) Math.round(60 * (double)(g - b) / delta());
		else if (g >= b && g >= r) matiz = (int) Math.round(60 * (double)(2 * I_MAX + (b - r)) / delta());
		else matiz = (int) Math.round(60 * (double)(4 * I_MAX + (r - g)) / delta());
		return (matiz >= 0) ? matiz : matiz + 360;
	}

	private int getSaturacao() {
		if (min() == max()) return 0;
		else return I_MAX * delta() / (I_MAX - (int) abs(min() + max() - I_MAX));
	}

	private int getLuz() {
		return (min() + max()) / 2;
	}

	private int delta() {
		return max() - min();
	}

	private int min() {
		int r = (int)red.getValue(), g = (int)green.getValue(), b = (int)blue.getValue();
		int min = (r <= g) ? r : g;
		return (min <= b) ? min : b;
	}

	private int max() {
		int r = (int)red.getValue(), g = (int)green.getValue(), b = (int)blue.getValue();
		int max = (r > g) ? r : g;
		return (max > b) ? max : b;
	}
}
