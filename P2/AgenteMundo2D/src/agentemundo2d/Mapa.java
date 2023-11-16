
package agentemundo2d;

import java.util.Scanner;
import java.io.InputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marta Rincón Otero
 */
public class Mapa {
    
    private int filas;
    private int columnas;
    private Map < SimpleEntry <Integer, Integer >, Integer> mapa;

    public Mapa(String archivo) {
        // Combino la ruta con el nombre del archivo pasado como argumento
        // Partimos del .jar (AgenteMundo2D.jar) y con /Mapas/ entro en el paquete de mapas
        String rutaCompleta = "/Mapas/" + archivo; 
        try {
            // Abre el archivo con la ruta completa
            InputStream inputStream = getClass().getResourceAsStream(rutaCompleta);
        
            if (inputStream != null) {
                Scanner scanner = new Scanner(inputStream);
                filas = scanner.nextInt();
                columnas = scanner.nextInt();
                mapa = new HashMap<>();

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        mapa.put(new SimpleEntry<>(i, j), scanner.nextInt());
                    }
                }

                scanner.close();
                
            } else {
                System.err.println("No se pudo encontrar el archivo dentro del paquete Mapas.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void imprimirMapa() {
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print( mapa.get(new SimpleEntry<>(i, j)) + " "); // uso new  para asegurar que creo una instancia única para representar las coordenadas específicas
            }
            System.out.println();
        }
    }
    
    public int obtenerCelda ( int fila, int columna) {
        SimpleEntry<Integer, Integer> coordenadas = new SimpleEntry<>(fila, columna); 
        if (mapa.containsKey(coordenadas)) { //Compruebo que el mapa tiene esas coordenadas (no esta fuera de los limites)
            return mapa.get(coordenadas);
        } else {
            System.out.println("ERROR: Coordenadas fuera de los límites del mapa.");
            return -1; // Valor de celda no válida
        }
    }
    
    public boolean esAccesible ( int fila, int columna) {
       SimpleEntry<Integer, Integer> coordenadas = new SimpleEntry<>(fila, columna);
        if (mapa.containsKey(coordenadas)) {
            return mapa.get(coordenadas) == 0; // Si es 0 (camino) es true , si es -1 (muro) devolverá false
        } else {
            System.out.println("ERROR: Coordenadas fuera de los límites del mapa.");
            return false;
        }
    }

}
