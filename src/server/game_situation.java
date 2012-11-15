package server;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wenzhao
 * Date: 12-11-15
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class game_situation {
    ArrayList<game_objects>   objs_local;
    D2vector                  user_center;
    game_situation(game_map map, D2vector center, int M_range, int N_range)
    {
        int M_low, M_high;
        int N_low, N_high;
        objs_local = new ArrayList<game_objects>();
        user_center = new D2vector(center);
        Set<game_objects> objset;

    }
}
