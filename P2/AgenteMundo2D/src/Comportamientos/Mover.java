package Comportamientos;

// Realizamos los imports que necesitamos.
import jade.core.behaviours.SimpleBehaviour;

/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class Mover extends SimpleBehaviour{
    boolean finalizado = false;
    
    public Mover(){
        //myAgent.getSiguienteMovimiento();
    }
    
    // ----------------------------------------------------------------------------------
    // Se realiza el movimiento del agente según el siguienteMovimiento.
    @Override
    public void action(){
        
    }
    
    // ----------------------------------------------------------------------------------
    // 
    @Override
    public boolean done(){
        return finalizado;
    }
}