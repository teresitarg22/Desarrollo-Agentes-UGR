
package Agente;

import jade.core.Agent;

import Comportamientos.Vision;
import Comportamientos.Mover;
import Comportamientos.TomaDecision;


/**
 * @author Diego Velazquez Ortuño
 */
public class AgenteMundo2D extends Agent {
        
    // -------------------------------------------------
    // Inicialización del agente.
    @Override
    public void setup(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        addBehaviour( new Vision(entorno) );
        addBehaviour( new TomaDecision(entorno) );
        addBehaviour( new Mover(entorno) );
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente...");
    }
}

