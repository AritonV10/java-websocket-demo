/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.sessionhandlers;

import com.vio.sockets.annotation.Handler;
import com.vio.sockets.annotation.Service;
import com.vio.sockets.model.Message;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.Session;

/**
 *
 * @author arito
 */



@Service @ApplicationScoped
public class SessionService implements Serializable {
   
    @Inject @Handler
    private SessionHandler handler;
   
    public SessionService(){}
    
    
    
    public void closeSession(Session session){
        handler.userClosedSessionNotification(session, (String) session.getUserProperties().get("username"));
    }
    
    public void sendNewUserNotification(Session session, String username){
        handler.newUserConnectedNotification(session, username);
    }
    
    public void sendUserDisconnectedNotification(Session session){
        handler.userClosedSessionNotification(session, (String) session.getUserProperties().get("username"));
    }
    
    public void broadcastMessage(Message message, Session session){
        handler.broadcastMessage(message, session);
    }
    
    public int test_method (int test_int){
        return handler.test_method(test_int);
    }
              
}