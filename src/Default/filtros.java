package Default;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class filtros {
	
	private String caminhoArquivo;
	private String caminhoPasta = new File("src/filtros/").getAbsolutePath();
	
	private void comporNome(String nome, int tamanho){
		String tamanhoS = Integer.toString(tamanho);
		caminhoArquivo = caminhoPasta+"\\"+nome+tamanhoS+"x"+tamanhoS+".csv";
	}
	
	@SuppressWarnings("deprecation")
	private double[][] lerFiltro (int tamanhoFiltro) throws FileNotFoundException{
		double[][] filtro = new double[tamanhoFiltro][tamanhoFiltro];
		
		String novaLinha; 
		FileInputStream inputStream;
		
		try {
			//System.out.println(caminhoArquivo); 
			inputStream = new FileInputStream(caminhoArquivo);
			DataInputStream dadosCSV = new DataInputStream(inputStream);
			
			// contador de linhas do csv
			int i = 0;
			
			// matriz para guardar os números em formato String
			String[][] matrizTexto = new String[0][];
			
			// interando pelas linhas do csv
			while ((novaLinha = dadosCSV.readLine()) != null) {
			    ++i;
			    
			    // matriz temporária com o texto das linhas do csv
			    String[][] mTextoTemp = new String[i][tamanhoFiltro];
			    
			    // dividindo o texto da nova linha
			    String textoDividido[] = novaLinha.split(";");
			    
			    // guardando o texto da nova linha na matriz temporária
			    mTextoTemp[i - 1] = textoDividido;

			    // copiando o conteúdo da matriz temporária para a matriz que guarda os números do filtro em formato String
			    System.arraycopy(matrizTexto, 0, mTextoTemp, 0, i - 1);
			    matrizTexto = mTextoTemp;
			}
			// convertendo a matriz texto para o formato Double
			for (int x = 0; x < tamanhoFiltro; x++)
			    for (int y = 0; y < tamanhoFiltro; y++)
			    	filtro[x][y] = Double.parseDouble(matrizTexto[x][y]);
			
		} catch (IOException e) {e.printStackTrace();}
		return filtro;
	}
	
	public double[][] criarFiltro (int tamanhoFiltro, int idFiltro){
		double[][] filtro = new double[tamanhoFiltro][tamanhoFiltro];
		switch(idFiltro){
			case(0):
			{
				//passa baixa
				comporNome("passabaixa", tamanhoFiltro);
				break;
			}
			case(1):
			{
				//gaussiano
				comporNome("gauss", tamanhoFiltro);
				break;
			}
			case(2):
			{
				//Prewitt
				comporNome("prewitt", tamanhoFiltro);
				break;
			}
			case(3):
			{
				//Sobel
				comporNome("sobel", tamanhoFiltro);
				break;
			}
			
		}
		try {
			// criando um novo filtro a partir dos arquivos csv contidos no projeto
			filtro = lerFiltro(tamanhoFiltro);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		return filtro;
	}
	
}