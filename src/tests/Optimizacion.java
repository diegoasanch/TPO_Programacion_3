package tests;

import PalabraDistancias.Distancia;
import implementaciones.DistanciaImplementacion;
import implementaciones.DistanciaImplementacionSinPoda;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class Optimizacion {

    private static Distancia optimizado;
    private static Distancia noOptimizado;
    private static List<TestData> pruebas;

    public static void main(String[] args) {

        optimizado = new DistanciaImplementacion();
        noOptimizado = new DistanciaImplementacionSinPoda();
        Resultado resultado = new Resultado();

        cargaCandidatas();
        testMenorDistanciaPrimero(resultado);
        testMenorDistanciaMedio(resultado);
        testMenorDistanciaUltimo(resultado);

        resultado.printResultados();
    }

    private static void cargaCandidatas() {
        pruebas = new ArrayList<>();

        pruebas.add(new TestData("ARMAR", "AMAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR", "HABLAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR", "HABLAR", "LANZAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR", "HABLAR", "LANZAR", "LIMPIAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR", "HABLAR", "LANZAR", "LIMPIAR", "CORTAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR", "HABLAR", "LANZAR", "LIMPIAR", "CORTAR", "CALAMAR"));
        pruebas.add(new TestData("ARMAR", "AMAR", "ALTAMAR", "ARRIBA", "ALZAR", "HABLAR", "LANZAR", "LIMPIAR", "CORTAR", "CALAMAR", "CALZAR"));
    }

    private static void testMenorDistanciaPrimero(Resultado res) {
        String base;
        Vector<String> candidatas;
        float[] testResult = new float[2];

        for (TestData prueba : pruebas) {
            base = prueba.getPalabraBase();
            candidatas = prueba.getCandidatasMejorPrimera();
            runTests(base, candidatas, testResult);

            res.addPrimeroNoOp(testResult[0]);
            res.addPrimeroOp(testResult[1]);
        }
    }

    private static void testMenorDistanciaUltimo(Resultado res) {
        String base;
        Vector<String> candidatas;
        float[] testResult = new float[2];

        for (TestData prueba : pruebas) {
            base = prueba.getPalabraBase();
            candidatas = prueba.getCandidatasMejorUltima();
            runTests(base, candidatas, testResult);

            res.addUltimoNoOp(testResult[0]);
            res.addUltimoOp(testResult[1]);        }
    }

    private static void testMenorDistanciaMedio(Resultado res) {
        String base;
        Vector<String> candidatas;
        float[] testResult = new float[2];

        for (TestData prueba : pruebas) {
            base = prueba.getPalabraBase();
            candidatas = prueba.getCandidatasMejorMedio();
            runTests(base, candidatas, testResult);

            res.addMedioNoOp(testResult[0]);
            res.addMedioOp(testResult[1]);
        }
    }

    private static void runTests(String palabraBase, Vector<String> candidatas, float[] res) {
        long startNoOp = System.nanoTime();
        noOptimizado.calcularDistancia(palabraBase, candidatas);
        long endNoOp = System.nanoTime();

        long startOp = System.nanoTime();
        optimizado.calcularDistancia(palabraBase, candidatas);
        long endOp = System.nanoTime();

        res[0] = getMillis(startNoOp, endNoOp);
        res[1] = getMillis(startOp, endOp);
    }

    private static float getMillis(long start, long end) {
        return ((float)(end - start) / 1_000_000f);
    }
}
