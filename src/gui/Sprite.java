package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Dimensoes;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

/**
 * Class responsible for showing the current state of the sprite.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Sprite extends JFrame {

	private JPanel contentPane;
	public JLabel label;

	/**
	 * Create the frame.
	 */
	public Sprite() {
		setTitle("Sprite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(950, 50, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, Dimensoes.LARGURA * Dimensoes.ZOOM, Dimensoes.ALTURA * Dimensoes.ZOOM);
		contentPane.add(label);
	}

}
