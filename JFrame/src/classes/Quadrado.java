package classes;

import java.awt.Color;
import java.awt.Graphics;

public class Quadrado extends Formas2D implements Desenhavel{
	private final int lado;
	//construtor
	public Quadrado(int lado) {
		this.lado=lado;
	}
	@Override
	public double getArea() {
		return this.lado*this.lado;
	}

	@Override
	public double getPerimetro() {
		return this.lado*4;
	}
	@Override
	public String toString() {
		//"Quadrado lado:4 per�metro:16 �rea:16"
		return super.toString()+" lado:"+this.lado
				+" per�metro:"+this.getPerimetro()
				+" �rea:"+this.getArea();
	}
	
	@Override
	public void desenha(Graphics g, int x, int y) {
		//desenha quadrado
		g.setColor(Color.BLACK);
		g.drawRect(x, y, this.lado, this.lado);
		
		//escreve info do quadrado dentro dele
		g.setColor(Color.BLUE);
		int incY =17;
		for(String texto:this.toString().split(" ")) {
			g.drawString(texto, x+5, y+incY);
			incY+=17;
		}
	}
	@Override
	public int getLargura() {
		return this.lado;
	}

}