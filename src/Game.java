import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 11/8/12
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGame
{
    private Particle particle;
    private static final int MAXFPS = 60;

    public Game()
    {
        super("Game");
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        particle = new Particle(300, 300);
    }

    // This method is called every time the game window is redrawn
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.draw(new Circle(particle.getX(), particle.getY(), 10));
    }

    // This method would handle events such as mouse movement and keypresses,
    // but we have no event based actions yet. The method is still included
    // because it must be implemented in order to extend BasicGame.
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_A))
        {
            particle.setX(particle.getX()-1);
        }

        if(input.isKeyDown(Input.KEY_D))
        {
            particle.setX(particle.getX()+1);
        }

        if(input.isKeyDown(Input.KEY_W))
        {
            particle.setY(particle.getY()-1);
        }

        if(input.isKeyDown(Input.KEY_S))
        {
            particle.setY(particle.getY()+1);
        }
    }

    public static void main(String []args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setTargetFrameRate(MAXFPS);
        app.setDisplayMode(800, 600, false);
        app.start();
    }
}
