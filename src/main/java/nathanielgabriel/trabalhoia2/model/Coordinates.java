package nathanielgabriel.trabalhoia2.model;

public class Coordinates
{
    private int y;

    private int x;

    public int getY ()
    {
        return y;
    }

    public void setY (int y)
    {
        this.y = y;
    }

    public int getX ()
    {
        return x;
    }

    public void setX (int x)
    {
        this.x = x;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [y = "+y+", x = "+x+"]";
    }
}
