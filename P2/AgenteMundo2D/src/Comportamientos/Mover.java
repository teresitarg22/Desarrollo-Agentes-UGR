package Comportamientos;

// Realizamos los imports que necesitamos.
import jade.core.behaviours.SimpleBehaviour;
import Agente.Mapa;

/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class Mover extends SimpleBehaviour{
    boolean finalizado = false;
    // int [][] mapa_aux = 
    
    public Mover(int [] movimiento, int distancia){
        
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