package commonlib.network;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchmakerResponseStartGame {
     private String hostname;
     private int port;
     private String password;

    public MatchmakerResponseStartGame(){};

    public MatchmakerResponseStartGame(String hostname, int port, String password) {
        this.hostname = hostname;
        this.port = port;
        this.password = password;
    }


    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
}
