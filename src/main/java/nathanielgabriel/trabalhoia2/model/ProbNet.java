package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProbNet
{
    @JsonProperty("AdditionalProperties")
    private Property[] AdditionalProperties;

    @JsonProperty("Variables")
    private Variable[] Variables;

    @JsonProperty("Links")
    private Link[] Links;

    @JsonProperty("Comment")
    private Comment Comment;

    private String type;

    @JsonProperty("Potentials")
    private Potential[] Potentials;

    public Variable[] getVariables ()
    {
        return Variables;
    }

    public void setVariables (Variable[] Variables)
    {
        this.Variables = Variables;
    }

    public Link[] getLinks ()
    {
        return Links;
    }

    public void setLinks (Link[] Links)
    {
        this.Links = Links;
    }

    public Comment getComment ()
    {
        return Comment;
    }

    public void setComment (Comment Comment)
    {
        this.Comment = Comment;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public Potential[] getPotentials ()
    {
        return Potentials;
    }

    public void setPotentials (Potential[] Potentials)
    {
        this.Potentials = Potentials;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [AdditionalProperties = "+AdditionalProperties+", Variables = "+Variables+", Links = "+Links+", Comment = "+Comment+", type = "+type+", Potentials = "+Potentials+"]";
    }
}
