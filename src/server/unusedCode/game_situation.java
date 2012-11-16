package server.unusedCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wenzhao
 * Date: 12-11-15
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class game_situation {
    ArrayList<game_objects>   objs_local;
    D2vector user_center;
    game_situation(game_map map, D2vector center, int M_range, int N_range)
    {
        int M_low, M_high;
        int N_low, N_high;
        M_low = center.get_mcor() - M_range/2;
        M_high =  center.get_mcor() + M_range/2;
        N_low = center.get_ncor() - N_range/2;
        N_high =  center.get_ncor() + N_range/2;
        if(M_low<0)
        {
            M_low =0;
        }
        if(M_high>map.M)
        {
            M_high = map.M;
        }
        if(N_low<0)
        {
            N_low =0;
        }
        if(N_high>map.N)
        {
            N_high =map.N;
        }
        objs_local = new ArrayList<game_objects>();
        user_center = new D2vector(center);
        Set<game_objects> objset = new HashSet<game_objects>();
        for(int i=M_low;i<M_high;i++)
        {
            for(int j=N_low;j<N_high;j++)
            {
                objset.add((map.map_grid[i][j]).gobj);
            }
        }
        Iterator<game_objects> it = objset.iterator();
        while (it.hasNext()) {
            objs_local.add(it.next()) ;
        }
    }
    game_situation()
    {
        objs_local = new ArrayList<game_objects>();
        user_center = new D2vector();
    }
    void Print_size()
    {
        System.out.format("number of objs in the game situation: %d%n",objs_local.size());
        for(int i=0;i<objs_local.size();i++)
        {
              objs_local.get(i).Printmyself();
        }
        System.out.format("game situation centered at "); user_center.Print();
    }
}
