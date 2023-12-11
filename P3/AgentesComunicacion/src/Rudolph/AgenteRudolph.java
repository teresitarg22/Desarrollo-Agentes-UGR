
package Rudolph;

import Comportamientos.ComunicacionRudolph;
import jade.core.Agent;

import Elementos.Entorno;

/**
 * @author Teresa Reyes García.
 */
public class AgenteRudolph extends Agent {
    
    // -------------------------------------------------
    // Constructor
    public AgenteRudolph(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        addBehaviour(new ComunicacionRudolph());
    }
    
    // -------------------------------------------------
    // Inicialización del agente Rudolph.
    @Override
    public void setup(){
        
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Rudolph...");
    }
    
}
