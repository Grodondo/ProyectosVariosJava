import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PalabraMasRepetida {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String frase1 = "El agUa es vital para la vida, así que debemos cuidar cada gota de agua";
        String frase2 = "El agua, limpia es esencial para nuestra salud, y proteger nuestras fuentes de agua debería ser una prioridad";

        // Limpiar las frases de puntuación y convertirlas a minúsculas
        frase1 = frase1.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        frase2 = frase2.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        // Dividir las frases en palabras
        String[] palabrasFrase1 = frase1.split(" ");
        String[] palabrasFrase2 = frase2.split(" ");

        // Crear un mapa para contar las palabras en ambas frases
        Map<String, Integer> conteoPalabras = new HashMap<>();

        // Contar palabras en la primera frase
        for (String palabra : palabrasFrase1) {
            conteoPalabras.put(palabra, conteoPalabras.getOrDefault(palabra, 0) + 1);
        }

        // Contar palabras en la segunda frase
        for (String palabra : palabrasFrase2) {
            conteoPalabras.put(palabra, conteoPalabras.getOrDefault(palabra, 0) + 1);
        }

        String palabraMasRepetida = null;
        int maxRepeticiones = 0;

        // Encontrar la palabra más repetida
        for (Map.Entry<String, Integer> entry : conteoPalabras.entrySet()) {
            if (entry.getValue() > maxRepeticiones) {
                palabraMasRepetida = entry.getKey();
                maxRepeticiones = entry.getValue();
            }
        }

        if (palabraMasRepetida != null) {
            System.out.println("La palabra más repetida es '" + palabraMasRepetida + "' con " + maxRepeticiones + " repeticiones.");
        } else {
            System.out.println("No se encontraron palabras repetidas.");
        }
    }
}