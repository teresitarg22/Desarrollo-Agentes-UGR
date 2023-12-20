
package Elementos;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;

/** 
 * EJECUCIÓN DEL PROGRAMA:
 * 
 * comando: java -cp dist/AgentesComunicacion.jar:dist/lib/jade.jar Elementos.main map.txt
 * formato: java -cp (ruta del jar del proyecto: ruta de jade) (paquete donde se encuentra el archivo main).main (nombre del mapa a usar)
 * Para indicar el mapa poner directamente el nombre del txt a probar.
 */
/**
 * @author Teresa Reyes García
 */
public class main {
        
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java -cp dist/AgentesComunicacion.jar:dist/lib/jade.jar Elementos.main map.txt ");
            return;
        }

        // ------------------------------------------------------
        // Si no ha habido errores creamos el mapa y el entorno.
        String archivo = args[0];
        
        Mapa mapa = new Mapa(archivo);
        Entorno entorno = new Entorno(mapa);

        MapaGUI mapaGUI = new MapaGUI(mapa,entorno);
        mapaGUI.setVisible(true);
        
        // Creamos el contenedor de los agentes.
        jade.core.Runtime rt = jade.core.Runtime.instance();

        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);
        
        // -------------------------------------------------------------------------
        // Hacemos una llamada a la interfaz MainListener. 
        mapaGUI.setMainListener(() -> {
            mainRudolph.main(entorno,container);
            mainSantaClaus.main(entorno,container);
            mainBuscador.main(entorno,container);
        });
    }
}


