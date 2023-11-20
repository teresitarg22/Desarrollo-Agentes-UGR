package Agente;

import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author diego
 */
public enum PosiblesMovimientos {
    ARRIBA(-1, 0),
    ABAJO(1, 0),
    IZQUIERDA(0, -1),
    DERECHA(0, 1),
    ARRIBA_DERECHA(-1, 1),
    ARRIBA_IZQUIERDA(-1, -1),
    ABAJO_DERECHA(1, 1),
    ABAJO_IZQUIERDA(1, -1);
    

    private final int x;
    private final int y;

    // Mapa para mapear coordenadas a movimientos
    private static final Map<SimpleEntry<Integer, Integer>, PosiblesMovimientos> coordenadasAMovimiento = new HashMap<>();

    static {
        for (PosiblesMovimientos movimiento : values()) {
            coordenadasAMovimiento.put(new SimpleEntry<>(movimiento.x, movimiento.y), movimiento);
        }
    }

    PosiblesMovimientos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public SimpleEntry<Integer, Integer> sumar(SimpleEntry<Integer, Integer> pos) {
        return new SimpleEntry<>(this.x + pos.getKey(), this.y + pos.getValue());
    }

    // Método para buscar la posición de un enumerado dado en el formato (x, y)
    public static PosiblesMovimientos getMovimiento(int x, int y) {
        return coordenadasAMovimiento.get(new SimpleEntry<>(x, y));
    }
}
