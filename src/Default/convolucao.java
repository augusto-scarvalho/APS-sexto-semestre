package Default;

public class convolucao {

	public  int[][] convolucao(double[][]filtro, int[][]imagem){
		int alturaImg = imagem.length;
		int larguraImg = imagem[0].length;
		int dimFiltro = filtro.length;

		int[][]imagemAlterada = new int[alturaImg][larguraImg];
		// iterando pelas linhas da matriz da imagem
		for(int x = 0; x < alturaImg; x++) {
			// iterando pelas colunas da matriz da imagem
			for(int y = 0; y < larguraImg; y++) {
				// iniciando a vari�vel que guarda o valor da somat�ria
				double acumulador = 0;
				// iterando pelas linhas da matriz do filtro
				for(int i = 0; i < dimFiltro; i++) {
					// iterando pelas colunas da matriz do filtro
					for(int j = 0; j < dimFiltro; j++) {
						try {
							// tenta multiplicar a posi��o do filtro invertido pelo pixel da imagem
							acumulador += imagem[x+i-(dimFiltro/2)][y+j-(dimFiltro/2)] * filtro[dimFiltro-1-i][dimFiltro-1-j];
						} catch(Exception e) {
							// exce��o nula quando um erro de "fora do alcance" ocorrer ao tentar referenciar um pixel que n�o existe na imagem
						}
					}
				}
				// pixel da imagem alterada recebendo o valor da somat�ria
				if(acumulador < 0)
					acumulador = 0;
				if(acumulador > 255)
					acumulador = 255;
				imagemAlterada[x][y] = (int) Math.round(acumulador);
			}
		}
		return imagemAlterada;
	}
}