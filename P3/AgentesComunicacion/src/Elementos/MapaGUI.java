
package Elementos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * @author Marta Rincón Otero
 */
public class MapaGUI extends javax.swing.JFrame {
    private final Mapa mapa;
    private final Entorno entorno;
    private final int TamCelda; // Tamaño del largo de la celda cuadrada
    private MainListener mainListener;
    private SimpleEntry<Integer,Integer> posBuscador;
    private SimpleEntry<Integer,Integer> posReno;
    private final Map < SimpleEntry <Integer, Integer >, JLabel> etiquetasMapa;
    
    private ImageIcon santaI, rudolphI, renoI, caminoI, elfoI;
   
    
    JPanel panelEtiquetas = new JPanel();
    
    // ------------------------------------------------------------------------------------
    // Constructor.
    public MapaGUI(Mapa mapa, Entorno entorno) {
        
        this.mapa = mapa;
        this.entorno = entorno;
        this.TamCelda = (int)((30*30)/this.mapa.getFilas());
        this.posBuscador = entorno.getPosicionBuscador();
        this.posReno = new SimpleEntry<>(-1, -1); // Por defecto el reno está en la celda -1,-1
        initComponents();
        
        this.santaI = scale("img/SantaClaus.png");
        this.rudolphI = scale("img/Rudolph.png");
        this.renoI = scale("img/Reno.png");
        this.caminoI = scale("img/Camino.png");
        this.elfoI = scale("img/Buscador.png");
               
        panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.Y_AXIS));
        etiquetasMapa = new HashMap<>();
        
        visualizarMapa();
        
        // Creamos un listener para el entorno.
        this.entorno.setEntornoListener(new EntornoListener(){
            @Override
            public void onPosicionAgenteActualizada(PosiblesMovimientos movimiento){
                SwingUtilities.invokeLater(() -> {
                    actualizarAgente(movimiento);
                });
            }
            @Override
            public void onVisualizarAccion(String accion){
                SwingUtilities.invokeLater(() -> {
                    visualizarAccion(accion);
                });
            }
            
            @Override
            public void onPosicionRenoActualizada(SimpleEntry<Integer, Integer> coord){
                 SwingUtilities.invokeLater(() -> {
                    actualizarReno(coord);
                });
            }
            
            @Override
            public void onUltimoReno(){
                 SwingUtilities.invokeLater(() -> {
                    etiquetasMapa.get(posReno).setIcon(null);
                });
            }
            
            @Override
            public void onRenoEncontrado(){
                SwingUtilities.invokeLater(() -> {
                    renoEncontrado();
                });
            }
            
            @Override
            public void onSantaEncontrado(SimpleEntry<Integer,Integer> coord){
                SwingUtilities.invokeLater(() -> {
                    santa(coord);
                });
            }
        });
    }

    // ------------------------------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabelCoordInicAgent = new javax.swing.JLabel();
        jLabelCoordXAgent = new javax.swing.JLabel();
        jLabelCoordYAgent = new javax.swing.JLabel();
        jTextFieldCoordXAgent = new javax.swing.JTextField();
        jTextFieldCoordYAgent = new javax.swing.JTextField();
        jLabelCoordInicObj = new javax.swing.JLabel();
        jLabelCoordXObj = new javax.swing.JLabel();
        jTextFieldCoordXObj = new javax.swing.JTextField();
        jLabelCoordYObj = new javax.swing.JLabel();
        jTextFieldCoordYObj = new javax.swing.JTextField();
        jButtonSet = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelMapa = new javax.swing.JPanel();
        jLabelDecision = new javax.swing.JLabel();
        jScrollPanelDecision = new javax.swing.JScrollPane();
        jButtonIniciar = new javax.swing.JButton();
        jLabelSC = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jLabelCoordInicAgent.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabelCoordInicAgent.setText("Coordenadas del elfo buscador:");

        jLabelCoordXAgent.setText("x:");

        jLabelCoordYAgent.setText("y:");

        jTextFieldCoordXAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCoordXAgentActionPerformed(evt);
            }
        });

        jLabelCoordInicObj.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabelCoordInicObj.setText("Coordenadas de Rudolph:");

        jLabelCoordXObj.setText("x:");

        jTextFieldCoordXObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCoordXObjActionPerformed(evt);
            }
        });

        jLabelCoordYObj.setText("y:");

        jButtonSet.setText("Establecer");
        jButtonSet.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jPanelMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelMapa.setName(""); // NOI18N
        jPanelMapa.setPreferredSize(new java.awt.Dimension(504, 504));

        javax.swing.GroupLayout jPanelMapaLayout = new javax.swing.GroupLayout(jPanelMapa);
        jPanelMapa.setLayout(jPanelMapaLayout);
        jPanelMapaLayout.setHorizontalGroup(
            jPanelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );
        jPanelMapaLayout.setVerticalGroup(
            jPanelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        jLabelDecision.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabelDecision.setText("Decisiones tomadas:");

        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPanelDecision, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCoordXObj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldCoordXObj, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCoordYObj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCoordYObj, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCoordXAgent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldCoordXAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCoordYAgent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCoordYAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelDecision)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabelSC, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)))
                            .addComponent(jLabelCoordInicAgent)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabelCoordInicObj)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelCoordInicAgent)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCoordXAgent)
                            .addComponent(jTextFieldCoordXAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCoordYAgent)
                            .addComponent(jTextFieldCoordYAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jLabelCoordInicObj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCoordXObj)
                            .addComponent(jTextFieldCoordXObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCoordYObj)
                            .addComponent(jTextFieldCoordYObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jButtonSet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDecision)
                            .addComponent(jLabelSC, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPanelDecision, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void visualizarMapa() {
        int filas = mapa.getFilas();
        int columnas = mapa.getColumnas();
        //Creo un grid del tamaño del mapa
        jPanelMapa.setLayout(new GridLayout(filas, columnas));
         
        // Establezco que el tamaño del jPanel para que el Gridlayout se extienda entero 
        jPanelMapa.setPreferredSize(new Dimension(filas*this.TamCelda,columnas*this.TamCelda/*jPanelMapa.getWidth(), jPanelMapa.getHeight()*/));
       
    
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JLabel label = new JLabel();
                
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1 ));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                
                SimpleEntry<Integer, Integer> coordenadas = new SimpleEntry<>(i, j);
                
                if ( mapa.esAccesible(coordenadas)) {
                    label.setBackground(Color.WHITE); // Casilla con valor 0
                    label.setBorder(null);
                } else {
                    label.setBackground(Color.DARK_GRAY); // Casilla con valor -1
                }
                jPanelMapa.add(label);
                etiquetasMapa.put(new SimpleEntry<>(i, j), label);
            }
        }
        
        this.setSize(new Dimension(columnas*this.TamCelda+350/*columnas*(int)(this.TamCelda*0.47)*/,filas*this.TamCelda+50/*filas*(int)(this.TamCelda*0.07)*/));
    }
  
    // ------------------------------------------------------------------------------------
    private void visualizarAccion(String accion) {
        JLabel nuevaEtiqueta = new JLabel(accion);
        panelEtiquetas.add(nuevaEtiqueta); // Agregar la nueva etiqueta al panel.

        jScrollPanelDecision.setViewportView(panelEtiquetas); // Agregar el panel al JScrollPane.

        // Desplazar automáticamente hacia abajo el JScrollPane.
        SwingUtilities.invokeLater(() -> {
            Rectangle bounds = nuevaEtiqueta.getBounds();
            jScrollPanelDecision.getViewport().scrollRectToVisible(bounds);
        });
        
        jScrollPanelDecision.revalidate();
        jScrollPanelDecision.repaint();
    }
    
    // ------------------------------------------------------------------------------------
    private void jTextFieldCoordXAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCoordXAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCoordXAgentActionPerformed

    // ------------------------------------------------------------------------------------
    private void jTextFieldCoordXObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCoordXObjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCoordXObjActionPerformed

    // ------------------------------------------------------------------------------------
    private void jButtonSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetActionPerformed
        String coordXStr = jTextFieldCoordXAgent.getText();
        String coordYStr = jTextFieldCoordYAgent.getText();
        
        String coordXObjStr = jTextFieldCoordXObj.getText();
        String coordYObjStr = jTextFieldCoordYObj.getText();
        
        SimpleEntry<Integer, Integer> posRudolph;
        
      
        
        //jLabelSC.setIcon(this.santaI);
        
        if ((!coordXStr.isEmpty() && !coordYStr.isEmpty()) && (!coordXObjStr.isEmpty() && !coordYObjStr.isEmpty())) {
            // Convertir los valores de texto a enteros y convertirlos a SimpleEntry.
            // ----------- Agente -----------
            int coordX = Integer.parseInt(coordXStr);
            int coordY = Integer.parseInt(coordYStr);
            posBuscador = new SimpleEntry<>(coordX, coordY);

            // ----------- Objetivo -----------
            int coordXObj = Integer.parseInt(coordXObjStr);
            int coordYObj = Integer.parseInt(coordYObjStr);
            posRudolph = new SimpleEntry<>(coordXObj, coordYObj);
        }
        else {
            posBuscador = this.entorno.getPosicionBuscador();
            posRudolph = this.entorno.getPosicionRudolph();
        }
        
        try {
            boolean esAccesibleAg = mapa.esAccesible(posBuscador);
            boolean esAccesibleObj = mapa.esAccesible(posRudolph);

            // -------------------
            // Comprobamos si la celda es accesible o no (si hay un obstáculo o no).
            if (esAccesibleAg && esAccesibleObj){
                entorno.setPosicionBuscador(posBuscador); // Llamar al método del agente para establecer la posición.

                // Establecer la imagen del agente en la casilla
                //ImageIcon imagenBuscador = new ImageIcon("img/Buscador.png");
                JLabel labelBuscador = etiquetasMapa.get(posBuscador);
                labelBuscador.setIcon(this.elfoI);
                
                // Llamar al método del entorno para establecer la posición del objetivo.
                entorno.setPosicionRudolph(posRudolph);
                
                // Hacemos que el primero objetivo sea su posicion inicial para que entre en el primer paso
                entorno.setPosicionObjetivo(posBuscador);

                // Establecer la imagen del objetivo en la casilla.
                //ImageIcon imagenRudolph = new ImageIcon("img/Rudolph.png");
                JLabel labelRudolph = etiquetasMapa.get(posRudolph);
                labelRudolph.setIcon(this.rudolphI);

                jButtonSet.setEnabled(false);
                jTextFieldCoordXAgent.setEnabled(false);
                jTextFieldCoordYAgent.setEnabled(false);
                jTextFieldCoordXObj.setEnabled(false);
                jTextFieldCoordYObj.setEnabled(false);
            } 
            // -------------------------------------
            else{
                String message = "Por favor, indique una casilla accesible para: ";
                if (!esAccesibleAg)
                    message+= " *agente ";

                if (!esAccesibleObj)
                    message+= "*objetivo";

                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        // -------------------------------------
        catch (NumberFormatException e){
            // Manejar error si los valores no son enteros.
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores enteros para X e Y.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSetActionPerformed
    
    // ------------------------------------------------------------------------------------
    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        if (mainListener != null) {
            mainListener.onIniciarButtonPreseed();
        }
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    // ------------------------------------------------------------------------------------
    public void setMainListener(MainListener listener){
        // Cuando se pulse el botón "Iniciar" comenzará el listener en el main.
        this.mainListener = listener;
    }
    
    // ------------------------------------------------------------------------------------
    // Se establece un icono donde está la posición del agente y puntos por donde ya ha pasado.
    public void actualizarAgente(PosiblesMovimientos movimiento) {
        if (posBuscador.equals(entorno.getPosicionRudolph()))
            etiquetasMapa.get(posBuscador).setIcon(this.rudolphI);
        else
            etiquetasMapa.get(posBuscador).setIcon(this.caminoI);
            
        this.posBuscador = movimiento.sumar(posBuscador);
        
        etiquetasMapa.get(posBuscador).setIcon(this.elfoI);
    }
    
    public void actualizarReno(SimpleEntry<Integer, Integer> coord){
         
        if (etiquetasMapa.containsKey(posReno)) {
            etiquetasMapa.get(posReno).setIcon(null);
        }
       
        posReno = coord;
        etiquetasMapa.get(posReno).setIcon(this.renoI);
    }
    
    public void santa(SimpleEntry<Integer,Integer> coord) {
        if (this.etiquetasMapa.containsKey(coord))
            this.etiquetasMapa.get(coord).setIcon(santaI);
    }
    
    public void renoEncontrado(){
        this.etiquetasMapa.replaceAll((key, value) -> {
            value.setIcon(null);
            return value;
        });
    }
    
    // ------------------------------------------------------------------------------------
    // Escalar el icono en funcion del tamaño de la celda. 
    public ImageIcon scale(String path) {
        return new ImageIcon((new ImageIcon(path)).getImage().getScaledInstance(this.TamCelda-(int)(this.TamCelda*0.2), this.TamCelda-(int)(this.TamCelda*0.2), java.awt.Image.SCALE_SMOOTH) );
    }
    
    // ------------------------------------------------------------------------------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButtonSet;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabelCoordInicAgent;
    private javax.swing.JLabel jLabelCoordInicObj;
    private javax.swing.JLabel jLabelCoordXAgent;
    private javax.swing.JLabel jLabelCoordXObj;
    private javax.swing.JLabel jLabelCoordYAgent;
    private javax.swing.JLabel jLabelCoordYObj;
    private javax.swing.JLabel jLabelDecision;
    private javax.swing.JLabel jLabelSC;
    private javax.swing.JPanel jPanelMapa;
    private javax.swing.JScrollPane jScrollPanelDecision;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldCoordXAgent;
    private javax.swing.JTextField jTextFieldCoordXObj;
    private javax.swing.JTextField jTextFieldCoordYAgent;
    private javax.swing.JTextField jTextFieldCoordYObj;
    // End of variables declaration//GEN-END:variables
}
