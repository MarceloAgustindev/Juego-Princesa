package juego;

public class Contador {
    private int tiempo;
    private int respaldo;
    private boolean activo;
    
public Contador(int tiempo){
    this.tiempo = tiempo;
    this.respaldo= tiempo;
    this.activo=false;
}
public void avanza(){
    this.tiempo++;
}
public void retrocede(){
    this.tiempo--;
}
public int time(){
    return tiempo;
}
public boolean chill(){
    return activo;
}
public void cambio(boolean estado){
    this.activo= estado;
}
public void actualizar(){
    if (this.activo==true){
        this.avanza();
    }
}

public void MeRespaldo(){
    this.tiempo=this.respaldo;
}
}
