
package Comportamientos;

// Realizamos los imports que necesitamos.
import Agente.AgenteMundo2D;
import jade.core.behaviours.SimpleBehaviour;
import java.util.AbstractMap.SimpleEntry;
import Agente.Entorno;

// Le pasamos una instancia de Entorno

/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class TomaDecision extends SimpleBehaviour{
    private SimpleEntry<Integer,Integer> objetivo = this.entorno.getPosicionObjetivo();
    SimpleEntry<Integer,Integer>  posicionActual = this.entorno.getPosicionAgente();
    
    Integer distanciaMinima = distanciaManhattan(posicionActual, objetivo);
    private final SimpleEntry<Integer, Integer>[] posiblesMovimientos;
    
    private boolean objetivoAlcanzado = false;      // Inicializamos el objetivo a false.
    private Entorno entorno;
    
    public TomaDecision(Entorno entorno){
        this.posiblesMovimientos = new SimpleEntry[]{
                new SimpleEntry<>(-1, 0), // Arriba.
                new SimpleEntry<>(1, 0),  // Abajo.
                new SimpleEntry<>(0, -1), // Izquierda.
                new SimpleEntry<>(0, 1)   // Derecha.
        };
        
        this.entorno = entorno;
    }
    
    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción y le pasa las coordenadas al siguiente comportamiento.
    @Override
    public void action() {
        SimpleEntry<Integer,Integer> siguienteMovimiento = calcularSiguienteMovimiento();
        
        if (siguienteMovimiento != null) {
            // Pasa el siguiente movimiento calculado al siguiente comportamiento: Mover
            ((AgenteMundo2D) myAgent).setSiguienteMovimiento(siguienteMovimiento, this.distanciaMinima + 1);
        } else {
            // ¿Es posible que no se encuentre siguiente movimiento?
            System.out.println("No se ha encontrado siguiente movimiento.");
        }

        // Verifica si el objetivo se ha alcanzado para comunicarse con el resto de comportamientos.
        if (verificarObjetivoAlcanzado()) {
            objetivoAlcanzado = true;
            System.out.println("¡Se ha alcanzado el objetivo!");
            ((AgenteMundo2D) myAgent).doDelete();
        }
    }

    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción basada en el entorno.
    private SimpleEntry<Integer,Integer> calcularSiguienteMovimiento() {
        SimpleEntry<Integer,Integer>  siguienteMovimiento = null;
        posicionActual = this.entorno.getPosicionAgente();
        
        // Actualizamos la distancia mínima calculando la distancia Manhattan entre la pos del agente y el objetivo.
        distanciaMinima = distanciaManhattan(posicionActual, objetivo); 
        
        // Para cada uno de los posibles movimientos...
        for (SimpleEntry<Integer,Integer> movimiento : posiblesMovimientos) {
            Integer filaSiguiente = posicionActual.getKey() + movimiento.getKey(); 
            Integer columnaSiguiente = posicionActual.getValue() + movimiento.getValue(); 
            
            // Calculamos la distancia al objetivo desde cada una de las celdas adyacentes.
            Integer distanciaAlObjetivo = distanciaManhattan (new SimpleEntry<Integer,Integer>(filaSiguiente,columnaSiguiente), objetivo); 
            
            // Comparamos las distancias con la distanciaMinima. Si la distancia al objetivo es menor, actualizamos la distancia mínima.
            if (distanciaAlObjetivo < distanciaMinima) {
                 distanciaMinima = distanciaAlObjetivo ; 
                 siguienteMovimiento = new SimpleEntry<Integer,Integer>(filaSiguiente,columnaSiguiente); 
            }
        }
        
        /*
            FUNCIONAMIENTO PLANTEADO:
        
            1. Obtenemos la pos actual del agente.
            2. Definimos nuestras posibles acciones: arriba, abajo, izq, der, diagonales 
            3. Definimos cuál es la distancia al obj desde la pos inicial == distanciaMinima
            4. Vemos el valor de las celdas adyacentes, empezando por la de enfrente.
            5. ¿Sabemos cuál es el objetivo? calculamos la distancia al objetivo desde cada una de las celdas adyacentes.
            6. Comparamos las distancias con la distancia_min. Si la distancia es menor, actualizamos la distancia mínima.
            7. Almacenamos las coordenadas de la celda con distancia menor como siguienteMovimiento.
             
        */
        
        return siguienteMovimiento;
    }
    
    // ----------------------------------------------------------------------------------
    // Calculamos la distancia Manhattan entre dos puntos.
    private int distanciaManhattan(SimpleEntry<Integer,Integer> puntoA, SimpleEntry<Integer,Integer> puntoB){
        return Math.abs(puntoA.getKey() - puntoB.getKey()) + Math.abs(puntoA.getValue() - puntoB.getValue());
    }
    
    // ----------------------------------------------------------------------------------
    // Verificamos si se ha llegado al objetivo.
    private boolean verificarObjetivoAlcanzado() {
        return (posicionActual == this.entorno.getPosicionObjetivo());
    }
    
    // ----------------------------------------------------------------------------------
    // El comportamiento termina cuando se alcanza el objetivo.
    @Override
    public boolean done() {
        System.out.println("Decisión");
        return objetivoAlcanzado; 
    }
}
