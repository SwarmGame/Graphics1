package commonlib.network;

import client.Game;
import client.network.GameClientListener;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import commonlib.GameSituationSerialized;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericClient implements Receive {
    protected Client client;
    private int debug;
    protected String hostname;
    protected int port;
    protected boolean isConnected;

    public GenericClient()
    {}

    public GenericClient(String hostname, int port)
    {
        client = new Client(8192, 8192);

        client.start();

        this.hostname = hostname;
        this.port = port;
        this.isConnected = false;
        client.addListener(new GenericListener(this));

    }

    public boolean connect() throws IOException
    {
        if (isConnected == true)
            return true;

        try
        {
            client.connect(5000, hostname, port, port);
        }
        catch (IOException io)
        {
            return false;
        }

        isConnected = true;
        return true;
    }

    public boolean send(Object object)
    {
        client.sendTCP(object);
        return true;
    }

    public void connected(Connection connection)
    {}


    public void disconnected(Connection connection)
    {
        System.out.println("Connection dropped");
        isConnected = false;
    }

    public void received(Connection connection, Object object)
    {}

}
