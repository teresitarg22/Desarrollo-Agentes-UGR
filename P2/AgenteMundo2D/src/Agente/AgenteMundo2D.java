
package Agente;

import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

import jade.core.Agent;

import Comportamientos.Vision;
import Comportamientos.Mover;
import Comportamientos.TomaDecision;
import Comportamientos.IniciarEstado;

/**
 * @author Diego Velazquez Ortuño
 */
public class AgenteMundo2D extends Agent{
    
    Map< SimpleEntry<Integer,Integer> , Integer > pesos = HashMap<>();

    // -------------------------------------------------
    // Inicialización del agente.
    @Override
    public void setup(){
        addBehaviour( new IniciarEstado() );
        addBehaviour( new Vision() );
        addBehaviour( new TomaDecision() );
        addBehaviour( new Mover() );
    }
    
    
}
