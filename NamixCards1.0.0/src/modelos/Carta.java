/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Usuario
 */
public class Carta {

    protected String nombre;

    public enum Tipo {
        hechizo,
        criatura
    }
    protected Tipo tipo;
    protected Integer coste;
    protected Integer poder;
    protected boolean enJuego;
    protected Jugador jugador;
    protected boolean ataco;
    protected boolean activa;
    
    public Carta(String nombre, Integer coste, Integer poder, Tipo tipo) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.coste = coste;
        this.poder = poder;
        this.jugador = null;
        this.enJuego = false;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Integer getCoste() {
        return coste;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public boolean isEnJuego() {
        return enJuego;
    }

    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isAtaco() {
        return ataco;
    }

    public void setAtaco(boolean ataco) {
        this.ataco = ataco;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    
}
