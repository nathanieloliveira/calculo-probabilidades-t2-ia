package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InferenceOptions
{
    @JsonProperty("MulticriteriaOptions")
    private MulticriteriaOptions MulticriteriaOptions;

    public MulticriteriaOptions getMulticriteriaOptions ()
    {
        return MulticriteriaOptions;
    }

    public void setMulticriteriaOptions (MulticriteriaOptions MulticriteriaOptions)
    {
        this.MulticriteriaOptions = MulticriteriaOptions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MulticriteriaOptions = "+MulticriteriaOptions+"]";
    }
}
