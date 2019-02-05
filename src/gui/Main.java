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

import classes.Dimensoes;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Main extends JFrame {
	private Main frame;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JSpinner spinWidth;
	private JSpinner spinHeight;
	private JTextField txtFolder;

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
		setBounds(100, 100, 390, 164);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 11, 60, 20);
		contentPane.add(lblModel);
		
		comboBox = new JComboBox(new String[] {"Other", "Antifarea (48 x 72)", "Cabbit (72 x 128)"});
		comboBox.setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateDimensions();
			}
		});
		comboBox.setBounds(80, 11, 294, 20);
		contentPane.add(comboBox);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(10, 42, 60, 20);
		contentPane.add(lblWidth);
		
		spinWidth = new JSpinner();
		spinWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeModel();
			}
		});
		spinWidth.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinWidth.setBounds(80, 42, 80, 20);
		contentPane.add(spinWidth);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(224, 42, 60, 20);
		contentPane.add(lblHeight);
		
		spinHeight = new JSpinner();
		spinHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeModel();
			}
		});
		spinHeight.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinHeight.setBounds(294, 42, 80, 20);
		contentPane.add(spinHeight);
		
		JLabel lblFolder = new JLabel("Images folder");
		lblFolder.setBounds(10, 73, 90, 20);
		contentPane.add(lblFolder);
		
		txtFolder = new JTextField();
		txtFolder.setText("images");
		txtFolder.setBounds(110, 73, 264, 20);
		contentPane.add(txtFolder);
		txtFolder.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				continueToGenerator();
			}
		});
		btnContinue.setBounds(270, 104, 104, 20);
		contentPane.add(btnContinue);
		
		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setBounds(10, 104, 46, 20);
		contentPane.add(lblZoom);
		
		ButtonGroup zoomGroup = new ButtonGroup();
		
		JRadioButton rdbtn1x = new JRadioButton("1x");
		rdbtn1x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Dimensoes.ZOOM = 1;
			}
		});
		rdbtn1x.setBounds(80, 104, 60, 20);
		contentPane.add(rdbtn1x);
		zoomGroup.add(rdbtn1x);
		
		JRadioButton rdbtn2x = new JRadioButton("2x");
		rdbtn2x.setSelected(true);
		rdbtn2x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Dimensoes.ZOOM = 2;
			}
		});
		rdbtn2x.setBounds(142, 104, 60, 20);
		contentPane.add(rdbtn2x);
		zoomGroup.add(rdbtn2x);
		
		JRadioButton rdbtn4x = new JRadioButton("4x");
		rdbtn4x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Dimensoes.ZOOM = 4;
			}
		});
		rdbtn4x.setBounds(204, 104, 60, 20);
		contentPane.add(rdbtn4x);
		zoomGroup.add(rdbtn4x);
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
	
	private void continueToGenerator() {
		Dimensoes.LARGURA = (short)(int)spinWidth.getValue();
		Dimensoes.ALTURA = (short)(int)spinHeight.getValue();
		Dimensoes.ALTURA_CAPA = (short)((Dimensoes.ALTURA * 3) >> 2);
		Sprite sprite = new Sprite();
		sprite.setVisible(true);
		Generator screen = new Generator(sprite, txtFolder.getText());
		screen.setVisible(true);
		frame.setVisible(false);
	}
}
