/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.sessionhandlers;

import com.vio.sockets.annotation.Handler;
import com.vio.sockets.configuration.MessageEndPoint;
import com.vio.sockets.model.AbstractDomain;
import com.vio.sockets.model.Message;
import com.vio.sockets.model.UserConnected;
import com.vio.sockets.model.UserDisconnected;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.lang.String;
import java.net.ConnectException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

/**
 *
 * @author arito
 */

@Handler @ApplicationScoped
public class SessionHandlerImpl implements SessionHandler{
    
    
    public SessionHandlerImpl(){};
    
    
    
    /**
     * 
     * @param message
     * @param session 
     */
    @Override
    public void broadcastMessage(Message message, Session session) {
       broadcastNotification(MessageEndPoint.CONNECTED_SESSIONS, com.vio.sockets.model.Message.class, message, session, "message");
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    @Override
    public boolean isConnected(String username) {
        /*
        Optional<String> isConnected_ = CONNECTED_USERS
                .stream()
                .filter((String user) -> user.equals(username))
                .findFirst();
               
        return isConnected_.isPresent();
        */
        
        return true;
    }
    /**
     * 
     * @param session
     * @param username 
     */
    @Override
    public void newUserConnectedNotification(Session session, String username) {
        broadcastNotification(MessageEndPoint.CONNECTED_SESSIONS, UserConnected.class, username, session, "connected");      
    }

    /**
     * 
     * @param session
     * @param username 
     */
    @Override
    public void userClosedSessionNotification(Session session, String username) {
        broadcastNotification(MessageEndPoint.CONNECTED_SESSIONS, UserDisconnected.class, username, session, "disconnected");
    }
    
    /**
     * 
     * @param <T>
     * @param clazz
     * @param ob
     * @param session
     * @param s 
     */
    private static <T> void broadcastNotification(Set<Session> CONNECTED_SESSIONS, Class<? extends AbstractDomain> clazz, T ob, Session session, String s) {
        if(s != null) switch(s){
            case "message":
                broadcastMessageToAll(CONNECTED_SESSIONS, clazz, ob, session);
                break;
            case "connected":
                broadcastNewNotification(CONNECTED_SESSIONS, clazz, ob, session, "User " + ob + " has joined the room");
                break;
            case "disconnected":
                broadcastNewNotification(CONNECTED_SESSIONS, clazz, ob, session, "User " + ob + " has left the room");
                break;
        }
    }
    private static <T> void broadcastMessageToAll(Set<Session> CONNECTED_SESSIONS, Class<? extends AbstractDomain> clazz, T ob, Session session){
        CONNECTED_SESSIONS
                .forEach((Session connectedSessions) -> {
                    synchronized (connectedSessions){
                        try {
                            session
                                    .getBasicRemote()
                                    .sendObject(ob);
                        } catch (IOException | EncodeException ex) {
                            Logger.getLogger(SessionHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });  
    }
    
    private static <T> void broadcastNewNotification(Set<Session> CONNECTED_SESSIONS, Class<? extends AbstractDomain> clazz, T ob, Session session, String notification){
        CONNECTED_SESSIONS
                .forEach((Session connectedSessions) -> {
                    synchronized (connectedSessions){
                        try {
                            try {
                                session
                                        .getBasicRemote()
                                        .sendObject(
                                                clazz
                                                        .getConstructor(String.class)
                                                        .newInstance(notification));
                            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                                Logger.getLogger(SessionHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException | EncodeException ex) {
                            Logger.getLogger(SessionHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });  
    }
    
    @Override
    public int test_method(int test_int) {
        return (test_int * 2);
    }
    
}
