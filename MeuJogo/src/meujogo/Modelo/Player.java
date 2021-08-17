package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {
	
	private int x, y;  // x, y, dx, dy, atributos para movimentação ao pressionar o botão
	private int dx, dy;
	private Image imagem; // Imagem do Player = Sprite
	private int altura, largura; // atributos usado para colisão
	private List<Tiro> tiros; // Atributo que estancia a classe Tiro
	private boolean isVisivel, isTurbo;
	private Timer timer; 
	
	public Player() {
		this.x = 100; // x e y = Coordenação da tela aonde o Player vai começar na tela ou quando respawnar
		this.y = 100;
		this.isVisivel = true;
		this.isTurbo = false;
		
		tiros = new ArrayList<Tiro>(); // Criando objeto Tiro
		
		timer = new Timer(5000, this); 
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isTurbo==true) {
			turbo();
			isTurbo = false;
		}
		if(isTurbo==false) {
			load();
		}
	}
	
	public void load() { // Definir a imagem do Player
		ImageIcon referencia = new ImageIcon("res\\spaceship.png");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null); // Definir a Altura da imagem
		largura = imagem.getWidth(null); // Definir a Largura da imagem
	}
	
	// metodos para mover o Player
	
	public void update(){ // metodo que faz o player se movimentar no eixo x e y
		x += dx;
		y += dy;
	}
	
	public void tiroSimples() { // metodo que posiciona aonde vai sair o tira do Player
		this.tiros.add(new Tiro(x + largura, y + (altura/2)));
	}
	
	public void turbo() {
		isTurbo = true;
		ImageIcon referencia = new ImageIcon("res\\turbo.png");
		imagem = referencia.getImage();
	}
	
	public Rectangle getBounds() { //Metodo que cria formas para colisão
		return new Rectangle(x,y,largura,altura);
	}
	
	//Obs: O plano cartesiano dos eixos x e y da criação de jogos é invertido!
	
	public void keyPressed(KeyEvent tecla) { // metodo quando pressiona alguma tecla do teclado
		int codigo = tecla.getKeyCode();
		if(codigo==KeyEvent.VK_UP) {
			dy = -3; // y moverá pra cima | ex: ( y = y + (-3)(dy))
		}if(codigo==KeyEvent.VK_DOWN) {
			dy = 3; // y moverá pra baixo | ex: ( y = y + 3(dy))
		}if(codigo==KeyEvent.VK_LEFT) {
			dx = -3; // x moverá pra esquerda | ex: ( x = x + (-3)(dx))
		}if(codigo==KeyEvent.VK_RIGHT) {
			dx = 3; // x moverá pra direita | ex: ( x = x + 3(dx))
		}if(codigo==KeyEvent.VK_A) { // Para atirar
			tiroSimples(); // Metodo de tiro
		}if(codigo==KeyEvent.VK_SPACE) {
			turbo();
		}
	}
	
	public void keyRelease(KeyEvent tecla) { // metodo quando PARA de pressionar alguma tecla do teclado
		int codigo = tecla.getKeyCode();
		if(codigo==KeyEvent.VK_UP) {
			dy = 0;
		}if(codigo==KeyEvent.VK_DOWN) {
			dy = 0;
		}if(codigo==KeyEvent.VK_LEFT) {
			dx = 0;
		}if(codigo==KeyEvent.VK_RIGHT) {
			dx = 0; 
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public boolean isTurbo() {
		return isTurbo;
	}
	
}
