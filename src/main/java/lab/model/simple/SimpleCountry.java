package lab.model.simple;

import lab.model.Country;
import lombok.Value;

import java.io.Serializable;

@Value
public class SimpleCountry implements Country, Serializable {

    private int id;

    private String name;

    private String codeName;
}
