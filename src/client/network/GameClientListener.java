package client.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/10/12
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameClientListener extends Listener
{
    GameClient client;

    public GameClientListener(GameClient client)
    {
        this.client = client;
    }

    /**
     * Called when the remote end is no longer connected. There is no guarantee as to what thread will invoke this method.
     */
    public void disconnected(Connection connection)
    {
        client.disconnected(connection);
    }

    /**
     * Called when an object has been received from the remote end of the connection.
     * This method should not block for long periods as other network
     * activity will not be processed until it returns.
     */
    public void received(Connection connection, Object object)
    {
        client.received(connection, object);
    }
}