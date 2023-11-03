/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentemundo2d;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author marta
 */
public class Mapa {
    
    private int filas;
    private int columnas;
    private int[][] mapa;

    public Mapa(String archivo) {
        try {
            Scanner scanner = new Scanner(new File(archivo));

            filas = scanner.nextInt();
            columnas = scanner.nextInt();
            mapa = new int[filas][columnas];

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    mapa[i][j] = scanner.nextInt();
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
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
