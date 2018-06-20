package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProbModelXML
{

    @JsonProperty("ProbNet")
    private ProbNet ProbNet;

    private String formatVersion;

    @JsonProperty("InferenceOptions")
    private InferenceOptions InferenceOptions;

    public ProbNet getProbNet ()
    {
        return ProbNet;
    }

    public void setProbNet (ProbNet ProbNet)
    {
        this.ProbNet = ProbNet;
    }

    public String getFormatVersion ()
    {
        return formatVersion;
    }

    public void setFormatVersion (String formatVersion)
    {
        this.formatVersion = formatVersion;
    }

    public InferenceOptions getInferenceOptions ()
    {
        return InferenceOptions;
    }

    public void setInferenceOptions (InferenceOptions InferenceOptions)
    {
        this.InferenceOptions = InferenceOptions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ProbNet = "+ProbNet+", formatVersion = "+formatVersion+", InferenceOptions = "+InferenceOptions+"]";
    }
}
