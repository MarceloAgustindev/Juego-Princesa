package juego;

//import javafx.scene.shape.Circle;

public class Colision {
public static boolean colisionDisparoDinos(Disparo disparo,Dinos dinos)
	{
		boolean colisionX = dinos.getX() - dinos.getAncho()/2 < disparo.getX() &&
							dinos.getX() + dinos.getAncho()/2 > disparo.getX();
							
		boolean colisionY = disparo.getY() + disparo.getRadio() > 
							dinos.getY() - dinos.getAlto() / 2;
							
		return colisionX && colisionY;
	}
    public static boolean colisionDisparoPrincesa(Disparo disparo,Princesa princesa)
	{
		boolean colisionX = princesa.getX() - princesa.getAncho()/2 < disparo.getX() &&
							princesa.getX() + princesa.getAncho()/2 > disparo.getX();
							
		boolean colisionY = disparo.getY() + disparo.getRadio() > 
							princesa.getY() - princesa.getAlto() / 2;
							
		return colisionX && colisionY;
	}
    public static boolean colisionDinoPrincesa(Dinos dinos,Princesa princesa)
	{
		boolean colisionX = princesa.getX() - princesa.getAncho()/2 < dinos.getX() + dinos.getAncho()/2 &&
							princesa.getX() + princesa.getAncho()/2 > dinos.getX() - dinos.getAncho()/2;
		boolean colisionY = dinos.getY() + dinos.getAlto()/2 > 
							princesa.getY() - princesa.getAlto() / 2;
							
		return colisionX && colisionY;
}
	 public static boolean checkCollisionDis(Disparo disparo1,Disparo disparo2) {
        double distance = Math.sqrt(Math.pow(disparo2.getX() - disparo1.getX(), 2) +
                                    Math.pow(disparo2.getY() - disparo1.getY(), 2));
        return distance <= (disparo1.getRadio() + disparo2.getRadio());
    }
	
	 public static boolean colisionDisparoBloque(Disparo disparo,Bloque bloque)
	    {
	        boolean colisionX = bloque.getX() - bloque.getAncho()/2 < disparo.getX() &&
	                            bloque.getX() + bloque.getAncho()/2 > disparo.getX();

	        boolean colisionY = disparo.getY() + disparo.getRadio() > 
	                            bloque.getY() - bloque.getAlto() / 2;

	        return colisionX && colisionY;
	    }
}