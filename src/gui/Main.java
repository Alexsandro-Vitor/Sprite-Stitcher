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
import functions.Reading;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Font;

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
	
	private ArrayList<Dimensions> dimensions;
	private ArrayList<String> dimensionChoices;

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
		this.getTemplatesDimensions();
		
		setTitle("Sprite Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblModel.setBounds(10, 11, 70, 20);
		contentPane.add(lblModel);
		
		comboBox = new JComboBox(this.dimensionChoices.toArray());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateDimensions();
			}
		});
		comboBox.setBounds(90, 11, 360, 20);
		contentPane.add(comboBox);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWidth.setBounds(10, 42, 70, 20);
		contentPane.add(lblWidth);
		
		spinWidth = new JSpinner();
		spinWidth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeModel();
			}
		});
		spinWidth.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinWidth.setBounds(90, 42, 60, 20);
		contentPane.add(spinWidth);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHeight.setBounds(160, 42, 70, 20);
		contentPane.add(lblHeight);
		
		spinHeight = new JSpinner();
		spinHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeModel();
			}
		});
		spinHeight.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinHeight.setBounds(240, 42, 60, 20);
		contentPane.add(spinHeight);
		
		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblZoom.setBounds(310, 42, 70, 20);
		contentPane.add(lblZoom);
		
		spinZoom = new JSpinner();
		spinZoom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinZoom.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinZoom.setBounds(390, 42, 60, 20);
		contentPane.add(spinZoom);
		
		JLabel lblBackY = new JLabel("Back Y");
		lblBackY.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBackY.setBounds(10, 73, 70, 20);
		contentPane.add(lblBackY);
		
		spinBackY = new JSpinner();
		spinBackY.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinBackY.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinBackY.setBounds(90, 73, 60, 20);
		contentPane.add(spinBackY);
		
		JLabel lblBackHeight = new JLabel("Back Height");
		lblBackHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBackHeight.setBounds(160, 73, 70, 20);
		contentPane.add(lblBackHeight);
		
		spinBackHeight = new JSpinner();
		spinBackHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinBackHeight.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinBackHeight.setBounds(240, 73, 60, 20);
		contentPane.add(spinBackHeight);
		
		JLabel lblFolder = new JLabel("Images folder");
		lblFolder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFolder.setBounds(10, 104, 80, 20);
		contentPane.add(lblFolder);
		
		txtFolder = new JTextField();
		txtFolder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFolder.setText("images");
		txtFolder.setBounds(100, 104, 200, 20);
		contentPane.add(txtFolder);
		txtFolder.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnContinue.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				continueToGenerator();
			}
		});
		btnContinue.setBounds(310, 104, 140, 20);
		contentPane.add(btnContinue);
	}
	
	private void getTemplatesDimensions() {
		this.dimensionChoices = new ArrayList<String>();
		this.dimensionChoices.add("Other");
		try {
			this.dimensions = Reading.readTemplatesData();
			for (Dimensions template : this.dimensions) {
				this.dimensionChoices.add(template.getChoiceText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the dimensions to match the selected one at the comboBox.
	 */
	private void updateDimensions() {
		for (Dimensions template : this.dimensions) {
			if (comboBox.getSelectedItem().equals(template.getChoiceText())) {
				spinWidth.setValue(template.width);
				spinHeight.setValue(template.height);
				spinBackY.setValue(template.backY);
				spinBackHeight.setValue(template.backHeight);
				txtFolder.setText(template.name);
			}
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
		Dimensions.BACK_Y = (int)spinBackY.getValue();
		Dimensions.BACK_HEIGHT = (int)spinBackHeight.getValue();
		Sprite sprite = new Sprite();
		sprite.setVisible(true);
		Generator screen = new Generator(sprite, txtFolder.getText());
		screen.setVisible(true);
		frame.setVisible(false);
	}
}
