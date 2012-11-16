package commonlib.network;

/**
 * This class implements Requirement "Security requirement"
 */
public class GameServerRequestAuth extends GameServerRequest
{

    private String name;
    private String password;

    public GameServerRequestAuth()
    {
        this.name = null;
    }

    public GameServerRequestAuth(String name)
    {
       this.name = name;
       this.password = "";
    }

    public GameServerRequestAuth(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

}
