package juego;

import java.awt.Color;

import entorno.Entorno;

public class Disparo{
	private int x;  //Posicion en X
	private int y;   // Posicion en Y
	private int FrameX;  // Cambio de frame a frame de X
	private int radio;
	private int lado;
	private boolean activo;                                                   
	
	public Disparo(int x, int y, int radio, int lado) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.lado = lado;
		this.FrameX= 5 * lado;
		this.activo=false;
	}
	public void dibujar(Entorno entorno)
	{
		entorno.dibujarCirculo(this.x, this.y, 2 * this.radio, Color.YELLOW);
	}
	public void Actualizacion(int ancho) {
        x += FrameX;
    }
	public boolean estado(){
		return activo;
	}
	public void Recargar(boolean estado){
		this.activo=estado;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadio() {
		return radio;
	}
	public boolean FueraDeLosBordes(int ancho){
		return this.x == ancho || this.x == 0;
	}
	
	public void dibujarShotDino(Entorno entorno){
		entorno.dibujarCirculo(this.x,this.y,2*this.radio,Color.blue);
	}
	
}