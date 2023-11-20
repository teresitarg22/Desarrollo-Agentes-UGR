
package Comportamientos;

// Realizamos los imports que necesitamos.
import Agente.AgenteMundo2D;
import jade.core.behaviours.SimpleBehaviour;
import java.util.AbstractMap.SimpleEntry;
import Agente.Entorno;
import Agente.PosiblesMovimientos;

// Le pasamos una instancia de Entorno

/**
 * @author Sergio Muñoz Gómez y Teresa Fernanda Reyes García
 */
public class TomaDecision extends SimpleBehaviour{
    private boolean objetivoAlcanzado = false;      // Inicializamos el objetivo a false.
    private int distanciaMinima;
    private Entorno entorno;
    
    public TomaDecision(Entorno entorno){
        this.entorno = entorno;
    }
    
    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción y le pasa las coordenadas al siguiente comportamiento.
    @Override
    public void action() {
        //SimpleEntry<Integer,Integer> siguienteMovimiento = calcularSiguienteMovimiento();
        PosiblesMovimientos siguienteMovimiento = calcularSiguienteMovimiento();
        
        if (siguienteMovimiento != null) {
            // Pasa el siguiente movimiento calculado al siguiente comportamiento: Mover
            //((AgenteMundo2D) myAgent).setSiguienteMovimiento(siguienteMovimiento, this.distanciaMinima + 1);
            this.entorno.setSiguienteMovimiento(siguienteMovimiento);
        } else {
            // ¿Es posible que no se encuentre siguiente movimiento?
            System.out.println("No se ha encontrado siguiente movimiento.");
        }

        // Verifica si el objetivo se ha alcanzado para comunicarse con el resto de comportamientos.
        /*if (this.entorno.getPosicionAgente() == this.entorno.getPosicionObjetivo()) {
            objetivoAlcanzado = true;
            System.out.println("¡Se ha alcanzado el objetivo!");
            ((AgenteMundo2D) myAgent).doDelete();
        }*/
    }

    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción basada en el entorno.
    //private SimpleEntry<Integer,Integer> calcularSiguienteMovimiento() {
    private PosiblesMovimientos calcularSiguienteMovimiento() {
        /*SimpleEntry<Integer,Integer> posicionActual = this.entorno.getPosicionAgente();
        PosiblesMovimientos siguienteMovimiento = null;
        
        // Actualizamos la distancia mínima calculando la distancia Manhattan entre la pos del agente y el objetivo.
        distanciaMinima = distanciaManhattan(posicionActual, this.entorno.getPosicionObjetivo()); 
        
        // Para cada uno de los posibles movimientos...
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            if (this.entorno.getSensores()[movimiento.ordinal()] == 0) {
                // Calculamos la distancia al objetivo desde cada una de las celdas adyacentes.
                Integer distanciaAlObjetivo = distanciaManhattan (movimiento.sumar(posicionActual), this.entorno.getPosicionObjetivo()); 

                // Comparamos las distancias con la distanciaMinima. Si la distancia al objetivo es menor, actualizamos la distancia mínima.
                if (distanciaAlObjetivo < distanciaMinima) {
                     distanciaMinima = distanciaAlObjetivo ; 
                     siguienteMovimiento = movimiento;//.sumar(posicionActual);
                }
            }
        }*/
        
        SimpleEntry<Integer,Integer> pos = this.entorno.getPosicionAgente();
        PosiblesMovimientos siguienteMovimiento = null;
        int distMin = Integer.MAX_VALUE;
        int segundoMejor = Integer.MAX_VALUE;
        
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            if (this.entorno.getSensores()[movimiento.ordinal()] == 0) {
                this.entorno.getPesos().putIfAbsent(movimiento.sumar(pos), distanciaManhattan(movimiento.sumar(pos),this.entorno.getPosicionObjetivo()));
                //int dist = distanciaManhattan(movimiento.sumar(pos),this.entorno.getPosicionObjetivo());
                int dist = this.entorno.getPesos().get(movimiento.sumar(pos));
                
                if (dist < distMin) {
                    siguienteMovimiento = movimiento;
                    
                    if (distMin != Integer.MAX_VALUE)
                        segundoMejor = distMin;
                    else segundoMejor = dist;
                    
                    distMin = dist;
                }
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
        
        this.entorno.setSegundoMejor(segundoMejor);
        
        return siguienteMovimiento;
    }
    
    // ----------------------------------------------------------------------------------
    // Calculamos la distancia Manhattan entre dos puntos.
    private int distanciaManhattan(SimpleEntry<Integer,Integer> puntoA, SimpleEntry<Integer,Integer> puntoB){
        return Math.abs(puntoA.getKey() - puntoB.getKey()) + Math.abs(puntoA.getValue() - puntoB.getValue());
    }
    
    // ----------------------------------------------------------------------------------
    // El comportamiento termina cuando se alcanza el objetivo.
    @Override
    public boolean done() {
        //System.out.println("Decisión");
        //return objetivoAlcanzado;
        //la idea de esto como está planteada es que si devuelve true en el done es que el comportamiento no debe ejecutarse más, por lo que se elimina de la lista de comportamientos
        //pero los demás no, ya que sólo se elimina este, por lo uq elos demás continuán sus ejecuciones
        return false;
    }
}
