package commonlib.network;

import com.esotericsoftware.kryonet.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: ALEXANDER
 * Date: 12/9/12
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Receive {
    public void connected(Connection connection);
    public void disconnected(Connection connection);
    public void received(Connection connection, Object object);
}
