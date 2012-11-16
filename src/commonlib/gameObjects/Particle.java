package commonlib.gameObjects;

/**
 * Particle
 * User: Derek
 * Date: 11/8/12
 * Time: 10:11 AM
 * Stores information for particles that move around their queen
 */
public class Particle
{
    public static final int RADIUS = 2;
    public static final int MAX_SPEED = 3;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;

    // Default constructor is required by Cryonet library
    public Particle ()
    {}

    public Particle(double x, double y)
    {
        this.x = x;
        this.y = y;
        velocityX = 1;
        velocityY = 0;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getVelocityX()
    {
        return velocityX;
    }

    public void setVelocityX(double velocityX)
    {
        this.velocityX = velocityX;
    }

    public double getVelocityY()
    {
        return velocityY;
    }

    public void setVelocityY(double velocityY)
    {
        this.velocityY = velocityY;
    }
}
