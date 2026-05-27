package Ejemplo2;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Helper.getPeopleUsingScanner("ficheros/people.csv");
        people.forEach(System.out::println);
        System.out.println("====email que no existe");
        String email = "kdjflksdjf";
        Optional<Person> person = Helper.getPersonByEmail(people, email);
        if (person.isPresent())
            System.out.println(person.get());
        else
            System.out.printf("No existe persona con email %s%n", email);
        email = "jdybald3@bloglovin.com";
        person = Helper.getPersonByEmail(people, email);
        if (person.isPresent())
            System.out.println(person.get());
        else
            System.out.printf("No existe persona con email %s%n", email);
        System.out.println("==========================");
        person = Helper.findByEmail("ficheros/people.csv", email);
        if (person.isPresent())
            System.out.println(person.get());
        else
            System.out.printf("No existe persona con email %s%n", email);
    }
}
