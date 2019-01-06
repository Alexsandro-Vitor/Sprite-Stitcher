package gui;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.image.BufferedImage;

import java.io.File;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import classes.Dimensoes;
import classes.ParteSprite;
import classes.Pastas;
import excecoes.TamanhoErradoException;
import funcoes.*;

import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Gerador extends JFrame {

	private Sprite sprite;
	private String pastaArquivos;
	private Pastas pastas;
	private BufferedImage buffer;
	private static Random random = new Random();
	private ParteSprite corpo;
	private ParteSprite elmo;
	private ParteSprite cabelo;
	private ParteSprite olhos;
	private ParteSprite face;
	private ParteSprite camisaA;
	private ParteSprite camisaB;
	private ParteSprite maos;
	private ParteSprite calcaA;
	private ParteSprite calcaB;
	private ParteSprite costas;
	private ParteSprite pes;
	private boolean deveAtualizar = true;
	private boolean rgba = true;

	private JPanel contentPane;
	private JTextField txtNomeSprite;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Gerador(Sprite sprite, String pastaArquivos) {
		this.sprite = sprite;
		this.pastaArquivos = pastaArquivos;
		this.pastas = new Pastas(pastaArquivos);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 676, 443);
		setResizable(false);
		setTitle("Gerador de Sprite");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCorpo = new JLabel("Corpo");
		lblCorpo.setBounds(10, 11, 60, 20);
		contentPane.add(lblCorpo);

		JComboBox<String> cmbCorpo = new JComboBox(Leitura.nomesArquivos(pastas.corpos));
		corpo = new ParteSprite("corpo", cmbCorpo, null, null, null, null);

		cmbCorpo.setBackground(Color.WHITE);
		cmbCorpo.addItemListener(itemListener);
		cmbCorpo.setBounds(80, 11, 200, 20);
		contentPane.add(cmbCorpo);

		JLabel lblVermelho = new JLabel("Vermelho");
		lblVermelho.setHorizontalAlignment(SwingConstants.CENTER);
		lblVermelho.setBounds(290, 11, 60, 20);
		contentPane.add(lblVermelho);

		JLabel lblVerde = new JLabel("Verde");
		lblVerde.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerde.setBounds(360, 11, 60, 20);
		contentPane.add(lblVerde);

		JLabel lblAzul = new JLabel("Azul");
		lblAzul.setHorizontalAlignment(SwingConstants.CENTER);
		lblAzul.setBounds(430, 11, 60, 20);
		contentPane.add(lblAzul);

		JLabel lblAlfa = new JLabel("Alfa");
		lblAlfa.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlfa.setBounds(500, 11, 60, 20);
		contentPane.add(lblAlfa);

		JButton btnMSLA = new JButton("MSLA");
		btnMSLA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rgba = !rgba;
				if (rgba) {
					btnMSLA.setText("MSLA");
					lblVermelho.setText("Vermelho");
					lblVerde.setText("Verde");
					lblAzul.setText("Azul");
				} else {
					btnMSLA.setText("RGBA");
					lblVermelho.setText("Matiz");
					lblVerde.setText("Saturação");
					lblAzul.setText("Luz");
				}
				deveAtualizar = false;
				elmo.setRGBA(rgba);
				cabelo.setRGBA(rgba);
				olhos.setRGBA(rgba);
				face.setRGBA(rgba);
				camisaA.setRGBA(rgba);
				camisaB.setRGBA(rgba);
				maos.setRGBA(rgba);
				calcaA.setRGBA(rgba);
				calcaB.setRGBA(rgba);
				costas.setRGBA(rgba);
				pes.setRGBA(rgba);
				atualizaSprite();
				deveAtualizar = true;
			}
		});
		btnMSLA.setBounds(570, 11, 90, 20);
		contentPane.add(btnMSLA);

		JLabel lblElmo = new JLabel("Elmo");
		lblElmo.setBounds(10, 42, 60, 20);
		contentPane.add(lblElmo);
		
		JComboBox<String> cmbElmo = new JComboBox(Leitura.nomesArquivos(pastas.elmos));
		JSpinner spinElmoRed = new JSpinner();
		JSpinner spinElmoGreen = new JSpinner();
		JSpinner spinElmoBlue = new JSpinner();
		JSpinner spinElmoAlfa = new JSpinner();
		elmo = new ParteSprite("elmo", cmbElmo, spinElmoRed, spinElmoGreen, spinElmoBlue, spinElmoAlfa);
		configParteSprite(elmo);
		
		cmbElmo.setBackground(Color.WHITE);
		cmbElmo.setBounds(80, 42, 200, 20);
		contentPane.add(cmbElmo);

		spinElmoRed.setBounds(290, 42, 60, 20);
		contentPane.add(spinElmoRed);

		spinElmoGreen.setBounds(360, 42, 60, 20);
		contentPane.add(spinElmoGreen);

		spinElmoBlue.setBounds(430, 42, 60, 20);
		contentPane.add(spinElmoBlue);

		spinElmoAlfa.setBounds(500, 42, 60, 20);
		contentPane.add(spinElmoAlfa);

		JButton btnElmoAleatorio = new JButton("Aleat\u00F3rio");
		btnElmoAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				corParteAleatoria(elmo);
			}
		});
		btnElmoAleatorio.setBounds(570, 42, 90, 20);
		contentPane.add(btnElmoAleatorio);

		JLabel lblCabelo = new JLabel("Cabelo");
		lblCabelo.setBounds(10, 73, 60, 20);
		contentPane.add(lblCabelo);

		JComboBox<String> cmbCabelo = new JComboBox(Leitura.nomesArquivos(pastas.cabelos));
		JSpinner spinCabeloRed = new JSpinner();
		JSpinner spinCabeloGreen = new JSpinner();
		JSpinner spinCabeloBlue = new JSpinner();
		cabelo = new ParteSprite("cabelo", cmbCabelo, spinCabeloRed, spinCabeloGreen, spinCabeloBlue, null);
		configParteSprite(cabelo);
		
		cmbCabelo.setBackground(Color.WHITE);
		cmbCabelo.setBounds(80, 73, 200, 20);
		contentPane.add(cmbCabelo);

		spinCabeloRed.setBounds(290, 73, 60, 20);
		contentPane.add(cabelo.red);

		spinCabeloGreen.setBounds(360, 73, 60, 20);
		contentPane.add(cabelo.green);

		spinCabeloBlue.setBounds(430, 73, 60, 20);
		contentPane.add(cabelo.blue);

		JButton btnCabeloAleatorio = new JButton("Aleat\u00F3rio");
		btnCabeloAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(cabelo);
			}
		});
		btnCabeloAleatorio.setBounds(570, 73, 90, 20);
		contentPane.add(btnCabeloAleatorio);

		JLabel lblOlhos = new JLabel("Olhos");
		lblOlhos.setBounds(10, 104, 60, 20);
		contentPane.add(lblOlhos);

		JComboBox<String> cmbOlhos = new JComboBox(Leitura.nomesArquivos(pastas.olhos));
		JSpinner spinOlhosRed = new JSpinner();
		JSpinner spinOlhosGreen = new JSpinner();
		JSpinner spinOlhosBlue = new JSpinner();
		olhos = new ParteSprite("olhos", cmbOlhos, spinOlhosRed, spinOlhosGreen, spinOlhosBlue, null);
		configParteSprite(olhos);
		
		cmbOlhos.setBackground(Color.WHITE);
		cmbOlhos.setBounds(80, 104, 200, 20);
		contentPane.add(cmbOlhos);

		spinOlhosRed.setBounds(290, 104, 60, 20);
		contentPane.add(spinOlhosRed);

		spinOlhosGreen.setBounds(360, 104, 60, 20);
		contentPane.add(spinOlhosGreen);

		spinOlhosBlue.setBounds(430, 104, 60, 20);
		contentPane.add(spinOlhosBlue);

		JButton btnOlhosAleatorio = new JButton("Aleat\u00F3rio");
		btnOlhosAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(olhos);
			}
		});
		btnOlhosAleatorio.setBounds(570, 104, 90, 20);
		contentPane.add(btnOlhosAleatorio);

		JLabel lblFace = new JLabel("Face");
		lblFace.setBounds(10, 135, 60, 20);
		contentPane.add(lblFace);

		JComboBox<String> cmbFace = new JComboBox(Leitura.nomesArquivos(pastas.faces));
		JSpinner spinFaceRed = new JSpinner();
		JSpinner spinFaceGreen = new JSpinner();
		JSpinner spinFaceBlue = new JSpinner();
		JSpinner spinFaceAlfa = new JSpinner();
		face = new ParteSprite("face", cmbFace, spinFaceRed, spinFaceGreen, spinFaceBlue, spinFaceAlfa);
		configParteSprite(face);
		
		cmbFace.setBackground(Color.WHITE);
		cmbFace.setBounds(80, 135, 200, 20);
		contentPane.add(cmbFace);

		spinFaceRed.setBounds(290, 135, 60, 20);
		contentPane.add(spinFaceRed);

		spinFaceGreen.setBounds(360, 135, 60, 20);
		contentPane.add(spinFaceGreen);

		spinFaceBlue.setBounds(430, 135, 60, 20);
		contentPane.add(spinFaceBlue);

		spinFaceAlfa.setBounds(500, 135, 60, 20);
		contentPane.add(spinFaceAlfa);

		JButton btnFaceAleatorio = new JButton("Aleat\u00F3rio");
		btnFaceAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(face);
			}
		});
		btnFaceAleatorio.setBounds(570, 135, 90, 20);
		contentPane.add(btnFaceAleatorio);

		JLabel lblCamisaA = new JLabel("Camisa A");
		lblCamisaA.setBounds(10, 166, 60, 20);
		contentPane.add(lblCamisaA);

		JComboBox<String> cmbCamisaA = new JComboBox(Leitura.nomesArquivos(pastas.camisas));
		JSpinner spinCamisaARed = new JSpinner();
		JSpinner spinCamisaAGreen = new JSpinner();
		JSpinner spinCamisaABlue = new JSpinner();
		JSpinner spinCamisaAAlfa = new JSpinner();
		camisaA = new ParteSprite("camisa A", cmbCamisaA, spinCamisaARed, spinCamisaAGreen, spinCamisaABlue, spinCamisaAAlfa);
		configParteSprite(camisaA);
		
		cmbCamisaA.setBackground(Color.WHITE);
		cmbCamisaA.setBounds(80, 166, 200, 20);
		contentPane.add(cmbCamisaA);

		spinCamisaARed.setBounds(290, 166, 60, 20);
		contentPane.add(spinCamisaARed);

		spinCamisaAGreen.setBounds(360, 166, 60, 20);
		contentPane.add(spinCamisaAGreen);

		spinCamisaABlue.setBounds(430, 166, 60, 20);
		contentPane.add(spinCamisaABlue);

		spinCamisaAAlfa.setBounds(500, 166, 60, 20);
		contentPane.add(spinCamisaAAlfa);
		
		JButton btnCamisaAAleatorio = new JButton("Aleat\u00F3rio");
		btnCamisaAAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(camisaA);
			}
		});
		btnCamisaAAleatorio.setBounds(570, 166, 90, 20);
		contentPane.add(btnCamisaAAleatorio);

		JLabel lblCamisaB = new JLabel("Camisa B");
		lblCamisaB.setBounds(10, 197, 60, 20);
		contentPane.add(lblCamisaB);

		JComboBox<String> cmbCamisaB = new JComboBox(Leitura.nomesArquivos(pastas.camisas));
		JSpinner spinCamisaBRed = new JSpinner();
		JSpinner spinCamisaBGreen = new JSpinner();
		JSpinner spinCamisaBBlue = new JSpinner();
		JSpinner spinCamisaBAlfa = new JSpinner();
		camisaB = new ParteSprite("camisa B", cmbCamisaB, spinCamisaBRed, spinCamisaBGreen, spinCamisaBBlue, spinCamisaBAlfa);
		configParteSprite(camisaB);
		
		cmbCamisaB.setBackground(Color.WHITE);
		cmbCamisaB.setBounds(80, 197, 200, 20);
		contentPane.add(cmbCamisaB);

		spinCamisaBRed.setBounds(290, 197, 60, 20);
		contentPane.add(spinCamisaBRed);

		spinCamisaBGreen.setBounds(360, 197, 60, 20);
		contentPane.add(spinCamisaBGreen);

		spinCamisaBBlue.setBounds(430, 197, 60, 20);
		contentPane.add(spinCamisaBBlue);

		spinCamisaBAlfa.setBounds(500, 197, 60, 20);
		contentPane.add(spinCamisaBAlfa);

		JButton btnCamisaBAleatorio = new JButton("Aleat\u00F3rio");
		btnCamisaBAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(camisaB);
			}
		});
		btnCamisaBAleatorio.setBounds(570, 197, 90, 20);
		contentPane.add(btnCamisaBAleatorio);

		JLabel lblMaos = new JLabel("M\u00E3os");
		lblMaos.setBounds(10, 228, 60, 20);
		contentPane.add(lblMaos);

		JComboBox<String> cmbMaos = new JComboBox(Leitura.nomesArquivos(pastas.maos));
		JSpinner spinMaosRed = new JSpinner();
		JSpinner spinMaosGreen = new JSpinner();
		JSpinner spinMaosBlue = new JSpinner();
		JSpinner spinMaosAlfa = new JSpinner();
		maos = new ParteSprite("mãos", cmbMaos, spinMaosRed, spinMaosGreen, spinMaosBlue, spinMaosAlfa);
		configParteSprite(maos);
		
		cmbMaos.setBackground(Color.WHITE);
		cmbMaos.setBounds(80, 228, 200, 20);
		contentPane.add(cmbMaos);

		spinMaosRed.setBounds(290, 228, 60, 20);
		contentPane.add(spinMaosRed);

		spinMaosGreen.setBounds(360, 228, 60, 20);
		contentPane.add(spinMaosGreen);

		spinMaosBlue.setBounds(430, 228, 60, 20);
		contentPane.add(spinMaosBlue);

		spinMaosAlfa.setBounds(500, 228, 60, 20);
		contentPane.add(spinMaosAlfa);

		JButton btnMaosAleatorio = new JButton("Aleat\u00F3rio");
		btnMaosAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(maos);
			}
		});
		btnMaosAleatorio.setBounds(570, 228, 90, 20);
		contentPane.add(btnMaosAleatorio);

		JLabel lblCalcaA = new JLabel("Calça A");
		lblCalcaA.setBounds(10, 259, 60, 20);
		contentPane.add(lblCalcaA);

		JComboBox<String> cmbCalcaA = new JComboBox(Leitura.nomesArquivos(pastas.calcas));
		JSpinner spinCalcaARed = new JSpinner();
		JSpinner spinCalcaAGreen = new JSpinner();
		JSpinner spinCalcaABlue = new JSpinner();
		JSpinner spinCalcaAAlfa = new JSpinner();
		calcaA = new ParteSprite("calça A", cmbCalcaA, spinCalcaARed, spinCalcaAGreen, spinCalcaABlue, spinCalcaAAlfa);
		configParteSprite(calcaA);
		
		cmbCalcaA.setBackground(Color.WHITE);
		cmbCalcaA.setBounds(80, 259, 200, 20);
		contentPane.add(cmbCalcaA);

		spinCalcaARed.setBounds(290, 259, 60, 20);
		contentPane.add(spinCalcaARed);

		spinCalcaAGreen.setBounds(360, 259, 60, 20);
		contentPane.add(spinCalcaAGreen);

		spinCalcaABlue.setBounds(430, 259, 60, 20);
		contentPane.add(spinCalcaABlue);

		spinCalcaAAlfa.setBounds(500, 259, 60, 20);
		contentPane.add(spinCalcaAAlfa);

		JButton btnCalcaAAleatorio = new JButton("Aleat\u00F3rio");
		btnCalcaAAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(calcaA);
			}
		});
		btnCalcaAAleatorio.setBounds(570, 259, 90, 20);
		contentPane.add(btnCalcaAAleatorio);

		JLabel lblCalcaB = new JLabel("Calça B");
		lblCalcaB.setBounds(10, 290, 60, 20);
		contentPane.add(lblCalcaB);

		JComboBox<String> cmbCalcaB = new JComboBox(Leitura.nomesArquivos(pastas.calcas));
		JSpinner spinCalcaBRed = new JSpinner();
		JSpinner spinCalcaBGreen = new JSpinner();
		JSpinner spinCalcaBBlue = new JSpinner();
		JSpinner spinCalcaBAlfa = new JSpinner();
		calcaB = new ParteSprite("calça B", cmbCalcaB, spinCalcaBRed, spinCalcaBGreen, spinCalcaBBlue, spinCalcaBAlfa);
		configParteSprite(calcaB);
		
		cmbCalcaB.setBounds(80, 290, 200, 20);
		contentPane.add(cmbCalcaB);

		spinCalcaBRed.setBounds(290, 290, 60, 20);
		contentPane.add(spinCalcaBRed);

		spinCalcaBGreen.setBounds(360, 290, 60, 20);
		contentPane.add(spinCalcaBGreen);

		spinCalcaBBlue.setBounds(430, 290, 60, 20);
		contentPane.add(spinCalcaBBlue);

		spinCalcaBAlfa.setBounds(500, 290, 60, 20);
		contentPane.add(spinCalcaBAlfa);

		JButton btnCalcaBAleatorio = new JButton("Aleat\u00F3rio");
		btnCalcaBAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(calcaB);
			}
		});
		btnCalcaBAleatorio.setBounds(570, 290, 90, 20);
		contentPane.add(btnCalcaBAleatorio);

		JLabel lblCostas = new JLabel("Costas");
		lblCostas.setBounds(10, 321, 60, 20);
		contentPane.add(lblCostas);

		JComboBox<String> cmbCostas = new JComboBox(Leitura.nomesArquivos(pastas.costas));
		JSpinner spinCostasRed = new JSpinner();
		JSpinner spinCostasGreen = new JSpinner();
		JSpinner spinCostasBlue = new JSpinner();
		JSpinner spinCostasAlfa = new JSpinner();
		costas = new ParteSprite("costas", cmbCostas, spinCostasRed, spinCostasGreen, spinCostasBlue, spinCostasAlfa);
		configParteSprite(costas);
		
		cmbCostas.setBackground(Color.WHITE);
		cmbCostas.setBounds(80, 321, 200, 20);
		contentPane.add(cmbCostas);

		spinCostasRed.setBounds(290, 321, 60, 20);
		contentPane.add(spinCostasRed);

		spinCostasGreen.setBounds(360, 321, 60, 20);
		contentPane.add(spinCostasGreen);

		spinCostasBlue.setBounds(430, 321, 60, 20);
		contentPane.add(spinCostasBlue);

		spinCostasAlfa.setBounds(500, 321, 60, 20);
		contentPane.add(spinCostasAlfa);

		JButton btnCostasAleatorio = new JButton("Aleat\u00F3rio");
		btnCostasAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(costas);
			}
		});
		btnCostasAleatorio.setBounds(570, 321, 90, 20);
		contentPane.add(btnCostasAleatorio);

		JLabel lblPes = new JLabel("P\u00E9s");
		lblPes.setBounds(10, 352, 60, 20);
		contentPane.add(lblPes);

		JComboBox<String> cmbPes = new JComboBox(Leitura.nomesArquivos(pastas.pes));
		JSpinner spinPesRed = new JSpinner();
		JSpinner spinPesGreen = new JSpinner();
		JSpinner spinPesBlue = new JSpinner();
		JSpinner spinPesAlfa = new JSpinner();
		pes = new ParteSprite("pes", cmbPes, spinPesRed, spinPesGreen, spinPesBlue, spinPesAlfa);
		configParteSprite(pes);
		
		cmbPes.setBackground(Color.WHITE);
		cmbPes.setBounds(80, 352, 200, 20);
		contentPane.add(cmbPes);

		spinPesRed.setBounds(290, 352, 60, 20);
		contentPane.add(spinPesRed);

		spinPesGreen.setBounds(360, 352, 60, 20);
		contentPane.add(spinPesGreen);

		spinPesBlue.setBounds(430, 352, 60, 20);
		contentPane.add(spinPesBlue);

		spinPesAlfa.setBounds(500, 352, 60, 20);
		contentPane.add(spinPesAlfa);

		JButton btnPesAleatorio = new JButton("Aleat\u00F3rio");
		btnPesAleatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(pes);
			}
		});
		btnPesAleatorio.setBounds(570, 352, 90, 20);
		contentPane.add(btnPesAleatorio);

		JButton btnAtualizarPastas = new JButton("Atualizar pastas");
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				atualizaPastas();
			}
		});

		JButton btnAleatorio = new JButton("Pe\u00E7as Aleat\u00F3rias");
		btnAleatorio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteAleatorio();
			}
		});
		btnAleatorio.setBounds(520, 383, 140, 20);
		contentPane.add(btnAleatorio);
		btnAtualizarPastas.setBounds(380, 383, 130, 20);
		contentPane.add(btnAtualizarPastas);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 383, 60, 20);
		contentPane.add(lblNome);

		txtNomeSprite = new JTextField();
		txtNomeSprite.setBounds(80, 383, 200, 20);
		contentPane.add(txtNomeSprite);
		txtNomeSprite.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				salvarSprite();
			}
		});
		btnSalvar.setBounds(290, 383, 80, 20);
		contentPane.add(btnSalvar);
	}
	
	void configParteSprite(ParteSprite parte) {
		parte.cmb.addItemListener(itemListener);
		parte.red.setToolTipText("Cor vermelha do " + parte.nome);
		parte.red.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		parte.red.addChangeListener(changeListener);
		parte.green.setToolTipText("Cor verde do " + parte.nome);
		parte.green.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		parte.green.addChangeListener(changeListener);
		parte.blue.setToolTipText("Cor azul do " + parte.nome);
		parte.blue.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		parte.blue.addChangeListener(changeListener);
		if (parte.alfa != null) {
			parte.alfa.setToolTipText("Opacidade do " + parte.nome);
			parte.alfa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
			parte.alfa.addChangeListener(changeListener);
		}
	}

	ItemListener itemListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (deveAtualizar) atualizaSprite();
		}
	};

	ChangeListener changeListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if (deveAtualizar) atualizaSprite();
		}
	};

	//Atualiza o sprite com as partes selecionadas
	private void atualizaSprite() {
		int[][] sprite;
		costas.atualizaCor(rgba);
		try {
			sprite = Imagem.capaAtras(Leitura.selecionarImagem(pastas.costas, costas));
		} catch (TamanhoErradoException e) {
			sprite = Imagem.gerarTransparencia();
		}
		System.out.println("Tempos:");
		long tempo = System.nanoTime();
		corpo.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.corpos, corpo);
		System.out.println("Sobrepor corpo: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		olhos.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.olhos, olhos);
		System.out.println("Sobrepor olhos: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		calcaB.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.calcas, calcaB);
		System.out.println("Sobrepor calcasB: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		camisaB.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.camisas, camisaB);
		System.out.println("Sobrepor camisaB: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		maos.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.maos, maos);
		System.out.println("Sobrepor maos: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		pes.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.pes, pes);
		System.out.println("Sobrepor pes: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		calcaA.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.calcas, calcaA);
		System.out.println("Sobrepor calcaA: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		camisaA.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.camisas, camisaA);
		System.out.println("Sobrepor camisaA: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		face.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.faces, face);
		System.out.println("Sobrepor face: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		try {
			sprite = Imagem.sobreporImagem(Imagem.capaFrente(Leitura.selecionarImagem(pastas.costas, costas)), sprite);
		} catch (TamanhoErradoException e) {}
		System.out.println("Sobrepor costas: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		cabelo.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.cabelos, cabelo);
		System.out.println("Sobrepor cabelo: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		elmo.atualizaCor(rgba);
		sprite = sobreporImagemArquivo(sprite, pastas.elmos, elmo);
		System.out.println("Sobrepor elmo: " + (System.nanoTime()-tempo));
		
		buffer = Imagem.matrizParaBuffer(sprite);
		this.sprite.label.setIcon(
			new ImageIcon(
				buffer.getScaledInstance(
					Dimensoes.ZOOM * Dimensoes.LARGURA,
					Dimensoes.ZOOM * Dimensoes.ALTURA,
					Image.SCALE_AREA_AVERAGING
				)
			)
		);
	}

	//Sobrepoe a imagem
	private int[][] sobreporImagemArquivo(int[][] base, File[] array, ParteSprite parte) {
		if (parte.cmb.getSelectedIndex() == 0) return base;
		try {
			int[][] imagem = Leitura.selecionarImagem(array, parte);
			return Imagem.sobreporImagem(imagem, base);
		} catch (TamanhoErradoException e) {
			return e.tratar(base);
		}
	}

	private void atualizaPastas() {
		pastas = new Pastas(pastaArquivos);
		atualizaCmb(corpo, pastas.corpos);
		atualizaCmb(elmo, pastas.elmos);
		atualizaCmb(cabelo, pastas.cabelos);
		atualizaCmb(olhos, pastas.olhos);
		atualizaCmb(face, pastas.faces);
		atualizaCmb(camisaA, pastas.camisas);
		atualizaCmb(camisaB, pastas.camisas);
		atualizaCmb(maos, pastas.maos);
		atualizaCmb(calcaA, pastas.calcas);
		atualizaCmb(calcaB, pastas.calcas);
		atualizaCmb(costas, pastas.costas);
		atualizaCmb(pes, pastas.pes);
		JOptionPane.showMessageDialog(null, "Pastas atualizadas");
	}

	void atualizaCmb(ParteSprite parte, File[] arq) {
		JComboBox<String> novo = new JComboBox<String>(Leitura.nomesArquivos(arq));
		novo.addItemListener(itemListener);
		novo.setBackground(Color.WHITE);
		novo.setBounds(parte.cmb.getBounds());
		contentPane.remove(parte.cmb);
		contentPane.add(novo);
		parte.cmb = novo;
	}

	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteAleatorio() {
		deveAtualizar = false;
		selecaoItemAleatorio(corpo.cmb);
		selecaoItemAleatorio(elmo.cmb);
		selecaoItemAleatorio(cabelo.cmb);
		selecaoItemAleatorio(olhos.cmb);
		selecaoItemAleatorio(face.cmb);
		selecaoItemAleatorio(camisaA.cmb);
		selecaoItemAleatorio(camisaB.cmb);
		selecaoItemAleatorio(maos.cmb);
		selecaoItemAleatorio(calcaA.cmb);
		selecaoItemAleatorio(calcaB.cmb);
		selecaoItemAleatorio(costas.cmb);
		selecaoItemAleatorio(pes.cmb);
		atualizaSprite();
		deveAtualizar = true;
	}

	private void corParteAleatoria(ParteSprite parte) {
		deveAtualizar = false;
		parte.red.setValue(random.nextInt((Integer) ((SpinnerNumberModel) parte.red.getModel()).getMaximum() + 1));
		parte.green.setValue(random.nextInt(256));
		parte.blue.setValue(random.nextInt(256));
		atualizaSprite();
		deveAtualizar = true;
	}

	@SuppressWarnings("rawtypes")
	private void selecaoItemAleatorio(JComboBox cmb) {
		cmb.setSelectedIndex(random.nextInt(cmb.getItemCount()));
	}

	private void salvarSprite() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + Escrita.salvarSprite(pastas, txtNomeSprite.getText(), buffer) + "\"");
	}
}
