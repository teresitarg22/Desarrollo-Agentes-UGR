
package Agente;

import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

/**
 * @ author Julia Cano Flores
 */
public class Entorno {
    private Mapa mapa;
    private SimpleEntry<Integer, Integer> posicionObjetivo = new SimpleEntry<>(1, 1); // Por defecto el objetivo está en la 0,0
    private SimpleEntry<Integer, Integer> posicionAgente = new SimpleEntry<>(0, 0); // Por defecto el agente está en la 0,0
    private int[] sensores;// = new int[8];
    private PosiblesMovimientos siguienteMovimiento;
    private EntornoListener entornoListener;
    private int segundoMejor;

    // ---------------------------------------------
    // Constructor.
    public Entorno(Mapa mapa) {
        this.mapa = mapa;
        this.segundoMejor = Integer.MAX_VALUE;
        this.siguienteMovimiento = null;
        this.sensores = new int[PosiblesMovimientos.values().length];

        this.actualizarSensores();
    }
    
    // ---------------------------------------------
    // Actualiza los sensores.
    public void actualizarSensores() {
        /*int contador = 0; 
        
        for (int i=posicionAgente.getKey()-1; i<=posicionAgente.getKey()+1; i++){
            for (int j=posicionAgente.getValue()-1; j<=posicionAgente.getValue(); j++){
                
                if(i != posicionAgente.getKey() || j != posicionAgente.getValue()){         // Ver alrededor del agente, no en la posición.
                    SimpleEntry<Integer, Integer> coordenadas = new SimpleEntry<>(i,j);
                    if (mapa.esAccesible(coordenadas)){
                        sensores[contador]=mapa.obtenerCelda(coordenadas); 
                    }
                    else{
                        sensores[contador] = -1;                                        // Si estás al borde del mapa, marca como no disponible.
                    }

                    contador++;
                }   
            }
        }*/
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            sensores[movimiento.ordinal()] = mapa.obtenerCelda(movimiento.sumar(this.posicionAgente));
        }
    }
    
    public void setSegundoMejor(int valor) {
        this.segundoMejor = valor;
    }
    
    public Map<SimpleEntry<Integer,Integer>,Integer> getPesos() {
        return this.mapa.getPesos();
    }
    
    // ---------------------------------------------
    // Actualiza la posición del agente.
    public void actualizarPosicionAgente(/*Integer arribaAbajo, Integer derechaIzquierda*/){    // Permite mover arriba, abajo, derecha, izquierda y diagonales. 
        
        //if (arribaAbajo <= 1 && arribaAbajo >= -1 && derechaIzquierda <= 1 && derechaIzquierda >= -1){  // Solo puedes avanzar una casilla.
            //int nueva_i = posicionAgente.getKey() + arribaAbajo; 
            //int nueva_j = posicionAgente.getValue() + derechaIzquierda; 

            //SimpleEntry<Integer, Integer> coordenadas = new SimpleEntry<>(nueva_i,nueva_j);
            SimpleEntry<Integer,Integer> coordenadas = this.siguienteMovimiento.sumar(this.posicionAgente);
            //if (mapa.esAccesible(coordenadas) && mapa.obtenerCelda(coordenadas)!=-1){ // La siguiente posición debe ser válida.
            if (this.entornoListener != null) {
                getPesos().put(this.posicionAgente, this.segundoMejor+1);
                this.posicionAgente = coordenadas;
                this.siguienteMovimiento = null;
                //this.actualizarSensores();
                this.entornoListener.onPosicionAgenteActualizada(this.posicionAgente);
            }
            else 
                System.out.print("No puedo continuar en esta dirección.");
        //}
    }  
    
    // ---------------------------------------------
    // Obtener los sensores.
    public int[] getSensores(){
        return sensores; 
    }
    
    // ---------------------------------------------
    // Establecer la posición actual del agente.
    public void setPosicionAgente(SimpleEntry<Integer,Integer> pos) {
        this.posicionAgente = pos;
    }
    
    // ---------------------------------------------
    // Establecer la posición del objetivo.
    public void setPosicionObjetivo(SimpleEntry<Integer,Integer> pos) {
        this.posicionObjetivo = pos;
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
    
    // ---------------------------------------------
    // Establecer la siguiente posición
    public void setSiguienteMovimiento(PosiblesMovimientos siguienteMovimiento) {
        this.siguienteMovimiento = siguienteMovimiento;
    }
    
    public void setEntornoListener(EntornoListener listener){
        this.entornoListener = listener;
    }
}
