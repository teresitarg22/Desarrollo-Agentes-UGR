/** 
 * ejecución EN MI CASO dentro de ~/.../JADE-all-4.6.0/jade/proyectos/Desarrollo-Agentes/P2/AgenteMundo2D$
 * comando: java -cp dist/AgenteMundo2D.jar:dist/lib/jade.jar agentemundo2d.main mapWithVerticalWall.txt
 * formato: java -cp (ruta del jar del proyecto: ruta de jade) (paquete donde se encuentra el archivo main).main (nombre del mapa a usar)
 * Para indicar el mapa poner directamente el nombre del txt a probar
 */

package Agente;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

/**
 * @author Marta Rincón Otero
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       if (args.length != 1) {
            System.out.println("Uso: java -cp dist/AgenteMundo2D.jar:dist/lib/jade.jar agentemundo2d.main mapa.txt");
            return;
        }
        
       // Si no ha habido errores creo el mapa.
        String archivo = args[0];
        Mapa mapa = new Mapa(archivo);
        Entorno entorno = new Entorno(mapa);
       
        // ------------------------------------------------------------------
        // Un vez incializado el mapa, iniciamos plataforma JADE.
        // Creamos contenedor.
        Runtime rt = Runtime.instance();
        
        Profile p = new ProfileImpl();
        AgentContainer container = rt.createMainContainer(p);
        
        // Crear y lanzar el agente. Uso de try catch para mostrar errores en caso de fallo.
        try {
           AgentController ac = container.createNewAgent("agentemundo2d","agentemundo2d.AgenteMundo2D", new Object[]{entorno} );
            ac.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        mapa.imprimirMapa();
    }
    
}
