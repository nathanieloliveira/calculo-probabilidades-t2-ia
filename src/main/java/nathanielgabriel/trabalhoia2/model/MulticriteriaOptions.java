package nathanielgabriel.trabalhoia2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MulticriteriaOptions
{
    @JsonProperty("SelectedAnalysisType")
    private String SelectedAnalysisType;

    public String getSelectedAnalysisType ()
    {
        return SelectedAnalysisType;
    }

    public void setSelectedAnalysisType (String SelectedAnalysisType)
    {
        this.SelectedAnalysisType = SelectedAnalysisType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SelectedAnalysisType = "+SelectedAnalysisType+"]";
    }
}
