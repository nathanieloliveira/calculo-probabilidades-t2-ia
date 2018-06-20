package nathanielgabriel.trabalhoia2.model;

import java.util.List;

public class Database {

    private final List<Variable> variables;
    private final List<Sample> samples;

    public Database(List<Variable> variables, List<Sample> samples) {
        this.variables = variables;
        this.samples = samples;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public List<Sample> getSamples() {
        return samples;
    }
}
