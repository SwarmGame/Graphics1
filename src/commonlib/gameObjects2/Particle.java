package commonlib.gameObjects2;

import commonlib.D2vector;
//import commonlib.Queen;
import commonlib.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 12/4/12
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
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
    public void Printmyself()
    {
        System.out.println("I am a particle");
        System.out.format("centered at :"); cor.Print();
    }
}
