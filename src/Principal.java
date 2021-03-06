import java.util.Vector;
import PalabraDistancias.MejorDistancia;
import implementaciones.DistanciaImplementacion;

public class Principal {
    public static void main(String[] args) {
        imprimirHeader();
        DistanciaImplementacion distanciaImp = new DistanciaImplementacion();

        String origen = distanciaImp.obtenerOrigenPorPantalla();
        Vector<String> candidatas = distanciaImp.obtenerCandidatasPorPantalla();

        if (candidatas.isEmpty()) {
            System.out.println("\nNo ingreso ninguna palabra candidata :(");
        }
        else {
            MejorDistancia resultado = distanciaImp.calcularDistancia(origen, candidatas);
            imprimirResultado(resultado);
        }
    }

    private static void imprimirResultado(MejorDistancia resultado) {
        System.out.println("\nMejor Palabra Obtenida: " + resultado.palabra + " con distancia: " + resultado.distancia);
    }

    private static void imprimirHeader() {
        System.out.println("TPO Programación III - 2021 1C\nDeterminar palabra a menor distancia.\n");
    }
}
