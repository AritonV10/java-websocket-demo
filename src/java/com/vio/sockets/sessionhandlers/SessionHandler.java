/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.sessionhandlers;

import com.vio.sockets.model.Message;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.websocket.Session;

/**
 *
 * @author arito
 */

public interface SessionHandler {
    
   
    
   /**
    * 
    * @param message
    * @param session 
    */
    void broadcastMessage(Message message, Session session);
    /**
     * 
     * @param username
     * @return 
     */
    boolean isConnected(String username);
    /**
     * 
     * @param session
     * @param username 
     */
    void newUserConnectedNotification(Session session, String username);
    /**
     * 
     * @param session
     * @param username 
     */
    void userClosedSessionNotification(Session session, String username);
    
    /**
     * 
     * @param test
     * @return 
     */
    int test_method(int test_int);
    
}
