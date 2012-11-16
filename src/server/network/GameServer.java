package server.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import commonlib.network.GameServerClientInitialization;
import commonlib.network.GameServerRequestAuth;
import commonlib.network.GameServerRequestMove;
import server.Game;
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
public class GameServer {
    Server server;
    Game game;
    // GameServerClient client;
    // Hash table that maps connections to player objects.
    // Currently connected players only
    //
    Hashtable<Connection, Player> players;
    //Set<Player>

    public int debug;

    public GameServer(Game game) throws IOException
    {
        this.game = game;
        debug = 0;
        players = new Hashtable<Connection, Player>();
        server = new Server();
        GameServerClientInitialization.initialize(server.getKryo());

        server.start();
        try
        {
            server.bind(8000, 8000);
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
       if (debug > 1)
           System.out.printf("New client connected. Awaiting GameServerRequestAuth object");
    }

    public void disconnected(Connection connection)
    {
        // Notify game about disconnection and remove player from hash table
        if (debug > 1)
        {
            Player player =  players.get(connection);
            System.out.printf("client disconnected: %s", player.getName());
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

        // First, check if it's an authentification request from client.
        // Currently, we only check username.

        if (object.getClass() == GameServerRequestAuth.class)
        {
            // Client connected or reconnected
            GameServerRequestAuth request = (GameServerRequestAuth) object;

            // login and password verification is actually done by createNewPlayer function
            //player = game.createNewPlayer(request.getName());

            player = game.verifyPlayer(request.getName(), request.getPassword());

            if (player != null)
            {
                 players.put(connection, player);
                if (debug > 0)
                {
                    System.out.printf("Client authenticated: %s", player.getName());
                }
            }
            else
            {
                if (debug > 0)
                {
                    System.out.printf("Client authentication failed %s:%s", request.getName(), request.getPassword());
                }
            }
            return;
        }


        // It's not a connection request.
        player = players.get(connection);
        if (player == null) {
            if (debug > 0)
                System.out.println("ERROR: GameServer::received - player isn't found in players hash table");
            //return;
        }

        if (debug > 1)
        {
            System.out.printf("Object received: %s", object.getClass());
            //System.out.println(object.getClass());
        }

        // Now figure out what to do
//        if (object.getClass() == GameServerRequestAuth.class)
//        {
//           // Client connected or reconnected
//           GameServerRequestAuth request = (GameServerRequestAuth) object;
//           player = game.createNewPlayer(request.getName());
//           players.put(connection, player);
//        }

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
            if (debug > 0)
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
           else if (debug > 1)
               System.out.println("sendToAll: null pointer in hash table");
       }
    }
}
