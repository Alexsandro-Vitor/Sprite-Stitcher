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
	private JSpinner spinLargura;
	private JSpinner spinAltura;
	private JTextField txtPasta;

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
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 11, 60, 20);
		contentPane.add(lblModelo);
		
		comboBox = new JComboBox(new String[] {"Outro", "Antifarea (48 x 72)", "Cabbit (72 x 128)"});
		comboBox.setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				atualizaDimensoes();
			}
		});
		comboBox.setBounds(80, 11, 294, 20);
		contentPane.add(comboBox);
		
		JLabel lblLargura = new JLabel("Largura");
		lblLargura.setBounds(10, 42, 60, 20);
		contentPane.add(lblLargura);
		
		spinLargura = new JSpinner();
		spinLargura.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mudarModelo();
			}
		});
		spinLargura.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinLargura.setBounds(80, 42, 80, 20);
		contentPane.add(spinLargura);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(224, 42, 60, 20);
		contentPane.add(lblAltura);
		
		spinAltura = new JSpinner();
		spinAltura.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mudarModelo();
			}
		});
		spinAltura.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinAltura.setBounds(294, 42, 80, 20);
		contentPane.add(spinAltura);
		
		JLabel lblPasta = new JLabel("Pasta de arquivos");
		lblPasta.setBounds(10, 73, 110, 20);
		contentPane.add(lblPasta);
		
		txtPasta = new JTextField();
		txtPasta.setText("imagens");
		txtPasta.setBounds(130, 73, 244, 20);
		contentPane.add(txtPasta);
		txtPasta.setColumns(10);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				continuar();
			}
		});
		btnContinuar.setBounds(270, 104, 104, 20);
		contentPane.add(btnContinuar);
		
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
	
	private void atualizaDimensoes() {
		if (comboBox.getSelectedItem() == "Antifarea (48 x 72)") {
			spinLargura.setValue(48);
			spinAltura.setValue(72);
			txtPasta.setText("Antifarea");
		} else if (comboBox.getSelectedItem() == "Cabbit (72 x 128)") {
			spinLargura.setValue(72);
			spinAltura.setValue(128);
			txtPasta.setText("Cabbit");
		}
	}
	
	private void mudarModelo() {
		comboBox.setSelectedIndex(0);
	}
	
	private void continuar() {
		Dimensoes.LARGURA = (short)(int)spinLargura.getValue();
		Dimensoes.ALTURA = (short)(int)spinAltura.getValue();
		Dimensoes.ALTURA_CAPA = (short)((Dimensoes.ALTURA * 3) >> 2);
		Sprite sprite = new Sprite();
		sprite.setVisible(true);
		Generator tela = new Generator(sprite, txtPasta.getText());
		tela.setVisible(true);
		frame.setVisible(false);
	}
}
