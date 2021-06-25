package tests;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
    private List<Float> primeroOptimizado;
    private List<Float> primeroNoOptimizado;

    private List<Float> ultimoOptimizado;
    private List<Float> ultimoNoOptimizado;

    private List<Float> medioOptimizado;
    private List<Float> medioNoOptimizado;

    public Resultado() {
        primeroOptimizado = new ArrayList<>();
        primeroNoOptimizado = new ArrayList<>();
        ultimoOptimizado = new ArrayList<>();
        ultimoNoOptimizado = new ArrayList<>();
        medioOptimizado = new ArrayList<>();
        medioNoOptimizado = new ArrayList<>();
    }

    public void addPrimeroOp(float resultado) {
        primeroOptimizado.add(resultado);
    }
    public void addPrimeroNoOp(float resultado) {
        primeroNoOptimizado.add(resultado);
    }
    public void addUltimoOp(float resultado) {
        ultimoOptimizado.add(resultado);
    }
    public void addUltimoNoOp(float resultado) {
        ultimoNoOptimizado.add(resultado);
    }
    public void addMedioOp(float resultado) {
        medioOptimizado.add(resultado);
    }
    public void addMedioNoOp(float resultado) {
        medioNoOptimizado.add(resultado);
    }

    public void printResultados() {
        System.out.println("Comparacion del algoritmo calcularDistancia con y sin poda.");
        System.out.println("Filas: Posicion mejor candidata y algoritmo  -  Columnas: Cantidad de candidatas");
        System.out.println("Cada celda: tiempo de ejecucion en ms (milisegundos)");

        System.out.format("%-22s", " ");
        for (var i = 0; i < primeroNoOptimizado.size(); i++) {
            System.out.format("%12d", i+1);
        }
        System.out.println();

        List<List<Float>> tabla = new ArrayList<>();

        tabla.add(primeroNoOptimizado);
        tabla.add(primeroOptimizado);
        tabla.add(medioNoOptimizado);
        tabla.add(medioOptimizado);
        tabla.add(ultimoNoOptimizado);
        tabla.add(ultimoOptimizado);

        String[] titulos = {
            "Primera NO optimizado",
            "Primera optimizado",
            "Mitad NO optimizado",
            "Mitad optimizado",
            "Ultima NO optimizado",
            "Ultima optimizado",
        };

        var i = 0;
        for (List<Float> linea : tabla) {
            System.out.format("%-22s    ", titulos[i++]);
            for (float res : linea) {
                System.out.format("%12f", res);
            }
            System.out.println();
        }
    }
}
