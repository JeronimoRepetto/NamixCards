/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.ControladorPantalla;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import juego.Juego;
import juego.Logger;
import juego.NamixCard;

/**
 *
 * @author jeron
 */
public class FinDelJuego extends javax.swing.JFrame {
    //OBJETOS
    private Juego PartidaAnterior;
    /**
     * Creates new form FinDelJuego
     */
    public FinDelJuego(Juego PartidaAnterior) {
        this.PartidaAnterior = PartidaAnterior;
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(this);
        URL url = getClass().getResource("/imagenes/NamixCardIco.png");
        ImageIcon imag = new ImageIcon(url);
        setIconImage(imag.getImage());
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
        setTitle("FIN DEL JUEGO");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        InicioJuego nuevoComienzo = new InicioJuego(PartidaAnterior);
        nuevoComienzo.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}