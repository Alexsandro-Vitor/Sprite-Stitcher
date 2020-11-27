package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Dimensions;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

/**
 * Class responsible for showing the current state of the sprite.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
@SuppressWarnings("serial")
public class Sprite extends JFrame {

	private JPanel contentPane;
	public JLabel label;

	/**
	 * Create the frame.
	 */
	public Sprite() {
		setTitle("Sprite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(980, 50, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, Dimensions.WIDTH * Dimensions.ZOOM, Dimensions.HEIGHT * Dimensions.ZOOM);
		contentPane.add(label);
	}

}
