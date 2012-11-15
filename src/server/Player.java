package server;

import commonlib.gameObjects.Swarm;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/10/12
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    String name;
    String password;
    Swarm swarm;
    int score;
    int destX;
    int destY;

    public void setDest(int x, int y)
    {
        destX = destX + x;
        destY = destY + y;
    }

    public Swarm getSwarm()
    {
        return swarm;
    }

    void init(String name, String password)
    {
        this.name = name;
        this.password = password;
        score = 0;
    }

    Player(String name)
    {
        init(name, "");
    }


    public Player(String name, String password)
    {
        init(name, password);
    }

    public String getName()
    {
        return name;
    }

    void addScore(int inc)
    {
        score+=inc;
    }
}
