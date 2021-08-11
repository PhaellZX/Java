package classes;

import java.awt.Color;
import java.awt.Graphics;

public class Retangulo extends Formas2D implements Desenhavel{
	private final int base;
	private final int altura; 
	public Retangulo(int base, int altura) {
		this.base=base;
		this.altura=altura;
	}
	@Override
	public double getArea() {
		return this.base*this.altura;
	}
	@Override
	public double getPerimetro() {
		return this.base*2+this.altura*2;
	}
	@Override
	public String toString() {
		return super.toString()+" altura:"+this.altura
				+" base:"+this.base
				+" per�metro:"+this.getPerimetro()
				+" �rea:"+this.getArea();
	}
	@Override
	public void desenha(Graphics g, int x, int y) {
		//desenha ret�ngulo
		g.setColor(Color.ORANGE);
		g.drawRect(x, y, this.base, this.altura);
				
		//escreve info do retangulo dentro dele
		g.setColor(Color.BLUE);
		int incY =17;
		for(String texto:this.toString().split(" ")) {
			g.drawString(texto, x+5, y+incY);
			incY+=17;
		}
		
	}
	@Override
	public int getLargura() {
		// TODO Auto-generated method stub
		return this.base;
	}
}
