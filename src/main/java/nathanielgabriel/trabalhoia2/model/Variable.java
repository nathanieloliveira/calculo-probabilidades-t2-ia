package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Variable
{
    @JsonProperty(required = true)
    private String name;

    private String role;

    private String type;

    @JsonProperty("States")
    private State[] States;

    @JsonProperty("Coordinates")
    private Coordinates Coordinates;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public State[] getStates ()
    {
        return States;
    }

    public void setStates (State[] States)
    {
        this.States = States;
    }

    public Coordinates getCoordinates ()
    {
        return Coordinates;
    }

    public void setCoordinates (Coordinates Coordinates)
    {
        this.Coordinates = Coordinates;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }
}
