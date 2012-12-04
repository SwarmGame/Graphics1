package server;


/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/8/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    public static final int DEBUG = 0;

    public static void main(String []args)
    {
        Game game;
        if(args.length == 4)
        {
            game = new Game(args[0], args[1], args[2], args[3]);
            game.start();
        }
        else
        {
            System.out.println("You must specify Player 1 name, Player 1 password, Player 2 name, and Player 2 password");
            System.exit(1);
        }
    }
}