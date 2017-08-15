/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class InfoVisualJuego {

    private ArrayList<Carta> cartasJugadorMano;
    private ArrayList<Carta> cartasJugadorTablero;
    private ArrayList<Carta> cartasPCTablero;
    private ArrayList<Carta> cartasPCMano;
    private int vidasJugador;
    private int vidasPC;
    private int manaTotalJugador;
    private int manaDispJugador;
    private int manaTotalPC;
    private int manaDispPC;

    public InfoVisualJuego() {
        //Crear las listas de cartas
        cartasJugadorMano = new ArrayList<>();
        cartasJugadorTablero = new ArrayList<>();
        cartasPCMano = new ArrayList<>();
        cartasPCTablero = new ArrayList<>();
    }

    public void actualizarInfo(Jugador j1, Jugador j2pc) {

        cartasJugadorMano = j1.getCartasEnMano();
        cartasJugadorTablero = j1.getCartasEnJuego();
        cartasPCMano = j2pc.getCartasEnMano();
        cartasPCTablero = j2pc.getCartasEnJuego();

        vidasJugador = j1.getVidas();
        vidasPC = j2pc.getVidas();

        manaTotalJugador = j1.getManaTotal();
        manaDispJugador = j1.getManaDisponible();
        manaTotalPC = j2pc.getManaTotal();
        manaDispPC = j2pc.getManaDisponible();

    }

    public ArrayList<Carta> getCartasJugadorMano() {
        return cartasJugadorMano;
    }

    public void setCartasJugadorMano(ArrayList<Carta> cartasJugadorMano) {
        this.cartasJugadorMano = cartasJugadorMano;
    }

    public ArrayList<Carta> getCartasJugadorTablero() {
        return cartasJugadorTablero;
    }

    public void setCartasJugadorTablero(ArrayList<Carta> cartasJugadorTablero) {
        this.cartasJugadorTablero = cartasJugadorTablero;
    }

    public ArrayList<Carta> getCartasPCTablero() {
        return cartasPCTablero;
    }

    public void setCartasPCTablero(ArrayList<Carta> cartasPCTablero) {
        this.cartasPCTablero = cartasPCTablero;
    }

    public ArrayList<Carta> getCartasPCMano() {
        return cartasPCMano;
    }

    public void setCartasPCMano(ArrayList<Carta> cartasPCMano) {
        this.cartasPCMano = cartasPCMano;
    }

    public int getVidasJugador() {
        return vidasJugador;
    }

    public void setVidasJugador(int vidasJugador) {
        this.vidasJugador = vidasJugador;
    }

    public int getVidasPC() {
        return vidasPC;
    }

    public void setVidasPC(int vidasPC) {
        this.vidasPC = vidasPC;
    }

    public int getManaTotalJugador() {
        return manaTotalJugador;
    }

    public void setManaTotalJugador(int manaTotalJugador) {
        this.manaTotalJugador = manaTotalJugador;
    }

    public int getManaDispJugador() {
        return manaDispJugador;
    }

    public void setManaDispJugador(int manaDispJugador) {
        this.manaDispJugador = manaDispJugador;
    }

    public int getManaTotalPC() {
        return manaTotalPC;
    }

    public void setManaTotalPC(int manaTotalPC) {
        this.manaTotalPC = manaTotalPC;
    }

    public int getManaDispPC() {
        return manaDispPC;
    }

    public void setManaDispPC(int manaDispPC) {
        this.manaDispPC = manaDispPC;
    }

}
