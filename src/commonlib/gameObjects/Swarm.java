package commonlib.gameObjects;

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
    private int MAX_SPEED = 3;

    // Default constructor is required by Cryonet library
    public Swarm(){}

    public Swarm(Queen queen)
    {
        this.queen = queen;
        particles = new ArrayList<Particle>();
    }

    public void move()
    {
        queen.move();
//        queen.setX(queen.getX() + x);
//        queen.setY(queen.getY() + y);

        moveParticles();
    }

    public void setDest(int dx, int dy)
    {
        queen.setDest(dx,dy);
    }

    private void moveParticles()
    {
        for(Particle particle : particles)
        {
            particle.setX(particle.getX() + particle.getVelocityX());
            particle.setY(particle.getY() + particle.getVelocityY());

            double queenX = queen.getX();
            double queenY = queen.getY();
            double accelerateX = queenX - particle.getX();
            double accelerateY = queenY - particle.getY();
            double velocityX = particle.getVelocityX();
            double velocityY = particle.getVelocityY();

            double distance = Math.sqrt(accelerateX*accelerateX + accelerateY*accelerateY);


            //normalize and scale down acceleration
            accelerateX = accelerateX/(5*distance);
            accelerateY = accelerateY/(5*distance);

            //add random acceleration
            accelerateX += Math.random()-.5;
            accelerateY += Math.random()-.5;


            velocityX += accelerateX;
            velocityY += accelerateY;

            //cap the total speed of the particles
            double totalVelocity = Math.sqrt(velocityX*velocityX + velocityY*velocityY);
            if(totalVelocity > MAX_SPEED)
            {
                velocityX = velocityX* MAX_SPEED /totalVelocity;
                velocityY = velocityY* MAX_SPEED /totalVelocity;
            }



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
