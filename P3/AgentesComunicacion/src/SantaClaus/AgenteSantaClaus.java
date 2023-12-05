
package SantaClaus;

import jade.core.Agent;
import java.util.Random;

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
    
    // ----------------------------------------------------------------------------------------------------------
    // Función que calcula si el Buscador es apto o no para recibir la misión.
    private boolean somosAptos() {
        // Queremos simular que el 80% son confiables (buenos) y el 20% no son confiables (malos).
        Random random = new Random();
        return random.nextDouble() <= 0.8;
    }
}
