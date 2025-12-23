package juego;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import entorno.Entorno;

public class Dinos {
	private int x, y, ancho, alto, velocidad, gravedad;
    private boolean cayendo;
	private boolean direccion;
    private Image imagenDer;
    private Image imagenIzq;
	
	// METODO CONSTRUCOR
	public Dinos(int x, int y, int ancho, int alto, int speedX) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = speedX;
		this.gravedad = 1;
		this.cayendo = false;

		try {
			InputStream directorioDinoDer = getClass().getResourceAsStream("/Imagenes/DinoDer.png");
            this.imagenDer = ImageIO.read(directorioDinoDer);
			
            //this.imagenDer = ImageIO.read(new File("C:/Users/Usuarioi7/Downloads/DinoDer.png"));
            
            InputStream directorioDinoIzq = getClass().getResourceAsStream("/Imagenes/DinoIzq.png");
            this.imagenIzq = ImageIO.read(directorioDinoIzq);
			
            //this.imagenIzq = ImageIO.read(new File("C:/Users/Usuarioi7/Downloads/DinoIzq.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	// MÉTODOS DE INSTANCIA
	public boolean pisando(Bloque bloque) {
		if (bloque != null 
				&& this.extremoInf() >= bloque.extremoSup() 
				&& this.extremoInf() <= bloque.extremoSup()
				&& this.getX() >= bloque.extremoIzq()
				&& this.getX() <= bloque.extremoDer()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean estaEntre(Bloque bloque) {
		if (bloque != null 
				&& this.extremoDer() >= bloque.extremoIzq() 
				&& this.extremoDer() <= bloque.extremoDer()
				&& this.getY() >= bloque.extremoSup()
				&& this.getY() <= bloque.extremoInf()) {
			return true;
		} else if (bloque != null 
				&& this.extremoIzq() <= bloque.extremoDer() 
				&& this.extremoIzq() >= bloque.extremoIzq()
				&& this.getY() >= bloque.extremoSup()
				&& this.getY() <= bloque.extremoInf()){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean estaPisando(Bloque bloque) {
		if (this.extremoInf() <= (bloque.getY() - (bloque.getAlto()) / 2) && bloque != null) {
			return true;
		} else {
			return false;
		}
	}
	public void estanColisionando(Dinos otro) {
		if (this.getY() >= otro.extremoSup() && this.getY() <= otro.extremoInf()) {
			if (this.extremoDer() == otro.extremoIzq() || this.extremoDer() <= otro.extremoDer() && this.extremoDer() >= otro.extremoIzq()) {
        	    this.setVelocidad(this.velocidad * -1);
        	    otro.setVelocidad(otro.velocidad * -1);
			}
		}
	}
	public boolean estanSuperpuestos(Dinos otro) {
		if (this.getX() <= otro.extremoDer() && this.getX() >= otro.getX() || this.getX() >= otro.extremoIzq() && this.getX() <= otro.getX()) {
			return true;
		} else { return false; }
	}
	
	public void dibujarDer(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
		entorno.dibujarImagen(this.imagenDer, this.x, this.y, 0, 0.08);
	}
	public void dibujarIzq(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
		entorno.dibujarImagen(this.imagenIzq, this.x, this.y, 0, 0.08);
	}
	
	public void descender() {
		this.setY(this.y + gravedad);
	}
	public void movimiento() {
		this.setX(this.x + velocidad);
	}
	public int extremoDer() {
		return (this.x + this.ancho/2);
	}
	public int extremoIzq() {
		return (this.x - this.ancho/2);
	}
	public int extremoInf() {
		return (this.y + this.alto/2);
	}
	public int extremoSup() {
		return (this.y - this.alto/2);
	}
	public void colisionar() {
		this.velocidad = velocidad * -1;
	}
	
	// GETTERS & SETTERS
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	public boolean getCayendo() {
		return cayendo;
	}
	
	public void setCayendo(boolean cayendo) {
		this.cayendo = cayendo;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	
	
}