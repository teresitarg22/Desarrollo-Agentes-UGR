
package Comportamientos;

import jade.core.behaviours.SimpleBehaviour;

import Agente.Entorno;

/**
 * @author Diego Velázquez Ortuño
 */
public class Vision extends SimpleBehaviour {
    private Entorno entorno;
    
    public Vision(Entorno entorno){
        this.entorno = entorno;
    }
    
    @Override
    public void action(){
        this.entorno.actualizarSensores();
    }
    
    @Override
    public boolean done(){
        return false;
    }
}
