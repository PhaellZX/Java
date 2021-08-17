package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {
	private Image imagem; // Atributo que recebe a imagem do tiro
	private int x, y; // Atributo que recebe a posição do tiro
	private int largura, altura; //  Atributo que recebe a altura e largura do tiro
	private boolean isVisivel; // Atributo que vai dizer o limite do tiro 
	
	private static final int LARGURA = 938; // Limite do tiro
	private static int VELOCIDADE = 2; // Velocidade do tiro
	
	public Tiro(int x, int y) { //Obs: O x e y são do Player passado por parâmetro 
		this.x = x;
		this.y = y;
		this.isVisivel = true;
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\tiroSimples.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		this.x += VELOCIDADE;
			if(this.x > LARGURA) {
				isVisivel = false;
			}
	}

	public Rectangle getBounds() { //Metodo que cria formas para colisão
		return new Rectangle(x,y,largura,altura);
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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
	
	
}
