package server.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import commonlib.network.GameServerClientInitialization;
import commonlib.network.GameServerRequestAuth;
import commonlib.network.GameServerRequestMove;
import server.Game;
import server.Main;
import server.Player;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Class that represents game server.
 * It handles new connections
 *
 * Client
 */
public class GameServer
{
    private Server server;
    private Game game;
    // Hash table that maps connections to player objects.
    // Currently connected players only
    Hashtable<Connection, Player> players;

    public GameServer(Game game, int port) throws IOException
    {
        this.game = game;
        players = new Hashtable<Connection, Player>();
        server = new Server(16384, 16384);
        GameServerClientInitialization.initialize(server.getKryo());

        server.start();
        try
        {
            System.out.println(port);
            server.bind(port, port);
        }
        catch (IOException io)
        {
           throw io;
        }

        server.addListener(new GameServerListener(this));

    }

    public void connected(Connection connection)
    {
       // Client has connected, but hasn't been authenticated yet
       if (Main.DEBUG > 1)
           System.out.printf("New client connected. Awaiting GameServerRequestAuth object\n");
    }

    public void disconnected(Connection connection)
    {
        // Notify game about disconnection and remove player from hash table
        if (Main.DEBUG > 1)
        {
            Player player =  players.get(connection);
            System.out.printf("client disconnected: %s\n", player.getName());
        }
        if (players != null)
        {
            game.disconnectPlayer(players.get(connection));
            players.remove(connection);
        }
    }

    public void received (Connection connection, Object object)
    {

        Player player = null;

        // First, check if it's an authentication request from client.
        if (object.getClass() == GameServerRequestAuth.class)
        {
            // Client connected or reconnected
            GameServerRequestAuth request = (GameServerRequestAuth) object;

            // login and password verification is actually done by createNewPlayer function
            player = game.verifyPlayer(request.getName(), request.getPassword());

            if (player != null)
            {
                players.put(connection, player);
                if (Main.DEBUG > 0)
                {
                    System.out.printf("Client authenticated: %s\n", player.getName());
                }
            }
            else
            {
                if (Main.DEBUG > 0)
                {
                    System.out.printf("Client authentication failed %s:%s\n", request.getName(), request.getPassword());
                }
            }
            return;
        }


        // It's not a connection request.
        player = players.get(connection);
        if (player == null)
        {
            if (Main.DEBUG > 0)
                System.out.println("ERROR: GameServer::received - player isn't found in players hash table");
            return;
        }

        if (Main.DEBUG > 1)
        {
            System.out.printf("Object received: %s\n", object.getClass());
        }

        if (object.getClass() == GameServerRequestMove.class)
        {
            // Move command - forward to movement controller
            GameServerRequestMove command = (GameServerRequestMove) object;
            game.getNetworkController().process(player, command);
        }


    }

    public void send(Player player, Object object)
    {
        Connection conn = null;
        Set set = players.keySet();
        Iterator itr = set.iterator();

        /* find Connection object that corresponds to given player */
        while (itr.hasNext())
        {
            Connection connection = (Connection)itr.next();
            if (players.get(connection) == player)
                conn = connection;
        }

        if (conn != null)
        {
            conn.sendTCP(object);
        }
        else
        {
            if (Main.DEBUG > 0)
                System.out.println("GameServer::send - can't find connection corresponding to player");
            return;
        }
    }


    public void sendToAll (Object object)
    {
       /* Typically used to send updated game situation to all clients */
       if (object == null)
           return; // Can't send null pointer over network

       Set set = players.keySet();
       Iterator itr = set.iterator();
       while (itr.hasNext())
       {
           Connection connection = (Connection)itr.next();
           if (connection != null)
                connection.sendTCP(object);
           else if (Main.DEBUG > 1)
               System.out.println("sendToAll: null pointer in hash table");
       }
    }
}
