package Comportamientos;

import jade.core.behaviours.SimpleBehaviour;
import Agente.Entorno;

/**
 * @author Sergio Muñoz Gómez 
 * @author Teresa Fernanda Reyes García
 */
public class Mover extends SimpleBehaviour{
    private final Entorno entorno;
    
    // ----------------------------------------------------------------------------------
    // Constructor
    public Mover(Entorno entorno){
        this.entorno = entorno;
    }
    
    // ----------------------------------------------------------------------------------
    // Se realiza el movimiento del agente según el siguiente movimiento calculado.
    @Override
    public void action(){
        //block(200); // Hacemos esto para poder mostrar en la GUI los pasos del agente lentamente.
        this.entorno.actualizarPosicionAgente(); // Actualizamos la posición del agente.
    }
    
    // ----------------------------------------------------------------------------------
    @Override
    public boolean done(){
        return false;
    }
}