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
	private boolean deveAtualizar = true;

	private JPanel contentPane;
	private JComboBox<String> cmbCorpo;
	private JComboBox<String> cmbElmo;
	private JSpinner spinRedElmo;
	private JSpinner spinGreenElmo;
	private JSpinner spinBlueElmo;
	private JSpinner spinAlfaElmo;
	private JComboBox<String> cmbCabelo;
	private JSpinner spinRedCabelo;
	private JSpinner spinGreenCabelo;
	private JSpinner spinBlueCabelo;
	private JComboBox<String> cmbOlhos;
	private JSpinner spinRedOlhos;
	private JSpinner spinGreenOlhos;
	private JSpinner spinBlueOlhos;
	private JComboBox<String> cmbFace;
	private JSpinner spinRedFace;
	private JSpinner spinGreenFace;
	private JSpinner spinBlueFace;
	private JSpinner spinAlfaFace;
	private JComboBox<String> cmbRoupa;
	private JSpinner spinRedRoupa;
	private JSpinner spinGreenRoupa;
	private JSpinner spinBlueRoupa;
	private JSpinner spinAlfaRoupa;
	private JComboBox<String> cmbCostas;
	private JSpinner spinRedCostas;
	private JSpinner spinGreenCostas;
	private JSpinner spinBlueCostas;
	private JSpinner spinAlfaCostas;
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
		setBounds(100, 100, 728, 319);
		setResizable(false);
		setTitle("Gerador de Sprite");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCorpo = new JLabel("Corpo");
		lblCorpo.setBounds(10, 11, 50, 20);
		contentPane.add(lblCorpo);

		cmbCorpo = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));
		
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
		cmbCorpo.setBounds(70, 11, 200, 20);
		contentPane.add(cmbCorpo);
		
		JLabel lblCoresRgba = new JLabel("Cores RGBA");
		lblCoresRgba.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoresRgba.setBounds(280, 11, 230, 20);
		contentPane.add(lblCoresRgba);

		JLabel lblElmo = new JLabel("Elmo");
		lblElmo.setBounds(10, 42, 50, 20);
		contentPane.add(lblElmo);

		cmbElmo = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
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
		cmbElmo.setBounds(70, 42, 200, 20);
		contentPane.add(cmbElmo);
		
		spinRedElmo = new JSpinner();
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
		spinRedElmo.setBounds(280, 42, 50, 20);
		contentPane.add(spinRedElmo);
		
		spinGreenElmo = new JSpinner();
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
		spinGreenElmo.setBounds(340, 42, 50, 20);
		contentPane.add(spinGreenElmo);
		
		spinBlueElmo = new JSpinner();
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
		spinBlueElmo.setBounds(400, 42, 50, 20);
		contentPane.add(spinBlueElmo);
		
		spinAlfaElmo = new JSpinner();
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
		spinAlfaElmo.setBounds(460, 42, 50, 20);
		contentPane.add(spinAlfaElmo);

		JLabel lblCabelo = new JLabel("Cabelo");
		lblCabelo.setBounds(10, 73, 50, 20);
		contentPane.add(lblCabelo);

		cmbCabelo = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
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
		cmbCabelo.setBounds(70, 73, 200, 20);
		contentPane.add(cmbCabelo);

		spinRedCabelo = new JSpinner();
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
		spinRedCabelo.setBounds(280, 73, 50, 20);
		contentPane.add(spinRedCabelo);
		
		spinGreenCabelo = new JSpinner();
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
		spinGreenCabelo.setBounds(340, 73, 50, 20);
		contentPane.add(spinGreenCabelo);
		
		spinBlueCabelo = new JSpinner();
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
		spinBlueCabelo.setBounds(400, 73, 50, 20);
		contentPane.add(spinBlueCabelo);
		
		JLabel lblOlhos = new JLabel("Olhos");
		lblOlhos.setBounds(10, 104, 50, 20);
		contentPane.add(lblOlhos);

		cmbOlhos = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
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
		cmbOlhos.setBounds(70, 104, 200, 20);
		contentPane.add(cmbOlhos);

		spinRedOlhos = new JSpinner();
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
		spinRedOlhos.setBounds(280, 104, 50, 20);
		contentPane.add(spinRedOlhos);
		
		spinGreenOlhos = new JSpinner();
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
		spinGreenOlhos.setBounds(340, 104, 50, 20);
		contentPane.add(spinGreenOlhos);
		
		spinBlueOlhos = new JSpinner();
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
		spinBlueOlhos.setBounds(400, 104, 50, 20);
		contentPane.add(spinBlueOlhos);
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setBounds(10, 135, 50, 20);
		contentPane.add(lblFace);

		cmbFace = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
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
		cmbFace.setBounds(70, 135, 200, 20);
		contentPane.add(cmbFace);

		spinRedFace = new JSpinner();
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
		spinRedFace.setBounds(280, 135, 50, 20);
		contentPane.add(spinRedFace);
		
		spinGreenFace = new JSpinner();
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
		spinGreenFace.setBounds(340, 135, 50, 20);
		contentPane.add(spinGreenFace);
		
		spinBlueFace = new JSpinner();
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
		spinBlueFace.setBounds(400, 135, 50, 20);
		contentPane.add(spinBlueFace);
		
		spinAlfaFace = new JSpinner();
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
		spinAlfaFace.setBounds(460, 135, 50, 20);
		contentPane.add(spinAlfaFace);

		JLabel lblRoupa = new JLabel("Roupa");
		lblRoupa.setBounds(10, 166, 50, 20);
		contentPane.add(lblRoupa);

		cmbRoupa = new JComboBox(Arquivo.nomesArquivos(pastas.roupas));
		cmbRoupa.setBackground(Color.WHITE);
		cmbRoupa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		cmbRoupa.setBounds(70, 166, 200, 20);
		contentPane.add(cmbRoupa);

		spinRedRoupa = new JSpinner();
		spinRedRoupa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinRedRoupa.setToolTipText("Cor vermelha da roupa");
		spinRedRoupa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinRedRoupa.setBounds(280, 166, 50, 20);
		contentPane.add(spinRedRoupa);
		
		spinGreenRoupa = new JSpinner();
		spinGreenRoupa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinGreenRoupa.setToolTipText("Cor verde da roupa");
		spinGreenRoupa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinGreenRoupa.setBounds(340, 166, 50, 20);
		contentPane.add(spinGreenRoupa);
		
		spinBlueRoupa = new JSpinner();
		spinBlueRoupa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinBlueRoupa.setToolTipText("Cor azul da roupa");
		spinBlueRoupa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinBlueRoupa.setBounds(400, 166, 50, 20);
		contentPane.add(spinBlueRoupa);
		
		spinAlfaRoupa = new JSpinner();
		spinAlfaRoupa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if (deveAtualizar) atualizaSprite();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		spinAlfaRoupa.setToolTipText("Opacidade da roupa");
		spinAlfaRoupa.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spinAlfaRoupa.setBounds(460, 166, 50, 20);
		contentPane.add(spinAlfaRoupa);

		JLabel lblCostas = new JLabel("Costas");
		lblCostas.setBounds(10, 197, 50, 20);
		contentPane.add(lblCostas);

		cmbCostas = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
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
		cmbCostas.setBounds(70, 197, 200, 20);
		contentPane.add(cmbCostas);

		spinRedCostas = new JSpinner();
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
		spinRedCostas.setBounds(280, 197, 50, 20);
		contentPane.add(spinRedCostas);
		
		spinGreenCostas = new JSpinner();
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
		spinGreenCostas.setBounds(340, 197, 50, 20);
		contentPane.add(spinGreenCostas);
		
		spinBlueCostas = new JSpinner();
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
		spinBlueCostas.setBounds(400, 197, 50, 20);
		contentPane.add(spinBlueCostas);
		
		spinAlfaCostas = new JSpinner();
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
		spinAlfaCostas.setBounds(460, 197, 50, 20);
		contentPane.add(spinAlfaCostas);

		JButton btnAtualizarPastas = new JButton("Atualizar pastas");
		btnAtualizarPastas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				atualizaPastas();
			}
		});
		btnAtualizarPastas.setBounds(10, 228, 245, 20);
		contentPane.add(btnAtualizarPastas);

		JButton btnAleatorio = new JButton("Aleat\u00F3rio");
		btnAleatorio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				spriteAleatorio();
			}
		});
		btnAleatorio.setBounds(265, 228, 245, 20);
		contentPane.add(btnAleatorio);

		lblSprite = new JLabel("");
		lblSprite.setBackground(Color.WHITE);
		lblSprite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSprite.setBounds(520, 11, 192, 256);
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
			costas = Arquivo.selecionarImagem(pastas.costas, cmbCostas, (int)spinRedCostas.getValue(), (int)spinGreenCostas.getValue(),
					(int)spinBlueCostas.getValue(), (int)spinAlfaCostas.getValue());
		} catch (TamanhoErradoException e) {
			costas = e.tratar(Imagem.gerarTransparencia());
		}
		int[][] sprite = Imagem.capaAtras(costas);
		sprite = sobreporImagemArquivo(sprite, pastas.corpos, cmbCorpo, 255, 255, 255, 255);
		sprite = sobreporImagemArquivo(sprite, pastas.olhos, cmbOlhos, (int)spinRedOlhos.getValue(), (int)spinGreenOlhos.getValue(),
				(int)spinBlueOlhos.getValue(), 255);
		sprite = sobreporImagemArquivo(sprite, pastas.roupas, cmbRoupa, (int)spinRedRoupa.getValue(), (int)spinGreenRoupa.getValue(),
				(int)spinBlueRoupa.getValue(), (int)spinAlfaRoupa.getValue());
		sprite = sobreporImagemArquivo(sprite, pastas.faces, cmbFace, (int)spinRedFace.getValue(), (int)spinGreenFace.getValue(),
				(int)spinBlueFace.getValue(), (int)spinAlfaFace.getValue());
		sprite = Imagem.sobreporImagem(Imagem.capaFrente(costas), sprite);
		sprite = sobreporImagemArquivo(sprite, pastas.cabelos, cmbCabelo, (int)spinRedCabelo.getValue(), (int)spinGreenCabelo.getValue(),
				(int)spinBlueCabelo.getValue(), 255);
		sprite = sobreporImagemArquivo(sprite, pastas.elmos, cmbElmo, (int)spinRedElmo.getValue(), (int)spinGreenElmo.getValue(),
				(int)spinBlueElmo.getValue(), (int)spinAlfaElmo.getValue());
		buffer = Imagem.matrizParaBuffer(sprite);
		lblSprite.setIcon(new ImageIcon(buffer.getScaledInstance(192, 256, Image.SCALE_AREA_AVERAGING)));
	}

	//Sobrepoe a imagem
	private static int[][] sobreporImagemArquivo(int[][] base, File[] array, JComboBox<String> cmb, int red, int green, int blue, int alfa)
			throws IOException {
		try {
			
			return Imagem.sobreporImagem(Arquivo.selecionarImagem(array, cmb, red, green, blue, alfa), base);
		} catch (TamanhoErradoException e) {
			return e.tratar(base);
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void atualizaPastas() {
		pastas = new Pastas();
		cmbCorpo = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));
		cmbElmo = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
		cmbCabelo = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
		cmbOlhos = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
		cmbFace = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
		cmbRoupa = new JComboBox(Arquivo.nomesArquivos(pastas.roupas));
		cmbCostas = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
	}

	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteAleatorio() {
		deveAtualizar = false;
		cmbCorpo.setSelectedIndex(random.nextInt(cmbCorpo.getItemCount()));
		cmbElmo.setSelectedIndex(random.nextInt(cmbElmo.getItemCount()));
		cmbCabelo.setSelectedIndex(random.nextInt(cmbCabelo.getItemCount()));
		cmbOlhos.setSelectedIndex(random.nextInt(cmbOlhos.getItemCount()));
		cmbFace.setSelectedIndex(random.nextInt(cmbFace.getItemCount()));
		cmbRoupa.setSelectedIndex(random.nextInt(cmbRoupa.getItemCount()));
		deveAtualizar = true;
		cmbCostas.setSelectedIndex(random.nextInt(cmbCostas.getItemCount()));
	}

	private void salvarSprite() throws HeadlessException, IOException {
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + Arquivo.salvarSprite(txtNomeSprite.getText(), buffer) + "\"");
	}
}
