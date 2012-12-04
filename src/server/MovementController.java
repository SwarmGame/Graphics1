package server;

import commonlib.gameObjects.Map;
import commonlib.gameObjects.Particle;
import commonlib.gameObjects.Queen;
import commonlib.gameObjects.Swarm;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 11/15/12
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MovementController
{

    public void moveSwarm(Player player)
    {
        Swarm swarm = player.getSwarm();
        int dirX = player.getMoveX();
        int dirY = player.getMoveY();

        moveQueen(swarm, dirX, dirY);
        moveParticles(swarm);
    }

    private void moveQueen(Swarm swarm, int dirX, int dirY)
    {
        Queen queen = swarm.getQueen();
        int posX = queen.getX() + dirX;
        int posY = queen.getY() + dirY;

        //The queen shouldn't move off the map
        if(posX >= Queen.RADIUS && posX <= (Map.WIDTH - Queen.RADIUS))
        {
            queen.setX(posX);
        }
        if(posY >= Queen.RADIUS && posY <= (Map.HEIGHT - Queen.RADIUS))
        {
            queen.setY(posY);
        }
    }

    private void moveParticles(Swarm swarm)
    {
        Queen queen = swarm.getQueen();
        for(Particle particle : swarm.getParticles())
        {
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
            if(totalVelocity > Particle.MAX_SPEED)
            {
                velocityX = velocityX * Particle.MAX_SPEED / totalVelocity;
                velocityY = velocityY * Particle.MAX_SPEED / totalVelocity;
            }

            particle.setVelocityX(velocityX);
            particle.setVelocityY(velocityY);

            particle.setX(particle.getX() + particle.getVelocityX());
            particle.setY(particle.getY() + particle.getVelocityY());

        }
    }
}
