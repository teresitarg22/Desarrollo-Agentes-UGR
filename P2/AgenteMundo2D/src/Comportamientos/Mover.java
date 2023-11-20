package Comportamientos;

// Realizamos los imports que necesitamos.
import jade.core.behaviours.SimpleBehaviour;
import java.util.AbstractMap.SimpleEntry;
import Agente.AgenteMundo2D;
import Agente.Entorno;

/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class Mover extends SimpleBehaviour{
    private Entorno entorno;
    /*SimpleEntry<Integer,Integer> siguienteMovimiento = null;
    Integer heuristica = null;*/
    
    public Mover(Entorno entorno){
        this.entorno = entorno;
        /*this.heuristica = ((AgenteMundo2D) myAgent).getHeuristica();
        this.siguienteMovimiento = ((AgenteMundo2D) myAgent).getSiguienteMovimiento();*/
    }
    
    // ----------------------------------------------------------------------------------
    // Se realiza el movimiento del agente según el siguienteMovimiento.
    @Override
    public void action(){
        // Actualizamos la posición del agente con las coordenadas dadas y actualizamos en nuestro mapa de pesos la heurística.
        /*this.entorno.actualizarPosicionAgente(this.siguienteMovimiento.getKey(), this.siguienteMovimiento.getValue());
        this.entorno.actualizarPesos(this.siguienteMovimiento, this.heuristica);*/
        block(250); // - SÓLO USAR EN PRUBAS, tira muchisimo de cpu
        this.entorno.actualizarPosicionAgente();
    }
    
    // ----------------------------------------------------------------------------------
    // 
    @Override
    public boolean done(){
        //System.out.println("Mover");
        return false;
    }
}