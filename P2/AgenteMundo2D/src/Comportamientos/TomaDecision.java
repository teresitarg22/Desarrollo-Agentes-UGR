
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
    // Calcula la próxima acción basada en el entorno usando LRTA*.
    private PosiblesMovimientos calcularSiguienteMovimiento() {    
        SimpleEntry<Integer,Integer> pos = this.entorno.getPosicionAgente();
        // Inicializamos el siguiente movimiento que devolveremos a nulo
        PosiblesMovimientos siguienteMovimiento = null;
        // Usaremos una distancia mínima para comprara cuál es el mejor povimiento posible.
        // Inicializado al máximo valor para que en la primera comprobación nos dvuelva una distancia real,
        // que será mínima en comparación al máximo valor.
        int distMin = Integer.MAX_VALUE;
        // Inicilizamos el peso del segundo mejor al máximo valor.
        int segundoMejor = Integer.MAX_VALUE;
        
        // Recorre todos los movimientos posibles
        for (PosiblesMovimientos movimiento : PosiblesMovimientos.values()) {
            // Comprueba si ese siguiente movimiento posible es un muro o es accesible (0).
            if (this.entorno.getSensores()[movimiento.ordinal()] == 0) {
                // Comprueba si la posición del movimiento dentro de la lista es mayor o igual que 4, lo que indica que es una esquina.
                // Si se cambia el orden de inicialización dentro del enumerado esto debe cambiar.
                if (movimiento.ordinal()>=4 &&
                    // Para esa esquina comprueba si sus casillas adyacnte son mueros, por lo que tenemos un muro diagonal tapándola
                    // y no es accesible, por lo que se salta la iteración actual y no hace estudio de ese posible movimiento
                    this.entorno.getSensores()[PosiblesMovimientos.getMovimiento(0, movimiento.y()).ordinal()] !=0 &&
                    this.entorno.getSensores()[PosiblesMovimientos.getMovimiento(movimiento.x(), 0).ordinal()] !=0 ) {
                    
                    continue;
                }
                
                SimpleEntry<Integer,Integer> posObj = this.entorno.getPosicionObjetivo();
                
                // Si el peso en esa siguiente posición ya estaba almacenado en la tabla de pesos no hace nada, de lo contrario calcula su peso como
                // la distancia Manhattan entre esa nueva posición y el objetivo.
                this.entorno.getPesos().putIfAbsent(movimiento.sumar(pos), distanciaManhattanDiagonal(movimiento.sumar(pos),posObj));
                int dist = this.entorno.getPesos().get(movimiento.sumar(pos));
                
                boolean asignarMovimiento = false;
                
                if (dist < distMin)
                    asignarMovimiento = true;
                else if ( dist == distMin && (distanciaManhattan(movimiento.sumar(pos),posObj) <= distanciaManhattan(siguienteMovimiento.sumar(pos),posObj)))
                    asignarMovimiento = true;
                
                if (asignarMovimiento) {
                    siguienteMovimiento = movimiento;
                    // Para poder guardar el segundo peso para posteriormente asignarlo al nodo que se abandona, necesitamos comprobar primero si el anterior tiene valor
                    // máximo, por lo que estamos en la primera iteración en la que se encuntra un mínimo y el segundo tendrá que ser también el primero (si no se encuentra un
                    // siguiente mínimo).
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
        return Math.abs(puntoB.getKey()-puntoA.getKey()) + Math.abs(puntoB.getValue()-puntoA.getValue());
    }
    
    // ----------------------------------------------------------------------------------
    // Calculamos la distancia Manhattan entre dos puntos teniendo en cuenta las diagonales.
    private int distanciaManhattanDiagonal(SimpleEntry<Integer,Integer> puntoA, SimpleEntry<Integer,Integer> puntoB){
                
        if (Math.abs(puntoB.getKey() - puntoA.getKey()) > Math.abs(puntoB.getValue() - puntoA.getValue()))
            return Math.abs(puntoB.getKey() - puntoA.getKey());
        else
            return Math.abs(puntoB.getValue() - puntoA.getValue());
    }
    
    // ----------------------------------------------------------------------------------
    // El comportamiento termina cuando se alcanza el objetivo.
    @Override
    public boolean done() {
        return false;
    }
}
