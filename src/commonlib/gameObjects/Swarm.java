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
            double accelerateX = queenX - particle.getX();
            double accelerateY = queenY - particle.getY();
            double velocityX = particle.getVelocityX();
            double velocityY = particle.getVelocityY();

            double distance = Math.sqrt(accelerateX*accelerateX + accelerateY*accelerateY);

            //normalize and scale down acceleration
            accelerateX = accelerateX/(5*distance);
            accelerateY = accelerateY/(5*distance);

//            if(accelerateX >= 5)
//            {
//                accelerateX = 5;
//            }
//
//            if(accelerateY >= 5)
//            {
//                accelerateY = 5;
//            }

//            if(distance <= 20)
//            {
//                accelerateX *= -1;
//                accelerateY *= -1;
//            }

            velocityX += accelerateX;
            velocityY += accelerateY;

            double totalVelocity = Math.sqrt(velocityX*velocityX + velocityY*velocityY);
            if(totalVelocity > 3)
            {
                velocityX = velocityX*3/totalVelocity;
                velocityY = velocityY*3/totalVelocity;
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
