/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.handler;

import com.vio.sockets.annotation.Handler;
import com.vio.sockets.annotation.Service;
import com.vio.sockets.model.Message;
import com.vio.sockets.sessionhandlers.SessionService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 *
 * @author arito
 */


public class CustomMessageHandler implements MessageHandler.Whole<String> {

    private Session session = null;
    
    @Inject @Service
    private SessionService sessionHandler;
    
    public CustomMessageHandler(Session sessions){ this.session = session;}
    
    /**
     *
     * @param message
     */
    @Override
    public void onMessage(String message) {
        String username = (String) this.session.getUserProperties().get("username");
        System.out.println(message);
        if(username == null){
            this.session.getUserProperties().put("username", message);
            sessionHandler.sendNewUserNotification(session, message);
        } else {
            sessionHandler.broadcastMessage(new Message(message), this.session);
        }
    }
    
}
