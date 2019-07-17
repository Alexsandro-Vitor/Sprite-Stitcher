package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;

import classes.Dimensions;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
/**
 * The initial screen of the application.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Main extends JFrame {
	private Main frame;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JSpinner spinWidth;
	private JSpinner spinHeight;
	private JTextField txtFolder;
	private JSpinner spinZoom;
	private JSpinner spinBackY;
	private JSpinner spinBackHeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Main() {
		frame = this;
		setTitle("Sprite Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 164);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 11, 70, 20);
		contentPane.add(lblModel);
		
		comboBox = new JComboBox(new String[] {"Other", "Antifarea (48 x 72)", "Cabbit (72 x 128)"});
		comboBox.setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateDimensions();
			}
		});
		comboBox.setBounds(90, 11, 360, 20);
		contentPane.add(comboBox);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(10, 42, 70, 20);
		contentPane.add(lblWidth);
		
		spinWidth = new JSpinner();
		spinWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeModel();
			}
		});
		spinWidth.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinWidth.setBounds(90, 42, 60, 20);
		contentPane.add(spinWidth);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(160, 42, 70, 20);
		contentPane.add(lblHeight);
		
		spinHeight = new JSpinner();
		spinHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeModel();
			}
		});
		spinHeight.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinHeight.setBounds(240, 42, 60, 20);
		contentPane.add(spinHeight);
		
		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setBounds(310, 42, 70, 20);
		contentPane.add(lblZoom);
		
		spinZoom = new JSpinner();
		spinZoom.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinZoom.setBounds(390, 42, 60, 20);
		contentPane.add(spinZoom);
		
		JLabel lblBackY = new JLabel("Back Y");
		lblBackY.setBounds(10, 73, 70, 20);
		contentPane.add(lblBackY);
		
		spinBackY = new JSpinner();
		spinBackY.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinBackY.setBounds(90, 73, 60, 20);
		contentPane.add(spinBackY);
		
		JLabel lblBackHeight = new JLabel("Back Height");
		lblBackHeight.setBounds(160, 73, 70, 20);
		contentPane.add(lblBackHeight);
		
		spinBackHeight = new JSpinner();
		spinBackHeight.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinBackHeight.setBounds(240, 73, 60, 20);
		contentPane.add(spinBackHeight);
		
		JLabel lblFolder = new JLabel("Images folder");
		lblFolder.setBounds(10, 104, 80, 20);
		contentPane.add(lblFolder);
		
		txtFolder = new JTextField();
		txtFolder.setText("images");
		txtFolder.setBounds(100, 104, 200, 20);
		contentPane.add(txtFolder);
		txtFolder.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				continueToGenerator();
			}
		});
		btnContinue.setBounds(310, 104, 140, 20);
		contentPane.add(btnContinue);
	}
	
	/**
	 * Updates the dimensions to match the selected one at the comboBox.
	 */
	private void updateDimensions() {
		if (comboBox.getSelectedItem() == "Antifarea (48 x 72)") {
			spinWidth.setValue(48);
			spinHeight.setValue(72);
			txtFolder.setText("Antifarea");
		} else if (comboBox.getSelectedItem() == "Cabbit (72 x 128)") {
			spinWidth.setValue(72);
			spinHeight.setValue(128);
			txtFolder.setText("Cabbit");
		}
	}
	
	private void changeModel() {
		comboBox.setSelectedIndex(0);
	}
	
	/**
	 * Moves to Generator and Sprite frames.
	 */
	private void continueToGenerator() {
		Dimensions.WIDTH = (int)spinWidth.getValue();
		Dimensions.HEIGHT = (int)spinHeight.getValue();
		Dimensions.ZOOM = (int)spinZoom.getValue();
		Dimensions.BACK_Y = ((Dimensions.HEIGHT * 3) >> 2);
		Dimensions.BACK_HEIGHT = (int)spinHeight.getValue();
		Sprite sprite = new Sprite();
		sprite.setVisible(true);
		Generator screen = new Generator(sprite, txtFolder.getText());
		screen.setVisible(true);
		frame.setVisible(false);
	}
}
