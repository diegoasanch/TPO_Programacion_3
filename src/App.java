import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import CalculaDistancia.DistanciaPalabras;

public class App {
    public static void main(String[] args) {
        // ------- TEST 1 -------
        System.out.println("\n------- TEST 1 -------");
        String palabra = "CARA";

        List<String> candidatos = new ArrayList<>();
        Collections.addAll(candidatos, "CARRO");
        runTests(palabra, candidatos);

        // ------- TEST 2 -------
        System.out.println("\n------- TEST 2 -------");
        palabra = "PADRON";

        candidatos = new ArrayList<>();
        Collections.addAll(candidatos, "PATRON");
        runTests(palabra, candidatos);

        // ------- TEST 3 -------
        System.out.println("\n------- TEST 3 -------");
        palabra = "CANTOR";

        candidatos = new ArrayList<>();
        Collections.addAll(candidatos, "CANTAR", "CANTA", "CONTAR");
        runTests(palabra, candidatos);

    }

    public static void runTests(String palabraBase, List<String> candidatos) {
        DistanciaPalabras metodos = new DistanciaPalabras();

        for (String palabraObjetivo : candidatos) {
            int nivel = 0;
            int diferenciaMinima = Integer.MAX_VALUE;
            int diferenciaActual = 0;

            int resutado = metodos.distanciaEntreDos(
                palabraBase,
                palabraObjetivo,
                nivel,
                diferenciaMinima,
                diferenciaActual
            );

            System.out.println(
                "La distancia entre las palabras " + palabraBase + " y " +
                palabraObjetivo + " es de " + resutado
            );
        }

    }
}
