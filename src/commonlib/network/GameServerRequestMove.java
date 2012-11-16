package commonlib.network;

/**
 * Game Server Request Move
 * User: ALEXANDER
 * Date: 11/10/12
 * Time: 9:17 PM
 */
public class GameServerRequestMove extends GameServerRequest
{
    private int x;
    private int y;

    public GameServerRequestMove()
    {}

    public GameServerRequestMove(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}


}
