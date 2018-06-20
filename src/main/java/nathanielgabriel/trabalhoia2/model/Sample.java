package nathanielgabriel.trabalhoia2.model;

import java.util.Map;

public class Sample {

    private final Map<Variable, Boolean> values;

    public Sample(Map<Variable, Boolean> values) {
        this.values = values;
    }

    public Map<Variable, Boolean> getValues() {
        return values;
    }
}
