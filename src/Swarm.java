import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 11/8/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Swarm
{
    private Queen queen;
    private List<Particle> particles;

    public Swarm(Queen queen)
    {
        this.queen = queen;
        particles = new ArrayList<Particle>();
    }

    public void move(int x, int y)
    {
        queen.setX(queen.getX() + x);
        queen.setY(queen.getY() + y);

        moveParticles();
    }

    private void moveParticles()
    {
        for(Particle particle : particles)
        {
            particle.setX(particle.getX() + particle.getVelocityX());
            particle.setY(particle.getY() + particle.getVelocityY());

            double queenX = queen.getX();
            double queenY = queen.getY();
            double x = queenX - particle.getX();
            double y = queenY - particle.getY();
            double velocityX = particle.getVelocityX();
            double velocityY = particle.getVelocityY();

            double distance = Math.sqrt(x*x + y*y);
            x = x/2*distance;
            y = y/2*distance;

            velocityX += x;
            velocityY += y;

            velocityX = velocityX*distance/(30*Math.sqrt(velocityX*velocityX + velocityY*velocityY));
            velocityY = velocityY*distance/(30*Math.sqrt(velocityX*velocityX + velocityY*velocityY));

            particle.setVelocityX(velocityX);
            particle.setVelocityY(velocityY);
        }
    }

    public Queen getQueen()
    {
        return queen;
    }

    public List<Particle> getParticles()
    {
        return particles;
    }

    public void addParticle(Particle particle)
    {
        particles.add(particle);
    }
}
