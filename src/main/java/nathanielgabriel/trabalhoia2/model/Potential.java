package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Potential
{
    enum PotentialType {
        Uniform,
        Table
    }

    @JsonProperty("Variables")
    private Variable[] Variables;

    private String role;

    @JsonProperty("type")
    private PotentialType type;

    @JsonProperty("Values")
    private String values;

    public Variable[] getVariables ()
    {
        return Variables;
    }

    public void setVariables (Variable[] Variables)
    {
        this.Variables = Variables;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public void setValues(Table table) {
        List<Variable> variables = new ArrayList<>();
        variables.add(table.getMain());
        variables.addAll(table.getParents());
        Variable[] vars = new Variable[variables.size()];
        variables.toArray(vars);
        setVariables(vars);

        type = PotentialType.Table;

        StringBuilder builder = new StringBuilder();
        double first = table.getProbabilities().get(0);
        builder.append(first);
        for (int i = 1; i < table.getProbabilities().size(); i++) {
            double d = table.getProbabilities().get(i);
            builder.append(" ");
            builder.append(d);
        }
        values = builder.toString();
    }

    public String getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "Potential{" +
                "Variables=" + Arrays.toString(Variables) +
                ", type=" + type +
                ", values='" + values + '\'' +
                '}';
    }
}
