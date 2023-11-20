
package Agente;

import jade.core.Agent;

import Comportamientos.Vision;
import Comportamientos.Mover;
import Comportamientos.TomaDecision;
import Comportamientos.IniciarEstado;
import java.util.AbstractMap.SimpleEntry;


/**
 * @author Diego Velazquez Ortuño
 */
public class AgenteMundo2D extends Agent {
    SimpleEntry<Integer,Integer> siguienteMovimiento = null;
    Integer heuristica = null;
        
    // -------------------------------------------------
    // Inicialización del agente.
    @Override
    public void setup(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        //addBehaviour( new IniciarEstado() );
        addBehaviour( new Vision(entorno) );
        addBehaviour( new TomaDecision(entorno) );
        addBehaviour( new Mover(entorno) );
    } 
    
    // -----------------------------------------------------------
    /*public SimpleEntry<Integer,Integer>getSiguienteMovimiento(){
        return this.siguienteMovimiento;
    }
    
    public Integer getHeuristica(){
        return this.heuristica;
    }
    
    public void setSiguienteMovimiento(SimpleEntry<Integer,Integer> movimiento, Integer heuristica){
        this.siguienteMovimiento = movimiento;
        this.heuristica = heuristica;
    }*/
}

