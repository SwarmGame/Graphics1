package commonlib;

//import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

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

    void move()
    {
        cor.setx(cor.getx()+dt*vel.getx());
        cor.sety(cor.gety()+dt*vel.gety());
        return ;
    }
    void reverse_move()
    {
        cor.setx(cor.getx()-dt*vel.getx());
        cor.sety(cor.gety()-dt*vel.gety());
        return ;
    }
    void move(game_map gmap)
    {
        this.move();
        if(!this.is_fit(gmap))
        {
            this.apply_to_map(gmap);
        }
        else
        {
            this.reverse_move();
        }
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
        gmap.game_objs.add(this);
    }
    void remove_from_map(game_map gmap)
    {
        int N = shape.nodes.size();
        //System.out.format("N = %d\n",N);
        int i;
        D2index tmpindx;
        for(i=0;i<N;i++)
        {
            tmpindx = shape.nodes.get(i).to_index();
            if(gmap.is_inside_map(tmpindx))
            {
                gmap.map_grid[tmpindx.m_cor][tmpindx.n_cor].set_obj(gmap.emptyobj);
                //System.out.format("set point empty!\n");
            }
        }
        gmap.remove_obj(this);

    }
    abstract void Printmyself();
    public static void main(String[] args)
    {
        D2vector l1 = new D2vector(1,1);
        Queen obj1 = new Queen(l1);
        game_objects obj2 = new Particle(l1,l1,obj1);
        game_objects obj3 = new nutrient(l1);
    }
}

//}
class Queen extends game_objects{
    //enum STATUS{SPEED, GROWTH, ATTACK,DEFEND};
    int max_health_point;
    int cur_health_point;
    int min_damage;
    int max_damage;
    int armor;
    int ID;
    ArrayList<skills> available_skills;
    Queen(D2vector loc)
    {
        is_controlable = true;
        cor = loc;
        vel = new D2vector(0,0);
        max_radius = 10;
        shape = new Shape(loc,max_radius);
        //System.out.format("Queen constructed at location"); cor.Print();
        //objtype = object_type.Queen;
    }
    Queen(D2vector loc, game_map map)
    {
        is_controlable = true;
        cor = loc;
        vel = new D2vector(0,0);
        shape = new Shape(cor,10);
        System.out.format("Queen constructed at location"); cor.Print();
        apply_to_map(map);
    }
    void Printmyself()
    {
        System.out.println("I am a Queen");
        System.out.format("centered at :"); cor.Print();
    }
boolean regenerate()
{
cur_health_point = max_health_point;
return true;
}

}

class Particle extends game_objects{
    Queen parent;
    Particle(D2vector loc, D2vector init_vel, Queen par)
    {
        is_controlable = false;
        cor = loc;
        vel = init_vel;
        max_radius = 2;
        shape = new Shape(loc,max_radius);
        parent = par;
        //System.out.println("particle constructed");
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
class Swarm extends game_objects
{
    private Queen queen;
    private List<Particle> particles;

    // Default constructor is required by Cryonet library
    public Swarm(){}

    public Swarm(Queen queen)
    {
        this.queen = queen;
        particles = new ArrayList<Particle>();
    }

    public Queen getQueen()
    {
        return queen;
    }

    public List<Particle> getParticles()
    {
        return particles;
    }

    public void addParticle(Particle particle)
    {
        particles.add(particle);
    }
    void Printmyself()
    {
        System.out.println("I am a swarm");
        System.out.format("centered at :"); queen.cor.Print();
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
