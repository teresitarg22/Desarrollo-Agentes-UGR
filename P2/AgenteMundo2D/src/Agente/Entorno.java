
package Agente;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList; 
import java.util.AbstractMap.SimpleEntry;



/**
 * @ author Julia Cano Flores
 */
public class Entorno {
    private Mapa mapa;
    private EntornoListener entornoListener;
    private DecisionListener decisionListener;
    private ArrayList<PosiblesMovimientos> decisionesTomadas;
    private int[] sensores;
    
    private SimpleEntry<Integer, Integer> posicionObjetivo = new SimpleEntry<>(1, 1); // Por defecto el objetivo está en la 0,0
    private SimpleEntry<Integer, Integer> posicionAgente = new SimpleEntry<>(0, 0); // Por defecto el agente está en la 0,0
    
    private PosiblesMovimientos siguienteMovimiento; // Cuál va a ser el próximo movimiento.
    private int segundoMejor;

    // ---------------------------------------------
    // Constructor.
    public Entorno(Mapa mapa) {
        this.mapa = mapa;
        this.segundoMejor = Integer.MAX_VALUE;
        this.siguienteMovimiento = null;
        this.sensores = new int[PosiblesMovimientos.values().length];
        this.decisionesTomadas = new ArrayList<PosiblesMovimientos>();

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
            getPesos().put(this.posicionAgente, this.segundoMejor+1);
            this.posicionAgente = coordenadas;
            this.siguienteMovimiento = null;
            
            // Llamamos al listener del entorno.
            this.entornoListener.onPosicionAgenteActualizada(this.posicionAgente);
        }
    }  
    
    // ---------------------------------------------
    // Actualiza la posición del agente.
    public void listarDecisiones(){
        if(this.decisionListener != null){
            this.decisionListener.onDecisionTomada(this.decisionesTomadas);
        }
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
        this.decisionesTomadas.add(siguienteMovimiento);
    }
    
    // ---------------------------------------------
    // Establecer el Listener del entorno.
    public void setEntornoListener(EntornoListener listener){
        this.entornoListener = listener;
    }
    
    // ---------------------------------------------
    // Establecer el Listener de las decisiones tomadas.
    public void setDecisionListener(DecisionListener listener){
        this.decisionListener = listener;
    }
}
