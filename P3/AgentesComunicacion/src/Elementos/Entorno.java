
package Elementos;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 * @ author Julia Cano Flores
 */
public final class Entorno {
    private final Mapa mapa;
    private EntornoListener entornoListener;
    private final int[] sensores;
    //private int pasos;
    
    private SimpleEntry<Integer, Integer> posicionObjetivo; 
    private SimpleEntry<Integer, Integer> posicionBuscador; 
    private SimpleEntry<Integer, Integer> posicionRudolph;
    //private SimpleEntry<Integer, Integer> posicionSanta; 
    
    private PosiblesMovimientos siguienteMovimiento; // Cuál va a ser el próximo movimiento.
    //private int segundoMejor;

    // ---------------------------------------------
    // Constructor.
    public Entorno(Mapa mapa) {
        this.posicionBuscador = new SimpleEntry<>(0, 0); // Por defecto el agente está en la celda 0,0
        //this.posicionSanta = new SimpleEntry<>(1, 1); // Por defecto el objetivo está en la celda 1,1
        this.posicionRudolph = new SimpleEntry<>(10,10);
        this.posicionObjetivo = this.posicionBuscador;
        
        this.mapa = mapa;
        //this.segundoMejor = Integer.MAX_VALUE;
        this.siguienteMovimiento = null;
        this.sensores = new int[PosiblesMovimientos.values().length];
        //this.pasos = 0;

        this.actualizarSensores();
    }
    
    // ---------------------------------------------
    // Actualiza los sensores.
    public void actualizarSensores() {
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            sensores[movimiento.ordinal()] = mapa.obtenerCelda(movimiento.sumar(this.posicionBuscador));
        }
    }
    
    // ---------------------------------------------
    // Actualiza la posición del agente.
    public void actualizarPosicionAgente(){
        // Permite mover arriba, abajo, derecha, izquierda y diagonales.
        //SimpleEntry<Integer,Integer> coordenadas = this.siguienteMovimiento.sumar(this.posicionBuscador);

        if (this.entornoListener != null) {
            //this.mapa.getVecesPisada().put(this.posicionBuscador, this.mapa.getVecesPisada().get(this.posicionBuscador)+1);
          
            //getPesos().put(this.posicionBuscador, this.segundoMejor+1);
            this.posicionBuscador = this.siguienteMovimiento.sumar(this.posicionBuscador);
           
            // Llamamos al listener del entorno.
            this.entornoListener.onPosicionAgenteActualizada(this.siguienteMovimiento);
            
            this.siguienteMovimiento = null;
            
            //this.pasos++;
        }
    }
    
    //Actualiza la posición del reno
    public void actualizarReno(SimpleEntry<Integer, Integer> coord){
        if (this.entornoListener != null) {
            this.entornoListener.onPosicionRenoActualizada(coord);
        } 
    }
    
    public void limpiarCamino() {
        if (this.entornoListener != null) {
            this.entornoListener.onRenoEncontrado();
        } 
    }
    
    public void setSanta(SimpleEntry<Integer,Integer> coord) {
        this.entornoListener.onSantaEncontrado(coord);
        this.setPosicionObjetivo(coord);
    }
    
    public void ultimoReno(){
        if (this.entornoListener != null) {
            this.entornoListener.onUltimoReno();
        }
          
    }
    
    // ---------------------------------------------
    // Añade la acción tomada en el protocolo.
    public void setAccion(String accion) {
        this.entornoListener.onVisualizarAccion(accion);
    }
    
    // ----------------------------------------------------------------------------------------------------------
    // Generamos de manera aleatoria las coordenadas de los renos que tiene que buscar el Agente Buscador.

    public AbstractMap.SimpleEntry<Integer, Integer> generarCoordenadas() {
        Random random = new Random();
        
        int x = random.nextInt(this.mapa.getFilas());  // Coordenada x : Número de filas del mapa.
        int y = random.nextInt(this.mapa.getColumnas());  // Coordenada y : Número de columnas del mapa.
        
        return new AbstractMap.SimpleEntry<>(x, y);
    }
 
       
    // --------------------------------- GETTERS ---------------------------------
    
    // ---------------------------------------------
    // Obtener los sensores.
    public int[] getSensores(){
        return sensores; 
    }
    
    // ---------------------------------------------
    // Obtener la posición actual del agente.
    public SimpleEntry<Integer, Integer> getPosicionBuscador() {
        return this.posicionBuscador;
    }
    
    // ---------------------------------------------
    // Obtener la posición del objetivo.
    public SimpleEntry<Integer, Integer> getPosicionObjetivo() {
        return this.posicionObjetivo;
    }
    
    // ---------------------------------------------
    // Obtener la posición de Rudolph.
    public SimpleEntry<Integer, Integer> getPosicionRudolph() {
        return this.posicionRudolph;
    }
    
    
    // --------------------------------- COMPROBADORES -----------------------------
    
    // ---------------------------------------------
    // Comprobamos si el agente se encuentra en el objetivo.
    public boolean enObjetivo() {
        return this.posicionBuscador.equals(this.posicionObjetivo);
    }
    
    
    // --------------------------------- SETTERS ---------------------------------
    
    // ---------------------------------------------
    // Establecer la posición actual del agente.
    public void setPosicionBuscador(SimpleEntry<Integer,Integer> pos) {
        this.posicionBuscador = pos;
    }
    
    // ---------------------------------------------
    // Establecer la posición del objetivo.
    public void setPosicionObjetivo(SimpleEntry<Integer,Integer> pos) {
        this.posicionObjetivo = pos;
    }
    
    // ---------------------------------------------
    // Establecer la posición de Rudolph.
    public void setPosicionRudolph(SimpleEntry<Integer,Integer> pos) {
        this.posicionRudolph = pos;
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
