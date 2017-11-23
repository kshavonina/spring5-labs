package lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsualPerson implements Person {
    private int id;

    private String name;

    private Country country;

    private int age;
    private float height;
    private boolean isProgrammer;

    private List<String> contacts;
}