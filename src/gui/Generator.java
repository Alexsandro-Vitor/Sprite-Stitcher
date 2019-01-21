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
import classes.SpritePart;
import classes.Pastas;
import excecoes.TamanhoErradoException;
import funcoes.*;

import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Generator extends JFrame {

	private Sprite sprite;
	private String pastaArquivos;
	private Pastas folders;
	private BufferedImage buffer;
	private static Random random = new Random();
	private SpritePart corpo;
	private SpritePart helm;
	private SpritePart hair;
	private SpritePart eyes;
	private SpritePart face;
	private SpritePart torsoA;
	private SpritePart torsoB;
	private SpritePart hands;
	private SpritePart legsA;
	private SpritePart legsB;
	private SpritePart back;
	private SpritePart shoes;
	private boolean deveAtualizar = true;
	private boolean rgba = true;

	private JPanel contentPane;
	private JTextField txtNameSprite;

	/**
	 * Create the frame.
	 */
	public Generator(Sprite sprite, String pastaArquivos) {
		this.sprite = sprite;
		this.pastaArquivos = pastaArquivos;
		this.folders = new Pastas(pastaArquivos);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 746, 443);
		setResizable(false);
		setTitle("Sprite Generator");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBody = new JLabel("Body");
		lblBody.setBounds(10, 11, 60, 20);
		contentPane.add(lblBody);

		JComboBox<String> cmbBody = new JComboBox(Leitura.nomesArquivos(folders.body));
		corpo = new SpritePart("corpo", cmbBody, null, null, null, null, null);

		cmbBody.setBackground(Color.WHITE);
		cmbBody.addItemListener(itemListener);
		cmbBody.setBounds(80, 11, 200, 20);
		contentPane.add(cmbBody);

		JLabel lblRed = new JLabel("Red");
		lblRed.setHorizontalAlignment(SwingConstants.CENTER);
		lblRed.setBounds(290, 11, 60, 20);
		contentPane.add(lblRed);

		JLabel lblGreen = new JLabel("Green");
		lblGreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreen.setBounds(360, 11, 60, 20);
		contentPane.add(lblGreen);

		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlue.setBounds(430, 11, 60, 20);
		contentPane.add(lblBlue);

		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlpha.setBounds(500, 11, 60, 20);
		contentPane.add(lblAlpha);

		JLabel lblHueSwap = new JLabel("Hue Swap");
		lblHueSwap.setHorizontalAlignment(SwingConstants.CENTER);
		lblHueSwap.setBounds(570, 11, 60, 20);
		contentPane.add(lblHueSwap);

		JButton btnHSB = new JButton("HSB");
		btnHSB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rgba = !rgba;
				if (rgba) {
					btnHSB.setText("HSB");
					lblRed.setText("Red");
					lblGreen.setText("Green");
					lblBlue.setText("Blue");
				} else {
					btnHSB.setText("RGB");
					lblRed.setText("Hue");
					lblGreen.setText("Saturation");
					lblBlue.setText("Bright");
				}
				deveAtualizar = false;
				helm.setRGBA(rgba);
				hair.setRGBA(rgba);
				eyes.setRGBA(rgba);
				face.setRGBA(rgba);
				torsoA.setRGBA(rgba);
				torsoB.setRGBA(rgba);
				hands.setRGBA(rgba);
				legsA.setRGBA(rgba);
				legsB.setRGBA(rgba);
				back.setRGBA(rgba);
				shoes.setRGBA(rgba);
				atualizaSprite();
				deveAtualizar = true;
			}
		});
		btnHSB.setBounds(640, 11, 90, 20);
		contentPane.add(btnHSB);

		JLabel lblHelm = new JLabel("Helm");
		lblHelm.setBounds(10, 42, 60, 20);
		contentPane.add(lblHelm);
		
		JComboBox<String> cmbHelm = new JComboBox(Leitura.nomesArquivos(folders.helm));
		JSpinner spinHelmRed = new JSpinner();
		JSpinner spinHelmGreen = new JSpinner();
		JSpinner spinHelmBlue = new JSpinner();
		JSpinner spinHelmAlpha = new JSpinner();
		JSpinner spinHelmHueSwap = new JSpinner();
		helm = new SpritePart("helm", cmbHelm, spinHelmRed, spinHelmGreen, spinHelmBlue, spinHelmAlpha, spinHelmHueSwap);
		configParteSprite(helm);
		
		cmbHelm.setBackground(Color.WHITE);
		cmbHelm.setBounds(80, 42, 200, 20);
		contentPane.add(cmbHelm);

		spinHelmRed.setBounds(290, 42, 60, 20);
		contentPane.add(spinHelmRed);

		spinHelmGreen.setBounds(360, 42, 60, 20);
		contentPane.add(spinHelmGreen);

		spinHelmBlue.setBounds(430, 42, 60, 20);
		contentPane.add(spinHelmBlue);

		spinHelmAlpha.setBounds(500, 42, 60, 20);
		contentPane.add(spinHelmAlpha);

		spinHelmHueSwap.setBounds(570, 42, 60, 20);
		contentPane.add(spinHelmHueSwap);

		JButton btnHelmRandom = new JButton("Random");
		btnHelmRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				corParteAleatoria(helm);
			}
		});
		btnHelmRandom.setBounds(640, 42, 90, 20);
		contentPane.add(btnHelmRandom);

		JLabel lblHair = new JLabel("Hair");
		lblHair.setBounds(10, 73, 60, 20);
		contentPane.add(lblHair);

		JComboBox<String> cmbHair = new JComboBox(Leitura.nomesArquivos(folders.hair));
		JSpinner spinHairRed = new JSpinner();
		JSpinner spinHairGreen = new JSpinner();
		JSpinner spinHairBlue = new JSpinner();
		JSpinner spinHairHueSwap = new JSpinner();
		hair = new SpritePart("hair", cmbHair, spinHairRed, spinHairGreen, spinHairBlue, null, spinHairHueSwap);
		configParteSprite(hair);
		
		cmbHair.setBackground(Color.WHITE);
		cmbHair.setBounds(80, 73, 200, 20);
		contentPane.add(cmbHair);

		spinHairRed.setBounds(290, 73, 60, 20);
		contentPane.add(spinHairRed);

		spinHairGreen.setBounds(360, 73, 60, 20);
		contentPane.add(spinHairGreen);

		spinHairBlue.setBounds(430, 73, 60, 20);
		contentPane.add(spinHairBlue);

		spinHairHueSwap.setBounds(570, 73, 60, 20);
		contentPane.add(spinHairHueSwap);

		JButton btnHairRandom = new JButton("Random");
		btnHairRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(hair);
			}
		});
		btnHairRandom.setBounds(640, 73, 90, 20);
		contentPane.add(btnHairRandom);

		JLabel lblEyes = new JLabel("Eyes");
		lblEyes.setBounds(10, 104, 60, 20);
		contentPane.add(lblEyes);

		JComboBox<String> cmbEyes = new JComboBox(Leitura.nomesArquivos(folders.eyes));
		JSpinner spinEyesRed = new JSpinner();
		JSpinner spinEyesGreen = new JSpinner();
		JSpinner spinEyesBlue = new JSpinner();
		JSpinner spinEyesHueSwap = new JSpinner();
		eyes = new SpritePart("eyes", cmbEyes, spinEyesRed, spinEyesGreen, spinEyesBlue, null, spinEyesHueSwap);
		configParteSprite(eyes);
		
		cmbEyes.setBackground(Color.WHITE);
		cmbEyes.setBounds(80, 104, 200, 20);
		contentPane.add(cmbEyes);

		spinEyesRed.setBounds(290, 104, 60, 20);
		contentPane.add(spinEyesRed);

		spinEyesGreen.setBounds(360, 104, 60, 20);
		contentPane.add(spinEyesGreen);

		spinEyesBlue.setBounds(430, 104, 60, 20);
		contentPane.add(spinEyesBlue);

		spinEyesHueSwap.setBounds(570, 104, 60, 20);
		contentPane.add(spinEyesHueSwap);

		JButton btnEyesRandom = new JButton("Random");
		btnEyesRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(eyes);
			}
		});
		btnEyesRandom.setBounds(640, 104, 90, 20);
		contentPane.add(btnEyesRandom);

		JLabel lblFace = new JLabel("Face");
		lblFace.setBounds(10, 135, 60, 20);
		contentPane.add(lblFace);

		JComboBox<String> cmbFace = new JComboBox(Leitura.nomesArquivos(folders.faces));
		JSpinner spinFaceRed = new JSpinner();
		JSpinner spinFaceGreen = new JSpinner();
		JSpinner spinFaceBlue = new JSpinner();
		JSpinner spinFaceAlpha = new JSpinner();
		JSpinner spinFaceHueSwap = new JSpinner();
		face = new SpritePart("face", cmbFace, spinFaceRed, spinFaceGreen, spinFaceBlue, spinFaceAlpha, spinFaceHueSwap);
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

		spinFaceAlpha.setBounds(500, 135, 60, 20);
		contentPane.add(spinFaceAlpha);

		spinFaceHueSwap.setBounds(570, 135, 60, 20);
		contentPane.add(spinFaceHueSwap);

		JButton btnFaceRandom = new JButton("Random");
		btnFaceRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(face);
			}
		});
		btnFaceRandom.setBounds(640, 135, 90, 20);
		contentPane.add(btnFaceRandom);

		JLabel lblTorsoA = new JLabel("Torso A");
		lblTorsoA.setBounds(10, 166, 60, 20);
		contentPane.add(lblTorsoA);

		JComboBox<String> cmbTorsoA = new JComboBox(Leitura.nomesArquivos(folders.torso));
		JSpinner spinTorsoARed = new JSpinner();
		JSpinner spinTorsoAGreen = new JSpinner();
		JSpinner spinTorsoABlue = new JSpinner();
		JSpinner spinTorsoAAlpha = new JSpinner();
		JSpinner spinTorsoAHueSwap = new JSpinner();
		torsoA = new SpritePart("torso A", cmbTorsoA, spinTorsoARed, spinTorsoAGreen, spinTorsoABlue, spinTorsoAAlpha, spinTorsoAHueSwap);
		configParteSprite(torsoA);
		
		cmbTorsoA.setBackground(Color.WHITE);
		cmbTorsoA.setBounds(80, 166, 200, 20);
		contentPane.add(cmbTorsoA);

		spinTorsoARed.setBounds(290, 166, 60, 20);
		contentPane.add(spinTorsoARed);

		spinTorsoAGreen.setBounds(360, 166, 60, 20);
		contentPane.add(spinTorsoAGreen);

		spinTorsoABlue.setBounds(430, 166, 60, 20);
		contentPane.add(spinTorsoABlue);

		spinTorsoAAlpha.setBounds(500, 166, 60, 20);
		contentPane.add(spinTorsoAAlpha);

		spinTorsoAHueSwap.setBounds(570, 166, 60, 20);
		contentPane.add(spinTorsoAHueSwap);
		
		JButton btnTorsoARandom = new JButton("Random");
		btnTorsoARandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(torsoA);
			}
		});
		btnTorsoARandom.setBounds(640, 166, 90, 20);
		contentPane.add(btnTorsoARandom);

		JLabel lblTorsoB = new JLabel("Torso B");
		lblTorsoB.setBounds(10, 197, 60, 20);
		contentPane.add(lblTorsoB);

		JComboBox<String> cmbTorsoB = new JComboBox(Leitura.nomesArquivos(folders.torso));
		JSpinner spinTorsoBRed = new JSpinner();
		JSpinner spinTorsoBGreen = new JSpinner();
		JSpinner spinTorsoBBlue = new JSpinner();
		JSpinner spinTorsoBAlpha = new JSpinner();
		JSpinner spinTorsoBHueSwap = new JSpinner();
		torsoB = new SpritePart("torso B", cmbTorsoB, spinTorsoBRed, spinTorsoBGreen, spinTorsoBBlue, spinTorsoBAlpha, spinTorsoBHueSwap);
		configParteSprite(torsoB);
		
		cmbTorsoB.setBackground(Color.WHITE);
		cmbTorsoB.setBounds(80, 197, 200, 20);
		contentPane.add(cmbTorsoB);

		spinTorsoBRed.setBounds(290, 197, 60, 20);
		contentPane.add(spinTorsoBRed);

		spinTorsoBGreen.setBounds(360, 197, 60, 20);
		contentPane.add(spinTorsoBGreen);

		spinTorsoBBlue.setBounds(430, 197, 60, 20);
		contentPane.add(spinTorsoBBlue);

		spinTorsoBAlpha.setBounds(500, 197, 60, 20);
		contentPane.add(spinTorsoBAlpha);

		spinTorsoBHueSwap.setBounds(570, 197, 60, 20);
		contentPane.add(spinTorsoBHueSwap);

		JButton btnTorsoBRandom = new JButton("Random");
		btnTorsoBRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(torsoB);
			}
		});
		btnTorsoBRandom.setBounds(640, 197, 90, 20);
		contentPane.add(btnTorsoBRandom);

		JLabel lblHands = new JLabel("Hands");
		lblHands.setBounds(10, 228, 60, 20);
		contentPane.add(lblHands);

		JComboBox<String> cmbHands = new JComboBox(Leitura.nomesArquivos(folders.hands));
		JSpinner spinHandsRed = new JSpinner();
		JSpinner spinHandsGreen = new JSpinner();
		JSpinner spinHandsBlue = new JSpinner();
		JSpinner spinHandsAlpha = new JSpinner();
		JSpinner spinHandsHueSwap = new JSpinner();
		hands = new SpritePart("hands", cmbHands, spinHandsRed, spinHandsGreen, spinHandsBlue, spinHandsAlpha, spinHandsHueSwap);
		configParteSprite(hands);
		
		cmbHands.setBackground(Color.WHITE);
		cmbHands.setBounds(80, 228, 200, 20);
		contentPane.add(cmbHands);

		spinHandsRed.setBounds(290, 228, 60, 20);
		contentPane.add(spinHandsRed);

		spinHandsGreen.setBounds(360, 228, 60, 20);
		contentPane.add(spinHandsGreen);

		spinHandsBlue.setBounds(430, 228, 60, 20);
		contentPane.add(spinHandsBlue);

		spinHandsAlpha.setBounds(500, 228, 60, 20);
		contentPane.add(spinHandsAlpha);

		spinHandsHueSwap.setBounds(570, 228, 60, 20);
		contentPane.add(spinHandsHueSwap);

		JButton btnHandsRandom = new JButton("Random");
		btnHandsRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(hands);
			}
		});
		btnHandsRandom.setBounds(640, 228, 90, 20);
		contentPane.add(btnHandsRandom);

		JLabel lblLegsA = new JLabel("Legs A");
		lblLegsA.setBounds(10, 259, 60, 20);
		contentPane.add(lblLegsA);

		JComboBox<String> cmbLegsA = new JComboBox(Leitura.nomesArquivos(folders.legs));
		JSpinner spinLegsARed = new JSpinner();
		JSpinner spinLegsAGreen = new JSpinner();
		JSpinner spinLegsABlue = new JSpinner();
		JSpinner spinLegsAAlpha = new JSpinner();
		JSpinner spinLegsAHueSwap = new JSpinner();
		legsA = new SpritePart("legs A", cmbLegsA, spinLegsARed, spinLegsAGreen, spinLegsABlue, spinLegsAAlpha, spinLegsAHueSwap);
		configParteSprite(legsA);
		
		cmbLegsA.setBackground(Color.WHITE);
		cmbLegsA.setBounds(80, 259, 200, 20);
		contentPane.add(cmbLegsA);

		spinLegsARed.setBounds(290, 259, 60, 20);
		contentPane.add(spinLegsARed);

		spinLegsAGreen.setBounds(360, 259, 60, 20);
		contentPane.add(spinLegsAGreen);

		spinLegsABlue.setBounds(430, 259, 60, 20);
		contentPane.add(spinLegsABlue);

		spinLegsAAlpha.setBounds(500, 259, 60, 20);
		contentPane.add(spinLegsAAlpha);

		spinLegsAHueSwap.setBounds(570, 259, 60, 20);
		contentPane.add(spinLegsAHueSwap);

		JButton btnLegsARandom = new JButton("Random");
		btnLegsARandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(legsA);
			}
		});
		btnLegsARandom.setBounds(640, 259, 90, 20);
		contentPane.add(btnLegsARandom);

		JLabel lblLegsB = new JLabel("Legs B");
		lblLegsB.setBounds(10, 290, 60, 20);
		contentPane.add(lblLegsB);

		JComboBox<String> cmbLegsB = new JComboBox(Leitura.nomesArquivos(folders.legs));
		JSpinner spinLegsBRed = new JSpinner();
		JSpinner spinLegsBGreen = new JSpinner();
		JSpinner spinLegsBBlue = new JSpinner();
		JSpinner spinLegsBAlpha = new JSpinner();
		JSpinner spinLegsBHueSwap = new JSpinner();
		legsB = new SpritePart("legs B", cmbLegsB, spinLegsBRed, spinLegsBGreen, spinLegsBBlue, spinLegsBAlpha, spinLegsBHueSwap);
		configParteSprite(legsB);
		
		cmbLegsB.setBounds(80, 290, 200, 20);
		contentPane.add(cmbLegsB);

		spinLegsBRed.setBounds(290, 290, 60, 20);
		contentPane.add(spinLegsBRed);

		spinLegsBGreen.setBounds(360, 290, 60, 20);
		contentPane.add(spinLegsBGreen);

		spinLegsBBlue.setBounds(430, 290, 60, 20);
		contentPane.add(spinLegsBBlue);

		spinLegsBAlpha.setBounds(500, 290, 60, 20);
		contentPane.add(spinLegsBAlpha);

		spinLegsBHueSwap.setBounds(570, 290, 60, 20);
		contentPane.add(spinLegsBHueSwap);

		JButton btnLegsBRandom = new JButton("Random");
		btnLegsBRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(legsB);
			}
		});
		btnLegsBRandom.setBounds(640, 290, 90, 20);
		contentPane.add(btnLegsBRandom);

		JLabel lblBack = new JLabel("Back");
		lblBack.setBounds(10, 321, 60, 20);
		contentPane.add(lblBack);

		JComboBox<String> cmbBack = new JComboBox(Leitura.nomesArquivos(folders.back));
		JSpinner spinBackRed = new JSpinner();
		JSpinner spinBackGreen = new JSpinner();
		JSpinner spinBackBlue = new JSpinner();
		JSpinner spinBackAlpha = new JSpinner();
		JSpinner spinBackHueSwap = new JSpinner();
		back = new SpritePart("back", cmbBack, spinBackRed, spinBackGreen, spinBackBlue, spinBackAlpha, spinBackHueSwap);
		configParteSprite(back);
		
		cmbBack.setBackground(Color.WHITE);
		cmbBack.setBounds(80, 321, 200, 20);
		contentPane.add(cmbBack);

		spinBackRed.setBounds(290, 321, 60, 20);
		contentPane.add(spinBackRed);

		spinBackGreen.setBounds(360, 321, 60, 20);
		contentPane.add(spinBackGreen);

		spinBackBlue.setBounds(430, 321, 60, 20);
		contentPane.add(spinBackBlue);

		spinBackAlpha.setBounds(500, 321, 60, 20);
		contentPane.add(spinBackAlpha);

		spinBackHueSwap.setBounds(570, 321, 60, 20);
		contentPane.add(spinBackHueSwap);

		JButton btnBackRandom = new JButton("Random");
		btnBackRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(back);
			}
		});
		btnBackRandom.setBounds(640, 321, 90, 20);
		contentPane.add(btnBackRandom);

		JLabel lblShoes = new JLabel("Shoes");
		lblShoes.setBounds(10, 352, 60, 20);
		contentPane.add(lblShoes);

		JComboBox<String> cmbShoes = new JComboBox(Leitura.nomesArquivos(folders.shoes));
		JSpinner spinShoesRed = new JSpinner();
		JSpinner spinShoesGreen = new JSpinner();
		JSpinner spinShoesBlue = new JSpinner();
		JSpinner spinShoesAlpha = new JSpinner();
		JSpinner spinShoesHueSwap = new JSpinner();
		shoes = new SpritePart("shoes", cmbShoes, spinShoesRed, spinShoesGreen, spinShoesBlue, spinShoesAlpha, spinShoesHueSwap);
		configParteSprite(shoes);
		
		cmbShoes.setBackground(Color.WHITE);
		cmbShoes.setBounds(80, 352, 200, 20);
		contentPane.add(cmbShoes);

		spinShoesRed.setBounds(290, 352, 60, 20);
		contentPane.add(spinShoesRed);

		spinShoesGreen.setBounds(360, 352, 60, 20);
		contentPane.add(spinShoesGreen);

		spinShoesBlue.setBounds(430, 352, 60, 20);
		contentPane.add(spinShoesBlue);

		spinShoesAlpha.setBounds(500, 352, 60, 20);
		contentPane.add(spinShoesAlpha);

		spinShoesHueSwap.setBounds(570, 352, 60, 20);
		contentPane.add(spinShoesHueSwap);

		JButton btnShoesRandom = new JButton("Random");
		btnShoesRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				corParteAleatoria(shoes);
			}
		});
		btnShoesRandom.setBounds(640, 352, 90, 20);
		contentPane.add(btnShoesRandom);

		JButton btnAtualizarPastas = new JButton("Update folders");
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				atualizaPastas();
			}
		});

		JButton btnRandom = new JButton("Random Parts");
		btnRandom.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteRandom();
			}
		});
		btnRandom.setBounds(590, 383, 140, 20);
		contentPane.add(btnRandom);
		btnAtualizarPastas.setBounds(450, 383, 130, 20);
		contentPane.add(btnAtualizarPastas);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 383, 60, 20);
		contentPane.add(lblName);

		txtNameSprite = new JTextField();
		txtNameSprite.setBounds(80, 383, 200, 20);
		contentPane.add(txtNameSprite);
		txtNameSprite.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				salvarSprite();
			}
		});
		btnSave.setBounds(290, 383, 150, 20);
		contentPane.add(btnSave);
	}
	
	void configParteSprite(SpritePart parte) {
		parte.getCmb().addItemListener(itemListener);
		parte.red.setToolTipText("Red color of " + parte.name);
		parte.red.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		parte.red.addChangeListener(changeListener);
		parte.green.setToolTipText("Green color of " + parte.name);
		parte.green.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		parte.green.addChangeListener(changeListener);
		parte.blue.setToolTipText("Blue color of " + parte.name);
		parte.blue.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		parte.blue.addChangeListener(changeListener);
		if (parte.alfa != null) {
			parte.alfa.setToolTipText("Alpha of " + parte.name);
			parte.alfa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
			parte.alfa.addChangeListener(changeListener);
		}
		parte.hueSwap.setToolTipText("Hue Swap of " + parte.name);
		parte.hueSwap.setModel(new SpinnerNumberModel(0, 0, 360, 1));
		parte.hueSwap.addChangeListener(changeListener);
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
		back.updateColor(rgba);
		try {
			sprite = Imagem.capaAtras(Leitura.selecionarImagem(folders.back, back));
		} catch (TamanhoErradoException e) {
			sprite = Imagem.gerarTransparencia();
		}
		System.out.println("Tempos:");
		long tempo = System.nanoTime();
		corpo.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.body, corpo);
		System.out.println("Sobrepor corpo: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		eyes.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.eyes, eyes);
		System.out.println("Sobrepor eyes: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		legsB.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.legs, legsB);
		System.out.println("Sobrepor legsB: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		torsoB.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.torso, torsoB);
		System.out.println("Sobrepor torsoB: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		hands.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.hands, hands);
		System.out.println("Sobrepor hands: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		shoes.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.shoes, shoes);
		System.out.println("Sobrepor shoes: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		legsA.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.legs, legsA);
		System.out.println("Sobrepor legsA: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		torsoA.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.torso, torsoA);
		System.out.println("Sobrepor torsoA: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		face.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.faces, face);
		System.out.println("Sobrepor face: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		try {
			sprite = Imagem.sobreporImagem(Imagem.capaFrente(Leitura.selecionarImagem(folders.back, back)), sprite);
		} catch (TamanhoErradoException e) {}
		System.out.println("Sobrepor back: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		hair.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.hair, hair);
		System.out.println("Sobrepor hair: " + (System.nanoTime()-tempo));
		
		tempo = System.nanoTime();
		helm.updateColor(rgba);
		sprite = sobreporImagemArquivo(sprite, folders.helm, helm);
		System.out.println("Sobrepor helm: " + (System.nanoTime()-tempo));
		
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
	private int[][] sobreporImagemArquivo(int[][] base, File[] array, SpritePart parte) {
		if (parte.getCmb().getSelectedIndex() == 0) return base;
		try {
			int[][] imagem = Leitura.selecionarImagem(array, parte);
			return Imagem.sobreporImagem(imagem, base);
		} catch (TamanhoErradoException e) {
			return e.tratar(base);
		}
	}

	private void atualizaPastas() {
		folders = new Pastas(pastaArquivos);
		atualizaCmb(corpo, folders.body);
		atualizaCmb(helm, folders.helm);
		atualizaCmb(hair, folders.hair);
		atualizaCmb(eyes, folders.eyes);
		atualizaCmb(face, folders.faces);
		atualizaCmb(torsoA, folders.torso);
		atualizaCmb(torsoB, folders.torso);
		atualizaCmb(hands, folders.hands);
		atualizaCmb(legsA, folders.legs);
		atualizaCmb(legsB, folders.legs);
		atualizaCmb(back, folders.back);
		atualizaCmb(shoes, folders.shoes);
		JOptionPane.showMessageDialog(null, "Updated folders");
	}

	void atualizaCmb(SpritePart parte, File[] arq) {
		JComboBox<String> novo = new JComboBox<String>(Leitura.nomesArquivos(arq));
		novo.addItemListener(itemListener);
		novo.setBackground(Color.WHITE);
		novo.setBounds(parte.getCmb().getBounds());
		contentPane.remove(parte.getCmb());
		contentPane.add(novo);
		parte.setCmb(novo);
	}

	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteRandom() {
		deveAtualizar = false;
		selecaoItemRandom(corpo.getCmb());
		selecaoItemRandom(helm.getCmb());
		selecaoItemRandom(hair.getCmb());
		selecaoItemRandom(eyes.getCmb());
		selecaoItemRandom(face.getCmb());
		selecaoItemRandom(torsoA.getCmb());
		selecaoItemRandom(torsoB.getCmb());
		selecaoItemRandom(hands.getCmb());
		selecaoItemRandom(legsA.getCmb());
		selecaoItemRandom(legsB.getCmb());
		selecaoItemRandom(back.getCmb());
		selecaoItemRandom(shoes.getCmb());
		atualizaSprite();
		deveAtualizar = true;
	}

	private void corParteAleatoria(SpritePart parte) {
		deveAtualizar = false;
		parte.red.setValue(random.nextInt((Integer) ((SpinnerNumberModel) parte.red.getModel()).getMaximum() + 1));
		parte.green.setValue(random.nextInt(256));
		parte.blue.setValue(random.nextInt(256));
		atualizaSprite();
		deveAtualizar = true;
	}

	private void selecaoItemRandom(JComboBox<String> cmb) {
		cmb.setSelectedIndex(random.nextInt(cmb.getItemCount()));
	}

	private void salvarSprite() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Sprite salvo com o name \"" + Escrita.salvarSprite(folders, txtNameSprite.getText(), buffer) + "\"");
	}
}
