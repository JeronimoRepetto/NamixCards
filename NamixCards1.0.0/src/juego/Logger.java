/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import LogsJuego.LogsJuego;

/**
 *
 * @author Jose
 */
public class Logger {
    
    private LogsJuego lg;
    private String fullLog;
    
    public Logger(){
        lg = new LogsJuego();
        fullLog = "";
    }
    
    public void log(String nuevaLine){
        fullLog+= "  " + nuevaLine + "\n";
        lg.CargarTexto(fullLog);
        
    }
    
    public void destruir(){
        lg.setVisible(false);
        lg = null;
        
    }
    
    
}
