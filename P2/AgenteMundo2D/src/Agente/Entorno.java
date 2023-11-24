
package Agente;

import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

/**
 * @ author Julia Cano Flores
 */
public final class Entorno {
    private final Mapa mapa;
    private EntornoListener entornoListener;
    private int[] sensores;
    private int pasos;
    
    private SimpleEntry<Integer, Integer> posicionObjetivo; 
    private SimpleEntry<Integer, Integer> posicionAgente; 
    
    private PosiblesMovimientos siguienteMovimiento; // Cuál va a ser el próximo movimiento.
    private int segundoMejor;

    // ---------------------------------------------
    // Constructor.
    public Entorno(Mapa mapa) {
        this.posicionObjetivo = new SimpleEntry<>(1, 1); // Por defecto el objetivo está en la celda 1,1
        this.posicionAgente = new SimpleEntry<>(0, 0); // Por defecto el agente está en la celda 0,0
        
        this.mapa = mapa;
        this.segundoMejor = Integer.MAX_VALUE;
        this.siguienteMovimiento = null;
        this.sensores = new int[PosiblesMovimientos.values().length];
        this.pasos = 0;

        this.actualizarSensores();
    }
    
    // ---------------------------------------------
    // Actualiza los sensores.
    public void actualizarSensores() {
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            sensores[movimiento.ordinal()] = mapa.obtenerCelda(movimiento.sumar(this.posicionAgente));
        }
    }
    
    // ---------------------------------------------
    // Actualiza la posición del agente.
    public void actualizarPosicionAgente(){     
        // Permite mover arriba, abajo, derecha, izquierda y diagonales.
        SimpleEntry<Integer,Integer> coordenadas = this.siguienteMovimiento.sumar(this.posicionAgente);

        if (this.entornoListener != null) {
            //this.mapa.getVecesPisada().put(this.posicionAgente, this.mapa.getVecesPisada().get(this.posicionAgente)+1);
            
            /*if (this.mapa.getVecesPisada().get(this.posicionAgente) > 3)
                getPesos().put(this.posicionAgente, Integer.MAX_VALUE);
            else*/
                getPesos().put(this.posicionAgente, this.segundoMejor+1);
            
            this.posicionAgente = coordenadas;
           
            // Llamamos al listener del entorno.
            this.entornoListener.onPosicionAgenteActualizada(this.siguienteMovimiento);
            
            this.siguienteMovimiento = null;
            
            this.pasos++;
        }
    }  
    
    public int getPasos() {
        return this.pasos;
    }
       
    // --------------------------------- GETTERS ---------------------------------
    
    // ---------------------------------------------
    // Obtener los sensores.
    public int[] getSensores(){
        return sensores; 
    }
    
    // ---------------------------------------------
    // Obtener el mapa de pesos.
    public Map<SimpleEntry<Integer,Integer>,Integer> getPesos() {
        return this.mapa.getPesos();
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
    
    // --------------------------------- SETTERS ---------------------------------
    
    // ---------------------------------------------
    // Establecemos el segundo mejor peso.
    public void setSegundoMejor(int valor) {
        this.segundoMejor = valor;
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
    // Establecer la siguiente posición.
    public void setSiguienteMovimiento(PosiblesMovimientos siguienteMovimiento) {
        this.siguienteMovimiento = siguienteMovimiento;
    }
    
    // ---------------------------------------------
    // Establecer el Listener del entorno.
    public void setEntornoListener(EntornoListener listener){
        this.entornoListener = listener;
    }
}
