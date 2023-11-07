
package Agente;

import java.util.Scanner;
import java.io.InputStream;

/**
 *
 * @author Marta Rincón Otero
 */
public class Mapa {
    
    private int filas;
    private int columnas;
    private int[][] mapa;

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
                mapa = new int[filas][columnas];

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        mapa[i][j] = scanner.nextInt();
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
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int obtenerCelda ( int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            // Si los valores dados no superan ni son menores que el tamaño de la matriz
            return mapa[fila][columna];
        } else {
            System.out.println("ERROR: Coordenadas fuera de los límites del mapa.");
            return -1; // Valor de celda no válida
        }
    }
    
    public boolean esAccesible ( int fila, int columna) {
       if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
           if (mapa[fila][columna] == 0)
                return true;
           else
               return false;
        } else {
            System.out.println("ERROR: Coordenadas fuera de los límites del mapa.");
            return false;
        } 
    }

}
