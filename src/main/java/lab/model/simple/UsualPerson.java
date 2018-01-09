package lab.model.simple;

import lab.model.Person;
import lombok.Value;

import java.util.List;

@Value
public class UsualPerson implements Person {

    private int id;

    private String name;

    private int age;
    private float height;
    private boolean isProgrammer;
    private boolean broke;

    private SimpleCountry country;

    private List<String> contacts;
}