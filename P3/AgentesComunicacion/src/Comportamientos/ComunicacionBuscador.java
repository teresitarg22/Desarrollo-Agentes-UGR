
package Comportamientos;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.AbstractMap.SimpleEntry;

/**
 * @author teresa
 */
public class ComunicacionBuscador extends Behaviour{
    private int step;
    private boolean finish;
    private String codigo;
    
    // -----------------------------------------------------------------------------------
    public ComunicacionBuscador(){
        this.step = 0;
        this.finish = false;
        this.codigo = null; 
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public void action(){
        
        switch(this.step){
            // -------------------------------------------------------
            // Propuesta a Santa sobre si somos aptos para la misión.
            case 0:
                ACLMessage propuesta = new ACLMessage(ACLMessage.PROPOSE);
                propuesta.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                propuesta.setContent("¿Somos aptos?");
                myAgent.send(propuesta);
                this.step = 1;
            break;
            
            // -------------------------------------------------------
            // Esperar la respuesta de Santa.
            case 1:
                ACLMessage respuesta = myAgent.blockingReceive();
                
                if (respuesta.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                    // Santa acepta la misión, recibimos un código:
                    this.codigo = respuesta.getContent();
                    this.step = 2;

                } else {
                    // Santa rechaza la misión.
                    myAgent.doDelete();
                }
                
            break;
            
            // -------------------------------------------------------
            // Le enviamos el código a Rudolph
            case 2:
                ACLMessage codigoRudolph = new ACLMessage(ACLMessage.REQUEST);
                codigoRudolph.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                codigoRudolph.setConversationId(this.codigo);
                myAgent.send(codigoRudolph);
                
                this.step = 3;
            break;
            
            // -------------------------------------------------------
            // Recibimos la respuesta con las coordenadas
            case 3: 
                ACLMessage respuestaCodigo = myAgent.blockingReceive();
                
                if (respuestaCodigo.getPerformative() == ACLMessage.AGREE) {
                    // Santa acepta la misión, recibimos un código:
                    String coord = respuestaCodigo.getContent();
                    SimpleEntry<Integer, Integer> coordenadas = convertirCoordenadas(coord);
                    
                    // HAY QUE LLAMAR AL COMPORTAMIENTO MOVERSE ETC
                    
                    this.step = 4;
                } else {
                    String motivo = respuestaCodigo.getContent();
                    
                    if(motivo.equals("No hay más renos"))
                        this.step = 5;
                    else
                        myAgent.doDelete(); // Q
                }
            break; 
            
            // -------------------------------------------------------
            // Informamos a Rudolph que hemos terminado con ese reno.
            case 4:
                // MOVERÁ HASTA RUDOLPH 
                
                ACLMessage terminado = new ACLMessage(ACLMessage.INFORM);
                terminado.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                terminado.setContent("He terminado, ¿dónde está el siguiente reno?");
                myAgent.send(terminado);
                
                this.step = 2;
            break;
            
            // -------------------------------------------------------
            // No hay más renos, se mueve a Santa
            case 5:
                
                // SE MUEVE HACIA SANTA E INFORMA.
                
                ACLMessage misionTerminada = new ACLMessage(ACLMessage.INFORM);
                misionTerminada.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                misionTerminada.setContent("¡He terminado la misión!");
                myAgent.doDelete();
            break;
        }
    }
    
    // -----------------------------------------------------------------------------------
    private SimpleEntry<Integer, Integer> convertirCoordenadas(String coord){
        String[] separacion = coord.split(",");
        
        if(separacion.length == 2)
            return new SimpleEntry<Integer, Integer>(Integer.parseInt(separacion[0]), Integer.parseInt(separacion[1]));
        else
            return null;
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public boolean done(){
        return this.finish;
    }
}
