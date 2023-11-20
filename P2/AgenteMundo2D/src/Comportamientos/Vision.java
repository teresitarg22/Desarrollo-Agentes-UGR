
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
        if (this.entorno.getPosicionAgente().equals(this.entorno.getPosicionObjetivo())) {
            System.out.println("¡Se ha alcanzado el objetivo!");
            myAgent.doDelete();
            //System.exit(0);
        }
        else this.entorno.actualizarSensores();
    }
    
    @Override
    public boolean done(){
        //System.out.println("Visión");
        return false;
    }
}
