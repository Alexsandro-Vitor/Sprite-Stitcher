package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import classes.SpritePart;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class PartPanel extends JPanel {
	private Generator generator;
	public SpritePart part;
	private JComboBox<String> cmb;
	private JSpinner spinAlpha;
	private JSpinner spinA;
	private JSpinner spinB;
	private JSpinner spinC;
	private JSpinner spinHueSwap;

	/**
	 * Create the panel.
	 */
	public PartPanel(Generator generator, String labelName, String tooltipName, String[] cmbOptions) {
		this.generator = generator;
		setLayout(null);
		
		JLabel lblPartName = new JLabel(labelName);
		lblPartName.setBounds(0, 0, 60, 20);
		add(lblPartName);
		
		cmb = new JComboBox(cmbOptions);
		cmb.setBounds(70, 0, 200, 20);
		add(cmb);
		cmb.setBackground(Color.WHITE);
		
		spinAlpha = new JSpinner();
		spinAlpha.setBounds(280, 0, 60, 20);
		add(spinAlpha);
		
		spinA = new JSpinner();
		spinA.setBounds(350, 0, 60, 20);
		add(spinA);
		
		spinB = new JSpinner();
		spinB.setBounds(420, 0, 60, 20);
		add(spinB);
		
		spinC = new JSpinner();
		spinC.setBounds(490, 0, 60, 20);
		add(spinC);
		
		spinHueSwap = new JSpinner();
		spinHueSwap.setBounds(560, 0, 60, 20);
		add(spinHueSwap);
		
		JButton btnRandom = new JButton("Random");
		btnRandom.setBounds(630, 0, 90, 20);
		add(btnRandom);
		btnRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				randomPartColor(part);
			}
		});
		
		JCheckBox chckbxLock = new JCheckBox("Lock");
		chckbxLock.setBounds(726, 0, 54, 20);
		add(chckbxLock);
		
		this.part = new SpritePart(tooltipName, cmb, spinA, spinB, spinC, spinAlpha, spinHueSwap, chckbxLock);
		this.configSpritePartUI();
	}
	
	/**
	 * Configures the UI components of the sprite part.
	 */
	void configSpritePartUI() {
		this.cmb.addItemListener(this.generator.itemListener);

		this.spinA.setToolTipText("Red color of " + this.part.name);
		this.spinA.setModel(this.part.red);
		this.spinA.addChangeListener(this.generator.changeListener);

		this.spinB.setToolTipText("Green color of " + this.part.name);
		this.spinB.setModel(this.part.green);
		this.spinB.addChangeListener(this.generator.changeListener);

		this.spinC.setToolTipText("Blue color of " + this.part.name);
		this.spinC.setModel(this.part.blue);
		this.spinC.addChangeListener(this.generator.changeListener);

		this.spinAlpha.setToolTipText("Alpha of " + this.part.name);
		this.spinAlpha.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		this.spinAlpha.addChangeListener(this.generator.changeListener);

		this.spinHueSwap.setToolTipText("Hue Swap of " + this.part.name);
		this.spinHueSwap.setModel(new SpinnerNumberModel(0, 0, 360, 1));
		this.spinHueSwap.addChangeListener(this.generator.changeListener);
	}

	/**
	 * Sets a random color for the part.
	 * @param part The part which colors will be changed.
	 */
	private void randomPartColor(SpritePart part) {
		this.generator.shouldUpdate = false;
		part.spinA.setValue(this.generator.random.nextInt((Integer) ((SpinnerNumberModel) part.spinA.getModel()).getMaximum() + 1));
		part.spinB.setValue(this.generator.random.nextInt((Integer) ((SpinnerNumberModel) part.spinB.getModel()).getMaximum() + 1));
		part.spinC.setValue(this.generator.random.nextInt((Integer) ((SpinnerNumberModel) part.spinC.getModel()).getMaximum() + 1));
		part.hueSwap.setValue(this.generator.random.nextInt(361));
		this.generator.updateSprite();
		this.generator.shouldUpdate = true;
	}

	/**
	 * Updates the combo box to show the current filenames in the part folder.
	 * @param files The files currently in the part folder.
	 */
	void updateCmb(String[] files) {
		this.cmb.setModel(new DefaultComboBoxModel<String>(files));
	}
}
