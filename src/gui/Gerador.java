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
	private Imagem imagem;
	private Arquivo arquivo;
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
		this.imagem = new Imagem((short)(sprite.label.getWidth() / 2), (short)(sprite.label.getHeight() / 2));
		this.arquivo = new Arquivo(imagem);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 676, 443);
		setResizable(false);
		setTitle("Gerador de Sprite");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 127));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCorpo = new JLabel("Corpo");
		lblCorpo.setBounds(10, 11, 60, 20);
		contentPane.add(lblCorpo);

		JComboBox<String> cmbCorpo = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));

		cmbCorpo.setBackground(Color.WHITE);
		cmbCorpo.addItemListener(itemListener);
		cmbCorpo.setBounds(80, 11, 200, 20);
		contentPane.add(cmbCorpo);

		corpo = new ParteSprite("corpo", cmbCorpo, null, null, null, null);

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

		JComboBox<String> cmbElmo = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
		cmbElmo.addItemListener(itemListener);
		cmbElmo.setBackground(Color.WHITE);
		cmbElmo.setBounds(80, 42, 200, 20);
		contentPane.add(cmbElmo);

		JSpinner spinRedElmo = new JSpinner();
		spinRedElmo.addChangeListener(changeListener);
		spinRedElmo.setToolTipText("Cor vermelha do elmo");
		spinRedElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedElmo.setBounds(290, 42, 60, 20);
		contentPane.add(spinRedElmo);

		JSpinner spinGreenElmo = new JSpinner();
		spinGreenElmo.addChangeListener(changeListener);
		spinGreenElmo.setToolTipText("Cor verde do elmo");
		spinGreenElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenElmo.setBounds(360, 42, 60, 20);
		contentPane.add(spinGreenElmo);

		JSpinner spinBlueElmo = new JSpinner();
		spinBlueElmo.addChangeListener(changeListener);
		spinBlueElmo.setToolTipText("Cor azul do elmo");
		spinBlueElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueElmo.setBounds(430, 42, 60, 20);
		contentPane.add(spinBlueElmo);

		JSpinner spinAlfaElmo = new JSpinner();
		spinAlfaElmo.setToolTipText("Opacidade do elmo");
		spinAlfaElmo.addChangeListener(changeListener);
		spinAlfaElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaElmo.setBounds(500, 42, 60, 20);
		contentPane.add(spinAlfaElmo);

		elmo = new ParteSprite("elmo", cmbElmo, spinRedElmo, spinGreenElmo, spinBlueElmo, spinAlfaElmo);

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

		JComboBox<String> cmbCabelo = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
		cmbCabelo.setBackground(Color.WHITE);
		cmbCabelo.addItemListener(itemListener);
		cmbCabelo.setBounds(80, 73, 200, 20);
		contentPane.add(cmbCabelo);

		JSpinner spinRedCabelo = new JSpinner();
		spinRedCabelo.addChangeListener(changeListener);
		spinRedCabelo.setToolTipText("Cor vermelha do cabelo");
		spinRedCabelo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCabelo.setBounds(290, 73, 60, 20);
		contentPane.add(spinRedCabelo);

		JSpinner spinGreenCabelo = new JSpinner();
		spinGreenCabelo.addChangeListener(changeListener);
		spinGreenCabelo.setToolTipText("Cor verde do cabelo");
		spinGreenCabelo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCabelo.setBounds(360, 73, 60, 20);
		contentPane.add(spinGreenCabelo);

		JSpinner spinBlueCabelo = new JSpinner();
		spinBlueCabelo.addChangeListener(changeListener);
		spinBlueCabelo.setToolTipText("Cor azul do cabelo");
		spinBlueCabelo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCabelo.setBounds(430, 73, 60, 20);
		contentPane.add(spinBlueCabelo);

		cabelo = new ParteSprite("cabelo", cmbCabelo, spinRedCabelo, spinGreenCabelo, spinBlueCabelo, null);

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

		JComboBox<String> cmbOlhos = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
		cmbOlhos.setBackground(Color.WHITE);
		cmbOlhos.addItemListener(itemListener);
		cmbOlhos.setBounds(80, 104, 200, 20);
		contentPane.add(cmbOlhos);

		JSpinner spinRedOlhos = new JSpinner();
		spinRedOlhos.addChangeListener(changeListener);
		spinRedOlhos.setToolTipText("Cor vermelha dos olhos");
		spinRedOlhos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedOlhos.setBounds(290, 104, 60, 20);
		contentPane.add(spinRedOlhos);

		JSpinner spinGreenOlhos = new JSpinner();
		spinGreenOlhos.addChangeListener(changeListener);
		spinGreenOlhos.setToolTipText("Cor verde dos olhos");
		spinGreenOlhos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenOlhos.setBounds(360, 104, 60, 20);
		contentPane.add(spinGreenOlhos);

		JSpinner spinBlueOlhos = new JSpinner();
		spinBlueOlhos.addChangeListener(changeListener);
		spinBlueOlhos.setToolTipText("Cor azul dos olhos");
		spinBlueOlhos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueOlhos.setBounds(430, 104, 60, 20);
		contentPane.add(spinBlueOlhos);

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

		olhos = new ParteSprite("olhos", cmbOlhos, spinRedOlhos, spinGreenOlhos, spinBlueOlhos, null);

		JComboBox<String> cmbFace = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
		cmbFace.addItemListener(itemListener);
		cmbFace.setBackground(Color.WHITE);
		cmbFace.setBounds(80, 135, 200, 20);
		contentPane.add(cmbFace);

		JSpinner spinRedFace = new JSpinner();
		spinRedFace.addChangeListener(changeListener);
		spinRedFace.setToolTipText("Cor vermelha do item da face");
		spinRedFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedFace.setBounds(290, 135, 60, 20);
		contentPane.add(spinRedFace);

		JSpinner spinGreenFace = new JSpinner();
		spinGreenFace.addChangeListener(changeListener);
		spinGreenFace.setToolTipText("Cor verde do item da face");
		spinGreenFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenFace.setBounds(360, 135, 60, 20);
		contentPane.add(spinGreenFace);

		JSpinner spinBlueFace = new JSpinner();
		spinBlueFace.addChangeListener(changeListener);
		spinBlueFace.setToolTipText("Cor azul do item da face");
		spinBlueFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueFace.setBounds(430, 135, 60, 20);
		contentPane.add(spinBlueFace);

		JSpinner spinAlfaFace = new JSpinner();
		spinAlfaFace.setToolTipText("Opacidade do item da face");
		spinAlfaFace.addChangeListener(changeListener);
		spinAlfaFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaFace.setBounds(500, 135, 60, 20);
		contentPane.add(spinAlfaFace);

		face = new ParteSprite("face", cmbFace, spinRedFace, spinGreenFace, spinBlueFace, spinAlfaFace);

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

		JComboBox<String> cmbCamisaA = new JComboBox(Arquivo.nomesArquivos(pastas.camisas));
		cmbCamisaA.setBackground(Color.WHITE);
		cmbCamisaA.addItemListener(itemListener);
		cmbCamisaA.setBounds(80, 166, 200, 20);
		contentPane.add(cmbCamisaA);

		JSpinner spinRedCamisaA = new JSpinner();
		spinRedCamisaA.addChangeListener(changeListener);
		spinRedCamisaA.setToolTipText("Cor vermelha da camisaA A");
		spinRedCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCamisaA.setBounds(290, 166, 60, 20);
		contentPane.add(spinRedCamisaA);

		JSpinner spinGreenCamisaA = new JSpinner();
		spinGreenCamisaA.addChangeListener(changeListener);
		spinGreenCamisaA.setToolTipText("Cor verde da camisaA A");
		spinGreenCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCamisaA.setBounds(360, 166, 60, 20);
		contentPane.add(spinGreenCamisaA);

		JSpinner spinBlueCamisaA = new JSpinner();
		spinBlueCamisaA.addChangeListener(changeListener);
		spinBlueCamisaA.setToolTipText("Cor azul da camisaA A");
		spinBlueCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCamisaA.setBounds(430, 166, 60, 20);
		contentPane.add(spinBlueCamisaA);

		JSpinner spinAlfaCamisaA = new JSpinner();
		spinAlfaCamisaA.addChangeListener(changeListener);
		spinAlfaCamisaA.setToolTipText("Opacidade da camisaA A");
		spinAlfaCamisaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCamisaA.setBounds(500, 166, 60, 20);
		contentPane.add(spinAlfaCamisaA);

		camisaA = new ParteSprite("camisa A", cmbCamisaA, spinRedCamisaA, spinGreenCamisaA, spinBlueCamisaA, spinAlfaCamisaA);

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

		JComboBox<String> cmbCamisaB = new JComboBox(Arquivo.nomesArquivos(pastas.camisas));
		cmbCamisaB.addItemListener(itemListener);
		cmbCamisaB.setBackground(Color.WHITE);
		cmbCamisaB.setBounds(80, 197, 200, 20);
		contentPane.add(cmbCamisaB);

		JSpinner spinRedCamisaB = new JSpinner();
		spinRedCamisaB.addChangeListener(changeListener);
		spinRedCamisaB.setToolTipText("Cor vermelha da camisaA B");
		spinRedCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCamisaB.setBounds(290, 197, 60, 20);
		contentPane.add(spinRedCamisaB);

		JSpinner spinGreenCamisaB = new JSpinner();
		spinGreenCamisaB.addChangeListener(changeListener);
		spinGreenCamisaB.setToolTipText("Cor verde da camisaA B");
		spinGreenCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCamisaB.setBounds(360, 197, 60, 20);
		contentPane.add(spinGreenCamisaB);

		JSpinner spinBlueCamisaB = new JSpinner();
		spinBlueCamisaB.addChangeListener(changeListener);
		spinBlueCamisaB.setToolTipText("Cor azul da camisaA B");
		spinBlueCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCamisaB.setBounds(430, 197, 60, 20);
		contentPane.add(spinBlueCamisaB);

		JSpinner spinAlfaCamisaB = new JSpinner();
		spinAlfaCamisaB.addChangeListener(changeListener);
		spinAlfaCamisaB.setToolTipText("Opacidade da camisaA B");
		spinAlfaCamisaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCamisaB.setBounds(500, 197, 60, 20);
		contentPane.add(spinAlfaCamisaB);

		camisaB = new ParteSprite("camisa B", cmbCamisaB, spinRedCamisaB, spinGreenCamisaB, spinBlueCamisaB, spinAlfaCamisaB);

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

		JComboBox<String> cmbMaos = new JComboBox(Arquivo.nomesArquivos(pastas.maos));
		cmbMaos.addItemListener(itemListener);
		cmbMaos.setBackground(Color.WHITE);
		cmbMaos.setBounds(80, 228, 200, 20);
		contentPane.add(cmbMaos);

		JSpinner spinRedMaos = new JSpinner();
		spinRedMaos.addChangeListener(changeListener);
		spinRedMaos.setToolTipText("Cor vermelha do item das m\u00E3os");
		spinRedMaos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedMaos.setBounds(290, 228, 60, 20);
		contentPane.add(spinRedMaos);

		JSpinner spinGreenMaos = new JSpinner();
		spinGreenMaos.addChangeListener(changeListener);
		spinGreenMaos.setToolTipText("Cor verde do item das m\u00E3os");
		spinGreenMaos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenMaos.setBounds(360, 228, 60, 20);
		contentPane.add(spinGreenMaos);

		JSpinner spinBlueMaos = new JSpinner();
		spinBlueMaos.addChangeListener(changeListener);
		spinBlueMaos.setToolTipText("Cor azul do item das m\u00E3os");
		spinBlueMaos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueMaos.setBounds(430, 228, 60, 20);
		contentPane.add(spinBlueMaos);

		JSpinner spinAlfaMaos = new JSpinner();
		spinAlfaMaos.addChangeListener(changeListener);
		spinAlfaMaos.setToolTipText("Opacidade do item das m\u00E3os");
		spinAlfaMaos.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaMaos.setBounds(500, 228, 60, 20);
		contentPane.add(spinAlfaMaos);

		maos = new ParteSprite("mãos", cmbMaos, spinRedMaos, spinGreenMaos, spinBlueMaos, spinAlfaMaos);

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

		JComboBox<String> cmbCalcaA = new JComboBox(Arquivo.nomesArquivos(pastas.calcas));
		cmbCalcaA.addItemListener(itemListener);
		cmbCalcaA.setBackground(Color.WHITE);
		cmbCalcaA.setBounds(80, 259, 200, 20);
		contentPane.add(cmbCalcaA);

		JSpinner spinRedCalcaA = new JSpinner();
		spinRedCalcaA.addChangeListener(changeListener);
		spinRedCalcaA.setToolTipText("Cor vermelha da calça A");
		spinRedCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCalcaA.setBounds(290, 259, 60, 20);
		contentPane.add(spinRedCalcaA);

		JSpinner spinGreenCalcaA = new JSpinner();
		spinGreenCalcaA.addChangeListener(changeListener);
		spinGreenCalcaA.setToolTipText("Cor verde da calça A");
		spinGreenCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCalcaA.setBounds(360, 259, 60, 20);
		contentPane.add(spinGreenCalcaA);

		JSpinner spinBlueCalcaA = new JSpinner();
		spinBlueCalcaA.addChangeListener(changeListener);
		spinBlueCalcaA.setToolTipText("Cor azul da calça A");
		spinBlueCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCalcaA.setBounds(430, 259, 60, 20);
		contentPane.add(spinBlueCalcaA);

		JSpinner spinAlfaCalcaA = new JSpinner();
		spinAlfaCalcaA.addChangeListener(changeListener);
		spinAlfaCalcaA.setToolTipText("Opacidade da calça A");
		spinAlfaCalcaA.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCalcaA.setBounds(500, 259, 60, 20);
		contentPane.add(spinAlfaCalcaA);

		calcaA = new ParteSprite("calça A", cmbCalcaA, spinRedCalcaA, spinGreenCalcaA, spinBlueCalcaA, spinAlfaCalcaA);

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

		JComboBox<String> cmbCalcaB = new JComboBox(Arquivo.nomesArquivos(pastas.calcas));
		cmbCalcaB.addItemListener(itemListener);
		cmbCalcaB.setBackground(Color.WHITE);
		cmbCalcaB.setBounds(80, 290, 200, 20);
		contentPane.add(cmbCalcaB);

		JSpinner spinRedCalcaB = new JSpinner();
		spinRedCalcaB.addChangeListener(changeListener);
		spinRedCalcaB.setToolTipText("Cor vermelha da calça B");
		spinRedCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCalcaB.setBounds(290, 290, 60, 20);
		contentPane.add(spinRedCalcaB);

		JSpinner spinGreenCalcaB = new JSpinner();
		spinGreenCalcaB.addChangeListener(changeListener);
		spinGreenCalcaB.setToolTipText("Cor verde da calça B");
		spinGreenCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCalcaB.setBounds(360, 290, 60, 20);
		contentPane.add(spinGreenCalcaB);

		JSpinner spinBlueCalcaB = new JSpinner();
		spinBlueCalcaB.addChangeListener(changeListener);
		spinBlueCalcaB.setToolTipText("Cor azul da calça B");
		spinBlueCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCalcaB.setBounds(430, 290, 60, 20);
		contentPane.add(spinBlueCalcaB);

		JSpinner spinAlfaCalcaB = new JSpinner();
		spinAlfaCalcaB.addChangeListener(changeListener);
		spinAlfaCalcaB.setToolTipText("Opacidade da calça B");
		spinAlfaCalcaB.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCalcaB.setBounds(500, 290, 60, 20);
		contentPane.add(spinAlfaCalcaB);

		calcaB = new ParteSprite("calça B", cmbCalcaB, spinRedCalcaB, spinGreenCalcaB, spinBlueCalcaB, spinAlfaCalcaB);

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

		JComboBox<String> cmbCostas = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
		cmbCostas.addItemListener(itemListener);
		cmbCostas.setBackground(Color.WHITE);
		cmbCostas.setBounds(80, 321, 200, 20);
		contentPane.add(cmbCostas);

		JSpinner spinRedCostas = new JSpinner();
		spinRedCostas.addChangeListener(changeListener);
		spinRedCostas.setToolTipText("Cor vermelha do item das costas");
		spinRedCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedCostas.setBounds(290, 321, 60, 20);
		contentPane.add(spinRedCostas);

		JSpinner spinGreenCostas = new JSpinner();
		spinGreenCostas.addChangeListener(changeListener);
		spinGreenCostas.setToolTipText("Cor verde do item das costas");
		spinGreenCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenCostas.setBounds(360, 321, 60, 20);
		contentPane.add(spinGreenCostas);

		JSpinner spinBlueCostas = new JSpinner();
		spinBlueCostas.addChangeListener(changeListener);
		spinBlueCostas.setToolTipText("Cor azul do item das costas");
		spinBlueCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueCostas.setBounds(430, 321, 60, 20);
		contentPane.add(spinBlueCostas);

		JSpinner spinAlfaCostas = new JSpinner();
		spinAlfaCostas.setToolTipText("Opacidade do item das costas");
		spinAlfaCostas.addChangeListener(changeListener);
		spinAlfaCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaCostas.setBounds(500, 321, 60, 20);
		contentPane.add(spinAlfaCostas);

		costas = new ParteSprite("costas", cmbCostas, spinRedCostas, spinGreenCostas, spinBlueCostas, spinAlfaCostas);

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

		JComboBox<String> cmbPes = new JComboBox(Arquivo.nomesArquivos(pastas.pes));
		cmbPes.addItemListener(itemListener);
		cmbPes.setBackground(Color.WHITE);
		cmbPes.setBounds(80, 352, 200, 20);
		contentPane.add(cmbPes);

		JSpinner spinRedPes = new JSpinner();
		spinRedPes.addChangeListener(changeListener);
		spinRedPes.setToolTipText("Cor vermelha do item dos p\u00E9s");
		spinRedPes.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedPes.setBounds(290, 352, 60, 20);
		contentPane.add(spinRedPes);

		JSpinner spinGreenPes = new JSpinner();
		spinGreenPes.addChangeListener(changeListener);
		spinGreenPes.setToolTipText("Cor verde do item dos p\u00E9s");
		spinGreenPes.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenPes.setBounds(360, 352, 60, 20);
		contentPane.add(spinGreenPes);

		JSpinner spinBluePes = new JSpinner();
		spinBluePes.addChangeListener(changeListener);
		spinBluePes.setToolTipText("Cor azul do item dos p\u00E9s");
		spinBluePes.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBluePes.setBounds(430, 352, 60, 20);
		contentPane.add(spinBluePes);

		JSpinner spinAlfaPes = new JSpinner();
		spinAlfaPes.addChangeListener(changeListener);
		spinAlfaPes.setToolTipText("Opacidade do item dos p\u00E9s");
		spinAlfaPes.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaPes.setBounds(500, 352, 60, 20);
		contentPane.add(spinAlfaPes);

		pes = new ParteSprite("pes", cmbPes, spinRedPes, spinGreenPes, spinBluePes, spinAlfaPes);

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

	ItemListener itemListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (deveAtualizar) atualizaSprite();
		}
	};

	ChangeListener changeListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if (deveAtualizar) {
				atualizaSprite();
			}
		}
	};

	//Atualiza o sprite com as partes selecionadas
	private void atualizaSprite() {
		if (!rgba) {
			elmo.atualizaCXM();
			cabelo.atualizaCXM();
			olhos.atualizaCXM();
			face.atualizaCXM();
			camisaA.atualizaCXM();
			camisaB.atualizaCXM();
			maos.atualizaCXM();
			calcaA.atualizaCXM();
			calcaB.atualizaCXM();
			costas.atualizaCXM();
			pes.atualizaCXM();
		}
		int[][] imagemCostas;
		try {
			imagemCostas = arquivo.selecionarImagem(pastas.costas, costas, rgba);
		} catch (TamanhoErradoException e) {
			imagemCostas = e.tratar(imagem.gerarTransparencia());
		}
		int[][] sprite = imagem.capaAtras(imagemCostas);
		sprite = sobreporImagemArquivo(sprite, pastas.corpos, corpo);
		sprite = sobreporImagemArquivo(sprite, pastas.olhos, olhos);
		sprite = sobreporImagemArquivo(sprite, pastas.calcas, calcaB);
		sprite = sobreporImagemArquivo(sprite, pastas.camisas, camisaB);
		sprite = sobreporImagemArquivo(sprite, pastas.maos, maos);
		sprite = sobreporImagemArquivo(sprite, pastas.pes, pes);
		sprite = sobreporImagemArquivo(sprite, pastas.calcas, calcaA);
		sprite = sobreporImagemArquivo(sprite, pastas.camisas, camisaA);
		sprite = sobreporImagemArquivo(sprite, pastas.faces, face);
		sprite = imagem.sobreporImagem(imagem.capaFrente(imagemCostas), sprite);
		sprite = sobreporImagemArquivo(sprite, pastas.cabelos, cabelo);
		sprite = sobreporImagemArquivo(sprite, pastas.elmos, elmo);
		buffer = imagem.matrizParaBuffer(sprite);
		this.sprite.label.setIcon(new ImageIcon(buffer.getScaledInstance(2 * imagem.LARGURA, 2 * imagem.ALTURA, Image.SCALE_AREA_AVERAGING)));
	}

	//Sobrepoe a imagem
	private int[][] sobreporImagemArquivo(int[][] base, File[] array, ParteSprite parte) {
		try {
			return imagem.sobreporImagem(arquivo.selecionarImagem(array, parte, rgba), base);
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
		JComboBox<String> novo = new JComboBox<String>(Arquivo.nomesArquivos(arq));
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
		parte.red.setValue(random.nextInt(256));
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
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + Arquivo.salvarSprite(pastas, txtNomeSprite.getText(), buffer) + "\"");
	}
}
