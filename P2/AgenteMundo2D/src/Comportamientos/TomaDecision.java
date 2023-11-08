
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
            // myAgent.addBehaviour(new Mover(siguienteMovimiento, distancia_min));
        } else {
            // ¿E posible que no se encuentre siguiente moviimento?
            
        }

        // Verifica si el objetivo se ha alcanzado para comunicarse con el resto de comportamientos.
        objetivoAlcanzado = verificarObjetivoAlcanzado();
        
        if (objetivoAlcanzado) {
            
        }
    }

    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción basada en el entorno.
    private int[] calcularSiguienteMovimiento() {
        int[] siguienteMovimiento = null;
        int distancia_min = Integer.MAX_VALUE;
        
        // int posActual = 
        
        // Aquí tenemos que tener en cuenta los sensores.
        // Queremos devolver las coordenadas al siguiente movimiento, se las pasaremos al comportamiento Mover.
        
        /*
            · Distancia Manhattan
            · Matrix auxiliar -- RTA* o LRTA*
        
            1. Obtenemos la pos actual del agente.
            2. Definimos nuestras posibles acciones: arriba, abajo, izq, der, diagonales 
            3. Definimos cuál es la distancia al obj desde la pos inicial == distancia_min
            4. Vemos el valor de las celdas adyacentes, empezando por la de enfrente.
            5. ¿Sabemos cuál es el objetivo? calculamos la distancia al objetivo desde cada una de las celdas adyacentes.
            6. Comparamos las distancias con la distancia_min. Si la distancia es menor, actualizamos la distancia mínima.
            7. Almacenamos las coordenadas de la celda con distancia menor como siguienteMovimiento.
            
            · Actualizar la matriz aux cuando ya sabes que siguiente movimiento hacer.
            
        */
        
        return siguienteMovimiento;
    }
    
    // ----------------------------------------------------------------------------------
    // Verificamos si se ha llegado al objetivo.
    private boolean verificarObjetivoAlcanzado() {
        boolean alcanzado = false;

        // Si sabemos cuales son las coordenadas del objetivo, tendremos que compararlas
        // con la posición actual de agente y ver si son iguales.
        
        return alcanzado; 
    }
    
    // ----------------------------------------------------------------------------------
    // El comportamiento termina cuando se alcanza el objetivo.
    @Override
    public boolean done() {
        return objetivoAlcanzado; 
    }
}
