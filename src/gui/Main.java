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
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

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
		setTitle("Gerador de Sprite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 164);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 11, 60, 20);
		contentPane.add(lblModelo);
		
		comboBox = new JComboBox(new String[] {"Outro", "RTP (96 x 128)"});
		comboBox.setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				atualizaDimensoes();
			}
		});
		comboBox.setBounds(80, 11, 200, 20);
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
		spinLargura.setBounds(80, 42, 60, 20);
		contentPane.add(spinLargura);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(150, 42, 60, 20);
		contentPane.add(lblAltura);
		
		spinAltura = new JSpinner();
		spinAltura.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mudarModelo();
			}
		});
		spinAltura.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinAltura.setBounds(220, 42, 60, 20);
		contentPane.add(spinAltura);
		
		JLabel lblPasta = new JLabel("Pasta de arquivos");
		lblPasta.setBounds(10, 73, 110, 20);
		contentPane.add(lblPasta);
		
		txtPasta = new JTextField();
		txtPasta.setText("imagens");
		txtPasta.setBounds(10, 104, 171, 20);
		contentPane.add(txtPasta);
		txtPasta.setColumns(10);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				continuar();
			}
		});
		btnContinuar.setBounds(191, 104, 89, 20);
		contentPane.add(btnContinuar);
	}
	
	private void atualizaDimensoes() {
		if (comboBox.getSelectedItem() == "RTP (96 x 128)") {
			spinLargura.setValue(96);
			spinAltura.setValue(128);
		}
	}
	
	private void mudarModelo() {
		comboBox.setSelectedIndex(0);
	}
	
	private void continuar() {
		Sprite sprite = new Sprite((int)spinLargura.getValue() * 2, (int)spinAltura.getValue() * 2);
		sprite.setVisible(true);
		Gerador tela = new Gerador(sprite, txtPasta.getText());
		tela.setVisible(true);
		
		frame.setVisible(false);
	}
}
