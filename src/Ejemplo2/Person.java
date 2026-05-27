package Ejemplo2;

import java.util.Objects;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private final String email;

    public Person(String email, String lastName, String firstName, int id) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(email.toLowerCase(),
                person.email.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", lastName, firstName);
    }
}
