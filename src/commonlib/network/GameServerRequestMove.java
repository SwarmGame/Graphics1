package commonlib.network;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/10/12
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameServerRequestMove extends GameServerRequest {
    int x;
    int y;

    public GameServerRequestMove()
    {}

    public GameServerRequestMove(int x, int y) {
        //super(type);
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}


}
