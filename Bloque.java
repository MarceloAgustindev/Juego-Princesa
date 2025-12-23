package juego;

import entorno.Entorno;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Bloque {
	private int x, y, ancho, alto;
	private boolean destruir;
    private Image bloqueDestructible;
    private Image bloqueIndestructible;
    private Image fondo;
	
	// METODO CONSTRUCTOR
	
	public Bloque (int x, int y, int ancho, int alto, boolean destruir) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.destruir=destruir;
		try {
			InputStream directorioLadrilloIndestructible = getClass().getResourceAsStream("/Imagenes/LadrilloIndestructible.jpg");
			this.bloqueIndestructible = ImageIO.read(directorioLadrilloIndestructible);
            //this.bloqueIndestructible = ImageIO.read(new File("C:/Users/Usuarioi7/Downloads/LadrilloIndestructible.png"));
			InputStream directorioLadrilloDestructible = getClass().getResourceAsStream("/Imagenes/LadrilloDestructible.png");
			this.bloqueDestructible = ImageIO.read(directorioLadrilloDestructible);
            //this.bloqueDestructible = ImageIO.read(new File("C:/Users/Usuarioi7/Downloads/LadrilloDestructible.jpg"));
            InputStream directorioFondo = getClass().getResourceAsStream("/Imagenes/Fondo.png");
            this.fondo = ImageIO.read(directorioFondo);
            //this.fondo = ImageIO.read(new File("src/Imagenes/Fondo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	// METODOS DE INSTANCIA
	
	public void Dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.LIGHT_GRAY);
        entorno.dibujarImagen(this.bloqueIndestructible, this.x, this.y, 0, 0.266);
    }
	
	public void Dibujar2(Entorno entorno){
        entorno.dibujarRectangulo(this.x,this.y, this.ancho, this.alto, 0, Color.MAGENTA);
        entorno.dibujarImagen(this.bloqueDestructible, this.x, this.y, 0, 0.067);
    }
	
	public void DibujarBase(Entorno entorno) {
        entorno.dibujarRectangulo(this.x,this.y, this.ancho, this.alto, 0, Color.DARK_GRAY);
        //entorno.dibujarImagen(this.bloqueDestructible, this.x, this.y, 0, 0.900);
	}
	
	public void DibujarFondo (Entorno entorno) {
		entorno.dibujarRectangulo(this.x,this.y, this.ancho, this.alto, 0, Color.DARK_GRAY);
        entorno.dibujarImagen(this.fondo, this.x, this.y, 0, 0.900);
	}
	
	public int extremoDer() {
		return (this.x + this.ancho/2);
	}
	
	public int extremoIzq() {
		return (this.x - this.ancho/2);
	}
	
	public int extremoSup() {
		return (this.y - this.alto/2);
	}
	
	public int extremoInf() {
		return (this.y + this.alto/2);
	}

	// SETTERS & GETTERS

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
	
	public boolean setDestruir(){
        return this.destruir;
    }
	
	   public Rectangle obtenerRectangulo() {
	        return new Rectangle (this.getX(), this.getY(), ancho, alto); 
	    }
}
