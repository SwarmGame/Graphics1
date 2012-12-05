package commonlib.gameObjects2;

import commonlib.D2vector;
import commonlib.Shape;
import commonlib.skills;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 12/4/12
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class nutrient extends game_objects
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
    public nutrient(D2vector loc)
    {
        is_controlable = false;
        cor = loc;
        vel = new D2vector(0,0);
        shape = new Shape(loc,1);
        //System.out.println("nutrient constructed");
    }

    @Override
    public void Printmyself()
    {
        System.out.println("I am a nutrient");
        System.out.format("centered at :"); cor.Print();
    }
}
