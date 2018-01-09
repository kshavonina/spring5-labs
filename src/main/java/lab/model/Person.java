package lab.model;

import java.util.List;

public interface Person {

    Country getCountry();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    boolean isBroke();

    List<String> getContacts();

    String getName();

    default void sayHello(Person person) {
        System.out.printf("Hello, %s, I`m %s%n", person.getName(), getName());
    }

    default boolean hasRight(String right) {
        return true;
    }
}