package gui;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.image.BufferedImage;

import java.io.File;

import java.util.Random;

import javax.swing.DefaultComboBoxModel;
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

import classes.Dimensions;
import classes.SpritePart;
import exceptions.WrongSizeException;
import classes.Folders;
import funcoes.*;

import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
/**
 * Class which manages the UI for selecting the parts and colors.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Generator extends JFrame {

	private Sprite sprite;
	private String imagesFolder;
	private Folders folders;
	private BufferedImage buffer;
	private static Random random = new Random();
	private SpritePart body;
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
	private SpritePart[] parts = new SpritePart[12];
	private boolean shouldUpdate = true;
	private boolean rgba = true;

	private JPanel contentPane;
	private JTextField txtNameSprite;

	/**
	 * Create the frame.
	 */
	public Generator(Sprite sprite, String imagesFolder) {
		this.sprite = sprite;
		this.imagesFolder = imagesFolder;
		this.folders = new Folders(imagesFolder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 806, 474);
		setResizable(false);
		setTitle("Sprite Generator");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRed = new JLabel("Red");
		lblRed.setBounds(290, 11, 60, 20);
		lblRed.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRed);

		JLabel lblGreen = new JLabel("Green");
		lblGreen.setBounds(360, 11, 60, 20);
		lblGreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGreen);

		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setBounds(430, 11, 60, 20);
		lblBlue.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBlue);

		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setBounds(500, 11, 60, 20);
		lblAlpha.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAlpha);

		JLabel lblHueSwap = new JLabel("Hue Swap");
		lblHueSwap.setBounds(570, 11, 60, 20);
		lblHueSwap.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblHueSwap);

		JButton btnHSB = new JButton("HSB");
		btnHSB.setBounds(640, 11, 90, 20);
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
				shouldUpdate = false;
				for (SpritePart part : parts) {
					part.setRGBA(rgba);
				}
				updateSprite();
				shouldUpdate = true;
			}
		});
		contentPane.add(btnHSB);

		JPanel panelBody = new JPanel();
		panelBody.setBounds(10, 42, 780, 20);
		contentPane.add(panelBody);
		panelBody.setLayout(null);

		JLabel lblBody = new JLabel("Body");
		lblBody.setBounds(0, 0, 60, 20);
		panelBody.add(lblBody);

		JComboBox<String> cmbBody = new JComboBox(Leitura.nomesArquivos(folders.body));
		cmbBody.setBounds(70, 0, 200, 20);
		panelBody.add(cmbBody);
		cmbBody.setBackground(Color.WHITE);

		JSpinner spinBodyRed = new JSpinner();
		spinBodyRed.setBounds(280, 0, 60, 20);
		panelBody.add(spinBodyRed);

		JSpinner spinBodyGreen = new JSpinner();
		spinBodyGreen.setBounds(350, 0, 60, 20);
		panelBody.add(spinBodyGreen);

		JSpinner spinBodyBlue = new JSpinner();
		spinBodyBlue.setBounds(420, 0, 60, 20);
		panelBody.add(spinBodyBlue);

		JSpinner spinBodyAlpha = new JSpinner();
		spinBodyAlpha.setBounds(490, 0, 60, 20);
		panelBody.add(spinBodyAlpha);

		JSpinner spinBodyHueSwap = new JSpinner();
		spinBodyHueSwap.setBounds(560, 0, 60, 20);
		panelBody.add(spinBodyHueSwap);

		JButton btnBodyRandom = new JButton("Random");
		btnBodyRandom.setBounds(630, 0, 90, 20);
		panelBody.add(btnBodyRandom);
		btnBodyRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(body);
			}
		});
		
		JCheckBox chckbxBodyLock = new JCheckBox("Lock");
		chckbxBodyLock.setBounds(726, 0, 54, 20);
		panelBody.add(chckbxBodyLock);

		JPanel panelHelm = new JPanel();
		panelHelm.setBounds(10, 73, 780, 20);
		contentPane.add(panelHelm);
		panelHelm.setLayout(null);

		JLabel lblHelm = new JLabel("Helm");
		lblHelm.setBounds(0, 0, 60, 20);
		panelHelm.add(lblHelm);

		JComboBox<String> cmbHelm = new JComboBox(Leitura.nomesArquivos(folders.helm));
		cmbHelm.setBounds(70, 0, 200, 20);
		panelHelm.add(cmbHelm);
		cmbHelm.setBackground(Color.WHITE);

		JSpinner spinHelmRed = new JSpinner();
		spinHelmRed.setBounds(280, 0, 60, 20);
		panelHelm.add(spinHelmRed);

		JSpinner spinHelmGreen = new JSpinner();
		spinHelmGreen.setBounds(350, 0, 60, 20);
		panelHelm.add(spinHelmGreen);

		JSpinner spinHelmBlue = new JSpinner();
		spinHelmBlue.setBounds(420, 0, 60, 20);
		panelHelm.add(spinHelmBlue);

		JSpinner spinHelmAlpha = new JSpinner();
		spinHelmAlpha.setBounds(490, 0, 60, 20);
		panelHelm.add(spinHelmAlpha);

		JSpinner spinHelmHueSwap = new JSpinner();
		spinHelmHueSwap.setBounds(560, 0, 60, 20);
		panelHelm.add(spinHelmHueSwap);

		JButton btnHelmRandom = new JButton("Random");
		btnHelmRandom.setBounds(630, 0, 90, 20);
		panelHelm.add(btnHelmRandom);
		btnHelmRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				randomPartColor(helm);
			}
		});
		
		JCheckBox chckbxHelmLock = new JCheckBox("Lock");
		chckbxHelmLock.setBounds(726, 0, 54, 20);
		panelHelm.add(chckbxHelmLock);

		JPanel panelHair = new JPanel();
		panelHair.setLayout(null);
		panelHair.setBounds(10, 104, 780, 20);
		contentPane.add(panelHair);

		JLabel lblHair = new JLabel("Hair");
		lblHair.setBounds(0, 0, 60, 20);
		panelHair.add(lblHair);

		JComboBox<String> cmbHair = new JComboBox(Leitura.nomesArquivos(folders.hair));
		cmbHair.setBounds(70, 0, 200, 20);
		panelHair.add(cmbHair);
		cmbHair.setBackground(Color.WHITE);

		JSpinner spinHairRed = new JSpinner();
		spinHairRed.setBounds(280, 0, 60, 20);
		panelHair.add(spinHairRed);

		JSpinner spinHairGreen = new JSpinner();
		spinHairGreen.setBounds(350, 0, 60, 20);
		panelHair.add(spinHairGreen);

		JSpinner spinHairBlue = new JSpinner();
		spinHairBlue.setBounds(420, 0, 60, 20);
		panelHair.add(spinHairBlue);

		JSpinner spinHairAlpha = new JSpinner();
		spinHairAlpha.setBounds(490, 0, 60, 20);
		panelHair.add(spinHairAlpha);

		JSpinner spinHairHueSwap = new JSpinner();
		spinHairHueSwap.setBounds(560, 0, 60, 20);
		panelHair.add(spinHairHueSwap);

		JButton btnHairRandom = new JButton("Random");
		btnHairRandom.setBounds(630, 0, 90, 20);
		panelHair.add(btnHairRandom);
		btnHairRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(hair);
			}
		});
		
		JCheckBox chckbxHairLock = new JCheckBox("Lock");
		chckbxHairLock.setBounds(726, 0, 54, 20);
		panelHair.add(chckbxHairLock);

		JPanel panelEyes = new JPanel();
		panelEyes.setLayout(null);
		panelEyes.setBounds(10, 135, 780, 20);
		contentPane.add(panelEyes);

		JLabel lblEyes = new JLabel("Eyes");
		lblEyes.setBounds(0, 0, 60, 20);
		panelEyes.add(lblEyes);

		JComboBox<String> cmbEyes = new JComboBox(Leitura.nomesArquivos(folders.eyes));
		cmbEyes.setBounds(70, 0, 200, 20);
		panelEyes.add(cmbEyes);
		cmbEyes.setBackground(Color.WHITE);

		JSpinner spinEyesRed = new JSpinner();
		spinEyesRed.setBounds(280, 0, 60, 20);
		panelEyes.add(spinEyesRed);

		JSpinner spinEyesGreen = new JSpinner();
		spinEyesGreen.setBounds(350, 0, 60, 20);
		panelEyes.add(spinEyesGreen);

		JSpinner spinEyesBlue = new JSpinner();
		spinEyesBlue.setBounds(420, 0, 60, 20);
		panelEyes.add(spinEyesBlue);

		JSpinner spinEyesAlpha = new JSpinner();
		spinEyesAlpha.setBounds(490, 0, 60, 20);
		panelEyes.add(spinEyesAlpha);

		JSpinner spinEyesHueSwap = new JSpinner();
		spinEyesHueSwap.setBounds(560, 0, 60, 20);
		panelEyes.add(spinEyesHueSwap);

		JButton btnEyesRandom = new JButton("Random");
		btnEyesRandom.setBounds(630, 0, 90, 20);
		panelEyes.add(btnEyesRandom);
		btnEyesRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(eyes);
			}
		});
		
		JCheckBox chckbxEyesLock = new JCheckBox("Lock");
		chckbxEyesLock.setBounds(726, 0, 54, 20);
		panelEyes.add(chckbxEyesLock);

		JPanel panelFace = new JPanel();
		panelFace.setLayout(null);
		panelFace.setBounds(10, 166, 780, 20);
		contentPane.add(panelFace);

		JLabel lblFace = new JLabel("Face");
		lblFace.setBounds(0, 0, 60, 20);
		panelFace.add(lblFace);

		JComboBox<String> cmbFace = new JComboBox(Leitura.nomesArquivos(folders.faces));
		cmbFace.setBounds(70, 0, 200, 20);
		panelFace.add(cmbFace);
		cmbFace.setBackground(Color.WHITE);

		JSpinner spinFaceRed = new JSpinner();
		spinFaceRed.setBounds(280, 0, 60, 20);
		panelFace.add(spinFaceRed);

		JSpinner spinFaceGreen = new JSpinner();
		spinFaceGreen.setBounds(350, 0, 60, 20);
		panelFace.add(spinFaceGreen);

		JSpinner spinFaceBlue = new JSpinner();
		spinFaceBlue.setBounds(420, 0, 60, 20);
		panelFace.add(spinFaceBlue);

		JSpinner spinFaceAlpha = new JSpinner();
		spinFaceAlpha.setBounds(490, 0, 60, 20);
		panelFace.add(spinFaceAlpha);

		JSpinner spinFaceHueSwap = new JSpinner();
		spinFaceHueSwap.setBounds(560, 0, 60, 20);
		panelFace.add(spinFaceHueSwap);

		JButton btnFaceRandom = new JButton("Random");
		btnFaceRandom.setBounds(630, 0, 90, 20);
		panelFace.add(btnFaceRandom);
		btnFaceRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(face);
			}
		});
		
		JCheckBox chckbxFaceLock = new JCheckBox("Lock");
		chckbxFaceLock.setBounds(726, 0, 54, 20);
		panelFace.add(chckbxFaceLock);

		JPanel panelTorsoA = new JPanel();
		panelTorsoA.setLayout(null);
		panelTorsoA.setBounds(10, 197, 780, 20);
		contentPane.add(panelTorsoA);

		JLabel lblTorsoA = new JLabel("Torso A");
		lblTorsoA.setBounds(0, 0, 60, 20);
		panelTorsoA.add(lblTorsoA);

		JComboBox<String> cmbTorsoA = new JComboBox(Leitura.nomesArquivos(folders.torso));
		cmbTorsoA.setBounds(70, 0, 200, 20);
		panelTorsoA.add(cmbTorsoA);
		cmbTorsoA.setBackground(Color.WHITE);

		JSpinner spinTorsoARed = new JSpinner();
		spinTorsoARed.setBounds(280, 0, 60, 20);
		panelTorsoA.add(spinTorsoARed);

		JSpinner spinTorsoAGreen = new JSpinner();
		spinTorsoAGreen.setBounds(350, 0, 60, 20);
		panelTorsoA.add(spinTorsoAGreen);

		JSpinner spinTorsoABlue = new JSpinner();
		spinTorsoABlue.setBounds(420, 0, 60, 20);
		panelTorsoA.add(spinTorsoABlue);

		JSpinner spinTorsoAAlpha = new JSpinner();
		spinTorsoAAlpha.setBounds(490, 0, 60, 20);
		panelTorsoA.add(spinTorsoAAlpha);

		JSpinner spinTorsoAHueSwap = new JSpinner();
		spinTorsoAHueSwap.setBounds(560, 0, 60, 20);
		panelTorsoA.add(spinTorsoAHueSwap);

		JButton btnTorsoARandom = new JButton("Random");
		btnTorsoARandom.setBounds(630, 0, 90, 20);
		panelTorsoA.add(btnTorsoARandom);
		btnTorsoARandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(torsoA);
			}
		});
		
		JCheckBox chckbxTorsoALock = new JCheckBox("Lock");
		chckbxTorsoALock.setBounds(726, 0, 54, 20);
		panelTorsoA.add(chckbxTorsoALock);

		JPanel panelTorsoB = new JPanel();
		panelTorsoB.setLayout(null);
		panelTorsoB.setBounds(10, 228, 780, 20);
		contentPane.add(panelTorsoB);

		JLabel lblTorsoB = new JLabel("Torso B");
		lblTorsoB.setBounds(0, 0, 60, 20);
		panelTorsoB.add(lblTorsoB);

		JComboBox<String> cmbTorsoB = new JComboBox(Leitura.nomesArquivos(folders.torso));
		cmbTorsoB.setBounds(70, 0, 200, 20);
		panelTorsoB.add(cmbTorsoB);
		cmbTorsoB.setBackground(Color.WHITE);

		JSpinner spinTorsoBRed = new JSpinner();
		spinTorsoBRed.setBounds(280, 0, 60, 20);
		panelTorsoB.add(spinTorsoBRed);

		JSpinner spinTorsoBGreen = new JSpinner();
		spinTorsoBGreen.setBounds(350, 0, 60, 20);
		panelTorsoB.add(spinTorsoBGreen);

		JSpinner spinTorsoBBlue = new JSpinner();
		spinTorsoBBlue.setBounds(420, 0, 60, 20);
		panelTorsoB.add(spinTorsoBBlue);

		JSpinner spinTorsoBAlpha = new JSpinner();
		spinTorsoBAlpha.setBounds(490, 0, 60, 20);
		panelTorsoB.add(spinTorsoBAlpha);

		JSpinner spinTorsoBHueSwap = new JSpinner();
		spinTorsoBHueSwap.setBounds(560, 0, 60, 20);
		panelTorsoB.add(spinTorsoBHueSwap);

		JButton btnTorsoBRandom = new JButton("Random");
		btnTorsoBRandom.setBounds(630, 0, 90, 20);
		panelTorsoB.add(btnTorsoBRandom);
		btnTorsoBRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(torsoB);
			}
		});
		
		JCheckBox chckbxTorsoBLock = new JCheckBox("Lock");
		chckbxTorsoBLock.setBounds(726, 0, 54, 20);
		panelTorsoB.add(chckbxTorsoBLock);

		JPanel panelHands = new JPanel();
		panelHands.setLayout(null);
		panelHands.setBounds(10, 259, 780, 20);
		contentPane.add(panelHands);

		JLabel lblHands = new JLabel("Hands");
		lblHands.setBounds(0, 0, 60, 20);
		panelHands.add(lblHands);

		JComboBox<String> cmbHands = new JComboBox(Leitura.nomesArquivos(folders.hands));
		cmbHands.setBounds(70, 0, 200, 20);
		panelHands.add(cmbHands);
		cmbHands.setBackground(Color.WHITE);

		JSpinner spinHandsRed = new JSpinner();
		spinHandsRed.setBounds(280, 0, 60, 20);
		panelHands.add(spinHandsRed);

		JSpinner spinHandsGreen = new JSpinner();
		spinHandsGreen.setBounds(350, 0, 60, 20);
		panelHands.add(spinHandsGreen);

		JSpinner spinHandsBlue = new JSpinner();
		spinHandsBlue.setBounds(420, 0, 60, 20);
		panelHands.add(spinHandsBlue);

		JSpinner spinHandsAlpha = new JSpinner();
		spinHandsAlpha.setBounds(490, 0, 60, 20);
		panelHands.add(spinHandsAlpha);

		JSpinner spinHandsHueSwap = new JSpinner();
		spinHandsHueSwap.setBounds(560, 0, 60, 20);
		panelHands.add(spinHandsHueSwap);

		JButton btnHandsRandom = new JButton("Random");
		btnHandsRandom.setBounds(630, 0, 90, 20);
		panelHands.add(btnHandsRandom);
		btnHandsRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(hands);
			}
		});
		
		JCheckBox chckbxHandsLock = new JCheckBox("Lock");
		chckbxHandsLock.setBounds(726, 0, 54, 20);
		panelHands.add(chckbxHandsLock);

		JPanel panelLegsA = new JPanel();
		panelLegsA.setLayout(null);
		panelLegsA.setBounds(10, 290, 780, 20);
		contentPane.add(panelLegsA);

		JLabel lblLegsA = new JLabel("Legs A");
		lblLegsA.setBounds(0, 0, 60, 20);
		panelLegsA.add(lblLegsA);

		JComboBox<String> cmbLegsA = new JComboBox(Leitura.nomesArquivos(folders.legs));
		cmbLegsA.setBounds(70, 0, 200, 20);
		panelLegsA.add(cmbLegsA);
		cmbLegsA.setBackground(Color.WHITE);

		JSpinner spinLegsARed = new JSpinner();
		spinLegsARed.setBounds(280, 0, 60, 20);
		panelLegsA.add(spinLegsARed);

		JSpinner spinLegsAGreen = new JSpinner();
		spinLegsAGreen.setBounds(350, 0, 60, 20);
		panelLegsA.add(spinLegsAGreen);

		JSpinner spinLegsABlue = new JSpinner();
		spinLegsABlue.setBounds(420, 0, 60, 20);
		panelLegsA.add(spinLegsABlue);

		JSpinner spinLegsAAlpha = new JSpinner();
		spinLegsAAlpha.setBounds(490, 0, 60, 20);
		panelLegsA.add(spinLegsAAlpha);

		JSpinner spinLegsAHueSwap = new JSpinner();
		spinLegsAHueSwap.setBounds(560, 0, 60, 20);
		panelLegsA.add(spinLegsAHueSwap);

		JButton btnLegsARandom = new JButton("Random");
		btnLegsARandom.setBounds(630, 0, 90, 20);
		panelLegsA.add(btnLegsARandom);
		btnLegsARandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(legsA);
			}
		});
		
		JCheckBox chckbxLegsALock = new JCheckBox("Lock");
		chckbxLegsALock.setBounds(726, 0, 54, 20);
		panelLegsA.add(chckbxLegsALock);

		JPanel panelLegsB = new JPanel();
		panelLegsB.setLayout(null);
		panelLegsB.setBounds(10, 321, 780, 20);
		contentPane.add(panelLegsB);

		JLabel lblLegsB = new JLabel("Legs B");
		lblLegsB.setBounds(0, 0, 60, 20);
		panelLegsB.add(lblLegsB);

		JComboBox<String> cmbLegsB = new JComboBox(Leitura.nomesArquivos(folders.legs));
		cmbLegsB.setBounds(70, 0, 200, 20);
		panelLegsB.add(cmbLegsB);
		cmbLegsB.setBackground(Color.WHITE);

		JSpinner spinLegsBRed = new JSpinner();
		spinLegsBRed.setBounds(280, 0, 60, 20);
		panelLegsB.add(spinLegsBRed);

		JSpinner spinLegsBGreen = new JSpinner();
		spinLegsBGreen.setBounds(350, 0, 60, 20);
		panelLegsB.add(spinLegsBGreen);

		JSpinner spinLegsBBlue = new JSpinner();
		spinLegsBBlue.setBounds(420, 0, 60, 20);
		panelLegsB.add(spinLegsBBlue);

		JSpinner spinLegsBAlpha = new JSpinner();
		spinLegsBAlpha.setBounds(490, 0, 60, 20);
		panelLegsB.add(spinLegsBAlpha);

		JSpinner spinLegsBHueSwap = new JSpinner();
		spinLegsBHueSwap.setBounds(560, 0, 60, 20);
		panelLegsB.add(spinLegsBHueSwap);

		JButton btnLegsBRandom = new JButton("Random");
		btnLegsBRandom.setBounds(630, 0, 90, 20);
		panelLegsB.add(btnLegsBRandom);
		btnLegsBRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(legsB);
			}
		});
		
		JCheckBox chckbxLegsBLock = new JCheckBox("Lock");
		chckbxLegsBLock.setBounds(726, 0, 54, 20);
		panelLegsB.add(chckbxLegsBLock);

		JPanel panelBack = new JPanel();
		panelBack.setLayout(null);
		panelBack.setBounds(10, 352, 780, 20);
		contentPane.add(panelBack);

		JLabel lblBack = new JLabel("Back");
		lblBack.setBounds(0, 0, 60, 20);
		panelBack.add(lblBack);

		JComboBox<String> cmbBack = new JComboBox(Leitura.nomesArquivos(folders.back));
		cmbBack.setBounds(70, 0, 200, 20);
		panelBack.add(cmbBack);
		cmbBack.setBackground(Color.WHITE);

		JSpinner spinBackRed = new JSpinner();
		spinBackRed.setBounds(280, 0, 60, 20);
		panelBack.add(spinBackRed);

		JSpinner spinBackGreen = new JSpinner();
		spinBackGreen.setBounds(350, 0, 60, 20);
		panelBack.add(spinBackGreen);

		JSpinner spinBackBlue = new JSpinner();
		spinBackBlue.setBounds(420, 0, 60, 20);
		panelBack.add(spinBackBlue);

		JSpinner spinBackAlpha = new JSpinner();
		spinBackAlpha.setBounds(490, 0, 60, 20);
		panelBack.add(spinBackAlpha);

		JSpinner spinBackHueSwap = new JSpinner();
		spinBackHueSwap.setBounds(560, 0, 60, 20);
		panelBack.add(spinBackHueSwap);

		JButton btnBackRandom = new JButton("Random");
		btnBackRandom.setBounds(630, 0, 90, 20);
		panelBack.add(btnBackRandom);
		btnBackRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(back);
			}
		});
		
		JCheckBox chckbxBackLock = new JCheckBox("Lock");
		chckbxBackLock.setBounds(726, 0, 54, 20);
		panelBack.add(chckbxBackLock);

		JPanel panelShoes = new JPanel();
		panelShoes.setLayout(null);
		panelShoes.setBounds(10, 383, 780, 20);
		contentPane.add(panelShoes);

		JLabel lblShoes = new JLabel("Shoes");
		lblShoes.setBounds(0, 0, 60, 20);
		panelShoes.add(lblShoes);

		JComboBox<String> cmbShoes = new JComboBox(Leitura.nomesArquivos(folders.shoes));
		cmbShoes.setBounds(70, 0, 200, 20);
		panelShoes.add(cmbShoes);
		cmbShoes.setBackground(Color.WHITE);

		JSpinner spinShoesRed = new JSpinner();
		spinShoesRed.setBounds(280, 0, 60, 20);
		panelShoes.add(spinShoesRed);

		JSpinner spinShoesGreen = new JSpinner();
		spinShoesGreen.setBounds(350, 0, 60, 20);
		panelShoes.add(spinShoesGreen);

		JSpinner spinShoesBlue = new JSpinner();
		spinShoesBlue.setBounds(420, 0, 60, 20);
		panelShoes.add(spinShoesBlue);

		JSpinner spinShoesAlpha = new JSpinner();
		spinShoesAlpha.setBounds(490, 0, 60, 20);
		panelShoes.add(spinShoesAlpha);

		JSpinner spinShoesHueSwap = new JSpinner();
		spinShoesHueSwap.setBounds(560, 0, 60, 20);
		panelShoes.add(spinShoesHueSwap);

		JButton btnShoesRandom = new JButton("Random");
		btnShoesRandom.setBounds(630, 0, 90, 20);
		panelShoes.add(btnShoesRandom);
		btnShoesRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(shoes);
			}
		});
		
		JCheckBox chckbxShoesLock = new JCheckBox("Lock");
		chckbxShoesLock.setBounds(726, 0, 54, 20);
		panelShoes.add(chckbxShoesLock);
		
		this.parts[0] = body = new SpritePart("body", cmbBody, spinBodyRed, spinBodyGreen,
				spinBodyBlue, spinBodyAlpha, spinBodyHueSwap, chckbxBodyLock);
		this.parts[1] = helm = new SpritePart("helm", cmbHelm, spinHelmRed, spinHelmGreen,
				spinHelmBlue, spinHelmAlpha, spinHelmHueSwap, chckbxHelmLock);
		this.parts[2] = hair = new SpritePart("hair", cmbHair, spinHairRed, spinHairGreen,
				spinHairBlue, spinHairAlpha, spinHairHueSwap, chckbxHairLock);
		this.parts[3] = eyes = new SpritePart("eyes", cmbEyes, spinEyesRed, spinEyesGreen,
				spinEyesBlue, spinEyesAlpha, spinEyesHueSwap, chckbxEyesLock);
		this.parts[4] = face = new SpritePart("face", cmbFace, spinFaceRed, spinFaceGreen,
				spinFaceBlue, spinFaceAlpha, spinFaceHueSwap, chckbxFaceLock);
		this.parts[5] = torsoA = new SpritePart("torso A", cmbTorsoA, spinTorsoARed, spinTorsoAGreen,
				spinTorsoABlue, spinTorsoAAlpha, spinTorsoAHueSwap, chckbxTorsoALock);
		this.parts[6] = torsoB = new SpritePart("torso B", cmbTorsoB, spinTorsoBRed, spinTorsoBGreen,
				spinTorsoBBlue, spinTorsoBAlpha, spinTorsoBHueSwap, chckbxTorsoBLock);
		this.parts[7] = hands = new SpritePart("hands", cmbHands, spinHandsRed, spinHandsGreen,
				spinHandsBlue, spinHandsAlpha, spinHandsHueSwap, chckbxHandsLock);
		this.parts[8] = legsA = new SpritePart("legs A", cmbLegsA, spinLegsARed, spinLegsAGreen,
				spinLegsABlue, spinLegsAAlpha, spinLegsAHueSwap, chckbxLegsALock);
		this.parts[9] = legsB = new SpritePart("legs B", cmbLegsB, spinLegsBRed, spinLegsBGreen,
				spinLegsBBlue, spinLegsBAlpha, spinLegsBHueSwap, chckbxLegsBLock);
		this.parts[10] = back = new SpritePart("back", cmbBack, spinBackRed, spinBackGreen,
				spinBackBlue, spinBackAlpha, spinBackHueSwap, chckbxBackLock);
		this.parts[11] = shoes = new SpritePart("shoes", cmbShoes, spinShoesRed, spinShoesGreen,
				spinShoesBlue, spinShoesAlpha, spinShoesHueSwap, chckbxShoesLock);
		for (SpritePart part : this.parts) {
			configSpritePartUI(part);
		}

		JButton btnAtualizarPastas = new JButton("Update folders");
		btnAtualizarPastas.setBounds(450, 414, 130, 20);
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				updateFolders();
			}
		});

		JButton btnRandom = new JButton("Random Parts");
		btnRandom.setBounds(590, 414, 140, 20);
		btnRandom.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteRandom();
			}
		});
		contentPane.add(btnRandom);
		contentPane.add(btnAtualizarPastas);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 414, 60, 20);
		contentPane.add(lblName);

		txtNameSprite = new JTextField();
		txtNameSprite.setBounds(80, 414, 200, 20);
		contentPane.add(txtNameSprite);
		txtNameSprite.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(290, 414, 150, 20);
		btnSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				saveSprite();
			}
		});
		contentPane.add(btnSave);
	}

	/**
	 * Configures the UI components of a sprite part.
	 * @param part The part which UI components will be configured.
	 */
	void configSpritePartUI(SpritePart part) {
		part.getCmb().addItemListener(itemListener);

		part.red.setToolTipText("Red color of " + part.name);
		part.red.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		part.red.addChangeListener(changeListener);

		part.green.setToolTipText("Green color of " + part.name);
		part.green.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		part.green.addChangeListener(changeListener);

		part.blue.setToolTipText("Blue color of " + part.name);
		part.blue.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		part.blue.addChangeListener(changeListener);

		part.alpha.setToolTipText("Alpha of " + part.name);
		part.alpha.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		part.alpha.addChangeListener(changeListener);

		part.hueSwap.setToolTipText("Hue Swap of " + part.name);
		part.hueSwap.setModel(new SpinnerNumberModel(0, 0, 360, 1));
		part.hueSwap.addChangeListener(changeListener);
	}

	/**
	 * The listener of the combo boxes.
	 */
	ItemListener itemListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (shouldUpdate) updateSprite();
		}
	};

	/**
	 * The listener of the spinners.
	 */
	ChangeListener changeListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if (shouldUpdate) updateSprite();
		}
	};

	/**
	 * Updates the sprite with the selected parts.
	 */
	private void updateSprite() {
		int[][] sprite;
		back.updateColor(rgba);
		try {
			sprite = ImageFunctions.capeBack(Leitura.selecionarImagem(folders.back, back));
		} catch (WrongSizeException e) {
			sprite = ImageFunctions.getTransparency();
		}
		System.out.println("Tempos:");
		long tempo = System.nanoTime();
		body.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.body, body);
		System.out.println("Sobrepor corpo: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		eyes.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.eyes, eyes);
		System.out.println("Sobrepor eyes: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		legsB.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.legs, legsB);
		System.out.println("Sobrepor legsB: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		torsoB.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.torso, torsoB);
		System.out.println("Sobrepor torsoB: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		hands.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.hands, hands);
		System.out.println("Sobrepor hands: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		shoes.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.shoes, shoes);
		System.out.println("Sobrepor shoes: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		legsA.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.legs, legsA);
		System.out.println("Sobrepor legsA: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		torsoA.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.torso, torsoA);
		System.out.println("Sobrepor torsoA: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		face.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.faces, face);
		System.out.println("Sobrepor face: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		try {
			sprite = ImageFunctions.overlapImage(ImageFunctions.capeFront(Leitura.selecionarImagem(folders.back, back)), sprite);
		} catch (WrongSizeException e) {}
		System.out.println("Sobrepor back: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		hair.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.hair, hair);
		System.out.println("Sobrepor hair: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		helm.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, folders.helm, helm);
		System.out.println("Sobrepor helm: " + (System.nanoTime()-tempo));

		buffer = ImageFunctions.matrixToBuffer(sprite);
		this.sprite.label.setIcon(
				new ImageIcon(
						buffer.getScaledInstance(
								Dimensions.ZOOM * Dimensions.WIDTH,
								Dimensions.ZOOM * Dimensions.HEIGHT,
								Image.SCALE_AREA_AVERAGING
								)
						)
				);
	}

	/**
	 * Reads a image and overlaps another one with it.
	 * @param base The image to be overlapped.
	 * @param array The image array.
	 * @param part The sprite part of the overlapping image.
	 * @return The overlapped image.
	 */
	private int[][] overlapImageWithFile(int[][] base, File[] array, SpritePart part) {
		if (part.getCmb().getSelectedIndex() == 0) return base;
		try {
			int[][] image = Leitura.selecionarImagem(array, part);
			return ImageFunctions.overlapImage(image, base);
		} catch (WrongSizeException e) {
			e.treat();
			return base;
		}
	}

	/**
	 * Updates all combo boxes with the folders' current content.
	 */
	private void updateFolders() {
		folders = new Folders(imagesFolder);
		updateCmb(body, folders.body);
		updateCmb(helm, folders.helm);
		updateCmb(hair, folders.hair);
		updateCmb(eyes, folders.eyes);
		updateCmb(face, folders.faces);
		updateCmb(torsoA, folders.torso);
		updateCmb(torsoB, folders.torso);
		updateCmb(hands, folders.hands);
		updateCmb(legsA, folders.legs);
		updateCmb(legsB, folders.legs);
		updateCmb(back, folders.back);
		updateCmb(shoes, folders.shoes);
		JOptionPane.showMessageDialog(null, "Updated the folders");
	}

	/**
	 * Updates the combo box of an part to show the current filenames in the part folder.
	 * @param part The part which combo box will be updated.
	 * @param files The files currently in the part folder.
	 */
	void updateCmb(SpritePart part, File[] files) {
		part.getCmb().setModel(new DefaultComboBoxModel<String>(Leitura.nomesArquivos(files)));
	}

	/**
	 * Selects random sprite parts to create a random sprite.
	 */
	private void spriteRandom() {
		this.shouldUpdate = false;
		for (SpritePart part : this.parts) {
			part.selectRandomPart(random);
		}
		updateSprite();
		this.shouldUpdate = true;
	}

	/**
	 * Sets a random color for the part.
	 * @param part The part which colors will be changed.
	 */
	private void randomPartColor(SpritePart part) {
		this.shouldUpdate = false;
		part.red.setValue(random.nextInt((Integer) ((SpinnerNumberModel) part.red.getModel()).getMaximum() + 1));
		part.green.setValue(random.nextInt((Integer) ((SpinnerNumberModel) part.green.getModel()).getMaximum() + 1));
		part.blue.setValue(random.nextInt((Integer) ((SpinnerNumberModel) part.blue.getModel()).getMaximum() + 1));
		part.hueSwap.setValue(random.nextInt(361));
		updateSprite();
		this.shouldUpdate = true;
	}

	/**
	 * Saves the sprite generated.
	 * @throws HeadlessException If GraphicsEnvironment.isHeadless returns true.
	 */
	private void saveSprite() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Saved sprite as \"" + Escrita.saveSprite(folders, txtNameSprite.getText(), buffer) + "\"");
	}
}