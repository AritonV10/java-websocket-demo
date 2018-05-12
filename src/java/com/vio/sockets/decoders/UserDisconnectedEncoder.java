/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vio.sockets.decoders;

import com.vio.sockets.model.UserDisconnected;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author arito
 */
public class UserDisconnectedEncoder implements Encoder.Text<UserDisconnected> {

    @Override
    public String encode(UserDisconnected arg0) throws EncodeException {
        JsonObject ob = Json
                .createObjectBuilder()
                .add("notification", arg0.getNotification())
                .build();
        
        return ob.toString();
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
        
    }
    
}
