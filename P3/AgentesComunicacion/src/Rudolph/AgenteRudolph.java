
package Rudolph;

import jade.core.Agent;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 * @author Teresa Reyes García.
 */
public class AgenteRudolph extends Agent {
    private int contadorRenos;
    
    // -------------------------------------------------
    // Constructor
    public AgenteRudolph(){
        Random random = new Random();
        contadorRenos = random.nextInt(7) + 4; // Número random de renos entre 4 y 10.
    }
    
    // -------------------------------------------------
    // Inicialización del agente Rudolph.
    @Override
    public void setup(){
        
    } 
    
    // ----------------------------------------------------------------------------------------------------------
    // Sobreescribimos el método takeDown() que es llamado por doDelete() para mostrar un mensaje de despedida.
    @Override
    public void takeDown(){
        System.out.println("\nTerminando el agente Rudolph...");
    }
    
    // ----------------------------------------------------------------------------------------------------------
    // Generamos de manera aleatoria las coordenadas de los renos que tiene que buscar el Agente Buscador.
    private SimpleEntry<Integer, Integer> obtenerCoordenadasReno() {
        Random random = new Random();
        
        // CAMBIAR ESTO PARA QUE EL NÚMERO DE CELDAS SE OBTENGA DEL MAPA (NUM DE FILAS, NUM COLUMNAS)
        int x = random.nextInt(40);  // Coordenada x entre 0 y 39
        int y = random.nextInt(40);  // Coordenada y entre 0 y 39
        return new SimpleEntry<>(x, y);
    }
}
