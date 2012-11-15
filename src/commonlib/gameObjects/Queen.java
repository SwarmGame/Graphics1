package commonlib.gameObjects;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 11/13/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Queen
{
    private int x;
    private int y;

    // Default constructor is required by Cryonet library
    public Queen(){}

    public Queen(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
