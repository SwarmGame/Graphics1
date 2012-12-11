package commonlib.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import server.Game;
import server.Main;
import server.Player;
import server.network.GameServerListener;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericServer implements Receive {

    protected Server server;
    // Hash table that maps connections to player objects.
    // Currently connected players only
    protected Hashtable<Connection, Object> connections;

    GenericServer()
    {}

    public GenericServer(int port) throws IOException
    {
        connections = new Hashtable<Connection, Object>();
        server = new Server(16384, 16384);
        //GameServerClientInitialization.initialize(server.getKryo());

        server.start();
        try
        {
            server.bind(port, port);
        }
        catch (IOException io)
        {
            throw io;
        }

        server.addListener(new GenericListener(this));

    }

    public void disconnected(Connection connection)
    {
        if (connections != null)
        {
            connections.remove(connection);
        }
    }

    public void received (Connection connection, Object object)
    {

    }

    public void send(Object recipient, Object object)
    {
        Connection conn = null;

        Set set = connections.keySet();
        Iterator itr = set.iterator();

        /* find Connection object that corresponds to given player */
        while (itr.hasNext())
        {
            Connection connection = (Connection)itr.next();
            if (connections.get(connection) == recipient)
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
        if (object == null)
            return; // Can't send null pointer over network

        Set set = connections.keySet();
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



    public void connected(Connection connection)
    {

    }

}
