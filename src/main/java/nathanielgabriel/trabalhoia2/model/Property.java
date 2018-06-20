package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {

    @JsonProperty("name")
    String name;

    @JsonProperty("value")
    String value;
}
