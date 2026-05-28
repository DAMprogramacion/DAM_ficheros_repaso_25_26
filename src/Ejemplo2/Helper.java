package Ejemplo2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Helper {
    public static List<Person> getPeople(String sPath) {
        List<Person> people = new ArrayList<>();
        Path path = Paths.get(sPath); //otra forma distinta a Path.of()
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] tokens =  line.split(",");
                try {
                    int id = Integer.parseInt(tokens[0].trim());
                    String firstName = tokens[1].trim();
                    String lastName = tokens[2].trim();
                    String email = tokens[3].trim();
                    Person person = new Person(email, lastName, firstName, id);
                    people.add(person);
                } catch (NumberFormatException e) {
                    System.err.println("Formato numérico no correcto");;
                }
            }
        } catch (IOException e) {
            System.err.println("Fichero no encontrado");;
        }
        return people;
    }
    public static Optional<Person> getPersonByEmail(List<Person> people, String email) {
        return people.stream().
                filter(person -> person.getEmail().equalsIgnoreCase(email)).
                findFirst();
    }
    public static List<Person> getPeopleUsingScanner(String sPath) {
        List<Person> people = new ArrayList<>();
        Path path = Paths.get(sPath); //otra forma distinta a Path.of()
        StringBuilder stringBuilder = new StringBuilder();
        int countLines = 0;
        stringBuilder.append("Leido fichero: ").append(path.getFileName()).append('\n');
        try {
            stringBuilder.append("Bytes leidos: ").append(Files.size(path)).append('\n');
            Scanner sc = new Scanner(path);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                countLines++;
                String[] tokens =  line.split(",");
                try {
                    int id = Integer.parseInt(tokens[0].trim());
                    String firstName = tokens[1].trim();
                    String lastName = tokens[2].trim();
                    String email = tokens[3].trim();
                    Person person = new Person(email, lastName, firstName, id);
                    people.add(person);
                } catch (NumberFormatException e) {
                    System.err.println("Formato numérico no correcto");;
                }
            }
        } catch (IOException e) {
            System.err.println("Fichero no encontrado");;
        }
        stringBuilder.append("Nº líneas leídas: ").append(countLines).append('\n');
        stringBuilder.append("Nº objetos creados: ").append(people.size()).append('\n');
        stringBuilder.append("Fecha del report: ").append(LocalDate.now().
                format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(stringBuilder.toString());
        getReport("ficheros/report.txt", stringBuilder.toString());
        return people;
    }


public static  Optional<Person> findByEmail(String filePath, String email) {

            try {
                return Files.lines(Path.of(filePath))
                        .filter(line -> line.matches("^[0-9]+,.+"))
                        .map(line -> line.split(","))
                        .filter(fields -> fields.length == 4)
                        .map(fields -> new Person(
                                fields[3].trim(),
                                fields[2].trim(),
                                fields[1].trim(),
                                Integer.parseInt(fields[0].trim())
                        ))
                        .filter(person -> person.getEmail().equalsIgnoreCase(email))
                        .findFirst();

            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    public static void getReport(String sOPath, String content) {
        Path path = Path.of(sOPath);
        try {
            Files.writeString(path, content);
            System.out.printf("Escrito report en el fichero %s de %d bytes%n",
                    path.getFileName(), Files.size(path));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}



