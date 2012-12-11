package commonlib.network;

import client.network.GameClient;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericListener extends Listener {
    //GenericServer server;
    private Receive object;

    public GenericListener(Receive object)
    {
        this.object = object;
    }

    /**
     * Called when the remote end has been connected.
     * This method should not block for long periods as other network activity will not be processed
     * until it returns.
     */
    public void connected(Connection connection)
    {
        this.object.connected(connection);
    }


    /**
     * Called when the remote end is no longer connected. There is no guarantee as to what thread will invoke this method.
     */
    public void disconnected(Connection connection)
    {
        this.object.disconnected(connection);
    }

    /**
     * Called when an object has been received from the remote end of the connection.
     * This method should not block for long periods as other network
     * activity will not be processed until it returns.
     */
    public void received(Connection connection, Object object)
    {
        this.object.received(connection, object);
    }

}
