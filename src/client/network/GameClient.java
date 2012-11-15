package client.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import commonlib.network.GameServerClientInitialization;
import commonlib.network.GameServerRequestMove;
import commonlib.network.GameServerResponseGameOver;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/10/12
 * Time: 8:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameClient {
    Client client;
    int debug;
    String hostname;
    int port;
    boolean isConnected;

    public GameClient(String hostname, int port)
    {
        client = new Client();
        Kryo kryo = client.getKryo();
        GameServerClientInitialization.initialize(client.getKryo());

        client.start();

        this.hostname = hostname;
        this.port = port;
        this.isConnected = false;
        client.addListener(new GameClientListener(this));

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
       System.out.println("Sending object");
       System.out.println(object);
       client.sendTCP(object);
       return true;
    }

    public boolean sendMoveCommand(int x, int y)
    {
        GameServerRequestMove request = new GameServerRequestMove(x,y);
        this.send(request);

        return true;
    }

    void disconnected(Connection connection)
    {
       System.out.println("Connection dropped");
       isConnected = false;
    }

    void received(Connection connection, Object object)
    {
        /* Notification from server received */
        /* It's either new game situation or "end of game" notification */
        System.out.print(object);
        if (object.getClass() == GameServerResponseGameOver.class)
        {
            GameServerResponseGameOver response = (GameServerResponseGameOver)object;
            System.out.println(response.getWinner());
        }
    }
}
