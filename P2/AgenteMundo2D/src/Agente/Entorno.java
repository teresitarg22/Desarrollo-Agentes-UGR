
package Agente;

import java.util.AbstractMap.SimpleEntry;

/**
 * @ author Julia Cano Flores
 */
public class Entorno {
    private Mapa mapa;
    private SimpleEntry<Integer, Integer> posicionObjetivo = new SimpleEntry<>(1, 1); // Por defecto el objetivo está en la 0,0
    private SimpleEntry<Integer, Integer> posicionAgente = new SimpleEntry<>(0, 0); // Por defecto el agente está en la 0,0
    private int[] sensores = new int[8]; 

    // ---------------------------------------------
    // Constructor.
    public Entorno(Mapa mapa) {
        this.mapa = mapa;
        generarSensores(); 
    }
    
    // ---------------------------------------------
    // Genera los sensores.
    private void generarSensores(){
        int contador = 0; 
        
        for (int i=posicionAgente.getKey()-1; i<=posicionAgente.getKey()+1; i++){
            for (int j=posicionAgente.getValue()-1; j<=posicionAgente.getValue(); j++){
                
                if(i != posicionAgente.getKey() || j != posicionAgente.getValue()){         //Ver alrededor del agente, no en la posición 
                    if (mapa.esAccesible(i, j)){
                        sensores[contador]=mapa.obtenerCelda(i, j); 
                    }
                    else{
                        sensores[contador] = -1;                                        // Si estás al borde del mapa, marca como no disponible
                    }

                    contador++;
                }   
            }
        } 
    }
    
    // ---------------------------------------------
    // Actualiza la posición del agente.
    public void actualizarPosicionAgente(int arribaAbajo, int derechaIzquierda){    // Permite mover arriba, abajo, derecha, izquierda y diagonales. 
        
        if (arribaAbajo <= 1 && arribaAbajo >= -1 && derechaIzquierda <= 1 && derechaIzquierda >= -1){  // Solo puedes avanzar una casilla
            int nueva_i = posicionAgente.getKey() + arribaAbajo; 
            int nueva_j = posicionAgente.getValue() + derechaIzquierda; 

            if (mapa.esAccesible(nueva_i, nueva_j) && mapa.obtenerCelda(nueva_i, nueva_j)!=-1){ // La siguiente posición debe ser válida
                posicionAgente = new SimpleEntry<>(nueva_i, nueva_j); 
                generarSensores(); 
            }
            else 
                System.out.print("No puedo continuar en esta dirección.");
        }
    }  
    
    // ---------------------------------------------
    // Obtener los sensores.
    public int[] getSensores(){
        return sensores; 
    }
    
    // ---------------------------------------------
    // Obtener la posición actual del agente.
    public SimpleEntry<Integer, Integer> getPosicionAgente() {
        return posicionAgente;
    }
    
    // ---------------------------------------------
    // Obtener la posición del objetivo.
    public SimpleEntry<Integer, Integer> getPosicionObjetivo() {
        return posicionObjetivo;
    }
}
