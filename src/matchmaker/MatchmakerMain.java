package matchmaker;

import server.Game;
import server.MainThread;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatchmakerMain {
    public static void main(String []args)
    {
//        MainThread gameThread;
//        gameThread = new MainThread("alex", "1", "derek", "2");
//        gameThread.start();
        try {
            Matchmaker matchmaker = new Matchmaker();
            while (true)
            {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
