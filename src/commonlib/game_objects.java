package commonlib;

//import java.util.ArrayList;

import java.util.ArrayList;

public abstract class game_objects
{
	//public abstract class game_objects_base {
    Shape shape;
    D2vector cor;
    D2vector     vel;
    double      max_radius;
    boolean      is_controlable;
    //public enum object_type{Raw,Queen, Nutrition, Particle};
    //object_type objtype;
    double dt;

    int move() // problematic : how to handle collision?
    {
        cor.setx(cor.getx()+dt*vel.getx());
        cor.sety(cor.gety()+dt*vel.gety());
        return 1;
    }
    void change_vel(D2vector new_vel)
    {
        this.vel.assign(new_vel);
    }
    boolean is_fit(game_map gmap)
    {
        return this.shape.is_fit_newloc(this.cor,gmap);
    }
    void get_next_vel(D2vector acc)
    {
        this.vel.assign(this.vel.plus(acc.multi(dt)));
    }
    void apply_to_map(game_map gmap)
    {
        int N = shape.nodes.size();
        //System.out.format("N = %d\n",N);
        int i;
        D2index tmpindx;
        for(i=0;i<N;i++)
        {
            tmpindx = shape.nodes.get(i).to_index();
            //System.out.format("tmp index:"); tmpindx.Print();
            //System.out.format("tmp node %d:",i); shape.nodes.get(i).Print();
            if(gmap.is_inside_map(tmpindx))
            {
                gmap.map_grid[tmpindx.m_cor][tmpindx.n_cor].set_obj(this);
                //System.out.format("changed objtype!\n");
            }
        }
    }
    abstract void Printmyself();
    public static void main(String[] args)
    {
        D2vector l1 = new D2vector(1,1);
        queen obj1 = new queen(l1);
        game_objects obj2 = new fighting_particle(l1,l1,obj1);
        game_objects obj3 = new nutrient(l1);
    }
}

//}
class queen extends game_objects{
    //enum STATUS{SPEED, GROWTH, ATTACK,DEFEND};
    int max_health_point;
    int cur_health_point;
    int min_damage;
    int max_damage;
    int armor;
    int ID;
    ArrayList<skills> available_skills;
    queen(D2vector loc)
    {
        is_controlable = true;
        cor = loc;
        vel = new D2vector(0,0);
        max_radius = 10;
        shape = new Shape(loc,max_radius);
        //System.out.format("queen constructed at location"); cor.Print();
        //objtype = object_type.Queen;
    }
    queen(D2vector loc, game_map map)
    {
        is_controlable = true;
        cor = loc;
        vel = new D2vector(0,0);
        shape = new Shape(cor,10);
        System.out.format("queen constructed at location"); cor.Print();
        apply_to_map(map);
    }
    void Printmyself()
    {
        System.out.println("I am a queen");
        System.out.format("centered at :"); cor.Print();
    }
boolean regenerate()
{
cur_health_point = max_health_point;
return true;
}

}

class fighting_particle extends game_objects{
    queen parent;
    fighting_particle(D2vector loc,D2vector init_vel, queen par)
    {
        is_controlable = false;
        cor = loc;
        vel = init_vel;
        max_radius = 2;
        shape = new Shape(loc,max_radius);
        parent = par;
        System.out.println("particle constructed");
    }
    void Printmyself()
    {
        System.out.println("I am a particle");
        System.out.format("centered at :"); cor.Print();
    }
}

class nutrient extends game_objects
{
    skills sk1;
    nutrient()
    {
        is_controlable = false;
        cor = new D2vector(-10,-10);
        vel = new D2vector(0,0);
        shape = new Shape(cor,1);
        //System.out.println("nutrient constructed");
    }
    nutrient(D2vector loc)
    {
        is_controlable = false;
        cor = loc;
        vel = new D2vector(0,0);
        shape = new Shape(loc,1);
        //System.out.println("nutrient constructed");
    }
    void Printmyself()
    {
        System.out.println("I am a nutrient");
        System.out.format("centered at :"); cor.Print();
    }
}

class empty extends game_objects
{
    empty()
    {
        is_controlable = false;
        cor = new D2vector(0,0);
        vel = new D2vector(0,0);
        shape = new Shape();
    }
    void Printmyself()
    {
        System.out.println("I am an empty object");
        System.out.format("centered at :"); cor.Print();
    }
}
