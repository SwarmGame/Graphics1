package server;

import commonlib.gameObjects.Particle;
import commonlib.gameObjects.Queen;
import commonlib.gameObjects.Swarm;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/10/12
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player
{
    private String name;
    private String password;
    private Swarm swarm;
    private int moveX, moveY;
    private final int NUM_PARTICLES = 2;

    public void setMoveCommand(int x, int y)
    {
        moveX = x;
        moveY = y;
    }

    public Swarm getSwarm()
    {
        return swarm;
    }

    public Player(String name, String password, int startingX, int startingY)
    {
        this.name = name;
        this.password = password;
        swarm = new Swarm(new Queen(startingX, startingY));
        for(int x=0; x<NUM_PARTICLES; x++)
        {
            swarm.addParticle(new Particle(startingX + randParticleDistance(), startingY + randParticleDistance()));
        }
    }

    //returns an int between -50 and 50
    private int randParticleDistance()
    {
        return (int)(Math.random()*100 - 50);
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public int getMoveX()
    {
        return moveX;
    }

    public int getMoveY()
    {
        return moveY;
    }
}
