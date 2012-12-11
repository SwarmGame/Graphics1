package matchmaker;

import commonlib.network.MatchmakerResponseStartGame;
import server.Game;
import server.MainThread;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Matchmaker {
    MatchmakerServer matchmakerServer;
    List<Player> players;
    String hostname;
    int port;

    static int uniquePort = 8000;
    static int getUniquePort() {
        if (uniquePort == 8001)
            // Matchmaker is listening on port 8001. Skipping this port
            uniquePort++;
        return uniquePort++;
    }

    Matchmaker() throws IOException {
        matchmakerServer = new MatchmakerServer(this, 8001);
        hostname = "seven.campus.nd.edu";
        port = getUniquePort();
        players = new LinkedList<Player>();
    }

    void joined(Player player)
    {
        System.out.printf("New player joined: %s\n", player.getName());
        players.add(player);

        if (players.size() == 2)
        {
            System.out.println("Starting game");
            startNewGameSession();
            players = new LinkedList<Player>();
        }
        return;
    }

    protected void startNewGameSession()
    {
        // Start new game session;
        //1. Start new server
        for (Player player1: players) {
            String password = UUID.randomUUID().toString();
            player1.setPassword(password);
        }

        System.out.printf("Port %d\n", port);

        MainThread gameThread = new MainThread(players.get(0).getName(),
                players.get(0).getPassword(),
                players.get(1).getName(),
                players.get(1).getPassword(),
                port);

        gameThread.start();

        //2. Inform players about new server

        for (Player player1: players)
        {
            System.out.println(player1.getName());
            System.out.println(player1.getPassword());
            matchmakerServer.send(player1, new MatchmakerResponseStartGame(hostname, port, player1.getPassword()));
        }

        port = getUniquePort();

    }

    public void eventLoop()
    {
        while (true)
        {

        }
    }

}
