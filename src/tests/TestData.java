package tests;

import java.util.Collections;
import java.util.Vector;

public class TestData {
    private String palabraBase;
    private String mejorCandidata;
    private Vector<String> candidatas;

    public TestData(String palabraBase, String mejorCandidata, String ...candidatas) {
        this.palabraBase = palabraBase;
        this.mejorCandidata = mejorCandidata;
        this.candidatas = new Vector<>();
        Collections.addAll(this.candidatas, candidatas);
    }

    // Para cuando solo hay una candidata
    public TestData(String palabraBase, String mejorCandidata) {
        this.palabraBase = palabraBase;
        this.mejorCandidata = mejorCandidata;
        this.candidatas = new Vector<>();
    }

    public String getPalabraBase() {
        return palabraBase;
    }

    public Vector<String> getCandidatasMejorPrimera() {
        Vector<String> resultado = new Vector<>();
        resultado.add(mejorCandidata);
        copiaVector(candidatas, resultado);
        return resultado;
    }

    public Vector<String> getCandidatasMejorUltima() {
        Vector<String> resultado = new Vector<>();
        copiaVector(candidatas, resultado);
        resultado.add(mejorCandidata);
        return resultado;
    }

    public Vector<String> getCandidatasMejorMedio() {
        Vector<String> resultado = new Vector<>();
        copiaVector(candidatas, resultado);
        resultado.insertElementAt(mejorCandidata, resultado.size()/2);
        return resultado;
    }

    private void copiaVector(Vector<String> desde, Vector<String> hacia) {
        for (String palabra : desde)
            hacia.add(palabra);
    }
}
