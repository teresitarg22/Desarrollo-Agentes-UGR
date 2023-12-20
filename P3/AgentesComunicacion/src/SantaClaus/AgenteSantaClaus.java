
package SantaClaus;

import Comportamientos.ComunicacionSantaClaus;
import Elementos.Entorno;

import jade.core.Agent;
import java.util.AbstractMap.SimpleEntry;

/**
 * @author Teresa Reyes García.
 */
public class AgenteSantaClaus extends Agent {
    
    private SimpleEntry<Integer,Integer> posicion;
    
    // -------------------------------------------------
    // Inicialización del agente Santa Claus.
    @Override
    public void setup(){
        Entorno entorno = (Entorno) getArguments()[0];
        
        this.posicion = entorno.generarCoordenadas();
        
        addBehaviour(new ComunicacionSantaClaus(entorno));
    } 
    
    public SimpleEntry<Integer,Integer> getPosicion() {
        return this.posicion;
    }
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Santa Claus...");
    }
   
}
