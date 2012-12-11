package matchmaker;

import com.esotericsoftware.kryonet.Connection;
import commonlib.network.GenericServer;
import commonlib.network.MatchmakerClientInitialization;
import commonlib.network.MatchmakerRequestJoin;
import matchmaker.Matchmaker;
import matchmaker.Player;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchmakerServer extends GenericServer {
    private Matchmaker matchmaker;

    public MatchmakerServer(Matchmaker matchmaker, int port) throws IOException {
        super(port);
        this.matchmaker = matchmaker;
        MatchmakerClientInitialization.initialize(server.getKryo());
    }

    public void received(Connection connection, Object object)
    {
        if (object.getClass() == MatchmakerRequestJoin.class)
        {
            System.out.println("Join request received");

            MatchmakerRequestJoin request = (MatchmakerRequestJoin)object;
            Player player = new Player(request.getName());
            connections.put(connection, player);
            matchmaker.joined(player);
        }
    }

}
