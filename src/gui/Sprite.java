package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Dimensoes;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Sprite extends JFrame {

	private JPanel contentPane;
	public JLabel label;

	/**
	 * Create the frame.
	 */
	public Sprite() {
		setTitle("Sprite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 50, Dimensoes.LARGURA * Dimensoes.ZOOM + 6, Dimensoes.ALTURA * Dimensoes.ZOOM + 29);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, Dimensoes.LARGURA * Dimensoes.ZOOM, Dimensoes.ALTURA * Dimensoes.ZOOM);
		contentPane.add(label);
	}

}
