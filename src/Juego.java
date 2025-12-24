package juego;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;	

	// Variables y métodos propios de cada grupo
	// ...
	int numPisos = 4;
	Bloque[][] pisos = new Bloque[numPisos][];
	Dinos[] dinosaurios = new Dinos[8];
	private Princesa princesa;
	private Disparo disparo;
	private Contador contador;
	private Contador contadorShotDino;
	private DatosIngame datosIngame;
	private Disparo[] disparoDino;
	private char TECLA_X = KeyEvent.VK_X;
	private char TECLA_C = KeyEvent.VK_C;
	private Bloque base;
	private Bloque fondo;

	Juego() {
		this.base=new Bloque(225, 575, 1600, 50, false);		
		this.fondo=new Bloque(405, 465, 1600, 50, false);
		
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		// Inicia el juego!
		this.entorno.iniciar();
		
		this.princesa = new Princesa(400, 350, 10, 20);
			        	  
		for (int piso = 0; piso < numPisos; piso++) {
		    // Crea el array de bloques para el piso actual
		    pisos[piso] = new Bloque[20];

		    // Calcula la posición y para el piso actual
		    int y = 450 - piso * 110; // Ajusta la separación vertical como desees

		    // Bucle para generar cada bloque en el piso actual
		    for (int bloque = 0; bloque < 20; bloque++) {
		        // Calcula la posición x del bloque
		        int x;

		        if (bloque == 0) {
		            x = 20; // Si es el primer bloque, empieza en la posición 0
		        } else if (pisos[piso][bloque - 1] == null) {
		            // Si el bloque anterior es nulo, calcula la posición basada en el bloque anterior al nulo
		            x = pisos[piso][bloque - 2].getX() + pisos[piso][bloque - 2].getAncho() * 2;
		        } else {
		            // Calcula la posición basada en el bloque anterior
		            x = pisos[piso][bloque - 1].getX() + pisos[piso][bloque - 1].getAncho();
		        }

		        // Crea el bloque con las coordenadas calculadas
		        int k=rand.nextInt(2);
                if(k==0){
                    pisos[piso][bloque] = new Bloque(x, y, 40, 30,false);
                }
                else{
                    pisos[piso][bloque] = new Bloque(x, y, 40, 30,true);
                }
		    }
		}
		
		// Dinosaurios
		for (int i = 0; i < dinosaurios.length; i++) {
            int firstRandom = rand.nextInt((250 - 20) + 1) + 20;
            int secondRandom = rand.nextInt((550 - 300) + 1) + 300;
            int numeroAleatorio = rand.nextBoolean() ? 1 : -1;

            if (i < 2) {
            	if(i % 2 == 0) {
                	dinosaurios[i] = new Dinos(firstRandom, 80, 10, 30, numeroAleatorio);
            	} else if(i % 2 != 0) {
                	dinosaurios[i] = new Dinos(secondRandom, 80, 10, 30, numeroAleatorio);
            	}
            } else if (i < 4) {
            	if(i % 2 == 0) {
                	dinosaurios[i] = new Dinos(firstRandom, 180, 10, 30, numeroAleatorio);
            	} else if(i % 2 != 0) {
                	dinosaurios[i] = new Dinos(secondRandom, 180, 10, 30, numeroAleatorio);
            	}
            } else if (i < 6) {
            	if(i % 2 == 0) {
                	dinosaurios[i] = new Dinos(firstRandom, 280, 10, 30, numeroAleatorio);
            	} else if(i % 2 != 0) {
                	dinosaurios[i] = new Dinos(secondRandom, 280, 10, 30, numeroAleatorio);
            	}
            } else if (i < 8) {
            	if(i % 2 == 0) {
                	dinosaurios[i] = new Dinos(firstRandom, 400, 10, 30, numeroAleatorio);
            	} else if(i % 2 != 0) {
                	dinosaurios[i] = new Dinos(secondRandom, 400, 10, 30, numeroAleatorio);
            	}
            }
        }
	
		// End Dinosaurios
		
		// DISPARO Y CONTADOR -----------------------------------------------------------------------------------------------------------------------------------
		
		//ESto es el tiro----------------------Esto-------------------------------------------------------------------------------------
				//Creacion del tiro
		        this.disparo=new Disparo(0,0,6,0);
				// contador de vidas?
		        this.datosIngame= new DatosIngame(50, 40);


				//Temporizador de disparo
				this.contador=new Contador(0);
				this.contadorShotDino= new Contador(0);
		        //______________________________________________________________________________________________________________________________
				
				
				//Creacion del tiro del dino______________________________Esto_________________________________________________________________________________
		        this.disparoDino= new Disparo[8];
		        for(int i=0;i<this.disparoDino.length;i++){
		             if(this.dinosaurios[i].getVelocidad()==1){
		                this.disparoDino[i]= new Disparo(this.dinosaurios[i].extremoDer(),this.dinosaurios[i].getY(), 6, 1);
		             } 
		            this.disparoDino[i] = new Disparo(this.dinosaurios[i].extremoIzq(), this.dinosaurios[i].getY(), 6,-1 );
		        }
		        //______________________________________________________________________________________________________________________________
		        
				
		// END DISPARO Y CONTADOR -----------------------------------------------------------------------------------------------------------------------------------
	
				
	
	
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
				
		
		// Procesamiento de un instante de tiempo
		// ...
		
		// PLATAFORMAS
		
		//FONDO
		
		this.fondo.DibujarFondo(this.entorno);
		
		// BASE
		if(this.base != null) {
			this.base.DibujarBase(this.entorno);
		}
		
		// PRINCESA
				if (this.princesa != null) {			
				this.princesa.Dibujar(this.entorno);
				}
		
		for (int i = 0; i < this.pisos.length; i++) {
			if(this.pisos[i] != null){
			for (int j = 0; j < this.pisos[i].length; j++) {
				if (this.pisos[i][j] != null && this.pisos[i][j].setDestruir()==false) {
                    this.pisos[i][j].Dibujar(this.entorno);
                }
                else if(this.pisos[i][j] != null && this.pisos[i][j].setDestruir()==true){
                    this.pisos[i][j].Dibujar2(this.entorno);
                }
			}
			}
		}
		
		
		// PRINCESA ------------------------------------------------------------------------------------------------------------------------
        // CAMINAR A LA DERECHA
        if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
                && this.princesa.getX() + this.princesa.getAncho() / 2 < this.entorno.ancho()) {
            this.princesa.moverDerecha();
        }
        
        // CAMINAR A LA IZQUIERDA
        if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
                && this.princesa.getX() - this.princesa.getAncho() / 2 > 0) {
            this.princesa.moverIzquierda();
        }
        
        // SE SETTEA LA VARIABLE DIRECCION
        if (this.entorno.sePresiono(this.entorno.TECLA_IZQUIERDA)) {
        	this.princesa.setDireccion(false);
        } else if (this.entorno.sePresiono(this.entorno.TECLA_DERECHA)) {
        	this.princesa.setDireccion(true);
        }
        if (this.princesa != null) {      
        if (this.princesa.isDireccion() == true) {
        	this.princesa.dibujarDer(this.entorno);
        } else if (this.princesa.isDireccion() == false) {
        	this.princesa.dibujarIzq(this.entorno);
        }		
        }
		// Manejar movimientos laterales de la princesa
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)) {
		    this.princesa.setX(this.princesa.getX() + this.princesa.getVelocidad());
		} else if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
		    this.princesa.setX(this.princesa.getX() - this.princesa.getVelocidad());
		}
		
		// Verificar si la princesa está pisando alguna plataforma
		
		boolean pisandoPlataforma = true;
		for (int i = 0; i < this.pisos.length; i++) {
			if(this.pisos[i] != null) {
				
			for (int j = 0; j < this.pisos[i].length; j++) {
				if (this.pisos[i][j] != null) {
					
				
				if (this.princesa.extremoSup() <= this.pisos[i][j].extremoInf()
					&& this.princesa.extremoSup() >= this.pisos[i][j].extremoSup()
					&& this.princesa.getX() >= this.pisos[i][j].extremoIzq()
					&& this.princesa.getX() <= this.pisos[i][j].extremoDer()) {
					this.princesa.setCayendo(true);
				}
				}
				
			}
			}
		}
		
		if (pisandoPlataforma 
			&& this.entorno.estaPresionada(this.TECLA_X) 
			&& !this.princesa.getCayendo() 
			&& this.princesa.getY() > this.princesa.getAlturaSalto()) {
			this.princesa.saltar();
			//System.out.println("rompe");
		} else {
			
			pisandoPlataforma = false;
			for (int i = 0; i < this.pisos.length; i++) {
				if(this.pisos[i]!=null) {
					
				for (int j = 0; j < this.pisos[i].length; j++) {
					if (this.pisos[i][j] != null && (this.princesa.pisando(this.pisos[i][j]) || this.princesa.pisando(base)) ) {
						this.princesa.setAlturaSalto(this.princesa.getY()-140);
						pisandoPlataforma = true;
						this.princesa.setCayendo(false); // Salir del bucle una vez que se encuentra una plataforma
						// 
					}
				}
				}
			}
						
				
			// Si no está pisando una plataforma sólida, dejar de setear Cayendo a false
			if (!pisandoPlataforma) {
				if(this.princesa!=null) {					
			    this.princesa.setCayendo(true);
				}
			}
	
			// Si la princesa está cayendo, aplicar la gravedad
			if (this.princesa!=null && this.princesa.getCayendo()) {
			    this.princesa.descender();
			}
	
			
		}
		
		
// BLOQUES -----------------------------------------------------------------------------------------------------------------------------------
		for (int i = 0; i < this.pisos.length; i++) {
			if (this.pisos[i]!=null) {
				
			for (int j = 0; j < this.pisos[i].length; j++) {
				if (this.pisos[i][j] != null) {					
					if (this.entorno.estaPresionada(this.TECLA_X)
						&& this.princesa.verificarColisionesBloque(this.pisos[i][j])
						&& this.pisos[i][j].setDestruir()==true) {
						System.out.println("rompe");
						this.pisos[i][j] = null;
					}
						
				}
						
			}
						
						
			}
		}
		
		
// END BLOQUES -----------------------------------------------------------------------------------------------------------------------------------
		
		
// DINOSAURIOS -----------------------------------------------------------------------------------------------------------------------------------
        
        // HACEMOS CAMINAR A LOS DINOSAURIOS
        for (int i = 0; i < this.dinosaurios.length; i++) {
        	if (this.dinosaurios[i]!=null) {
        		this.dinosaurios[i].movimiento();        		
        	}
        }
        
        // COLISION Y CAMBIO DE DIRECCION CUANDO LLEGAN AL BORDE DE LA PANTALLA
        for (int i = 0; i < this.dinosaurios.length; i++) {
        	if (this.dinosaurios[i]!=null) {        		
        		if (this.dinosaurios[i].extremoDer() >= this.entorno.ancho()
        				|| this.dinosaurios[i].extremoIzq() <= 0) {
        			this.dinosaurios[i].setVelocidad(this.dinosaurios[i].getVelocidad() * -1);
        		}
        	}
        }
        
        
        for (int i = 0; i < this.pisos.length; i++) {
        	if (this.pisos[i]!=null) {
        	for (int j = 0; j < this.pisos[i].length; j++) {
            	 for (int k = 0; k < dinosaurios.length; k++) {
            		if (this.dinosaurios[k]!=null && this.pisos[i][j]!=null) {
            			if (this.dinosaurios[k].estaEntre(this.pisos[i][j])) {
            				this.dinosaurios[k].setVelocidad(this.dinosaurios[k].getVelocidad() * -1);
            			}
            		}
                }
            }
        	}
        }
        
        // COLISION Y CAMBIO DE DIRECCIÓN ENTRE DINOSAURIOS
        for (int i = 0; i < this.dinosaurios.length; i++) {
            for (int j = 0; j < this.dinosaurios.length; j++) {
            	if (this.dinosaurios[i]!=null && this.dinosaurios[j]!=null) {
            		this.dinosaurios[i].estanColisionando(this.dinosaurios[j]);            		
            	}
            }
        }
        
        for (int k = 0; k < dinosaurios.length; k++) {
            boolean dinosaurioPisandoPlataforma = false;
            for (int i = 0; i < this.pisos.length; i++) {
            	if(this.pisos[i]!=null) {
                for (int j = 0; j < this.pisos[i].length; j++) {
                    if (this.pisos[i][j] != null && this.dinosaurios[k]!=null &&
                            (this.dinosaurios[k].pisando(this.pisos[i][j]) || this.dinosaurios[k].pisando(base) )) {
                        dinosaurioPisandoPlataforma = true;
                        this.dinosaurios[k].setCayendo(false); // Dejar de caer si está sobre una plataforma
                        break; // Salir del bucle una vez que se encuentra una plataforma
                    }
                }
            	}
            }
            
            // Si no está pisando una plataforma sólida, comenzar a caer
            if (this.dinosaurios[k]!=null && !dinosaurioPisandoPlataforma) {
                this.dinosaurios[k].setCayendo(true);
            }
            
            // Aplicar la gravedad al dinosaurio si está cayendo
            if (this.dinosaurios[k]!=null && this.dinosaurios[k].getCayendo()) {
                this.dinosaurios[k].descender();
            }
        }
	
		
        
        for (int i = 0; i < this.dinosaurios.length; i++) {
        	if (this.dinosaurios[i]!=null && this.dinosaurios[i].getVelocidad() > 0) {
                this.dinosaurios[i].dibujarDer(this.entorno);
        	} else if (this.dinosaurios[i]!=null && this.dinosaurios[i].getVelocidad() < 0) {
        		this.dinosaurios[i].dibujarIzq(this.entorno);
        	}
        	
        	
        }
        
     //END DINOSAURIOS ------------------------------------------------------------------------------------------------------------------------------
	
     // 
      //Disparo-----------------------Esto-------------------------------------------------------------------------------

		 // Para disparar un disparo de Izquierda y de Derecha
        if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && this.entorno.sePresiono(this.TECLA_C)
               && this.princesa.extremoDer() < this.entorno.ancho() &&  this.contador.time() == 0 && this.contador.chill()==false)   {
                   System.out.println("entra aca");
           this.disparo = new Disparo(this.princesa.extremoDer(), this.princesa.getY(), 6, 1);
           this.contador.cambio(true);
       }
       
       if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
               && this.entorno.sePresiono(this.TECLA_C)
               && this.princesa.extremoIzq() > 0  && this.contador.time() == 0
               && this.contador.chill()==false ) {
                   System.out.println("entra alla");
           this.disparo = new Disparo(this.princesa.extremoIzq(), this.princesa.getY(), 6, -1);
           this.contador.cambio(true);
       }
       //Dispara el Dinosaurio
       if (this.contadorShotDino != null) {
    	   this.contadorShotDino.avanza();    	   
       
      if (this.contadorShotDino.time() == 300 ) {
       for(int i=0;i<this.disparoDino.length;i++){
           if( this.dinosaurios[i]!=null && this.dinosaurios[i].getVelocidad()==1 ){
              this.disparoDino[i]= new Disparo(this.dinosaurios[i].extremoDer(),this.dinosaurios[i].getY(), 6, 1);
           } 
           else if(this.dinosaurios[i]!=null && this.dinosaurios[i].getVelocidad()==-1  ){
               this.disparoDino[i]= new Disparo(this.dinosaurios[i].extremoDer(),this.dinosaurios[i].getY(), 6, -1);
           }
      }
       this.contadorShotDino.MeRespaldo();
      }
	}
    //Colision Disparo Dino  -----------------COMPLETO-----------------------------------------------------------
   for(int i =0; i< this.dinosaurios.length;i++){
       if(this.disparo != null && this.dinosaurios[i] != null && Colision.colisionDisparoDinos(this.disparo,this.dinosaurios[i]) && this.dinosaurios[i].getY()+ (this.dinosaurios[i].getAlto()/2)>=this.disparo.getY() 
       && this.dinosaurios[i].getY()- (this.dinosaurios[i].getAlto()/2)<=this.disparo.getY() ){
           this.disparo=null;
           this.dinosaurios[i]=null;
           this.datosIngame.sumarVictimas();
           this.datosIngame.sumaPuntos(2);
       }
   }
   //----------------------------------------------------------------------------------------------------------------
   //colision Disparo Disparo-------------------------------COMPLETO--------------------------------------
   if (this.disparoDino != null) {
	   
   for(int i=0;i<this.disparoDino.length;i++){
       if(this.disparo!=null & this.disparoDino[i]!=null && Colision.checkCollisionDis(this.disparo,this.disparoDino[i])){
           this.disparo=null;
           this.disparoDino[i]=null;
           this.datosIngame.sumaPuntos(1);
       }
   }
   }
   //------------------------------------------------------------------------------------------------------------------------
   //Colision Dino Princesa-------------------------Completo-----------------------------------------------------------------------

   for(int i =0; i<this.dinosaurios.length;i++){
       if(this.princesa !=null && this.dinosaurios[i] !=null && Colision.colisionDinoPrincesa(this.dinosaurios[i],this.princesa) && this.princesa.getY()+ (this.princesa.getAlto()/2)>=this.dinosaurios[i].getY() 
    	       && this.princesa.getY()- (this.princesa.getAlto()/2)<=this.dinosaurios[i].getY()){
           this.dinosaurios[i]=null;
           this.datosIngame.daño();
           System.out.println("UWU ME golpeaste");
       }
   }
   //---------------------------------------------------------------------------------------------------------------------------
   //Colision disparo de los Dinos Princesa ---------------Completo-----------------------------------------------------------------
   if (this.disparoDino != null) {
	   
   for(int i=0;i<this.disparoDino.length;i++){
       if(this.princesa!=null && this.disparoDino[i]!= null && Colision.colisionDisparoPrincesa(this.disparoDino[i], this.princesa) && this.princesa.getY()+ (this.princesa.getAlto()/2)>=this.disparoDino[i].getY() 
       && this.princesa.getY()- (this.princesa.getAlto()/2)<=this.disparoDino[i].getY()){
           this.disparoDino[i]=null;
           this.datosIngame.daño();
           System.out.println("Fuck Men , Dont Shoot me");
       }
   }
   }
   //Colision disparo con Bloques
   
   for(int k=0;k<this.pisos.length;k++){
	   if (this.pisos[k]!=null) {		  
       for(int m=0;m<this.pisos[k].length;m++){
           if(this.disparo!=null && this.pisos[k][m]!=null && Colision.colisionDisparoBloque(this.disparo, this.pisos[k][m])&& this.pisos[k][m].getY()+ (this.pisos[k][m].getAlto()/2)>=this.disparo.getY() 
           && this.pisos[k][m].getY()- (this.pisos[k][m].getAlto()/2)<=this.disparo.getY()){
               this.disparo=null;
           }
       }
	   }       
      }
      for(int i=0;i<this.pisos.length;i++){
    	  if(this.pisos[i]!=null) {
    		  
       for(int j=0;j<this.pisos[i].length;j++){
    	   if (this.disparoDino != null) {
    		   
           for(int k=0;k<this.disparoDino.length;k++){
               if(this.disparoDino[k]!=null&& this.pisos[i][j]!=null && Colision.colisionDisparoBloque(this.disparoDino[k], this.pisos[i][j])&& this.pisos[i][j].getY()+ (this.pisos[i][j].getAlto()/2)>=this.disparoDino[k].getY() 
           && this.pisos[i][j].getY()- (this.pisos[i][j].getAlto()/2)<=this.disparoDino[k].getY()){
               this.disparoDino[k]=null;
               }
              }
    	   }
           
           }
    	  }

       }
   
   //---------------------------------------------------------------------------------------------------------------------------------
   // Condiciones para terminar el juego----------------------------------------------------------------------------------------------
   if (this.datosIngame != null) {
   if(this.datosIngame.CantVidas()==0){
       System.out.println("Te quedaste sin vidas moriste");
       System.exit(0);
   }
   }
   if(this.princesa!=null && this.princesa.extremoInf()==0){
       System.out.println("Excelente, !!!!!escapaste¡¡¡¡¡");
       System.exit(0);    
   }








		// Quiero Hacer que espere un tiempo entre disparo y disparo 
		// Empiezo el temporizador
   if (this.contador != null) {
	   
		if (this.contador.chill()==true) {
           this.contador.avanza();
       }
		if(this.contador.time()==100){
			this.contador.MeRespaldo();
		}
		if(this.contador.time()==0){
           this.contador.cambio(false);
       }
   }

		 // actualizar disparos
        
           if(this.disparo != null){
		    this.disparo.Actualizacion(this.entorno.ancho());
       }
        
           if(this.disparoDino != null){
        	   
		 for(int i=0;i<this.disparoDino.length;i++){
           if(this.disparoDino[i] != null){
			 this.disparoDino[i].Actualizacion(this.entorno.ancho());
		 }	
		 }
       
        //destruccion del disparo
       
        if(this.disparo != null){
        if (this.disparo.FueraDeLosBordes(this.entorno.ancho())) {
           this.disparo = null;
        } 
       } 
       
        for(int j=0; j< this.disparoDino.length;j++){
           if(this.disparoDino[j]!= null && this.disparoDino[j].FueraDeLosBordes(this.entorno.ancho())){
               this.disparoDino[j]=null;
           }
       }
        }
		// Dibujar el tiro de la princesa y de los dinosaurios
      
           if (this.disparo != null) {
            this.disparo.dibujar(entorno);
       }
           if(this.disparoDino != null){        	   
        	   for(int i=0;i<this.disparoDino.length;i++){
           			if (this.disparoDino[i]!= null) {
           				this.disparoDino[i].dibujarShotDino(entorno);
           			}
           
       			}
           }
       //______________________________________________________________________________________________________________________________Fin  
     
    //    
           
           
           if(this.datosIngame != null){        	   
        	   this.datosIngame.Dibujar(this.entorno);    
           }
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
