package nathanielgabriel.trabalhoia2.model;

public class Comment
{
    private String content;

    private String showWhenOpeningNetwork;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getShowWhenOpeningNetwork ()
    {
        return showWhenOpeningNetwork;
    }

    public void setShowWhenOpeningNetwork (String showWhenOpeningNetwork)
    {
        this.showWhenOpeningNetwork = showWhenOpeningNetwork;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", showWhenOpeningNetwork = "+showWhenOpeningNetwork+"]";
    }
}
