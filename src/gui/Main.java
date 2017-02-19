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

import classes.ParteSprite;
import classes.Pastas;
import excecoes.TamanhoErradoException;
import funcoes.*;

import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Pastas pastas = new Pastas();
	private BufferedImage buffer;
	private static Random random = new Random();
	private ParteSprite corpo;
	private ParteSprite elmo;
	private ParteSprite cabelo;
	private ParteSprite olhos;
	private ParteSprite face;
	private ParteSprite roupa;
	private ParteSprite costas;
	private boolean deveAtualizar = true;

	private JPanel contentPane;
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

		JComboBox<String> cmbCorpo = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));
		
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
		
		corpo = new ParteSprite(cmbCorpo, null, null, null, null);
		
		JLabel lblCoresRgba = new JLabel("Cores RGBA");
		lblCoresRgba.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoresRgba.setBounds(280, 11, 230, 20);
		contentPane.add(lblCoresRgba);

		JLabel lblElmo = new JLabel("Elmo");
		lblElmo.setBounds(10, 42, 50, 20);
		contentPane.add(lblElmo);

		JComboBox<String> cmbElmo = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
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
		
		JSpinner spinRedElmo = new JSpinner();
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
		
		JSpinner spinGreenElmo = new JSpinner();
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
		
		JSpinner spinBlueElmo = new JSpinner();
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
		
		JSpinner spinAlfaElmo = new JSpinner();
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
		
		elmo = new ParteSprite(cmbElmo, spinRedElmo, spinGreenElmo, spinBlueElmo, spinAlfaElmo);

		JLabel lblCabelo = new JLabel("Cabelo");
		lblCabelo.setBounds(10, 73, 50, 20);
		contentPane.add(lblCabelo);

		JComboBox<String> cmbCabelo = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
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

		JSpinner spinRedCabelo = new JSpinner();
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
		
		JSpinner spinGreenCabelo = new JSpinner();
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
		
		JSpinner spinBlueCabelo = new JSpinner();
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
		
		cabelo = new ParteSprite(cmbCabelo, spinRedCabelo, spinGreenCabelo, spinBlueCabelo, null);
		
		JLabel lblOlhos = new JLabel("Olhos");
		lblOlhos.setBounds(10, 104, 50, 20);
		contentPane.add(lblOlhos);

		JComboBox<String> cmbOlhos = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
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

		JSpinner spinRedOlhos = new JSpinner();
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
		
		JSpinner spinGreenOlhos = new JSpinner();
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
		
		JSpinner spinBlueOlhos = new JSpinner();
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
		
		olhos = new ParteSprite(cmbOlhos, spinRedOlhos, spinGreenOlhos, spinBlueOlhos, null);

		JComboBox<String> cmbFace = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
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

		JSpinner spinRedFace = new JSpinner();
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
		
		JSpinner spinGreenFace = new JSpinner();
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
		
		JSpinner spinBlueFace = new JSpinner();
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
		
		JSpinner spinAlfaFace = new JSpinner();
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
		
		face = new ParteSprite(cmbFace, spinRedFace, spinGreenFace, spinBlueFace, spinAlfaFace);

		JLabel lblRoupa = new JLabel("Roupa");
		lblRoupa.setBounds(10, 166, 50, 20);
		contentPane.add(lblRoupa);

		JComboBox<String> cmbRoupa = new JComboBox(Arquivo.nomesArquivos(pastas.roupas));
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

		JSpinner spinRedRoupa = new JSpinner();
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
		
		JSpinner spinGreenRoupa = new JSpinner();
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
		
		JSpinner spinBlueRoupa = new JSpinner();
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
		
		JSpinner spinAlfaRoupa = new JSpinner();
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
		
		roupa = new ParteSprite(cmbRoupa, spinRedRoupa, spinGreenRoupa, spinBlueRoupa, spinAlfaRoupa);

		JComboBox<String> cmbCostas = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
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

		JSpinner spinRedCostas = new JSpinner();
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
		
		JSpinner spinGreenCostas = new JSpinner();
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
		
		JSpinner spinBlueCostas = new JSpinner();
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
		
		JSpinner spinAlfaCostas = new JSpinner();
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
		
		costas = new ParteSprite(cmbCostas, spinRedCostas, spinGreenCostas, spinBlueCostas, spinAlfaCostas);

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
		int[][] imagemCostas;
		try {
			imagemCostas = Arquivo.selecionarImagem(pastas.costas, costas);
		} catch (TamanhoErradoException e) {
			imagemCostas = e.tratar(Imagem.gerarTransparencia());
		}
		int[][] sprite = Imagem.capaAtras(imagemCostas);
		sprite = sobreporImagemArquivo(sprite, pastas.corpos, corpo);
		sprite = sobreporImagemArquivo(sprite, pastas.olhos, olhos);
		sprite = sobreporImagemArquivo(sprite, pastas.roupas, roupa);
		sprite = sobreporImagemArquivo(sprite, pastas.faces, face);
		sprite = Imagem.sobreporImagem(Imagem.capaFrente(imagemCostas), sprite);
		sprite = sobreporImagemArquivo(sprite, pastas.cabelos, cabelo);
		sprite = sobreporImagemArquivo(sprite, pastas.elmos, elmo);
		buffer = Imagem.matrizParaBuffer(sprite);
		lblSprite.setIcon(new ImageIcon(buffer.getScaledInstance(192, 256, Image.SCALE_AREA_AVERAGING)));
	}

	//Sobrepoe a imagem
	private static int[][] sobreporImagemArquivo(int[][] base, File[] array, ParteSprite parte)
			throws IOException {
		try {
			
			return Imagem.sobreporImagem(Arquivo.selecionarImagem(array, parte), base);
		} catch (TamanhoErradoException e) {
			return e.tratar(base);
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void atualizaPastas() {
		pastas = new Pastas();
		corpo.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.corpos));
		elmo.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.elmos));
		cabelo.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.cabelos));
		olhos.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.olhos));
		face.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.faces));
		roupa.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.roupas));
		costas.cmb = new JComboBox(Arquivo.nomesArquivos(pastas.costas));
	}

	//Seleciona aleatoriamente partes do sprite para criar um sprite aleatorio
	private void spriteAleatorio() {
		deveAtualizar = false;
		corpo.cmb.setSelectedIndex(random.nextInt(corpo.cmb.getItemCount()));
		elmo.cmb.setSelectedIndex(random.nextInt(elmo.cmb.getItemCount()));
		cabelo.cmb.setSelectedIndex(random.nextInt(cabelo.cmb.getItemCount()));
		olhos.cmb.setSelectedIndex(random.nextInt(olhos.cmb.getItemCount()));
		face.cmb.setSelectedIndex(random.nextInt(face.cmb.getItemCount()));
		roupa.cmb.setSelectedIndex(random.nextInt(roupa.cmb.getItemCount()));
		deveAtualizar = true;
		costas.cmb.setSelectedIndex(random.nextInt(costas.cmb.getItemCount()));
	}

	private void salvarSprite() throws HeadlessException, IOException {
		JOptionPane.showMessageDialog(null, "Sprite salvo com o nome \"" + Arquivo.salvarSprite(txtNomeSprite.getText(), buffer) + "\"");
	}
}
