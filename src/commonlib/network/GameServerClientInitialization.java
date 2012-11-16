package commonlib.network;

import com.esotericsoftware.kryo.Kryo;
import commonlib.gameObjects.Particle;
import commonlib.gameObjects.Queen;
import commonlib.gameObjects.Swarm;
//import commonlib.GameObjects.Circle;

/**
 * Register classes that are going to be sent over the network.
 *
 * Explanation from Cryonet library documentation:
 *
 * This must be done on both the client and server, before any network communication occurs. It is very important that
 * the exact same classes are registered on both the client and server, and that they are registered in the exact same
 * order. Because of this, typically the code that registers classes is placed in a method on a class available to both
 * the client and server.
 *
 */
public class GameServerClientInitialization {
    public static boolean initialize(Kryo kryo)
    {
       //kryo.register(Circle.class);
       kryo.register(GameServerRequest.class);
       kryo.register(GameServerRequestMove.class);
       kryo.register(GameServerRequestAuth.class);
       kryo.register(GameServerResponseGameOver.class);
       kryo.register(Swarm.class);
       kryo.register(Queen.class);
       kryo.register(Particle.class);
       kryo.register(java.util.ArrayList.class);
       return true;
    }
}
