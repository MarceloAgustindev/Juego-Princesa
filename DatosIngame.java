package juego;
import entorno.*;
public class DatosIngame {
    private int Vidas;
    private int victimas;
    private int puntos;
    private double posX;
    private double posY;
    public DatosIngame(double posX ,double posY){
        this.Vidas=3;
        this.victimas = 0;
        this.puntos=this.victimas+this.victimas;
        this.posX=posX;
        this.posY=posY;
    }
    public void Dibujar(Entorno entorno){
        entorno.escribirTexto("Victimas: " + this.victimas ,this.posX, this.posY);
        entorno.escribirTexto("Puntos: " + this.puntos ,this.posX, this.posY + 10);
        entorno.escribirTexto("Vidas: " + this.Vidas ,this.posX, this.posY + 20);

    }
    public void sumarVictimas(){
        this.victimas++;
    }
    public void sumaPuntos(int cantpuntos){
        this.puntos=this.puntos+cantpuntos;
    }
    public void daño(){
        this.Vidas--;
    }
    public int CantVidas(){
        return this.Vidas;
    }
    
}
