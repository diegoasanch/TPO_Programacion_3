import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import PalabraDistancias.Distancia;
import PalabraDistancias.MejorDistancia;

public class DistanciaImplementacion implements Distancia {

    private Scanner scan;

    public DistanciaImplementacion() {
        scan = new Scanner(System.in);
    }

    @Override
    public MejorDistancia calcularDistancia(String origen, Vector<String> candidatas) {
        MejorDistancia resultado = new MejorDistancia();
        resultado.distancia = Integer.MAX_VALUE; // Mejor distancia inicial alta
        int resultadoComparacion;
        int nivel, diferenciaActual;

        for (String candidata : candidatas) {
            nivel = diferenciaActual = 0;
            resultadoComparacion = distanciaEntreDos(origen, candidata, nivel, resultado.distancia, diferenciaActual);

            if (resultadoComparacion < resultado.distancia) {
                resultado.distancia = resultadoComparacion;
                resultado.palabra = candidata;
            }
        }
        return resultado;
    }

    @Override
    public String obtenerOrigenPorPantalla() {
        System.out.print("> Ingrese la palabra origen: ");
        return scan.nextLine();
    }

    @Override
    public Vector<String> obtenerCandidatasPorPantalla(){
        System.out.println(
            "\nA continuacion ingrese las palabras candidatas. Ingrese alguno de los " +
            "siguientes [\"\", \"fin\", \"-1\"] para finalizar. "
        );
        List<String> salida = new ArrayList<String>();
        Collections.addAll(salida ,"", "fin", "-1");

        String palabra;
        Vector<String> resultado = new Vector<String>();

        System.out.print("> Ingrese una candidata: ");
        palabra = scan.nextLine();
        while (!salida.contains(palabra.toLowerCase())) {
            resultado.add(palabra);
            System.out.print("> Ingrese una candidata: ");
            palabra = scan.nextLine();
        }
        return resultado;
    }

    private int distanciaEntreDos(
        String base,
        String objetivo,
        int nivel,
        int diferenciaMinima,
        int diferenciaActual
    ) {
        if (nivel >= Math.max(base.length(), objetivo.length())) // Caso base
            return diferenciaActual;
        else if (caracterEsIgual(base, objetivo, nivel)) // Pasamos al siguiente nivel sin modificar
            return distanciaEntreDos(base, objetivo, nivel+1, diferenciaMinima, diferenciaActual);
        else if (diferenciaActual+1 >= diferenciaMinima) // Poda
            return diferenciaMinima;
        else {
            diferenciaActual++;

            if (nivel < base.length()) {
                String baseEliminando = eliminaCaracter(base, nivel);
                int difEliminando = distanciaEntreDos(baseEliminando, objetivo, nivel, diferenciaMinima, diferenciaActual);
                diferenciaMinima = Math.min(diferenciaMinima, difEliminando);
            }
            if (nivel < objetivo.length()) {
                String baseInsertando = insertaCaracter(base, objetivo, nivel);
                int difInsertando = distanciaEntreDos(baseInsertando, objetivo, nivel+1, diferenciaMinima, diferenciaActual);
                diferenciaMinima = Math.min(diferenciaMinima, difInsertando);
            }
            if (nivel < base.length() && nivel < objetivo.length()) {
                String baseReemplazando = reemplazaCaracter(base, objetivo, nivel);
                int difReemplazando = distanciaEntreDos(baseReemplazando, objetivo, nivel+1, diferenciaMinima, diferenciaActual);
                diferenciaMinima = Math.min(diferenciaMinima, difReemplazando);
            }
            return diferenciaMinima;
        }
    }

    private boolean caracterEsIgual(String cadena1, String cadena2, int indice) {
        if (indice < Math.min(cadena1.length(), cadena2.length()))
            return cadena1.charAt(indice) == cadena2.charAt(indice);
        return false;
    }

    private String reemplazaCaracter(String base, String objetivo, int indice) {
        return base.substring(0, indice) + objetivo.charAt(indice) + base.substring(indiceSeguro(base, indice+1), base.length());
    }

    private String eliminaCaracter(String base, int indice) {
        return base.substring(0, indice) + base.substring(indiceSeguro(base, indice+1), base.length());
    }

    private String insertaCaracter(String base, String objetivo, int indice) {
        if (indice < base.length())
            return base.substring(0, indice) + objetivo.charAt(indice) + base.substring(indice, base.length());
        return base + objetivo.charAt(indice);
    }

    private int indiceSeguro(String palabra, int indice) {
        return Math.min(palabra.length(), indice);
    }
}
