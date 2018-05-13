package com.vio.sockets.configuration;

import com.vio.sockets.handler.CustomMessageHandler;
import com.vio.sockets.model.Message;
import com.vio.sockets.model.UserDisconnected;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

/**
 *
 * @author arito
 */


public class MessageEndPoint extends Endpoint {

    private static final Logger LOG = Logger.getLogger(MessageEndPoint.class.getName());
    public static Set<Session> CONNECTED_SESSIONS = Collections.synchronizedSet(new HashSet<Session>());
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        session.setMaxIdleTimeout(0);
        CONNECTED_SESSIONS.add(session);
        LOG.log(Level.INFO, "{0} has connected", session.getId());   
        session.addMessageHandler(new CustomMessageHandler(session));
        
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {   
        final String USER_DISCONNECTED = session.getUserProperties().get("username") + " has disconnected";
       // CustomMessageHandler.broadcastMessage(new UserDisconnected(USER_DISCONNECTED, "System"), session);
        CONNECTED_SESSIONS.remove(session);
    }
    
    public static Set<Session> getSessions(){
        return CONNECTED_SESSIONS;
    }
}
