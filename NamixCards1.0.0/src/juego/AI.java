/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.ArrayList;
import java.util.Objects;
import modelos.Carta;
import modelos.Jugador;

/**
 *
 * @author Usuario
 */
class AI {

    public boolean charlatan;

    private final Jugador yo;
    private final Juego juego;
    private boolean mueroEnProxTurno;
    private int poderDisponible;
    private int dañoMaximoPosible;
    private ArrayList<Carta> combinacionDañoMaxima, mejorCombinacionCriaturas, cartasJugables;
    private float chanceDePerder;
    private float poderOponente;
    private final ArrayList<Par> parCriaturas;
    private final ArrayList<Par> parHechizos;

    public AI(Juego juego, Jugador jugador, boolean charlatan) {

        this.yo = jugador;
        this.juego = juego;
        this.charlatan = charlatan;
        combinacionDañoMaxima = new ArrayList<>();
        mejorCombinacionCriaturas = new ArrayList<>();
        parCriaturas = new ArrayList<>();
        parHechizos = new ArrayList<>();
        cartasJugables = new ArrayList<>();

    }

    public void play() {

        //Actuar
        evalVictoria();
        evalDerrota();
        jugar();
        parCriaturas.clear();
        parHechizos.clear();
        juego.terminarTurno();
    }

    private void evalVictoria() {
        //Resetar valores
        poderDisponible = 0;
        combinacionDañoMaxima.clear();
        mejorCombinacionCriaturas.clear();
        cartasJugables.clear();

        dañoMaximoPosible = calcDañoMaximo();
        mejorCombinacioCriaturas(cartasJugables);

    }

    private void evalDerrota() {

        chanceDePerder = 0;
        mueroEnProxTurno = false;
        poderOponente = 0;
        for (Carta c : juego.getJugadorPasivo().getCartasEnJuego()) {
            poderOponente += c.getPoder();
        }
        if (poderOponente >= yo.getVidas()) {

            mueroEnProxTurno = true;
        }
        chanceDePerder = calcChanceDePerder();
        if (charlatan) {
            juego.logger.log("--Mis chances de perder son: " + chanceDePerder);
        }

    }

    private void jugar() {
        //Matar oponenete si es posible
        if (dañoMaximoPosible >= juego.getJugadorPasivo().getVidas()) {
            if (charlatan) {
                juego.logger.log("--Voy con todo al jugador");
                juego.logger.log("--Plan ofensivo");
            }
            jugarCartas(combinacionDañoMaxima, true);
            attacarJugador(yo.getCartasEnJuego());
        } else if (mueroEnProxTurno || chanceDePerder > 0.55f) {

            if (charlatan) {
                juego.logger.log("--Plan defensivo");
            }
            jugarCartas(mejorCombinacionCriaturas, false);
            ataqueDefensivo(yo.getCartasEnJuego());
            hechizosACriaturas(false);
            if (mueroEnProxTurno || chanceDePerder > 0.71) {
                if (charlatan) {
                    juego.logger.log("--Estoy en el horno, muero en proximo turno si no hago algo...");
                }
                tirarTodosLosHechizos();
            }
        } else {
            if (charlatan) {
                juego.logger.log("--Plan seguro");
            }
            jugarCartas(mejorCombinacionCriaturas, false);
            if (chanceDePerder > 0.35) {
                ataqueOfensivo(yo.getCartasEnJuego());
            } else {
                ataqueSeguro(yo.getCartasEnJuego());
            }
            hechizosACriaturas(true);
        }
    }

    private void ataqueDefensivo(ArrayList<Carta> cartasEnJuego) {

        //Attacar criaturas
        for (Carta c : cartasEnJuego) {
            if (c.isAtaco()) {
                continue;
            }
            atacarCriatura(c, mejorObjectivoCriatura(c, false));
        }
        ejecutarPares(parCriaturas);
       
        //atacar jugador
        for (Carta c : cartasEnJuego) {
            if (!c.isAtaco()) {
                attacarJugador(c);
            }
        }
    }

    private void ataqueOfensivo(ArrayList<Carta> cartasEnJuego) {

        for (Carta c : cartasEnJuego) {
            if (c.isAtaco()) {
                continue;
            }
            atacarCriatura(c, mejorObjectivoCriatura(c, true));
        }
        ejecutarPares(parCriaturas);
        //atacar jugador
        for (Carta c : cartasEnJuego) {
            if (!c.isAtaco()) {
                attacarJugador(c);
            }
        }
    }

    private void ataqueSeguro(ArrayList<Carta> cartasEnJuego) {

        for (Carta c : cartasEnJuego) {
            if (c.isAtaco()) {
                continue;
            }
            atacarCriatura(c, objectivoCriaturaSeguro(c));
        }
        ejecutarPares(parCriaturas);
        //atacar jugador
        for (Carta c : cartasEnJuego) {
            if (!c.isAtaco()) {
                attacarJugador(c);
            }
        }
    }

    private ArrayList<Carta> getCartasJugables() {
        //Seleccionas entre todas las cartas disponibles las jugables con el mana
        for (Carta c : yo.getCartasEnMano()) {
            if (c.getCoste() <= yo.getManaDisponible()) {
                cartasJugables.add(c);
            }
        }
        return cartasJugables;

    }

    private int calcDañoMaximo() {
        int max = poderDisponible;
        max += cambinacionDañoMaxima(getCartasJugables());
        return max;
    }

    private int cambinacionDañoMaxima(ArrayList<Carta> cartasJugables) {

        int mejorSumPoder = 0;

        for (Carta c : cartasJugables) {

            if (c.getCoste() > yo.getManaDisponible()) {
                continue;
            }

            int mana = yo.getManaDisponible();
            int poder = 0;
            ArrayList<Carta> combActual = new ArrayList<>();
            combActual.add(c);
            poder += c.getPoder();
            mana -= c.getCoste();

            for (Carta e : cartasJugables) {
                if (combActual.contains(e)) {
                    continue;
                }
                if (e.getCoste() > mana) {
                    continue;
                }
                combActual.add(e);
                mana -= e.getCoste();
                poder += e.getPoder();
            }

            if (poder > mejorSumPoder) {
                mejorSumPoder = poder;
                combinacionDañoMaxima.clear();
                combinacionDañoMaxima.addAll(combActual);

            }

        }

        return mejorSumPoder;
    }

    private void mejorCombinacioCriaturas(ArrayList<Carta> cartasJugables) {

        int mejorSumPoder = 0;

        for (Carta c : cartasJugables) {

            if (c.getTipo() == Carta.Tipo.hechizo || c.getCoste() > yo.getManaDisponible()) {
                continue;
            }

            int mana = yo.getManaDisponible();
            int poder = 0;
            ArrayList<Carta> combActual = new ArrayList<>();
            combActual.add(c);
            poder += c.getPoder();
            mana -= c.getCoste();

            for (Carta e : cartasJugables) {
                if (e.getTipo() == Carta.Tipo.hechizo) {
                    continue;
                }
                if (combActual.contains(e)) {
                    continue;
                }

                if (e.getCoste() > mana) {
                    continue;
                }
                combActual.add(e);
                mana -= e.getCoste();
                poder += e.getPoder();
            }
            if (poder > mejorSumPoder) {
                mejorSumPoder = poder;
                mejorCombinacionCriaturas.clear();
                mejorCombinacionCriaturas.addAll(combActual);
            }

        }
        
    }

    private int calcDañoNesesario() {

        for (Carta c : yo.getCartasEnJuego()) {
            poderDisponible += c.getPoder();
        }
        return (juego.getJugadorPasivo().getVidas() - poderDisponible);
    }

    private void jugarCartas(ArrayList<Carta> cartas, boolean conHechizos) {
        for (Carta c : cartas) {
            if (c.getTipo() == Carta.Tipo.criatura) {
                juego.cartaClickeda(c);
            } else if (conHechizos) {
                juego.cartaClickeda(c);
                juego.oponenteClickeado(juego.getJugadorPasivo());
            }

        }
    }

    private void attacarJugador(Carta carta) {

        juego.cartaClickeda(carta);
        juego.oponenteClickeado(juego.getJugadorPasivo());

    }

    private void attacarJugador(ArrayList<Carta> cartas) {
        for (Carta c : cartas) {
            juego.cartaClickeda(c);
            juego.oponenteClickeado(juego.getJugadorPasivo());
        }
    }

    private void hechizosACriaturas(boolean optimo) {

        for (Carta hechizo : yo.getCartasEnMano()) {
            if (hechizo.getTipo() == Carta.Tipo.hechizo && hechizo.getCoste() <= yo.getManaDisponible()) {
                Carta cartaADañar = mejorObjetivoHechizo(hechizo, optimo);
                if (cartaADañar != null && estariaViva(cartaADañar)) {

                    parHechizos.add(new Par(hechizo, cartaADañar));

                }
            }
        }

        ejecutarPares(parHechizos);
    }

    private void ejecutarPares(ArrayList<Par> pares) {
        for (Par p : pares) {
            juego.cartaClickeda(p.atacante);
            juego.cartaClickeda(p.objetivo);
        }
    }

    private void atacarCriatura(Carta cartaAtacante, Carta mejorObjetivo) {
        if (cartaAtacante == null || mejorObjetivo == null) {
            return;
        }
        //No queremos añadir dos veces el mismo objetivo
        for (Par p : parCriaturas) {
            if (p.objetivo.equals(mejorObjetivo)) {
                return;
            }
        }

        parCriaturas.add(new Par(cartaAtacante, mejorObjetivo));

    }

    private Carta mejorObjetivoHechizo(Carta hechizo, boolean optimo) {
        //buscar carta de igual poder
        for (Carta cObj : juego.getJugadorPasivo().getCartasEnJuego()) {
            if (Objects.equals(cObj.getPoder(), hechizo.getPoder())) {
                return cObj;
            }
        }
        if (!optimo) {
            //Buscar la mas grande que podamos matar
            int selIndex = -1;
            int maxPoder = 0;
            int i = 0;
            for (Carta cOrd : juego.getJugadorActivo().getCartasEnJuego()) {
                if (cOrd.getPoder() < hechizo.getPoder()) {
                    if (cOrd.getPoder() > maxPoder) {
                        selIndex = i;
                        maxPoder = cOrd.getPoder();
                    }
                }
                i++;
            }

            if (selIndex > -1) {
                return juego.getJugadorActivo().getCartasEnJuego().get(selIndex);
            }
        }
        //si no podemos matar devolvemos null
        return null;
    }

    //Tirar todos los hechizos en mano a las criaturas del oponente (de manera bruta)
    private void tirarTodosLosHechizos() {

        parHechizos.clear();
        //Lista con hechizos que se pueden tirar
        ArrayList<Carta> hechizos = new ArrayList<>();
        int manaDip = yo.getManaDisponible();
        for (Carta c : yo.getCartasEnMano()) {
            if (c.getTipo() == Carta.Tipo.hechizo) {
                if (manaDip >= c.getCoste()) {
                    hechizos.add(c);
                    manaDip -= c.getCoste();
                }
            }
        }
        //crear pares para ejecutar
        for (Carta c : juego.getJugadorPasivo().getCartasEnJuego()) {
            if (hechizos.isEmpty()) {
                break;
            }
            parHechizos.add(new Par(hechizos.get(0), c));
            hechizos.remove(0);
        }

        ejecutarPares(parHechizos);
       
    }

    private Carta mejorObjectivoCriatura(Carta criatura, boolean optimo) {

        for (Carta c : juego.getJugadorPasivo().getCartasEnJuego()) {
            if (Objects.equals(c.getPoder(), criatura.getPoder())) {
                return c;
            }
        }

        if (!optimo) {
            //Buscar la mas grande de todas
            int selIndex = -1;
            int maxPoder = 0;
            int i = 0;
            for (Carta cFuerta : juego.getJugadorActivo().getCartasEnJuego()) {

                if (cFuerta.getPoder() > maxPoder) {
                    selIndex = i;
                    maxPoder = cFuerta.getPoder();
                }

                i++;
            }

            if (selIndex > -1) {
                return juego.getJugadorActivo().getCartasEnJuego().get(selIndex);
            }
        }
        //si no podemos matar devolvemos null
        return null;
    }

    private Carta objectivoCriaturaSeguro(Carta criatura) {

        //Buscar la mas grande de todas las que podamos matar sin morir
        int selIndex = -1;
        int maxPoder = 0;
        int i = 0;
        for (Carta cFuerta : juego.getJugadorActivo().getCartasEnJuego()) {
            if (cFuerta.getPoder() < criatura.getPoder()) {
                if (cFuerta.getPoder() > maxPoder) {
                    selIndex = i;
                    maxPoder = cFuerta.getPoder();
                }
            }
            i++;
        }

        if (selIndex > -1) {
            return juego.getJugadorActivo().getCartasEnJuego().get(selIndex);
        }

        //si no podemos matar devolvemos null
        return null;
    }

    private float calcChanceDePerder() {
        float vidasRestantes = (float) (yo.getVidas() - poderOponente);
        int cartasManoOp = juego.getJugadorPasivo().getCartasEnMano().size();
        int manaOp = juego.getJugadorPasivo().getManaTotal();
        //A mayot vidas restantes, menor es el total
        float peorCaso = Juego.VIDAS * 2.5f + (Juego.MAX_EN_JUEGO * Juego.MAX_EN_MANO) / 3f;
        return ((Juego.VIDAS - vidasRestantes) * 2.5f + (cartasManoOp * manaOp) / 3f) / peorCaso;

    }

    private boolean estariaViva(Carta cartaADañar) {
        int daño = 0;
        int vida = cartaADañar.getPoder();
        for (Par p : parHechizos) {
            if (p.objetivo.equals(cartaADañar)) {
                vida -= p.atacante.getPoder();
                if (vida <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private class Par {

        Carta atacante;
        Carta objetivo;

        public Par(Carta atacante, Carta objetivo) {
            this.atacante = atacante;
            this.objetivo = objetivo;
        }
    }

}
