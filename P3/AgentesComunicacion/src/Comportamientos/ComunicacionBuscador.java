
package Comportamientos;

import Elementos.Entorno;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.AbstractMap.SimpleEntry;

/**
 * @author Teresa Reyes García
 */
public class ComunicacionBuscador extends Behaviour{
    private int step;
    private boolean finish;
    private String codigo;
    private final Entorno entorno;
    
    // -----------------------------------------------------------------------------------
    public ComunicacionBuscador(Entorno entorno){
        this.step = 0;
        this.finish = false;
        this.codigo = null; 
        
        this.entorno = entorno;
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
                    this.finish = true;
                }
                
            break;
            
            // -------------------------------------------------------
            // Le enviamos el código a Rudolph
            case 2:
                ACLMessage codigoRudolph = new ACLMessage(ACLMessage.REQUEST);
                codigoRudolph.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                codigoRudolph.setContent("¿Cuáles son las coordenadas del reno que tengo que buscar?");
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
                    
                    // HAY QUE LLAMAR AL COMPORTAMIENTO MOVERSE !!!!
                    
                    this.step = 4;
                } else {
                    String motivo = respuestaCodigo.getContent();
                    
                    if(motivo.equals("No hay más renos"))
                        this.step = 5;
                    else{
                        myAgent.doDelete();
                        this.finish = true;
                    }
                }
            break; 
            
            // -------------------------------------------------------
            // Informamos a Rudolph que hemos terminado con ese reno.
            case 4:
                // EL BUSCADOR SE MOVERÁ HASTA RUDOLPH 
                
                ACLMessage terminado = new ACLMessage(ACLMessage.INFORM);
                terminado.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                terminado.setContent("He terminado, ¿dónde está el siguiente reno?");
                myAgent.send(terminado);
                
                ACLMessage informe = myAgent.blockingReceive();
                
                // Recibimos la respuesta informativa de Rudolph de buen trabajo.
                if (informe.getPerformative() == ACLMessage.INFORM){
                    this.step = 2;
                }
            break;
            
            // -------------------------------------------------------
            // No hay más renos, le pedimos a Santa sus coordenadas
            case 5:
                ACLMessage misionTerminada = new ACLMessage(ACLMessage.REQUEST);
                misionTerminada.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                misionTerminada.setContent("¡He terminado la misión! ¿Dónde estás?");
                
                this.step = 6;
            break;
            
            // -------------------------------------------------------
            // Recibimos las coordenadas de Santa y vamos hasta él.
            case 6:
                ACLMessage posSanta = myAgent.blockingReceive();
                
                if (posSanta.getPerformative() == ACLMessage.AGREE) {
                    // Obtenemos las coordenadas de Santa para ir hasta él.
                    String coord = posSanta.getContent();
                    SimpleEntry<Integer, Integer> coordenadas = convertirCoordenadas(coord);
                    
                    // HAY QUE LLAMAR AL COMPORTAMIENTO MOVERSE PARA IR HASTA SANTA
                    
                    ACLMessage posSantaCompleta = new ACLMessage(ACLMessage.INFORM);
                    posSantaCompleta.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                    posSantaCompleta.setContent("¡Ya estoy aquí!");
                    myAgent.send(posSantaCompleta);
                    
                    this.step = 7;
                }  
            break;
            
            // -------------------------------------------------------
            // Recibimos el Hohoho de Santa y termina la misión.
            case 7:
                ACLMessage misionCompleta = myAgent.blockingReceive();
                
                if (misionCompleta.getPerformative() == ACLMessage.INFORM) {
                    myAgent.doDelete();
                    this.finish = true;
                }
            break;
        }
    }
    
    // -----------------------------------------------------------------------------------
    private SimpleEntry<Integer, Integer> convertirCoordenadas(String coord){
        String[] separacion = coord.split(",");
        
        if(separacion.length == 2)
            return new SimpleEntry<>(Integer.parseInt(separacion[0]), Integer.parseInt(separacion[1]));
        else
            return null;
    }
    
    // -----------------------------------------------------------------------------------
    @Override
    public boolean done(){
        return this.finish;
    }
}
