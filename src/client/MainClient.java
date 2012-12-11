package client;

import client.network.MatchmakerClient;
import org.newdawn.slick.SlickException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainClient {
    public static void main(String []args) throws IOException {
        String hostname;
        String name;
        int port;

        if(args.length != 3)
        {
            System.out.println("You must provide a name, hostname and port");
            System.exit(1);
        }

        name = args[0];
        System.out.println(name);
        hostname = args[1];
        String str = args[2];
        port = Integer.parseInt(str);

        GameMatchmaker gameMatchmaker = new GameMatchmaker();
        gameMatchmaker.startMatchmaker(name, hostname, port);
        gameMatchmaker.EventLoop();

        String [] argc;
        argc = new String[4];
        argc[0] = name;
        argc[1] = gameMatchmaker.getServerPassword();
        argc[2] = gameMatchmaker.getServerHostname();
        argc[3] = Integer.toString(gameMatchmaker.getServerPort());

        System.out.println("New game");

        try {
            Game.main(argc);
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

}
