package tests;

import java.util.Collections;
import java.util.Vector;

import PalabraDistancias.MejorDistancia;
import implementaciones.DistanciaImplementacion;

public class Test {
    public static void main(String[] args) {
        System.out.println("\n-------------- TEST 1 --------------");
        Vector<String> candidatos = new Vector<>();
        candidatos.add("CARRO");
        runTests("CARA", candidatos);

        System.out.println("\n-------------- TEST 2 --------------");
        candidatos.clear();
        candidatos.add("PATRON");
        runTests("PADRON", candidatos);

        System.out.println("\n-------------- TEST 3 --------------");
        candidatos.clear();
        Collections.addAll(candidatos, "CANTAR", "CANTA", "CONTAR");
        runTests("CANTOR", candidatos);
    }

    public static void runTests(String palabraBase, Vector<String> candidatas) {
        DistanciaImplementacion metodos = new DistanciaImplementacion();
        System.out.println("Palabra base \"" + palabraBase + "\"\nCandidatas:");

        for (String pal : candidatas)
            System.out.println("   - " + pal);

        MejorDistancia resultado = metodos.calcularDistancia(palabraBase, candidatas);
        System.out.println("Mejor palabra obtenida: " + resultado.palabra + " con distancia: " + resultado.distancia);
    }

}
