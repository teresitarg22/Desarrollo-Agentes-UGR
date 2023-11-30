
package Buscador;

import jade.core.Agent;

/**
 * @author Teresa Reyes García.
 */
public class AgenteBuscador extends Agent {
    // -------------------------------------------------
    // Inicialización del agente Buscador.
    @Override
    public void setup(){
        
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Buscador...");
    }
}
