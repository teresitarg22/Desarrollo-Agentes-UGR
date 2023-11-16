
package Agente;

import jade.core.Agent;

import Comportamientos.Vision;
import Comportamientos.Mover;
import Comportamientos.TomaDecision;
import Comportamientos.IniciarEstado;

/**
 * @author Diego Velazquez Ortuño
 */
public class AgenteMundo2D extends Agent {

    // -------------------------------------------------
    // Inicialización del agente.
    @Override
    public void setup(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        addBehaviour( new IniciarEstado() );
        addBehaviour( new Vision(entorno) );
        addBehaviour( new TomaDecision(entorno) );
        addBehaviour( new Mover(entorno) );
    }   
}

