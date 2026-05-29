package Ejemplo4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//cityName,countryName,latitude,longitude
public class Helper {
    public static Map<String, List<City>> getCities(String sPath) {
        Map<String, List<City>> cities = new HashMap<>();
        Path inPath = Paths.get(sPath);
        try {
            List<String> lines = Files.readAllLines(inPath);
            //List<City> cityObjects = new ArrayList<>();
            for (String line : lines) {
                String[] tokens = line.split(",");
                String nameCity    = tokens[0].trim();
                String nameCountry = tokens[1].trim();
                double latitude    = Double.parseDouble(tokens[2]);
                double longitude   = Double.parseDouble(tokens[3]);
                City city = new City(nameCity,nameCountry, latitude, longitude);
             //   cityObjects.add(city);
                cities.computeIfAbsent(
                        city.nameCountry(),
                        k -> new ArrayList<>()
                ).add(city);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

     public static void writeCountryFiles(Map<String, List<City>> countryMap) {
            for (Map.Entry<String, List<City>> entry : countryMap.entrySet()) {
                String country = entry.getKey();
                List<City> cities = entry.getValue();

                // Nombre del fichero: españa.txt, francia.txt, etc.
                Path filePath = Paths.get("ficheros/" + country + ".txt");

                // Convertimos cada ciudad a texto
                List<String> lines = cities.stream()
                        .map(City::toString)
                        .toList();

                // Escribir el fichero
                try {
                    Files.write(filePath, lines, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    public static void main(String[] args) {
        Map<String, List<City>> map = getCities("ficheros/cities.csv");
        for (String country : map.keySet())
            System.out.printf("%s -> %s%n" , country, map.get(country));
        writeCountryFiles(map);
    }
}
