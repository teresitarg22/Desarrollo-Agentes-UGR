
package Comportamientos;

import Elementos.Entorno;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 * @author Teresa Reyes García
 */
public class ComunicacionRudolph extends Behaviour{
    private int step;
    private boolean finish;
    private final String codigoSecreto;
    private int contadorRenos;
    private final Entorno entorno;
    
    // -----------------------------------------------------------------------------------
    public ComunicacionRudolph(Entorno entorno){
        this.step = 0;
        this.finish = false;
        this.codigoSecreto = "NAVIDAD";
        this.entorno = entorno;
        
        Random random = new Random();
        this.contadorRenos = random.nextInt(7) + 4; // Número random de renos entre 4 y 10.
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public void action(){
        
        switch(this.step){
            case 0:
                ACLMessage informeSanta = myAgent.blockingReceive();
                
                if(informeSanta.getPerformative() == ACLMessage.INFORM){
                    if(informeSanta.getContent().equals("Nos vamos.")){
                        myAgent.doDelete();
                        this.finish = true;
                    }
                    else
                        this.step = 1; // Todo correcto, comienza la misión.
                }
            break;
            
            // -------------------------------------------------------------
            // Recibe la propuesta del buscador y contesta con coordenadas.
            case 1:
               ACLMessage codigoBuscador = myAgent.blockingReceive();
               
               if (codigoBuscador.getConversationId() != null && codigoBuscador.getConversationId().equals(this.codigoSecreto)){
                    
                    if(this.contadorRenos > 0){
                        ACLMessage respuesta = codigoBuscador.createReply();
                        respuesta.setPerformative(ACLMessage.AGREE);
                        SimpleEntry<Integer, Integer> coordenadasReno = this.entorno.generarCoordenadas();
                    
                        // Convierte las coordenadas a una cadena para enviarlas.
                        String coord = coordenadasReno.getKey() + "," + coordenadasReno.getValue();
                        respuesta.setContent(coord);

                        myAgent.send(respuesta);
                        this.contadorRenos--;
                        
                        this.step = 2;
                    }
                    else{
                        // No hay más renos, informamos al buscador.
                        ACLMessage respuesta = codigoBuscador.createReply();
                        respuesta.setPerformative(ACLMessage.INFORM);
                        respuesta.setContent("No hay más renos.");
                        myAgent.send(respuesta);
                        
                        myAgent.doDelete();
                        this.finish = true;
                    }
                }
               else {
                    ACLMessage respuesta = codigoBuscador.createReply();
                    respuesta.setPerformative(ACLMessage.INFORM);
                    respuesta.setContent("Código incorrecto.");
                    

                    myAgent.send(respuesta);
               }

            break;
            
            // -------------------------------------------------------
            // Recibe el informe del Buscador.
            case 2:
               ACLMessage informeBuscador = myAgent.blockingReceive();
               
                if (informeBuscador.getPerformative() == ACLMessage.INFORM){
                   ACLMessage respuestaInforme = informeBuscador.createReply();
                   respuestaInforme.setContent("¡Buen trabajo!");
                   
                   this.step = 1;
                }
                              
            break;
        }
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public boolean done(){
        return this.finish;
    }
}
