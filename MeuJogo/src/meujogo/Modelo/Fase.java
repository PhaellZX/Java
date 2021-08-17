package meujogo.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
	private Image fundo; // Atributo que cria o fundo da fase usando o objeto 'Image'
	private Player player; // Atributo que recebe o Player 
	private Timer timer; // Atributo que recebe a velocidade do jogo
	private List<Enemy1> enemy1; // Atributo que recebe a lista de inimigos
	private List<Stars> stars; // Atributo que recebe a lista de inimigos
	private boolean emJogo;
	
	
	public Fase() {
		setFocusable(true);  // Melhorar o desenpenho do game
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("res\\background.jpg"); // Recebe a imagem 
		fundo = referencia.getImage();
		
		player = new Player(); // Criado o objeto player na fase
		player.load(); // carregar a imagem do player
		
		addKeyListener(new TecladoAdapter()); // Estanciar a entrada do teclado
		
		timer = new Timer(1, this); // Colocando o valor da velocidade do jogo
		timer.start(); // Iniciando a velocidade
		
		inicializaInimigos();
		inicializaStars();
		emJogo = true;
		}
	
	public void inicializaInimigos() { // Metodo que faz aparecer os inimigos na fase
		int condernadas[] = new int [40];
		enemy1 = new ArrayList<Enemy1>();
		
		for (int i : condernadas) { 
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			enemy1.add(new Enemy1(x,y));
		}
	}
	
	public void inicializaStars() { // Metodo que faz aparecer as estrelas na fase
		int condernadas[] = new int [100];
		stars = new ArrayList<Stars>();
		for (int i : condernadas) { 
			int x = (int) (Math.random() * 1024 + 0);
			int y = (int) (Math.random() * 780 + 0);
			stars.add(new Stars(x,y));
		}
	}
	public void paint(Graphics g) { // Metodo que printa a imagem
		Graphics2D graficos = (Graphics2D) g;
		if(emJogo==true) {
			graficos.drawImage(fundo, 0, 0, null); // Aparecer a imagem com as suas posições 
			
			for(int p = 0; p < stars.size(); p++) { // Repetições de tiros
				Stars q = stars.get(p);
				q.load();
				
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this); //Aparecer a imagem Player
			
			List<Tiro> tiros = player.getTiros(); // Adicionando os tiros na lista
			for(int i = 0; i < tiros.size(); i++) { // Repetições de tiros
				Tiro m = tiros.get(i);
				m.load();
				
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			
			} 
		} else {
			ImageIcon fimDeJogo = new ImageIcon("res\\fimdejogo.png");
			graficos.drawImage(fimDeJogo.getImage(), 0, 0, null);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) { // O metodo actionPerformed da interface ActionListener serve para atualizar a tela quando playr estiver se movendo
		player.update(); // Atualizar a movimentação do player
		
		if(player.isTurbo()) {
			timer.setDelay(2);
		}
		
		if(player.isTurbo() ==  false) {
			timer.setDelay(5);
		}
		
		List<Tiro> tiros = player.getTiros(); // Adicionando os tiros na lista
		for (int p = 0; p < stars.size(); p++) {
			Stars on = stars.get(p);
				if(on.isVisivel()) {
					on.update();
				}else {
					stars.remove(p);
				}
		}
		
		for(int i = 0; i < tiros.size(); i++) { // Repetições de tiros
			Tiro m = tiros.get(i);
			if(m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
		
		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if(in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}
		
		checarColisoes();
		
		repaint(); // sempre que o player se mover ele vai repintar a imagem sem deixar tem rastros de movimentação
	}
	
	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		
		for(int i = 0; i < enemy1.size(); i++) { //Colisão entre o player e o enemy
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if(formaNave.intersects(formaEnemy1)) {
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false;
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for(int j = 0; j < tiros.size(); j++) { // Colisão entre os tiros e o enemy
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBounds();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}
	
	private class TecladoAdapter extends KeyAdapter{ // Metodo de entrada de teclado
		
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
}
