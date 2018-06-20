package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Link
{
    @JsonProperty("Variables")
    private Variable[] Variable;

    private boolean directed;

    public Variable[] getVariable ()
    {
        return Variable;
    }

    public void setVariable (Variable[] Variable)
    {
        this.Variable = Variable;
    }

    public boolean getDirected ()
    {
        return directed;
    }

    public void setDirected (boolean directed)
    {
        this.directed = directed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Variable = "+Variable+", directed = "+directed+"]";
    }
}
