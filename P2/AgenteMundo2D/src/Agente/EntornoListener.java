package Agente;

import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author diego
 */
public interface EntornoListener {
    void onPosicionAgenteActualizada(SimpleEntry<Integer,Integer> pos);
}
