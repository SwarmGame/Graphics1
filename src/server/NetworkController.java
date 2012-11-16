package server;

import commonlib.network.GameServerRequestMove;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/14/12
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkController
{
    private Game game;


    public NetworkController(Game game)
    {
        this.game = game;
    }

    public void process(Player player, GameServerRequestMove request)
    {
        player.setMoveCommand(request.getX(), request.getY());

        if (Main.DEBUG > 1)
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
