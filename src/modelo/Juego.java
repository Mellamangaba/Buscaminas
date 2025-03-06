/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package modelo;

import Principal.Celda;
import Principal.FormInicio;
import Principal.Tablero;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;


public class Juego extends javax.swing.JFrame {
    private Tablero tablero;
    private JButton[][] botones;
    
    public Juego(int filas, int columnas, int minas) {                
        setTitle("Metrobuscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
                
        tablero = new Tablero(filas, columnas, minas);
        botones = new JButton[filas][columnas];
        
        InicializarInterfaz();
        
    }
    private void InicializarInterfaz(){
        JPanel panelTablero = new JPanel(new GridLayout(tablero.getFilas(), tablero.getColumnas()));
        
        for (int i = 0; i < tablero.getFilas(); i++){
            for (int j = 0; j < tablero.getColumnas(); j++){
                final int fila = i;
                final int columna = j;
                
                Celda celda = tablero.ObtenerCelda(fila, columna);
                JButton boton = new JButton(celda.getId());
                boton.setPreferredSize(new java.awt.Dimension(60, 60));
                
                boton.addActionListener(e -> ClicCasilla(fila, columna));
                
                boton.addMouseListener(new MouseAdapter(){                    
                    public void mouseClicked(MouseEvent e){
                        if (SwingUtilities.isRightMouseButton(e)){
                            tablero.MarcarCelda(fila, columna);
                            ActualizarInterfaz();
                        }
                    }
                });
                botones[i][j] = boton;
                panelTablero.add(boton);
            }
        }
        add(panelTablero);
        pack();
    }
    // Metodo para controlar la funcion del clic izquierdo del mouse sobre una casilla 
    private void ClicCasilla(int fila, int columna){
        tablero.RevelarCelda(fila, columna);
        ActualizarInterfaz();
        
        if (tablero.ObtenerCelda(fila, columna).isEsMina()){
            javax.swing.JOptionPane.showMessageDialog(this, "Has perdido", "Partida terminada", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();
            FormInicio inicio = new FormInicio();
            inicio.setVisible(true);
        } else if (tablero.VerificarVictoria()){
            javax.swing.JOptionPane.showMessageDialog(this, "Has ganado", "Partida terminada", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();
            FormInicio inicio = new FormInicio();
            inicio.setVisible(true);
        }
    }
    
    private void ActualizarInterfaz(){
        for (int i = 0; i < tablero.getFilas(); i++){
            for (int j = 0; j < tablero.getColumnas(); j++){
                JButton boton = botones[i][j];
                Celda celda = tablero.ObtenerCelda(i, j);
                
                if (celda.isEsRevelada()){
                    if(celda.isEsMina()){
                       boton.setText("💣");
                    } else {  
                       boton.setText(Integer.toString(celda.getMinasAdyacentes()));                       
                    }                    
                } else if (celda.isEsMarcada()){
                    boton.setText("🚩");
                } else {
                    boton.setText("");
                }
            }
        }    
    }
    /*
    private void ReiniciarJuego(){
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();
        int minas = tablero.getNumeroMinas();
        tablero = new Tablero(filas, columnas, minas);
        InicializarInterfaz();
    }
    */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Salir.setBackground(new java.awt.Color(204, 204, 204));
        Salir.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Salir.setText("X");
        Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalirMouseExited(evt);
            }
        });
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 363, Short.MAX_VALUE)
                .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 291, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirMouseClicked

    private void SalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseEntered
        Salir.setForeground(Color.red);
    }//GEN-LAST:event_SalirMouseEntered

    private void SalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirMouseExited
        Salir.setForeground(Color.black);
    }//GEN-LAST:event_SalirMouseExited

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Salir;
    // End of variables declaration//GEN-END:variables
}
