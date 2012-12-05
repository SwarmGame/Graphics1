package commonlib.gameObjects2;

import commonlib.D2vector;
import commonlib.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 12/4/12
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class empty extends game_objects
{
    public empty()
    {
        is_controlable = false;
        cor = new D2vector(0,0);
        vel = new D2vector(0,0);
        shape = new Shape();
    }
    public void Printmyself()
    {
        System.out.println("I am an empty object");
        System.out.format("centered at :"); cor.Print();
    }
}
