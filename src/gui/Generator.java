package gui;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.image.BufferedImage;

import java.io.IOException;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import classes.Dimensions;
import exceptions.WrongSizeException;
import functions.*;
import classes.Folders;

import javax.swing.event.ChangeEvent;
import java.awt.Font;

@SuppressWarnings("serial")
/**
 * Class which manages the UI for selecting the parts and colors.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Generator extends JFrame {

	private Sprite sprite;
	public Folders folders;
	private BufferedImage buffer = ImageFunctions.matrixToBuffer(ImageFunctions.getTransparency());
	public Random random = new Random();
	private JLabel lblA;
	private JLabel lblB;
	private JLabel lblC;
	private JLabel lblD;
	private JLabel lblOriginal;
	private JLabel lblNew;
	private PartPanel[] panels = new PartPanel[12];
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
	boolean rgba = true;

	private JPanel contentPane;
	private JTextField txtNameSprite;

	/**
	 * Create the frame.
	 */
	public Generator(Sprite sprite, String imagesFolder) {
		this.sprite = sprite;
		this.folders = new Folders(imagesFolder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 816, 485);
		setTitle("Sprite Generator");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblColorMode = new JLabel("Color Change Mode");
		lblColorMode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblColorMode.setBounds(10, 11, 110, 20);
		contentPane.add(lblColorMode);

		JComboBox<String> cmbColorChange = new JComboBox<String>();
		cmbColorChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbColorChange.setBackground(Color.WHITE);
		cmbColorChange.setBounds(130, 11, 150, 20);
		setColorChangeOptions(cmbColorChange);
		contentPane.add(cmbColorChange);

		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlpha.setBounds(290, 11, 60, 20);
		lblAlpha.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAlpha);

		lblA = new JLabel("Red");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblA.setBounds(360, 11, 60, 20);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblA);

		lblB = new JLabel("Green");
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblB.setBounds(430, 11, 60, 20);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblB);

		lblC = new JLabel("Blue");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblC.setBounds(500, 11, 60, 20);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblC);

		lblD = new JLabel("Hue Swap");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblD.setBounds(570, 11, 60, 20);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblD);

		lblOriginal = new JLabel("Original");
		lblOriginal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOriginal.setBounds(360, 11, 180, 20);
		lblOriginal.setHorizontalAlignment(SwingConstants.CENTER);

		lblNew = new JLabel("New");
		lblNew.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNew.setBounds(550, 11, 180, 20);
		lblNew.setHorizontalAlignment(SwingConstants.CENTER);

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

		face = new PartPanel(this, "Face", "face", folders.files(Folders.PartTypes.FACE));
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

		this.panels[0] = body;
		this.panels[1] = helm;
		this.panels[2] = hair;
		this.panels[3] = eyes;
		this.panels[4] = face;
		this.panels[5] = torsoA;
		this.panels[6] = torsoB;
		this.panels[7] = hands;
		this.panels[8] = legsA;
		this.panels[9] = legsB;
		this.panels[10] = back;
		this.panels[11] = shoes;

		JButton btnAtualizarPastas = new JButton("Update folders");
		btnAtualizarPastas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarPastas.setBounds(450, 414, 130, 20);
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				updateFolders();
			}
		});

		JButton btnRandom = new JButton("Random Parts");
		btnRandom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRandom.setBounds(590, 414, 140, 20);
		btnRandom.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteRandom();
			}
		});
		contentPane.add(btnRandom);
		contentPane.add(btnAtualizarPastas);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setBounds(10, 414, 60, 20);
		contentPane.add(lblName);

		txtNameSprite = new JTextField();
		txtNameSprite.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNameSprite.setBounds(80, 414, 200, 20);
		contentPane.add(txtNameSprite);
		txtNameSprite.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setBounds(290, 414, 150, 20);
		btnSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				saveSprite();
			}
		});
		contentPane.add(btnSave);
	}

	private String lastColorMode;
	void setColorChangeOptions(JComboBox<String> cmb) {
		String[] options = {"RGB", "HSB", "Palette"};
		cmb.setModel(new DefaultComboBoxModel<String>(options));
		cmb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Last color mode: " + lastColorMode);
					switch ((String)e.getItem()) {
					case "RGB":
						System.out.println("RGB");
						swapLabelsPaletteToColor();
						lblA.setText("Red");
						lblB.setText("Green");
						lblC.setText("Blue");
						lblD.setText("Hue Swap");
						
						rgba = true;
						shouldUpdate = false;
						for (PartPanel part : panels) {
							part.setColorMode();
							part.updateSpinners(rgba);
						}
						updateSprite();
						shouldUpdate = true;
						break;
					case "HSB":
						System.out.println("HSB");
						swapLabelsPaletteToColor();
						lblA.setText("Hue");
						lblB.setText("Saturation");
						lblC.setText("Bright");
						lblD.setText("Hue Swap");
						
						rgba = false;
						shouldUpdate = false;
						for (PartPanel part : panels) {
							part.setColorMode();
							part.updateSpinners(rgba);
						}
						updateSprite();
						shouldUpdate = true;
						break;
					case "Palette":
						System.out.println("Palette");
						swapLabelsColorToPalette();
						shouldUpdate = false;
						for (PartPanel part : panels) {
							part.setPaletteMode();
						}
						updateSprite();
						shouldUpdate = true;
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					lastColorMode = (String)e.getItem();
				}
			}
		});
	}
	
	/**
	 * Swap the labels to use the palette selection ones.
	 */
	private void swapLabelsColorToPalette() {
		contentPane.remove(lblA);
		contentPane.remove(lblB);
		contentPane.remove(lblC);
		contentPane.remove(lblD);
		contentPane.add(lblOriginal);
		contentPane.add(lblNew);
		
		contentPane.revalidate();
		contentPane.repaint();
		
	}
	
	/**
	 * Swap the labels to use the color selection ones.
	 */
	private void swapLabelsPaletteToColor() {
		contentPane.remove(lblOriginal);
		contentPane.remove(lblNew);
		contentPane.add(lblA);
		contentPane.add(lblB);
		contentPane.add(lblC);
		contentPane.add(lblD);
		
		contentPane.revalidate();
		contentPane.repaint();
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
		back.updateColor(rgba);
		try {
			sprite = ImageFunctions.capeBack(Reading.selectImage(folders, back));
		} catch (WrongSizeException e) {
			sprite = ImageFunctions.getTransparency();
		}
		System.out.println("Tempos:");
		long tempo = System.nanoTime();
		body.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, body);
		System.out.println("Sobrepor corpo: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		eyes.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, eyes);
		System.out.println("Sobrepor eyes: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		legsB.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, legsB);
		System.out.println("Sobrepor legsB: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		torsoB.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, torsoB);
		System.out.println("Sobrepor torsoB: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		hands.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, hands);
		System.out.println("Sobrepor hands: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		shoes.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, shoes);
		System.out.println("Sobrepor shoes: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		legsA.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, legsA);
		System.out.println("Sobrepor legsA: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		torsoA.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, torsoA);
		System.out.println("Sobrepor torsoA: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		face.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, face);
		System.out.println("Sobrepor face: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		try {
			sprite = ImageFunctions.overlapImage(ImageFunctions.capeFront(Reading.selectImage(folders, back)), sprite);
		} catch (WrongSizeException e) {}
		System.out.println("Sobrepor back: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		hair.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, hair);
		System.out.println("Sobrepor hair: " + (System.nanoTime()-tempo));

		tempo = System.nanoTime();
		helm.updateColor(rgba);
		sprite = overlapImageWithFile(sprite, helm);
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
	private int[][] overlapImageWithFile(int[][] base, PartPanel part) {
		if (part.cmb.getSelectedIndex() == 0) return base;
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
		String[] palettes = folders.files(Folders.PartTypes.PALETTES);
		body.updateCmb(folders.files(Folders.PartTypes.BODY), palettes);
		helm.updateCmb(folders.files(Folders.PartTypes.HELM), palettes);
		hair.updateCmb(folders.files(Folders.PartTypes.HAIR), palettes);
		eyes.updateCmb(folders.files(Folders.PartTypes.EYES), palettes);
		face.updateCmb(folders.files(Folders.PartTypes.FACE), palettes);
		torsoA.updateCmb(folders.files(Folders.PartTypes.TORSO), palettes);
		torsoB.updateCmb(folders.files(Folders.PartTypes.TORSO), palettes);
		hands.updateCmb(folders.files(Folders.PartTypes.HANDS), palettes);
		legsA.updateCmb(folders.files(Folders.PartTypes.LEGS), palettes);
		legsB.updateCmb(folders.files(Folders.PartTypes.LEGS), palettes);
		back.updateCmb(folders.files(Folders.PartTypes.BACK), palettes);
		shoes.updateCmb(folders.files(Folders.PartTypes.SHOES), palettes);
		JOptionPane.showMessageDialog(null, "Updated the folders");
	}

	/**
	 * Selects random sprite parts to create a random sprite.
	 */
	private void spriteRandom() {
		this.shouldUpdate = false;
		for (PartPanel panel : this.panels) {
			panel.selectRandomPart(random);
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
			String fileName = Writing.saveSprite(folders, txtNameSprite.getText(), buffer);
			Writing.saveLicense(folders, fileName, this.assemblePartLicenses());
			JOptionPane.showMessageDialog(null, "Saved sprite as \"" + fileName + "\"");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private String assemblePartLicenses() throws IOException {
		String assembledLicense = "";
		for (PartPanel panel : this.panels) {
			String selectedItem = (String)panel.cmb.getSelectedItem();
			if (selectedItem != null) {
				String license = Reading.getLicenses(this.folders, panel);
				assembledLicense += selectedItem + ":\n" + license + "\n\n";
			}
		}
		return assembledLicense;
	}
}