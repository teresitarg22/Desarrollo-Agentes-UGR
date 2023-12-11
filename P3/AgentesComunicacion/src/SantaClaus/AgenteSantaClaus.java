
package SantaClaus;

import Comportamientos.ComunicacionSantaClaus;
import Elementos.Entorno;

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
        Entorno entorno = (Entorno) getArguments()[0];
        
        addBehaviour(new ComunicacionSantaClaus());
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Santa Claus...");
    }
   
}
