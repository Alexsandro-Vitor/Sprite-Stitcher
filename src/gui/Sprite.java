package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Sprite extends JFrame {

	private JPanel contentPane;
	public JLabel label;

	/**
	 * Create the frame.
	 */
	public Sprite(int largura, int altura) {
		setTitle("Sprite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 100, largura + 6, altura + 29);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, largura, altura);
		contentPane.add(label);
	}

}
