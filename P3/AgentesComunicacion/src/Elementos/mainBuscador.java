package Elementos;


// Añadimos al agente: BUSCADOR
import Buscador.AgenteBuscador;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

/**
 * @author Teresa Reyes García.
 */
public class mainBuscador {
    private Entorno entorno;
    
    public mainBuscador(Entorno entorno){
        this.entorno = entorno;
    }
    
    public static void main(String[] args) {
        
        // Creamos el contenedor de los agentes.
        Runtime rt = Runtime.instance();

        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);
        
        // Crear y lanzar el agente. Uso de try catch para mostrar errores en caso de fallo.
        try {
           AgentController ac = container.createNewAgent("AgenteBuscador","Buscador.AgenteBuscador", null);
            ac.start();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
