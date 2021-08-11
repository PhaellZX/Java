package classes;

public class Circulo extends Formas2D{
	private final int raio;
	
	public Circulo(int raio) {
		this.raio = raio;
	}

	@Override
	public double getArea() {
		return Math.PI*this.raio*this.raio;
	}

	@Override
	public double getPerimetro() {
		return 2*Math.PI*this.raio;
	}
}
