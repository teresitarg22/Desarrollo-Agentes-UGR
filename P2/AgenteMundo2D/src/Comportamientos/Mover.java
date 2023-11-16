package Comportamientos;

// Realizamos los imports que necesitamos.
import jade.core.behaviours.SimpleBehaviour;

import Agente.Entorno;

/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class Mover extends SimpleBehaviour{
    private Entorno entorno;
    
    boolean finalizado = false;
    
    public Mover(Entorno entorno){
        this.entorno = entorno;
        //myAgent.getSiguienteMovimiento();
    }
    
    // ----------------------------------------------------------------------------------
    // Se realiza el movimiento del agente según el siguienteMovimiento.
    @Override
    public void action(){
        this.entorno.actualizarPosicionAgente(,);
    }
    
    // ----------------------------------------------------------------------------------
    // 
    @Override
    public boolean done(){
        // ?? revisar esto:
        this.entorno.actualizarPesos(,);
        return finalizado;
    }
}