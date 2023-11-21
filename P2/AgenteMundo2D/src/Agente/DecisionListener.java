
package Agente;

import java.util.ArrayList; 

/**
 * @author Teresa Reyes García
 */
public interface DecisionListener {
    void onDecisionTomada(ArrayList<PosiblesMovimientos> decisionesTomadas);
}
