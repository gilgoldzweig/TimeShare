package cc.time_share.android.server;


import cc.time_share.android.models.Request;
import cc.time_share.android.models.User;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class ServerHandler {
    private static final ServerHandler ourInstance = new ServerHandler();

    public static ServerHandler getInstance() {
        return ourInstance;
    }

    private ServerHandler() {
    }

    public User getUserFromServer() {
        return new User();
    }
}