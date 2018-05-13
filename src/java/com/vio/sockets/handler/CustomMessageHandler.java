package com.vio.sockets.handler;

import com.vio.sockets.configuration.MessageEndPoint;
import com.vio.sockets.model.Message;
import com.vio.sockets.model.UserConnected;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 *
 * @author arito
 */


public class CustomMessageHandler implements MessageHandler.Whole<String> {

    private final Session session;
    public CustomMessageHandler(Session sessions){ this.session = sessions;}
    
    /**
     *
     * @param message
     */
    @Override
    public void onMessage(String message /* username as well */) {
        String username = (String) this.session.getUserProperties().get("username");
        if(username == null){
            this.session.getUserProperties().put("username", message);
            final String NEW_USER = message + " has joined the room";
            broadcastMessage(new UserConnected(NEW_USER, "System"), session);    
        } else {
            broadcastMessage(new Message(message, username), session);
        }
    }
    
    private static boolean isAvailable(String username){
       Optional<Session> session = MessageEndPoint.CONNECTED_SESSIONS
                .stream()
                .filter(sessions -> sessions.getUserProperties().get("username").equals(username))
                .findFirst();
       
       return session.isPresent();
    }
    
    public static <T> void broadcastMessage(T message, Session session){
        MessageEndPoint.CONNECTED_SESSIONS
                .forEach((Session connectedSession) -> {        
                        synchronized (connectedSession){
                            try {
                                connectedSession
                                        .getBasicRemote()
                                        .sendObject(message);
                            } catch (IOException ex) {
                                Logger.getLogger(CustomMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (EncodeException ex) {
                                Logger.getLogger(CustomMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } 
                });
    }
    
    public static Set<String> connectedSessions(){
        Set<String> users = new HashSet<String>();
        MessageEndPoint
                .CONNECTED_SESSIONS
                .forEach(user -> users.add((String) user.getUserProperties().get("username")));
        
        return users;
    }
    
    
}
