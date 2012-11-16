package server.unusedCode;

import java.util.ArrayList;

public class Shape {
	enum geotype{Circle, Ellipse, Square, Rectangle,Complex}; 
    D2vector center;
    D2vector direction;
    double dh;
    ArrayList<D2vector> nodes;
    Shape()
    {
    	
    }
    Shape(D2vector loc, double r)   // currently, we only support creating circular shapes 
    {
    	int ILIMIT = (int)Math.floor(r);
    	D2vector tmp = new D2vector();
    	System.out.format("tries to create shape at location"); loc.Print();
    	System.out.format("with diameter %e%n",r);
    	nodes = new ArrayList<D2vector>();
    	center = loc;
    	double tmp_dis;
    	for(int i=-ILIMIT;i<=ILIMIT;i++)
    	{
    		for(int j=-ILIMIT;j<=ILIMIT;j++)
    		{
    			tmp.setx(loc.getx()+i);
    			tmp.sety(loc.gety()+j);
    			tmp_dis = loc.distance(tmp);
    			if(tmp_dis<=r)
    			{
    				nodes.add(tmp);
    				//System.out.format("Added to nodes: "); tmp.Print();
    			}
    			else
    			{
    				//System.out.format("distance = %e, abandoned",tmp_dis); tmp.Print();
    			}
    		}
    	}
    }
    boolean is_fit_newloc(D2vector new_center, game_map map)
    {
    	ArrayList<D2vector> tmp_nodes = new ArrayList<D2vector>();
    	int i;
    	int tmpi, tmpj;
    	D2vector move_vector = new_center.minus(this.center);
    	for(i=0;i<this.nodes.size();i++)
    	{
    		tmp_nodes.add(this.nodes.get(i).plus(move_vector));
    	}
    	for(i=0;i<tmp_nodes.size();i++)
    	{
    		tmpi = (int) tmp_nodes.get(i).getx();
    		tmpj = (int) tmp_nodes.get(i).gety();
    		if(map.is_inmap(tmpi,tmpj))
    		{
    			if(map.map_grid[tmpi][tmpj].gobj!=map.emptyobj)
        		{
        			return false;
        		}
    		}
    	}
    	return true;
    }
    public static void main(String[] args)
    {
    	D2vector center = new D2vector(2,3.5);
    	Shape mycircle = new Shape(center, 2.0);
    }
}
