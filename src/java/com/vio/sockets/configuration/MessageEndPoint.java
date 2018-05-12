/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.configuration;

import com.vio.sockets.sessionhandlers.SessionHandler;
import com.vio.sockets.annotation.Handler;
import com.vio.sockets.annotation.Service;
import com.vio.sockets.handler.CustomMessageHandler;
import com.vio.sockets.sessionhandlers.SessionService;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

/**
 *
 * @author arito
 */


public class MessageEndPoint extends Endpoint {

    private static final Logger LOG = Logger.getLogger(MessageEndPoint.class.getName());
    public static Set<Session> CONNECTED_SESSIONS = Collections.synchronizedSet(new HashSet<Session>());

    
    public MessageEndPoint(){}
    
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        CONNECTED_SESSIONS.add(session);
        LOG.log(Level.INFO, "{0} has connected", session.getId());
        session.addMessageHandler(new CustomMessageHandler(session));
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {   
        //sessionService.closeSession(session);
    }
    
    public static Set<Session> getSessions(){
        return CONNECTED_SESSIONS;
    }
}
