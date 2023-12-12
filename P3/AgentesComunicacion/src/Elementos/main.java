
package Elementos;

/**
 * @author Teresa Reyes García
 */
public class main {
        
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(" ... ");
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
        });
    }
}
