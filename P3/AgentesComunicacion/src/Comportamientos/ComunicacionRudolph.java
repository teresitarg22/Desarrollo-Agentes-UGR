
package Comportamientos;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 *
 * @author teresa
 */
public class ComunicacionRudolph extends Behaviour{
    private int step;
    private boolean finish;
    private final String codigoSecreto;
    private int contadorRenos;
    
    public ComunicacionRudolph(){
        this.step = 0;
        this.finish = false;
        this.codigoSecreto = "NAVIDAD";
        
        Random random = new Random();
        this.contadorRenos = random.nextInt(7) + 4; // Número random de renos entre 4 y 10.
    }
    
    @Override
    public void action(){
        
        switch(this.step){
            case 0:
                ACLMessage informeSanta = myAgent.blockingReceive();
                
                if(informeSanta.getPerformative() == ACLMessage.INFORM){
                    if(informeSanta.getContent().equals("Nos vamos."))
                        myAgent.doDelete();
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
                        SimpleEntry<Integer, Integer> coordenadasReno = obtenerCoordenadasReno();
                    
                        // Convierte las coordenadas a una cadena para enviarlas.
                        String coord = coordenadasReno.getKey() + "," + coordenadasReno.getValue();
                        respuesta.setContent(coord);

                        myAgent.send(respuesta);
                        this.contadorRenos--;
                    }
                }
               else{
                    ACLMessage respuesta = codigoBuscador.createReply();
                    respuesta.setPerformative(ACLMessage.CANCEL);
                    
                    if(this.contadorRenos == 0){
                        respuesta.setContent("No hay más renos.");
                    }
                    else{
                        respuesta.setContent("Código incorrecto.");
                    }
                    
                    myAgent.send(respuesta);
               }

                this.step = 2;
            break;
            
            // -------------------------------------------------------
            // Recibe el informe del Buscador.
            case 2:
               ACLMessage informeBuscador = myAgent.blockingReceive();
               
                if (informeBuscador.getPerformative() == ACLMessage.INFORM){
                   ACLMessage respuestaInforme = informeBuscador.createReply();
                   respuestaInforme.setContent("¡Buen trabajo!");
                } 
               
               this.step = 1;
            break;
        }
    }
    
    // ----------------------------------------------------------------------------------------------------------
    // Generamos de manera aleatoria las coordenadas de los renos que tiene que buscar el Agente Buscador.
    private AbstractMap.SimpleEntry<Integer, Integer> obtenerCoordenadasReno() {
        Random random = new Random();
        
        // AÑADIR QUE NO ESTÉN EN LA POSICIÓN DE SANTA O DE RUDOLPH
        // CAMBIAR ESTO PARA QUE EL NÚMERO DE CELDAS SE OBTENGA DEL MAPA (NUM DE FILAS, NUM COLUMNAS)
        
        int x = random.nextInt(40);  // Coordenada x entre 0 y 39
        int y = random.nextInt(40);  // Coordenada y entre 0 y 39
        return new AbstractMap.SimpleEntry<>(x, y);
    }
    
    @Override
    public boolean done(){
        return this.finish;
    }
}
