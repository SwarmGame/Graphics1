package server;

import commonlib.network.GameServerResponseGameOver;
import server.network.GameServer;

import java.io.IOException;

/**
 *
 */
public class Game {
    public int debug;

    GameServer gameServer;
    MovementController movementController;

    Player players[];
//  Map
//  EventQueue
//
    public MovementController getMovementController()
    {
        return movementController;
    }

    void start()
    {
        System.out.println("Starting game server");
        try {
            gameServer = new GameServer(this);
            gameServer.debug = 1;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        movementController = new MovementController(this);

//        AppGameContainer app = new AppGameContainer( new SimpleServer() );
//        app.setDisplayMode(800, 600, false);
//        app.start();

// Normally, we would start a game here
// But for now we we run a while (1) loop
        int timeout = 0;
        while (true)
        {
            try {
                timeout++;
                Thread.sleep(1);
                if ((timeout % 1000) == 0) {
                    //System.out.println("Sending hello message");
                    // Calculate game situation
                    // Calculate
                    // Send game situation to everyone
                    gameServer.sendToAll("Hello");
                   // timeout = 0;
                }

                if (timeout == 10000)
                {
                    gameServer.sendToAll(new GameServerResponseGameOver("x_x_x"));
                    timeout = 0;
                }


            } catch (InterruptedException e) {
                // Exception can be generated if signal is received by thread
                // Just ignore it
                // e.printStackTrace();
            }
        }
    }

    public Player createNewPlayer(String name) {
        //return null;  //To change body of created methods use File | Settings | File Templates.

        return new Player(name);
    }

    public Player verifyPlayer(String name, String password)
    {
        return null;
    }

    public void moveCommandReceived(Player player, int x, int y)
    {
       // New destination: x,y for player
       // player.swarm.destX = x;
       // player.swarm.destY = y;

    }

    public void disconnectPlayer(Player player)
    {
        return;
    }
}
