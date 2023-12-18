
package Buscador;

import Comportamientos.ComunicacionBuscador;
import jade.core.Agent;
import Elementos.Entorno;

/**
 * @author Teresa Reyes García.
 */
public class AgenteBuscador extends Agent {
    
    // -------------------------------------------------
    // Inicialización del agente Buscador.
    @Override
    public void setup(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        addBehaviour(new ComunicacionBuscador(entorno));
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Buscador...");
    }
    
}
