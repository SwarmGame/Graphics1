package commonlib.gameObjects;

/**
 * Queen
 * User: Derek
 * Date: 11/13/12
 * Time: 3:18 PM
 * Stores information about the Queen, which is controlled directly by the player
 */
public class Queen
{
    public static final int RADIUS = 10;
    public static final int SPEED = 2;
    public static final int MAX_HP = 20;
    private int x;
    private int y;
    private int hitPoints;

    // Default constructor is required by Cryonet library
    public Queen(){}

    public Queen(int x, int y)
    {
        this.x = x;
        this.y = y;
        hitPoints = 20;
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

    public int getHitPoints()
    {
        return hitPoints;
    }

    public void takeDamage(int damage)
    {
        hitPoints -= damage;
    }
}
