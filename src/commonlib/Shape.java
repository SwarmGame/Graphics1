package commonlib;

import commonlib.gameObjects2.game_map;

import java.util.ArrayList;

public class Shape {
	//enum geotype{Circle, Eclipse, Square, Rectangle,Complex};
    String shapename;
    D2vector center;
    D2vector direction;
    double dh;

    public ArrayList<D2vector> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<D2vector> nodes) {
        this.nodes = nodes;
    }

    ArrayList<D2vector> nodes;
    private class Shape_para
    {
        double  r;    //for circle
        double  a,b;  //for eclipse
        double  nn;   //for Square
        double  m,n;  //for rectangle
        double  x_range, y_range; // for complex shape
    }
    public Shape()
    {
        center = new D2vector(0,0);
        direction = new D2vector(1,0);
        nodes = new ArrayList<D2vector>();
    }
    Shape(D2vector loc, String type,Shape_para para)
    {
        center = new D2vector(0,0);
        direction = new D2vector(1,0);
        nodes = new ArrayList<D2vector>();
    }
    public Shape(D2vector loc, double r)   // currently, we only support creating circular shapes
    {
    	int ILIMIT = (int)Math.ceil(r);
    	D2vector tmp ;
    	//System.out.format("tries to create shape at location"); loc.Print();
    	//System.out.format("with diameter %e%n",r);
    	nodes = new ArrayList<D2vector>();
    	center = loc;
    	double tmp_dis;
    	for(int i=-ILIMIT;i<=ILIMIT;i++)
    	{
    		for(int j=-ILIMIT;j<=ILIMIT;j++)
    		{
    			//tmp.setx(loc.getx()+i);
    			//tmp.sety(loc.gety()+j);
                tmp = new D2vector(loc.getx()+i,loc.gety()+j);
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
    public boolean is_fit_newloc(D2vector new_center, game_map map)
    {
    	ArrayList<D2vector> tmp_nodes = new ArrayList<D2vector>();
    	int i;
    	int tmpi, tmpj;
    	D2vector move_vector = new_center.minus(this.center);
        D2index tmpindx;
    	for(i=0;i<this.nodes.size();i++)
    	{
    		tmp_nodes.add(this.nodes.get(i).plus(move_vector));
    	}
    	for(i=0;i<tmp_nodes.size();i++)
    	{
            tmpindx = tmp_nodes.get(i).to_index();
    		if(map.is_occupied(tmpindx))
    		{
                 //System.out.format("fit shape attempt failed");
        	     return false;

    		}
    	}
        //System.out.format("fit shape attempt success");
    	return true;
    }
    public static void main(String[] args)
    {
    	D2vector center = new D2vector(2,3.5);
    	Shape mycircle = new Shape(center, 2.0);
    }
}
