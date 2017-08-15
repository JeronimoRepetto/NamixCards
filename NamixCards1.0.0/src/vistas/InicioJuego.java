/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.ControladorPantalla;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import juego.Juego;
import vistaImagenes.BotonesParaInicioJuego;
import vistaImagenes.PanelPantallaInicio;

/**
 *
 * @author jeron
 */
public class InicioJuego extends javax.swing.JFrame {
   //VISTAS
    private InfoJuego iJ;
    //OBJETOS
    private Juego PartidaAnterior;
    /**
     * Creates new form InicioJuego
     */
    //CONTROLADOR VACIO (UNICAMENTE SE LLAMA DESDE EL MAIN)
    public InicioJuego() {
           
        //SE CREA UN PANEL PARA AGREGAR FONDO
        PanelPantallaInicio fondo = new PanelPantallaInicio();
        initComponents();
        
        AgregarBotones();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.add(fondo,BorderLayout.CENTER);
         fondo.repaint();
         //SE COLOCA ICONO A LA VENTANA
        URL url = getClass().getResource("/imagenes/NamixCardIco.png");
        ImageIcon imag = new ImageIcon(url);
        setIconImage(imag.getImage());
        
        
        
       

    }
    //CONSTRUCTOR SOBRECARGADO SE LLAMA SIEMPRE DESPUES DE UNA PARTIDA
    public InicioJuego(Juego PartidaAnterior) {
           this.PartidaAnterior = PartidaAnterior;

        PanelPantallaInicio fondo = new PanelPantallaInicio();
        initComponents();
        AgregarBotones();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.add(fondo,BorderLayout.CENTER);
         fondo.repaint();
        URL url = getClass().getResource("/imagenes/NamixCardIco.png");
        ImageIcon imag = new ImageIcon(url);
        setIconImage(imag.getImage());

        
        

    }
        //METODO PARA AGREGAR BOTONES A LA PANTALLA
        public void AgregarBotones(){
            
            BotonesParaInicioJuego botones = new BotonesParaInicioJuego(this, PartidaAnterior);
            Dimension tam = this.getSize();
            
            
            botones.setVisible(true);
            
            botones.setSize(tam.height - tam.height/4, tam.width - tam.width/4);
            botones.setLocation(this.getWidth() / (int)6, this.getHeight() / (int)5);
            botones.repaint();
            this.add(botones);
        
        
        }
    
    //METODO PARA BORRAR DE MEMORIA LA PANTALLA INFOJUEGO
    public void EliminarInfo(InfoJuego info){
        
        info = null;
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
        setTitle("Namix Cards");
        setUndecorated(true);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
