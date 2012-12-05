package commonlib.gameObjects2;

import commonlib.*;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 12/4/12
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
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

    public D2vector getCor() {
        return cor;
    }

    public void setCor(D2vector cor) {
        this.cor = cor;
    }

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
    public boolean is_fit(game_map gmap)
    {
        return this.shape.is_fit_newloc(this.cor,gmap);
    }
    void get_next_vel(D2vector acc)
    {
        this.vel.assign(this.vel.plus(acc.multi(dt)));
    }
    public void apply_to_map(game_map gmap)
    {
        int N = shape.getNodes().size();
        //System.out.format("N = %d\n",N);
        int i;
        D2index tmpindx;
        for(i=0;i<N;i++)
        {
            tmpindx = shape.getNodes().get(i).to_index();
            //System.out.format("tmp index:"); tmpindx.Print();
            //System.out.format("tmp node %d:",i); shape.nodes.get(i).Print();
            if(gmap.is_inside_map(tmpindx))
            {
                gmap.map_grid[tmpindx.getM_cor()][tmpindx.getN_cor()].set_obj(this);
                //System.out.format("changed objtype!\n");
            }
        }
        gmap.game_objs.add(this);
    }
    void remove_from_map(game_map gmap)
    {
        int N = shape.getNodes().size();
        //System.out.format("N = %d\n",N);
        int i;
        D2index tmpindx;
        for(i=0;i<N;i++)
        {
            tmpindx = shape.getNodes().get(i).to_index();
            if(gmap.is_inside_map(tmpindx))
            {
                gmap.map_grid[tmpindx.getM_cor()][tmpindx.getN_cor()].set_obj(gmap.emptyobj);
                //System.out.format("set point empty!\n");
            }
        }
        gmap.remove_obj(this);

    }
    public abstract void Printmyself();
    public static void main(String[] args)
    {
        D2vector l1 = new D2vector(1,1);
        Queen obj1 = new Queen(l1);
        game_objects obj2 = new Particle(l1,l1,obj1);
        game_objects obj3 = new nutrient(l1);
    }
}

