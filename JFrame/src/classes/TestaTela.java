package classes;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestaTela {
	public static void main(String[] args) {
		
		//cria um Frame com o t�tulo
		JFrame janela = new JFrame("Exemplo figuras geom�tricas");
		
		//configura tamanho
		janela.setSize(1000, 400);
		
		//deixa ela visivel (mostra)
		janela.setVisible(true);
		
		//para fechar a janela
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//janela esta vazia, necessitamos colocar conte�do
		Tela painel = new Tela(new Quadrado(120), new Quadrado(150), 
				new Retangulo(150,100));
		
		janela.add(painel);
		
	}
}

