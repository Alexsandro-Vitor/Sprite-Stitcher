package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import classes.ParteSprite;
import classes.Pastas;
import excecoes.TamanhoErradoException;
import funcoes.*;

import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Pastas pastas = new Pastas();
	private BufferedImage buffer;
	private static Random random = new Random();
	private ParteSprite corpo;
	private ParteSprite elmo;
	private ParteSprite cabelo;
	private ParteSprite olhos;
	private ParteSprite face;
	private ParteSprite camisaA;
	private ParteSprite camisaB;
	private ParteSprite calcaA;
	private ParteSprite calcaB;
	private ParteSprite costas;
	private boolean deveAtualizar = true;

	private JPanel contentPane;
	private JLabel lblSprite;
	private JTextField txtNomeSprite;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 380);
		setResizable(false);
		setTitle("Gerador de Sprite");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCorpo = new JLabel("Corpo");
		lblCorpo.setBounds(10, 11, 60, 20);
		contentPane.add(lblCorpo);

		JComboBox<String> cmbCorpo = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));
		
		cmbCorpo.setBackground(Color.WHITE);
		cmbCorpo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCorpo.setBounds(80, 11, 200, 20);
		contentPane.add(cmbCorpo);
		
		corpo = new ParteSprite(cmbCorpo, null, null, null, null);
		
		JLabel lblCoresRgba = new JLabel("Cores RGBA");
		lblCoresRgba.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoresRgba.setBounds(290, 11, 230, 20);
		contentPane.add(lblCoresRgba);

		JLabel lblElmo = new JLabel("Elmo");
		lblElmo.setBounds(10, 42, 60, 20);
		contentPane.add(lblElmo);

		JComboBox<String> cmbElmo = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
		cmbElmo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbElmo.setBackground(Color.WHITE);
		cmbElmo.setBounds(80, 42, 200, 20);
		contentPane.add(cmbElmo);
		
		JSpinner spinRedElmo = new JSpinner();
		spinRedElmo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedElmo.setToolTipText("Cor vermelha do elmo");
		spinRedElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedElmo.setBounds(290, 42, 50, 20);
		contentPane.add(spinRedElmo);
		
		JSpinner spinGreenElmo = new JSpinner();
		spinGreenElmo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenElmo.setToolTipText("Cor verde do elmo");
		spinGreenElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenElmo.setBounds(350, 42, 50, 20);
		contentPane.add(spinGreenElmo);
		
		JSpinner spinBlueElmo = new JSpinner();
		spinBlueElmo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueElmo.setToolTipText("Cor azul do elmo");
		spinBlueElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueElmo.setBounds(410, 42, 50, 20);
		contentPane.add(spinBlueElmo);
		
		JSpinner spinAlfaElmo = new JSpinner();
		spinAlfaElmo.setToolTipText("Opacidade do elmo");
		spinAlfaElmo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinAlfaElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaElmo.setBounds(470, 42, 50, 20);
		contentPane.add(spinAlfaElmo);
		
		elmo = new ParteSprite(cmbElmo, spinRedElmo, spinGreenElmo, spinBlueElmo, spinAlfaElmo);

		JLabel lblCabelo = new JLabel("Cabelo");
		lblCabelo.setBounds(10, 73, 60, 20);
		contentPane.add(lblCabelo);

		JComboBox<String> cmbCabelo = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
		cmbCabelo.setBackground(Color.WHITE);
		cmbCabelo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCabelo.setBounds(80, 73, 200, 20);
		contentPane.add(cmbCabelo);

		JSpinner spinRedCabelo = new JSpinner();
		spinRedCabelo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedCabelo.setToolTipText("Cor vermelha do cabelo");
		spinRedCabelo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCabelo.setBounds(290, 73, 50, 20);
		contentPane.add(spinRedCabelo);
		
		JSpinner spinGreenCabelo = new JSpinner();
		spinGreenCabelo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenCabelo.setToolTipText("Cor verde do cabelo");
		spinGreenCabelo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCabelo.setBounds(350, 73, 50, 20);
		contentPane.add(spinGreenCabelo);
		
		JSpinner spinBlueCabelo = new JSpinner();
		spinBlueCabelo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueCabelo.setToolTipText("Cor azul do cabelo");
		spinBlueCabelo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCabelo.setBounds(410, 73, 50, 20);
		contentPane.add(spinBlueCabelo);
		
		cabelo = new ParteSprite(cmbCabelo, spinRedCabelo, spinGreenCabelo, spinBlueCabelo, null);
		
		JLabel lblOlhos = new JLabel("Olhos");
		lblOlhos.setBounds(10, 104, 60, 20);
		contentPane.add(lblOlhos);

		JComboBox<String> cmbOlhos = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
		cmbOlhos.setBackground(Color.WHITE);
		cmbOlhos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbOlhos.setBounds(80, 104, 200, 20);
		contentPane.add(cmbOlhos);

		JSpinner spinRedOlhos = new JSpinner();
		spinRedOlhos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedOlhos.setToolTipText("Cor vermelha dos olhos");
		spinRedOlhos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedOlhos.setBounds(290, 104, 50, 20);
		contentPane.add(spinRedOlhos);
		
		JSpinner spinGreenOlhos = new JSpinner();
		spinGreenOlhos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenOlhos.setToolTipText("Cor verde dos olhos");
		spinGreenOlhos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenOlhos.setBounds(350, 104, 50, 20);
		contentPane.add(spinGreenOlhos);
		
		JSpinner spinBlueOlhos = new JSpinner();
		spinBlueOlhos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueOlhos.setToolTipText("Cor azul dos olhos");
		spinBlueOlhos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueOlhos.setBounds(410, 104, 50, 20);
		contentPane.add(spinBlueOlhos);
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setBounds(10, 135, 60, 20);
		contentPane.add(lblFace);
		
		olhos = new ParteSprite(cmbOlhos, spinRedOlhos, spinGreenOlhos, spinBlueOlhos, null);

		JComboBox<String> cmbFace = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
		cmbFace.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbFace.setBackground(Color.WHITE);
		cmbFace.setBounds(80, 135, 200, 20);
		contentPane.add(cmbFace);

		JSpinner spinRedFace = new JSpinner();
		spinRedFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedFace.setToolTipText("Cor vermelha do item da face");
		spinRedFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedFace.setBounds(290, 135, 50, 20);
		contentPane.add(spinRedFace);
		
		JSpinner spinGreenFace = new JSpinner();
		spinGreenFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenFace.setToolTipText("Cor verde do item da face");
		spinGreenFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenFace.setBounds(350, 135, 50, 20);
		contentPane.add(spinGreenFace);
		
		JSpinner spinBlueFace = new JSpinner();
		spinBlueFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueFace.setToolTipText("Cor azul do item da face");
		spinBlueFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueFace.setBounds(410, 135, 50, 20);
		contentPane.add(spinBlueFace);
		
		JSpinner spinAlfaFace = new JSpinner();
		spinAlfaFace.setToolTipText("Opacidade do item da face");
		spinAlfaFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		spinAlfaFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaFace.setBounds(470, 135, 50, 20);
		contentPane.add(spinAlfaFace);
		
		face = new ParteSprite(cmbFace, spinRedFace, spinGreenFace, spinBlueFace, spinAlfaFace);

		JLabel lblCamisaA = new JLabel("Camisa A");
		lblCamisaA.setBounds(10, 166, 60, 20);
		contentPane.add(lblCamisaA);

		JComboBox<String> cmbCamisaA = new JComboBox(Arquivo.nomesArquivos(pastas.camisas));
		cmbCamisaA.setBackground(Color.WHITE);
		cmbCamisaA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCamisaA.setBounds(80, 166, 200, 20);
		contentPane.add(cmbCamisaA);

		JSpinner spinRedCamisaA = new JSpinner();
		spinRedCamisaA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedCamisaA.setToolTipText("Cor vermelha da camisaA A");
		spinRedCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCamisaA.setBounds(290, 166, 50, 20);
		contentPane.add(spinRedCamisaA);
		
		JSpinner spinGreenCamisaA = new JSpinner();
		spinGreenCamisaA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenCamisaA.setToolTipText("Cor verde da camisaA A");
		spinGreenCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCamisaA.setBounds(350, 166, 50, 20);
		contentPane.add(spinGreenCamisaA);
		
		JSpinner spinBlueCamisaA = new JSpinner();
		spinBlueCamisaA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueCamisaA.setToolTipText("Cor azul da camisaA A");
		spinBlueCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCamisaA.setBounds(410, 166, 50, 20);
		contentPane.add(spinBlueCamisaA);
		
		JSpinner spinAlfaCamisaA = new JSpinner();
		spinAlfaCamisaA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinAlfaCamisaA.setToolTipText("Opacidade da camisaA A");
		spinAlfaCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCamisaA.setBounds(470, 166, 50, 20);
		contentPane.add(spinAlfaCamisaA);
		
		camisaA = new ParteSprite(cmbCamisaA, spinRedCamisaA, spinGreenCamisaA, spinBlueCamisaA, spinAlfaCamisaA);
		
		JLabel lblCamisaB = new JLabel("Camisa B");
		lblCamisaB.setBounds(10, 197, 60, 20);
		contentPane.add(lblCamisaB);
		
		JComboBox<String> cmbCamisaB = new JComboBox(Arquivo.nomesArquivos(pastas.camisas));
		cmbCamisaB.setBackground(Color.WHITE);
		cmbCamisaB.setBounds(80, 197, 200, 20);
		contentPane.add(cmbCamisaB);
		
		JSpinner spinRedCamisaB = new JSpinner();
		spinRedCamisaB.setToolTipText("Cor vermelha da camisaA B");
		spinRedCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCamisaB.setBounds(290, 197, 50, 20);
		contentPane.add(spinRedCamisaB);
		
		JSpinner spinGreenCamisaB = new JSpinner();
		spinGreenCamisaB.setToolTipText("Cor verde da camisaA B");
		spinGreenCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCamisaB.setBounds(350, 197, 50, 20);
		contentPane.add(spinGreenCamisaB);
		
		JSpinner spinBlueCamisaB = new JSpinner();
		spinBlueCamisaB.setToolTipText("Cor azul da camisaA B");
		spinBlueCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCamisaB.setBounds(410, 197, 50, 20);
		contentPane.add(spinBlueCamisaB);
		
		JSpinner spinAlfaCamisaB = new JSpinner();
		spinAlfaCamisaB.setToolTipText("Opacidade da camisaA B");
		spinAlfaCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCamisaB.setBounds(470, 197, 50, 20);
		contentPane.add(spinAlfaCamisaB);
		
		camisaB = new ParteSprite(cmbCamisaB, spinRedCamisaB, spinGreenCamisaB, spinBlueCamisaB, spinAlfaCamisaB);
		
		JLabel lblCalcaA = new JLabel("Calça A");
		lblCalcaA.setBounds(10, 228, 60, 20);
		contentPane.add(lblCalcaA);
		
		JComboBox<String> cmbCalcaA = new JComboBox(Arquivo.nomesArquivos(pastas.calcas));
		cmbCalcaA.setBackground(Color.WHITE);
		cmbCalcaA.setBounds(80, 228, 200, 20);
		contentPane.add(cmbCalcaA);
		
		JSpinner spinRedCalcaA = new JSpinner();
		spinRedCalcaA.setToolTipText("Cor vermelha da calça A");
		spinRedCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCalcaA.setBounds(290, 228, 50, 20);
		contentPane.add(spinRedCalcaA);
		
		JSpinner spinGreenCalcaA = new JSpinner();
		spinGreenCalcaA.setToolTipText("Cor verde da calça A");
		spinGreenCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCalcaA.setBounds(350, 228, 50, 20);
		contentPane.add(spinGreenCalcaA);
		
		JSpinner spinBlueCalcaA = new JSpinner();
		spinBlueCalcaA.setToolTipText("Cor azul da calça A");
		spinBlueCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCalcaA.setBounds(410, 228, 50, 20);
		contentPane.add(spinBlueCalcaA);
		
		JSpinner spinAlfaCalcaA = new JSpinner();
		spinAlfaCalcaA.setToolTipText("Opacidade da calça A");
		spinAlfaCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCalcaA.setBounds(470, 228, 50, 20);
		contentPane.add(spinAlfaCalcaA);
		
		calcaA = new ParteSprite(cmbCalcaA, spinRedCalcaA, spinGreenCalcaA, spinBlueCalcaA, spinAlfaCalcaA);
		
		JLabel lblCalcaB = new JLabel("Calça B");
		lblCalcaB.setBounds(10, 259, 60, 20);
		contentPane.add(lblCalcaB);
		
		JComboBox<String> cmbCalcaB = new JComboBox(Arquivo.nomesArquivos(pastas.calcas));
		cmbCalcaB.setBackground(Color.WHITE);
		cmbCalcaB.setBounds(80, 259, 200, 20);
		contentPane.add(cmbCalcaB);
		
		JSpinner spinRedCalcaB = new JSpinner();
		spinRedCalcaB.setToolTipText("Cor vermelha da calça B");
		spinRedCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCalcaB.setBounds(290, 259, 50, 20);
		contentPane.add(spinRedCalcaB);
		
		JSpinner spinGreenCalcaB = new JSpinner();
		spinGreenCalcaB.setToolTipText("Cor verde da calça B");
		spinGreenCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCalcaB.setBounds(350, 259, 50, 20);
		contentPane.add(spinGreenCalcaB);
		
		JSpinner spinBlueCalcaB = new JSpinner();
		spinBlueCalcaB.setToolTipText("Cor azul da calça B");
		spinBlueCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCalcaB.setBounds(410, 259, 50, 20);
		contentPane.add(spinBlueCalcaB);
		
		JSpinner spinAlfaCalcaB = new JSpinner();
		spinAlfaCalcaB.setToolTipText("Opacidade da calça B");
		spinAlfaCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCalcaB.setBounds(470, 259, 50, 20);
		contentPane.add(spinAlfaCalcaB);
		
		calcaB = new ParteSprite(cmbCalcaB, spinRedCalcaB, spinGreenCalcaB, spinBlueCalcaB, spinAlfaCalcaB);

		JLabel lblCostas = new JLabel("Costas");
		lblCostas.setBounds(10, 290, 60, 20);
		contentPane.add(lblCostas);

		JComboBox<String> cmbCostas = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
		cmbCostas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCostas.setBackground(Color.WHITE);
		cmbCostas.setBounds(80, 290, 200, 20);
		contentPane.add(cmbCostas);

		JSpinner spinRedCostas = new JSpinner();
		spinRedCostas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedCostas.setToolTipText("Cor vermelha do item das costas");
		spinRedCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCostas.setBounds(290, 290, 50, 20);
		contentPane.add(spinRedCostas);
		
		JSpinner spinGreenCostas = new JSpinner();
		spinGreenCostas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenCostas.setToolTipText("Cor verde do item das costas");
		spinGreenCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCostas.setBounds(350, 290, 50, 20);
		contentPane.add(spinGreenCostas);
		
		JSpinner spinBlueCostas = new JSpinner();
		spinBlueCostas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueCostas.setToolTipText("Cor azul do item das costas");
		spinBlueCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCostas.setBounds(410, 290, 50, 20);
		contentPane.add(spinBlueCostas);
		
		JSpinner spinAlfaCostas = new JSpinner();
		spinAlfaCostas.setToolTipText("Opacidade do item das costas");
		spinAlfaCostas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		spinAlfaCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCostas.setBounds(470, 290, 50, 20);
		contentPane.add(spinAlfaCostas);
		
		costas = new ParteSprite(cmbCostas, spinRedCostas, spinGreenCostas, spinBlueCostas, spinAlfaCostas);

		JButton btnAtualizarPastas = new JButton("Atualizar pastas");
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				atualizaPastas();
			}
		});
		
				JButton btnAleatorio = new JButton("Aleat\u00F3rio");
				btnAleatorio.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						spriteAleatorio();
					}
				});
				btnAleatorio.setBounds(530, 290, 192, 20);
				contentPane.add(btnAleatorio);
		btnAtualizarPastas.setBounds(530, 321, 192, 20);
		contentPane.add(btnAtualizarPastas);

		lblSprite = new JLabel("");
		lblSprite.setBackground(Color.WHITE);
		lblSprite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSprite.setBounds(530, 11, 192, 256);
		contentPane.add(lblSprite);

		JLabel lblNomeDoSprite = new JLabel("Nome do Sprite");
		lblNomeDoSprite.setBounds(10, 321, 90, 20);
		contentPane.add(lblNomeDoSprite);

		txtNomeSprite = new JTextField();
		txtNomeSprite.setBounds(110, 321, 290, 20);
		contentPane.add(txtNomeSprite);
		txtNomeSprite.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					salvarSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(410, 321, 110, 20);
		contentPane.add(btnSalvar);
	}

	//Atualiza o sprite com as partes selecionadas
	private void atualizaSprite() throws IOException {
		int[][] imagemCostas;
		try {
			imagemCostas = Arquivo.selecionarImagem(pastas.costas, costas);
		} catch (TamanhoErradoException e) {
			imagemCostas = e.tratar(Imagem.gerarTransparencia());
		}
		int[][] sprite = Imagem.capaAtras(imagemCostas);
		sprite = sobreporImagemArquivo(sprite, pastas.corpos, corpo);
		sprite = sobreporImagemArquivo(sprite, pastas.olhos, olhos);
		sprite = sobreporImagemArquivo(sprite, pastas.calcas, calcaB);
		sprite = sobreporImagemArquivo(sprite, pastas.camisas, camisaB);
		sprite = sobreporImagemArquivo(sprite, pastas.calcas, calcaA);
		sprite = sobreporImagemArquivo(sprite, pastas.camisas, camisaA);
		sprite = sobreporImagemArquivo(sprite, pastas.faces, face);
		sprite = Imagem.sobreporImagem(Imagem.capaFrente(imagemCostas), sprite);
		sprite = sobreporImagemArquivo(sprite, pastas.cabelos, cabelo);
		sprite = sobreporImagemArquivo(sprite, pastas.elmos, elmo);
		buffer = Imagem.matrizParaBuffer(sprite);
		lblSprite.setIcon(new ImageIcon(buffer.getScaledInstance(2 * Imagem.LARGURA, 2 * Imagem.ALTURA, Image.SCALE_AREA_AVERAGING)));
	}

	//Sobrepoe a imagem
	private static int[][] sobreporImagemArquivo(int[][] base, File[] array, ParteSprite parte)
			throws IOException {
		try {
			
			return Imagem.sobreporImagem(Arquivo.selecionarImagem(array, parte), base);
		} catch (TamanhoErradoException e) {
			return e.tratar(base);
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void atualizaPastas() {
		pastas = new Pastas();
		corpo.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));
		elmo.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
		cabelo.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
		olhos.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
		face.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
		camisaA.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.camisas));
		camisaB.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.camisas));
		calcaA.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.calcas));
		calcaB.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.calcas));
		costas.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
	}

	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteAleatorio() {
		deveAtualizar = false;
		corpo.cmb.setSelectedIndex(random.nextInt(corpo.cmb.getItemCount()));
		elmo.cmb.setSelectedIndex(random.nextInt(elmo.cmb.getItemCount()));
		cabelo.cmb.setSelectedIndex(random.nextInt(cabelo.cmb.getItemCount()));
		olhos.cmb.setSelectedIndex(random.nextInt(olhos.cmb.getItemCount()));
		face.cmb.setSelectedIndex(random.nextInt(face.cmb.getItemCount()));
		camisaA.cmb.setSelectedIndex(random.nextInt(camisaA.cmb.getItemCount()));
		camisaB.cmb.setSelectedIndex(random.nextInt(camisaB.cmb.getItemCount()));
		calcaA.cmb.setSelectedIndex(random.nextInt(calcaA.cmb.getItemCount()));
		calcaB.cmb.setSelectedIndex(random.nextInt(calcaB.cmb.getItemCount()));
		deveAtualizar = true;
		costas.cmb.setSelectedIndex(random.nextInt(costas.cmb.getItemCount()));
	}

	private void salvarSprite() throws HeadlessException, IOException {
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + Arquivo.salvarSprite(txtNomeSprite.getText(), buffer) + "\"");
	}
}
