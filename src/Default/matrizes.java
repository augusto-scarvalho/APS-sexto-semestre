package Default;

public class matrizes {
	
	protected double[][] rodarMatriz(double[][] matrizOriginal) {
            // salvando a quantidade de linhas e colunas da matriz
	    int linhas = matrizOriginal.length;
	    int colunas = matrizOriginal[0].length;

	    double[][] novaMatriz = new double[colunas][linhas];

            // iterando entre as linhas da matriz original
	    for (int x = 0; x < linhas; x++) {
            // iterando entre as colunas da matriz original
	        for (int y = 0; y < colunas; y++) {
                        // salvando o valor da matriz original na nova matriz rotacionada
	        	novaMatriz[y][linhas - 1 - x] = matrizOriginal[x][y];
	        }
	    }
	    return novaMatriz;
	}
	
	protected double[][] multiplicar(double[][] matriz1, double[][] matriz2){		
		int alturaMatriz1 = matriz1.length;
		int larguraMatriz1 = matriz1[0].length;
		
		int alturaMatriz2 = matriz2.length;		
		int larguraMatriz2 = matriz2[0].length;
		
		double[][] matrizResultado = new double[alturaMatriz1][larguraMatriz2];
		
		if(alturaMatriz2 != larguraMatriz1)
			return null;
		else {					
			for(int x = 0; x < alturaMatriz1; x++)
				for(int y = 0; y < larguraMatriz2; y++)
					for(int z = 0; z < larguraMatriz1; z++)
						matrizResultado[x][y] += matriz1[x][z] * matriz2[z][y];
			
			return matrizResultado;
		}
	}
}