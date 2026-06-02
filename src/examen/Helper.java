package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Mobile> getMobiles (String sPath){
        List<Mobile> mobiles = new ArrayList<>();
        Path path = Path.of(sPath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines){
                String[] tokens = line.split(",");
                if (tokens.length == 5) {
                    String marca = tokens[0];
                    String modelo = tokens[1];
                    String so = tokens[2];
                    try {
                        int fecha = Integer.parseInt(tokens[3]);
                        double precio = Double.parseDouble(tokens[3]);
                        Mobile mobile = new Mobile(marca, modelo, so, fecha, precio);
                        mobiles.add(mobile);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return mobiles;
    }
    public static void writeMobileByBrand(String brand, List<Mobile> mobiles){
        String sPath = "ficheros/" + brand + ".csv";
        Path path    = Path.of(sPath);
        StringBuilder stringBuilder = new StringBuilder();
        for (Mobile mobile : mobiles){
            if (mobile.mobileBrand().equalsIgnoreCase(brand))
               // System.out.println(mobile);
                stringBuilder.append(mobile.toString()).append('\n');
        }
        try {
            Files.writeString(path, stringBuilder.toString(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        //getMobiles("ficheros/mobiles.csv").forEach(System.out::println);
        writeMobileByBrand("samsung", getMobiles("ficheros/mobiles.csv"));
    }
}
