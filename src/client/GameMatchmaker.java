package client;

import client.network.MatchmakerClient;
import commonlib.network.MatchmakerResponseStartGame;
import matchmaker.Matchmaker;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/10/12
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameMatchmaker {
    MatchmakerClient matchmakerClient;
   volatile boolean done;

    public String getServerHostname() {
        return serverHostname;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    String serverHostname;
    int serverPort;
    String serverPassword;



    public GameMatchmaker()
    {
        matchmakerClient = null;
        done = false;
    }

    public void startMatchmaker(String name, String hostname, int port)
    {
        matchmakerClient = new MatchmakerClient(this, name, hostname, port);

        try {
            matchmakerClient.connect();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        matchmakerClient.join();

        System.out.println("Connected");

    }

    public void startGame(MatchmakerResponseStartGame response)
    {
        System.out.println("Starting game - startGame");
        serverHostname = response.getHostname();
        serverPassword = response.getPassword();
        serverPort = response.getPort();
        done = true;

    }

    public void EventLoop()
    {
        while (done == false)
        {}
        System.out.println("End EventLoop");
    }
}
