package server;

import commonlib.GameSituationSerialized;
import commonlib.gameObjects2.game_map;
import commonlib.network.GameServerResponseGameOver;
import server.network.GameServer;

import java.io.IOException;

/**
 *
 */
public class Game
{
    //30 updates per 1000 millisecond
    private final int TIME_BETWEEN_UPDATES = 1000/30;

    private GameServer gameServer;
    private NetworkController networkController;
    private MovementController movementController;
    private GameStateController gameStateController;

    private Player player1;
    private Player player2;
    private String player1Name;
    private String player2Name;
    private String player1Password;
    private String player2Password;

    private game_map mygamemap;

    public Game(String player1Name, String player1Password, String player2Name, String player2Password)
    {
        this.player1Name = player1Name;
        this.player1Password = player1Password;
        this.player2Name = player2Name;
        this.player2Password = player2Password;
    }

    public NetworkController getNetworkController()
    {
        return networkController;
    }

    void start()
    {
        System.out.println("Starting game server");
        try
        {
            gameServer = new GameServer(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mygamemap = new game_map(1000,1000);
        mygamemap.initialize_map(0);
        //mygamemap.generate_new_nutrient(30);
        int i =0;
//        while(i<10000)
//        {
//           mygamemap.generate_new_nutrient(1);
//           mygamemap.remove_last_item();
//           i++;
//        }

        movementController = new MovementController();
        networkController = new NetworkController(this);
        gameStateController = new GameStateController();
        player1 = new Player(player1Name, player1Password, 50, 250);
        player2 = new Player(player2Name, player2Password, 450, 250);

        // The game starts here, looping until a player wins
        int timeout = 0;
        while (true)
        {
            GameSituationSerialized gameSituation = new GameSituationSerialized();
            try
            {
                timeout++;
                Thread.sleep(TIME_BETWEEN_UPDATES);

                //mygamemap.generate_new_nutrient(1);
                //mygamemap.remove_last_item();

                movementController.moveSwarm(player1);
                movementController.moveSwarm(player2);

                gameSituation.setSwarm1(player1.getSwarm());
                gameSituation.setSwarm2(player2.getSwarm());

                String winner = gameStateController.calculateDamage(player1, player2);
                gameSituation.setWinner(winner);

                gameServer.sendToAll(gameSituation);

                if(winner != null)
                {
                    gameServer.sendToAll(new GameServerResponseGameOver(winner));
                    break;
                }

                //the server times out if it is taking too long to update
                if(timeout == 10000)
                {
                    gameServer.sendToAll(new GameServerResponseGameOver("Disconnect"));
                    timeout = 0;
                }

            }
            catch (InterruptedException e)
            {
                // Exception can be generated if signal is received by thread
                // Just ignore it
                // e.printStackTrace();
            }
        }
    }

    public Player verifyPlayer(String name, String password)
    {
        if (this.player1.getName().equals(name) && this.player1.getPassword().equals(password))
            return this.player1;
        else if (this.player2.getName().equals(name) && this.player2.getPassword().equals(password))
            return this.player2;
        else
            return null;
    }

    public void disconnectPlayer(Player player)
    {
        return;
    }
}
