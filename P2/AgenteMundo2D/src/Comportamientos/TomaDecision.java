
package Comportamientos;

// Realizamos los imports que necesitamos.
import jade.core.behaviours.SimpleBehaviour;


/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class TomaDecision extends SimpleBehaviour{
    private boolean objetivoAlcanzado = false;      // Inicializamos el objetivo a false.

    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción y le pasa las coordenadas al siguiente comportamiento.
    @Override
    public void action() {
        int [] siguienteMovimiento = calcularSiguienteMovimiento();
        
        if (siguienteMovimiento != null) {
            // Pasa el siguiente movimiento calculado al siguiente comportamiento: Mover
            // myAgent.addBehaviour(new Mover(siguienteMovimiento));
        } else {
            // No se encontró un movimiento para continuar.
            //System.out.println("No se encontró un camino al objetivo.");
        }

        // Verifica si el objetivo se ha alcanzado para comunicarse con el resto de comportamientos.
        if (objetivoAlcanzado) {
            
        }
    }

    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción basada en el entorno.
    private int[] calcularSiguienteMovimiento() {
        int[] siguienteMovimiento = {}; 
        
        // Implementar quizá el algoritmo A* para calcular el siguiente movimiento.
        // Queremos devolver las coordenadas al siguiente movimiento, se las pasaremos al comportamiento Mover.
        
        return siguienteMovimiento;
    }
    
    // ----------------------------------------------------------------------------------
    // El comportamiento termina cuando se alcanza el objetivo.
    @Override
    public boolean done() {
        return objetivoAlcanzado; 
    }
}
