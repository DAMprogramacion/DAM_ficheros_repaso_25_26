package ejemplo1;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ejemplo1 {
    public static void main(String[] args) {
        //definir el path de entrada
        Path inPath = Path.of("ficheros/ejempl1.txt");
        String contenido = "";
        try {
            contenido = Files.readString(inPath);
        } catch (IOException e) {
            System.err.println(e);
        }
        //probamos lo leído
       // System.out.println(contenido);
        String[] palabras = contenido.split("[\s+\n]");
        List<String> palabrasLimpias = new ArrayList<>();
        for (String palabra : palabras)
            palabrasLimpias.add(palabra.replaceAll("[,\\.;]", ""));
        System.out.println(palabrasLimpias);
        System.out.printf("Nº palabras leídas: %d%n%n", palabras.length);
        String[] preposicionesEspañol = {
        "a", "ante", "bajo", "cabe", "con", "contra", "de", "desde",
                "durante", "en", "entre", "hacia", "hasta", "mediante", "para",
                "por", "según", "sin", "so", "sobre", "tras", "versus", "vía"};
        int contadorPreposiciones = 0;
        for (String preposicion : preposicionesEspañol) {
            int frecuencia = Collections.frequency(palabrasLimpias, preposicion);
            System.out.printf("Nº repeticiones de %s: %d%n", preposicion, frecuencia);
            contadorPreposiciones += frecuencia;
        }
        System.out.printf("%nNº TOTAL DE PREPOSICIONES: %d%n", contadorPreposiciones);

    }
}
