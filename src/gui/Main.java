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

import excecoes.TamanhoErradoException;
import funcoes.*;

import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Pastas pastas = new Pastas();
	private BufferedImage buffer;
	private static Random random = new Random();

	private JPanel contentPane;
	private JComboBox<String> cmbCorpo;
	private JSpinner spinCorpo;
	private JComboBox<String> cmbElmo;
	private JSpinner spinElmo;
	private JComboBox<String> cmbCabelo;
	private JComboBox<String> cmbOlhos;
	private JComboBox<String> cmbFace;
	private JSpinner spinFace;
	private JComboBox<String> cmbRoupa;
	private JSpinner spinRoupa;
	private JComboBox<String> cmbCostas;
	private JSpinner spinCostas;
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

		cmbCorpo = new JComboBox<String>(Arquivo.nomesArquivos(pastas.corpos));
		cmbCorpo.setBackground(Color.WHITE);
		cmbCorpo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
					System.out.print("Chamou atualiza");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCorpo.setBounds(70, 11, 252, 20);
		contentPane.add(cmbCorpo);

		spinCorpo = new JSpinner();
		spinCorpo.setEnabled(false);
		spinCorpo.setToolTipText("Opacidade do Sprite");
		spinCorpo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinCorpo.setBounds(332, 11, 50, 20);
		contentPane.add(spinCorpo);

		JLabel lblElmo = new JLabel("Elmo");
		lblElmo.setBounds(10, 42, 50, 20);
		contentPane.add(lblElmo);

		cmbElmo = new JComboBox<String>(Arquivo.nomesArquivos(pastas.elmos));
		cmbElmo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbElmo.setBackground(Color.WHITE);
		cmbElmo.setBounds(70, 42, 252, 20);
		contentPane.add(cmbElmo);

		spinElmo = new JSpinner();
		spinElmo.setToolTipText("Opacidade do elmo");
		spinElmo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinElmo.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinElmo.setBounds(332, 42, 50, 20);
		contentPane.add(spinElmo);

		JLabel lblCabelo = new JLabel("Cabelo");
		lblCabelo.setBounds(10, 73, 50, 20);
		contentPane.add(lblCabelo);

		cmbCabelo = new JComboBox<String>(Arquivo.nomesArquivos(pastas.cabelos));
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

		cmbOlhos = new JComboBox<String>(Arquivo.nomesArquivos(pastas.olhos));
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
		lblFace.setBounds(10, 135, 50, 20);
		contentPane.add(lblFace);

		cmbFace = new JComboBox<String>(Arquivo.nomesArquivos(pastas.faces));
		cmbFace.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbFace.setBackground(Color.WHITE);
		cmbFace.setBounds(70, 135, 252, 20);
		contentPane.add(cmbFace);

		spinFace = new JSpinner();
		spinFace.setToolTipText("Opacidade do item da face");
		spinFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		spinFace.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinFace.setBounds(332, 135, 50, 20);
		contentPane.add(spinFace);

		JLabel lblRoupa = new JLabel("Roupa");
		lblRoupa.setBounds(10, 166, 50, 20);
		contentPane.add(lblRoupa);

		cmbRoupa = new JComboBox<String>(Arquivo.nomesArquivos(pastas.roupas));
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
		lblCostas.setBounds(10, 197, 50, 20);
		contentPane.add(lblCostas);

		cmbCostas = new JComboBox<String>(Arquivo.nomesArquivos(pastas.costas));
		cmbCostas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbCostas.setBackground(Color.WHITE);
		cmbCostas.setBounds(70, 197, 252, 20);
		contentPane.add(cmbCostas);

		spinCostas = new JSpinner();
		spinCostas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		spinCostas.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinCostas.setBounds(332, 197, 50, 20);
		contentPane.add(spinCostas);

		JButton btnAtualizarPastas = new JButton("Atualizar pastas");
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				atualizaPastas();
			}
		});
		btnAtualizarPastas.setBounds(10, 228, 181, 20);
		contentPane.add(btnAtualizarPastas);

		JButton btnAleatorio = new JButton("Aleat\u00F3rio");
		btnAleatorio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteAleatorio();
			}
		});
		btnAleatorio.setBounds(201, 228, 181, 20);
		contentPane.add(btnAleatorio);

		lblSprite = new JLabel("");
		lblSprite.setBackground(Color.WHITE);
		lblSprite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSprite.setBounds(392, 11, 192, 256);
		contentPane.add(lblSprite);

		JLabel lblNomeDoSprite = new JLabel("Nome do Sprite");
		lblNomeDoSprite.setBounds(10, 259, 90, 20);
		contentPane.add(lblNomeDoSprite);

		txtNomeSprite = new JTextField();
		txtNomeSprite.setBounds(110, 259, 182, 20);
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
		btnSalvar.setBounds(302, 259, 80, 20);
		contentPane.add(btnSalvar);
	}

	//Atualiza o sprite com as partes selecionadas
	private void atualizaSprite() throws IOException {
		int[][] costas;
		try {
			costas = Arquivo.selecionarImagem(pastas.costas, cmbCostas, (int)spinCostas.getValue());
		} catch (TamanhoErradoException e) {
			costas = e.tratar(Imagem.gerarTransparencia());
		}
		int[][] sprite = Imagem.capaAtras(costas);
		sprite = sobreporImagemArquivo(sprite, pastas.corpos, cmbCorpo, 255);
		sprite = sobreporImagemArquivo(sprite, pastas.olhos, cmbOlhos, 255);
		sprite = sobreporImagemArquivo(sprite, pastas.roupas, cmbRoupa, (int)spinRoupa.getValue());
		sprite = sobreporImagemArquivo(sprite, pastas.faces, cmbFace, (int)spinFace.getValue());
		sprite = sobreporImagemArquivo(sprite, pastas.cabelos, cmbCabelo, 255);
		sprite = sobreporImagemArquivo(sprite, pastas.elmos, cmbElmo, (int)spinElmo.getValue());
		sprite = Imagem.sobreporImagem(Imagem.capaFrente(costas), sprite);
		buffer = Imagem.matrizParaBuffer(sprite);
		lblSprite.setIcon(new ImageIcon(buffer.getScaledInstance(192, 256, Image.SCALE_AREA_AVERAGING)));
	}

	//Sobrepoe a imagem
	private int[][] sobreporImagemArquivo(int[][] base, File[] array, JComboBox<String> cmb, int alfa) throws IOException {
		try {
			return Imagem.sobreporImagem(Arquivo.selecionarImagem(array, cmb, alfa), base);
		} catch (TamanhoErradoException e) {
			return e.tratar(base);
		}
	}

	private void atualizaPastas() {
		pastas = new Pastas();
		cmbCorpo = new JComboBox<String>(Arquivo.nomesArquivos(pastas.corpos));
		cmbElmo = new JComboBox<String>(Arquivo.nomesArquivos(pastas.elmos));
		cmbCabelo = new JComboBox<String>(Arquivo.nomesArquivos(pastas.cabelos));
		cmbOlhos = new JComboBox<String>(Arquivo.nomesArquivos(pastas.olhos));
		cmbFace = new JComboBox<String>(Arquivo.nomesArquivos(pastas.faces));
		cmbRoupa = new JComboBox<String>(Arquivo.nomesArquivos(pastas.roupas));
		cmbCostas = new JComboBox<String>(Arquivo.nomesArquivos(pastas.costas));
	}

	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteAleatorio() {
		cmbCorpo.setSelectedIndex(random.nextInt(cmbCorpo.getItemCount()));
		cmbElmo.setSelectedIndex(random.nextInt(cmbElmo.getItemCount()));
		cmbCabelo.setSelectedIndex(random.nextInt(cmbCabelo.getItemCount()));
		cmbOlhos.setSelectedIndex(random.nextInt(cmbOlhos.getItemCount()));
		cmbFace.setSelectedIndex(random.nextInt(cmbFace.getItemCount()));
		cmbRoupa.setSelectedIndex(random.nextInt(cmbRoupa.getItemCount()));
		cmbCostas.setSelectedIndex(random.nextInt(cmbCostas.getItemCount()));
	}

	private void salvarSprite() throws HeadlessException, IOException {
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + Arquivo.salvarSprite(txtNomeSprite.getText(), buffer) + "\"");
	}
}
