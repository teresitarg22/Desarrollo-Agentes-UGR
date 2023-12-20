package Elementos;

import java.util.AbstractMap.SimpleEntry;

/**
 * @author Diego Velázquez Ortuño
 */
public interface EntornoListener {
    void onPosicionAgenteActualizada(PosiblesMovimientos movimiento);
    void onVisualizarAccion(String accion);
    void onPosicionRenoActualizada(SimpleEntry<Integer, Integer> coord);
    void onUltimoReno();
    void onRenoEncontrado();
    void onSantaEncontrado(SimpleEntry<Integer,Integer> coord);
}
