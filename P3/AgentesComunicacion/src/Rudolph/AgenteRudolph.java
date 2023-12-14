
package Rudolph;

import Comportamientos.ComunicacionRudolph;
import jade.core.Agent;

import Elementos.Entorno;

/**
 * @author Teresa Reyes García.
 */
public class AgenteRudolph extends Agent {
    
    // -------------------------------------------------
    // Inicialización del agente Rudolph.
    @Override
    public void setup(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        addBehaviour(new ComunicacionRudolph(entorno));
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Rudolph...");
    }
    
}
