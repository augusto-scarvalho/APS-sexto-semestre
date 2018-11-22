package Default;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public class Imagem {

	/**
	 * Converte uma imagem no formato BufferedImage para um vetor de 2 dimensões do tipo inteiro
	 * Código retirado de: https://sites.google.com/site/osdevlab/learn-java/image-processing/gettwodimensionalpixelarray
	 *
	 * @param imagem que será convertida no tipo BufferedImage
	 * @return vetor de 2 dimensões do tipo inteiro com os valores dos pixels da imagem
	 */
	static int[][] convertToArrayLocation(BufferedImage inputImage) {
		final byte[] pixels = ((DataBufferByte) inputImage.getRaster().getDataBuffer()).getData();
		//get image width value
		final int width = inputImage.getWidth();
		//get image height value
		final int height = inputImage.getHeight();
		//Initialize the array with height and width
		int[][] result = new int[width][height];
		//this loop allocates pixels value to two dimensional array
		for (int pixel = 0, row = 0, col = 0; (col < width-1 && pixel < pixels.length); pixel++) {
			int argb = 0;
			argb = (int) pixels[pixel];
			//if pixel value is negative, change to positive
			if (argb < 0) {
				argb += 256;
			}
			result[row][col] = argb;
			row++;
			if (row == width) {
				row = 0;
				col++;
			}
		}
		//return the result as two dimensional array
		return result; 
	}

	/**
	 * Cria uma imagem em escala de cinza a partir de um vetor de inteiros com duas dimensões.  0 = preto, 255 = branco.
	 * 
	 * @param vetor de inteiros com duas dimensões contendo os valores dos pixels da imagem
	 * @return imagem em escala de cinza com o tipo BufferedImage
	 */
	public BufferedImage createImage(int image[][]) {
		int width = image.length;
		int height = image[0].length;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// Convert the 2D data into a 1D array:
		int oneD[] = new int[width*height];
		// For each row
		for(int i=0; i<width; i++)
		{
			// Check if this row has the correct number of pixels
			if(image[i].length != height){
				System.out.println("Image is not rectangular");
				return img;
			}
			// for each column
			for(int j=0; j<height; j++){
				// check for invalid values
				if(image[i][j] > 255)
					image[i][j] = 255;
				else if(image[i][j] < 0)
					image[i][j] = 0;

				// Combine each pixel into one int.
				// First 8 bits are alpha value (255=opaque)
				// Then red, green, and blue (8-bits each)
				oneD[j*width+i] = 0xFF000000 |
						(0x000000FF & image[i][j]) << 16 |
						(0x000000FF & image[i][j]) << 8 |
						(0x000000FF & image[i][j]);
			}
		}
		img.setRGB(0, 0, width, height, oneD, 0, width);
		return img;
	}

	/*
	 * Transforma a imagem original em uma nova imagem em escala de cinza
	 * 
	 * @param imagem original no formato BufferedImage
	 * @return imagem em escala de cinza no formato BufferedImage
	 */
	protected BufferedImage toGrayscale(BufferedImage image) throws IOException {
		BufferedImage output = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = output.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return output;
	}

	/*
	 * Aplica o processo de limiarização por binarização
	 * 
	 * @param1 Imagem que será limiarizada do tipo BufferedImage
	 * @param2 valor de limiar do tipo inteiro
	 * @return Imagem limiarizada do tipo BufferedImage
	 */
	protected BufferedImage limiar(BufferedImage image, int t) {
		int BLACK = Color.BLACK.getRGB();
		int WHITE = Color.WHITE.getRGB();
		BufferedImage output = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		// Percorre a imagem definindo na saída o pixel como branco se o valor
		// na entrada for menor que o threshold, ou como preto se for maior.
		for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixel = new Color(image.getRGB(x, y));
				output.setRGB(x, y, pixel.getRed() < t ? BLACK : WHITE);
			}
		return output;
	}
}