
package Elementos;

import java.util.Scanner;
import java.io.InputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marta Rincón Otero
 */
public class Mapa {
    private int filas;
    private int columnas;
    private Map < SimpleEntry <Integer, Integer >, Integer> mapa;
    //private Map < SimpleEntry <Integer, Integer >, Integer> pesos;
    //private Map < SimpleEntry <Integer, Integer >, Integer> vecesPisada;

    // ---------------------------------------------
    // Constructor.
    public Mapa(String archivo){
        // Combinamos la ruta con el nombre del archivo pasado como argumento.
        // Partimos del .jar (AgenteMundo2D.jar) y con /Mapas/ entro en el paquete de mapas.
        String rutaCompleta = "/Mapa/" + archivo; 
        
        try {
            // Abre el archivo con la ruta completa.
            InputStream inputStream = getClass().getResourceAsStream(rutaCompleta);
        
            if (inputStream != null){
                Scanner scanner = new Scanner(inputStream);
                filas = scanner.nextInt();
                columnas = scanner.nextInt();
                mapa = new HashMap<>();
                //pesos = new HashMap<>();
                //vecesPisada = new HashMap<>();

                for (int i = 0; i < filas; i++){
                    for (int j = 0; j < columnas; j++){
                        mapa.put(new SimpleEntry<>(i, j), scanner.nextInt());
                        //vecesPisada.put(new SimpleEntry<>(i, j), 0);
                    }
                }

                scanner.close(); // Se cierra el scanner.
            } 
            // -------------------------
            else{
                System.err.println("No se pudo encontrar el archivo dentro del paquete Mapas.");
            }
        }
        // -------------------------
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ---------------------------------------------
    // Imprime el mapa.
    public void imprimirMapa() {
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                // Usamos new  para asegurar que creo una instancia única para representar las coordenadas específicas.
                System.out.print( mapa.get(new SimpleEntry<>(i, j)) + " "); 
            }
            
            System.out.println();
        }
    }
    
    // ---------------------------------------------
    // Obtener el valor de una celda dada.
    public int obtenerCelda (SimpleEntry<Integer, Integer> coordenadas ) { 
        if (mapa.containsKey(coordenadas)){ // Comprobamos que el mapa tiene esas coordenadas (no esta fuera de los limites).
            return mapa.get(coordenadas);
        } 
        else{
            return -1; // Valor de celda no válida.
        }
    }
    
    // ---------------------------------------------
    // Obtiene si una celda es accesible o no.
    public boolean esAccesible ( SimpleEntry<Integer, Integer> coordenadas) {
        if (mapa.containsKey(coordenadas)){
            return mapa.get(coordenadas) == 0; // Si es 0 (camino) es true , si es -1 (muro) devolverá false.
        } 
        else{
            System.out.println("ERROR: Coordenadas fuera de los límites del mapa.");
            return false;
        }
    }

    // --------------------------------- GETTERS ---------------------------------
    
    // ---------------------------------------------
    // Obtener el valor de las filas del mapa.
    public int getFilas() {
        return filas;
    }
    
    // ---------------------------------------------
    // Obtener el valor de las columnas del mapa.
    public int getColumnas() {
        return columnas;
    }

    // ---------------------------------------------
    // Obtenemos el mapa de pesos.
    /*public Map<SimpleEntry<Integer,Integer>,Integer> getPesos() {
        return this.pesos;
    }
    
    public Map<SimpleEntry<Integer,Integer>,Integer> getVecesPisada() {
        return this.vecesPisada;
    }*/
}
