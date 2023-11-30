
package SantaClaus;

import jade.core.Agent;

/**
 *
 * @author Teresa Reyes García.
 */
public class AgenteSantaClaus extends Agent {
    // -------------------------------------------------
    // Inicialización del agente Santa Claus.
    @Override
    public void setup(){
        
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Santa Claus...");
    }
}
