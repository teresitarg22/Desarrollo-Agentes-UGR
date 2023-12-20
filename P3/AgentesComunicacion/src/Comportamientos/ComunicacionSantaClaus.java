
package Comportamientos;

import Elementos.Entorno;
import SantaClaus.AgenteSantaClaus;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

/**
 * @author Teresa Reyes García
 */
public class ComunicacionSantaClaus extends Behaviour{
    private int step;
    private boolean finish;
    private final String codigoSecreto;
    private final Entorno entorno;
    
    // -----------------------------------------------------------------------------------
    public ComunicacionSantaClaus(Entorno entorno){
        this.step = 0;
        this.finish = false;
        this.codigoSecreto = "NAVIDAD";
        this.entorno = entorno;
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public void action(){
        
        switch(this.step){
            // -------------------------------------------------------
            // Recibe la propuesta del buscador
            case 0:
                ACLMessage propuesta = myAgent.blockingReceive();

                if (somosAptos()) {
                     ACLMessage mision = new ACLMessage(ACLMessage.INFORM);
                     mision.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                     mision.setContent("Todo correcto.");
                     myAgent.send(mision);

                     // --------------------------------------------
                     // Aceptar la propuesta y enviar un código
                     ACLMessage aceptacion = propuesta.createReply();
                     aceptacion.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                     aceptacion.setContent(this.codigoSecreto);
                     myAgent.send(aceptacion);

                     this.step = 1;
                 } 
                 else {
                     // Si no hay misión por completar, avisamos a Rudolph para que termine.
                     ACLMessage misionTerminada = new ACLMessage(ACLMessage.INFORM);
                     misionTerminada.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                     misionTerminada.setContent("Nos vamos.");
                     myAgent.send(misionTerminada);

                     // --------------------------------------------
                     // Rechazar la propuesta
                     ACLMessage rechazo = propuesta.createReply();
                     rechazo.setPerformative(ACLMessage.REJECT_PROPOSAL);
                     rechazo.setContent("Has sido malo.");
                     myAgent.send(rechazo);

                     myAgent.doDelete();
                     this.finish = true;
                 }
               
            break;
            
            // -------------------------------------------------------
            // Santa recibe el informe del buscador de que ha terminado.
            case 1:
               ACLMessage misionTerminada = myAgent.blockingReceive();
               if (misionTerminada.getPerformative() == ACLMessage.REQUEST){
                    ACLMessage pos = misionTerminada.createReply();
                    pos.setPerformative(ACLMessage.AGREE);
                    
                    pos.setContent(((AgenteSantaClaus)myAgent).getPosicion().getKey()+","+((AgenteSantaClaus)myAgent).getPosicion().getValue());
                    myAgent.send(pos);
                    
                    this.step = 2;
               }
            break;
            
            case 2:
                ACLMessage fin = myAgent.blockingReceive();
                
                if (fin.getPerformative() == ACLMessage.INFORM) {
                    ACLMessage despedida = new ACLMessage(ACLMessage.INFORM);
                    despedida.addReceiver(new AID("AgenteBuscador", AID.ISLOCALNAME));
                    despedida.setContent("HoHoHo!");
                    
                    this.entorno.limpiarCamino();
                    
                    myAgent.send(despedida);
                    
                    myAgent.doDelete();
                    this.finish = true;
                }
            break;
        }
    }
    
    // ----------------------------------------------------------------------------------------------------------
    // Función que calcula si el Buscador es apto o no para recibir la misión.
    private boolean somosAptos() {
        // Queremos simular que el 80% son confiables (buenos) y el 20% no son confiables (malos).
        Random random = new Random();
        return random.nextDouble() <= 0.8;
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public boolean done(){
        return this.finish;
    }
}
