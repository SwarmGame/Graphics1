package commonlib.network;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 11/14/12
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameServerResponseGameOver extends GameServerResponse
{
    private String winner;

    public GameServerResponseGameOver()
    {
    }

    public GameServerResponseGameOver(String winner)
    {
       this.winner = winner;
    }

    public String getWinner()
    {
        return winner;
    }
}
