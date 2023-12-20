package Elementos;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

/**
 * @author Teresa Reyes García.
 */
public class mainRudolph {
    
    public static void main(Entorno entorno, AgentContainer container) {
        // Crear y lanzar el agente. Uso de try catch para mostrar errores en caso de fallo.
        try {
           AgentController ac = container.createNewAgent("AgenteRudolph","Rudolph.AgenteRudolph", new Object[]{entorno});
            ac.start();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
