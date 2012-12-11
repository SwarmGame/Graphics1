package commonlib.network;

import com.esotericsoftware.kryo.Kryo;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchmakerClientInitialization {
    public static boolean initialize(Kryo kryo)
    {
        kryo.register(MatchmakerRequestJoin.class);
        kryo.register(MatchmakerResponseStartGame.class);
        //kryo.register(List<nutrient>.class);
        return true;
    }

}
