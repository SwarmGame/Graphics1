package server.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * This class implements callback functions called from cryonet library
 */
public class GameServerListener extends Listener
{
    private GameServer server;

    public GameServerListener(GameServer server)
    {
        this.server = server;
    }
    /**
     * Called when the remote end has been connected.
     * This method should not block for long periods as other network activity will not be processed
     * until it returns.
     */
    public void connected(Connection connection)
    {
        if (server != null)
            server.connected(connection);
        else
            System.out.println("server pointer is null");
    }

    /**
     * Called when the remote end is no longer connected. There is no guarantee as to what thread will invoke this method.
     */
    public void disconnected(Connection connection)
    {
        server.disconnected(connection);
    }

    /**
     * Called when an object has been received from the remote end of the connection.
     * This method should not block for long periods as other network
     * activity will not be processed until it returns.
     */
    public void received(Connection connection, Object object)
    {
        server.received(connection, object);
    }
}
