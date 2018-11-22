package Default;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

public class principal extends JFrame implements ActionListener {

	private JButton Carregar, Suaviza, SAIR, Limiar,Detec;
	private Dimension d1 = new Dimension(175,60);
	private Dimension SCREENSIZE = new Dimension(1000, 600);
	private Font fBotoes = new Font("Arial", Font.BOLD, 25);

	private ImageIcon imagen = new ImageIcon("");
	private ImageIcon imagen2 = new ImageIcon("");

	private JLabel L1,L2;
	private JLabel imageLabel = new JLabel(imagen);
	private JLabel imageLabel2 = new JLabel(imagen);
	private JLabel label=new JLabel();
	private JLabel label2=new JLabel();
	private JLabel label3=new JLabel();

	private int [][] imgmat = null;
	private int [][] imgtmp = null;
	private int [][] imgtmp2 = null;

	private filtros filtros = new filtros();
	private matrizes matrizes = new matrizes();
	private convolucao convolucao = new convolucao();
	private BufferedImage imgAtual = null;
	private Imagem bufimg = new Imagem();

	private JRadioButton rad1 = new JRadioButton("passabaixa",true);
	private JRadioButton rad2 = new JRadioButton("gaussiano");
	private JRadioButton rad5 = new JRadioButton("Prewitt",true);
	private JRadioButton rad6 = new JRadioButton("Sobel");
	private JRadioButton rad3 = new JRadioButton("3x3",true);
	private JRadioButton rad4 = new JRadioButton("5x5");
	private ButtonGroup grupo1 = new ButtonGroup();
	private ButtonGroup grupo2 = new ButtonGroup();
	private ButtonGroup grupo3 = new ButtonGroup();

	private ImageIcon icon = new ImageIcon("src/icone/Icone.jpg");

	private int tamanho=3,filtro1=0,filtro2=2;

	public principal() {

		setTitle("Processamento de imagens");
		setSize(SCREENSIZE);
		setLocationRelativeTo(null);
		setResizable(false);
		setFocusableWindowState(true);
		setFocusableWindowState(true);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		rad1.setBorderPainted(true);
		rad2.setBorderPainted(true);
		rad3.setBorderPainted(true);
		rad4.setBorderPainted(true);
		rad5.setBorderPainted(true);
		rad6.setBorderPainted(true);

		rad1.setLocation(15, 450);
		rad1.setSize(125,50);
		rad1.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		rad1.setForeground(new Color(225, 112, 85));
		rad1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		rad2.setLocation(15, 500);
		rad2.setSize(125,50);
		rad2.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		rad2.setForeground(new Color(225, 112, 85));
		rad2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		rad3.setLocation(140, 450);
		rad3.setSize(100,50);
		rad3.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		rad3.setForeground(new Color(225, 112, 85));
		rad3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		rad4.setLocation(140, 500);
		rad4.setSize(100,50);
		rad4.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		rad4.setForeground(new Color(225, 112, 85));
		rad4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		rad5.setLocation(240, 450);
		rad5.setSize(130,50);
		rad5.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		rad5.setForeground(new Color(225, 112, 85));
		rad5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		rad6.setLocation(240, 500);
		rad6.setSize(130,50);
		rad6.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		rad6.setForeground(new Color(225, 112, 85));
		rad6.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		label = new JLabel("Suavização", JLabel.CENTER);
		label.setLocation(15, 430);
		label.setSize(125,20);
		label.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label.setOpaque(true);
		label.setBackground(new Color(225, 112, 85));
		label.setForeground(Color.white);
		label2 = new JLabel("Tamanho", JLabel.CENTER);
		label2.setLocation(140, 430);
		label2.setSize(100,20);
		label2.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		label2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label2.setOpaque(true);
		label2.setBackground(new Color(225, 112, 85));
		label2.setForeground(Color.white);
		label3 = new JLabel("Detecção de Bordas", JLabel.CENTER);
		label3.setLocation(240, 430);
		label3.setSize(130,20);
		label3.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
		label3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		label3.setOpaque(true);
		label3.setBackground(new Color(225, 112, 85));
		label3.setForeground(Color.white);

		imageLabel.setLocation(15, 70);
		imageLabel.setSize(350,350);
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		imageLabel2.setLocation(615, 70);
		imageLabel2.setSize(350,350);
		imageLabel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		L1 = new JLabel("Imagem Original", JLabel.CENTER);
		L1.setLocation(15, 25);
		L1.setSize(350,40);
		L1.setFont(new Font("Source Sans Pro", Font.BOLD, 36));
		L1.setForeground(new Color(225, 112, 85));

		L2 = new JLabel("Imagem Modificada", JLabel.CENTER);
		L2.setLocation(620, 25);
		L2.setSize(350,40);
		L2.setFont(new Font("Source Sans Pro", Font.BOLD, 36));
		L2.setForeground(new Color(225, 112, 85));

		Suaviza = new JButton("Suavização");
		Suaviza.setLocation(400,170);
		Suaviza.setSize(175,60);
		Suaviza.setFont(fBotoes);
		Suaviza.setBorder(BorderFactory.createLineBorder(new Color(250, 177, 160)));
		Suaviza.setBackground(new Color(225, 112, 85));
		Suaviza.setForeground(Color.white);
		Suaviza.setFocusable(false);
		Suaviza.addActionListener(this);

		Detec = new JButton("Bordas");
		Detec.setLocation(400,270);
		Detec.setSize(175,60);
		Detec.setFont(fBotoes);
		Detec.setBorder(BorderFactory.createLineBorder(new Color(250, 177, 160)));
		Detec.setBackground(new Color(225, 112, 85));
		Detec.setForeground(Color.white);
		Detec.setFocusable(false);
		Detec.addActionListener(this);

		Limiar = new JButton("Limiar");
		Limiar.setLocation(400,370);
		Limiar.setSize(175,60);
		Limiar.setFont(fBotoes);
		Limiar.setBorder(BorderFactory.createLineBorder(new Color(250, 177, 160)));
		Limiar.setBackground(new Color(225, 112, 85));
		Limiar.setForeground(Color.white);
		Limiar.setFocusable(false);
		Limiar.addActionListener(this);


		SAIR = new JButton("Sair");
		SAIR.setLocation(400,470);
		SAIR.setSize(d1);
		SAIR.setFont(fBotoes);
		SAIR.setBorder(BorderFactory.createLineBorder(new Color(250, 177, 160)));
		SAIR.setBackground(new Color(225, 112, 85));
		SAIR.setForeground(Color.white);
		SAIR.setFocusable(false);
		SAIR.addActionListener(this);        


		Carregar = new JButton("Carregar img");
		Carregar.setLocation(400,70);
		Carregar.setSize(d1);
		Carregar.setFont(fBotoes);
		Carregar.setBorder(BorderFactory.createLineBorder(new Color(250, 177, 160)));
		Carregar.setBackground(new Color(225, 112, 85));
		Carregar.setForeground(Color.white);
		Carregar.setFocusable(false);
		Carregar.addActionListener(this);     

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				fecharJanela();
			}
		});

		getContentPane().setLayout(null);

		getContentPane().add(imageLabel);
		getContentPane().add(imageLabel2);
		getContentPane().add(L1);
		getContentPane().add(L2);
		getContentPane().add(label);
		getContentPane().add(label2);
		getContentPane().add(label3);
		getContentPane().add(Suaviza);
		getContentPane().add(Detec);
		getContentPane().add(Limiar);
		getContentPane().add(SAIR);
		getContentPane().add(Carregar);
		getContentPane().add(rad1);
		getContentPane().add(rad2);
		getContentPane().add(rad3);
		getContentPane().add(rad4);
		getContentPane().add(rad5);
		getContentPane().add(rad6);
		grupo1.add(rad1);
		grupo1.add(rad2);
		grupo2.add(rad3);
		grupo2.add(rad4);
		grupo3.add(rad5);
		grupo3.add(rad6);
		rad1.addActionListener(suavil);
		rad2.addActionListener(suavil);
		rad3.addActionListener(tamanhol);
		rad4.addActionListener(tamanhol);
		rad5.addActionListener(bordasl);
		rad6.addActionListener(bordasl);

		setIconImage(icon.getImage());

		setVisible(true);
	}

	public void fecharJanela() {
		if(JOptionPane.showConfirmDialog(this, "Deseja realmente sair?",
				"CONFIRMAR PARA SAIR", JOptionPane.INFORMATION_MESSAGE) == 0) {
			System.exit(0);
		}
	}

	ActionListener suavil = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			AbstractButton aButton = (AbstractButton) actionEvent.getSource();
			if (aButton.getText()=="passabaixa") {
				filtro2=0;
			}else {
				filtro2=1;
			}
		}
	};
	ActionListener bordasl = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			AbstractButton aButton = (AbstractButton) actionEvent.getSource();
			if (aButton.getText()=="Prewitt") {
				filtro2=2;
			}else {
				filtro2=3;
			}
		}
	};
	ActionListener tamanhol = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			AbstractButton aButton = (AbstractButton) actionEvent.getSource();
			if (aButton.getText()=="3x3") {
				tamanho=3;
			}else {
				tamanho=5;
			}
		}
	};

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == Carregar) {
			carregar();
		}
		else if(e.getSource() == SAIR) {
			fecharJanela();
		}

		else if(e.getSource() == Suaviza) {

			// verifica se uma imagem já foi carregada
			if(imgmat != null) {
				// carregando o filtro de suavização dos arquivos contidos no projeto
				double[][] filtroSuavizacao = filtros.criarFiltro(tamanho, filtro1);
				// convolução entre o filtro de suavização e a imagem no formato matricial
				imgmat = convolucao.convolucao(filtroSuavizacao, imgmat);

				// convertendo a imagem no formato matricial para BufferedImage
				imgAtual = bufimg.createImage(imgmat);

				// carregando a imagem na interface
				imagen2 = new ImageIcon(imgAtual);
				imageLabel2.setIcon(imagen2);

				// mudando a escala da imagem para exibição na interface
				mudarEscala();
			}

			else {
				JOptionPane.showMessageDialog(null,
						"Antes de aplicar o filtro de suavização, é necessário carregar uma imagem no programa.",
						"Imagem ainda não carregada!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		else if(e.getSource()== Detec) {

			// verifica se uma imagem já foi carregada
			if(imgmat != null) {
				// carregando o filtro de detecção de bordas dos arquivos contidos no projeto
				double[][] filtroBordas = filtros.criarFiltro(tamanho, filtro2);
				// convolução entre o filtro de detecção de bordas e a imagem no formato matricial
				imgtmp = convolucao.convolucao(filtroBordas, imgmat);

				// fazendo a rotação do filtro de detecção de bordas para obter sua versão horizontal
				filtroBordas = matrizes.rodarMatriz(filtroBordas);
				// convolução entre o filtro de detecção de bordas horizontal e a imagem no formato matricial
				imgtmp2 = convolucao.convolucao(filtroBordas, imgmat);

				// achando a magnitude entre o resultado das convoluções com o filtro horizontal e vertical através da distância euclidiana
				for(int x = 0; x < imgmat.length; x++)
					for(int y = 0; y < imgmat[0].length; y++)
						imgmat[x][y] = (int) Math.sqrt((imgtmp[x][y]*imgtmp[x][y]) + (imgtmp2[x][y]*imgtmp2[x][y]));

				// convertendo a imagem no formato matricial para BufferedImage
				imgAtual = bufimg.createImage(imgmat);

				// carregando a imagem na interface
				imagen2 = new ImageIcon(imgAtual);
				imageLabel2.setIcon(imagen2);

				// mudando a escala da imagem para exibição na interface
				mudarEscala();
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Antes de aplicar o filtro de detecção de bordas, é necessário carregar uma imagem no programa.",
						"Imagem ainda não carregada!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		else if(e.getSource()== Limiar) {

			// verifica se uma imagem já foi carregada
			if(imgmat != null) {
				imgAtual = bufimg.createImage(imgmat);

				// lendo o valor de limiar digitado pelo usuário
				int valorLimiar = -1;
				while(true) {
					try {
						while(valorLimiar < 0 || valorLimiar > 255)
							valorLimiar = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor entre 0 e 255 para o limiar:"));
						break;
					} catch(Exception vL) {
						JOptionPane.showMessageDialog(null,
								"Antes de aplicar a limiarização, é necessário escolher um valor válido.",
								"Operação cancelada.",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				// aplicando o processo de limiarização por binarização
				imgAtual = bufimg.limiar(imgAtual, valorLimiar);

				// carregando a imagem na interface
				imagen2 = new ImageIcon(imgAtual);
				imageLabel2.setIcon(imagen2);

				// mudando a escala da imagem para exibição na interface
				mudarEscala();

				// obtendo a imagem no formato matricial, vetor de inteiros com duas dimensões
				imgmat = bufimg.convertToArrayLocation(imgAtual);
			}			
			else {
				JOptionPane.showMessageDialog(null,
						"Antes de aplicar a limiarização, é necessário carregar uma imagem no programa.",
						"Imagem ainda não carregada!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		new principal().setVisible(true);
	}

	/*
	 * Carrega imagem no programa através de um file picker.
	 * Processa a imagem para iniciar outras operações.
	 */
	private void carregar() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Image files", "png",
				"jpg", "bmp"));
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return;
		try {
			// Carregamos a imagem do disco
			File img = chooser.getSelectedFile();
			imgAtual = ImageIO.read(img);

			//carrega imagem original da label da esquerda
			imagen = new ImageIcon(img.getPath());
			imageLabel.setIcon(imagen);

			// Geramos uma versão em escala de cinza
			imgAtual = bufimg.toGrayscale(imgAtual);

			// carregando a imagem em escala de cinza na label da direita
			imagen2 = new ImageIcon(imgAtual);
			imageLabel2.setIcon(imagen2);

			// obtendo a imagem no formato matricial, vetor de inteiros com duas dimensões
			imgmat = bufimg.convertToArrayLocation(imgAtual);

			// mudando a escala da imagem para exibição na interface
			mudarEscala();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro ao processar imagem.");
			e1.printStackTrace();
		}
	}

	/*
	 * Muda a escala das imagens exibidas na interface para o mesmo tamanho das labels (350x350)
	 */
	private void mudarEscala() {
		imagen.setImage(imagen.getImage().getScaledInstance(350,350, Image.SCALE_AREA_AVERAGING));
		imagen2.setImage(imagen2.getImage().getScaledInstance(350,350, Image.SCALE_AREA_AVERAGING));
	}
}
