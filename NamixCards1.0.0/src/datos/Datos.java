package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import modelos.Carta;

/**
 * @author Jose
 *
 */
public class Datos {

    public ArrayList<Carta> cargarDatos(String nombre) {

        BufferedReader buffer = null;
        ArrayList<Carta> mazo = new ArrayList<Carta>();

        String sep = File.separator;
        if (nombre == null) {
            nombre = "src/csv/mazo1v2.csv";
        }

        try {

            String line;
            buffer = new BufferedReader(new FileReader(nombre));

            while ((line = buffer.readLine()) != null) {
                //Nos saltiamos cualquier line que no empieze con 0
                if (line.startsWith("0")) {
                    ArrayList<Carta> c = csvToCarta(line);
                    if (c != null) {
                        mazo.addAll(c);
                    }
                }
            }

            return mazo;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    // Convertir CSV a ArrayList the cartas utilizando el metodo Split
    private ArrayList<Carta> csvToCarta(String csvLine) {
        ArrayList<Carta> c = new ArrayList<>();

        if (csvLine != null) {
            String[] splitData = csvLine.split("\\s*;\\s*");
            for (int i = 0; i < Integer.parseInt(splitData[5]); i++) {

                Carta.Tipo tipo = splitData[2].equalsIgnoreCase("criatura") ? Carta.Tipo.criatura : Carta.Tipo.hechizo;
                Carta cr = new Carta(splitData[1], Integer.parseInt(splitData[4]), Integer.parseInt(splitData[3]), tipo);
                c.add(cr);
            }
        }

        return c;
    }

}
