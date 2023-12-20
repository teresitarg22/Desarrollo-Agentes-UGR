
package Elementos;

import java.util.AbstractMap.SimpleEntry;

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
        
        // -------------------------------------------------------------------------
        // Hacemos una llamada a la interfaz MainListener. 
        mapaGUI.setMainListener(() -> {              
            mainBuscador.main(entorno);
            mainSantaClaus.main(entorno);
            mainRudolph.main(entorno);
           
           //entorno.actualizarReno(new SimpleEntry<>(15,15));
        });
    }
}
