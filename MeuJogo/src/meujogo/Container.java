package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;

public class Container extends JFrame { // Classe que vai ser a tela do jogo
	
	public Container() {  // Iniciando a tela do jogo
		add(new Fase());  // Adiciona a classe Fase dentro da classe Container 
		setTitle("Meu Jogo");  // Titulo do jogo na aba da tela
		setSize(1024,728); // Tamanho da tela do jogo setSize(widht,height)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quando aperta no 'x' da tela � para fecha-lo
		setLocationRelativeTo(null); // Onde a tela vai ser iniciada ex: no valor 'null' vai aparecer no centro da tela do monitor
		this.setResizable(false); // Desativar a Maximiza��o da tela do jogo ex: false -> n�o ativado | true -> ativado
		setVisible(true); // Os comando do jogo ser� visivel 
	}
	public static void main(String args[]) {
		new Container(); // Iniciar a tela do jogo
	}
}
