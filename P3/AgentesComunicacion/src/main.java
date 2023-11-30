
// Añadimos a todos los agentes.
import Buscador.AgenteBuscador;
import Rudolph.AgenteRudolph;
import SantaClaus.AgenteSantaClaus;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

/**
 * @author Teresa Reyes García.
 */
public class main {
    
    public static void main(String[] args) {
       // Creamos el contenedor de los agentes.
        Runtime rt = Runtime.instance();

        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);
    }
}
