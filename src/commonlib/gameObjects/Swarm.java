package commonlib.gameObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Swarm
 * User: Derek
 * Date: 11/8/12
 * Time: 10:11 AM
 * Stores information about the swarm, which contains a single queen and any number of particles
 */
public class Swarm
{
    private Queen queen;
    private List<Particle> particles;

    // Default constructor is required by Cryonet library
    public Swarm(){}

    public Swarm(Queen queen)
    {
        this.queen = queen;
        particles = new ArrayList<Particle>();
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
