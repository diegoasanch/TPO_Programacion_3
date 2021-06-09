import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import CalculaDistancia.DistanciaPalabras;

public class App {
    public static void main(String[] args) {
        System.out.println("\n------- TEST 1 -------");
        List<String> candidatos = new ArrayList<>();
        candidatos.add("CARRO");
        runTests("CARA", candidatos);

        System.out.println("\n------- TEST 2 -------");
        candidatos.clear();
        candidatos.add("PATRON");
        runTests("PADRON", candidatos);

        System.out.println("\n------- TEST 3 -------");
        candidatos.clear();
        Collections.addAll(candidatos, "CANTAR", "CANTA", "CONTAR");
        runTests("CANTOR", candidatos);
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
