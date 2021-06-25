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

        pruebas.add(new TestData("COLOR", "CALOR"));
        pruebas.add(new TestData("TECLADO", "TECLAS", "TABLERO"));
        pruebas.add(new TestData("TAZA", "CAZA", "TAZON", "TARZAN"));
        pruebas.add(new TestData("PILA", "PALA", "POLAR", "POLO", "PALO"));
        pruebas.add(new TestData("CARA", "PARA", "CARTAS", "CURAS", "CARTON", "CARROS"));
        pruebas.add(new TestData("CANTOR", "CANTAR", "CANTA", "CONTAR", "CANTANTE", "CAJERO", "CORTINA"));
        pruebas.add(new TestData("PADRON", "PATRON", "PUERTA", "PADRE", "LADRON", "PIEDRA", "PADRES", "PUENTE"));
        pruebas.add(new TestData("REGLA", "REGLAS", "RELOJ", "RENGLON", "RASTRO", "RELACION", "ROTO", "RATON", "RUIDO"));
        pruebas.add(new TestData("TESLA", "MESA", "TESTEAR", "ELSA", "BALZA", "TESIS", "TERMAS", "TESTIFICAR", "TEXAS", "TEMBLAR"));
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
