/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Collections;
import juego.Juego;

/**
 *
 * @author Jose
 */
public class Jugador {

    public enum TipoJugador {
        pc,
        humano
    }

    public enum RobarCartas {
        robaNormal,
        noHayMasMazo,
        noHayMasLugar
    }

    private ArrayList<Carta> mazo;
    private ArrayList<Carta> cartasEnMano;
    private ArrayList<Carta> cartasEnJuego;
    //private boolean juegaPrimero;
    private int vidas;
    private int manaTotal;
    private int manaDisponible;
    private boolean activo;
    private TipoJugador tipoJugador;
    private String nombre;

    public Jugador(ArrayList<Carta> mazo, boolean juegaPrimero) {
        this.mazo = mazo;
        this.vidas = Juego.VIDAS;

        if (juegaPrimero) {
            this.manaDisponible = 1;
            this.manaTotal = 1;
            this.activo = true;
        } else {
            this.manaDisponible = 1;
            this.manaTotal = 1;
        }

        cartasEnJuego = new ArrayList<>();
        cartasEnMano = new ArrayList<>();

        asignarJugadorACartas();
        mezclar();
        manoInicial();

    }

    private void mezclar() {
        Collections.shuffle(mazo);
    }

    private void manoInicial() {

        for (int i = 0; i < Juego.MANO_INICIAL; i++) {
            cartasEnMano.add(mazo.get(mazo.size() - 1));
            mazo.remove(mazo.size() - 1);
        }
    }

    ////METODOS PUBLICOS///
    public RobarCartas robarCarta() {
        if (mazo.size() <= 0) {
            System.out.println("Jugador.robarCarta: no hay mas cartas "
                    + "en el mazo");
            return RobarCartas.noHayMasMazo;
        } else if (cartasEnMano.size() >= Juego.MAX_EN_MANO) {
            return RobarCartas.noHayMasLugar;
        } else {

            int index = mazo.size() - 1;
            cartasEnMano.add(mazo.get(index));
            mazo.remove(index);
            return RobarCartas.robaNormal;

        }

    }

    public void jugarCarta(Carta carta) {

        if (cartasEnMano.contains(carta)) {
            cartasEnJuego.add(carta);
            cartasEnMano.remove(carta);
        } else {
            System.out.println("Carta.jugarCarta: la carta que se intenta jugar "
                    + "no se encouentra en la mano de el jugador");
        }

    }

    //Indicar el judar "dueño" de la carta
    private void asignarJugadorACartas() {
        for (int i = 0; i < mazo.size(); i++) {
            mazo.get(i).setJugador(this);
        }
    }

    //GETTER SETTERS//
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    public void setMazo(ArrayList<Carta> mazo) {
        this.mazo = mazo;
    }

    public ArrayList<Carta> getCartasEnMano() {
        return cartasEnMano;
    }

    public void setCartasEnMano(ArrayList<Carta> cartasEnMano) {
        this.cartasEnMano = cartasEnMano;
    }

    public ArrayList<Carta> getCartasEnJuego() {
        return cartasEnJuego;
    }

    public void setCartasEnJuego(ArrayList<Carta> cartasEnJuego) {
        this.cartasEnJuego = cartasEnJuego;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getManaTotal() {
        return manaTotal;
    }

    public void setManaTotal(int manaTotal) {
        this.manaTotal = manaTotal;
    }

    public int getManaDisponible() {
        return manaDisponible;
    }

    public void setManaDisponible(int manaDisponible) {
        this.manaDisponible = manaDisponible;
    }

    public TipoJugador getTipoJugador() {
        return tipoJugador;
    }

    public void setTipoJugador(TipoJugador tipoJugador) {
        this.tipoJugador = tipoJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
