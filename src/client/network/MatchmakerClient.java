package client.network;

import client.GameMatchmaker;
import com.esotericsoftware.kryonet.Connection;
import commonlib.network.GenericClient;
import commonlib.network.MatchmakerClientInitialization;
import commonlib.network.MatchmakerRequestJoin;
import commonlib.network.MatchmakerResponseStartGame;

/**
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 11:29 PM
 * Matchmaker Client
 */
public class MatchmakerClient extends GenericClient {

    String name;
    GameMatchmaker gameMatchmaker;

    public MatchmakerClient(String name, String hostname, int port)
    {
        super(hostname, port);
        this.name = name;
        MatchmakerClientInitialization.initialize(client.getKryo());
    }

    public MatchmakerClient(GameMatchmaker gameMatchmaker, String name, String hostname, int port)
    {
        super(hostname, port);
        this.gameMatchmaker = gameMatchmaker;
        this.name = name;
        MatchmakerClientInitialization.initialize(client.getKryo());
    }


    public void join()
    {
        send(new MatchmakerRequestJoin(name));
    }

    public void received(Connection connection, Object object)
    {
        if (object.getClass() == MatchmakerResponseStartGame.class)
        {
            System.out.println("Connecting to server");
            if (gameMatchmaker != null)
                gameMatchmaker.startGame((MatchmakerResponseStartGame)object);
        }
    }


}
