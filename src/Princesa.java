package juego;

import entorno.Entorno;
import java.awt.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Princesa {
	private int x, y, ancho, alto, gravedad, velocidad, alturaSalto;
	private boolean cayendo;
    private boolean direccion;
    private Image imagenDer;
    private Image imagenIzq;
	
	// METODO CONSTRUCTOR
	
	public Princesa(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.gravedad = 5;
		this.velocidad = 3;
		this.alturaSalto = y-80;
		this.cayendo = false;
		this.direccion = true;
		try {
			InputStream directorioPrincesaDer = getClass().getResourceAsStream("/Imagenes/PrincesaDer.png");
            this.imagenDer = ImageIO.read(directorioPrincesaDer);			
            //this.imagenDer = ImageIO.read(new File("C:/Users/Usuarioi7/Downloads/PrincesaDer.png"));
            InputStream directorioPrincesaIzq = getClass().getResourceAsStream("/Imagenes/PrincesaIzq.png");
            this.imagenIzq = ImageIO.read(directorioPrincesaIzq);			
            //this.imagenIzq = ImageIO.read(new File("C:/Users/Usuarioi7/Downloads/PrincesaIzq.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// METODOS DE INSTANCIA
	public void Dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.PINK);   
    }
	
	public void dibujarDer(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.PINK);
		entorno.dibujarImagen(this.imagenDer, this.x, this.y, 0, 0.07);
	}
	public void dibujarIzq(Entorno entorno)
	{
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.PINK);
		entorno.dibujarImagen(this.imagenIzq, this.x, this.y, 0, 0.07);
	}
	
	public void descender() {
		this.setY(this.getY()+gravedad);
	}
	public void saltar() {
		this.setY(this.getY()-gravedad);
	}
	
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
	
	public void moverDerecha()
	{
		this.x = this.x + 5;
	}
	public void moverIzquierda()
	{
		this.x = this.x - 5;
	}
	
	public boolean isDireccion() {
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
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

	public int getAlturaSalto() {
		return alturaSalto;
	}

	public void setAlturaSalto(int alturaSalto) {
		this.alturaSalto = alturaSalto;
	}
	
	
	public Rectangle obtenerRectangulo () {
        return new Rectangle (this.getX(), this.getY(), ancho, alto);
    }
	
	public boolean verificarColisionesBloque(Bloque bloque) {
        //Rectangle rectPrincesa = this.obtenerRectangulo();
        if (bloque != null) {

            //Rectangle rectBloque = bloque.obtenerRectangulo();


        if (bloque != null) {
            // Crear un sub-rectángulo para la parte superior de la princesa
            Rectangle topRectPrincesa = new Rectangle(
                this.x - this.getAncho()/2,
                this.y - this.getAlto()/2,
                this.ancho,
                this.alto/6
            );


            // Crear un sub-rectángulo para la parte inferior del bloque
            Rectangle bottomRectBloque = new Rectangle(
            		bloque.getX() - bloque.getAncho()/2,
            		bloque.getY()  + bloque.getAlto() / 2,
            		bloque.getAncho(),
            		bloque.getAlto()/6
            );

            // Verificar si estos sub-rectángulos se intersectan
            if (topRectPrincesa.intersects(bottomRectBloque)) {
                System.out.println("Colision");
                return true;
            }
        }
        }
        return false;
    }
	
	
	
	

}
