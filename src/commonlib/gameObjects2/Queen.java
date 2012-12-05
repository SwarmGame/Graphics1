package commonlib.gameObjects2;

import commonlib.D2vector;
import commonlib.Shape;
import commonlib.skills;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 12/4/12
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */ //}
public class Queen extends game_objects {
    //enum STATUS{SPEED, GROWTH, ATTACK,DEFEND};
    int max_health_point;
    int cur_health_point;
    int min_damage;
    int max_damage;
    int armor;
    int ID;
    ArrayList<skills> available_skills;
    public Queen(D2vector loc)
    {
        is_controlable = true;
        cor = loc;
        vel = new D2vector(0,0);
        max_radius = 10;
        shape = new Shape(loc,max_radius);
        //System.out.format("Queen constructed at location"); cor.Print();
        //objtype = object_type.Queen;
    }
    public Queen(D2vector loc, game_map map)
    {
        is_controlable = true;
        cor = loc;
        vel = new D2vector(0,0);
        shape = new Shape(cor,10);
        System.out.format("Queen constructed at location"); cor.Print();
        apply_to_map(map);
    }
    public void Printmyself()
    {
        System.out.println("I am a Queen");
        System.out.format("centered at :"); cor.Print();
    }
public boolean regenerate()
{
cur_health_point = max_health_point;
return true;
}

}
