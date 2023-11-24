
package Comportamientos;

import jade.core.behaviours.SimpleBehaviour;
import Agente.Entorno;

/**
 * @author Diego Velázquez Ortuño
 */
public class Vision extends SimpleBehaviour {
    private final Entorno entorno;
    
    // -------------------------------------------------------
    // Constructor.
    public Vision(Entorno entorno){
        this.entorno = entorno;
    }
    
    // -------------------------------------------------------
    @Override
    public void action(){
        // Comprobamos si se ha alcanzado el objetivo.
        if (this.entorno.getPosicionAgente().equals(this.entorno.getPosicionObjetivo())) {
            System.out.println("¡Se ha alcanzado el objetivo! Nº pasos: " + this.entorno.getPasos());
            
            myAgent.doDelete(); // Eliminamos el agente una vez finalizado.
        }
        else this.entorno.actualizarSensores(); // Actualizamos los sensores.
    }
    
    // -------------------------------------------------------
    @Override
    public boolean done(){
        return false;
    }
}
