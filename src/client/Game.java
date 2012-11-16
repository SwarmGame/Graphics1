package client;

import client.network.GameClient;
import commonlib.GameSituationSerialized;
import commonlib.gameObjects.Particle;
import commonlib.gameObjects.Queen;
import commonlib.gameObjects.Swarm;
import commonlib.network.GameServerRequest;
import commonlib.network.GameServerRequestAuth;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Derek
 * Date: 11/8/12
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends BasicGame
{
    private Swarm swarm;
    private Swarm swarm1;
    private GameSituationSerialized gameSituation;

    private static final int MAXFPS = 60;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private GameClient client;
    //private Rectangle backgroundRectangle;

    public Game()
    {
        super("client.Game");
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        client = new GameClient(this, "localhost", 8000);
        try {
            client.connect();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        client.send(new GameServerRequestAuth("alex", "1"));
        swarm = new Swarm(new Queen(300, 300));
        swarm.addParticle(new Particle(300, 200));
        swarm.addParticle(new Particle(200, 300));

        //backgroundRectangle = new Rectangle(250,250,250,250);

        swarm1 = new Swarm(new Queen(300, 300));
        swarm1.addParticle(new Particle(300, 200));


        try
        {
            backgroundTexture = TextureLoader.getTexture("JPG", new FileInputStream("src/resources/textures/dirt.jpg"));
        }
        catch(IOException e)
        {
            System.out.println("Background texture not found");
            System.out.println(e);
            System.exit(1);
        }

        backgroundImage = new Image(backgroundTexture);
    }

    public void newGameSituation(Swarm swarm)
    {
         this.swarm = swarm;
    }

    // This method is called every time the game window is redrawn
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        backgroundImage.draw(0,0,1000,1000);
        g.draw(new Circle(swarm.getQueen().getX(), swarm.getQueen().getY(), 10));
        for(Particle particle : swarm.getParticles())
        {
            g.draw(new Circle((int)particle.getX(), (int)particle.getY(), 2));
        }

        g.draw(new Circle(swarm1.getQueen().getX(), swarm1.getQueen().getY(), 10));
        for(Particle particle : swarm1.getParticles())
        {
            g.draw(new Circle((int)particle.getX(), (int)particle.getY(), 2));
        }
    }

    // This method handles events such as mouse movement and key presses
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        Input input = gc.getInput();
        int x = 0;
        int y = 0;
        //commonlib.gameObjects.Queen queen = swarm.getQueen();

        if(input.isKeyDown(Input.KEY_A))
        {
            x -= 2;
        }

        if(input.isKeyDown(Input.KEY_D))
        {
            x += 2;
        }

        if(input.isKeyDown(Input.KEY_W))
        {
            y -= 2;
        }

        if(input.isKeyDown(Input.KEY_S))
        {
            y += 2;
        }

        //swarm.move(x,y);
        client.sendMoveCommand(x,y);
    }

    public static void main(String []args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setTargetFrameRate(MAXFPS);
        app.setDisplayMode(500, 500, false);
        app.start();
    }
}