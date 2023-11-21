package Agente;

import java.util.AbstractMap.SimpleEntry;

/**
 * @author Diego Velázquez Ortuño
 */
public interface EntornoListener {
    void onPosicionAgenteActualizada(SimpleEntry<Integer,Integer> pos);
}
