
package Comportamientos;

import jade.core.behaviours.SimpleBehaviour;
import java.util.AbstractMap.SimpleEntry;
import Agente.Entorno;
import Agente.PosiblesMovimientos;

/**
 * @author Sergio Muñoz Gómez 
 * @author Teresa Fernanda Reyes García
 * @author Diego Velázquez Ortuño
 */
public class TomaDecision extends SimpleBehaviour{
    private final Entorno entorno;
    
    // ----------------------------------------------------------------------------------
    // Constructor.
    public TomaDecision(Entorno entorno){
        this.entorno = entorno;
    }
    
    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción y le pasa las coordenadas al siguiente comportamiento.
    @Override
    public void action() {
        PosiblesMovimientos siguienteMovimiento = calcularSiguienteMovimiento();
        
        if (siguienteMovimiento != null){
            // Establecemos el siguiente movimiento calculado.
            this.entorno.setSiguienteMovimiento(siguienteMovimiento);
        } 
        else{
            // ¿Es posible que no se encuentre siguiente movimiento?
            System.out.println("No se ha encontrado siguiente movimiento.");
        }
    }

    // ----------------------------------------------------------------------------------
    // Calcula la próxima acción basada en el entorno.
    private PosiblesMovimientos calcularSiguienteMovimiento() {     
        SimpleEntry<Integer,Integer> pos = this.entorno.getPosicionAgente();
        PosiblesMovimientos siguienteMovimiento = null;
        int distMin = Integer.MAX_VALUE;
        int segundoMejor = Integer.MAX_VALUE;
        
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            if (this.entorno.getSensores()[movimiento.ordinal()] == 0) {
                if (movimiento.ordinal()>=4 &&
                    this.entorno.getSensores()[PosiblesMovimientos.getMovimiento(0, movimiento.y()).ordinal()] !=0 &&
                    this.entorno.getSensores()[PosiblesMovimientos.getMovimiento(movimiento.x(), 0).ordinal()] !=0 ) {
                    
                    continue;
                }
                
                this.entorno.getPesos().putIfAbsent(movimiento.sumar(pos), distanciaManhattan(movimiento.sumar(pos),this.entorno.getPosicionObjetivo()));
                int dist = this.entorno.getPesos().get(movimiento.sumar(pos));
                
                // Comprobamos si la distancia es menor que la distancia mínima.
                if (dist < distMin) {
                    siguienteMovimiento = movimiento;
                    
                    // Comprobamos valores para la segunda mejor distancia mínima.
                    if (distMin != Integer.MAX_VALUE)
                        segundoMejor = distMin;
                    else segundoMejor = dist;

                    distMin = dist; // Actualizamos la distancia mínima.
                }
            }
        }
        
        // Establecemos el segundo mejor y devolvemos el siguiente movimiento calculado.
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
        return false;
    }
}
