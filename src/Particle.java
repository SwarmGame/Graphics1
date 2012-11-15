/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 11/8/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Particle
{
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;


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

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}
