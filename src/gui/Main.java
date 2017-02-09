package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

import funcoes.Imagem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main extends JFrame {

	private File[] corpos = new File("imagens\\corpos").listFiles();
	private File[] elmos = new File("imagens\\elmos").listFiles();
	private File[] cabelos = new File("imagens\\cabelos").listFiles();
	private File[] olhos = new File("imagens\\olhos").listFiles();
	private File[] faces = new File("imagens\\faces").listFiles();
	private File[] roupas = new File("imagens\\roupas").listFiles();
	private File[] costas = new File("imagens\\costas").listFiles();
	private BufferedImage buffer;
	private static Random random = new Random();
	
	private JPanel contentPane;
	private JComboBox cmbCorpo;
	private JSpinner spinCorpo;
	private JComboBox cmbCabelo;
	private JComboBox cmbOlhos;
	private JComboBox cmbRoupa;
	private JSpinner spinRoupa;
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 319);
		setResizable(false);
		setTitle("Gerador de Sprite");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCorpo = new JLabel("Corpo");
		lblCorpo.setBounds(10, 11, 50, 20);
		contentPane.add(lblCorpo);

		cmbCorpo = new JComboBox(nomesArquivos(corpos));
		cmbCorpo.setBackground(Color.WHITE);
		cmbCorpo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCorpo.setBounds(70, 11, 252, 20);
		contentPane.add(cmbCorpo);
		
		spinCorpo = new JSpinner();
		spinCorpo.setToolTipText("Opacidade do Sprite");
		spinCorpo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinCorpo.setBounds(332, 11, 50, 20);
		contentPane.add(spinCorpo);
		
		JLabel lblElmo = new JLabel("Elmo");
		lblElmo.setEnabled(false);
		lblElmo.setBounds(10, 42, 50, 20);
		contentPane.add(lblElmo);
		
		JComboBox cmbElmo = new JComboBox(nomesArquivos(elmos));
		cmbElmo.setEnabled(false);
		cmbElmo.setBackground(Color.WHITE);
		cmbElmo.setBounds(70, 42, 252, 20);
		contentPane.add(cmbElmo);
		
		JSpinner spinElmo = new JSpinner();
		spinElmo.setEnabled(false);
		spinElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinElmo.setBounds(332, 42, 50, 20);
		contentPane.add(spinElmo);

		JLabel lblCabelo = new JLabel("Cabelo");
		lblCabelo.setBounds(10, 73, 50, 20);
		contentPane.add(lblCabelo);

		cmbCabelo = new JComboBox(nomesArquivos(cabelos));
		cmbCabelo.setBackground(Color.WHITE);
		cmbCabelo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCabelo.setBounds(70, 73, 252, 20);
		contentPane.add(cmbCabelo);

		JLabel lblOlhos = new JLabel("Olhos");
		lblOlhos.setBounds(10, 104, 50, 20);
		contentPane.add(lblOlhos);

		cmbOlhos = new JComboBox(nomesArquivos(olhos));
		cmbOlhos.setBackground(Color.WHITE);
		cmbOlhos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbOlhos.setBounds(70, 104, 252, 20);
		contentPane.add(cmbOlhos);
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setEnabled(false);
		lblFace.setBounds(10, 135, 50, 20);
		contentPane.add(lblFace);
		
		JComboBox cmbFace = new JComboBox(nomesArquivos(faces));
		cmbFace.setEnabled(false);
		cmbFace.setBackground(Color.WHITE);
		cmbFace.setBounds(70, 135, 252, 20);
		contentPane.add(cmbFace);
		
		JSpinner spinFace = new JSpinner();
		spinFace.setEnabled(false);
		spinFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinFace.setBounds(332, 135, 50, 20);
		contentPane.add(spinFace);

		JLabel lblRoupa = new JLabel("Roupa");
		lblRoupa.setBounds(10, 166, 50, 20);
		contentPane.add(lblRoupa);

		cmbRoupa = new JComboBox(nomesArquivos(roupas));
		cmbRoupa.setBackground(Color.WHITE);
		cmbRoupa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbRoupa.setBounds(70, 166, 252, 20);
		contentPane.add(cmbRoupa);
		
		JButton btnAleatorio = new JButton("Aleat\u00F3rio");
		btnAleatorio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteAleatorio();
			}
		});
		
		spinRoupa = new JSpinner();
		spinRoupa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRoupa.setToolTipText("Opacidade da roupa");
		spinRoupa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRoupa.setBounds(332, 166, 50, 20);
		contentPane.add(spinRoupa);
		
		JLabel lblCostas = new JLabel("Costas");
		lblCostas.setEnabled(false);
		lblCostas.setBounds(10, 197, 50, 20);
		contentPane.add(lblCostas);
		
		JComboBox cmbCostas = new JComboBox(nomesArquivos(costas));
		cmbCostas.setEnabled(false);
		cmbCostas.setBackground(Color.WHITE);
		cmbCostas.setBounds(70, 197, 252, 20);
		contentPane.add(cmbCostas);
		
		JSpinner spinCostas = new JSpinner();
		spinCostas.setEnabled(false);
		spinCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinCostas.setBounds(332, 197, 50, 20);
		contentPane.add(spinCostas);
		btnAleatorio.setBounds(10, 228, 372, 20);
		contentPane.add(btnAleatorio);

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
		
		txtNomeSprite = new JTextField();
		txtNomeSprite.setBounds(110, 259, 182, 20);
		contentPane.add(txtNomeSprite);
		txtNomeSprite.setColumns(10);
		btnSalvar.setBounds(302, 259, 80, 20);
		contentPane.add(btnSalvar);

		lblSprite = new JLabel("");
		lblSprite.setBackground(Color.WHITE);
		lblSprite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSprite.setBounds(392, 11, 192, 256);
		contentPane.add(lblSprite);
		
		JLabel lblNomeDoSprite = new JLabel("Nome do Sprite");
		lblNomeDoSprite.setBounds(10, 259, 90, 20);
		contentPane.add(lblNomeDoSprite);
	}

	private static String[] nomesArquivos(File[] array) {
		String[] saida = new String[array.length + 1];
		saida[0] = "[Vazio]";
		for (int i = 1; i < saida.length; i++) {
			saida[i] = array[i-1].getName();
		}
		return saida;
	}
	
	//Atualiza o sprite com as partes selecionadas
	private void atualizaSprite() throws IOException {
		int[][] imagemCorpo = selecionarImagem(corpos, cmbCorpo, 255);
		imagemCorpo = sobreporImagemArquivo(imagemCorpo, olhos, cmbOlhos, 255);
		imagemCorpo = sobreporImagemArquivo(imagemCorpo, roupas, cmbRoupa, (int)spinRoupa.getValue());
		System.out.println("alfa = " + (int)spinRoupa.getValue());
		imagemCorpo = sobreporImagemArquivo(imagemCorpo, cabelos, cmbCabelo, 255);
		buffer = Imagem.matrizParaBuffer(imagemCorpo);
		lblSprite.setIcon(new ImageIcon(buffer.getScaledInstance(192, 256, Image.SCALE_AREA_AVERAGING)));
	}
	
	//Sobrepoe a imagem
	private int[][] sobreporImagemArquivo(int[][] base, File[] array, JComboBox cmb, int alfa) throws IOException {
		int[][] acima = selecionarImagem(array, cmb, alfa);
		return Imagem.sobreporImagem(acima, base);
	}
	
	//Pega a imagem selecionada pelo comboBox
	private int[][] selecionarImagem(File[] array, JComboBox cmb, int alfa) throws IOException {
		int[][] matriz;
		try {
			matriz = Imagem.lerImagem(array[cmb.getSelectedIndex() - 1], alfa);
		} catch (ArrayIndexOutOfBoundsException e) {
			matriz = Imagem.gerarTransparencia();
		}
		return matriz;
	}
	
	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteAleatorio() {
		cmbCorpo.setSelectedIndex(random.nextInt(cmbCorpo.getItemCount()));
		cmbCabelo.setSelectedIndex(random.nextInt(cmbCabelo.getItemCount()));
		cmbOlhos.setSelectedIndex(random.nextInt(cmbOlhos.getItemCount()));
		cmbRoupa.setSelectedIndex(random.nextInt(cmbRoupa.getItemCount()));
	}
	
	//Salva o sprite gerado e exibe uma mensagem avisando
	private void salvarSprite() throws IOException {
		String nome = nomeSprite();
		ImageIO.write(buffer, "PNG", new File("imagens\\sprites\\" + nome));
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + nome + "\"");
	}
	
	//Determina o nome com o qual o sprite será salvo
	private String nomeSprite() {
		try {
			new FileReader("imagens\\sprites\\" + txtNomeSprite.getText() + ".png");
			for (int i = 2; ; i++) {
				try {
					new FileReader("imagens\\sprites\\" + txtNomeSprite.getText() + '('+ i + ").png");
				} catch (FileNotFoundException e) {
					return txtNomeSprite.getText() + '(' + i + ").png";
				}
			}
		} catch (FileNotFoundException e) {
			return txtNomeSprite.getText() + ".png";
		}
		
	}
}
