package commonlib;

import commonlib.gameObjects.Swarm;

/**
 * Game Situation Serialized
 * User: ALEXANDER
 * Date: 11/15/12
 * Time: 7:31 PM
 * Stores the information to send from the server to the client
 */
public class GameSituationSerialized
{
    private Swarm swarm1;
    private Swarm swarm2;
    private String winner;

    public Swarm getSwarm1()
    {
        return swarm1;
    }

    public void setSwarm1(Swarm swarm1)
    {
        this.swarm1 = swarm1;
    }

    public Swarm getSwarm2()
    {
        return swarm2;
    }

    public void setSwarm2(Swarm swarm2)
    {
        this.swarm2 = swarm2;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }
}
