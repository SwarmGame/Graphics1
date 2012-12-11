package server;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainThread extends Thread {
    private String player1Name;
    private String player2Name;
    private String player1Password;
    private String player2Password;
    private int port;

    public MainThread(String player1Name, String player1Password, String player2Name, String player2Password, int port)
    {
        this.player1Name = player1Name;
        this.player1Password = player1Password;
        this.player2Name = player2Name;
        this.player2Password = player2Password;
        this.port = port;
    }

    public void run()
    {
        Game game = new Game(player1Name, player1Password, player2Name, player2Password);
        game.start(port);
    }
}
