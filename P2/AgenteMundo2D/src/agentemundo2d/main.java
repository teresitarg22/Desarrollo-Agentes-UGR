/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agentemundo2d;



/**
 *
 * @author marta
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       if (args.length != 1) {
            System.out.println("Uso: java Main archivo.txt");
            return;
        }

        String archivo = args[0];

        Mapa mapa = new Mapa(archivo);
      
    }
    
}
