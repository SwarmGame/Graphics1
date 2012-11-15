package server;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/8/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    public static void main(String []args){
        Game game = new Game();
        game.debug = 2;
        game.start();
    }
}