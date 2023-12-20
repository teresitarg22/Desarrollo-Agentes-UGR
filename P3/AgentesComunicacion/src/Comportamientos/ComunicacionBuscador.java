
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
        //System.out.print("\n\n\n\n"+myAgent.getContainerController().getAgent()+"\n\n\n\n");
        if (this.entorno.enObjetivo()) {
            switch(this.step){
                // -------------------------------------------------------
                // Propuesta a Santa sobre si somos aptos para la misión.
                case 0:
                    ACLMessage propuesta = new ACLMessage(ACLMessage.PROPOSE);
                    propuesta.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                    propuesta.setContent("¿Somos aptos?");
                    myAgent.send(propuesta);
                    this.entorno.setAccion("Elfo -> Santa   ¿Somos aptos?");
                    this.step = 1;
                break;

                // -------------------------------------------------------
                // Esperar la respuesta de Santa.
                case 1:
                    ACLMessage respuesta = myAgent.blockingReceive();

                    if (respuesta.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                        // Santa acepta la misión, recibimos un código:
                        this.codigo = respuesta.getContent();
                        
                        this.entorno.setPosicionObjetivo(this.entorno.getPosicionRudolph());
                        
                        this.entorno.setAccion("Elfo <- Santa   Código recibido ("+this.codigo+").");
                        this.step = 2;

                    } else {
                        // Santa rechaza la misión.
                        this.entorno.setAccion("Elfo <- Santa   Santa ha rechazado la misión.");
                        this.finish = true;
                        myAgent.doDelete();
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
                    
                    this.entorno.setAccion("Elfo -> Rudolph   Este es el código: "+this.codigo+".");
                    this.step = 3;
                break;

                // -------------------------------------------------------
                // Recibimos la respuesta con las coordenadas
                case 3: 
                    ACLMessage respuestaCodigo = myAgent.blockingReceive();

                    if (respuestaCodigo.getPerformative() == ACLMessage.AGREE) {
                        // Santa acepta la misión, recibimos un código:
                        //String coord = respuestaCodigo.getContent();
                        //SimpleEntry<Integer, Integer> coordenadas = convertirCoordenadas(coord);

                        this.entorno.setPosicionObjetivo(convertirCoordenadas(respuestaCodigo.getContent()));

                        this.entorno.setAccion("Elfo <- Rudolph   Coordenadas recibidas ("+convertirCoordenadas(respuestaCodigo.getContent())+").");
                        this.step = 4;
                    } else {
                        String motivo = respuestaCodigo.getContent();

                        if(motivo.equals("No hay más renos")) {
                            this.entorno.setAccion("Elfo <- Rudolph   No hay más renos.");
                            this.step = 5;
                        } else {
                            this.entorno.setAccion("Elfo <- Rudolph   El código no es correcto.");
                            this.finish = true;
                            myAgent.doDelete();                            
                        }
                    }
                break; 

                // -------------------------------------------------------
                // Si las coordenadas objetivo no son las de Rudolph, las ponemos, y si llega a ella informamos a Rudolph que hemos terminado con ese reno.
                case 4:
                    if (this.entorno.getPosicionRudolph() != this.entorno.getPosicionObjetivo())
                        this.entorno.setPosicionObjetivo(this.entorno.getPosicionRudolph());
                    else {
                        ACLMessage terminado = new ACLMessage(ACLMessage.INFORM);
                        terminado.addReceiver(new AID("AgenteRudolph", AID.ISLOCALNAME));
                        terminado.setContent("He terminado, ¿dónde está el siguiente reno?");
                        myAgent.send(terminado);

                        ACLMessage informe = myAgent.blockingReceive();

                        // Recibimos la respuesta informativa de Rudolph de buen trabajo.convertirCoordenadas
                        if (informe.getPerformative() == ACLMessage.INFORM){
                            this.entorno.setAccion("Elfo -> Rudolph   Reno encontrado. ¿Cuál es el siguiente reno?");
                            this.step = 2;
                        }
                    } 
                break;

                // -------------------------------------------------------
                // No hay más renos, le pedimos a Santa sus coordenadas
                case 5:
                    ACLMessage misionTerminada = new ACLMessage(ACLMessage.REQUEST);
                    misionTerminada.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                    misionTerminada.setContent("¡He terminado la misión! ¿Dónde estás?");

                    this.entorno.setAccion("Elfo -> Santa   Misión terminada, ¿cuáles son tus coordenadas?");
                    this.step = 6;
                break;

                // -------------------------------------------------------
                // Recibimos las coordenadas de Santa y vamos hasta él.
                case 6:
                    ACLMessage posSanta = myAgent.blockingReceive();

                    if (posSanta.getPerformative() == ACLMessage.AGREE) {
                        // Obtenemos las coordenadas de Santa para ir hasta él.
                        //String coord = posSanta.getContent();
                        //SimpleEntry<Integer, Integer> coordenadas = convertirCoordenadas(coord);

                        this.entorno.setPosicionObjetivo(convertirCoordenadas(posSanta.getContent()));

                        ACLMessage posSantaCompleta = new ACLMessage(ACLMessage.INFORM);
                        posSantaCompleta.addReceiver(new AID("AgenteSantaClaus", AID.ISLOCALNAME));
                        posSantaCompleta.setContent("¡Ya estoy aquí!");
                        myAgent.send(posSantaCompleta);

                        this.entorno.setAccion("Elfo <- Santa   Coordenadas de Santa recibidas ("+convertirCoordenadas(posSanta.getContent())+").");
                        this.step = 7;
                    }  
                break;

                // -------------------------------------------------------
                // Recibimos el Hohoho de Santa y termina la misión.
                case 7:
                    ACLMessage misionCompleta = myAgent.blockingReceive();

                    if (misionCompleta.getPerformative() == ACLMessage.INFORM) {
                        this.entorno.setAccion("Elfo <- Santa   Santa nos dice HO HO HO.");
                        this.finish = true;
                        myAgent.doDelete();
                    }
                break;
            }
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
