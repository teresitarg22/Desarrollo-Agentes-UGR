
package Rudolph;

import jade.core.Agent;

/**
 * @author Teresa Reyes García.
 */
public class AgenteRudolph extends Agent {
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
