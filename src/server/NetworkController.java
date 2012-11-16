package server;

import commonlib.network.GameServerRequestMove;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/14/12
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkController {
    Game game;
    public int debug;


    public NetworkController(Game game)
    {
        this.game = game;
        this.debug = game.debug;
    }

    public void process(Player player, GameServerRequestMove request)
    {
        player.setDest(request.getX(), request.getY());

        if (debug > 1)
        {
            System.out.print("New destination for player ");
            System.out.print(player.getName());
            System.out.print(" ");
            System.out.print(request.getX());
            System.out.print(",");
            System.out.println(request.getY());

        }
    }

}
