package commonlib.network;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchmakerRequestJoin {
    private String name;

    public MatchmakerRequestJoin(){};

    public MatchmakerRequestJoin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
