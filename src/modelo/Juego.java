/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package modelo;

import Principal.Celda;
import Principal.FormInicio;
import Principal.Tablero;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Juego extends javax.swing.JFrame {

    private Tablero tablero;
    private JButton[][] botones;

    private JRadioButton bfsButton;
    private JRadioButton dfsButton;
    private ButtonGroup group;
    private JButton guardarButton;

    public Juego(int filas, int columnas, int minas) {
        setTitle("Metrobuscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        tablero = new Tablero(filas, columnas, minas);
        botones = new JButton[filas][columnas];

        InicializarInterfaz();
        AgregarRadioButtons();
        AgregarBotonGuardar();
        setLocationRelativeTo(null);
        pack();
        revalidate();
        repaint();
    }

    public Juego(Tablero tableroCargado) {
        this.tablero = tableroCargado;
        botones = new JButton[tableroCargado.getFilas()][tableroCargado.getColumnas()];

        setTitle("Metrobuscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        InicializarInterfaz(); 
        AgregarRadioButtons(); 
        AgregarBotonGuardar(); 

        setLocationRelativeTo(null); 
        pack();
        revalidate();
        repaint();
    }

    private void InicializarInterfaz() {
        JPanel panelTablero = new JPanel(new GridLayout(tablero.getFilas(), tablero.getColumnas()));

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                final int fila = i;
                final int columna = j;

                Celda celda = tablero.ObtenerCelda(fila, columna);
                JButton boton = new JButton(celda.getId());
                boton.setPreferredSize(new java.awt.Dimension(60, 60));

                boton.addActionListener(e -> ClicCasilla(fila, columna));

                boton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
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

    private void AgregarBotonGuardar() {
        guardarButton = new JButton("Guardar Partida");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuardarPartida();
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(guardarButton);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void GuardarPartida() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                for (int i = 0; i < tablero.getFilas(); i++) {
                    for (int j = 0; j < tablero.getColumnas(); j++) {
                        Celda celda = tablero.ObtenerCelda(i, j);
                        if (celda.isEsMina()) {
                            writer.write("M");
                        } else if (celda.isEsMarcada()) {
                            writer.write("B");
                        } else if (celda.isEsRevelada()) {
                            writer.write(Integer.toString(celda.getMinasAdyacentes()));
                        } else {
                            writer.write("V");
                        }

                        if (j < tablero.getColumnas() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");
                }
                JOptionPane.showMessageDialog(this, "Partida guardada correctamente.");
                dispose();
                FormInicio inicio = new FormInicio();
                inicio.setVisible(true);
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la partida: " + e.getMessage());
            }
        }
    }

    private void AgregarRadioButtons() {
        bfsButton = new JRadioButton("BFS (Búsqueda en amplitud)");
        dfsButton = new JRadioButton("DFS (Búsqueda en profundidad)");

        group = new ButtonGroup();
        group.add(bfsButton);
        group.add(dfsButton);

        JPanel panelBusqueda = new JPanel(new FlowLayout());
        panelBusqueda.add(bfsButton);
        panelBusqueda.add(dfsButton);

        bfsButton.setSelected(true);

        bfsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });

        dfsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });

        getContentPane().add(panelBusqueda, java.awt.BorderLayout.NORTH);
    }

    // Metodo para controlar la funcion del clic izquierdo del mouse sobre una casilla 
    private void ClicCasilla(int fila, int columna) {

        if (bfsButton.isSelected()) {
            tablero.RevelarCeldaAmplitud(fila, columna); // Usar BFS
        } else if (dfsButton.isSelected()) {
            tablero.RevelarCeldaProfundidad(fila, columna); // Usar DFS
        }
        ActualizarInterfaz();

        if (tablero.ObtenerCelda(fila, columna).isEsMina()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Has perdido", "Partida terminada", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();
            FormInicio inicio = new FormInicio();
            inicio.setVisible(true);
        } else if (tablero.VerificarVictoria()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Has ganado", "Partida terminada", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();
            FormInicio inicio = new FormInicio();
            inicio.setVisible(true);
        }
    }

    private void ActualizarInterfaz() {
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                JButton boton = botones[i][j];
                Celda celda = tablero.ObtenerCelda(i, j);

                if (celda.isEsRevelada()) {
                    if (celda.isEsMina()) {
                        boton.setText("💣");
                    } else {
                        boton.setText(Integer.toString(celda.getMinasAdyacentes()));
                    }
                } else if (celda.isEsMarcada()) {
                    boton.setText("🚩");
                } else {
                    boton.setText("");
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
