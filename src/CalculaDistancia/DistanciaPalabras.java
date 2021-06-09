package CalculaDistancia;

public class DistanciaPalabras {
    public int distanciaEntreDos(
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



    public void testMethods() {
        String base = "CARA";
        String destino = "CARRO";
        int indice = 3;
        String sonIguales = caracterEsIgual(base, destino, indice) ? "SI" : "NO";
        String reemplazado = reemplazaCaracter(base, destino, indice);
        String eliminado = eliminaCaracter(base, indice);
        String insertado = insertaCaracter(base, destino, indice);

        System.out.println("BASE: " + base + "\nDESTINO: " + destino);
        System.out.println("caracterEsIgual en " + indice + ": " + sonIguales);
        System.out.println("Remplaza en " + indice + ": " + reemplazado);
        System.out.println("Elimina en " + indice + ": " + eliminado);
        System.out.println("Inserta en " + indice + ": " + insertado);
    }

}
