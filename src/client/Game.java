package client;

import client.network.GameClient;
import commonlib.GameSituationSerialized;
import commonlib.gameObjects.Map;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Game
 * User: Derek
 * Date: 11/8/12
 * Time: 9:42 AM
 * Sets up and draws the game using the Slick library
 */
public class Game extends BasicGame
{
    private static final int MAXFPS = 30;

    private static String username;
    private static String password;
    private static String hostname;
    private static String port;
    private List<Image> queenImages;
    private GameSituationSerialized gameSituationSerialized;
    private Image backgroundImage;
    private GameClient client;

    public Game()
    {
        super("client.Game");
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        client = new GameClient(this, hostname, Integer.parseInt(port));
        try
        {
            client.connect();
        }
        catch (IOException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        client.send(new GameServerRequestAuth(username, password));
        queenImages = new ArrayList<Image>();

        try
        {
            queenImages.add(new Image("src/resources/queens/queen1.jpg"));
            queenImages.add(new Image("src/resources/queens/queen2.jpg"));
            Texture backgroundTexture = TextureLoader.getTexture("JPG", new FileInputStream("src/resources/textures/space1.jpg"));
            backgroundImage = new Image(backgroundTexture);
        }
        catch(IOException e)
        {
            System.out.println("Image resources not found");
            System.out.println(e);
            System.exit(1);
        }

    }

    public void newGameSituation(GameSituationSerialized situationSerialized)
    {
        this.gameSituationSerialized = situationSerialized;
    }


    void drawSwarm(Swarm swarm, Graphics g)
    {
        for(Particle particle : swarm.getParticles())
        {
            Circle circle = new Circle((int)particle.getX(), (int)particle.getY(), Particle.RADIUS);
            g.fill(circle);
        }

        int currentHP = swarm.getQueen().getHitPoints();
        g.setColor(g.getColor().darker((float)(Queen.MAX_HP - currentHP)/Queen.MAX_HP));
        //g.texture(new Circle(swarm.getQueen().getX(), swarm.getQueen().getY(), Queen.RADIUS), queenImages.get(0));
        g.fill(new Circle(swarm.getQueen().getX(), swarm.getQueen().getY(), Queen.RADIUS));
    }
    // This method is called every time the game window is redrawn
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        backgroundImage.draw(0,0, Map.WIDTH, Map.HEIGHT);

        if (gameSituationSerialized != null)
        {
            Swarm swarm1 = gameSituationSerialized.getSwarm1();
            Swarm swarm2 = gameSituationSerialized.getSwarm2();
            g.setColor(Color.blue);
            drawSwarm(swarm1, g);
            g.drawString(swarm1.getQueen().getHitPoints() + "/20", 50, 470);
            g.setColor(Color.red);
            drawSwarm(swarm2, g);
            g.drawString(swarm2.getQueen().getHitPoints() + "/20", 400, 470);

            g.setColor(Color.black);
            String winner = gameSituationSerialized.getWinner();
            if(winner != null)
            {
                g.drawString(winner, 165, 235);
            }
        }


    }

    // This method handles events such as mouse movement and key presses
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        Input input = gc.getInput();
        int x = 0;
        int y = 0;

        if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
        {
            x -= Queen.SPEED;
        }

        if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
        {
            x += Queen.SPEED;
        }

        if(input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
        {
            y -= Queen.SPEED;
        }

        if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
        {
            y += Queen.SPEED;
        }

        client.sendMoveCommand(x,y);
    }


    public static void main(String []args) throws SlickException
    {
        if(args.length != 4)
        {
            System.out.println("You must provide a username, password, hostname, and port");
            System.exit(1);
        }
        username = args[0];
        password = args[1];
        hostname = args[2];
        port = args[3];
        AppGameContainer app = new AppGameContainer(new Game());
        app.setTargetFrameRate(MAXFPS);
        app.setDisplayMode(Map.WIDTH, Map.HEIGHT, false);
        app.setShowFPS(false);
        app.start();
    }
}