package gui;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.ComboBoxModel;
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
import functions.*;
import classes.Folders;

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
	public Random random = new Random();
	private JLabel lblA;
	private JLabel lblB;
	private JLabel lblC;
	private JLabel lblD;
	private SpritePart[] parts = new SpritePart[12];
	private PartPanel body;
	private PartPanel helm;
	private PartPanel hair;
	private PartPanel eyes;
	private PartPanel face;
	private PartPanel torsoA;
	private PartPanel torsoB;
	private PartPanel hands;
	private PartPanel legsA;
	private PartPanel legsB;
	private PartPanel back;
	private PartPanel shoes;
	public boolean shouldUpdate = true;
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

		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setBounds(290, 11, 60, 20);
		lblAlpha.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAlpha);

		lblA = new JLabel("Red");
		lblA.setBounds(360, 11, 60, 20);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblA);

		lblB = new JLabel("Green");
		lblB.setBounds(430, 11, 60, 20);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblB);

		lblC = new JLabel("Blue");
		lblC.setBounds(500, 11, 60, 20);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblC);

		lblD = new JLabel("Hue Swap");
		lblD.setBounds(570, 11, 60, 20);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblD);

		JButton btnHSB = new JButton("HSB");
		btnHSB.setBounds(640, 11, 90, 20);
		btnHSB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rgba = !rgba;
				if (rgba) {
					btnHSB.setText("HSB");
					lblA.setText("Red");
					lblB.setText("Green");
					lblC.setText("Blue");
				} else {
					btnHSB.setText("RGB");
					lblA.setText("Hue");
					lblB.setText("Saturation");
					lblC.setText("Bright");
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

		JComboBox<String> cmbColorChange = new JComboBox();
		cmbColorChange.setBounds(740, 11, 50, 20);
		setColorChangeOptions(cmbColorChange);
		contentPane.add(cmbColorChange);

		body = new PartPanel(this, "Body", "body", folders.files(Folders.PartTypes.BODY));
		body.setBounds(10, 42, 780, 20);
		contentPane.add(body);
		
		helm = new PartPanel(this, "Helm", "helm", folders.files(Folders.PartTypes.HELM));
		helm.setBounds(10, 73, 780, 20);
		contentPane.add(helm);

		hair = new PartPanel(this, "Hair", "hair", folders.files(Folders.PartTypes.HAIR));
		hair.setBounds(10, 104, 780, 20);
		contentPane.add(hair);

		eyes = new PartPanel(this, "Eyes", "eyes", folders.files(Folders.PartTypes.EYES));
		eyes.setBounds(10, 135, 780, 20);
		contentPane.add(eyes);

		face = new PartPanel(this, "Face", "face", folders.files(Folders.PartTypes.FACES));
		face.setBounds(10, 166, 780, 20);
		contentPane.add(face);

		torsoA = new PartPanel(this, "Torso A", "torso A", folders.files(Folders.PartTypes.TORSO));
		torsoA.setBounds(10, 197, 780, 20);
		contentPane.add(torsoA);

		torsoB = new PartPanel(this, "Torso B", "torso B", folders.files(Folders.PartTypes.TORSO));
		torsoB.setLayout(null);
		torsoB.setBounds(10, 228, 780, 20);
		contentPane.add(torsoB);

		hands = new PartPanel(this, "Hands", "hands", folders.files(Folders.PartTypes.HANDS));
		hands.setBounds(10, 259, 780, 20);
		contentPane.add(hands);

		legsA = new PartPanel(this, "Legs A", "legs A", folders.files(Folders.PartTypes.LEGS));
		legsA.setBounds(10, 290, 780, 20);
		contentPane.add(legsA);

		legsB = new PartPanel(this, "Legs B", "legs B", folders.files(Folders.PartTypes.LEGS));
		legsB.setBounds(10, 321, 780, 20);
		contentPane.add(legsB);

		back = new PartPanel(this, "Back", "back", folders.files(Folders.PartTypes.BACK));
		back.setBounds(10, 352, 780, 20);
		contentPane.add(back);

		shoes = new PartPanel(this, "Shoes", "shoes", folders.files(Folders.PartTypes.SHOES));
		shoes.setBounds(10, 383, 780, 20);
		contentPane.add(shoes);

		this.parts[0] = body.part;
		this.parts[1] = helm.part;
		this.parts[2] = hair.part;
		this.parts[3] = eyes.part;
		this.parts[4] = face.part;
		this.parts[5] = torsoA.part;
		this.parts[6] = torsoB.part;
		this.parts[7] = hands.part;
		this.parts[8] = legsA.part;
		this.parts[9] = legsB.part;
		this.parts[10] = back.part;
		this.parts[11] = shoes.part;

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

	private String lastColorMode;
	void setColorChangeOptions(JComboBox cmb) {
		String[] options = {"RGB", "HSB", "Palette"};
		cmb.setModel(new DefaultComboBoxModel<String>(options));
		cmb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Last color mode: " + lastColorMode);
					switch ((String)e.getItem()) {
					case "RGB":
						System.out.println("RGB");
						lblA.setText("Red");
						lblB.setText("Green");
						lblC.setText("Blue");
						lblD.setText("Hue Swap");
						break;
					case "HSB":
						System.out.println("HSB");
						lblA.setText("Hue");
						lblB.setText("Saturation");
						lblC.setText("Bright");
						lblD.setText("Hue Swap");
						break;
					case "Palette":
						System.out.println("Palette");
						lblA.setText("Original");
						lblA.setBounds(lblA.getBounds().x, lblA.getBounds().y, lblA.getBounds().width * 2, lblA.getBounds().height);
						lblB.setText("");
						lblC.setText("New");
						lblD.setText("");
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					lastColorMode = (String)e.getItem();
				}
			}
		});
	}

	/**
	 * The listener of the combo boxes.
	 */
	public ItemListener itemListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (shouldUpdate) updateSprite();
		}
	};

	/**
	 * The listener of the spinners.
	 */
	public ChangeListener changeListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if (shouldUpdate) updateSprite();
		}
	};

	/**
	 * Updates the sprite with the selected parts.
	 */
	public void updateSprite() {
		int[][] sprite;
		back.part.updateColor(rgba);
		try {
			sprite = ImageFunctions.capeBack(Reading.selectImage(folders, back.part));
		} catch (WrongSizeException e) {
			sprite = ImageFunctions.getTransparency();
		}
		System.out.println("Tempos:");
		long tempo = System.nanoTime();
		body.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, body.part);
		System.out.println("Sobrepor corpo: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		eyes.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, eyes.part);
		System.out.println("Sobrepor eyes: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		legsB.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, legsB.part);
		System.out.println("Sobrepor legsB: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		torsoB.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, torsoB.part);
		System.out.println("Sobrepor torsoB: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		hands.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, hands.part);
		System.out.println("Sobrepor hands: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		shoes.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, shoes.part);
		System.out.println("Sobrepor shoes: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		legsA.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, legsA.part);
		System.out.println("Sobrepor legsA: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		torsoA.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, torsoA.part);
		System.out.println("Sobrepor torsoA: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		face.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, face.part);
		System.out.println("Sobrepor face: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		try {
			sprite = ImageFunctions.overlapImage(ImageFunctions.capeFront(Reading.selectImage(folders, back.part)), sprite);
		} catch (WrongSizeException e) {}
		System.out.println("Sobrepor back: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		hair.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, hair.part);
		System.out.println("Sobrepor hair: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		helm.part.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, helm.part);
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
	private int[][] overlapImageWithFile(int[][] base, SpritePart part) {
		if (part.getCmb().getSelectedIndex() == 0) return base;
		try {
			int[][] image = Reading.selectImage(folders, part);
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
		body.updateCmb(folders.files(Folders.PartTypes.BODY));
		helm.updateCmb(folders.files(Folders.PartTypes.HELM));
		hair.updateCmb(folders.files(Folders.PartTypes.HAIR));
		eyes.updateCmb(folders.files(Folders.PartTypes.EYES));
		face.updateCmb(folders.files(Folders.PartTypes.FACES));
		torsoA.updateCmb(folders.files(Folders.PartTypes.TORSO));
		torsoB.updateCmb(folders.files(Folders.PartTypes.TORSO));
		hands.updateCmb(folders.files(Folders.PartTypes.HANDS));
		legsA.updateCmb(folders.files(Folders.PartTypes.LEGS));
		legsB.updateCmb(folders.files(Folders.PartTypes.LEGS));
		back.updateCmb(folders.files(Folders.PartTypes.BACK));
		shoes.updateCmb(folders.files(Folders.PartTypes.SHOES));
		JOptionPane.showMessageDialog(null, "Updated the folders");
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
	 * Saves the sprite generated.
	 * @throws HeadlessException If GraphicsEnvironment.isHeadless returns true.
	 */
	private void saveSprite() throws HeadlessException {
		try {
			JOptionPane.showMessageDialog(null, "Saved sprite as \"" + Writing.saveSprite(folders, txtNameSprite.getText(), buffer) + "\"");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}