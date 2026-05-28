package Ejemplo3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class Ejemplo3 {
    public static void main(String[] args) {
        Path inPath = Path.of("ficheros/java.png");
        Path outPath1 = Path.of("ficheros/java_bakc_1.png");
        Path outPath2 = Path.of("ficheros/java_back_2.png");

        try {
            byte[] bytes = Files.readAllBytes(inPath);
            //usando Write y Read
            Files.write(outPath1, bytes, StandardOpenOption.CREATE);
            System.out.printf("Escrito fichero %s de tantos bytes %d%n",
                    outPath1.getFileName(), Files.size(outPath1));
            //usando copy
            Files.copy(inPath, outPath2, StandardCopyOption.REPLACE_EXISTING);
            System.out.printf("Escrito fichero %s de tantos bytes %d%n",
                    outPath2.getFileName(), Files.size(outPath2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
